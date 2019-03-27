<%@page import="java.util.List"%>
<%@page import="com.esynergy.erm.model.ob.Currency"%>
<%@page import="com.esynergy.erm.web.action.IPageContains"%>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@ taglib prefix="modong" uri="/WEB-INF/ModongTag.tld" %> 
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <link rel="stylesheet" href="<s:url value='/resources/assets/css/dataTables.bootstrap.css' />"/>
   <style>
		.radioMarginRight{
			margin-right: 0px !important;
		}
		label.radioMarginRight{
			margin-right: 20px !important;
		}
		.modal {
		  text-align: center;
		  padding: 0!important;
		}
		
		.modal:before {
		  content: '';
		  display: inline-block;
		  height: 100%;
		  vertical-align: middle;
		  margin-right: -4px;
		}
		
		.modal-dialog {
		  display: inline-block;
		  text-align: left;
		  vertical-align: middle;
		}
	</style>
</head>
 
 <body>	 
	<!--EXCHANGE RATE LIST -->
          	<div class="row mt">
          		<div class="col-lg-12">
          			<div class="form-panel">
                  	  <h4 class="mb"><i class="fa fa-cog"></i><span class="subject-text"><s:text name="header.manual.list"/></span></h4>
                  	  <modong:sys-permission function="CreateExchangeRateManual">
	                  	  <table align="right" style="margin-bottom:5px">
		                      <tr>
		                      	<td  align="right" >
<!-- 		                      		<a data-toggle="modal" data-target="#myModal"> -->
			                      		<button type="button" onclick="clickAddManualTarget();" class="btn btn-theme" style="width: 100px" 
			                      			title="<s:text name='msg.add.new.exchange.rate'/>">			                      			
			                      			<span class="glyphicon glyphicon-plus-sign"></span>
			                      			<span ><s:text name="btn.add"/></span>
			                      		</button>
<!-- 		                      		</a> -->
		                      	</td>
		                      </tr>
	                      </table>
                      </modong:sys-permission>
                      <div>
                       
                      </div>
   			          <table class="table table-bordered table-striped  table-hover" id="manualListTbl" style="display:none;">
				                      <thead>
				                  
										  <tr>
										  	<th><s:text name="label.first.currency"/></th>
										  	<th><s:text name="label.pair.currency"/></th>	    
										  	<th><s:text name="label.status"/></th>
											<th><s:text name="label.last.update"/></th>
											<th><s:text name="label.last.update.by"/></th>
											<th><s:text name="label.owner"/></th>
											<th style="text-align: center;"><s:text name="label.option"/></th>
										  </tr>
				                      </thead>
				                      <tbody>
				                      	    <s:iterator value="manualTargetList" status="hdrsts">
				                      	    		<tr>
				                      	    		 	<td style="text-align: left;"><s:property value="baseCurrency.code" /></td>
				                      	    			<td style="text-align: left;"><s:property value="pairCurrency.code" /></td>
				                      	    			<td style="text-align: left;">
				                      	    				<s:if test='%{status == "A"}'>
				                      	    					Active
				                      	    				</s:if>
				                      	    				<s:else>
				                      	    					Inactive
				                      	    				</s:else>
<%-- 				                      	    				<s:property value="status" /> --%>
				                      	    			</td>  					                      	    			
				                      	    			<td style="text-align: left;"><s:date name="changeDate" format="yyyy/MM/dd HH:mm:ss"/></td>
				                      	    			<td style="text-align: left;"><s:property value="changeUser"/></td>
				                      	    			<td style="text-align: left;">
				                      	    				<s:if test='%{owner != null}'>
				                      	    					<s:property value="owner.logOnId"/>
				                      	    				</s:if>
				                      	    				<s:else>
				                      	    					<s:property value=""/>
				                      	    				</s:else>
				                      	    			</td>
				                      	    			<td style="text-align: center;"> 
<%-- 				                      	    			  <s:url var="editURL"   action="prepareEditExchangeRateByManualAction"><s:param name="parm" value="%{[1].id}" />  </s:url> --%>
<%-- 				                      	    			  <s:url var="viewURL"   action="prepareViewExchangeRateByManualAction"><s:param name="parm" value="%{[1].id}" />  </s:url> --%>
<%-- 				                      	    			    <s:a href="%{viewURL}"> --%>
<!-- 				                      	    			    	<button class="btn btn-success btn-sm"  -->
<%-- 				                      	    			    			title="<s:text name="label.view.detail"/>"> --%>
<!-- 				                      	    			    		<i class="fa fa-file"></i> -->
<!-- 				                      	    			    	</button> -->
<%-- 				                      	    			    </s:a> --%>
				                      	    			  
<%-- 				                      	    			    <modong:sys-permission function="EditExchangeRateManual" user="${createdUser}" > --%>
<%-- 					                      	    			    <s:a href="%{editURL}"> --%>
					                      	    			    	<button class="btn btn-primary btn-sm" 
					                      	    			    		onclick="clickEditManualTarget(this.parentNode);"
					                      	    			    			title="<s:text  name="msg.edit"/>">
					                      	    			    		<i class="glyphicon glyphicon-edit"></i>
					                      	    			    	</button>
					                      	    			    	<span data-name="id" style="display:none;"><s:property value="id" /></span>
							                      	    		 	<span data-name="baseCurrency" style="display:none;"><s:property value="baseCurrency.id" /></span>
							                      	    		 	<span data-name="pairCurrency" style="display:none;"><s:property value="pairCurrency.id" /></span>
							                      	    		 	<span data-name="status" style="display:none;"><s:property value="status" /></span>
<%-- 					                      	    			    </s:a> --%>
<%-- 				                      	    			    </modong:sys-permission> --%>
				                      	    			    
				                      	    			</td>
				                      	    		</tr>
				                      	    	
				                      	    </s:iterator>
				                      </tbody>
				       </table>
	
          			</div><!-- /panel -->
          		</div><!-- /col-lg-12 -->
          	</div><!-- /row -->
          	
          	
          	<!-- Modal -->
		          <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
		              <div class="modal-dialog modal-lg">
		                  <div class="modal-content">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title" id="modalHeaderText">Add Pair Currency</h4>
		                      </div>
		                      <div class="modal-body">
		                          
<!-- 		                          <p>Add Pair Currency</p> -->
		                          
		                          <s:if test="errors['isError']" >
		                          <div class="alert alert-danger alert-dismissable fade in" id="divShowError" >
								      <span class="glyphicon glyphicon-exclamation-sign"></span>
								      <s:property value="actionMsg['error']"/>
							   	  </div>
							   	  </s:if>
		                          
								  <s:form name="manualTargetForm"  id="manualTargetForm" theme="simple" class="form-inline" role="form">
										<s:hidden name="menuName"/>
										<s:hidden
												cssStyle="display:none;"
												name="manualTargetForm.id"
												id="idAdd" />	
										
										<div align="center">
										<div class="form-group" style="width:25%;">
										<div><s:text name="label.base.currency"/></div>
										<s:select class="form-control"
												cssStyle="width:60%"
											    headerKey="-1"
												headerValue="---Please Select---"
												list="currencyList"
												listKey="id"
												listValue="code"
												name="manualTargetForm.baseCurrency"
												id="baseCurrencyAdd" />	
					                    </div>
					                    
					                    <div class="form-group" style="width:25%;">
										<div ><s:text name="label.pair.currency"/></div>
											<s:select class="form-control"
													cssStyle="width:60%"
												    headerKey="-1"
													headerValue="---Please Select---"
													list="currencyList"
													listKey="id"
													listValue="code"
													name="manualTargetForm.pairCurrency"
													id="pairCurrencyAdd" />
										</div>
										
										<div class="form-group" style="width:25%;">
										<div ><s:text name="label.owner"/></div>
											<s:select class="form-control"
													cssStyle="width:60%"
												    headerKey="-1"
													headerValue=""
													list="userList"
													listKey="id"
													listValue="logOnId"
													name="manualTargetForm.ownerID"
													id="ownerIDAdd" />
										</div>
										
										<div class="form-group" style="wdith:25%;">
										<div ><s:text name="label.status"/></div>
										<s:radio 
											cssClass="radioMarginRight"
										    name="manualTargetForm.status"
										    id="statusAdd"
											list="statusList"
											listKey="value"
											listValue="descriptionShort"
											value="defaultStatusValue"
										/>
										</div>
										</div>
										
										</s:form>
		                          
		                           
		                          
<!-- 		                          <input type="text" name="email" id="email" placeholder="Email" autocomplete="off" class="form-control placeholder-no-fix"> -->
<%-- 		                          <span id="resetMsg"></span> --%>
								
		                      </div>
		                      <div class="modal-footer">
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Close</button>
		                          <button class="btn btn-theme" type="button" onclick="addManualTarget();"  data-loading-text="<i class='fa fa-spinner fa-spin'></i> Processing..">Submit</button>
		                      </div>
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
          			                
 <script type="text/javascript" src="<s:url value='/resources/assets/js/jquery.dataTables.js'/>"></script>
 <script type="text/javascript" src="<s:url value='/resources/assets/js/dataTables.bootstrap.js'/>"></script>
 <script type ="text/javascript">
  	$(document).ready(function(){
 	    $('#manualListTbl').dataTable({
 	    	searching: false,
 	    	 "order": [[ 3, "desc" ]]
 	    });
 	    $('#manualListTbl').css("display","");
 	    
 	    let isError = '<s:property value="errors[\'isError\']" /> ';
 	    if(isError.trim()){
 	    	$("#myModal").modal("show");
 	    }
 	});
  	
  	function clickAddManualTarget(){
  		
  		
  		
  		$("#divShowError").css("display","none");
  		
  		$("modalHeaderText").html("Add Pair Currency");
  		
  		$("#idAdd").val('0');
  		$("#baseCurrencyAdd").val('-1').trigger('change');
  		$("#pairCurrencyAdd").val('-1').trigger('change');
  		$("[type=radio]").filter('[value=A]').prop('checked',true);
  		
	    $("#myModal").modal("show");
  	}
  	
  	function clickEditManualTarget(trEl){
  		
  		$("#divShowError").css("display","none");

  		$("modalHeaderText").html("Edit Pair Currency");
  		
  		let idVal = $(trEl).find("span[data-name=id]").html();
  		let baseCurrencyIDVal = $(trEl).find("span[data-name=baseCurrency]").html();
  		let pairCurrencyIDVal = $(trEl).find("span[data-name=pairCurrency]").html();
  		let statusVal = $(trEl).find("span[data-name=status]").html();
  		
  		$("#idAdd").val(idVal);
  		$("#baseCurrencyAdd").val(baseCurrencyIDVal).trigger('change');
  		$("#pairCurrencyAdd").val(pairCurrencyIDVal).trigger('change');
  		$("[type=radio]").filter('[value=' + statusVal + ']').prop('checked',true);
  		
	    $("#myModal").modal("show");
  	}
  	
  	function addManualTarget(){
  		document.getElementById("manualTargetForm").action = "addManualTarget.action";
    	document.getElementById("manualTargetForm").submit();
  	}
 
  
  	$('#baseCurrencyCdt').select2();
  	$('#pairCurrencyCdt').select2();

  	$('#baseCurrencyAdd').select2({
  		dropdownParent:$("#myModal")
  	});
  	$('#pairCurrencyAdd').select2({
  		dropdownParent:$("#myModal")
  	});

  	
 </script>
    
</body>
</html>