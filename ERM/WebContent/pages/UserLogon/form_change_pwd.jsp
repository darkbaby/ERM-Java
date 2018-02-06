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
	<s:form name="userChangePwdForm" method="POST"  id="userChangePwdForm" theme="simple" class="form-inline" role="form">
          	<div class="row mt">
          		<div class="col-lg-12">
          			<div class="form-panel">
                  	  <h4 class="mb"><i class="fa fa-cog"></i><span class="subject-text"><s:text name="header.user.info"/></span></h4>
   			 		  <s:if test="errors['isSuccess']">
	                  	       <div class="alert alert-success alert-dismissable fade in" id="success-alert" >
								    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
								    <h5><span class="glyphicon glyphicon-ok"></span>
								      <s:property value="actionMsg['saveSuccess']"/> </h5>
								      
							   </div> 
                 	   </s:if>
   			 		 <div class="row mt">
                  	  	<div class="col-sm-12">
		   			 		  <table class="table-form" >
		   			 		  	<tr>
		   			 		  		<td class="label-view"><s:text name="user.logon.id"/></td>
		   			 		  		<td><s:property value="userChangePwdForm.userLogonId"/></td> 
		   			 		  		<td class="label-view"><s:text name="label.extract.status"/></td>
		   			 		  		<td colspan="4"><s:radio 
										cssClass="radioMarginRight"
									    name="userLogonForm.status"
									    id="userLogonForm.status"
										list="statusList"
										listKey="value"
										listValue="descriptionShort"
										value="%{userChangePwdForm.status}"
										disabled="true"/></td>
		   			 		  		 			 		  		
		   			 		  	</tr>
		   			 		  	<tr>
		   			 		  		<td class="label-view"><s:text name="user.first.name"/></td>
		   			 		  		<td><s:property value="userChangePwdForm.firstName"/></td>
		   			 		  		<td class="label-view"><s:text name="user.last.name"/></td>
		   			 		  		<td><s:property value="userChangePwdForm.lastName"/></td>
		   			 		  		<td class="label-view"><s:text name="user.email"/></td>
		   			 		  		<td><s:property value="userChangePwdForm.emailAddr"/></td>
		   			 		  	</tr>
		   			 		  	<tr>
		   			 		  		<td class="label-view"><s:text name="user.country"/></td>
		   			 		  		<td><s:property value="user.country.countryName"/></td>
		   			 		  		<td class="label-view"><s:text name="user.authorize.group"/></td>
		   			 		  		<td colspan="4"><s:property value="user.authorizeGroup.groupName"/></td>
		   			 		  	</tr>
		   			 		   
		   			 		  	<tr>
		   			 		  		<td class="label-view"><s:text name="user.curent.pwd"/></td>
		   			 		  		<td colspan="6">
		   			 		  			<s:password name="userChangePwdForm.currenctPwd" id="currenctPwd"/>
		   			 		  			<div class="has-error"><s:property value="errors['currenPwdError']"/></div>
		   			 		  		</td>
		   			 		  		 
		   			 		  	</tr>
		   			 		  	<tr>
		   			 		  		<td class="label-view"><s:text name="user.new.pwd"/></td>
		   			 		  		<td><s:password name="userChangePwdForm.pwd" id="pwd"/>
		   			 		  			<div class="has-error"><s:property value="errors['pwdError']"/></div>
		   			 		  			<div class="has-error"><s:property value="errors['pwdFindSpaceError']"/></div>
		   			 		  		</td>
		   			 		  		<td class="label-view"><s:text name="user.confirm.new.pwd"/></td>
		   			 		  		<td colspan="4"><s:password name="userChangePwdForm.pwdConfirm" id="pwdConfirm"/>
		   			 		  			<div class="has-error"><s:property value="errors['pwdConfirmError']"/></div>
		   			 		  			<div class="has-error"><s:property value="errors['pwdConfirmNotMatError']"/></div>
		   			 		  		</td> 
		   			 		  	</tr>
		   			 		  	 
		   			 		  </table>
		   			 	</div>
		   			 </div>
                  	  
					</div><!-- /form-panel -->		
          		</div><!-- /col-lg-12 -->
          	</div><!-- /row --> 	
          	
			 
		   <div>
          					<table style="width:100%" align="center">
								<tr>
									<td align="center">
											 
												<button type="button" id="cancelBtn" style="width:150px" class="btn btn-default btn-sm" title="<s:text name='btn.cancel'/>">
														<span class="glyphicon glyphicon-chevron-left"></span>
														<span><s:text name='btn.cancel'/></span>
												</button>
											 
										 
											<button style="width:150px" type="button" class="btn btn-primary btn-sm" id="saveBtn"  >
												<span class="glyphicon glyphicon-floppy-disk"></span>
												<span><s:text name='btn.save'/></span>
											</button>
										
									</td>
								</tr>
							</table>	
					</div>
					<br/>
					<br/>	
	</s:form>
 
 <script type ="text/javascript">
	$('#saveBtn').click(function(){
		$('#userChangePwdForm').attr('action','changePwd').submit();
	});
	$('#cancelBtn').click(function(){
		window.history.back();
	});
	 
 </script>
    
 
</body>
</html>