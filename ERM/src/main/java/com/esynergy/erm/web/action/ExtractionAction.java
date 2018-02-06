package com.esynergy.erm.web.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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
import com.esynergy.erm.model.form.ExtractionDetailForm;
import com.esynergy.erm.model.form.ExtractionForm;
import com.esynergy.erm.model.form.ExtractionSearchForm;
import com.esynergy.erm.model.form.ExtractionTimeForm;
import com.esynergy.erm.model.ob.Bank;
import com.esynergy.erm.model.ob.CodeValue;
import com.esynergy.erm.model.ob.Country;
import com.esynergy.erm.model.ob.Currency;
import com.esynergy.erm.model.ob.ExtractionDetail;
import com.esynergy.erm.model.ob.ExtractionTime;
import com.esynergy.erm.model.ob.Extraction;
import com.esynergy.erm.service.BankService;
import com.esynergy.erm.service.CodeValueService;
import com.esynergy.erm.service.CountryService;
import com.esynergy.erm.service.CurrencyService;
import com.esynergy.erm.service.ExtractionDetailService;
import com.esynergy.erm.service.ExtractionService;
import com.esynergy.erm.service.ExtractionTimeService;
import com.esynergy.erm.service.SeleniumService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings({ "serial", "rawtypes" })
@Controller("extractionAction")
public class ExtractionAction extends ActionCommon implements
	 ServletRequestAware, ModelDriven {
	
	private static final Logger logger = Logger
			.getLogger(ExtractionAction.class);
	
	private Extraction extraction = new Extraction();
	private ExtractionForm extractionForm = new ExtractionForm();
	private List<Long> extractionDetailRemoveList = new ArrayList<Long>();
	
	private List<Extraction> extractionList = new ArrayList<Extraction>();
	private ExtractionSearchForm extractionSearchForm = new ExtractionSearchForm();
	
	private String parm;
	
	@Autowired
	private CurrencyService currencyService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private ExtractionService extractionService;
	
	@Autowired
	private ExtractionDetailService extractionDetailService;
	
	@Autowired
	private ExtractionTimeService extractionTimeService;
	
	@Autowired
	private CodeValueService codeValueService;
	
	@Autowired
	private BankService bankService;
	
	@Autowired
	private SeleniumService seleniumService;
	
	private List<Currency> currencyList = new ArrayList<Currency>();
	private Map<Long,Currency> currencyMap = new HashMap<Long,Currency>();
	private List<Country> countryList = new ArrayList<Country>();
	private List<CodeValue> extractionTimeList = new ArrayList<CodeValue>();
	private List<CodeValue> extractionTypeList = new ArrayList<CodeValue>();
	private List<CodeValue> extractionDateList = new ArrayList<CodeValue>();
	private List<CodeValue> pageTypeList = new ArrayList<CodeValue>();
	private List<CodeValue> currencyTypeList = new ArrayList<CodeValue>();
	private List<CodeValue> statusList = new ArrayList<CodeValue>();
	private List<CodeValue> formatDateList = new ArrayList<CodeValue>();

	private List<Currency> currencyListAPIPool = new ArrayList<Currency>();
	private List<Currency> currencyListSelectedAPI = new ArrayList<Currency>();

	@SuppressWarnings("unchecked")
	public void setServletRequest(HttpServletRequest request) {
		super.setServletRequest(request);
		request.setAttribute(MAIN_MENU_ATTR, MENU_EXCHANGE_RATE);
		request.setAttribute(SUB_MENU_ATTR, MENU_EXCHANGE_RATE_AUTO);
		request.setAttribute(SUB_MENU1_ATTR, MENU_EXTRACTION_MANAGE);
		HttpSession session = request.getSession(false) == null ? request
				.getSession(true) : request.getSession(false);
		if (session.getAttribute(SESSION_CURRENCY_LIST) == null) {
			currencyList = currencyService.listAll();
			session.setAttribute(SESSION_CURRENCY_LIST, currencyList);
		} else {
			currencyList = (List<Currency>) session
					.getAttribute(SESSION_CURRENCY_LIST);
		}
		if (session.getAttribute(SESSION_CURRENCY_MAP) == null) {
			currencyMap = currencyService.listAllMap();
			session.setAttribute(SESSION_CURRENCY_MAP, currencyMap);
		} else {
			currencyMap = (Map<Long, Currency>) session
					.getAttribute(SESSION_CURRENCY_MAP);
		}
		if(session.getAttribute(SESSION_COUNTRY_LIST) == null) {
			countryList = countryService.listAll();
			session.setAttribute(SESSION_COUNTRY_LIST, countryList);
		} else {
			countryList = (List<Country>) session.getAttribute(SESSION_COUNTRY_LIST);
		}
		if(session.getAttribute(SESSION_EXTRACTION_TIME_LIST) == null) {
			extractionTimeList = codeValueService.listAllExtractionTimeField();
			session.setAttribute(SESSION_EXTRACTION_TIME_LIST, extractionTimeList);
		} else {
			extractionTimeList = (List<CodeValue>) session.getAttribute(SESSION_EXTRACTION_TIME_LIST);
		}
		if(session.getAttribute(SESSION_EXTRACTION_TYPE_LIST) == null) {
			extractionTypeList = codeValueService.listAllExtractionType();
			session.setAttribute(SESSION_EXTRACTION_TYPE_LIST, extractionTypeList);
		} else {
			extractionTypeList = (List<CodeValue>) session.getAttribute(SESSION_EXTRACTION_TYPE_LIST);
		}
		if(session.getAttribute(SESSION_EXTRACTION_DATE_LIST) == null) {
			extractionDateList = codeValueService.listAllExtractionDate();
			session.setAttribute(SESSION_EXTRACTION_DATE_LIST, extractionDateList);
		} else {
			extractionDateList = (List<CodeValue>) session.getAttribute(SESSION_EXTRACTION_DATE_LIST);
		}
		if(session.getAttribute(SESSION_PAGE_TYPE_LIST) == null) {
			pageTypeList = codeValueService.listAllPageType();
			session.setAttribute(SESSION_PAGE_TYPE_LIST, pageTypeList);
		} else {
			pageTypeList = (List<CodeValue>) session.getAttribute(SESSION_PAGE_TYPE_LIST);
		}
		if(session.getAttribute(SESSION_CURRENCY_TYPE_LIST) == null) {
			currencyTypeList = codeValueService.listAllCurrencyType();
			session.setAttribute(SESSION_CURRENCY_TYPE_LIST, currencyTypeList);
		} else {
			currencyTypeList = (List<CodeValue>) session.getAttribute(SESSION_CURRENCY_TYPE_LIST);
		}
		if(session.getAttribute(SESSION_STATUS_LIST) == null) {
			statusList = codeValueService.listAllStatus();
			session.setAttribute(SESSION_STATUS_LIST, statusList);
		} else {
			statusList = (List<CodeValue>) session.getAttribute(SESSION_STATUS_LIST);
		}
		if(session.getAttribute(SESSION_FORMAT_DATE_LIST) == null) {
			formatDateList = codeValueService.listAllFormatDate();
			session.setAttribute(SESSION_FORMAT_DATE_LIST, formatDateList);
		} else {
			formatDateList = (List<CodeValue>) session.getAttribute(SESSION_FORMAT_DATE_LIST);
		}
	}

	public HttpServletRequest getReq() {
		return (HttpServletRequest) ActionContext.getContext().get(
				ServletActionContext.HTTP_REQUEST);
	}
	
	public String prepareCreate() {
		
		IUser user =(IUser) getReq().getSession(false).getAttribute(SESSION_USER);
		if(!ValidatorUtil.checkPermission(user, "CreateScraping")){
			return ERROR;
		}
		
		super.setActionMsg(new HashMap<String,Object>());
		super.setErrors(new HashMap<String,Object>());
		
		super.addActionMsg("actionTitle","header.extraction.create");
		
		extraction = new Extraction();
		extractionDetailRemoveList = new ArrayList<Long>();
		extractionForm = new ExtractionForm();
		currencyListAPIPool = new ArrayList<Currency>(currencyList);
		currencyListSelectedAPI = new ArrayList<Currency>();
		for(CodeValue item : extractionTimeList) {
			ExtractionTimeForm timeForm = new ExtractionTimeForm();
			timeForm.setExtractionTime(item.getValue());
			timeForm.setChk("false");
			timeForm.setExtractionTimeLabel(item.getDescriptionShort());
			extractionForm.addExtractionTimeFormList(timeForm);
		}
		List<ExtractionDetailForm> tempDetailFormList = new ArrayList<ExtractionDetailForm>();
		for(int i=0;i<3;i++) {
			ExtractionDetailForm tempDetailForm = new ExtractionDetailForm();
			tempDetailFormList.add(tempDetailForm);
		}
		extractionForm.setExtractionDetailFormList(tempDetailFormList);
		return SUCCESS;
	}
	
	public String prepareEdit() {
		
		
		super.setActionMsg(new HashMap<String,Object>());
		super.setErrors(new HashMap<String,Object>());
		
		super.addActionMsg("actionTitle","header.extraction.edit");
		
		HttpServletRequest request = getReq();
		if(request.getParameter("parm") == null) {
			return INPUT;
		}
		else {
//			System.out.println("not null");
			long id = Long.parseLong(UIUtil.prepareConvertStringToNumber(request.getParameter("parm")));
			extractionDetailRemoveList = new ArrayList<Long>();
			extraction = extractionService.getById(id);
			IUser user =(IUser) getReq().getSession(false).getAttribute(SESSION_USER);
			if(!ValidatorUtil.checkPermission(user, "EditScraping")){
				return ERROR;
			}

			extractionForm = new ExtractionForm();
			currencyListAPIPool = new ArrayList<Currency>(currencyList);
			currencyListSelectedAPI = new ArrayList<Currency>();
			populateObToForm(extraction, extractionForm);
			Collections.sort(currencyListSelectedAPI, new Comparator<Currency>() {
				public int compare(Currency o1, Currency o2) {
					return o1.getCode().compareTo(o2.getCode()) < 0 ? -1:1;
				}
			});
			return SUCCESS;
		}
	}
	
	public String prepareMange(){		
		if(UIUtil.isEmptyOrNull(getReq().getParameter("updated"))){
			super.errors.clear();
		}
	    super.addActionMsg("actionTitle", "header.mangement.extraction");
	    extractionList = extractionService.listAll();
	    extractionSearchForm = new ExtractionSearchForm();
	    return SUCCESS;
	}
   
	public String search(){
	   extractionList = extractionService.search(extractionSearchForm.getBankName(), extractionSearchForm.getBankCountry(), extractionSearchForm.getTypeOfSetting(), extractionSearchForm.getStatus());
	   return SUCCESS;
	}
   
	public String prepareView(){
		IUser user =(IUser) getReq().getSession(false).getAttribute(SESSION_USER);
		if(!ValidatorUtil.checkPermission(user, "ViewScraping")){
			return ERROR;
		}
		
	   super.errors =new HashMap<String, Object>();
	   super.actionMsg = new HashMap<String, Object>();
	   super.addActionMsg("actionTitle", "header.extraction.view");
	   HttpServletRequest req =  getReq();
	   extractionForm = new ExtractionForm();
	   if(!UIUtil.isEmptyOrNull(req.getParameter("parm"))){
		   extraction = extractionService.getById(Long.parseLong(req.getParameter("parm").trim()));
		   populateObToForm(extraction,extractionForm);
	   }
	   return SUCCESS;
	}
	
	public String prepareRedirect() {
		if(extractionForm.getExtractionType() == 3) {
			currencyListAPIPool = new ArrayList<Currency>(currencyList);
			currencyListSelectedAPI = new ArrayList<Currency>();
			if(extractionForm.getSelectedCurrencyAPI() != null) {
				for (long item : extractionForm.getSelectedCurrencyAPI()) {
					for (Currency item2 : currencyList) {
						if(item == item2.getId()) {
							currencyListSelectedAPI.add(item2);
							currencyListAPIPool.remove(item2);
							break;
						}
					}
				}
			}
		}
		
		List<String> extractionTimeSelectedValue = new ArrayList<String>();
		for(ExtractionTimeForm item : extractionForm.getExtractionTimeFormList()) {
			if(!item.getExtractionTime().equals("false")) {
				extractionTimeSelectedValue.add(item.getExtractionTime());
			}
		}
		
		List<ExtractionTimeForm> newExtractionTimeForm = new ArrayList<ExtractionTimeForm>();
		for(CodeValue item : extractionTimeList) {
			ExtractionTimeForm timeForm = new ExtractionTimeForm();
			timeForm.setExtractionTime(item.getValue());
			if(extractionTimeSelectedValue.contains(item.getValue())) {
				timeForm.setChk("true");
			}
			else {
				timeForm.setChk("false");
			}
			timeForm.setExtractionTimeLabel(item.getDescriptionShort());
			newExtractionTimeForm.add(timeForm);
		}
		extractionForm.setExtractionTimeFormList(newExtractionTimeForm);

		return SUCCESS;
	}
	
	public String removeDetailForm() {
//		super.errors = new HashMap<String, Object>();
//		super.actionMsg = new HashMap<String, Object>();
		
//		super.addActionMsg("actionTitle","header.extraction.create");
		
		List<ExtractionDetailForm> removeList = new ArrayList<ExtractionDetailForm>();
		for (int i = 0; i < extractionForm.getExtractionDetailFormList().size(); i++) {
			ExtractionDetailForm formDetail = extractionForm.getExtractionDetailFormList().get(i);
			if (!UIUtil.isEmptyOrNull(formDetail.getChk())
					&& (formDetail.getChk().equals(CHK_ON) 
					|| formDetail.getChk().equals("true"))) {
				removeList.add(formDetail);
				if (formDetail.getId() > 0) {
//					ExchangeRateManualDetail obDetail = new ExchangeRateManualDetail();
//					populateFormToObDetail(formDetail, obDetail);
					extractionDetailRemoveList.add(formDetail.getId());
				}
			}
		}
		
		extractionForm.getExtractionDetailFormList().removeAll(removeList);
		
		////////////////
		
		List<String> extractionTimeSelectedValue = new ArrayList<String>();
		for(ExtractionTimeForm item : extractionForm.getExtractionTimeFormList()) {
			if(!item.getExtractionTime().equals("false")) {
				extractionTimeSelectedValue.add(item.getExtractionTime());
			}
		}
		
		List<ExtractionTimeForm> newExtractionTimeForm = new ArrayList<ExtractionTimeForm>();
		for(CodeValue item : extractionTimeList) {
			ExtractionTimeForm timeForm = new ExtractionTimeForm();
			timeForm.setExtractionTime(item.getValue());
			if(extractionTimeSelectedValue.contains(item.getValue())) {
				timeForm.setChk("true");
			}
			else {
				timeForm.setChk("false");
			}
			timeForm.setExtractionTimeLabel(item.getDescriptionShort());
			newExtractionTimeForm.add(timeForm);
		}
		extractionForm.setExtractionTimeFormList(newExtractionTimeForm);
		
		return SUCCESS;
	}
	
	public String changeExtractionType() {
		if(extraction.getId() > 0 && extraction.getExtractionType() == extractionForm.getExtractionType()) {
			List<ExtractionDetailForm> newExtractionDetailFormList = new ArrayList<ExtractionDetailForm>();
			ExtractionDetailForm detailForm;
			for (ExtractionDetail detail : extraction.getExtractionDetailList()) {
				detailForm = new ExtractionDetailForm();
				detailForm.setCurrency(detail.getCurrency().getId());
				detailForm.setCurrencyCode(detail.getCurrency().getCode());
				detailForm.setExtractionCurrency(detail.getExtractCurrency());
				detailForm.setId(detail.getId());
				detailForm.setValue(detail.getValue());
				detailForm.setExtractionBuyingRate(detail.getExtractBuyingRate());
				detailForm.setExtractionSellingRate(detail.getExtractSellingRate());
				newExtractionDetailFormList.add(detailForm);
				if(extractionForm.getExtractionType() == 3) {
					currencyListSelectedAPI.add(detail.getCurrency());
					currencyListAPIPool.remove(detail.getCurrency());
				}
			}
			for(long item : extractionDetailRemoveList) {
				for(int i=newExtractionDetailFormList.size()-1 ; i>=0 ; i--) {
					if(item == newExtractionDetailFormList.get(i).getId()) {
						newExtractionDetailFormList.remove(i);
						break;
					}
				}
			}
			extractionForm.setExtractionDetailFormList(newExtractionDetailFormList);
			extractionForm.setCssDate(extraction.getCssDate());
		}
		else {
			List<ExtractionDetailForm> tempDetailFormList = new ArrayList<ExtractionDetailForm>();
			for(int i=0;i<3;i++) {
				ExtractionDetailForm tempDetailForm = new ExtractionDetailForm();
				tempDetailFormList.add(tempDetailForm);
			}
			extractionForm.setExtractionDetailFormList(tempDetailFormList);
		}
		
		List<String> extractionTimeSelectedValue = new ArrayList<String>();
		for(ExtractionTimeForm item : extractionForm.getExtractionTimeFormList()) {
			if(!item.getExtractionTime().equals("false")) {
				extractionTimeSelectedValue.add(item.getExtractionTime());
			}
		}
		
		List<ExtractionTimeForm> newExtractionTimeForm = new ArrayList<ExtractionTimeForm>();
		for(CodeValue item : extractionTimeList) {
			ExtractionTimeForm timeForm = new ExtractionTimeForm();
			timeForm.setExtractionTime(item.getValue());
			if(extractionTimeSelectedValue.contains(item.getValue())) {
				timeForm.setChk("true");
			}
			else {
				timeForm.setChk("false");
			}
			timeForm.setExtractionTimeLabel(item.getDescriptionShort());
			newExtractionTimeForm.add(timeForm);
		}
		extractionForm.setExtractionTimeFormList(newExtractionTimeForm);
		
		return SUCCESS;
	}
	
	public String compose() {
		super.errors = new HashMap<String, Object>();
		
		try {
			System.out.println(extractionForm.getUrl());
			System.out.println(extractionForm.getExtractionDate());
			System.out.println(extractionForm.getExtractionType());
			System.out.println(extractionForm.getFormatDate());
			System.out.println(extractionForm.getId());
			System.out.println(extractionForm.getBankID());
			System.out.println(extractionForm.getPairCurrencyType());
			System.out.println(extractionForm.getBankName());
			System.out.println(extractionForm.getBankCountry());
			System.out.println(extractionForm.getExtractionDetailFormList().size());
			
			if(validateForm(extractionForm)) {
				System.out.println("validate");
			}
			else {
//				super.addErrors("isError",true);
				super.addActionMsg("error",super.getText("error.action.message"));
				System.out.println("invalidate");
				return INPUT;
			}
			
			Extraction header = new Extraction();
			populateFormToOb(extractionForm, header);
			
			System.out.println("header bank name : " + header.getBank().getBankName());
			System.out.println("header bank country : " + header.getBank().getCountry().getCountryName());
			System.out.println("header bank url : " + header.getBankURL());
			System.out.println("header extract day : " + header.getExtractionDate());
			System.out.println("header extract time : " + header.getExtractionTimeList().size());
			System.out.println("header format date : " + header.getFormatDate());
			System.out.println("header pair type : " + header.getPairCurrencyType());
			System.out.println("header extract type : " + header.getExtractionType());
			for (ExtractionDetail item : header.getExtractionDetailList()) {
				System.out.println("detail currency code : " + item.getCurrency().getCode());
				System.out.println("detail currency name : " + item.getCurrency().getDescription());
				System.out.println("detail value : " + item.getValue());
				System.out.println("detail currency ex : " + item.getExtractCurrency());
				System.out.println("detail buying rate : " + item.getExtractBuyingRate());
				System.out.println("detail selling rate : " + item.getExtractSellingRate());
			}

			HttpSession session = getReq().getSession();
			
			IUser user = (IUser)session.getAttribute(SESSION_USER);
			
			if(header.getId() > 0) {
				if(extractionDetailRemoveList.size() > 0) {
					for(int i=0;i<extractionDetailRemoveList.size();i++) {
						ExtractionDetail detail = new ExtractionDetail();
						detail.setId(extractionDetailRemoveList.get(i));
						extractionDetailService.delete(detail);
					}
				}
				
				for(ExtractionTime item : extraction.getExtractionTimeList()) {
					boolean isFound = false;
					long id = item.getId();
					for(ExtractionTime item2 : header.getExtractionTimeList()) {
						if(id == item2.getId()) {
							isFound = true;
							break;
						}
					}
					if(!isFound) {
						ExtractionTime time = new ExtractionTime();
						time.setId(id);
						extractionTimeService.delete(time);
					}
				}
				
				header.setAddUser(extraction.getAddUser());
				header.setAddDate(extraction.getAddDate());
				header.setChangeDate(new Date());
				header.setChangeUser(user.getLogOnId());
				
				extractionService.update(header);
			}
			else {
				
				header.setAddDate(new Date());
				header.setAddUser(user.getLogOnId());
				header.setChangeDate(new Date());
				header.setChangeUser(user.getLogOnId());
				extractionService.save(header);
			}

		 	super.addActionMsg("saveSuccess",getText("msg.action.save.success"));
		 	super.addErrors("isSuccess",true);
		 	
			return SUCCESS;
		}
		catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
	}
	
	public String remove() {
		
		HttpServletRequest req = getReq();
		
		if(UIUtil.isEmptyOrNull(req.getParameter("parm"))) {
			return INPUT;
		}
		else {
			long idRemove = Long.parseLong(req.getParameter("parm"));
			Extraction ob = extractionService.getById(idRemove);
			IUser user =(IUser) getReq().getSession(false).getAttribute(SESSION_USER);
			if(!ValidatorUtil.checkPermission(user, "DeleteScraping")){
				return ERROR;
			}
			ob.setDeleteFlag(1);
			extractionService.update(ob);
			
			super.addActionMsg("saveSuccess",getText("msg.action.remove.success"));
		 	super.addErrors("isSuccess",true);
			
			return SUCCESS;
			

		 	
		/*	for(int i=0 ;i<extractionList.size();i++) {
				Extraction ob = extractionList.get(i);
				if(ob.getId() == idRemove) {
					if(ob.getStatus().equals("A")) {
						return INPUT;
					}
					else {
						extractionService.delete(ob);
						return SUCCESS;
					}
				}
			}*/
			
		//	return INPUT;
		}		
	}
	
	public String preview() {		
		super.errors = new HashMap<String, Object>();
		
		String tbURL = extractionForm.getUrl();
		Long ddlExtractionType = extractionForm.getExtractionType();

		if(!UIUtil.isEmptyOrNull(tbURL)) {
			
			try {
				seleniumService.initialWebDriver();
				seleniumService.scrapPage(tbURL);
				if(seleniumService.isSuccessfullyScrap()) {
					super.addErrors("errorURL","");
					int runningNumber = 0;
					if(ddlExtractionType == 1) {
						for (ExtractionDetailForm detailForm : extractionForm.getExtractionDetailFormList()) {
							String textExtractCurrency = detailForm.getExtractionCurrency();
							String textExtractBuyingRate = detailForm.getExtractionBuyingRate();
							String textExtractSellingRate = detailForm.getExtractionSellingRate();
							
							if(UIUtil.isEmptyOrNull(textExtractCurrency)) {
								super.addErrors("s1ExtractionCurrency" + runningNumber, "");
							}
							else {
								String resultExtractCurrency = seleniumService.getFieldData(textExtractCurrency,false);
								if(UIUtil.isEmptyOrNull(resultExtractCurrency)) {
									super.addErrors("s1errorExtractionCurrency" + runningNumber, "No Data");
								}
								else {
									try {
			            				double testParse = Double.parseDouble(resultExtractCurrency);
										super.addErrors("s1errorExtractionCurrency" + runningNumber, "Data Incorrect");
			            			}
			            			catch (Exception e) {
										super.addErrors("s1ExtractionCurrency" + runningNumber, resultExtractCurrency);
									}
								}
							}
							
							if(UIUtil.isEmptyOrNull(textExtractBuyingRate)) {
								super.addErrors("s1ExtractionBuyingRate" + runningNumber, "");
							}
							else {
								String resultExtractBuyingRate = seleniumService.getFieldData(textExtractBuyingRate,false);
								if(UIUtil.isEmptyOrNull(resultExtractBuyingRate)) {
									super.addErrors("s1errorExtractionBuyingRate" + runningNumber, "No Data");
								}
								else {
									try {
			            				double testParse = Double.parseDouble(resultExtractBuyingRate);
										super.addErrors("s1ExtractionBuyingRate" + runningNumber, resultExtractBuyingRate);
			            			}
			            			catch (Exception e) {
										super.addErrors("s1errorExtractionBuyingRate" + runningNumber, "Data Incorrect");
									}
								}
							}
							
							if(UIUtil.isEmptyOrNull(textExtractSellingRate)) {
								super.addErrors("s1ExtractionSellingRate" + runningNumber, "");
							}
							else {
								String resultExtractSellingRate = seleniumService.getFieldData(textExtractSellingRate,false);
								if(UIUtil.isEmptyOrNull(resultExtractSellingRate)) {
									super.addErrors("s1errorExtractionSellingRate" + runningNumber, "No Data");
								}
								else {
									try {
			            				double testParse = Double.parseDouble(resultExtractSellingRate);
										super.addErrors("s1ExtractionSellingRate" + runningNumber, resultExtractSellingRate);
			            			}
			            			catch (Exception e) {
										super.addErrors("s1errorExtractionSellingRate" + runningNumber, "Data Incorrect");
									}
								}
							}
						
							runningNumber++;
						}
					}
					else if(ddlExtractionType == 2) {
						seleniumService.dividePageResult("td");
						for (ExtractionDetailForm detailForm : extractionForm.getExtractionDetailFormList()) {
							
							if(detailForm.getCurrency() == -1) {
								super.addErrors("s2errorCurrency" + runningNumber, "currency must be selected");
							}
							else {
								
								String currencyCode = currencyMap.get(detailForm.getCurrency()).getCode();
								
								String textExtractBuyingRate = detailForm.getExtractionBuyingRate();
								String textExtractSellingRate = detailForm.getExtractionSellingRate();
								
								if(UIUtil.isEmptyOrNull(textExtractBuyingRate)) {
									super.addErrors("s2ExtractionBuyingRate" + runningNumber, "");
								}
								else {
									boolean isNumber = true;
									try {
							    		int testParse = Integer.parseInt(textExtractBuyingRate);
							    	}
							    	catch (Exception e) {
							    		isNumber = false;
										super.addErrors("s2errorExtractionBuyingRate" + runningNumber, "Need number format");
									}
									
									if(isNumber) {
										String resultExtractBuyingRate = seleniumService.getFieldData(currencyCode, Integer.parseInt(textExtractBuyingRate));
										if(UIUtil.isEmptyOrNull(resultExtractBuyingRate)) {
											super.addErrors("s2errorExtractionBuyingRate" + runningNumber, "No Data");
										}
										else {
											super.addErrors("s2ExtractionBuyingRate" + runningNumber, resultExtractBuyingRate);
										}
									}
									
								}
								
								if(UIUtil.isEmptyOrNull(textExtractSellingRate)) {
									super.addErrors("s2ExtractionSellingRate" + runningNumber, "");
								}
								else {
									boolean isNumber = true;
									try {
							    		int testParse = Integer.parseInt(textExtractSellingRate);
							    	}
							    	catch (Exception e) {
							    		isNumber = false;
										super.addErrors("s2errorExtractionSellingRate" + runningNumber, "Need number format");
									}
									
									if(isNumber) {
										String resultExtractSellingRate = seleniumService.getFieldData(currencyCode, Integer.parseInt(textExtractSellingRate));
										if(UIUtil.isEmptyOrNull(resultExtractSellingRate)) {
											super.addErrors("s2errorExtractionSellingRate" + runningNumber, "No Data");
										}
										else {
											super.addErrors("s2ExtractionSellingRate" + runningNumber, resultExtractSellingRate);
										}
									}

								}
							}
							
							runningNumber++;
						}
					}
					seleniumService.quitWebDriver();
				}
				else {
					super.addErrors("errorURL","cant open the webpage, please check the url and try again after few minutes");
					seleniumService.quitWebDriver();
				}
			}
			catch (Exception e) {
				super.addErrors("errorTotal",e.getMessage() + e.getStackTrace());
				seleniumService.quitWebDriver();
			}
		}
		else {
			super.addErrors("errorURL","URL cant be empty");
		}
		
		
		return SUCCESS;
		
	}
	
	public boolean validateForm(ExtractionForm form) {
		
		boolean resultValidate = true;
		
		String tbBankName = form.getBankName();
		if(UIUtil.isEmptyOrNull(tbBankName)) {
			resultValidate = false;
			super.addErrors("errorBankName","Bank Name cant be empty");
		}
		else {
			if(!UIUtil.isEmptyOrNull(extraction.getBank().getBankName()) &&
					!tbBankName.equalsIgnoreCase(extraction.getBank().getBankName())) {
				List<Bank> bankList = bankService.getBankWithName(tbBankName);
				if(bankList.size() > 0) {
					resultValidate = false;
					super.addErrors("errorBankName","Bank Name is existed in Database, Plz use another");
				}
			}
		}
		
		String tbBankShortName = form.getBankShortName();
		if(UIUtil.isEmptyOrNull(tbBankShortName)) {
			resultValidate = false;
			super.addErrors("errorBankShortName","Short Name cant be empty");
		}
		else {
			if(!UIUtil.isEmptyOrNull(extraction.getBank().getBankShortName()) && 
					!tbBankShortName.equalsIgnoreCase(extraction.getBank().getBankShortName())) {
				List<Bank> bankList = bankService.getBankWithName(tbBankShortName);
				if(bankList.size() > 0) {
					resultValidate = false;
					super.addErrors("errorBankShortName","Short Name is existed in Database, Plz use another");
				}
			}
		}
		
		long ddlBankOfCountry = form.getBankCountry();
		if(ddlBankOfCountry == -1) {
			resultValidate = false;
			super.addErrors("errorBankOfCountry","Please select Bank of Country");
		}
		
		long ddlBaseCurrency = form.getBaseCurrency();
		if(ddlBaseCurrency == -1) {
			resultValidate = false;
			super.addErrors("errorBaseCurrency","Please select Base Currency");
		}
		
		String tbURL = form.getUrl();
		if(UIUtil.isEmptyOrNull(tbURL)) {
			resultValidate = false;
			super.addErrors("errorURL","URL cant be empty");
		}
		else {
			if(!UIUtil.isEmptyOrNull(extraction.getBankURL()) && 
					!tbURL.equalsIgnoreCase(extraction.getBankURL())) {
				if(extractionService.isExistURL(tbURL)) {
					resultValidate = false;
					super.addErrors("errorURL","URL is existed in Database, Plz use another");
				}
			}
		}
		
		String ddlFormatDate = form.getFormatDate();
		if(ddlFormatDate.equals("-1")) {
			resultValidate = false;
			super.addErrors("errorFormatDate","Please select Format Date");
		}
		
		long ddlTypeOfCurrency = form.getPairCurrencyType();
		if(ddlTypeOfCurrency == -1) {
			resultValidate = false;
			super.addErrors("errorTypeOfCurrency","Please select Type of Currency");
		}
		
		String ddlDateToExtract = form.getExtractionDate();
		if(ddlDateToExtract.equals("-1")) {
			resultValidate = false;
			super.addErrors("errorDateToExtract","Please select Date to Extract");
		}
		
		long ddlTypeOfHeader = form.getPageType();
		if(ddlTypeOfHeader == -1) {
			resultValidate = false;
			super.addErrors("errorTypeOfHeader","Please select Type of Header on Webpage");
		}
		
		//extraction Time
		long countFalse = 0;
		List<ExtractionTimeForm> chkTime = form.getExtractionTimeFormList();
		for (ExtractionTimeForm item : chkTime) {
			if(!item.getExtractionTime().equalsIgnoreCase("false")) {
//				item.setChk("true");
			}
			else {
				countFalse++;
			}
		}
		if(countFalse == chkTime.size()) {
			resultValidate = false;
			super.addErrors("errorTimeToExtract","Please select at least one of these time");
		}
		//extraction Time
		
		Long ddlExtractionType = form.getExtractionType();
		if(ddlExtractionType == 1) {
			
			String tbCSSSelector = form.getCssDate();
			if(UIUtil.isEmptyOrNull(tbCSSSelector)) {
				resultValidate = false;
				super.addErrors("errorCSSSelector","CSS Selector cant be empty");
			}
			
			int countValidateDetailForm = 0;
			List<String> checkExistPair = new ArrayList<String>();
			int runningIndex = 0;
			for (ExtractionDetailForm detailForm : form.getExtractionDetailFormList()) {
				long ddlCurrency = detailForm.getCurrency();
				long ddlValue = detailForm.getValue();
				
				String validateDDL = ddlCurrency + " " + ddlValue;
				String textExtractBuyingRate = detailForm.getExtractionBuyingRate();
				String textExtractSellingRate = detailForm.getExtractionSellingRate();
				
				if(ddlCurrency == -1) {
					resultValidate = false;
					super.addErrors("s1errorCurrency" + runningIndex, "Please select currency");
				}
				
				if(ddlValue == -1) {
					resultValidate = false;
					super.addErrors("s1errorValue" + runningIndex, "Please select value");
				}
				
				if(UIUtil.isEmptyOrNull(textExtractBuyingRate) && UIUtil.isEmptyOrNull(textExtractSellingRate)) {
					resultValidate = false;
					super.addErrors("s1errorExtractionBuyingRate" + runningIndex, "At least 1 rate must be fill");
				}
													
				if(checkExistPair.contains(validateDDL)) {
					resultValidate = false;
					super.addErrors("errorSettingTable1-2", "Currency and value have some duplicate data");
				}
				else {
					checkExistPair.add(validateDDL);
					countValidateDetailForm++;
				}
				
				runningIndex++;
			}
			
			if(countValidateDetailForm == 0) {
				super.addErrors("errorSettingTable1-1", "Please fill some valid setting extraction that you need to scrap later");
				resultValidate = false;
			}
			
			if(resultValidate && "A".equalsIgnoreCase(form.getStatus())) {
				
				try {
					seleniumService.initialWebDriver();
					seleniumService.scrapPage(tbURL);
					if(seleniumService.isSuccessfullyScrap()) {
						
						int runningNumber = 0;						
						
						for (ExtractionDetailForm detailForm : extractionForm.getExtractionDetailFormList()) {
							
							String textExtractCurrency = detailForm.getExtractionCurrency();
							String textExtractBuyingRate = detailForm.getExtractionBuyingRate();
							String textExtractSellingRate = detailForm.getExtractionSellingRate();
							
							if(UIUtil.isEmptyOrNull(textExtractCurrency)) {
								super.addErrors("s1ExtractionCurrency" + runningNumber, "");
							}
							else {
								String resultExtractCurrency = seleniumService.getFieldData(textExtractCurrency,false);
								if(UIUtil.isEmptyOrNull(resultExtractCurrency)) {
									resultValidate = false;
									super.addErrors("s1errorExtractionCurrency" + runningNumber, "No Data");
								}
								else {
									try {
			            				double testParse = Double.parseDouble(resultExtractCurrency);
										resultValidate = false;
										super.addErrors("s1errorExtractionCurrency" + runningNumber, "Data Incorrect");
			            			}
			            			catch (Exception e) {
										super.addErrors("s1ExtractionCurrency" + runningNumber, resultExtractCurrency);
									}
								}
							}
							
							if(UIUtil.isEmptyOrNull(textExtractBuyingRate)) {
								super.addErrors("s1ExtractionBuyingRate" + runningNumber, "");
							}
							else {
								String resultExtractBuyingRate = seleniumService.getFieldData(textExtractBuyingRate,false);
								if(UIUtil.isEmptyOrNull(resultExtractBuyingRate)) {
									resultValidate = false;
									super.addErrors("s1errorExtractionBuyingRate" + runningNumber, "No Data");
								}
								else {
									try {
			            				double testParse = Double.parseDouble(resultExtractBuyingRate);
										super.addErrors("s1ExtractionBuyingRate" + runningNumber, resultExtractBuyingRate);
			            			}
			            			catch (Exception e) {
										resultValidate = false;
										super.addErrors("s1errorExtractionBuyingRate" + runningNumber, "Data Incorrect");
									}
								}
							}
							
							if(UIUtil.isEmptyOrNull(textExtractSellingRate)) {
								super.addErrors("s1ExtractionSellingRate" + runningNumber, "");
							}
							else {
								String resultExtractSellingRate = seleniumService.getFieldData(textExtractSellingRate,false);
								if(UIUtil.isEmptyOrNull(resultExtractSellingRate)) {
									resultValidate = false;
									super.addErrors("s1errorExtractionSellingRate" + runningNumber, "No Data");
								}
								else {
									try {
			            				double testParse = Double.parseDouble(resultExtractSellingRate);
										super.addErrors("s1ExtractionSellingRate" + runningNumber, resultExtractSellingRate);
			            			}
			            			catch (Exception e) {
										resultValidate = false;
										super.addErrors("s1errorExtractionSellingRate" + runningNumber, "Data Incorrect");
									}
								}
							}
						
							runningNumber++;
						}
						
						
						runningNumber = 0;
						for(ExtractionDetailForm item : form.getExtractionDetailFormList()) {
							if(item.getCurrency() != -1
									&& !UIUtil.isEmptyOrNull(item.getExtractionCurrency())) {
								String resultExtractionCurrency = seleniumService.getFieldData(item.getExtractionCurrency(),false);
								if(!resultExtractionCurrency.contains(currencyMap.get(item.getCurrency()).getCode())) {
									resultValidate = false;
									super.addErrors("s1errorExtractionCurrency" + runningNumber
											, "Currency is not match - " + resultExtractionCurrency);
								}
							}
							runningNumber++;
						}
						
					}
					else {
						resultValidate = false;
						super.addErrors("errorURL","cant open the webpage, please check the url and try again after few minutes");
					}
					seleniumService.quitWebDriver();
				}
				catch (Exception e) {
					resultValidate = false;
					super.addErrors("errorTotal", e.getMessage() + e.getStackTrace());
					seleniumService.quitWebDriver();
				}
			}
		}
		else if(ddlExtractionType == 2) {
			
			int countValidateDetailForm = 0;
			List<String> checkExistPair = new ArrayList<String>(); 
			int runningNumber = 0;
			for (ExtractionDetailForm detailForm : form.getExtractionDetailFormList()) {
				long ddlCurrency = detailForm.getCurrency();
				long ddlValue = detailForm.getValue();
				String validateDDL = ddlCurrency + " " + ddlValue;
				String textExtractBuyingRate = detailForm.getExtractionBuyingRate();
				String textExtractSellingRate = detailForm.getExtractionSellingRate();
				
				if(ddlCurrency == -1) {
					resultValidate = false;
					super.addErrors("s2errorCurrency" + runningNumber, "Please select currency");
				}
				
				if(ddlValue == -1) {
					resultValidate = false;
					super.addErrors("s2errorValue" + runningNumber, "Please select value");
				}
				
				if(UIUtil.isEmptyOrNull(textExtractBuyingRate) && UIUtil.isEmptyOrNull(textExtractSellingRate)) {
					resultValidate = false;
					super.addErrors("s2errorExtractionBuyingRate" + runningNumber,"at least 1 rate must be fill");
				}
				
				if(checkExistPair.contains(validateDDL)) {
					super.addErrors("errorSettingTable2-2", "Currency and value have some duplicate data");
					resultValidate = false;
				}
				else {
					checkExistPair.add(validateDDL);
					countValidateDetailForm++;
				}
				
				runningNumber++;
			}
			
			if(countValidateDetailForm == 0) {
				super.addErrors("errorSettingTable2-1", "Please fill valid some setting extraction that you need to scrap later");
				resultValidate = false;
			}
			
			if(resultValidate && "A".equalsIgnoreCase(form.getStatus())) {
				try {
					seleniumService.initialWebDriver();
					seleniumService.scrapPage(tbURL);
					if(seleniumService.isSuccessfullyScrap()) {
						seleniumService.dividePageResult("td");
						runningNumber = 0;
						for (ExtractionDetailForm detailForm : extractionForm.getExtractionDetailFormList()) {
							if(detailForm.getCurrency() == -1) {
								super.addErrors("s2errorExtractionCurrency" + runningNumber, "currency must be selected");
								resultValidate = false;
							}
							else {
								
								String currencyCode = currencyMap.get(detailForm.getCurrency()).getCode();
								
								String textExtractBuyingRate = detailForm.getExtractionBuyingRate();
								String textExtractSellingRate = detailForm.getExtractionSellingRate();
								
								if(UIUtil.isEmptyOrNull(textExtractBuyingRate)) {
									super.addErrors("s2ExtractionBuyingRate" + runningNumber, "");
								}
								else {
									boolean isNumber = true;
									try {
							    		int testParse = Integer.parseInt(textExtractBuyingRate);
							    	}
							    	catch (Exception e) {
							    		isNumber = false;
										super.addErrors("s2errorExtractionBuyingRate" + runningNumber, "Need number format");
									}
									
									if(isNumber) {
										String resultExtractBuyingRate = seleniumService.getFieldData(currencyCode, Integer.parseInt(textExtractBuyingRate));
										if(UIUtil.isEmptyOrNull(resultExtractBuyingRate)) {
											super.addErrors("s2errorExtractionBuyingRate" + runningNumber, "No Data");
										}
										else {
											super.addErrors("s2ExtractionBuyingRate" + runningNumber, resultExtractBuyingRate);
										}
									}
									
								}
								
								if(UIUtil.isEmptyOrNull(textExtractSellingRate)) {
									super.addErrors("s2ExtractionSellingRate" + runningNumber, "");
								}
								else {
									boolean isNumber = true;
									try {
							    		int testParse = Integer.parseInt(textExtractSellingRate);
							    	}
							    	catch (Exception e) {
							    		isNumber = false;
										super.addErrors("s2errorExtractionSellingRate" + runningNumber, "Need number format");
									}
									
									if(isNumber) {
										String resultExtractSellingRate = seleniumService.getFieldData(currencyCode, Integer.parseInt(textExtractSellingRate));
										if(UIUtil.isEmptyOrNull(resultExtractSellingRate)) {
											super.addErrors("s2errorExtractionSellingRate" + runningNumber, "No Data");
										}
										else {
											super.addErrors("s2ExtractionSellingRate" + runningNumber, resultExtractSellingRate);
										}
									}

								}
							}
						}	
					}
					else {
						resultValidate = false;
						super.addErrors("errorURL","cant open the webpage, please check the url and try again after few minutes");
					}
					seleniumService.quitWebDriver();
				}
				catch (Exception e) {
					resultValidate = false;
					super.addErrors("errorTotal", e.getMessage() + e.getStackTrace());
					seleniumService.quitWebDriver();
				}
			}
		}
		else if(ddlExtractionType == 3) {
			if(form.getSelectedCurrencyAPI().size() == 0) {
				super.addErrors("errorSettingTable3","Please select at least one currency");
				resultValidate = false;
			}
		}
				
		return resultValidate;
	}

	public void populateFormToOb(ExtractionForm form,
			Extraction ob) {
		ob.setId(form.getId());
		Bank tempBank = new Bank();
		tempBank.setId(form.getBankID());
		tempBank.setBankName(form.getBankName());
		tempBank.setBankShortName(form.getBankShortName());
		tempBank.setCountry(countryService.getById(form.getBankCountry()));
		ob.setBank(tempBank);
		ob.setBaseCurrency(currencyMap.get(form.getBaseCurrency()));
		ob.setBankURL(form.getUrl());
		ob.setExtractionDate(form.getExtractionDate());
		
		ExtractionTime timeOB;
		for(ExtractionTimeForm item : form.getExtractionTimeFormList()) {
			timeOB = new ExtractionTime();
			if(item.getExtractionTime().equalsIgnoreCase("false")) {
				continue;
			}
			else {
				timeOB.setId(item.getId());
				timeOB.setExtractionTime(Long.parseLong(item.getExtractionTime()));
				timeOB.setExtraction(ob);
				ob.addExtractionTimeList(timeOB);
			}
		}
		
		ob.setExtractionType(form.getExtractionType());
		ob.setFormatDate(form.getFormatDate());
		ob.setPageType(form.getPageType());
		ob.setPairCurrencyType(form.getPairCurrencyType());
		ob.setStatus(form.getStatus());

		HttpSession session = getReq().getSession();
		IUser user = (IUser)session.getAttribute(SESSION_USER);
		
		if(ob.getExtractionType() == 1) {
			ExtractionDetail detailOB;
			for(ExtractionDetailForm item : form.getExtractionDetailFormList()){
				detailOB = new ExtractionDetail();
//				if(item.getCurrency() == -1 || item.getExtractionBuyingRate().equals("")
//						|| item.getExtractionSellingRate().equals("")
//						|| item.getValue() == -1) {
//					continue;
//				}
				detailOB.setCurrency(currencyMap.get(item.getCurrency()));
				detailOB.setExtractBuyingRate(item.getExtractionBuyingRate());
				detailOB.setExtractCurrency(item.getExtractionCurrency());
				detailOB.setExtraction(ob);
				detailOB.setExtractSellingRate(item.getExtractionSellingRate());
				detailOB.setId(item.getId());
				detailOB.setValue((long)item.getValue());
				detailOB.setAddDate(new Date());
				detailOB.setAddUser(user.getLogOnId());
				detailOB.setChangeDate(new Date());
				detailOB.setChangeUser(user.getLogOnId());
				ob.addExtractionDetailList(detailOB);
			}
			ob.setCssDate(form.getCssDate());
		}
		else if(ob.getExtractionType() == 2) {
			ExtractionDetail detailOB;
			for(ExtractionDetailForm item : form.getExtractionDetailFormList()){
				detailOB = new ExtractionDetail();
//				if(item.getCurrency() == -1 || item.getExtractionBuyingRate().equals("")
//						|| item.getExtractionSellingRate().equals("") || item.getValue() == -1) {
//					continue;
//				}
				
				detailOB.setCurrency(currencyMap.get(item.getCurrency()));
				detailOB.setExtractBuyingRate(item.getExtractionBuyingRate());
				detailOB.setExtraction(ob);
				detailOB.setExtractSellingRate(item.getExtractionSellingRate());
				detailOB.setId(item.getId());
				detailOB.setValue((long)item.getValue());
				detailOB.setAddDate(new Date());
				detailOB.setAddUser(user.getLogOnId());
				detailOB.setChangeDate(new Date());
				detailOB.setChangeUser(user.getLogOnId());
				ob.addExtractionDetailList(detailOB);
			}
		}
		else if(ob.getExtractionType() == 3) {
			
			List<ExtractionDetail> newDetailOBList = new ArrayList<ExtractionDetail>();
			for(int i=0;i<form.getSelectedCurrencyAPI().size();i++) {
				ExtractionDetail newDetailOB = new ExtractionDetail();
				newDetailOB.setValue(1);
				newDetailOB.setExtraction(ob);
				newDetailOB.setAddDate(new Date());
				newDetailOB.setAddUser(user.getLogOnId());
				newDetailOB.setChangeDate(new Date());
				newDetailOB.setChangeUser(user.getLogOnId());
				boolean isFound = false;
				Long cur = form.getSelectedCurrencyAPI().get(i);
				for(ExtractionDetail item : extraction.getExtractionDetailList()) {
					if(item.getCurrency().getId() == cur) {
						newDetailOB.setCurrency(item.getCurrency());
						newDetailOB.setId(item.getId());
						newDetailOBList.add(newDetailOB);
						isFound = true;
						break;
					}
				}
				if(!isFound) {
					for (Currency item2 : currencyList) {
						if(item2.getId() == cur) {
							newDetailOB.setCurrency(item2);
							break;
						}
					}
					newDetailOBList.add(newDetailOB);
				}
			}
			
			extractionDetailRemoveList = new ArrayList<Long>();
			for(ExtractionDetail item : extraction.getExtractionDetailList()) {
				boolean isFound = false;
				for(ExtractionDetail item2 : newDetailOBList) {
					if(item.getId() == item2.getId()) {
						isFound = true;
						break;
					}
				}
				if(!isFound) {
					extractionDetailRemoveList.add(item.getId());
				}
			}
			
			ob.setExtractionDetailList(new HashSet<ExtractionDetail>(newDetailOBList));
		}
	}
	
	public void populateObToForm(Extraction ob,
			ExtractionForm form) {
		form.setId(ob.getId());
		form.setBankID(ob.getBank().getId());
		form.setBankName(ob.getBank().getBankName());
		form.setBankShortName(ob.getBank().getBankShortName());
		form.setBankCountry(ob.getBank().getCountry().getId());
		form.setCssDate(ob.getCssDate());
		form.setExtractionDate(ob.getExtractionDate());
		form.setExtractionType(ob.getExtractionType());
		form.setFormatDate(ob.getFormatDate());
		form.setPageType(ob.getPageType());
		form.setStatus(ob.getStatus());
		form.setPairCurrencyType(ob.getPairCurrencyType());
		form.setUrl(ob.getBankURL());
		
		ExtractionTimeForm timeForm;
		for(int i=0;i<extractionTimeList.size();i++) {
			timeForm = new ExtractionTimeForm();
			timeForm.setExtractionTime(extractionTimeList.get(i).getValue());
			timeForm.setExtractionTimeLabel(extractionTimeList.get(i).getDescriptionShort());
			
			boolean isBreak = false;
			for(ExtractionTime timeOB : ob.getExtractionTimeList()) {
				if(Long.parseLong(extractionTimeList.get(i).getValue()) == timeOB.getExtractionTime()) {
					timeForm.setId(timeOB.getId());
					timeForm.setChk("true");
					isBreak = true;
					break;
				}
			}
			if(!isBreak) {
				timeForm.setChk("false");
			}
			
			form.addExtractionTimeFormList(timeForm);
		}
		
		ExtractionDetailForm detailForm;
		for (ExtractionDetail detail : ob.getExtractionDetailList()) {
			detailForm = new ExtractionDetailForm();
			detailForm.setCurrency(detail.getCurrency().getId());
			detailForm.setCurrencyCode(detail.getCurrency().getCode());
			detailForm.setExtractionCurrency(detail.getExtractCurrency());
			detailForm.setId(detail.getId());
			detailForm.setValue(detail.getValue());
			detailForm.setExtractionBuyingRate(detail.getExtractBuyingRate());
			detailForm.setExtractionSellingRate(detail.getExtractSellingRate());
			form.addExtractionDetailFormList(detailForm);
			if(ob.getExtractionType() == 3) {
				currencyListSelectedAPI.add(detail.getCurrency());
				currencyListAPIPool.remove(detail.getCurrency());
			}
		}
		
		//For View Page
		
		form.setBankCountryName(ob.getBank().getCountry().getCountryName());
		form.setPairCurrencyTypeName(ob.getPairCurrencyType() == 1 ? "Direct Quote" : "Indirect Quote");
		form.setExtractionTypeName(ob.getExtractionType() == 1 ? "CSS Selector" : "Index Number of TD Tag");
		form.setPageTypeName(ob.getPageType() == 1 ? "Normal" : "Fixed Header");
		form.setStatusName(ob.getStatus().equals("A") ? "Active" : "In-active");
//		form.setCountryCode(ob.getBank().getCountry().getCurrency().getCode());
		
		form.setBaseCurrency(ob.getBaseCurrency().getId());
		form.setBaseCurrencyName(ob.getBaseCurrency().getCode());
		
		//End For View Page
	}
	
	public String getParm() {
		return parm;
	}

	public void setParm(String parm) {
		this.parm = parm;
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return extraction;
	}

	public Extraction getExtraction() {
		return extraction;
	}

	public void setExtraction(Extraction extraction) {
		this.extraction = extraction;
	}

	public List<Extraction> getExtractionList() {
		return extractionList;
	}

	public void setExtractionList(List<Extraction> extractionList) {
		this.extractionList = extractionList;
	}
	
	public List<Long> getExtractionDetailRemoveList() {
		return extractionDetailRemoveList;
	}

	public void setExtractionDetailRemoveList(List<Long> extractionDetailIDRemoveList) {
		this.extractionDetailRemoveList = extractionDetailIDRemoveList;
	}
	
	public ExtractionForm getExtractionForm() {
		return extractionForm;
	}

	public void setExtractionForm(ExtractionForm extractionForm) {
		this.extractionForm = extractionForm;
	}

	public ExtractionSearchForm getExtractionSearchForm() {
		return extractionSearchForm;
	}

	public void setExtractionSearchForm(ExtractionSearchForm extractionSearchForm) {
		this.extractionSearchForm = extractionSearchForm;
	}

	public List<Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}

	public List<Currency> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<Currency> currencyList) {
		this.currencyList = currencyList;
	}
	
	public List<Currency> getCurrencyListAPIPool() {
		return currencyListAPIPool;
	}

	public void setCurrencyListAPIPool(List<Currency> currencyListAPIPool) {
		this.currencyListAPIPool = currencyListAPIPool;
	}

	public List<Currency> getCurrencyListSelectedAPI() {
		return currencyListSelectedAPI;
	}

	public void setCurrencyListSelectedAPI(List<Currency> currencyListSelectedAPI) {
		this.currencyListSelectedAPI = currencyListSelectedAPI;
	}

	public List<CodeValue> getExtractionTypeList() {
		return extractionTypeList;
	}

	public void setExtractionTypeList(List<CodeValue> extractionTypeList) {
		this.extractionTypeList = extractionTypeList;
	}

	public List<CodeValue> getPageTypeList() {
		return pageTypeList;
	}

	public void setPageTypeList(List<CodeValue> pageTypeList) {
		this.pageTypeList = pageTypeList;
	}

	public List<CodeValue> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CodeValue> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public List<CodeValue> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<CodeValue> statusList) {
		this.statusList = statusList;
	}

	public List<CodeValue> getFormatDateList() {
		return formatDateList;
	}

	public void setFormatDateList(List<CodeValue> formatDateList) {
		this.formatDateList = formatDateList;
	}

	public List<CodeValue> getExtractionDateList() {
		return extractionDateList;
	}

	public void setExtractionDateList(List<CodeValue> extractionDateList) {
		this.extractionDateList = extractionDateList;
	}
	
	public String getDefaultStatusValue() {
		return IPageContains.RECORD_STS_ACTIVE;
	}
}
