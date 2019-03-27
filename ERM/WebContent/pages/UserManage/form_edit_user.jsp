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
	<s:form name="userLogonForm" method="POST"  id="userLogonForm" theme="simple" class="form-inline" role="form">
          	<s:hidden name="menuName"/>
          	<div class="row mt">
          		<div class="col-lg-12">
          			<div class="form-panel">
                  	  <h4 class="mb"><i class="fa fa-cog"></i><span class="subject-text"><s:text name="header.user.info"/></span></h4>
   			 
	                 <s:if test="errors['isError']">
	                  	       <div class="alert alert-danger alert-dismissable fade in"  >
								    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
								    <h5><span class="glyphicon glyphicon-exclamation-sign"></span>
								      <s:property value="actionMsg['error']"/> </h5>
							   </div> 
                  	  </s:if> 
                  	  <div class="row mt">
                  	  	<div class="col-sm-3">
                  	  		<div class="form-group">
                  	  			<div><strong><s:text name="user.logon.id"></s:text></strong></div> 
                  	  			<s:hidden name="userLogonForm.id"/>
                  	  			<s:textfield name="userLogonForm.userLogonId" 
                  	  						id="userLogonId" 
                  	  						cssClass="form-control" 
                  	  						cssStyle="width:200px;" 
                  	  						maxLength="16" 
                  	  						value="%{userLogonForm.userLogonId}"/>
                  	  			<div class="has-error"><s:property value="errors['logOnIdError']"/></div>
                  	  			<div class="has-error"><s:property value="errors['logOnIdInvalidError']"/></div> 
                  	  		</div>
                  	  	</div>
                  	  	<div class="col-sm-3">
                  	  		<div><strong><s:text name="label.status"></s:text></strong></div> 
                  	  		<s:if test="userLogonForm.status == null">
									<s:radio 
									cssClass="radioMarginRight"
								    name="userLogonForm.status"
								    id="userLogonForm.status"
									list="statusList"
									listKey="value"
									listValue="descriptionShort"
									value="defaultStatusValue"
								/>
								</s:if>
								<s:else>
									<s:radio 
										cssClass="radioMarginRight"
									    name="userLogonForm.status"
									    id="userLogonForm.status"
										list="statusList"
										listKey="value"
										listValue="descriptionShort"
										value="%{userLogonForm.status}"
									/>
								</s:else>
                  	  	</div>
                  	  </div>
                  	  <s:if test="%{userLogonForm.id==0}">
                  	  <div class="row mt">
                  	  	<div class="col-sm-3">
                  	  		<div class="form-group">
                  	  			<div><strong><s:text name="user.pwd"></s:text></strong></div> 
                  	  			<s:password name="userLogonForm.pwd" cssClass="form-control" cssStyle="width:200px;" maxLength="16"/>
                  	  			<div class="has-error"><s:property value="errors['pwdError']"/></div> 
                  	  			<div class="has-error"><s:property value="errors['pwdFindSpaceError']"/></div>
                  	  			
                  	  		</div>
                  	  	</div>
                  	  	<div class="col-sm-3">
                  	  		<div class="form-group">
                  	  			<div><strong><s:text name="user.pwd.confirm"></s:text></strong></div> 
                  	  			<s:password name="userLogonForm.pwdConfirm" cssClass="form-control"  cssStyle="width:200px;" maxLength="16"/>
                  	  			<div class="has-error"><s:property value="errors['pwdConfirmError']"/></div>
                  	  			<div class="has-error"><s:property value="errors['pwdConfirmNotMatError']"/></div>
                  	  		</div>
                  	  	</div>
                  	  </div>
                  	  </s:if>
                  	  <div class="row mt">
                  	  	<div class="col-sm-3">
                  	  		<div class="form-group">
                  	  			<div><strong><s:text name="user.first.name"></s:text></strong></div> 
                  	  			<s:textfield name="userLogonForm.firstName"  
                  	  						value="%{userLogonForm.firstName}"
                  	  						cssClass="form-control"  
                  	  						cssStyle="width:200px;"
                  	  						 maxLength="20"/>
                  	  			<div class="has-error"><s:property value="errors['firstNameError']"/></div>
                  	  		</div>
                  	  	</div>
                  	  	<div class="col-sm-3">
                  	  		<div class="form-group">
                  	  			<div><strong><s:text name="user.last.name"></s:text></strong></div> 
                  	  			<s:textfield name="userLogonForm.lastName" 
                  	  						value="%{userLogonForm.lastName}"
                  	  						cssClass="form-control"  
                  	  						cssStyle="width:200px;" 
                  	  						maxLength="20"/>
                  	  			<div class="has-error"><s:property value="errors['lastNameError']"/></div>
                  	  		</div>
                  	  	</div>
                  	  </div>
                  	  <div class="row mt">
                  	  	<div class="col-sm-4">
                  	  		<div class="form-group">
                  	  			<div><strong><s:text name="user.email"></s:text></strong></div> 
                  	  			<s:textfield name="userLogonForm.emailAddr" 
                  	  						value="%{userLogonForm.emailAddr}"
                  	  						cssClass="form-control"  
                  	  						cssStyle="width:400px;" 
                  	  						maxLength="100"/>
                  	  			<div class="has-error"><s:property value="errors['emailRequireError']"/></div>
                  	  			<div class="has-error"><s:property value="errors['emailPatternError']"/></div>
                  	  			<div class="has-error"><s:property value="errors['emailExistError']"/></div>
                  	  		</div>
                  	  	</div>
                  	  </div>
                  	  <div class="row mt">
                  	  	<div class="col-sm-6">
                  	  		<div class="form-group">
                  	  			<div><strong><s:text name="user.country"></s:text></strong></div> 
                  	  			<s:select class="form-control" 
                  	  					  id="countryId"
                  	  					  name="userLogonForm.countryId" 
                  	  					  headerKey=""
                  	  					  headerValue="---Plese Select---"
                  	  					  list="countryList"
                  	  					  listKey="id"
                  	  					  listValue="countryName"
                  	  					  value="userLogonForm.countryId"/>
                  	  			<div class="has-error"><s:property value="errors['countryRequireError']"/></div>
                  	  					  
                  	  		</div>
                  	  	</div>
                  	  </div>
                  	  <div class="row mt">
                  	  	<div class="col-sm-6">
                  	  		<div class="form-group">
                  	  			<div><strong><s:text name="user.authorize.group"></s:text></strong></div> 
                  	  			<s:select class="form-control" 
                  	  					  id="groupId"
                  	  					  name="userLogonForm.groupId" 
                  	  					  headerKey=""
                  	  					  headerValue="---Plese Select---"
                  	  					  list="authorizeGroupList"
                  	  					  listKey="id"
                  	  					  listValue="groupName"
                  	  					  value="userLogonForm.groupId"/>
                  	  			<div class="has-error"><s:property value="errors['groupRequireError']"/></div>
                  	  					  
                  	  		</div>
                  	  	</div>
                  	  </div>
                  	  
                  	  <s:if test="%{userLogonForm.groupId != 3}">
                  	  	<div class="row mt" id="locationPersonOnlyTable" style="display:none;">
                  	  </s:if>
                  	  <s:else>
                  	  	<div class="row mt" id="locationPersonOnlyTable">
                  	  </s:else>
                  	  	<div class="col-sm-6">
                  	  		<div class="form-group">
                  	  			<div><strong><s:text name="label.currency"></s:text></strong></div> 
                  	  			<table id="formTable" class="table table-striped table-advance table-hover" style="width:100%">
									<thead>
										<tr>
											<th>
												<s:text name="label.select" />
											</th>
											<th>
												<s:text name="label.base.currency" />
											</th>
											<th>
												<s:text name="label.pair.currency" />
											</th>
											<th>
												<s:text name="label.owner" />
											</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="userLogonForm.manualTargetCheckFormList" status="sts">
											<tr>
												<td>
													<s:hidden name="userLogonForm.manualTargetCheckFormList[%{#sts.index}].id" />
													<s:if test="%{haveAnotherOwner}">
													<s:checkbox
							  							name="userLogonForm.manualTargetCheckFormList[%{#sts.index}].chk"
				 										id="userLogonForm.manualTargetCheckFormList[%{#sts.index}].chk"
				 										fieldValue="%{id}"
				 										value="%{chk}"
				 										disabled="true"
							  						/>
													</s:if>
													<s:else>
													<s:checkbox
							  							name="userLogonForm.manualTargetCheckFormList[%{#sts.index}].chk"
				 										id="userLogonForm.manualTargetCheckFormList[%{#sts.index}].chk"
				 										fieldValue="%{id}"
				 										value="%{chk}"
							  						/>
													</s:else>
							  					</td>
												<td>
													<s:property
														value="baseCurrencyStr" 
													/>
												</td>
												<td>
													<s:property
														value="pairCurrencyStr"
													/>
												</td>
												<td>
													<s:property
														value="owner"
													/>
												</td>
											</tr>
										</s:iterator>
									</tbody>
								</table>  
                  	  		</div>
                  	  	</div>
                  	  </div>

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
											 
										<s:if test="userLogonForm.id != 0">
											<button style="width:150px" type="button" class="btn btn-danger btn-sm" id="removeBtn" title="<s:text name='btn.remove'/>">
												<span class="glyphicon glyphicon-trash"></span>
												<span><s:text name='btn.remove'/></span>
											</button>
										</s:if>
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
<script src="<s:url value='/resources/assets/js/select2.min.js' />"></script>      
<script src="<s:url value="/resources/assets/moment-master/min/moment.min.js"/>"></script>
<script src="<s:url value="/resources/assets/js/bootbox.min.js"/>"></script> 
<script src="<s:url value="/resources/assets/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"/>"></script>

 <script type ="text/javascript">
	$('#saveBtn').click(function(){
		$('#userLogonForm').attr('action','saveUser').submit();
	});
// 	$('#cancelBtn').click(function(){
// 		window.history.back();
// 	});
	$("#removeBtn").click(function(){
		 
		    bootbox.confirm({
		        title: "Confirm Remove ?",
		        message: "Do you want to remove User now? This cannot be undone.",
		        buttons: {
		            cancel: {
		                label: '<i class="fa fa-times"></i> Cancel'
		            },
		            confirm: {
		                label: '<i class="fa fa-check"></i> Confirm'
		            }
		        },
		        callback: function (result) {
		            if(result){
		            	$('#userLogonForm').attr('action','removeUser').submit();
		            }
		        }
		    });
		
	});
	
	$("#groupId").change(function(){
		groupName = $(this).val();
		if(groupName == '3'){
			$("#locationPersonOnlyTable").css('display', '');
		}
		else{
			$("#locationPersonOnlyTable").css('display', 'none');
		}
	});
	
 </script>
    
 <script type="text/javascript">
  $('select').select2();
</script>  
</body>
</html>