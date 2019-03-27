package com.esynergy.spring.common.tag;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.esynergy.erm.common.util.UIUtil;
import com.esynergy.erm.common.util.ValidatorUtil;
import com.esynergy.erm.model.IUser;
import com.esynergy.erm.model.ob.AuthorizeGroup;
import com.esynergy.erm.model.ob.AuthorizePermission;
import com.esynergy.erm.web.action.IPageContains;

@SuppressWarnings("serial")
public class PermissionTag extends TagSupport{
	private String function;
	private String user;
	
	
	
    @Override
    public int doStartTag() throws JspException {
    	ServletRequest req =   pageContext.getRequest();
    	HttpSession    session = pageContext.getSession();
    	IUser user = (IUser)session.getAttribute(IPageContains.SESSION_USER);
    	
    	if(ValidatorUtil.checkPermissionAdmin(user)) {
    		return EVAL_BODY_INCLUDE;
    	}
    	else {
    		if(UIUtil.isEmptyOrNull(this.user)){
        		if(ValidatorUtil.checkPermission(user, this.function)){
            		return EVAL_BODY_INCLUDE;
            	}
        	}else{
        		if(ValidatorUtil.checkPermission(user, this.function,this.user)){
            		return EVAL_BODY_INCLUDE;
            	}
        	}
            return SKIP_BODY;
    	}
    	
    }

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
