package com.esynergy.erm.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.esynergy.common.web.action.ActionCommon;
import com.esynergy.erm.common.util.UIUtil;
import com.esynergy.erm.common.util.ValidatorUtil;
import com.esynergy.erm.model.IUser;
import com.esynergy.erm.model.form.GenerateRateBankForm;
import com.esynergy.erm.model.form.GenerateRateDetailForm;
import com.esynergy.erm.model.form.GenerateRateFileForm;
import com.esynergy.erm.model.form.GenerateRateForm;
import com.esynergy.erm.model.form.GenerateRateSearchForm;
import com.esynergy.erm.model.form.GenerateRateTimeForm;
import com.esynergy.erm.model.ob.Bank;
import com.esynergy.erm.model.ob.CodeValue;
import com.esynergy.erm.model.ob.Currency;
import com.esynergy.erm.model.ob.Extraction;
import com.esynergy.erm.model.ob.ExtractionDetail;
import com.esynergy.erm.model.ob.GenerateRate;
import com.esynergy.erm.model.ob.GenerateRateBank;
import com.esynergy.erm.model.ob.GenerateRateDetail;
import com.esynergy.erm.model.ob.GenerateRateFile;
import com.esynergy.erm.model.ob.GenerateRateResultExchangeRate;
import com.esynergy.erm.model.ob.GenerateRateTime;
import com.esynergy.erm.model.ob.ManualTarget;
import com.esynergy.erm.service.BankService;
import com.esynergy.erm.service.CodeValueService;
import com.esynergy.erm.service.CurrencyService;
import com.esynergy.erm.service.ExtractionService;
import com.esynergy.erm.service.GenerateRateDetailService;
import com.esynergy.erm.service.GenerateRateService;
import com.esynergy.erm.service.GenerateRateTimeService;
import com.esynergy.erm.service.ManualTargetService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings({ "serial", "rawtypes" })
@Controller("generateRateAction")
public class GenerateRateAction extends ActionCommon implements
	ServletRequestAware, ModelDriven {

	private static final Logger logger = Logger.getLogger(GenerateRateAction.class);
	
	private GenerateRate generateRate = new GenerateRate();
	private GenerateRateSearchForm generateRateSearchForm = new GenerateRateSearchForm();
	private GenerateRateForm generateRateForm = new GenerateRateForm();
	private List<GenerateRateResultExchangeRate> generateRateResultExchangeRateList = new ArrayList<GenerateRateResultExchangeRate>();
	private List<Long> generateRateRemoveDetail = new ArrayList<Long>();
	
	private List<GenerateRate> generateRateManageList = new ArrayList<GenerateRate>();
	
	private InputStream inputStream;
	private String fileName;
	private long contentLength;
	
	private String parm;
	private String parmDownload;
	private String removeID;
	
	@Autowired
	private CurrencyService currencyService;
	
	@Autowired
	private BankService bankService;
	
	@Autowired
	private ExtractionService extractionService;
	
	@Autowired
	private ManualTargetService manualTargetService;
		
	@Autowired
	private CodeValueService codeValueService;
	
	@Autowired
	private GenerateRateService generateRateService;
	
	@Autowired
	private GenerateRateDetailService generateRateDetailService;
	
	@Autowired
	private GenerateRateTimeService generateRateTimeService;
	
	private List<Currency> currencyList = new ArrayList<Currency>();
	private Map<Long,Currency> currencyMap = new HashMap<Long,Currency>();
	private List<CodeValue> generateRateTimeList = new ArrayList<CodeValue>();

	@SuppressWarnings("unchecked")
	public void setServletRequest(HttpServletRequest request) {
		super.setServletRequest(request);
		request.setAttribute(MAIN_MENU_ATTR, MENU_GENERATE_RATE);
		HttpSession session = request.getSession(false) == null ? request.getSession(true) : request.getSession(false);
		if (session.getAttribute(SESSION_CURRENCY_LIST) == null) {
			currencyList = currencyService.listAll();
			session.setAttribute(SESSION_CURRENCY_LIST, currencyList);
		} else {
			currencyList = (List<Currency>) session.getAttribute(SESSION_CURRENCY_LIST);
		}
		if (session.getAttribute(SESSION_CURRENCY_MAP) == null) {
			currencyMap = currencyService.listAllMap();
			session.setAttribute(SESSION_CURRENCY_MAP, currencyMap);
		} else {
			currencyMap = (Map<Long, Currency>) session
					.getAttribute(SESSION_CURRENCY_MAP);
		}
		if(session.getAttribute(SESSION_GENERATE_RATE_TIME_LIST) == null) {
			generateRateTimeList = codeValueService.listAllGenerateRateTimeField();
			session.setAttribute(SESSION_GENERATE_RATE_TIME_LIST, generateRateTimeList);
		} else {
			generateRateTimeList = (List<CodeValue>) session.getAttribute(SESSION_GENERATE_RATE_TIME_LIST);
		}
	}

	public HttpServletRequest getReq() {
		return (HttpServletRequest) ActionContext.getContext().get(
				ServletActionContext.HTTP_REQUEST);
	}
	
	public String prepareManage() {
		if(UIUtil.isEmptyOrNull(getReq().getParameter("updated"))){
			super.errors.clear();
		}		
		super.addActionMsg("actionTitle","header.generate.rate.management");
		
		generateRateManageList = generateRateService.listAll();
		
		return SUCCESS;
	}
	
	public String prepareView() {
		IUser user =(IUser) getReq().getSession(false).getAttribute(SESSION_USER);
		if(!ValidatorUtil.checkPermission(user, "ViewGenerateRate")){
			return ERROR;
		}
		
		super.setActionMsg(new HashMap<String,Object>());
		super.setErrors(new HashMap<String,Object>());
		
		super.addActionMsg("actionTitle","header.view.generate.rate");
		
		if(!UIUtil.isEmptyOrNull(getReq().getParameter("parm"))) {
			generateRate = generateRateService.getByID(Long.parseLong(parm));
			generateRateForm = new GenerateRateForm();
			populateObToForm(generateRate, generateRateForm);
			generateRateRemoveDetail = new ArrayList<Long>();
			return SUCCESS;
		}
		else {
			return INPUT;
		}
	}
	
	public String prepareCreate() {
		IUser user =(IUser) getReq().getSession(false).getAttribute(SESSION_USER);
		if(!ValidatorUtil.checkPermission(user, "CreateGenerateRate")){
			return ERROR;
		}
		
		super.setActionMsg(new HashMap<String,Object>());
		super.setErrors(new HashMap<String,Object>());
		
		super.addActionMsg("actionTitle","header.create.list.generate.rate");
		List<Extraction> extractionActiveList = extractionService.listAllActive();
		List<ManualTarget> manualTargetActiveList = manualTargetService.listAll();
		generateRateResultExchangeRateList = new ArrayList<GenerateRateResultExchangeRate>();
		adjustObforViewing(extractionActiveList, manualTargetActiveList, generateRateResultExchangeRateList);
		
		generateRateSearchForm = new GenerateRateSearchForm();
		
		generateRate = new GenerateRate();
		generateRateForm = new GenerateRateForm();
		for(CodeValue item : generateRateTimeList) {
			GenerateRateTimeForm timeForm = new GenerateRateTimeForm();
			timeForm.setGenerateTime(item.getValue());
			timeForm.setChk("false");
			timeForm.setGenerateTimeLabel(item.getDescriptionShort());
			generateRateForm.addTimeFormList(timeForm);
		}
		
		generateRateRemoveDetail = new ArrayList<Long>();
		
		return SUCCESS;
	}
	
	public String prepareEdit() {
		super.setActionMsg(new HashMap<String,Object>());
		super.setErrors(new HashMap<String,Object>());
		super.addActionMsg("actionTitle","header.create.list.generate.rate");
		
		if(!UIUtil.isEmptyOrNull(getReq().getParameter("parm"))) {
		
			generateRateSearchForm = new GenerateRateSearchForm();
			
			generateRate = generateRateService.getByID(Long.parseLong(parm));
			IUser user =(IUser) getReq().getSession(false).getAttribute(SESSION_USER);
			if(!ValidatorUtil.checkPermission(user, "EditGenerateRate")){
				return ERROR;
			}
			generateRateForm = new GenerateRateForm();
			populateObToForm(generateRate, generateRateForm);
			generateRateRemoveDetail = new ArrayList<Long>();
			
			List<Extraction> extractionActiveList = extractionService.listAllActive();
			List<ManualTarget> manualTargetActiveList = manualTargetService.listAll();
			
			generateRateResultExchangeRateList = new ArrayList<GenerateRateResultExchangeRate>();
			adjustObforViewing(extractionActiveList, manualTargetActiveList, generateRateResultExchangeRateList);
			filterRecordWithConditionForm(generateRateSearchForm, generateRateResultExchangeRateList);
			
			return SUCCESS;
		}
		else {
			return INPUT;
		}
	}
	
	public String search() {
		
//		super.setActionMsg(new HashMap<String,Object>());
//		super.setErrors(new HashMap<String,Object>());
//		super.addActionMsg("actionTitle","header.generate.rate");
		
//		generateRateSearchForm = new GenerateRateSearchForm();
//		generateRateForm = new GenerateRateForm();
		
		List<Extraction> extractionActiveList = extractionService.listAllActive();
		List<ManualTarget> manualTargetActiveList = manualTargetService.listAll();
		
		generateRateResultExchangeRateList = new ArrayList<GenerateRateResultExchangeRate>();
		adjustObforViewing(extractionActiveList, manualTargetActiveList, generateRateResultExchangeRateList);
		filterRecordWithConditionForm(generateRateSearchForm, generateRateResultExchangeRateList);
		
		return SUCCESS;
	}
	
	public String save() {
		
		super.errors = new HashMap<String, Object>();
		
		if(validateForm(generateRateForm)) {
			GenerateRate ob = new GenerateRate();
			populateFormToOb(generateRateForm, ob);
			
			HttpSession session = getReq().getSession();
			
			IUser user = (IUser)session.getAttribute(SESSION_USER);
			
			if(ob.getId() > 0) {
				
				for(GenerateRateDetail item : generateRate.getGenerateRateDetails()) {
					if(generateRateRemoveDetail.contains(item.getId())) {
						generateRateDetailService.delete(item);
					}
				}
				
				for(GenerateRateTime item : generateRate.getGenerateRateTimes()) {
					boolean isFound = false;
					long id = item.getId();
					for(GenerateRateTime item2 : ob.getGenerateRateTimes()) {
						if(id == item2.getId()) {
							isFound = true;
							break;
						}
					}
					if(!isFound) {
						GenerateRateTime time = new GenerateRateTime();
						time.setId(id);
						generateRateTimeService.delete(time);
					}
				}
				
				ob.setAddUser(generateRate.getAddUser());
				ob.setAddDate(generateRate.getAddDate());
				ob.setChangeDate(new Date());
				ob.setChangeUser(user.getLogOnId());
				
				generateRateService.update(ob);
			}
			else {
				
				ob.setAddDate(new Date());
				ob.setAddUser(user.getLogOnId());
				ob.setChangeDate(new Date());
				ob.setChangeUser(user.getLogOnId());
				generateRateService.save(ob);
			}
			
		 	super.addActionMsg("saveSuccess",getText("msg.action.save.success"));
		 	super.addErrors("isSuccess",true);
			
			return SUCCESS;
		}
		else {
			return INPUT;
		}
		
	}
	
	public String removeDetailForm() {
		
		for(int i=0;i<generateRateForm.getDetailFormList().size();i++) {
			GenerateRateDetailForm item = generateRateForm.getDetailFormList().get(i);
			if(item.getId() > 0 && item.getId() == Long.parseLong(removeID)) {
				generateRateRemoveDetail.add(item.getId());
				generateRateForm.getDetailFormList().remove(i);
				break;
			}
		}
		
		return SUCCESS;
	}
	
	public String remove() {
		
		if(UIUtil.isEmptyOrNull(getReq().getParameter("parm"))) {
			return INPUT;
		}
		else {
			
			GenerateRate removingOB = generateRateService.getByID(Long.parseLong(getReq().getParameter("parm")));

			IUser user =(IUser) getReq().getSession(false).getAttribute(SESSION_USER);
			if(!ValidatorUtil.checkPermission(user, "DeleteGenerateRate")){
				return ERROR;
			}
			generateRateService.remove(removingOB);
			
			super.addActionMsg("saveSuccess",getText("msg.action.remove.success"));
		 	super.addErrors("isSuccess",true);
		}
		
		return SUCCESS;
	}
	
	public String prepareRedirect() {
		
		List<GenerateRateTimeForm> newTimeFormList = new ArrayList<GenerateRateTimeForm>();
		for(CodeValue item : generateRateTimeList) {
			GenerateRateTimeForm timeForm = new GenerateRateTimeForm();
			timeForm.setGenerateTimeLabel(item.getDescriptionShort());
			timeForm.setGenerateTime(item.getValue());
			
			boolean isFound = false;
			for(GenerateRateTimeForm item2 : generateRateForm.getTimeFormList()) {
				if(item2.getGenerateTime().equals(item.getValue())) {
					isFound = true;
					timeForm.setChk("true");
					timeForm.setId(item2.getId());
					break;
				}
			}
			
			if(!isFound) {
				timeForm.setChk("false");
			}
			
			newTimeFormList.add(timeForm);
		}
		
		generateRateForm.setTimeFormList(newTimeFormList);
		
		return SUCCESS;
	}
	
	public boolean validateForm(GenerateRateForm form) {
		boolean resultValidate = true;
		
		String profileName = form.getProfileName();
		if(UIUtil.isEmptyOrNull(profileName)) {
			resultValidate = false;
			super.addErrors("errorProfileName","Name can't be empty");
		}
		
		boolean isFoundTime = false;
		for(GenerateRateTimeForm item : form.getTimeFormList()) {
			if(item.getGenerateTime().equalsIgnoreCase("false")) {
				continue;
			}
			else {
				isFoundTime = true;
				break;
			}
		}
		if(!isFoundTime) {
			resultValidate = false;
			super.addErrors("errorTime", "Please select at least 1 time to generate rate");
		}
		
		if(form.getDetailFormList().size() == 0) {
			resultValidate = false;
			super.addErrors("errorDetail", "Please select at least 1 exchange rate to generate rate");
		}
		
		int runningIndex = 0;
		Map<String,String> bpCurrencyMap = new HashMap<String, String>();
		for(GenerateRateDetailForm item : form.getDetailFormList()) {
			
			String typeField = item.getType();
			if(UIUtil.isEmptyOrNull(typeField)) {
				resultValidate = false;
				super.addErrors("errorType" + runningIndex,"Type can't be empty");
			}
			else {
				String forKey = item.getBaseCurrency() + " " + item.getPairCurrency();
				if(bpCurrencyMap.containsKey(forKey)) {
					String originalType = bpCurrencyMap.get(forKey);
					if(originalType.equalsIgnoreCase(typeField)) {
						resultValidate = false;
						super.addErrors("errorType" + runningIndex,"Type can't be duplicate with the same base and pair currency");
					}
				}
				else {
					bpCurrencyMap.put(forKey, typeField);
				}
			}
			runningIndex++;
		}
		
		return resultValidate;
	}
	
	public String download() {
		
		if(!UIUtil.isEmptyOrNull(getReq().getParameter("parmDownload"))) {
			try {
				long parmDownloadID = Long.parseLong(getReq().getParameter("parmDownload"));
				File downloadFile = null;
				for(GenerateRateFileForm item : generateRateForm.getFileFormList()) {
					if(item.getId() == parmDownloadID) {
						downloadFile = new File(item.getUrl());
						break;
					}
				}
				
				if(downloadFile != null && downloadFile.length() >= 0) {
					inputStream = new FileInputStream(downloadFile);
					fileName = downloadFile.getName();
					contentLength = downloadFile.length();
					return SUCCESS;
				}
				else {
					return INPUT;
				}
			}
			catch (Exception e) {
				return INPUT;
			}
		}
		else {
			return INPUT;
		}
	}
	
	private void adjustObforViewing(List<Extraction>  extractionList, List<ManualTarget> manualTargetList,
			List<GenerateRateResultExchangeRate> obView) {
		
		for (Extraction item : extractionList) {
			
			for(ExtractionDetail item2 : item.getExtractionDetailList()) {
				
				GenerateRateResultExchangeRate rateView = new GenerateRateResultExchangeRate();
				
				rateView.setPairCurrency(item2.getCurrency());
				
				for(Currency cur : currencyList) {
//					if(cur.getCode().equalsIgnoreCase(item.getBank().getCountry().getCurrencyCode())){
					if(cur.getId() == item.getBank().getCountry().getCurrency().getId()){
						rateView.setBaseCurrency(cur);
					}
				}
				
				rateView.setPairCurrencyType(item.getPairCurrencyType());
				rateView.setBankName(item.getBank().getBankName());
				rateView.setType(IPageContains.ER_ORIGIN_AUTO);
				
				obView.add(rateView);
			}
		}
		
		for (ManualTarget item : manualTargetList) {
			
			GenerateRateResultExchangeRate rateView = new GenerateRateResultExchangeRate();
			GenerateRateResultExchangeRate rateView2 = new GenerateRateResultExchangeRate();
			
			rateView.setPairCurrency(item.getPairCurrency());
			rateView.setBaseCurrency(item.getBaseCurrency());
			rateView.setPairCurrencyType(1);
			rateView.setBankName("-");
			rateView.setType(IPageContains.ER_ORIGIN_MANUAL);
			
			rateView2.setPairCurrency(item.getPairCurrency());
			rateView2.setBaseCurrency(item.getBaseCurrency());
			rateView2.setPairCurrencyType(2);
			rateView2.setBankName("-");
			rateView2.setType(IPageContains.ER_ORIGIN_MANUAL);
			
			obView.add(rateView);
			obView.add(rateView2);
		}
		
	}
	
	private void filterRecordWithConditionForm(GenerateRateSearchForm conditionForm, List<GenerateRateResultExchangeRate> obList) {
		
		long pairCurrencyID = conditionForm.getPairCurrencyID();
		long baseCurrencyID = conditionForm.getBaseCurrencyID();
		String bankName = conditionForm.getBankName();
		
		Currency pairCurrency = null;
		Currency baseCurrency = null;
		
		if(pairCurrencyID != -1) {
			pairCurrency = currencyMap.get(pairCurrencyID);
		}
		
		if(baseCurrencyID != -1) {
			baseCurrency = currencyMap.get(baseCurrencyID);
		}
		
		for(int i=obList.size()-1;i>=0;i--) {
			GenerateRateResultExchangeRate item = obList.get(i);
			if(pairCurrency != null && !item.getBaseCurrency().getCode().equalsIgnoreCase(pairCurrency.getCode())) {
				obList.remove(i);
				continue;
			}
			if(baseCurrency != null && !item.getPairCurrency().getCode().equalsIgnoreCase(baseCurrency.getCode())) {
				obList.remove(i);
				continue;
			}
			if(!UIUtil.isEmptyOrNull(bankName) && !UIUtil.isEmptyOrNull(item.getBankName()) && !item.getBankName().toLowerCase().contains(bankName.toLowerCase()) ) {
				obList.remove(i);
				continue;
			}
		}
		
	}
	
	private void populateObToForm(GenerateRate ob, GenerateRateForm form) {
		
		form.setId(ob.getId());
		form.setProfileName(ob.getFileName());
		
		for(GenerateRateDetail item : ob.getGenerateRateDetails()) {
			GenerateRateDetailForm detailForm = new GenerateRateDetailForm();
			detailForm.setId(item.getId());
			if(item.getGenerateRateBanks() == null || item.getGenerateRateBanks().size() == 0) {
				GenerateRateBankForm bankForm = new GenerateRateBankForm();
				bankForm.setBankName("-");
				detailForm.setBankForm(bankForm);
			}
			else {
				GenerateRateBankForm bankForm = new GenerateRateBankForm();
				bankForm.setId(item.getGenerateRateBanks().iterator().next().getId());
				bankForm.setBankName(item.getGenerateRateBanks().iterator().next().getBank().getBankName());
				detailForm.setBankForm(bankForm);
			}
			detailForm.setBaseCurrency(item.getBaseCurrency().getCode());
			detailForm.setPairCurrency(item.getPairCurrency().getCode());
			detailForm.setPairCurrencyType(item.getPairCurrencyType() + "");
			detailForm.setRateType(item.getRateType());
			detailForm.setType(item.getType());
			form.addDetailFormList(detailForm);
		}
		
		for(CodeValue item : generateRateTimeList) {
			GenerateRateTimeForm timeForm = new GenerateRateTimeForm();
			timeForm.setGenerateTimeLabel(item.getDescriptionShort());
			timeForm.setGenerateTime(item.getValue());
			timeForm.setChk("false");
			for(GenerateRateTime item2 : ob.getGenerateRateTimes()) {
				if(item2.getGenerateTime() == Long.parseLong(item.getValue())) {
					timeForm.setId(item2.getId());
					timeForm.setChk("true");
				}
			}
			form.addTimeFormList(timeForm);
		}
		
		for(GenerateRateFile item : ob.getGenerateRateFiles()) {
			GenerateRateFileForm fileForm = new GenerateRateFileForm();
			fileForm.setId(item.getId());
			fileForm.setFileName(item.getName());
			fileForm.setUrl(item.getUrl());
			fileForm.setAddDate(item.getAddDate());
			form.addFileFormList(fileForm);
		}
		
		Collections.sort(form.getDetailFormList(), new Comparator<GenerateRateDetailForm>() {
			public int compare(GenerateRateDetailForm o1, GenerateRateDetailForm o2) {
				return o1.getType().compareTo(o2.getType()) < 0 ? -1:1;
			}
		});
		
		Collections.sort(form.getFileFormList(), new Comparator<GenerateRateFileForm>() {
			public int compare(GenerateRateFileForm o1, GenerateRateFileForm o2) {
				return o1.getAddDate().compareTo(o2.getAddDate()) < 0 ? -1:1;
			}
		});
		
	}
	
	private void populateFormToOb(GenerateRateForm form, GenerateRate ob) {
		
		HttpSession session = getReq().getSession();
		
		IUser user = (IUser)session.getAttribute(SESSION_USER);
		
		ob.setId(form.getId());
		ob.setFileName(form.getProfileName());
		ob.setStatus("A");
		
		List<GenerateRateDetail> obDetailList = new ArrayList<GenerateRateDetail>();
		for(GenerateRateDetailForm item : form.getDetailFormList()) {
			GenerateRateDetail detail = new GenerateRateDetail();
			
			detail.setId(item.getId());
			
			for(Currency cur : currencyList) {
				if(cur.getCode().equalsIgnoreCase(item.getBaseCurrency())){
					detail.setBaseCurrency(cur);
				}
			}
			
			for(Currency cur : currencyList) {
				if(cur.getCode().equalsIgnoreCase(item.getPairCurrency())){
					detail.setPairCurrency(cur);
				}
			}
			
			if(!UIUtil.isEmptyOrNull(item.getBankForm().getBankName()) && !item.getBankForm().getBankName().equalsIgnoreCase("-") ) {
				Bank foundedBank = bankService.getBankWithName(item.getBankForm().getBankName()).get(0);
				GenerateRateBank rateBank = new GenerateRateBank();
				rateBank.setId(item.getBankForm().getId());
				rateBank.setBank(foundedBank);
				rateBank.setGenerateRateDetail(detail);
				Set<GenerateRateBank> bankSet = new HashSet<GenerateRateBank>();
				bankSet.add(rateBank);
				detail.setGenerateRateBanks(bankSet);
			}

			detail.setPairCurrencyType(Long.parseLong(item.getPairCurrencyType()));
			detail.setRateType(item.getRateType());
			detail.setType(item.getType());
			detail.setGenerateRate(ob);

			detail.setAddUser(user.getLogOnId());
			detail.setChangeUser(user.getLogOnId());
			detail.setAddDate(new Date());
			detail.setChangeDate(new Date());
			obDetailList.add(detail);
		}
		
		List<GenerateRateTime> obTimeList = new ArrayList<GenerateRateTime>();
		for(GenerateRateTimeForm item : form.getTimeFormList()) {
			GenerateRateTime time = new GenerateRateTime();
			if(item.getGenerateTime().equalsIgnoreCase("false")) {
				continue;
			}
			else {
				time.setId(item.getId());
				time.setGenerateTime(Long.parseLong(item.getGenerateTime()));
				time.setGenerateRate(ob);
				obTimeList.add(time);
			}
		}
		
		Set<GenerateRateTime> obTimeSet = new HashSet<GenerateRateTime>(obTimeList);
		ob.setGenerateRateTimes(obTimeSet);
		Set<GenerateRateDetail> obDetailSet = new HashSet<GenerateRateDetail>(obDetailList);
		ob.setGenerateRateDetails(obDetailSet);
	}
	
//	private File generateFileForDownloading(GenerateRateForm form) {
//		try {
//			File fileToDownload = new File();
//			FileWriter fw = new FileWriter(fileToDownload);
//				
//			String toWrite = "Type" + ", " + "Bank Name" + ", " + "Pair Currency" +
//					", " + "Base Currency" + ", " + "Date" + ", " +
//					"Buying Rate" + ", " + "Selling Rate" + ", " + "Avg. Rate";
//			
//			for(GenerateRateRecordForm item : form.getGenerateRateRecordFormList()) {
//				
//				if (!UIUtil.isEmptyOrNull(item.getChk())
//						&& (item.getChk().equals(CHK_ON) || item.getChk().equals("true"))) {
//					toWrite = item.getTypeOfCurrency() + ", " + item.getBankName() + ", " + item.getPairCurrency() +
//							", " + item.getBaseCurrency() + ", " + item.getDate().toString() + ", " +
//							item.getBuyingRate() + ", " + item.getSellingRate() + ", " + item.getAvgRate();
//					fw.write(toWrite);
//					fw.write(System.getProperty("line.separator"));
//				}
//				
//			}
//			
//			fw.flush();
//			fw.close();
//			
//			return fileToDownload;
//		}
//		catch (Exception e) {
//			return null;
//		}
//	}
	
	@Override
	public Object getModel() {
		return null;
	}

	public GenerateRateSearchForm getGenerateRateSearchForm() {
		return generateRateSearchForm;
	}

	public void setGenerateRateSearchForm(GenerateRateSearchForm generateRateSearchForm) {
		this.generateRateSearchForm = generateRateSearchForm;
	}

	public GenerateRateForm getGenerateRateForm() {
		return generateRateForm;
	}

	public void setGenerateRateForm(GenerateRateForm generateRateForm) {
		this.generateRateForm = generateRateForm;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getContentLength() {
		return contentLength;
	}

	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}

	public List<Currency> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<Currency> currencyList) {
		this.currencyList = currencyList;
	}

	public List<GenerateRateResultExchangeRate> getGenerateRateResultExchangeRateList() {
		return generateRateResultExchangeRateList;
	}

	public void setGenerateRateResultExchangeRateList(
			List<GenerateRateResultExchangeRate> generateRateResultExchangeRateList) {
		this.generateRateResultExchangeRateList = generateRateResultExchangeRateList;
	}
	public String getParm() {
		return parm;
	}

	public void setParm(String parm) {
		this.parm = parm;
	}

	public String getParmDownload() {
		return parmDownload;
	}

	public void setParmDownload(String parmDownload) {
		this.parmDownload = parmDownload;
	}
	

	public String getRemoveID() {
		return removeID;
	}

	public void setRemoveID(String removeID) {
		this.removeID = removeID;
	}

	public List<GenerateRate> getGenerateRateManageList() {
		return generateRateManageList;
	}

	public void setGenerateRateManageList(List<GenerateRate> generateRateManageList) {
		this.generateRateManageList = generateRateManageList;
	}
	
}
