package com.esynergy.erm.web.action;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.esynergy.erm.model.IUser;
import com.esynergy.erm.model.ob.AuthorizeFunction;
import com.esynergy.erm.model.ob.AuthorizePermission;
import com.esynergy.erm.model.ob.ExchangeRateManual;
import com.esynergy.erm.service.ExchangeRateManualService;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ERMInterceptor extends AbstractInterceptor implements IPageContains {

	@Autowired
	private MandatoryAsset mandatoryAsset;
	
	@Autowired 
	private ExchangeRateManualService exchangeRateManualService;
		
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		Map<String,AuthorizeFunction> permissionFunctionNameMap;
		permissionFunctionNameMap = mandatoryAsset.getAuthorizeFunctionMap();
		
		Map session = invocation.getInvocationContext().getSession();
		
		String actionName = invocation.getInvocationContext().getName().toLowerCase();
		if(actionName.contains(".")) {
			actionName = actionName.split(".")[0].toLowerCase();
		}
		
		if(session.get(SESSION_USER) == null) {
			if (!(invocation.getAction() instanceof UserLogonAction)) {
				return "session_timeout";
			}
		}
		else {
			if(permissionFunctionNameMap.get(actionName) != null) {
				IUser iUser = (IUser) session.get(SESSION_USER);
				String logOnID = iUser.getLogOnId();
				Iterator<AuthorizePermission> permItr = iUser.getAuthorizeGroup().getPermissionList().iterator();
				boolean isValidPerm = false;
				boolean isAdmin = false;
				while(permItr.hasNext()) {
					AuthorizePermission perm = permItr.next();
					String permissionName = perm.getAuthorizeFunction().getFunctionName().toLowerCase();
					if(actionName.contains(permissionName)) {
						isValidPerm = true;
					}
					if(permissionName.equalsIgnoreCase("admin")) {
						isAdmin = true;
						break;
					}
				}
				
				if(isAdmin) {
					return invokeInvocation(invocation);
				}
				else if(isValidPerm) {
					if(actionName.equalsIgnoreCase("PrepareEditExchangeRateByManualAction")) {
						Object parameterObj = invocation.getInvocationContext().getParameters().get("parmID");
						if(parameterObj != null) {
							boolean isNumeric = StringUtils.isNumeric(parameterObj.toString());
							if (isNumeric) {
								Long parmID = Long.parseLong(parameterObj.toString());
								ExchangeRateManual tempExchangeRageManual = exchangeRateManualService.getById(parmID);
								if(tempExchangeRageManual.getCreatedUser().equalsIgnoreCase(logOnID)) {
									return invokeInvocation(invocation);
								}
								else {
									return "permission_missing";
								}
							}
						}
					}
				}
				else {
					return "permission_missing";
				}
			}
		}
		
	    return invokeInvocation(invocation);

	}
	
	private String invokeInvocation(ActionInvocation invocation) throws Exception {
		
		if (!(invocation.getAction() instanceof UserChangePwdAction)) {
			Map session = invocation.getInvocationContext().getSession();
			session.put("lastAction", invocation.getInvocationContext().getName());
		}
		
		return invocation.invoke();
	}

}
