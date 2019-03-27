package com.esynergy.erm.web.action;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.esynergy.erm.model.IUser;
import com.esynergy.erm.model.form.ManualTargetForm;
import com.esynergy.erm.model.form.ManualTargetSearchForm;
import com.esynergy.erm.model.form.UserLogonForm;
import com.esynergy.erm.model.ob.CodeValue;
import com.esynergy.erm.model.ob.Currency;
import com.esynergy.erm.model.ob.ManualTarget;
import com.esynergy.erm.model.ob.User;
import com.esynergy.erm.service.ManualTargetService;
import com.opensymphony.xwork2.ActionContext;
import com.esynergy.common.web.action.ActionCommon;

@SuppressWarnings({ "serial", "rawtypes" })
//@Controller("maintainManualAction")
public class MaintainManualAction extends ActionCommon {

	private static final Logger logger = Logger.getLogger(MaintainManualAction.class);
	private List<ManualTarget> manualTargetList = new ArrayList<ManualTarget>();
	private ManualTargetSearchForm manualTargetSearchForm = new ManualTargetSearchForm();
	private ManualTargetForm manualTargetForm = new ManualTargetForm();
	
	private List<IUser> userList = new ArrayList<IUser>();
	private Map<Long, IUser> userMap = new HashMap<Long, IUser>();
	private List<Currency> currencyList = new ArrayList<Currency>();
	private Map<Long,Currency> currencyMap = new HashMap<Long,Currency>();
	private List<CodeValue> statusList = new ArrayList<CodeValue>();
	
	private String menuName;
	
	@Autowired
	private MandatoryAsset mandatoryAsset;
	
	@Autowired private ManualTargetService manualTargetService;
	
	@SuppressWarnings("unchecked")
	public void setServletRequest(HttpServletRequest request) {
		super.setServletRequest(request);
		request.setAttribute(MAIN_MENU_ATTR, MENU_EXCHANGE_RATE);
		request.setAttribute(SUB_MENU_ATTR, MENU_EXCHANGE_RATE_MANUAL);
		request.setAttribute(SUB_MENU1_ATTR, MENU_EXCHANGE_RATE_MANUAL_LIST_MAINTAIN);
		
		currencyList = mandatoryAsset.getCurrencyList();
		currencyMap = mandatoryAsset.getCurrencyMap();
		statusList = mandatoryAsset.getExtractionStatusList();
		userList = mandatoryAsset.getUserList();
		for(int i = userList.size()-1 ; i >= 0 ; i--) {
			IUser item = userList.get(i);
			if(item.getAuthorizeGroup().getId() == 3) {
				continue;
			}
			else {
				userList.remove(i);
			}
		}
		userMap = mandatoryAsset.getUserMap();
	}

 	public HttpServletRequest getReq() {
		return (HttpServletRequest) ActionContext.getContext().get(
				ServletActionContext.HTTP_REQUEST);
	} 

	public String prepareManagement() {
		
		menuName = getText("header.mangement.maintain.manual.list");
		
		if(getReq().getParameter("updated") != null &&  getReq().getParameter("updated").equalsIgnoreCase("true")) {
			super.addActionMsg("resultMessage",getText("msg.action.save.success"));
		 	super.addErrors("haveResult", true);
		}
		
		manualTargetSearchForm = new ManualTargetSearchForm();
		manualTargetList = manualTargetService.listAllNotExpire();
		
		return SUCCESS;
	}
	
	public String search() throws ParseException {
		menuName = getText("header.mangement.maintain.manual.list");
				
		long baseCurrencyID = Long.parseLong(manualTargetSearchForm.getBaseCurrency());
		long pairCurrencyID = Long.parseLong(manualTargetSearchForm.getPairCurrency());

		manualTargetList = manualTargetService.searchByParam(baseCurrencyID, pairCurrencyID);
		
		return SUCCESS;
	}
	
	public String save() throws IOException {

		if(validateForm()) {
			
			Date now = new Date();
	
			ManualTarget ob = null;
			if(manualTargetForm.getId() == 0) {
				ob = new ManualTarget();
				populateFormToOb(manualTargetForm, ob);
				ob.setChangeDate(now);
				ob.setEffectiveDate(now);
				ob.setChangeUser(iUser.getLogOnId());
				ob.setAddDate(now);
				ob.setAddUser(iUser.getLogOnId());
				manualTargetService.save(ob);
			}
			else {
				ob = manualTargetService.getByID(manualTargetForm.getId());
				populateFormToOb(manualTargetForm, ob);
				ob.setChangeDate(now);
				ob.setEffectiveDate(now);
				ob.setChangeUser(iUser.getLogOnId());
				manualTargetService.update(ob);
			}
			
			return SUCCESS;
		}
		else {
			super.addActionMsg("actionTitle", "header.mangement.maintain.manual.list");
			manualTargetSearchForm = new ManualTargetSearchForm();
			manualTargetList = manualTargetService.listAllNotExpire();
			return INPUT;
		}
	}
	
	public boolean validateForm() {
		
		String baseCurrencyIDStr = manualTargetForm.getBaseCurrency();
		String pairCurrencyIDStr = manualTargetForm.getPairCurrency();
		
		long baseCurrencyID = Long.parseLong(baseCurrencyIDStr);
		long pairCurrencyID = Long.parseLong(pairCurrencyIDStr);
		
		if(baseCurrencyID == -1 || pairCurrencyID == -1) {
			super.addActionMsg("error",getText("error.action.message"));
			errors.put("isError", true);
			return false;
		}
		else {
			if(manualTargetForm.getId() > 0) {
				ManualTarget forCheck = manualTargetService.getByID(manualTargetForm.getId());
				if(forCheck.getBaseCurrency().getId() == baseCurrencyID 
						&& forCheck.getPairCurrency().getId() == pairCurrencyID) {
					return true;
				}
			}
			
			List<ManualTarget> checkList = manualTargetService.searchByParam(baseCurrencyID, pairCurrencyID);
			if(checkList == null || checkList.size() == 0) {
				return true;
			}
			else {
				super.addActionMsg("error",getText("error.action.message"));
				super.errors.put("isError", true);
				return false;
			}
		}
	}
	
	public void populateObToFormUser(User ob,UserLogonForm form){
		form = new UserLogonForm();
		form.setId(ob.getId());
		form.setUserLogonId(ob.getLogOnId());
		form.setFirstName(ob.getFirstName());
		form.setLastName(ob.getLastName());
		form.setPwd(ob.getPwd());
	}

	public void populateFormToOb(ManualTargetForm form,
			ManualTarget ob) {
		if(form.getOwnerID() == -1) {
			ob.setOwner(null);
		}
		else {
			
			ob.setOwner((User)userMap.get(form.getOwnerID()));
		}
		ob.setId(form.getId());
		ob.setBaseCurrency(currencyMap.get(Long.parseLong(form.getBaseCurrency())));
		ob.setPairCurrency(currencyMap.get(Long.parseLong(form.getPairCurrency())));
		ob.setStatus(form.getStatus());
	}
	
	public List<ManualTarget> getManualTargetList() {
		return manualTargetList;
	}

	public void setManualTargetList(List<ManualTarget> manualTargetList) {
		this.manualTargetList = manualTargetList;
	}

	public ManualTargetSearchForm getManualTargetSearchForm() {
		return manualTargetSearchForm;
	}

	public void setManualTargetSearchForm(ManualTargetSearchForm manualTargetSearchForm) {
		this.manualTargetSearchForm = manualTargetSearchForm;
	}

	public List<Currency> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<Currency> currencyList) {
		this.currencyList = currencyList;
	}

	public ManualTargetForm getManualTargetForm() {
		return manualTargetForm;
	}

	public void setManualTargetForm(ManualTargetForm manualTargetForm) {
		this.manualTargetForm = manualTargetForm;
	}

	public List<CodeValue> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<CodeValue> statusList) {
		this.statusList = statusList;
	}

	public String getDefaultStatusValue() {
		return IPageContains.RECORD_STS_ACTIVE;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public List<IUser> getUserList() {
		return userList;
	}

	public void setUserList(List<IUser> userList) {
		this.userList = userList;
	}
	
}
