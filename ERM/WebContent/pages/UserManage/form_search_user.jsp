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
     <link href="<s:url value='/resources/assets/css/select2.min.css' />" rel="stylesheet" />
     <link href="<s:url value="/resources/assets/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css"/>" rel="stylesheet" />
</head>
 
 <body>	 
	<!-- ADD EXCHANGE RATE -->
	<s:form name="userLogonSearchForm" method="POST"  id="userLogonSearchForm" theme="simple" class="form-inline" role="form">
          	<div class="row mt">
          		<div class="col-lg-12">
          			<div class="form-panel02">
                  	  <h4 class="mb"><i class="fa fa-cog"></i><span class="subject-text"><s:text name="label.search"/></span></h4>
                  	  <div class="row mt">
                  	  	<div class="col-sm-2">
                  	  		<div class="form-group">
                  	  			<div><strong><s:text name="user.logon.id"></s:text></strong></div> 
                  	  			<s:textfield name="userLogonSearchForm.userLogonId" 
                  	  						id="userLogonId" 
                  	  						cssClass="form-control" 
                  	  						cssStyle="width:200px;" 
                  	  						maxLength="16" 
                  	  						value="%{userLogonSearchForm.userLogonId}"/>
                  	  			<div class="has-error"><s:property value="errors['logOnIdError']"/></div>
                  	  			<div class="has-error"><s:property value="errors['logOnIdInvalidError']"/></div> 
                  	  		</div>
                  	  	</div>
                  	  	<div class="col-sm-2">
                  	  		<div class="form-group">
                  	  			<div><strong><s:text name="user.first.name"></s:text></strong></div> 
                  	  			<s:textfield name="userLogonSearchForm.firstName"  
                  	  						id="firstName"
                  	  						value="%{userLogonSearchForm.firstName}"
                  	  						cssClass="form-control"  
                  	  						cssStyle="width:200px;"
                  	  						 maxLength="20"/>
                  	  			<div class="has-error"><s:property value="errors['firstNameError']"/></div>
                  	  		</div>
                  	  	</div>
                  	  	<div class="col-sm-2">
                  	  		<div class="form-group">
                  	  			<div><strong><s:text name="user.last.name"></s:text></strong></div> 
                  	  			<s:textfield name="userLogonSearchForm.lastName" 
                  	  						id="lastName"
                  	  						value="%{userLogonSearchForm.lastName}"
                  	  						cssClass="form-control"  
                  	  						cssStyle="width:200px;" 
                  	  						maxLength="20"/>
                  	  			<div class="has-error"><s:property value="errors['lastNameError']"/></div>
                  	  		</div>
                  	  	</div>
                  	  	<div class="col-sm-4">
                  	  		<div class="form-group">
                  	  			<div><strong><s:text name="user.email"></s:text></strong></div> 
                  	  			<s:textfield name="userLogonSearchForm.emailAddr"
                  	  						id="emailAddr" 
                  	  						value="%{userLogonSearchForm.emailAddr}"
                  	  						cssClass="form-control"  
                  	  						cssStyle="width:400px;" 
                  	  						maxLength="100"/>
                  	  			<div class="has-error"><s:property value="errors['emailRequireError']"/></div>
                  	  			<div class="has-error"><s:property value="errors['emailPatternError']"/></div>
                  	  		</div>
                  	  	</div>
                  	  </div>
                  	  <div class="row mt">
                  	  	<div class="col-sm-3">
                  	  		<div class="form-group">
                  	  			<div><strong><s:text name="user.country"></s:text></strong></div> 
                  	  			<s:select class="form-control" 
                  	  					  id="countryId"
                  	  					  name="userLogonSearchForm.countryId" 
                  	  					  headerKey=""
                  	  					  headerValue="----------------------ALL----------------------"
                  	  					  list="countryList"
                  	  					  listKey="id"
                  	  					  listValue="countryName"
                  	  					  value="userLogonSearchForm.countryId"/>
                  	  			<div class="has-error"><s:property value="errors['countryRequireError']"/></div>
                  	  					  
                  	  		</div>
                  	  	</div>
                  	 
                  	  	<div class="col-sm-2">
                  	  		<div class="form-group">
                  	  			<div><strong><s:text name="user.authorize.group"></s:text></strong></div> 
                  	  			<s:select class="form-control" 
                  	  					  id="groupId"
                  	  					  name="userLogonSearchForm.groupId" 
                  	  					  headerKey=""
                  	  					  headerValue="---------------ALL--------------"
                  	  					  list="authorizeGroupList"
                  	  					  listKey="id"
                  	  					  listValue="groupName"
                  	  					  value="userLogonSearchForm.groupId"/>
                  	  			<div class="has-error"><s:property value="errors['groupRequireError']"/></div>
                  	  					  
                  	  		</div>
                  	  		
                  	  	</div>
                  	   <div class="col-sm-3">
                  	  			<div class="form-group">
									<br/>
									<button style="width:100%" type="button" class="btn btn-theme btn-sm" id="searchBtn">Search</button>
								</div>
								<div class="form-group">
									<br/>
									<button style="width:100%" type="button" id="resetBtn" class="btn btn-warning btn-sm"><s:text name="btn.clear" /></button>
								</div>
                  	  </div>	 
                  	  </div>

					</div><!-- /form-panel -->		
          		</div><!-- /col-lg-12 -->
          	</div><!-- /row --> 	
	</s:form>
<script src="<s:url value='/resources/assets/js/select2.min.js' />"></script>      
<script src="<s:url value="/resources/assets/moment-master/min/moment.min.js"/>"></script>
<script src="<s:url value="/resources/assets/js/bootbox.min.js"/>"></script> 
<script src="<s:url value="/resources/assets/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"/>"></script>

 <script type ="text/javascript">
	$('#searchBtn').click(function(){
		$('#userLogonSearchForm').attr('action','searchUser').submit();
	});
	$('#resetBtn').click(function(){
		 $('#userLogonId').val("");
		 $('#firstName').val("");
		 $('#lastName').val("");
		 $('#emailAddr').val("");
		 $('#countryId').val("");
		 $('#select2-countryId-container').text("----------------------ALL----------------------");
		 $('#groupId').val("");
		 $('#select2-groupId-container').text("---------------ALL--------------");
	});
 </script>
    
 <script type="text/javascript">
  $('select').select2();
</script>  
</body>
</html>