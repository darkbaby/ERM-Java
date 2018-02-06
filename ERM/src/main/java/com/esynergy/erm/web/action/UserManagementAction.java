package com.esynergy.erm.web.action;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.esynergy.erm.model.IUser;
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
import com.esynergy.erm.common.util.EmailUtility;
import com.esynergy.erm.common.util.UIUtil;
import com.esynergy.erm.common.util.ValidatorUtil;

@SuppressWarnings({ "serial", "rawtypes" })
@Controller("userManageAction")
public class UserManagementAction extends ActionCommon implements
		  ServletRequestAware, ModelDriven {

	private static final Logger logger = Logger.getLogger(UserManagementAction.class);
	private UserLogonForm userLogonForm;
	private List<Country> countryList = new ArrayList<Country>();
	private List<AuthorizeGroup> authorizeGroupList = new ArrayList<AuthorizeGroup>();
	private List<IUser> userList = new ArrayList<IUser>();
	private List<CodeValue> statusList = new ArrayList<CodeValue>();
	private UserLogonSearchForm userLogonSearchForm = new UserLogonSearchForm();
	private UserLogonResetPwdForm userLogonResetPwdForm = new UserLogonResetPwdForm();
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
		    IUser userLogOnNow = (IUser) session.getAttribute(SESSION_USER);
	    	if(!userLogOnNow.getAuthorizeGroup().getGroupName().equals(AUTHO_GROUP_ADMIN)){
	    		forward(request,"permissionMissing.action");   
	    	} 
	     
		request.setAttribute(MAIN_MENU_ATTR, MENU_USER_MANAGEMENT);
		
		if(session.getAttribute(SESSION_COUNTRY_LIST) == null) {
			countryList = countryService.listAll();
			session.setAttribute(SESSION_COUNTRY_LIST, countryList);
		} else {
			countryList = (List<Country>) session.getAttribute(SESSION_COUNTRY_LIST);
		}
		if(session.getAttribute(SESSION_AUTHOGROUP_LIST)==null){
			authorizeGroupList = authorizeGroupService.listAll();
			session.setAttribute(SESSION_AUTHOGROUP_LIST, authorizeGroupList);
		}else{
			authorizeGroupList =  (List<AuthorizeGroup>)session.getAttribute(SESSION_AUTHOGROUP_LIST);
		}
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

	public String prepareMangement() {
		 
		if(UIUtil.isEmptyOrNull((String)getReq().getAttribute("updated"))){
			super.errors.clear();
		}
			userLogonSearchForm = new UserLogonSearchForm();
			userList = userService.list();
		 
		super.addActionMsg("actionTitle", "header.mangement.user");
		return SUCCESS;
	}

	public String prepareEdit() {
		super.errors = new HashMap<String, Object>();
		super.actionMsg = new HashMap<String, Object>();

		super.addActionMsg("actionTitle", "header.edit.user");
		long idLong = Long
				.parseLong(UIUtil.prepareConvertStringToNumber(parm));
		user = userService.getById(idLong);
		userLogonForm = new UserLogonForm();
		populateObToForm(user,userLogonForm);
		super.errors = new HashMap<String, Object>();
		return SUCCESS;
	}
	
	public String prepareView(){
		super.errors = new HashMap<String, Object>();
		super.actionMsg = new HashMap<String, Object>();
		super.addActionMsg("actionTitle", "header.view.user");
		long idLong = Long
				.parseLong(UIUtil.prepareConvertStringToNumber(parm));
		user = userService.getById(idLong);
		return SUCCESS;
	}

	public String prepareCreate() {
		super.errors = new HashMap<String, Object>();
		super.actionMsg = new HashMap<String, Object>();
		super.addActionMsg("actionTitle", "header.create.user");
		userLogonForm = new UserLogonForm(); 
		super.errors = new HashMap<String, Object>();
		return SUCCESS;
	}

	public String save() throws IOException {
		super.errors = new HashMap<String, Object>();
		 
		if (!validateForm()) { 
			return INPUT;
		} else {
			User u = new User();
			populateFormToOb(userLogonForm, u);
			HttpSession session = getReq().getSession(false);
			IUser userAdmin = (IUser)session.getAttribute(SESSION_USER);
			u.setLastUpdateUser(userAdmin.getLogOnId());
			userService.save(u);
			getReq().setAttribute("updated","updated");
		 	super.addActionMsg("saveSuccess",getText("msg.action.save.success"));
		 	errors.put("isSuccess", true);
			return SUCCESS; 
		}
	}
	public String remove() throws IOException {
		super.errors = new HashMap<String, Object>();
		  
			User u = (User)userService.getById(userLogonForm.getId());
			u.setRecordStatus(RECORD_STS_DELETE);
			HttpSession session = getReq().getSession(false);
			IUser userAdmin = (IUser)session.getAttribute(SESSION_USER);
			u.setLastUpdateUser(userAdmin.getLogOnId());
			userService.save(u);
			getReq().setAttribute("updated","updated");
		 	super.addActionMsg("saveSuccess",getText("msg.action.remove.success"));
		 	errors.put("isSuccess", true);
			return SUCCESS; 
	}
	public String search() throws ParseException {
		super.errors = new HashMap<String, Object>();
		super.actionMsg = new HashMap<String, Object>();
		super.addActionMsg("actionTitle", "header.mangement.user");
		
		Map<String,Object> searchParm = new HashMap<String,Object>();
		searchParm.put(ATTR_USER_LOGON_ID, userLogonSearchForm.getUserLogonId());
		searchParm.put(ATTR_USER_FIRST_NAME, userLogonSearchForm.getFirstName());
		searchParm.put(ATTR_USER_LAST_NAME, userLogonSearchForm.getLastName());
		searchParm.put(ATTR_USER_EMAIL_ADDR, userLogonSearchForm.getEmailAddr());
		searchParm.put(ATTR_USER_COUNTRY_ID,UIUtil.isEmptyOrNull(userLogonSearchForm.getCountryId())?0:Long.parseLong(userLogonSearchForm.getCountryId()));
		searchParm.put(ATTR_USER_GROUP_ID, UIUtil.isEmptyOrNull(userLogonSearchForm.getGroupId())?0:Long.parseLong(userLogonSearchForm.getGroupId()));
		userList = userService.search(searchParm);
		return SUCCESS;

	}
	public String prepareResetPwd() {
		super.errors = new HashMap<String, Object>();
		super.actionMsg = new HashMap<String, Object>();

		super.addActionMsg("actionTitle", "header.reset.pwd.user");
		long idLong = Long
				.parseLong(UIUtil.prepareConvertStringToNumber(parm));
		user = userService.getById(idLong);
		userLogonResetPwdForm = new UserLogonResetPwdForm();
		populateObToForm(user,userLogonResetPwdForm.getUser());
		HttpSession session = getReq().getSession(false);
		IUser userAdmin = (IUser)session.getAttribute(SESSION_USER);
		userLogonResetPwdForm.setAdminLogOnId(userAdmin.getLogOnId());
		super.errors = new HashMap<String, Object>();
		return SUCCESS;
	}
	public String createNewPwd() {
		super.errors = new HashMap<String, Object>();
		super.actionMsg = new HashMap<String, Object>();

		super.addActionMsg("actionTitle", "header.reset.pwd.user");
		
		HttpSession session = getReq().getSession(false);
		IUser userAdmin = (IUser)session.getAttribute(SESSION_USER);
	    if(userService.checkPwd(userLogonResetPwdForm.getAdminPwd(), userAdmin)){
	    	userLogonResetPwdForm.setNewPasswordOfUser(UIUtil.getStrRandom(8)); 
	    }else{
	    	userLogonResetPwdForm.setNewPasswordOfUser(null);
	    	addErrors("pwdError", "error.invalid");
	    }
		return SUCCESS;
	}
	public String updatePwd() throws AddressException, MessagingException{
		super.errors = new HashMap<String, Object>();
		super.actionMsg = new HashMap<String, Object>();
 
		user = userService.getById(userLogonResetPwdForm.getUser().getId());
		user.setPwd(userLogonResetPwdForm.getNewPasswordOfUser());
		User u = new User();
		u = (User) user;
		u.setLastUpdateUser(userLogonResetPwdForm.getAdminLogOnId());
		userService.savePwd(u);
		sendEmailInformUserResetPwd();
		getReq().setAttribute("updated","updated");
		String[] msgParm = {u.getLogOnId()};
	 	super.addActionMsg("saveSuccess",getText("msg.action.reset.pwd.success"),msgParm);
	 	errors.put("isSuccess", true);
		Map<String,Object> searchParm = new HashMap<String,Object>();
		searchParm.put(ATTR_USER_LOGON_ID, userLogonSearchForm.getUserLogonId());
		searchParm.put(ATTR_USER_FIRST_NAME, userLogonSearchForm.getFirstName());
		searchParm.put(ATTR_USER_LAST_NAME, userLogonSearchForm.getLastName());
		searchParm.put(ATTR_USER_EMAIL_ADDR, userLogonSearchForm.getEmailAddr());
		searchParm.put(ATTR_USER_COUNTRY_ID,UIUtil.isEmptyOrNull(userLogonSearchForm.getCountryId())?0:Long.parseLong(userLogonSearchForm.getCountryId()));
		searchParm.put(ATTR_USER_GROUP_ID, UIUtil.isEmptyOrNull(userLogonSearchForm.getGroupId())?0:Long.parseLong(userLogonSearchForm.getGroupId()));
		userList = userService.search(searchParm);
		super.addActionMsg("actionTitle", "header.mangement.user"); 
		return SUCCESS;
	}
    
	private void sendEmailInformUserResetPwd() throws AddressException, MessagingException{
		final String HOST = "smtp.gmail.com";
		final String PORT = "587";
		final String USER_NAME = "rcl.erm.system@gmail.com"; 
		final String PASSWORD = "erm12345678";
		final String SUBJECT = "[ERM] Reset your password by Administrator";
		StringBuilder message = new StringBuilder();
		message.append("<strong> Dear : </strong>"+user.getFirstName()+" "+user.getLastName());
		message.append("<br/> <strong>  Subject : </strong>"+SUBJECT);
		message.append("<br/> <br/> Your password has been reset successfully.");
		message.append("<br/> User Logon ID : "+user.getLogOnId());
		message.append("<br/> New Password : "+userLogonResetPwdForm.getNewPasswordOfUser());
		message.append("<br/> <br/> Please visit the site : 192.168.10.63:8080/ERM/logon for sign-in and change your password.");
		EmailUtility.sendEmail(HOST, PORT, USER_NAME, PASSWORD, user.getEmailAddress(),
				SUBJECT, message.toString(), null);
	}
 
	public boolean validateForm() {
		errors.put("isError", false);
		errors = new HashMap<String, Object>();
		if(userLogonForm.getId()==0){
			if (ValidatorUtil.checkString(userLogonForm.getUserLogonId(),
					true,MIN_USER_ID,MAX_USER_ID)) {
				String[] errMsgParm = {String.valueOf(MIN_USER_ID),String.valueOf(MAX_USER_ID)};
				addErrors("logOnIdError", "error.require.character",errMsgParm);
			}
			if (ValidatorUtil.checkString(userLogonForm.getUserLogonId(),
					true,MIN_USER_ID,MAX_USER_ID)) {
				String[] errMsgParm = {String.valueOf(MIN_USER_ID),String.valueOf(MAX_USER_ID)};
				addErrors("logOnIdError", "error.require.character",errMsgParm);
			}else{
				if(ValidatorUtil.checkUserId((userLogonForm.getUserLogonId()))){
					addErrors("logOnIdInvalidError", "error.invalid");
				}else{
					if(userService.getDupllicate(userLogonForm.getId(), userLogonForm.getUserLogonId())){
						addErrors("logOnIdError", "error.dupplicate");
					}
				}
			}
			if(ValidatorUtil.checkString(userLogonForm.getPwd(), true,MIN_PWD,MAX_PWD)){
				String[] errMsgParm = {String.valueOf(MIN_PWD),String.valueOf(MAX_PWD)};
				addErrors("pwdError", "error.require.character", errMsgParm);
			}else{
				if(ValidatorUtil.findSpace(userLogonForm.getPwd())){
					addErrors("pwdFindSpaceError", "error.find.space");
				}
			}
			if(UIUtil.isEmptyOrNull(userLogonForm.getPwdConfirm())){
				addErrors("pwdConfirmError", "error.require");
			}else{
				if(!userLogonForm.getPwd().equals(userLogonForm.getPwdConfirm())){
					addErrors("pwdConfirmNotMatError", "error.pwd.confirm");
				}
			}
		}
		if(ValidatorUtil.checkString(userLogonForm.getFirstName(), true,MIN_PERSON_NAME,MAX_PERSON_NAME)){
			String[] errMsgParm = {String.valueOf(MIN_PERSON_NAME),String.valueOf(MAX_PERSON_NAME)};
			addErrors("firstNameError", "error.require.character", errMsgParm);
		} 
		if(ValidatorUtil.checkString(userLogonForm.getLastName(), true,MIN_PERSON_NAME,MAX_PERSON_NAME)){
			String[] errMsgParm = {String.valueOf(MIN_PERSON_NAME),String.valueOf(MAX_PERSON_NAME)};
			addErrors("lastNameError", "error.require.character", errMsgParm);
		} 
		if(UIUtil.isEmptyOrNull(userLogonForm.getEmailAddr())){
			addErrors("emailRequireError", "error.require");
		}else{
			if(ValidatorUtil.checkEmail(userLogonForm.getEmailAddr())){
				addErrors("emailPatternError", "error.email.pattern");
			}
		}
		if(UIUtil.isEmptyOrNull(userLogonForm.getCountryId())){
			addErrors("countryRequireError", "error.require");
		}
		if(UIUtil.isEmptyOrNull(userLogonForm.getGroupId())){
			addErrors("groupRequireError", "error.require");
		} 
		if (errors.isEmpty()) {
			return true;
		}
		super.addActionMsg("error",getText("error.action.message"));
		errors.put("isError", true);
		return false;
	}
	public void populateObToForm(IUser ob,UserLogonForm form){
		form.setId(ob.getId());
		form.setUserLogonId(ob.getLogOnId());
		form.setFirstName(ob.getFirstName());
		form.setLastName(ob.getLastName());
		form.setPwd(ob.getPwd());
		form.setEmailAddr(ob.getEmailAddress());
		form.setCountryId(String.valueOf(ob.getCountry().getId()));
		form.setGroupId(String.valueOf(ob.getAuthorizeGroup().getId()));
		form.setCreatedDate(ob.getCreatedDate());
		form.setCreatedUser(ob.getCreatedUser());
		form.setLastUpdateDate(ob.getLastUpdateDate());
		form.setLastUpdateUser(ob.getLastUpdateUser());
		form.setStatus(ob.getRecordStatus());
	}

	 
	public void populateFormToOb(UserLogonForm form, IUser ob){
		ob.setId(form.getId());
		ob.setLogOnId(form.getUserLogonId());
		ob.setFirstName(form.getFirstName());
		ob.setLastName(form.getLastName());
		ob.setPwd(form.getPwd());
		ob.setEmailAddress(form.getEmailAddr());
		ob.setCountry(countryService.getById(Long.parseLong(UIUtil.prepareConvertStringToNumber(form.getCountryId()))));
		ob.setAuthorizeGroup(authorizeGroupService.getById(Long.parseLong(UIUtil.prepareConvertStringToNumber(form.getGroupId()))));
		ob.setCreatedDate(form.getCreatedDate());
		ob.setCreatedUser(form.getCreatedUser());
		ob.setLastUpdateDate(form.getLastUpdateDate());
		ob.setLastUpdateUser(form.getLastUpdateUser());
		ob.setRecordStatus(form.getStatus()); 
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return userLogonForm;
	}
	 

	public String getParm() {
		return parm;
	}

	public void setParm(String parm) {
		this.parm = parm;
	}
	public List<Country> getCountryList() {
		return countryList;
	}

	public List<AuthorizeGroup> getAuthorizeGroupList() {
		return authorizeGroupList;
	}

 

	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}

	public void setAuthorizeGroupList(List<AuthorizeGroup> authorizeGroupList) {
		this.authorizeGroupList = authorizeGroupList;
	}

	public IUser getUser() {
		return user;
	}

	public void setUser(IUser user) {
		this.user = user;
	}

	public UserLogonForm getUserLogonForm() {
		return userLogonForm;
	}

	public void setUserLogonForm(UserLogonForm userLogonForm) {
		this.userLogonForm = userLogonForm;
	}

	public List<IUser> getUserList() {
		return userList;
	}

	public void setUserList(List<IUser> userList) {
		this.userList = userList;
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

	public UserLogonSearchForm getUserLogonSearchForm() {
		return userLogonSearchForm;
	}

	public void setUserLogonSearchForm(UserLogonSearchForm userLogonSearchForm) {
		this.userLogonSearchForm = userLogonSearchForm;
	}

	public UserLogonResetPwdForm getUserLogonResetPwdForm() {
		return userLogonResetPwdForm;
	}

	public void setUserLogonResetPwdForm(UserLogonResetPwdForm userLogonResetPwdForm) {
		this.userLogonResetPwdForm = userLogonResetPwdForm;
	}
}
