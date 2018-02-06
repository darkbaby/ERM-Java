package com.esynergy.common.web.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.esynergy.erm.common.util.UIUtil;
import com.esynergy.erm.model.ob.User;
import com.esynergy.erm.web.action.IPageContains;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public abstract class ActionCommon extends ActionSupport implements IPageContains ,ServletRequestAware {
	protected int rowindex;
	protected Map<String,Object> errors  ;
	protected Map<String,Object>  actionMsg  ;
	public ActionCommon(){
		 errors = new HashMap<String, Object>();
		 actionMsg = new HashMap<String, Object>();
	}
	public int getRowindex() {
		return rowindex;
	}
	public void setRowindex(int rowindex) {
		this.rowindex = rowindex;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map getErrors() {
		return errors;
	}
	public void setErrors(Map<String, Object> errors) {
		this.errors = errors;
	}
	
	public void addErrors(String err,boolean value ) {
		this.errors.put(err, value);
	}
	public void addErrors(String err,String msgKey ) {
		this.errors.put(err, getText(msgKey));
	}
	public void addErrors(String err,String msgKey,String[] arg) {
		this.errors.put(err, getText(msgKey,arg));
	}
	
	public void addActionMsg(String msg,String msgKey ) {
		this.actionMsg.put(msg, (String)getText(msgKey));
	}
	public void addActionMsg(String msg,String msgKey,String[] arg) {
		this.actionMsg.put(msg, getText(msgKey,arg));
	}
	public Map<String, Object> getActionMsg() {
		return actionMsg;
	}
	public void setActionMsg(Map<String, Object> actionMsg) {
		this.actionMsg = actionMsg;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
	    Locale locale = new Locale("en", "US");
	    ActionContext.getContext().setLocale(locale);
	    HttpSession session = null;
	    if(request.getSession(false) == null ){
	    	forward(request,"sessionTimeOut.action");
	    }else{
	    	session = request.getSession(false);
	    	if(session.getAttribute(SESSION_USER)==null){
	    		forward(request,"sessionTimeOut.action");
	    	}else{
	    		User user = (User)session.getAttribute(SESSION_USER);
	    		if(UIUtil.isEmptyOrNull(user.getLogOnId()) || UIUtil.isEmptyOrNull(user.getPwd())){
	    			forwardToLogon(request);
	    			//forward(request,"sessionTimeOut.action");
	    		}
	    	}
	    }
	    request.setAttribute(MAIN_MENU_ATTR,"");
		request.setAttribute(SUB_MENU_ATTR, "");
		request.setAttribute(SUB_MENU1_ATTR, "");
	}
	public void forwardToLogon(HttpServletRequest request){
		 HttpServletResponse response = ServletActionContext.getResponse();
		    try {
				request.getRequestDispatcher("/prepareLogon.action").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	public void forward(HttpServletRequest request,String actionName){
		 HttpServletResponse response = ServletActionContext.getResponse();
		    try {
				request.getRequestDispatcher(actionName).forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	 
}
