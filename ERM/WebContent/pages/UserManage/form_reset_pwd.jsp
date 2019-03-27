<%@page import="java.util.List"%>
<%@page import="com.esynergy.erm.model.ob.Currency"%>
<%@page import="com.esynergy.erm.web.action.IPageContains"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  
</head>
 
 <body>	 
	<!-- ADD EXCHANGE RATE -->
	<s:form name="userLogonResetPwdForm" method="POST"  id="userLogonResetPwdForm" theme="simple" class="form-inline" role="form">
          	<s:hidden name="menuName"/>
          	<s:hidden name="userLogonResetPwdForm.user.id" />
          	<div class="row mt">
          		<div class="col-lg-12">
          			<div class="form-panel">
                  	  <h4 class="mb"><i class="fa fa-cog"></i><span class="subject-text"><s:text name="header.user.info"/></span></h4>
   			 		 
   			 		 <div class="row mt">
                  	  	<div class="col-sm-12">
		   			 		  <table class="table-form" >
		   			 		  	<tr>
		   			 		  		<td class="label-view"><s:text name="user.logon.id"/></td>
		   			 		  		<td>
		   			 		  			<s:property value="userLogonResetPwdForm.user.userLogonId"/>
		   			 		  		</td> 
		   			 		  		<td class="label-view"><s:text name="label.extract.status"/></td>
		   			 		  		<td colspan="4">
		   			 		  			<s:radio 
										cssClass="radioMarginRight"
									    id="userLogonResetPwdForm.user.status"
										list="statusList"
										listKey="value"
										listValue="descriptionShort"
										value="%{userLogonResetPwdForm.user.status}"
										disabled="true"/></td>
		   			 		  	</tr>
		   			 		  	<tr>
		   			 		  		<td class="label-view"><s:text name="user.first.name"/></td>
		   			 		  		<td>
		   			 		  			<s:property value="userLogonResetPwdForm.user.firstName"/>
		   			 		  		</td>
		   			 		  		<td class="label-view"><s:text name="user.last.name"/></td>
		   			 		  		<td>
		   			 		  			<s:property value="userLogonResetPwdForm.user.lastName"/>
		   			 		  		</td>
		   			 		  		<td class="label-view"><s:text name="user.email"/></td>
		   			 		  		<td>
		   			 		  			<s:property value="userLogonResetPwdForm.user.emailAddr"/>
		   			 		  		</td>
		   			 		  	</tr>
		   			 		  	<tr>
		   			 		  		<td class="label-view"><s:text name="user.country"/></td>
		   			 		  		<td>
		   			 		  			<s:property value="user.country.countryName"/>
		   			 		  		</td>
		   			 		  		<td class="label-view">
		   			 		  			<s:text name="user.authorize.group"/>
		   			 		  		</td>
		   			 		  		<td colspan="4">
		   			 		  			<s:property value="user.authorizeGroup.groupName"/>
		   			 		  		</td>
		   			 		  	</tr>
		   			 		  	<s:if test="%{userLogonResetPwdForm.newPasswordOfUser != NULL }">
		   			 		  	<tr>
		   			 		  		<td class="label-view"><s:text name="user.new.pwd"/></td>
		   			 		  		<td colspan="6"><span class="text-success">
		   			 		  			<strong>
		   			 		  				<s:hidden name="userLogonResetPwdForm.newPasswordOfUser"/>
		   			 		  				<s:property  value="userLogonResetPwdForm.newPasswordOfUser"/>
		   			 		  			</strong></span>
		   			 		  		</td>
		   			 		  	</tr>
		   			 		  	</s:if>
		   			 		  	<s:else>
		   			 		  	<tr>
		   			 		  		<td class="label-view"><s:text name="admin.logon.id"/></td>
		   			 		  		<td>
		   			 		  			<s:property  value="userLogonResetPwdForm.adminLogOnId"/>
		   			 		  		</td>
		   			 		  		<td class="label-view"><s:text name="admin.pwd"/></td>
		   			 		  		<td colspan="4"> <s:password name="userLogonResetPwdForm.adminPwd"  cssStyle="width:200px;" maxLength="16"/>
                  	  								 <div class="has-error"><s:property value="errors['pwdError']"/></div> 
		   			 		  						 <s:textfield
		   			 		  						    name="forDisableEnter"
		   			 		  						 	cssStyle="display:none;"
		   			 		  						 />
		   			 		  		</td>
		   			 		  	</tr>
		   			 		  	</s:else>
		   			 		  </table>
		   			 	</div>
		   			 </div>
                  	 <%--  <div class="row mt">
                  	  	<div class="col-sm-3">
                  	  		<div class="form-group">
                  	  			<div><strong><s:text name="user.logon.id"></s:text></strong></div> 
                  	  			<s:textfield name="userLogonForm.userLogonId" 
                  	  						id="userLogonId" 
                  	  						cssClass="form-control" 
                  	  						cssStyle="width:200px;" 
                  	  						maxLength="16" 
                  	  						value="%{userLogonForm.userLogonId}"/>	 
                  	  		</div>
                  	  	</div>  
 
                  	  	</div>--%>
					</div><!-- /form-panel -->		
          		</div><!-- /col-lg-12 -->
          	</div><!-- /row --> 	
          	
			 
		   <div>
          					<table style="width:100%" align="center">
								<tr>
									<td align="center">
											 <s:url var="cancleURL" action="prepareManageUser"/>
											 <s:a href="%{cancleURL}">
												<button type="button" id="cancelBtn" style="width:150px" class="btn btn-default btn-sm" title="<s:text name='btn.cancel'/>">
														<span class="glyphicon glyphicon-chevron-left"></span>
														<span><s:text name='btn.cancel'/></span>
												</button>
											 </s:a>
										 <s:if test="%{userLogonResetPwdForm.newPasswordOfUser == NULL }">
											<button style="width:150px" type="button" class="btn btn-warning btn-sm" id="createNewPwdBtn" title="<s:text name='btn.create.new.pwd'/>">
												<%-- <span class="glyphicon glyphicon-trash"></span> --%>
												<span><s:text name='btn.create.new.pwd'/></span>
											</button>
										 </s:if>  
										<s:else>
											<button style="width:150px" type="button" class="btn btn-primary btn-sm" id="saveBtn"  >
												<span class="glyphicon glyphicon-floppy-disk"></span>
												<span><s:text name='btn.save'/></span>
											</button>
										</s:else> 
									</td>
								</tr>
							</table>	
					</div>
					<br/>
					<br/>	
	</s:form>
 
 <script type ="text/javascript">
	$('#saveBtn').click(function(){
		$('#userLogonResetPwdForm').attr('action','savePwdUser').submit();
	});
	$('#createNewPwdBtn').click(function(){
		$('#userLogonResetPwdForm').attr('action','createNewPwdUser').submit();
	});
// 	$('#cancelBtn').click(function(){
// 		window.history.back();
// 	});
 </script>
    
 
</body>
</html>