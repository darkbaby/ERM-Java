package com.esynergy.erm.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.esynergy.erm.model.IUser;
import com.esynergy.erm.model.form.UserChangePwdForm;
import com.esynergy.erm.model.ob.CodeValue;
import com.esynergy.erm.model.ob.User;
import com.esynergy.erm.service.CodeValueService;
import com.esynergy.erm.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.esynergy.common.web.action.ActionCommon;
import com.esynergy.erm.common.util.UIUtil;
import com.esynergy.erm.common.util.ValidatorUtil;

@SuppressWarnings({ "serial", "rawtypes" })
public class UserChangePwdAction extends ActionCommon {

	private static final Logger logger = Logger.getLogger(UserChangePwdAction.class);
	private List<CodeValue> statusList = new ArrayList<CodeValue>();
	private UserChangePwdForm userChangePwdForm = new UserChangePwdForm();
	private IUser  user;
	
	private String menuName;
	private String cancelAction;
	
	@Autowired private MandatoryAsset mandatoryAsset;
	
	@Autowired private CodeValueService codeValueService;
	@Autowired private UserService userService;
	
	@SuppressWarnings("unchecked")
	public void setServletRequest(HttpServletRequest request) {
		super.setServletRequest(request);
//		HttpSession session = request.getSession(false) == null ? request
//				.getSession(true) : request.getSession(false);
		//request.setAttribute(MAIN_MENU_ATTR, MENU_USER_MANAGEMENT);
		
//		if(session.getAttribute(SESSION_STATUS_LIST) == null) {
//			statusList = codeValueService.listAllStatus();
//			session.setAttribute(SESSION_STATUS_LIST, statusList);
//		} else {
//			statusList = (List<CodeValue>) session.getAttribute(SESSION_STATUS_LIST);
//		}
		statusList = mandatoryAsset.getExtractionStatusList();
		
		if(getReq().getSession().getAttribute("lastAction") != null) {
			cancelAction = getReq().getSession().getAttribute("lastAction").toString();
		}
		else {
			cancelAction = "prepareDashboard";
		}
		
	}

 	public HttpServletRequest getReq() {
		return (HttpServletRequest) ActionContext.getContext().get(
				ServletActionContext.HTTP_REQUEST);
	} 

 	public String prepareChangePwd() {

 		menuName = getText("header.user.change.pwd");
 		
 		if(getReq().getParameter("updated") != null && getReq().getParameter("updated").equalsIgnoreCase("true")) {
 			super.addActionMsg("resultMessage",getText("msg.action.change.pwd.success"));
		 	super.addErrors("haveResult", true);
 		}
 		
		user = iUser;
		userChangePwdForm = new UserChangePwdForm();
		populateObToForm(user,userChangePwdForm);
		
		return SUCCESS;
	}
	
	public String changePwd(){
		
 		menuName = getText("header.user.change.pwd");
		
		if(validateForm()){
			User u = (User)iUser;
			u.setPwd(userChangePwdForm.getPwd());
			userService.savePwd(u);
			
			IUser updatedUser = userService.getUser(iUser.getLogOnId(), userChangePwdForm.getPwd());
		 	getReq().getSession().setAttribute(SESSION_USER, updatedUser);
			return SUCCESS;
		}
		else {
			user = iUser;
			userChangePwdForm = new UserChangePwdForm();
			populateObToForm(user,userChangePwdForm);
			return INPUT;
		}
		 
	}
	
	public boolean validateForm() {
		
		if(!userService.checkPwd(userChangePwdForm.getCurrentPwd(), iUser)){
			addErrors("currentPwdError", "error.invalid");
		}
		
		if(ValidatorUtil.checkString(userChangePwdForm.getPwd(), true,MIN_PWD,MAX_PWD)){
			String[] errMsgParm = {String.valueOf(MIN_PWD),String.valueOf(MAX_PWD)};
			addErrors("pwdError", "error.require.character", errMsgParm);
		}
		else{
			if(ValidatorUtil.findSpace(userChangePwdForm.getPwd())){
				addErrors("pwdFindSpaceError", "error.find.space");
			}
		}
		
		if(UIUtil.isEmptyOrNull(userChangePwdForm.getPwdConfirm())){
			addErrors("pwdConfirmError", "error.require");
		}
		else{
			if(!userChangePwdForm.getPwd().equals(userChangePwdForm.getPwdConfirm())){
				addErrors("pwdConfirmNotMatError", "error.pwd.confirm");
			}
		}
		 
		if (errors.isEmpty()) {
			return true;
		}
		
		super.addActionMsg("error",getText("error.action.message"));
		errors.put("isError", true);
		return false;
	}
	
	public void populateObToForm(IUser ob,UserChangePwdForm form){
		form.setId(ob.getId());
		form.setUserLogonId(ob.getLogOnId());
		form.setFirstName(ob.getFirstName());
		form.setLastName(ob.getLastName());
		//form.setPwd(ob.getPwd());
		form.setEmailAddr(ob.getEmailAddress());
		form.setCountryId(String.valueOf(ob.getCountry().getId()));
		form.setGroupId(String.valueOf(ob.getAuthorizeGroup().getId()));
		form.setCreatedDate(ob.getCreatedDate());
		form.setCreatedUser(ob.getCreatedUser());
		form.setLastUpdateDate(ob.getLastUpdateDate());
		form.setLastUpdateUser(ob.getLastUpdateUser());
		form.setStatus(ob.getRecordStatus());
	}
	 
//	public String getParm() {
//		return parm;
//	}
//
//	public void setParm(String parm) {
//		this.parm = parm;
//	} 
	
	public IUser getUser() {
		return user;
	}

	public void setUser(IUser user) {
		this.user = user;
	}

	public List<CodeValue> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<CodeValue> statusList) {
		this.statusList = statusList;
	}

	public UserChangePwdForm getUserChangePwdForm() {
		return userChangePwdForm;
	}

	public void setUserChangePwdForm(UserChangePwdForm userChangePwdForm) {
		this.userChangePwdForm = userChangePwdForm;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getCancelAction() {
		return cancelAction;
	}

	public void setCancelAction(String cancelAction) {
		this.cancelAction = cancelAction;
	}
 
}
