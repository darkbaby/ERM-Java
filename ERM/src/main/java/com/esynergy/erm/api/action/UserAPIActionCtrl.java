package com.esynergy.erm.api.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.esynergy.erm.common.util.EmailUtility;
import com.esynergy.erm.common.util.UIUtil;
import com.esynergy.erm.common.util.ValidatorUtil;
import com.esynergy.erm.model.IUser;
import com.esynergy.erm.model.ob.User;
import com.esynergy.erm.model.ob.json.JSONData;
import com.esynergy.erm.service.UserService;
import com.esynergy.erm.web.action.IPageContains;
import com.google.gson.JsonParser;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Controller("userAPIActionCtrl")
@Scope("prototype")
public class UserAPIActionCtrl extends ActionSupport {
	//private static final Logger logger = Logger.getLogger(UserAPIActionCtrl.class);
	private  IUser  user;
	private  JSONObject  result;
	private String parm;
	private JSONData data;
	@Autowired UserService userService;
	
 	@SuppressWarnings("unchecked")
	public String getByEmail() throws ParseException, AddressException, MessagingException {
 		String key =(String) data.getHeader().getKey();
 		if(!IPageContains.USER_API_KEY.equals(key)){
 			result = new JSONObject();
 			result.put("result", "error");
 			result.put("desc", "API key invalid.");
 			return SUCCESS;
 		}else{
 			JSONObject json = convertStringToJSON(data.getDetail());
 			String emailAddr = (String) json.get("emailAddress");
 			if(ValidatorUtil.checkEmail(emailAddr)){
 				result = new JSONObject();
	 			result.put("result", "error");
	 			result.put("desc", getText("error.email.pattern"));
 				return SUCCESS;
 			}
	 		Map<String,Object> parmMap = new HashMap<String,Object>();
	 		parmMap.put(IPageContains.ATTR_USER_EMAIL_ADDR, emailAddr);
	 		user = userService.getByEmail(parmMap);
	 		if(user!=null){
	 			//user.get
	 			String newPwd = UIUtil.getStrRandom(8);
	 			user.setPwd(newPwd);
	 			User u = new User();
	 			u = (User) user;
	 			u.setLastUpdateUser("SYSRESET");
	 			userService.savePwd(u);
	 			sendEmailForgetEmail(newPwd);
 	 			result = new JSONObject();
	 			result.put("result", "success");
	 			result.put("desc", "Success Please check your email.");
	 		}else{
	 			result = new JSONObject();
	 			result.put("result", "error");
	 			result.put("desc", "email address not found.");
	 		}
 		}
		return SUCCESS;
	}
 	private void sendEmailForgetEmail(String newPwd) throws AddressException, MessagingException{
		final String HOST = "smtp.gmail.com";
		final String PORT = "587";
		final String USER_NAME = "rcl.erm.system@gmail.com"; 
		final String PASSWORD = "erm12345678";
		final String SUBJECT = "[ERM] Reset your password";
		StringBuilder message = new StringBuilder();
		message.append("<strong> Dear : </strong>"+user.getFirstName()+" "+user.getLastName());
		message.append("<br/> <strong>  Subject : </strong>"+SUBJECT);
		message.append("<br/> <br/> Your password has been reset successfully.");
		message.append("<br/> User Logon ID : "+user.getLogOnId());
		message.append("<br/> New Password : "+newPwd);
		message.append("<br/> <br/> Please visit the site : 192.168.10.63:8080/ERM/logon for sign-in and change your password.");
		EmailUtility.sendEmail(HOST, PORT, USER_NAME, PASSWORD, user.getEmailAddress(),
				SUBJECT, message.toString(), null);
	}
 	private JSONObject convertStringToJSON(String str) throws ParseException{
 		return (JSONObject) new JSONParser().parse(str); 
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

	public JSONData getData() {
		return data;
	}

	public void setData(JSONData data) {
		this.data = data;
	}
	public JSONObject getResult() {
		return result;
	}
	public void setResult(JSONObject result) {
		this.result = result;
	}

 

}
