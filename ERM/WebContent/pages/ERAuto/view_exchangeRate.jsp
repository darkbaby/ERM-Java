<%@page import="com.esynergy.erm.web.action.IPageContains"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="modong" uri="/WEB-INF/ModongTag.tld" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     
     <link href="<s:url value='/resources/assets/css/app.css' />" rel="stylesheet"></link>

</head>
 
 <body>
			<h3 class="subject"><i class="fa fa-dashboard"></i><span class="subject-text"><s:property value="menuName"/></span></h3>
			<div class="has-error"><s:property value="errors['downloadFileError']" /></div>
			<div >
		      	<s:include value="view_detail_exchangeRate.jsp"/>
		    </div>
		    <div>
		    	<table style="width:100%" align="center">
									<tr>
										<td align="center">
										
<%-- 												<s:url var="cancleURL" action="prepareAutoRateAdjust"/> --%>
												<s:set var="targetAction" value="%{backAction}" />
												<s:url var="cancleURL" action="%{#targetAction}"/>
												
												<s:a href="%{cancleURL}" >       	 
								               	 	<button style="width:150px" 
													type="button" id="cancelBtn" 
													class="btn btn-default btn-sm" 
													title="<s:text name='btn.cancel'/>">
														<span class="glyphicon glyphicon-chevron-left"></span>
														<span><s:text name='btn.back'/></span>
													</button>	
								               	</s:a>
<%-- 													<button style="width:150px" type="button" id="cancelBtn" class="btn btn-default btn-sm" title="<s:text name='btn.cancel'/>"> --%>
<%-- 														<span class="glyphicon glyphicon-chevron-left"></span> --%>
<%-- 														<span><s:text name='btn.back'/></span> --%>
<!-- 													</button> -->
												 
												<modong:sys-permission function="PrepareEditExchangeRateAuto">
												<s:url var="editURL"   action="prepareEditExchangeRateAuto">
													<s:param name="parmID" value="%{exchangeRateAutoForm.id}" />
													<s:param name="parmBackAction" value="%{#targetAction}" />
												</s:url>        
									            <s:a href="%{editURL}">
									                  <button style="width:150px"  class="btn btn-primary btn-sm" 
									                      	    	title="<s:text  name="msg.edit"/>">
									                      	<i class="glyphicon glyphicon-edit"></i>
									                      	<span><s:text name='btn.edit'/></span>
									                 </button>
									            </s:a>
									            </modong:sys-permission>
										</td>
									</tr>
			   </table>	
		    </div>
		 
		   
</section>	
 
</body>
</html>