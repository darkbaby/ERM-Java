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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.esynergy.erm.model.IExchangeRate;
import com.esynergy.erm.model.IFileUpload;
import com.esynergy.erm.model.IUser;
import com.esynergy.erm.model.form.ExchangeRateManualDetailForm;
import com.esynergy.erm.model.form.ExchangeRateManualForm;
import com.esynergy.erm.model.form.ExchangeRateManualSearchForm;
import com.esynergy.erm.model.form.FileUploadERManualForm;
import com.esynergy.erm.model.form.UserLogonForm;
import com.esynergy.erm.model.ob.CodeValue;
import com.esynergy.erm.model.ob.Country;
import com.esynergy.erm.model.ob.Currency;
import com.esynergy.erm.model.ob.ExchangeRateAutoHISTLog;
import com.esynergy.erm.model.ob.ExchangeRateDetail;
import com.esynergy.erm.model.ob.ExchangeRateManual;
import com.esynergy.erm.model.ob.FileUpload;
import com.esynergy.erm.model.ob.FileUploadERManual;
import com.esynergy.erm.model.ob.User;
import com.esynergy.erm.service.CodeValueService;
import com.esynergy.erm.service.CurrencyService;
import com.esynergy.erm.service.ExchangeRateAutoHISTLogService;
import com.esynergy.erm.service.ExchangeRateManualService;
import com.esynergy.erm.service.ExchangeRateDetailService;
import com.esynergy.erm.service.FileUploadService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.esynergy.common.web.action.ActionCommon;
import com.esynergy.erm.common.util.UIUtil;
import com.esynergy.erm.common.util.ValidatorUtil;

@SuppressWarnings({ "serial", "rawtypes" })
@Controller("exchangeRagteByManualAction")
public class ExchangeRagteByManualAction extends ActionCommon implements
		  ServletRequestAware, ModelDriven {

	private static final Logger logger = Logger.getLogger(ExchangeRagteByManualAction.class);
	private ExchangeRateManual exchangeRateManual = new ExchangeRateManual();
	private List<IExchangeRate> exchangeRateManualList = new ArrayList<IExchangeRate>();
	private ExchangeRateManualForm exchangeRateManualForm = new ExchangeRateManualForm();
	private List<ExchangeRateDetail> exchangeRateManualDetailRemoveList = new ArrayList<ExchangeRateDetail>();
	private ExchangeRateManualSearchForm exchangeRateManualSearchForm = new ExchangeRateManualSearchForm();
	private List<String> userUpdateList = new ArrayList<String>();
	private List<FileUpload> fileUploadRemoveList = new ArrayList<FileUpload>();
	private List<Currency> currencyList = new ArrayList<Currency>();
	private String parm;
	private List<CodeValue> currencyTypeList = new ArrayList<CodeValue>();
	private static String FILE_UPLOAD_URL = "/home/suse/workspace/ERM/manual_file";//"D:\\projects\\erm\\fileUpload\\ERByManual";
	/*private static String FILE_UPLOAD_URL = "D:\\projects\\erm\\fileUpload\\ERByManual";*/
	private String saveDirectory;
	private InputStream fileDownloadStream;
	private String fileDownloadName;
	private long contentLength;
	private Currency baseCurrency = new Currency();
	
	@Autowired private ExchangeRateManualService exchangeRateManualService;
	@Autowired private CurrencyService currencyService;
	@Autowired private FileUploadService fileUploadERManualService;
	@Autowired private ExchangeRateDetailService exchangeRateDetailService;
	@Autowired private CodeValueService codeValueService;
	
	@Autowired private ExchangeRateAutoHISTLogService exchangeRateAutoHISTLogService;

	@SuppressWarnings("unchecked")
	public void setServletRequest(HttpServletRequest request) {
		super.setServletRequest(request);
		request.setAttribute(MAIN_MENU_ATTR, MENU_EXCHANGE_RATE);
		request.setAttribute(SUB_MENU_ATTR, MENU_EXCHANGE_RATE_MANUAL);
		HttpSession session = request.getSession(false) == null ? request
				.getSession(true) : request.getSession(false);
		if (session.getAttribute(SESSION_CURRENCY_LIST) == null) {
			currencyList = currencyService.listAll();
			session.setAttribute(SESSION_CURRENCY_LIST, currencyList);
		} else {
			currencyList = (List<Currency>) session
					.getAttribute(SESSION_CURRENCY_LIST);
		}
		if(session.getAttribute(SESSION_CURRENCY_TYPE_LIST) == null) {
			currencyTypeList = codeValueService.listAllCurrencyType();
			session.setAttribute(SESSION_CURRENCY_TYPE_LIST, currencyTypeList);
		} else {
			currencyTypeList = (List<CodeValue>) session.getAttribute(SESSION_CURRENCY_TYPE_LIST);
		}
	}

 	public HttpServletRequest getReq() {
		return (HttpServletRequest) ActionContext.getContext().get(
				ServletActionContext.HTTP_REQUEST);
	} 

	public String prepareMangement() {
		 
		if(UIUtil.isEmptyOrNull((String)getReq().getAttribute("updated"))){
			super.errors.clear();
		}
		exchangeRateManualSearchForm = new ExchangeRateManualSearchForm();
		exchangeRateManualSearchForm.setRateDateStart(DATE_FORMAT.format(new Date()));
		exchangeRateManualSearchForm.setRateDateEnd(DATE_FORMAT.format(new Date()));
		super.addActionMsg("actionTitle", "header.mangement.exchange.rate.manual");
		userUpdateList = exchangeRateManualService.listAllUserUpdate();
		exchangeRateManualList = exchangeRateManualService.listExchangeRateManualByRateDate(new Date());
		
		return SUCCESS;
	}

	public String prepareEdit() {
		IUser user =(IUser) getReq().getSession(false).getAttribute(SESSION_USER);
		long idLong = Long
				.parseLong(UIUtil.prepareConvertStringToNumber(parm));
		exchangeRateManual = exchangeRateManualService.getById(idLong);
		if(!ValidatorUtil.checkPermission(user, "EditExchangeRateManual",exchangeRateManual.getCreatedUser())){
			return ERROR;
		}
		super.errors = new HashMap<String, Object>();
		super.actionMsg = new HashMap<String, Object>();

		super.addActionMsg("actionTitle", "header.edit.exchange.rate");
		HttpServletRequest request = getReq();
		HttpSession session = request.getSession();
		
		this.exchangeRateManualForm = new ExchangeRateManualForm();
		this.exchangeRateManualDetailRemoveList = new ArrayList<ExchangeRateDetail>();
		this.fileUploadRemoveList = new ArrayList<FileUpload>();
		populateObToForm(exchangeRateManual, exchangeRateManualForm);
		if(session.getAttribute(SESSION_FILE_UPLOAD_BY_MANUAL)!=null) 
			{session.removeAttribute(SESSION_FILE_UPLOAD_BY_MANUAL);}
		session.setAttribute(SESSION_FILE_UPLOAD_BY_MANUAL, exchangeRateManualForm.getFileUploadMap());
		super.errors = new HashMap<String, Object>();
		return SUCCESS;
	}
	public String prepareView(){
		super.errors = new HashMap<String, Object>();
		super.actionMsg = new HashMap<String, Object>();
		super.addActionMsg("actionTitle", "header.view.exchange.manual.rate");
		HttpServletRequest request = getReq();
		HttpSession session = request.getSession();
		exchangeRateManual = new ExchangeRateManual();
		exchangeRateManual = exchangeRateManualService.getById(Long
				.parseLong(UIUtil.prepareConvertStringToNumber(request
						.getParameter("parm"))));
		Map<String,IFileUpload> fileMap = new HashMap<String, IFileUpload>();
		if(exchangeRateManual!=null){
			if(exchangeRateManual.getFileUploadERManualList()!=null 
					&& exchangeRateManual.getFileUploadERManualList().size()>0){
				for(IFileUpload f:exchangeRateManual.getFileUploadERManualList()){
					fileMap.put(String.valueOf(f.getId()), f);
				}
			}
		}
		if(session.getAttribute(SESSION_FILE_UPLOAD_BY_MANUAL)!=null) 
			session.removeAttribute(SESSION_FILE_UPLOAD_BY_MANUAL);
	    session.setAttribute(SESSION_FILE_UPLOAD_BY_MANUAL, fileMap);
		 
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String downloadFile()   {
	 	HttpServletRequest request = getReq();
		HttpSession  session =  request.getSession(false);
		Map<String,Object> fileMap =(Map<String,Object>) session.getAttribute(SESSION_FILE_UPLOAD_BY_MANUAL);
		IFileUpload fileUploadERManual = (IFileUpload) fileMap.get(request.getParameter("parm"));
		 
			File file = new File(fileUploadERManual.getUrl());
			this.contentLength = file.length();
			try {
				this.fileDownloadStream = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			fileDownloadName = file.getName(); 
		 
		return SUCCESS;
	}

	public String prepareCreateForm() {
		IUser user =(IUser) getReq().getSession(false).getAttribute(SESSION_USER);
		if(!ValidatorUtil.checkPermission(user, "CreateExchangeRateManual")){
			return ERROR;
		}
		super.errors = new HashMap<String, Object>();
		super.actionMsg = new HashMap<String, Object>();
		super.addActionMsg("actionTitle", "header.add.exchange.rate");
		this.exchangeRateManualDetailRemoveList = new ArrayList<ExchangeRateDetail>();
		this.fileUploadRemoveList = new ArrayList<FileUpload>();
		this.exchangeRateManualForm = new ExchangeRateManualForm();
    	for(int i=0;i<5;i++){
    		exchangeRateManualForm.addFileUploadERManualList(new FileUploadERManualForm());
    	}
		super.errors = new HashMap<String, Object>();
		return SUCCESS;
	}

	public String save() throws IOException {
		super.errors = new HashMap<String, Object>();
		/*super.actionMsg = new HashMap<String, Object>();*/
		HttpSession session = getReq().getSession(false);
		IUser user = (IUser)session.getAttribute(SESSION_USER);
		baseCurrency = user.getCountry().getCurrency(); 
		if (!validateForm()) {
			for (FileUploadERManualForm fileT: exchangeRateManualForm.getFileUploadERManualList()) {
				if(fileT.getFile()!=null){
					fileT.setFile(null);  
					fileT.setName(null);
				}
			}
			return INPUT;

		} else {
			exchangeRateManual = new ExchangeRateManual();
			exchangeRateManualForm.setLastUpdateUser(user.getLogOnId());
			
			populateFormToOb(exchangeRateManualForm, exchangeRateManual);
			exchangeRateManual.setLastUpdateUser(user.getLogOnId());
			exchangeRateManualService.save(exchangeRateManual);

			if (exchangeRateManual.getFileUploadERManualList() != null) {
			      /* String classpath = request.getSession().getServletContext()
										.getRealPath("/")
										+ "FileUpload\\ERByManual";*/
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
						File destFile = new File(FILE_UPLOAD_URL + File.separator
								+ fileName);
						try {
							//FilesUtil.saveFile(fileUploadOb.getFile(), fileName, classpath);
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
			if (exchangeRateManualDetailRemoveList != null
					&& exchangeRateManualDetailRemoveList.size() > 0) {
				for (ExchangeRateDetail obDetail : exchangeRateManualDetailRemoveList) {
					exchangeRateDetailService.delete(obDetail);
				}
			}
		 	if(fileUploadRemoveList !=null 
					&& fileUploadRemoveList.size()>0){
				for(FileUpload filRemove:fileUploadRemoveList){
					 /*filRemove.setExchangeRateManual(exchangeRateManual);*/
					 fileUploadERManualService.delete(filRemove);
					 File file = new File(filRemove.getUrl());
					 file.delete();
				}
			}
		 	getReq().setAttribute("updated","updated");
		 	super.addActionMsg("saveSuccess",getText("msg.action.save.success"));
		 	errors.put("isSuccess", true);
			return SUCCESS;
		}
	}
	public String remove(){
		exchangeRateManualService.remove(exchangeRateManualService.getById(exchangeRateManualForm.getId()));
		if(exchangeRateManualForm.getFileUploadERManualList()!=null && exchangeRateManualForm.getFileUploadERManualList().size()>0){
			removeFiles(exchangeRateManualForm.getFileUploadERManualList());
		}
		getReq().setAttribute("updated","updated");
		super.addActionMsg("saveSuccess",getText("msg.action.remove.success"));
	 	errors.put("isSuccess", true);
		return SUCCESS;
	}

	public String search() throws ParseException {
		super.errors = new HashMap<String, Object>();
		super.actionMsg = new HashMap<String, Object>();
		super.addActionMsg("actionTitle", "header.mangement.exchange.rate.manual");
		this.exchangeRateManualList = exchangeRateManualService.search(exchangeRateManualSearchForm.getRateDateStart(), exchangeRateManualSearchForm.getRateDateEnd(), exchangeRateManualSearchForm.getUpdateBy());
 		return SUCCESS;

	}

	public String removeForm() {
		super.errors = new HashMap<String, Object>();
		//super.actionMsg = new HashMap<String, Object>();
		HttpSession session = getReq().getSession(false);
		IUser user = (IUser)session.getAttribute(SESSION_USER);
		baseCurrency = user.getCountry().getCurrency();
		List<ExchangeRateManualDetailForm> removeList = new ArrayList<ExchangeRateManualDetailForm>();
		for (int i = 0; i < exchangeRateManualForm.getExchangeRateDetailList()
				.size(); i++) {
			ExchangeRateManualDetailForm formDetail = exchangeRateManualForm
					.getExchangeRateDetailList().get(i);
			if (!UIUtil.isEmptyOrNull(formDetail.getChk())
					&& (formDetail.getChk().equals(CHK_ON) || formDetail
							.getChk().equals("true"))) {
				removeList.add(formDetail);
				if (formDetail.getId() != 0) {
					ExchangeRateDetail obDetail = new ExchangeRateDetail();
					populateFormToObDetail(formDetail, obDetail);
					exchangeRateManualDetailRemoveList.add(obDetail);
				}
			}
		}
		exchangeRateManualForm.getExchangeRateDetailList()
				.removeAll(removeList);
		return SUCCESS;
	}
	public String removeFileUpload() throws CloneNotSupportedException{
		int indexRemove = Integer.parseInt(this.parm.trim());
		FileUploadERManualForm tmpRemoeve  = new FileUploadERManualForm();
		FileUploadERManualForm formRemove = exchangeRateManualForm.getFileUploadERManualList().get(indexRemove);
		tmpRemoeve = (FileUploadERManualForm) formRemove.clone();
		if(tmpRemoeve.getId()!=0){
			FileUploadERManual fileUploadERManualOb = new FileUploadERManual();
			populateFormToObFileUpload(tmpRemoeve,fileUploadERManualOb);
			fileUploadRemoveList.add(fileUploadERManualOb);
		}
		exchangeRateManualForm.getFileUploadERManualList().get(indexRemove).setId(0);
		exchangeRateManualForm.getFileUploadERManualList().get(indexRemove).setFile(null);
		exchangeRateManualForm.getFileUploadERManualList().get(indexRemove).setName("");
		exchangeRateManualForm.getFileUploadERManualList().get(indexRemove).setUrl("");

		return SUCCESS;
	}
	public void removeFiles(List<FileUploadERManualForm> list){
		for(FileUploadERManualForm f:list){
			if(f.getId()!=0){
				File file = new File(f.getUrl());
				file.delete();
			}
			 
		}
	}
	public boolean validateForm() {
		errors.put("isError", false);
		errors = new HashMap<String, Object>();
		if (ValidatorUtil.checkString(exchangeRateManualForm.getRateDate(),
				true)) {
			addErrors("rateDateError", "error.require");
		}
		if(exchangeRateManualForm.getPairCurrencyType().equals("-1")){
			addErrors("errorTypeOfCurrency", "error.require");
		}
		if (exchangeRateManualForm.getExchangeRateDetailList() != null
				&& exchangeRateManualForm.getExchangeRateDetailList().size() > 0) {
			List<Object> exchangeRateDetailTMPList = new ArrayList<Object>();
			for (int i = 0; i < exchangeRateManualForm
					.getExchangeRateDetailList().size(); i++) {
				ExchangeRateManualDetailForm exchangeRateManualDetailForm = exchangeRateManualForm
						.getExchangeRateDetailList().get(i);
				exchangeRateManualDetailForm.setFirstCurrency(String.valueOf(baseCurrency.getId()));
				exchangeRateDetailTMPList.add(exchangeRateManualDetailForm
						.getPairCurrency()
						+ exchangeRateManualDetailForm.getFirstCurrency());
			}
			Set<Object> exchangeRateDetailDuplList = UIUtil
					.duplicateList(exchangeRateDetailTMPList);

			for (int i = 0; i < exchangeRateManualForm
					.getExchangeRateDetailList().size(); i++) {
				ExchangeRateManualDetailForm exchangeRateManualDetailForm = exchangeRateManualForm
						.getExchangeRateDetailList().get(i);
				exchangeRateManualDetailForm.setFirstCurrency(String.valueOf(baseCurrency.getId()));
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
								.getPairCurrency();
						param[1] = exchangeRateManualDetailForm
								.getFirstCurrency();
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
					}
				}
				// End Check Duplicate
				if (UIUtil.isEmptyOrNull(exchangeRateManualDetailForm
						.getBuyingRate())
						&& UIUtil.isEmptyOrNull(exchangeRateManualDetailForm
								.getSellingRate())) {
					addErrors("rateRequire" + i, "error.rate.require");
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
	 	form.setCreatedDate(ob.getCreatedDate());
	 	form.setCreatedUser(ob.getCreatedUser());
	 	form.setLastUpdateUser(ob.getLastUpdateUser());
		/*populateObToFormUser(ob.getCreatedUser(),form.getCreatedUser());
		populateObToFormUser(ob.getLastUpdateUser(),form.getLastUpdateUser());*/
		form.setRateDate(DATE_FORMAT.format(ob.getRateDate()));
		form.setRateDateStatic(DATE_FORMAT.format(ob.getRateDate()));
		form.setPairCurrencyType(String.valueOf(ob.getPariCurrencyType()));
		for (ExchangeRateDetail dtl : ob.getExchangeRateDetails()) {
			ExchangeRateManualDetailForm dtlForm = new ExchangeRateManualDetailForm();
			form.addExchangeRateDetailList(dtlForm);
			dtlForm.setId(dtl.getId());
			dtlForm.setPairCurrency(String.valueOf(dtl.getPairCurrency()
					.getId()));
			dtlForm.setPairCurrencyStatic(String.valueOf(dtl
					.getPairCurrency().getId()));
			dtlForm.setFirstCurrency(String.valueOf(dtl.getFirstCurrency()
					.getId()));
			dtlForm.setFirstCurrencyStatic(String.valueOf(dtl.getFirstCurrency()
					.getId()));
			dtlForm.setValue(VALUE_RATE_FORMAT.format(dtl.getValue()));
			dtlForm.setBuyingRate(EXCHANGE_RATE_FORMAT.format(dtl
					.getBuyingRate()));
			dtlForm.setSellingRate(EXCHANGE_RATE_FORMAT.format(dtl
					.getSellingRate()));
		}
		form.setFileUploadERManualList(new ArrayList<FileUploadERManualForm>());
		if (ob.getFileUploadERManualList() != null
				&& ob.getFileUploadERManualList().size() > 0) {
			Iterator itr = ob.getFileUploadERManualList().iterator();
			while (itr.hasNext()) {
				FileUploadERManual fileOb = (FileUploadERManual) itr.next();
				FileUploadERManualForm fileForm = new FileUploadERManualForm();
				fileForm.setId(fileOb.getId());
				fileForm.setName(fileOb.getName());
				fileForm.setUrl(fileOb.getUrl());
				form.putFileUploadMap(String.valueOf(fileOb.getId()), fileForm);
				form.addFileUploadERManualList(fileForm);
			}
		}
		while(form.getFileUploadERManualList().size()<5){
			form.addFileUploadERManualList(new FileUploadERManualForm());
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
		ob.setCreatedUser(form.getCreatedUser());
		ob.setLastUpdateUser(form.getCreatedUser());
		ob.setLastUpdateDate(form.getLastUpdateDate());
		ob.setPariCurrencyType(Long.parseLong(form.getPairCurrencyType()));

		if (!UIUtil.isEmptyOrNull(form.getRateDate())) {
			try {
				ob.setRateDate(DATE_FORMAT.parse((form.getRateDate())));
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
				ob.addExchangeRateDetails(exchangeRateDetail);
				populateFormToObDetail(exchangeRateManualDetailForm,
						exchangeRateDetail);
			}
		}
		FileUploadERManual fileUpLoadeOb = null;
		form.getFileUploadERManualList();
		if(form.getFileUploadERManualList()!=null && form.getFileUploadERManualList().size()>0){
			for (FileUploadERManualForm fileUplForm : form.getFileUploadERManualList()) {
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
		/*ob.setPairCurrency(currencyService.getById(Long.parseLong(form
				.getPairCurrency())));*/
		ob.setFirstCurrency(baseCurrency);
		ob.setValue(UIUtil.isEmptyOrNull(form.getValue()) ? 0d : Double
				.parseDouble(form.getValue()));
		ob.setBuyingRate((UIUtil.isEmptyOrNull(form.getBuyingRate()) ? 0d
				: Double.parseDouble(UIUtil.prepareConvertStringToNumber(form
						.getBuyingRate()))));
		ob.setSellingRate((UIUtil.isEmptyOrNull(form.getSellingRate()) ? 0d
				: Double.parseDouble(UIUtil.prepareConvertStringToNumber(form
						.getSellingRate()))));

	}

	public void populateFormToObFileUpload(FileUploadERManualForm form,
			FileUploadERManual ob) {
		    ob.setId(form.getId());
		    ob.setUrl(form.getUrl());
		    ob.setName(form.getName());
		    ob.setFile(form.getFile());
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return exchangeRateManualForm;
	}
	public ExchangeRateManual getExchangeRateManual() {
		return exchangeRateManual;
	}

	public void setExchangeRateManual(ExchangeRateManual exchangeRateManual) {
		this.exchangeRateManual = exchangeRateManual;
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

	public String getParm() {
		return parm;
	}

	public void setParm(String parm) {
		this.parm = parm;
	}
 

	public List<CodeValue> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CodeValue> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

}
