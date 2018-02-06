package com.esynergy.erm.web.action;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.esynergy.erm.model.IUser;
import com.esynergy.erm.model.form.UserChangePwdForm;
import com.esynergy.erm.model.form.UserLogonForm;
import com.esynergy.erm.model.form.UserLogonResetPwdForm;
import com.esynergy.erm.model.form.UserLogonSearchForm;
import com.esynergy.erm.model.ob.AuthorizeGroup;
import com.esynergy.erm.model.ob.CodeValue;
import com.esynergy.erm.model.ob.Country;
import com.esynergy.erm.model.ob.ExchangeRateAutoHISTLog;
import com.esynergy.erm.model.ob.User;
import com.esynergy.erm.service.AuthorizeGroupService;
import com.esynergy.erm.service.CodeValueService;
import com.esynergy.erm.service.CountryService;
import com.esynergy.erm.service.ExchangeRateAutoHISTLogService;
import com.esynergy.erm.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.esynergy.common.web.action.ActionCommon;
import com.esynergy.erm.common.util.UIUtil;
import com.esynergy.erm.common.util.ValidatorUtil;

@SuppressWarnings({ "serial", "rawtypes" })
@Controller("userChangePwdAction")
public class UserChangePwdAction extends ActionCommon implements
		  ServletRequestAware, ModelDriven {

	private static final Logger logger = Logger.getLogger(UserChangePwdAction.class);
	private List<CodeValue> statusList = new ArrayList<CodeValue>();
	private UserChangePwdForm userChangePwdForm = new UserChangePwdForm();
	private IUser  user;
	private String parm;
	
	@Autowired private CodeValueService codeValueService;
	@Autowired private CountryService countryService;
	@Autowired private AuthorizeGroupService authorizeGroupService;
	@Autowired private UserService userService;
	
	@SuppressWarnings("unchecked")
	public void setServletRequest(HttpServletRequest request) {
		super.setServletRequest(request);
		HttpSession session = request.getSession(false) == null ? request
				.getSession(true) : request.getSession(false);
		//request.setAttribute(MAIN_MENU_ATTR, MENU_USER_MANAGEMENT);
		
		if(session.getAttribute(SESSION_STATUS_LIST) == null) {
			statusList = codeValueService.listAllStatus();
			session.setAttribute(SESSION_STATUS_LIST, statusList);
		} else {
			statusList = (List<CodeValue>) session.getAttribute(SESSION_STATUS_LIST);
		}
	}

 	public HttpServletRequest getReq() {
		return (HttpServletRequest) ActionContext.getContext().get(
				ServletActionContext.HTTP_REQUEST);
	} 

	public String prepareChangePwd() {
		super.errors = new HashMap<String, Object>();
		super.actionMsg = new HashMap<String, Object>();

		super.addActionMsg("actionTitle", "header.user.change.pwd");
		HttpSession session = getReq().getSession(false);
		user = (IUser)session.getAttribute(SESSION_USER);
		userChangePwdForm = new UserChangePwdForm();
		populateObToForm(user,userChangePwdForm);
		 
		super.errors = new HashMap<String, Object>();
		return SUCCESS;
	}
	public String changePwd(){
		super.errors = new HashMap<String, Object>();
		super.actionMsg = new HashMap<String, Object>();
		HttpSession session = getReq().getSession(false);
		user = (IUser)session.getAttribute(SESSION_USER);
		 
			if(validateForm()){
				User u = (User)user;
				u.setPwd(userChangePwdForm.getPwd());
				userService.savePwd(u);
				getReq().setAttribute("updated","updated");
				super.addActionMsg("saveSuccess",getText("msg.action.change.pwd.success"));
			 	errors.put("isSuccess", true);
			 	user = (IUser)u;
			 	session.setAttribute(SESSION_USER, user);
			 	
			}
		 
		super.addActionMsg("actionTitle", "header.user.change.pwd"); 
		return SUCCESS;
	}

 
	public boolean validateForm() {
		errors.put("isError", false);
		errors = new HashMap<String, Object>();
			if(!userService.checkPwd(userChangePwdForm.getCurrenctPwd(), user)){
				addErrors("currenPwdError", "error.invalid");
			}
			if(ValidatorUtil.checkString(userChangePwdForm.getPwd(), true,MIN_PWD,MAX_PWD)){
				String[] errMsgParm = {String.valueOf(MIN_PWD),String.valueOf(MAX_PWD)};
				addErrors("pwdError", "error.require.character", errMsgParm);
			}else{
				if(ValidatorUtil.findSpace(userChangePwdForm.getPwd())){
					addErrors("pwdFindSpaceError", "error.find.space");
				}
			}
			if(UIUtil.isEmptyOrNull(userChangePwdForm.getPwdConfirm())){
				addErrors("pwdConfirmError", "error.require");
			}else{
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
	public Object getModel() {
		// TODO Auto-generated method stub
		return userChangePwdForm;
	}
	 

	public String getParm() {
		return parm;
	}

	public void setParm(String parm) {
		this.parm = parm;
	} 
	
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
 
}
