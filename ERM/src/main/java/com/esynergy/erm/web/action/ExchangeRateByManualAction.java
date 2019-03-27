package com.esynergy.erm.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.esynergy.erm.model.IExchangeRate;
import com.esynergy.erm.model.form.ExchangeRateManualDetailForm;
import com.esynergy.erm.model.form.ExchangeRateManualForm;
import com.esynergy.erm.model.form.ExchangeRateManualSearchForm;
import com.esynergy.erm.model.form.FileUploadForm;
import com.esynergy.erm.model.form.UserLogonForm;
import com.esynergy.erm.model.ob.CodeValue;
import com.esynergy.erm.model.ob.Currency;
import com.esynergy.erm.model.ob.ExchangeRateDetail;
import com.esynergy.erm.model.ob.ExchangeRateManual;
import com.esynergy.erm.model.ob.FileUpload;
import com.esynergy.erm.model.ob.FileUploadERManual;
import com.esynergy.erm.model.ob.ManualTarget;
import com.esynergy.erm.model.ob.User;
import com.esynergy.erm.service.CurrencyService;
import com.esynergy.erm.service.ExchangeRateManualService;
import com.esynergy.erm.service.ExchangeRateDetailService;
import com.esynergy.erm.service.FileUploadService;
import com.esynergy.erm.service.ManualTargetService;
import com.opensymphony.xwork2.ActionContext;

import oracle.net.aso.d;

import com.esynergy.common.web.action.ActionCommon;
import com.esynergy.erm.common.util.UIUtil;
import com.esynergy.erm.common.util.ValidatorUtil;

@SuppressWarnings({ "serial", "rawtypes" })
//@Controller("exchangeRagteByManualAction")
public class ExchangeRateByManualAction extends ActionCommon {

	private static final Logger logger = Logger.getLogger(ExchangeRateByManualAction.class);
//	private ExchangeRateManual exchangeRateManual = new ExchangeRateManual();
	private List<IExchangeRate> exchangeRateManualList = new ArrayList<IExchangeRate>();
	private ExchangeRateManualForm exchangeRateManualForm = new ExchangeRateManualForm();
	private ExchangeRateManualSearchForm exchangeRateManualSearchForm = new ExchangeRateManualSearchForm();
	private List<String> userUpdateList = new ArrayList<String>();
	private List<Currency> currencyList = new ArrayList<Currency>();
	private List<CodeValue> currencyTypeList = new ArrayList<CodeValue>();
	private List<CodeValue> valueList = new ArrayList<CodeValue>();
	private String FILE_UPLOAD_URL = "";
	private String saveDirectory;
	private InputStream fileDownloadStream;
	private String fileDownloadName;
	private long contentLength;
	
	private String menuName;
	
	@Autowired
	private MandatoryAsset mandatoryAsset;
	
	@Autowired private ExchangeRateManualService exchangeRateManualService;
	@Autowired private CurrencyService currencyService;
	@Autowired private FileUploadService fileUploadERManualService;
	@Autowired private ExchangeRateDetailService exchangeRateDetailService;
//	@Autowired private CodeValueService codeValueService;
	@Autowired private ManualTargetService manualTargetService;
	
	@SuppressWarnings("unchecked")
	public void setServletRequest(HttpServletRequest request) {
		super.setServletRequest(request);
		request.setAttribute(MAIN_MENU_ATTR, MENU_EXCHANGE_RATE);
		request.setAttribute(SUB_MENU_ATTR, MENU_EXCHANGE_RATE_MANUAL);
		request.setAttribute(SUB_MENU1_ATTR, MENU_EXCHANGE_RATE_MANUAL_RATE_MANAGE);
		
//		mandatoryAsset.initialAsset();
		currencyList = mandatoryAsset.getCurrencyList();
		currencyTypeList = mandatoryAsset.getExtractionCurrencyTypeList();
		valueList = mandatoryAsset.getExtractionValueList();
		FILE_UPLOAD_URL = mandatoryAsset.getManualDirectory();
	}

 	public HttpServletRequest getReq() {
		return (HttpServletRequest) ActionContext.getContext().get(
				ServletActionContext.HTTP_REQUEST);
	}

	public String prepareManagement() {
		if(getReq().getParameter("updated") != null && getReq().getParameter("updated").equalsIgnoreCase("true")){
		 	super.addActionMsg("resultMessage",getText("msg.action.save.success"));
		 	super.addErrors("haveResult", true);
		}
		if(getReq().getParameter("removed") != null && getReq().getParameter("removed").equalsIgnoreCase("true")){
			super.addActionMsg("resultMessage",getText("msg.action.remove.success"));
		 	super.addErrors("haveResult", true);
		}
		
		menuName = getText("header.mangement.exchange.rate.manual");
		
		exchangeRateManualSearchForm = new ExchangeRateManualSearchForm();
		exchangeRateManualSearchForm.setRateDateStart(DATE_FORMAT.format(new Date()));
		exchangeRateManualSearchForm.setRateDateEnd(DATE_FORMAT.format(new Date()));
		exchangeRateManualSearchForm.setUpdateBy("");
		userUpdateList = exchangeRateManualService.listAllUserUpdate();
		exchangeRateManualList = exchangeRateManualService.listExchangeRateManualByRateDate(new Date());
		
		return SUCCESS;
	}
	
	public String prepareEdit() {
		
		menuName = getText("header.edit.exchange.rate");
		
		long idLong = Long
				.parseLong(UIUtil.prepareConvertStringToNumber(getReq().getParameter("parmID")));
		ExchangeRateManual exchangeRateManual = exchangeRateManualService.getById(idLong);
//		if(!ValidatorUtil.checkPermission(iUser, "EditExchangeRateManual",exchangeRateManual.getCreatedUser())){
//			return ERROR;
//		}
		
		this.exchangeRateManualForm = new ExchangeRateManualForm();
		populateObToForm(exchangeRateManual, exchangeRateManualForm);
		return SUCCESS;
	}
	
	public String prepareView(){
		menuName = getText("header.view.exchange.manual.rate");

		HttpServletRequest request = getReq();
		HttpSession session = request.getSession();
		ExchangeRateManual exchangeRateManual = new ExchangeRateManual();
		exchangeRateManual = exchangeRateManualService.getById(Long
				.parseLong(UIUtil.prepareConvertStringToNumber(request
						.getParameter("parmID"))));
		this.exchangeRateManualForm = new ExchangeRateManualForm();
		populateObToForm(exchangeRateManual, exchangeRateManualForm);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String downloadFile()   {
	 	HttpServletRequest request = getReq();
		 
	 	String idObject = request.getParameter("parmID");
	 	String urlDownload = request.getParameter("parmURL");
		
		try {
		 	urlDownload = java.net.URLDecoder.decode(urlDownload, "UTF-8");
		 	File file = new File(urlDownload);
			this.contentLength = file.length();
			this.fileDownloadStream = new FileInputStream(file);
			this.fileDownloadName = file.getName(); 
			return SUCCESS;
		} catch (FileNotFoundException e) {
			return "file_not_found";
		}
		catch (Exception e) {
			return "file_not_found";
		}
	}

	public String prepareCreateForm() {
		
		menuName = getText("header.add.exchange.rate");

		this.exchangeRateManualForm = new ExchangeRateManualForm();

		List<ManualTarget> manualTargetByUserIDList = new ArrayList<ManualTarget>();
		if(iUser.getAuthorizeGroup().getId() == 1) {
			 manualTargetByUserIDList = manualTargetService.listAllActiveNotExpire();
		}
		else if(iUser.getAuthorizeGroup().getId() == 3) {
			 manualTargetByUserIDList = manualTargetService.getByOwnerID(iUser.getId());
		}
//		for(int runningIndex=manualTargetByUserIDList.size()-1 ; runningIndex>=0; runningIndex--) {
//			ManualTarget item = manualTargetByUserIDList.get(runningIndex);
//			Object[] param = new Object[5];
//			param[0] = item.getBaseCurrency().getId();
//			param[1] = item.getPairCurrency().getId();
//			param[2] = 0;
//			param[3] = DATE_FORMAT.format(new Date());
//			param[4] = 0;
//			List<Long> ExchangeRateManualDetailListChk = exchangeRateManualService
//					.getExchangeRateManualByRateDateAndCurrencyForDupChk(param);
//			if(ExchangeRateManualDetailListChk != null 
//					&& ExchangeRateManualDetailListChk.size() > 0) {
//				manualTargetByUserIDList.remove(runningIndex);
//			}
//		}
		for(ManualTarget item : manualTargetByUserIDList) {
			ExchangeRateManualDetailForm ermdForm = new ExchangeRateManualDetailForm();
			ermdForm.setFirstCurrency(String.valueOf(item.getBaseCurrency().getId()));
			ermdForm.setFirstCurrencyStatic(item.getBaseCurrency().getCode());
			ermdForm.setPairCurrency(String.valueOf(item.getPairCurrency().getId()));
			ermdForm.setPairCurrencyStatic(item.getPairCurrency().getCode());
			ermdForm.setValue("1");
			exchangeRateManualForm.addExchangeRateDetailList(ermdForm);
		}
    	for(int i=0;i<5;i++){
    		exchangeRateManualForm.addFileUploadList(new FileUploadForm());
    	}

    	return SUCCESS;
	}

	public String save() throws IOException {
		
		if (!validateForm()) {
			for (FileUploadForm fileT: exchangeRateManualForm.getFileUploadList()) {
				if(fileT.getFile()!=null && fileT.getId() == 0){
					fileT.setFile(null);  
					fileT.setName(null);
				}
			}
			return INPUT;

		} else {
			ExchangeRateManual exchangeRateManual = new ExchangeRateManual();
			populateFormToOb(exchangeRateManualForm, exchangeRateManual);
			
			if(exchangeRateManual.getId() > 0) {
				ExchangeRateManual temp = exchangeRateManualService.getById(exchangeRateManual.getId());
				exchangeRateManual.setLastUpdateUser(iUser.getLogOnId());
				exchangeRateManual.setLastUpdateDate(new Date());
				exchangeRateManual.setCreatedDate(temp.getCreatedDate());
				exchangeRateManual.setCreatedUser(temp.getCreatedUser());
			}
			else {
				exchangeRateManual.setLastUpdateUser(iUser.getLogOnId());
				exchangeRateManual.setLastUpdateDate(new Date());
				exchangeRateManual.setCreatedUser(iUser.getLogOnId());
				exchangeRateManual.setCreatedDate(new Date());
			}
			
			exchangeRateManualService.save(exchangeRateManual);

			if (exchangeRateManual.getFileUploadERManualList() != null) {
				for (FileUpload fileUploadOb: exchangeRateManual.getFileUploadERManualList()) {
				 
					if(fileUploadOb.getFile()!=null){
						String fileName = null;
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyyMMddHHmmss");
						String dateStr = sdf.format(new Date());
						String idStr = String.valueOf(fileUploadOb.getId());
						while (idStr.length() < 5) {
							idStr = "0" + idStr;
						}
						fileName = dateStr
								+ idStr
								+ "."
								+ FilenameUtils
										.getExtension(fileUploadOb.getName());
						
						try {
//							String tempPathFile = "D:\\ERM";
//							File destDirectory = new File(tempPathFile);
//							File destFile = new File(tempPathFile + File.separator
//									+ fileName);
//							fileUploadOb.setUrl(tempPathFile + "/" + fileName);
							
							File destDirectory = new File(FILE_UPLOAD_URL);
							if(destDirectory.exists() && destDirectory.isDirectory()) {
								
							}
							else {
								destDirectory.mkdirs();
							}
							
							File destFile = new File(FILE_UPLOAD_URL + File.separator
									+ fileName);
						
						 	FileUtils.copyFile(fileUploadOb.getFile(), destFile);
							fileUploadOb.setName(fileName);
							fileUploadOb.setUrl(FILE_UPLOAD_URL + "/" + fileName);
							fileUploadERManualService.update(fileUploadOb);
						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}
				}
			}  
			if (exchangeRateManualForm.getExchangeRateDetailFormRemoveList() != null
					&& exchangeRateManualForm.getExchangeRateDetailFormRemoveList().size() > 0) {
				for (Long obID : exchangeRateManualForm.getExchangeRateDetailFormRemoveList()) {
					ExchangeRateDetail tempObDetail = new ExchangeRateDetail();
					tempObDetail.setId(obID);
					exchangeRateDetailService.delete(tempObDetail);
				}
			}
		 	if(exchangeRateManualForm.getFileUploadFormRemoveList() !=null 
					&& exchangeRateManualForm.getFileUploadFormRemoveList().size()>0){
				for(FileUploadForm filRemove:exchangeRateManualForm.getFileUploadFormRemoveList()){
					FileUpload tempOBFileUpload = new FileUpload();
					tempOBFileUpload.setId(filRemove.getId());
					fileUploadERManualService.delete(tempOBFileUpload);
					File file = new File(filRemove.getUrl());
					file.delete();
				}
			}

			return SUCCESS;
		}
	}
	
	public String remove() {
//		ExchangeRateManual exchangeRateManual = new ExchangeRateManual();
//		ExchangeRateManual tempEx = exchangeRateManualService.getById(exchangeRateManualForm.getId());
//		exchangeRateManual = (ExchangeRateManual)tempEx.clone();
//		populateFormToOb(exchangeRateManualForm, exchangeRateManual);
//		
//		if(exchangeRateManual.getId() > 0) {
//			ExchangeRateManual temp = exchangeRateManualService.getById(exchangeRateManual.getId());
//			exchangeRateManual.setLastUpdateUser(iUser.getLogOnId());
//			exchangeRateManual.setLastUpdateDate(new Date());
//			exchangeRateManual.setCreatedDate(temp.getCreatedDate());
//			exchangeRateManual.setCreatedUser(temp.getCreatedUser());
//		}
//		else {
//			exchangeRateManual.setLastUpdateUser(iUser.getLogOnId());
//			exchangeRateManual.setLastUpdateDate(new Date());
//			exchangeRateManual.setCreatedUser(iUser.getLogOnId());
//			exchangeRateManual.setCreatedDate(new Date());
//		}
		
//		exchangeRateManualService.remove(exchangeRateManual);
		
		
		exchangeRateManualService.remove(exchangeRateManualService.getById(exchangeRateManualForm.getId()));
		if(exchangeRateManualForm.getFileUploadList()!=null && exchangeRateManualForm.getFileUploadList().size()>0){
			removeFiles(exchangeRateManualForm.getFileUploadList());
		}
		return SUCCESS;
	}

	public String search() throws ParseException {
		menuName = getText("header.mangement.exchange.rate.manual");
		userUpdateList = exchangeRateManualService.listAllUserUpdate();
		
		
		if(UIUtil.isEmptyOrNull(exchangeRateManualSearchForm.getRateDateStart()) 
				|| UIUtil.isEmptyOrNull(exchangeRateManualSearchForm.getRateDateEnd())) {
			exchangeRateManualSearchForm.setRateDateStart(DATE_FORMAT.format(new Date()));
			exchangeRateManualSearchForm.setRateDateEnd(DATE_FORMAT.format(new Date()));
		}
		
		exchangeRateManualList = exchangeRateManualService.search(exchangeRateManualSearchForm.getRateDateStart(),
				exchangeRateManualSearchForm.getRateDateEnd(), exchangeRateManualSearchForm.getUpdateBy());
 		return SUCCESS;
	}

	public String removeFileUpload() throws CloneNotSupportedException{
		int indexRemove = Integer.parseInt(getReq().getParameter("parmID"));
		FileUploadForm tmpRemoeve  = new FileUploadForm();
		FileUploadForm formRemove = exchangeRateManualForm.getFileUploadList().get(indexRemove);
		tmpRemoeve = (FileUploadForm) formRemove.clone();
		if(tmpRemoeve.getId()!=0){
			exchangeRateManualForm.getFileUploadFormRemoveList().add(tmpRemoeve);
		}
		exchangeRateManualForm.getFileUploadList().get(indexRemove).setId(0);
		exchangeRateManualForm.getFileUploadList().get(indexRemove).setFile(null);
		exchangeRateManualForm.getFileUploadList().get(indexRemove).setName("");
		exchangeRateManualForm.getFileUploadList().get(indexRemove).setUrl("");
		
		//Clear file other is not save
		if(exchangeRateManualForm.getId() == 0) {
			for (int i = 0; i < exchangeRateManualForm.getFileUploadList().size(); i++) {
				if(exchangeRateManualForm.getFileUploadList().get(i).getId() == 0) {
					exchangeRateManualForm.getFileUploadList().get(i).setFile(null);
					exchangeRateManualForm.getFileUploadList().get(i).setName("");
					exchangeRateManualForm.getFileUploadList().get(i).setUrl("");
				}
			}
		}

		return SUCCESS;
	}
	public void removeFiles(List<FileUploadForm> list){
		for(FileUploadForm f:list){
			if(f.getId()!=0){
				File file = new File(f.getUrl());
				file.delete();
			}
		}
	}
	public boolean validateForm() {
		if (ValidatorUtil.checkString(exchangeRateManualForm.getRateDate(),
				true)) {
			addErrors("rateDateError", "error.require");
		}
		if(exchangeRateManualForm.getPairCurrencyType().equals("-1")){
			addErrors("errorTypeOfCurrency", "error.require");
		}
		if (exchangeRateManualForm.getExchangeRateDetailList() != null
				&& exchangeRateManualForm.getExchangeRateDetailList().size() > 0) {
			
			for (int i = exchangeRateManualForm.getExchangeRateDetailList().size()-1; i >= 0; i--) {
				ExchangeRateManualDetailForm exchangeRateManualDetailForm = exchangeRateManualForm
						.getExchangeRateDetailList().get(i);
				if(UIUtil.isEmptyOrNull(exchangeRateManualDetailForm.getBuyingRate())
						&& UIUtil.isEmptyOrNull(exchangeRateManualDetailForm.getSellingRate())) {
					exchangeRateManualForm.getExchangeRateDetailList().remove(i);
				}
			}
			
			
			List<Object> exchangeRateDetailTMPList = new ArrayList<Object>();
			for (int i = 0; i < exchangeRateManualForm
					.getExchangeRateDetailList().size(); i++) {
				ExchangeRateManualDetailForm exchangeRateManualDetailForm = exchangeRateManualForm
						.getExchangeRateDetailList().get(i);				
				//cho comment 2018/03/20 : do not fix base currency according to country of user
//				exchangeRateManualDetailForm.setFirstCurrency(String.valueOf(baseCurrency.getId()));
				exchangeRateDetailTMPList.add(exchangeRateManualDetailForm
						.getPairCurrency()
						+ exchangeRateManualDetailForm.getFirstCurrency());
			}
			Set<Object> exchangeRateDetailDuplList = UIUtil
					.duplicateList(exchangeRateDetailTMPList);

			boolean isAtLeastOneRowValid = false;
			for (int i = 0; i < exchangeRateManualForm
					.getExchangeRateDetailList().size(); i++) {
				ExchangeRateManualDetailForm exchangeRateManualDetailForm = exchangeRateManualForm
						.getExchangeRateDetailList().get(i);
				//cho comment 2018/03/20 : do not fix base currency according to country of user
//				exchangeRateManualDetailForm.setFirstCurrency(String.valueOf(baseCurrency.getId()));
				if (ValidatorUtil.checkString(
						exchangeRateManualDetailForm.getPairCurrency(), true)) {
					addErrors("firstCurrencyError" + i, "error.require");
				}
				/*if (ValidatorUtil.checkString(
						exchangeRateManualDetailForm.getPairCurrency(), true)) {
					addErrors("pairCurrencyError" + i, "error.require");
				}*/
				if (exchangeRateManualDetailForm.getPairCurrency().equals(
						exchangeRateManualDetailForm.getFirstCurrency())) {
					addErrors("currencyError" + i, "error.currency.first.pair");
				}
				// Start Check Duplicate
				if (!UIUtil.isEmptyOrNull(exchangeRateManualDetailForm
						.getPairCurrency())
						&& !UIUtil.isEmptyOrNull(exchangeRateManualDetailForm
								.getFirstCurrency())
						&& !UIUtil.isEmptyOrNull(exchangeRateManualForm
								.getRateDate())) {
					if (!UIUtil.isEmptyOrNull(exchangeRateManualForm
							.getRateDate())) {
						if (exchangeRateDetailDuplList
								.contains(exchangeRateManualDetailForm
										.getPairCurrency()
										+ exchangeRateManualDetailForm
												.getFirstCurrency())) {
							Currency firstCurrency = currencyService
									.getById(Long
											.parseLong(exchangeRateManualDetailForm
													.getPairCurrency()));
							Currency pairCurrency = currencyService
									.getById(Long
											.parseLong(exchangeRateManualDetailForm
													.getFirstCurrency()));
							String[] errMsgParm = { firstCurrency.getCode(),
									pairCurrency.getCode(),
									exchangeRateManualForm.getRateDate() };
							addErrors("rateDupplicate" + i,
									"error.rate.dupplicate", errMsgParm);
						}

						Object[] param = new Object[5];
						param[0] = exchangeRateManualDetailForm
								.getFirstCurrency();
						param[1] = exchangeRateManualDetailForm
								.getPairCurrency();
						param[2] = exchangeRateManualDetailForm.getId();
						param[3] = exchangeRateManualForm.getRateDate();
						param[4] = exchangeRateManualForm.getId();
						List<Long> ExchangeRateManualDetailListChk = exchangeRateManualService
								.getExchangeRateManualByRateDateAndCurrencyForDupChk(param);
						if (ExchangeRateManualDetailListChk != null
								&& ExchangeRateManualDetailListChk.size() > 0) {

							Currency firstCurrency = currencyService
									.getById(Long
											.parseLong(exchangeRateManualDetailForm
													.getFirstCurrency()));
							Currency pairCurrency = currencyService
									.getById(Long
											.parseLong(exchangeRateManualDetailForm
													.getPairCurrency()));
							String[] errMsgParm = { firstCurrency.getCode(),
									pairCurrency.getCode(),
									exchangeRateManualForm.getRateDate() };
							addErrors("rateDupplicate" + i,
									"error.rate.dupplicate", errMsgParm);
						}
					}
				}
				// End Check Duplicate
				if (UIUtil.isEmptyOrNull(exchangeRateManualDetailForm
						.getBuyingRate())
						&& UIUtil.isEmptyOrNull(exchangeRateManualDetailForm
								.getSellingRate())) {
					addErrors("rateRequire" + i, "error.rate.require");
				}
				else {
					isAtLeastOneRowValid = true;
				}
				if(isAtLeastOneRowValid) {
					for(int runningRemove=0;runningRemove<exchangeRateManualForm.getExchangeRateDetailList().size();
							runningRemove++) {
						errors.remove("rateRequire" + runningRemove);
					}
				}
				if (!UIUtil.isEmptyOrNull(exchangeRateManualDetailForm
						.getBuyingRate())) {
					if (ValidatorUtil.checkAmount(
							exchangeRateManualDetailForm.getBuyingRate(),
							MIN_RATE, MAX_RATE, DECIMAL_RATE)) {
						String[] errMsgParm = { MIN_RATE_STR, MAX_RATE_STR };
						addErrors("buyingRateError" + i, "error.rate.format",
								errMsgParm);
					}
				}
				if (!UIUtil.isEmptyOrNull(exchangeRateManualDetailForm
						.getSellingRate())) {
					if (ValidatorUtil.checkAmount(
							exchangeRateManualDetailForm.getSellingRate(),
							MIN_RATE, MAX_RATE, DECIMAL_RATE)) {
						String[] errMsgParm = { MIN_RATE_STR, MAX_RATE_STR };
						addErrors("sellingRateError" + i, "error.rate.format",
								errMsgParm);
					}
				}
			}
		} else {
			addErrors("rateDetailError", "error.require");
		}

		if (errors.isEmpty()) {
			return true;
		}
		super.addActionMsg("error",getText("error.action.message"));
		errors.put("isError", true);
		return false;
	}
	public void populateObToFormUser(User ob,UserLogonForm form){
		form = new UserLogonForm();
		form.setId(ob.getId());
		form.setUserLogonId(ob.getLogOnId());
		form.setFirstName(ob.getFirstName());
		form.setLastName(ob.getLastName());
		form.setPwd(ob.getPwd());
	}

	public void populateObToForm(ExchangeRateManual ob,
			ExchangeRateManualForm form) {
		form.setId(ob.getId());
//	 	form.setCreatedDate(ob.getCreatedDate());
//	 	form.setCreatedUser(ob.getCreatedUser());
//	 	form.setLastUpdateUser(ob.getLastUpdateUser());
		/*populateObToFormUser(ob.getCreatedUser(),form.getCreatedUser());
		populateObToFormUser(ob.getLastUpdateUser(),form.getLastUpdateUser());*/
		form.setRateDate(DATE_FORMAT.format(ob.getRateDate()));
		form.setRateDateStatic(DATE_FORMAT.format(ob.getRateDate()));
		form.setRefDate(DATE_FORMAT.format(ob.getRefDate()));
		form.setPairCurrencyType(String.valueOf(ob.getPariCurrencyType()));
		for (ExchangeRateDetail dtl : ob.getExchangeRateDetails()) {
			ExchangeRateManualDetailForm dtlForm = new ExchangeRateManualDetailForm();
			
			dtlForm.setId(dtl.getId());
			dtlForm.setPairCurrency(String.valueOf(dtl.getPairCurrency()
					.getId()));
			dtlForm.setPairCurrencyStatic(String.valueOf(dtl.getPairCurrency()
					.getCode()));
			dtlForm.setFirstCurrency(String.valueOf(dtl.getFirstCurrency()
					.getId()));
			dtlForm.setFirstCurrencyStatic(String.valueOf(dtl.getFirstCurrency()
					.getCode()));
//			String tempValue = VALUE_RATE_FORMAT.format(dtl.getValue());
//			tempValue = tempValue.replaceAll(",", "");
//			dtlForm.setValue(tempValue);
//			dtlForm.setValue(VALUE_RATE_FORMAT.format(dtl.getValue()));
			dtlForm.setValue((int)dtl.getValue() + "");
			dtlForm.setBuyingRate(EXCHANGE_RATE_FORMAT.format(dtl
					.getBuyingRate()));
			dtlForm.setSellingRate(EXCHANGE_RATE_FORMAT.format(dtl
					.getSellingRate()));
			form.addExchangeRateDetailList(dtlForm);
		}
		form.setFileUploadList(new ArrayList<FileUploadForm>());
		if (ob.getFileUploadERManualList() != null
				&& ob.getFileUploadERManualList().size() > 0) {
			Iterator itr = ob.getFileUploadERManualList().iterator();
			while (itr.hasNext()) {
				FileUploadERManual fileOb = (FileUploadERManual) itr.next();
				FileUploadForm fileForm = new FileUploadForm();
				fileForm.setId(fileOb.getId());
				fileForm.setName(fileOb.getName());
				fileForm.setUrl(fileOb.getUrl());
//				form.putFileUploadMap(String.valueOf(fileOb.getId()), fileForm);
				form.addFileUploadList(fileForm);
			}
		}
		while(form.getFileUploadList().size()<5){
			form.addFileUploadList(new FileUploadForm());
		}
	}
	
	public void populateFormToObUser(UserLogonForm form, User ob){
		ob = new User();
		ob.setId(form.getId());
		ob.setLogOnId(form.getUserLogonId());
		ob.setFirstName(form.getFirstName());
		ob.setLastName(form.getLastName());
		ob.setPwd(form.getPwd());
	}
	
	
	public void populateFormToOb(ExchangeRateManualForm form,
			ExchangeRateManual ob) {
		ob.setId(form.getId());
//		ob.setCreatedUser(form.getCreatedUser());
//		ob.setLastUpdateUser(form.getCreatedUser());
//		ob.setLastUpdateDate(form.getLastUpdateDate());
		ob.setPariCurrencyType(Long.parseLong(form.getPairCurrencyType()));

		if (!UIUtil.isEmptyOrNull(form.getRateDate())) {
			try {
				ob.setRateDate(DATE_FORMAT.parse((form.getRateDate())));
				ob.setRefDate(DATE_FORMAT.parse((form.getRateDate())));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (form.getExchangeRateDetailList() != null
				&& form.getExchangeRateDetailList().size() > 0) {
			for (ExchangeRateManualDetailForm exchangeRateManualDetailForm : form
					.getExchangeRateDetailList()) {
				ExchangeRateDetail exchangeRateDetail = new ExchangeRateDetail();
				exchangeRateDetail.setExchangeRate(ob);
				
				if(UIUtil.isEmptyOrNull(exchangeRateManualDetailForm.getBuyingRate()) 
						&& UIUtil.isEmptyOrNull(exchangeRateManualDetailForm.getSellingRate())) {
				
				}
				else {
					ob.addExchangeRateDetails(exchangeRateDetail);
					populateFormToObDetail(exchangeRateManualDetailForm,
							exchangeRateDetail);
				}
			}
		}
		FileUploadERManual fileUpLoadeOb = null;
		if(form.getFileUploadList()!=null && form.getFileUploadList().size()>0){
			for (FileUploadForm fileUplForm : form.getFileUploadList()) {
				   if(fileUplForm.getFile()!=null || fileUplForm.getId()!=0){
						fileUpLoadeOb = new FileUploadERManual();
						fileUpLoadeOb.setExchangeRateManual(ob);
						ob.addFileUploadERManualList(fileUpLoadeOb);
						populateFormToObFileUpload(fileUplForm,fileUpLoadeOb);
				   }
			}
		}
	}

	public void populateFormToObDetail(ExchangeRateManualDetailForm form,
			ExchangeRateDetail ob) {
		ob.setId(form.getId());
		ob.setPairCurrency(currencyService.getById(Long.parseLong(form
				.getPairCurrency())));
		//cho comment 2018/03/20 : do not fix base currency according to country of user 
		//ob.setFirstCurrency(baseCurrency);
		ob.setFirstCurrency(currencyService.getById(Long.parseLong(form
				.getFirstCurrency())));
		ob.setValue(UIUtil.isEmptyOrNull(form.getValue()) ? 0d : Double
				.parseDouble(form.getValue()));
		ob.setBuyingRate((UIUtil.isEmptyOrNull(form.getBuyingRate()) ? 0d
				: Double.parseDouble(UIUtil.prepareConvertStringToNumber(form
						.getBuyingRate()))));
		ob.setSellingRate((UIUtil.isEmptyOrNull(form.getSellingRate()) ? 0d
				: Double.parseDouble(UIUtil.prepareConvertStringToNumber(form
						.getSellingRate()))));
	}

	public void populateFormToObFileUpload(FileUploadForm form,
			FileUploadERManual ob) {
		    ob.setId(form.getId());
		    ob.setUrl(form.getUrl());
		    ob.setName(form.getName());
		    ob.setFile(form.getFile());
	}
	public ExchangeRateManualForm getExchangeRateManualForm() {
		return exchangeRateManualForm;
	}

	public void setExchangeRateManualForm(
			ExchangeRateManualForm exchangeRateManualForm) {
		this.exchangeRateManualForm = exchangeRateManualForm;
	}

	public List<Currency> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<Currency> currencyList) {
		this.currencyList = currencyList;
	}

	public List<IExchangeRate> getExchangeRateManualList() {
		return exchangeRateManualList;
	}

	public void setExchangeRateManualList(
			List<IExchangeRate> exchangeRateManualList) {
		this.exchangeRateManualList = exchangeRateManualList;
	}

	public List<String> getUserUpdateList() {
		return userUpdateList;
	}

	public void setUserUpdateList(List<String> userUpdateList) {
		this.userUpdateList = userUpdateList;
	}

	public ExchangeRateManualSearchForm getExchangeRateManualSearchForm() {
		return exchangeRateManualSearchForm;
	}

	public void setExchangeRateManualSearchForm(
			ExchangeRateManualSearchForm exchangeRateManualSearchForm) {
		this.exchangeRateManualSearchForm = exchangeRateManualSearchForm;
	}

	public String getSaveDirectory() {
		return saveDirectory;
	}

	public void setSaveDirectory(String saveDir) {
		this.saveDirectory = saveDir;
	}

	public InputStream getFileDownloadStream() {
		return fileDownloadStream;
	}

	public void setFileDownloadStream(InputStream fileDownloadStream) {
		this.fileDownloadStream = fileDownloadStream;
	}

	public String getFileDownloadName() {
		return fileDownloadName;
	}

	public void setFileDownloadName(String fileDownloadName) {
		this.fileDownloadName = fileDownloadName;
	}

	public long getContentLength() {
		return contentLength;
	}

	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}

	public List<CodeValue> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CodeValue> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public List<CodeValue> getValueList() {
		return valueList;
	}

	public void setValueList(List<CodeValue> valueList) {
		this.valueList = valueList;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

}
