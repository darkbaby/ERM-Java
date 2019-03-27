package com.esynergy.erm.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.esynergy.common.web.action.ActionCommon;
import com.esynergy.erm.common.util.UIUtil;
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
import com.esynergy.erm.service.ExtractionDetailService;
import com.esynergy.erm.service.ExtractionService;
import com.esynergy.erm.service.ExtractionTimeService;
import com.esynergy.erm.service.PreviewExtractionService;
import com.esynergy.erm.service.ScrapExtractionRunable;
import com.esynergy.erm.service.ScrapExtractionService;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings({ "serial" })
public class ExtractionAction extends ActionCommon {
	
	private static final Logger logger = Logger
			.getLogger(ExtractionAction.class);
	
	private ExtractionForm extractionForm = new ExtractionForm();
	
	private List<Extraction> extractionList = new ArrayList<Extraction>();
	private ExtractionSearchForm extractionSearchForm = new ExtractionSearchForm();
	
	private String parm;
	
	private String menuName;
	
	@Autowired
	private MandatoryAsset mandatoryAsset;
		
	@Autowired
	private ExtractionService extractionService;
	
	@Autowired
	private ExtractionDetailService extractionDetailService;
	
	@Autowired
	private ExtractionTimeService extractionTimeService;
		
	@Autowired
	private BankService bankService;
	
	@Autowired
	private PreviewExtractionService previewExtractionServiceService;
	
	@Autowired
	private ScrapExtractionService scrapExtractionService;
	
	private List<Currency> currencyList = new ArrayList<Currency>();
	private Map<Long,Currency> currencyMap = new HashMap<Long,Currency>();
	private List<Country> countryList = new ArrayList<Country>();
	private Map<Long, Country> countryMap = new HashMap<Long, Country>();
	private List<CodeValue> extractionTimeList = new ArrayList<CodeValue>();
	private List<CodeValue> extractionTypeList = new ArrayList<CodeValue>();
	private List<CodeValue> extractionDateList = new ArrayList<CodeValue>();
	private List<CodeValue> pageTypeList = new ArrayList<CodeValue>();
	private List<CodeValue> currencyTypeList = new ArrayList<CodeValue>();
	private List<CodeValue> statusList = new ArrayList<CodeValue>();
	private List<CodeValue> formatDateList = new ArrayList<CodeValue>();
	private List<CodeValue> valueList = new ArrayList<CodeValue>();
	private String pythonDirectory = "";
		
	@SuppressWarnings("unchecked")
	public void setServletRequest(HttpServletRequest request) {
		super.setServletRequest(request);

		request.setAttribute(MAIN_MENU_ATTR, MENU_EXCHANGE_RATE);
		request.setAttribute(SUB_MENU_ATTR, MENU_EXCHANGE_RATE_AUTO);
		request.setAttribute(SUB_MENU1_ATTR, MENU_EXTRACTION_MANAGE);
		
		currencyList = mandatoryAsset.getCurrencyList();
		currencyMap = mandatoryAsset.getCurrencyMap();
		countryList = mandatoryAsset.getCountryList();
		countryMap = mandatoryAsset.getCountryMap();
		extractionTimeList = mandatoryAsset.getExtractionTimeList();
		extractionTypeList = mandatoryAsset.getExtractionTypeList();
		extractionDateList = mandatoryAsset.getExtractionDateList();
		pageTypeList = mandatoryAsset.getExtractionPageTypeList();
		currencyTypeList = mandatoryAsset.getExtractionCurrencyTypeList();
		statusList = mandatoryAsset.getExtractionStatusList();
		formatDateList = mandatoryAsset.getExtractionFormatDateList();
		valueList = mandatoryAsset.getExtractionValueList();
		pythonDirectory = mandatoryAsset.getPythonDirectory();
	}

	public HttpServletRequest getReq() {
		return (HttpServletRequest) ActionContext.getContext().get(
				ServletActionContext.HTTP_REQUEST);
	}
	
	public String prepareCreate() {
		
		menuName = getText("header.extraction.create");
		
		extractionForm = new ExtractionForm();
		extractionForm.setExtractionType(1);
		List<ExtractionDetailForm> tempDetailFormList = new ArrayList<ExtractionDetailForm>();
		List<ExtractionDetailForm> tempDetailFormList2 = new ArrayList<ExtractionDetailForm>();
		for(int i=0;i<3;i++) {
			ExtractionDetailForm tempDetailForm = new ExtractionDetailForm();
			ExtractionDetailForm tempDetailForm2 = new ExtractionDetailForm();
			tempDetailFormList.add(tempDetailForm);
			tempDetailFormList2.add(tempDetailForm2);
		}
		extractionForm.setExtractionDetailFormList(tempDetailFormList);
		extractionForm.setExtractionDetailFormList2(tempDetailFormList2);
		
		populateTimeList(extractionForm);
		
		return SUCCESS;
	}
	
	public String prepareEdit() {
		menuName = getText("header.extraction.edit");
		
		if(getReq().getParameter("parm") == null) {
			return INPUT;
		}
		else {
			long id = Long.parseLong(UIUtil.prepareConvertStringToNumber(getReq().getParameter("parm")));
			Extraction extraction = extractionService.getById(id);
			extractionForm = new ExtractionForm();
			populateObToForm(extraction, extractionForm);
			return SUCCESS;
		}
	}
	
	public String prepareManage(){		
		if(getReq().getParameter("updated") != null && getReq().getParameter("updated").equalsIgnoreCase("true")){
		 	super.addActionMsg("resultMessage",getText("msg.action.save.success"));
		 	super.addErrors("haveResult",true);
		}
		if(getReq().getParameter("removed") != null && getReq().getParameter("removed").equalsIgnoreCase("true")){
			super.addActionMsg("resultMessage",getText("msg.action.remove.success"));
		 	super.addErrors("haveResult",true);
		}
		if(getReq().getParameter("begin") != null && getReq().getParameter("begin").equalsIgnoreCase("true")){
			super.addActionMsg("resultMessage",getText("msg.scrap.all.begin"));
		 	super.addErrors("haveResult",true);
		}
		if(getReq().getParameter("running") != null && getReq().getParameter("running").equalsIgnoreCase("true")){
			super.addActionMsg("resultMessageRed",getText("msg.scrap.all.running"));
		 	super.addErrors("haveResultRed",true);
		}
		if(getReq().getParameter("error") != null && getReq().getParameter("error").equalsIgnoreCase("true")){
			super.addActionMsg("resultMessageRed",getText("msg.invalid.field"));
		 	super.addErrors("haveResultRed",true);
		}
		
		menuName = getText("header.mangement.extraction");
		
	    extractionList = extractionService.listAll();
	    extractionSearchForm = new ExtractionSearchForm();
	    return SUCCESS;
	}
   
	public String search(){
		menuName = getText("header.mangement.extraction");
		
		extractionList = extractionService.search(extractionSearchForm.getBankName(), extractionSearchForm.getBankCountry(), extractionSearchForm.getTypeOfSetting(), extractionSearchForm.getStatus());
		return SUCCESS;
	}
   
	public String prepareView(){
		
		menuName = getText("header.extraction.view");

		extractionForm = new ExtractionForm();
		if(!UIUtil.isEmptyOrNull(getReq().getParameter("parm"))){
			Extraction extraction = extractionService.getById(Long.parseLong(getReq().getParameter("parm").trim()));
			populateObToForm(extraction,extractionForm);
		}
		return SUCCESS;
	}
	
	public String removeDetailForm() {
		
		if(extractionForm.getExtractionType() == 1) {
			List<ExtractionDetailForm> removeList = new ArrayList<ExtractionDetailForm>();
			for (int i = 0; i < extractionForm.getExtractionDetailFormList().size(); i++) {
				ExtractionDetailForm formDetail = extractionForm.getExtractionDetailFormList().get(i);
				if (!UIUtil.isEmptyOrNull(formDetail.getChk())
						&& (formDetail.getChk().equals(CHK_ON) 
						|| formDetail.getChk().equals("true"))) {
					removeList.add(formDetail);
					if (formDetail.getId() > 0) {
						extractionForm.getExtractionDetailFormRemoveList().add(formDetail.getId());
					}
				}
			}
			extractionForm.getExtractionDetailFormList().removeAll(removeList);
		}
		else if(extractionForm.getExtractionType() == 2) {
			List<ExtractionDetailForm> removeList = new ArrayList<ExtractionDetailForm>();
			for (int i = 0; i < extractionForm.getExtractionDetailFormList2().size(); i++) {
				ExtractionDetailForm formDetail = extractionForm.getExtractionDetailFormList2().get(i);
				if (!UIUtil.isEmptyOrNull(formDetail.getChk())
						&& (formDetail.getChk().equals(CHK_ON) 
						|| formDetail.getChk().equals("true"))) {
					removeList.add(formDetail);
					if (formDetail.getId() > 0) {
						extractionForm.getExtractionDetailFormRemoveList().add(formDetail.getId());
					}
				}
			}
			extractionForm.getExtractionDetailFormList2().removeAll(removeList);
		}
		
		////////////////
		
		populateTimeList(extractionForm);
		
		return SUCCESS;
	}
	
	public String compose() {
		
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
			
			Extraction tempHeader = null;
			if(extractionForm.getId() > 0) {
				tempHeader = extractionService.getById(extractionForm.getId());
			}
			
			if(validateForm(tempHeader, extractionForm)) {
				System.out.println("validate");
			}
			else {
				super.addActionMsg("error", super.getText("error.action.message"));
				System.out.println("invalidate");
				populateTimeList(extractionForm);
				return INPUT;
			}
			
//			Extraction extraction = extractionService.getById(extractionForm.getId());
			Extraction header = new Extraction();
			populateFormToOb(extractionForm, header);
						
			if(header.getId() > 0) {
				if(tempHeader.getExtractionType() != header.getExtractionType()) {
					while(tempHeader.getExtractionDetailList().iterator().hasNext()) {
						ExtractionDetail detail = new ExtractionDetail();
						detail.setId(tempHeader.getExtractionDetailList().iterator().next().getId());
						extractionDetailService.delete(detail);
					}
				}
				else {
					for(int i=0;i<extractionForm.getExtractionDetailFormRemoveList().size();i++) {
						ExtractionDetail detail = new ExtractionDetail();
						detail.setId(extractionForm.getExtractionDetailFormRemoveList().get(i));
						extractionDetailService.delete(detail);
					}
				}
				
				for(ExtractionTime item : tempHeader.getExtractionTimeList()) {
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
				
				for(ExtractionDetail item : header.getExtractionDetailList()) {
					for(ExtractionDetail item2 : tempHeader.getExtractionDetailList()) {
						if(item.getId() == item2.getId()) {
							item.setAddDate(item2.getAddDate());
							item.setAddUser(item2.getAddUser());
							break;
						}
					}
				}
				
				header.setAddUser(tempHeader.getAddUser());
				header.setAddDate(tempHeader.getAddDate());
				header.setChangeDate(new Date());
				header.setChangeUser(iUser.getLogOnId());
				extractionService.update(header);
			}
			else {
				
				header.setAddDate(new Date());
				header.setAddUser(iUser.getLogOnId());
				header.setChangeDate(new Date());
				header.setChangeUser(iUser.getLogOnId());
				extractionService.save(header);
			}


			return SUCCESS;
		}
		catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
	}
	
	public String remove() {
				
		if(UIUtil.isEmptyOrNull(getReq().getParameter("parm"))) {
			return INPUT;
		}
		else {
			long idRemove = Long.parseLong(getReq().getParameter("parm"));
			Extraction ob = extractionService.getById(idRemove);
			ob.setDeleteFlag(1);
			extractionService.update(ob);
			return SUCCESS;
		}		
	}
	
	public String preview() {		
		
		String tbURL = extractionForm.getUrl();
		Long ddlExtractionType = extractionForm.getExtractionType();

		if(!UIUtil.isEmptyOrNull(tbURL)) {
			
			try {
				int runningNumber = 0;
				List<String> descriptionCssSelectors = new ArrayList<String>();
				descriptionCssSelectors.add(ddlExtractionType.toString());
				descriptionCssSelectors.add(tbURL);
				if(ddlExtractionType == 1) {
					for (ExtractionDetailForm detailForm : extractionForm.getExtractionDetailFormList()) {
						String textExtractCurrency = detailForm.getExtractionCurrency();
						String textExtractBuyingRate = detailForm.getExtractionBuyingRate();
						String textExtractSellingRate = detailForm.getExtractionSellingRate();
						
						descriptionCssSelectors.add(textExtractCurrency + "," + textExtractBuyingRate
								+ "," + textExtractSellingRate);
					}
					previewExtractionServiceService.initialProcessBuilder();
					previewExtractionServiceService.setPythonDirectory(pythonDirectory);
					previewExtractionServiceService.createInputFile(descriptionCssSelectors);
					previewExtractionServiceService.callPythonPhantomJS();
					previewExtractionServiceService.readOutputFile();
					if(previewExtractionServiceService.getIsSuccessful()) {
						for (ExtractionDetailForm detailForm : extractionForm.getExtractionDetailFormList()) {
							String resultExtractCurrency = previewExtractionServiceService.getFieldValue(runningNumber+1, 1);
							String resultExtractBuyingRate = previewExtractionServiceService.getFieldValue(runningNumber+1, 2);
							String resultExtractSellingRate = previewExtractionServiceService.getFieldValue(runningNumber+1, 3);

							if(resultExtractCurrency.equalsIgnoreCase("No Data")
									|| resultExtractCurrency.equalsIgnoreCase("Data Incorrect")) {
								super.addErrors("s1errorExtractionCurrency" + runningNumber, resultExtractCurrency);
							}
							else {
								super.addErrors("s1ExtractionCurrency" + runningNumber, resultExtractCurrency);
							}
							
							if(resultExtractBuyingRate.equalsIgnoreCase("No Data")
									|| resultExtractBuyingRate.equalsIgnoreCase("Data Incorrect")) {
								super.addErrors("s1errorExtractionBuyingRate" + runningNumber, resultExtractBuyingRate);
							}
							else {
								super.addErrors("s1ExtractionBuyingRate" + runningNumber, resultExtractBuyingRate);
							}
							
							if(resultExtractSellingRate.equalsIgnoreCase("No Data")
									|| resultExtractSellingRate.equalsIgnoreCase("Data Incorrect")) {
								super.addErrors("s1errorExtractionSellingRate" + runningNumber, resultExtractSellingRate);
							}
							else {
								super.addErrors("s1ExtractionSellingRate" + runningNumber, resultExtractSellingRate);
							}
							
							runningNumber++;
						}
					}
					else {
						super.addErrors("errorURL", previewExtractionServiceService.getErrorFromFile());
					}
					previewExtractionServiceService.deleteOutputFile();
				}
				else if(ddlExtractionType == 2) {
					for (ExtractionDetailForm detailForm : extractionForm.getExtractionDetailFormList2()) {
						String currencyCode = currencyMap.get(detailForm.getCurrency()).getCode();
						String textExtractBuyingRate = detailForm.getExtractionBuyingRate();
						String textExtractSellingRate = detailForm.getExtractionSellingRate();
						
						descriptionCssSelectors.add(currencyCode + "," + textExtractBuyingRate + "," + textExtractSellingRate);
					}
					previewExtractionServiceService.initialProcessBuilder();
					previewExtractionServiceService.setPythonDirectory(pythonDirectory);
					previewExtractionServiceService.createInputFile(descriptionCssSelectors);
					previewExtractionServiceService.callPythonPhantomJS();
					previewExtractionServiceService.readOutputFile();
					if(previewExtractionServiceService.getIsSuccessful()) {
						for (ExtractionDetailForm detailForm : extractionForm.getExtractionDetailFormList2()) {
							String resultExtractBuyingRate = previewExtractionServiceService.getFieldValue(runningNumber+1, 1);
							String resultExtractSellingRate = previewExtractionServiceService.getFieldValue(runningNumber+1, 2);
							
							if(resultExtractBuyingRate.equalsIgnoreCase("No Data")
									|| resultExtractBuyingRate.equalsIgnoreCase("Data Incorrect")) {
								super.addErrors("s2errorExtractionBuyingRate" + runningNumber, resultExtractBuyingRate);
							}
							else {
								super.addErrors("s2ExtractionBuyingRate" + runningNumber, resultExtractBuyingRate);
							}
							
							if(resultExtractSellingRate.equalsIgnoreCase("No Data")
									|| resultExtractSellingRate.equalsIgnoreCase("Data Incorrect")) {
								super.addErrors("s2errorExtractionSellingRate" + runningNumber, resultExtractSellingRate);
							}
							else {
								super.addErrors("s2ExtractionSellingRate" + runningNumber, resultExtractSellingRate);
							}
							
							runningNumber++;
						}
					}
					else {
						super.addErrors("errorURL", previewExtractionServiceService.getErrorFromFile());
					}
					previewExtractionServiceService.deleteOutputFile();
				}
				else {
					super.addErrors("errorURL","extraction type error");
				}
			}
			catch (Exception e) {
				super.addErrors("errorTotal","exception occured");
			}
		}
		else {
			super.addErrors("errorURL","URL cant be empty");
		}
		
		populateTimeList(extractionForm);
		
		return SUCCESS;
	}
	
	public boolean validateForm(Extraction tempHeader ,ExtractionForm form) {
		
		boolean resultValidate = true;
		
		String tbBankName = form.getBankName();
		if(UIUtil.isEmptyOrNull(tbBankName)) {
			resultValidate = false;
			super.addErrors("errorBankName","Bank Name cant be empty");
		}
		else {
			
			if(tempHeader != null &&
					!tbBankName.equalsIgnoreCase(tempHeader.getBank().getBankName())) {
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
			
			if(tempHeader != null && 
					!tbBankShortName.equalsIgnoreCase(tempHeader.getBank().getBankShortName())) {
				List<Bank> bankList = bankService.getBankWithShortName(tbBankShortName);
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
			
			if(tempHeader != null &&
					!tbURL.equalsIgnoreCase(tempHeader.getBankURL())) {
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
				
				runningIndex = 0;
				List<String> descriptionCssSelectors = new ArrayList<String>();
				descriptionCssSelectors.add(ddlExtractionType.toString());
				descriptionCssSelectors.add(tbURL);
				
				for (ExtractionDetailForm detailForm : extractionForm.getExtractionDetailFormList()) {
					String textExtractCurrency = detailForm.getExtractionCurrency();
					String textExtractBuyingRate = detailForm.getExtractionBuyingRate();
					String textExtractSellingRate = detailForm.getExtractionSellingRate();
					
					descriptionCssSelectors.add(textExtractCurrency + "," + textExtractBuyingRate
							+ "," + textExtractSellingRate);
				}
				previewExtractionServiceService.initialProcessBuilder();
				previewExtractionServiceService.setPythonDirectory(pythonDirectory);
				previewExtractionServiceService.createInputFile(descriptionCssSelectors);
				previewExtractionServiceService.callPythonPhantomJS();
				previewExtractionServiceService.readOutputFile();
				if(previewExtractionServiceService.getIsSuccessful()) {
					for (ExtractionDetailForm detailForm : extractionForm.getExtractionDetailFormList()) {
						String resultExtractCurrency = previewExtractionServiceService.getFieldValue(runningIndex+1, 1);
						String resultExtractBuyingRate = previewExtractionServiceService.getFieldValue(runningIndex+1, 2);
						String resultExtractSellingRate = previewExtractionServiceService.getFieldValue(runningIndex+1, 3);

						if(resultExtractCurrency.equalsIgnoreCase("No Data")
								|| resultExtractCurrency.equalsIgnoreCase("Data Incorrect")) {
							resultValidate = false;
							super.addErrors("s1errorExtractionCurrency" + runningIndex, resultExtractCurrency);
						}
						
						if(resultExtractBuyingRate.equalsIgnoreCase("No Data")
								|| resultExtractBuyingRate.equalsIgnoreCase("Data Incorrect")) {
							resultValidate = false;
							super.addErrors("s1errorExtractionBuyingRate" + runningIndex, resultExtractBuyingRate);
						}
						
						if(resultExtractSellingRate.equalsIgnoreCase("No Data")
								|| resultExtractSellingRate.equalsIgnoreCase("Data Incorrect")) {
							resultValidate = false;
							super.addErrors("s1errorExtractionSellingRate" + runningIndex, resultExtractSellingRate);
						}
						
						runningIndex++;
					}
					
					runningIndex = 0;
					for(ExtractionDetailForm item : form.getExtractionDetailFormList()) {
						if(item.getCurrency() != -1
								&& !UIUtil.isEmptyOrNull(item.getExtractionCurrency())) {
							String resultExtractionCurrency = previewExtractionServiceService.getFieldValue(runningIndex+1, 1);
							if(!resultExtractionCurrency.contains(currencyMap.get(item.getCurrency()).getCode())) {
								resultValidate = false;
								super.addErrors("s1errorExtractionCurrency" + runningIndex
										, "Currency is not match - " + resultExtractionCurrency);
							}
						}
						runningIndex++;
					}
				}
				else {
					resultValidate = false;
					super.addErrors("errorURL", previewExtractionServiceService.getErrorFromFile());
				}
				previewExtractionServiceService.deleteOutputFile();
			}
		}
		else if(ddlExtractionType == 2) {
			
			int countValidateDetailForm = 0;
			List<String> checkExistPair = new ArrayList<String>(); 
			int runningNumber = 0;
			for (ExtractionDetailForm detailForm : form.getExtractionDetailFormList2()) {
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
				
				runningNumber = 0;
				List<String> descriptionCssSelectors = new ArrayList<String>();
				descriptionCssSelectors.add(ddlExtractionType.toString());
				descriptionCssSelectors.add(tbURL);
				
				for (ExtractionDetailForm detailForm : extractionForm.getExtractionDetailFormList2()) {
					String currencyCode = currencyMap.get(detailForm.getCurrency()).getCode();
					String textExtractBuyingRate = detailForm.getExtractionBuyingRate();
					String textExtractSellingRate = detailForm.getExtractionSellingRate();
					
					descriptionCssSelectors.add(currencyCode + "," + textExtractBuyingRate + "," + textExtractSellingRate);
				}
				previewExtractionServiceService.initialProcessBuilder();
				previewExtractionServiceService.setPythonDirectory(pythonDirectory);
				previewExtractionServiceService.createInputFile(descriptionCssSelectors);
				previewExtractionServiceService.callPythonPhantomJS();
				previewExtractionServiceService.readOutputFile();
				if(previewExtractionServiceService.getIsSuccessful()) {
					for (ExtractionDetailForm detailForm : extractionForm.getExtractionDetailFormList2()) {
						String resultExtractBuyingRate = previewExtractionServiceService.getFieldValue(runningNumber+1, 1);
						String resultExtractSellingRate = previewExtractionServiceService.getFieldValue(runningNumber+1, 2);
						
						if(resultExtractBuyingRate.equalsIgnoreCase("No Data")
								|| resultExtractBuyingRate.equalsIgnoreCase("Data Incorrect")) {
							resultValidate = false;
							super.addErrors("s2errorExtractionBuyingRate" + runningNumber, resultExtractBuyingRate);
						}
						
						if(resultExtractSellingRate.equalsIgnoreCase("No Data")
								|| resultExtractSellingRate.equalsIgnoreCase("Data Incorrect")) {
							resultValidate = false;
							super.addErrors("s2errorExtractionSellingRate" + runningNumber, resultExtractSellingRate);
						}
						
						runningNumber++;
					}
				}
				else {
					resultValidate = false;
					super.addErrors("errorURL", previewExtractionServiceService.getErrorFromFile());
				}
				previewExtractionServiceService.deleteOutputFile();
			}
		}
				
		return resultValidate;
	}

	public String scrap() {
		
		
		if(scrapExtractionService.isRunning()) {
			
			return INPUT;
		}
		else {

			if(UIUtil.isEmptyOrNull(getReq().getParameter("parm"))) {
				
				Executor executor = Executors.newSingleThreadExecutor();
				executor.execute(new ScrapExtractionRunable(scrapExtractionService, "0",iUser.getLogOnId()));
			}
			else {
				String forSend = getReq().getParameter("parm");
				try {
					int forSendInt = Integer.parseInt(forSend);
				}
				catch (Exception e) {
					return ERROR;
				}
				Executor executor = Executors.newSingleThreadExecutor();
				executor.execute(new ScrapExtractionRunable(scrapExtractionService, forSend, iUser.getLogOnId()));
			}
			
			return SUCCESS;
		}
		
	}
	
	public void populateFormToOb(ExtractionForm form, Extraction ob) {
		ob.setId(form.getId());
		Bank tempBank = new Bank();
		tempBank.setId(form.getBankID());
		tempBank.setBankName(form.getBankName());
		tempBank.setBankShortName(form.getBankShortName());
		tempBank.setCountry(countryMap.get(form.getBankCountry()));
		ob.setBank(tempBank);
		ob.setExtractionType(form.getExtractionType());
		ob.setBankURL(form.getUrl());
		ob.setFormatDate(form.getFormatDate());
		ob.setBaseCurrency(currencyMap.get(form.getBaseCurrency()));
		ob.setExtractionDate(form.getExtractionDate());
		ob.setPageType(form.getPageType());
		ob.setPairCurrencyType(form.getPairCurrencyType());
		ob.setStatus(form.getStatus());
		
		ExtractionTime timeOB;
		Set<ExtractionTime> tempTimeOBSet = new HashSet<ExtractionTime>();
		for(ExtractionTimeForm item : form.getExtractionTimeFormList()) {
			timeOB = new ExtractionTime();
			if(item.getExtractionTime().equalsIgnoreCase("false")) {
				continue;
			}
			else {
				timeOB.setId(item.getId());
				timeOB.setExtractionTime(Long.parseLong(item.getExtractionTime()));
				timeOB.setExtraction(ob);
				tempTimeOBSet.add(timeOB);
			}
		}
		ob.setExtractionTimeList(tempTimeOBSet);
		
		Set<ExtractionDetail> tempDetailOBSet = new HashSet<ExtractionDetail>();
		if(ob.getExtractionType() == 1) {
			ExtractionDetail detailOB;
			for(ExtractionDetailForm item : form.getExtractionDetailFormList()){
				detailOB = new ExtractionDetail();
				detailOB.setCurrency(currencyMap.get(item.getCurrency()));
				detailOB.setExtractBuyingRate(item.getExtractionBuyingRate());
				detailOB.setExtractCurrency(item.getExtractionCurrency());
				detailOB.setExtraction(ob);
				detailOB.setExtractSellingRate(item.getExtractionSellingRate());
				detailOB.setId(item.getId());
				detailOB.setValue((long)item.getValue());
				detailOB.setAddDate(new Date());
				detailOB.setAddUser(iUser.getLogOnId());
				detailOB.setChangeDate(new Date());
				detailOB.setChangeUser(iUser.getLogOnId());
				tempDetailOBSet.add(detailOB);
			}
			ob.setCssDate(form.getCssDate());
		}
		else if(ob.getExtractionType() == 2) {
			ExtractionDetail detailOB;
			for(ExtractionDetailForm item : form.getExtractionDetailFormList2()){
				detailOB = new ExtractionDetail();
				detailOB.setCurrency(currencyMap.get(item.getCurrency()));
				detailOB.setExtractBuyingRate(item.getExtractionBuyingRate());
				detailOB.setExtraction(ob);
				detailOB.setExtractSellingRate(item.getExtractionSellingRate());
				detailOB.setId(item.getId());
				detailOB.setValue((long)item.getValue());
				detailOB.setAddDate(new Date());
				detailOB.setAddUser(iUser.getLogOnId());
				detailOB.setChangeDate(new Date());
				detailOB.setChangeUser(iUser.getLogOnId());
				tempDetailOBSet.add(detailOB);
			}
		}
		ob.setExtractionDetailList(tempDetailOBSet);
	}
	
	public void populateObToForm(Extraction ob, ExtractionForm form) {
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
			if(ob.getExtractionType() == 1) {
				form.getExtractionDetailFormList().add(detailForm);
			}
			else if(ob.getExtractionType() == 2){
				form.getExtractionDetailFormList2().add(detailForm);
			}
		}
		
		if(ob.getExtractionType() == 1) {
			List<ExtractionDetailForm> tempDetailFormList = new ArrayList<ExtractionDetailForm>();
			for(int i=0;i<3;i++) {
				ExtractionDetailForm tempDetailForm = new ExtractionDetailForm();
				tempDetailFormList.add(tempDetailForm);
			}
			extractionForm.setExtractionDetailFormList2(tempDetailFormList);
		}
		else if(ob.getExtractionType() == 2){
			List<ExtractionDetailForm> tempDetailFormList = new ArrayList<ExtractionDetailForm>();
			for(int i=0;i<3;i++) {
				ExtractionDetailForm tempDetailForm = new ExtractionDetailForm();
				tempDetailFormList.add(tempDetailForm);
			}
			extractionForm.setExtractionDetailFormList(tempDetailFormList);
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
	
	public void populateTimeList(ExtractionForm form) {
		List<String> extractionTimeSelectedValue = new ArrayList<String>();
		for(ExtractionTimeForm item : form.getExtractionTimeFormList()) {
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
		form.setExtractionTimeFormList(newExtractionTimeForm);
	}
	
	public String getParm() {
		return parm;
	}

	public void setParm(String parm) {
		this.parm = parm;
	}

	public List<Extraction> getExtractionList() {
		return extractionList;
	}

	public void setExtractionList(List<Extraction> extractionList) {
		this.extractionList = extractionList;
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

	public List<CodeValue> getValueList() {
		return valueList;
	}

	public void setValueList(List<CodeValue> valueList) {
		this.valueList = valueList;
	}

	public List<CodeValue> getExtractionDateList() {
		return extractionDateList;
	}

	public void setExtractionDateList(List<CodeValue> extractionDateList) {
		this.extractionDateList = extractionDateList;
	}
	
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getDefaultStatusValue() {
		return IPageContains.RECORD_STS_ACTIVE;
	}
}
