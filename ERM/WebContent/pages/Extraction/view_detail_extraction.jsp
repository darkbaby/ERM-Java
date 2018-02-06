<%@page import="java.util.List"%>
<%@page import="com.esynergy.erm.model.ob.Currency"%>
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
     <link href="<s:url value='/resources/assets/css/select2.min.css' />" rel="stylesheet" />
     <link href="<s:url value="/resources/assets/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css"/>" rel="stylesheet" />
</head>
 
 <body>	 
 	
		<div class="row mt">
			<div class="col-lg-12">
				<div class="form-panel">
					<h4 class="mb"><i class="fa fa-info"></i>
						<span class="subject-text"><s:text name="header.bank.info"/></span>
                  	</h4>
               
					<table style="width:100%;">
						<tr>
							<th style="width:50%;">
								<s:text name="label.bank.name"/>
							</th>
							<th style="width:20%;">
								<s:text name="label.bank.short.name"/>
							</th>
							<th	style="width:30%;">
								<s:text name="label.bank.country"/>
							</th>
						</tr>
						<tr>
							<td>
								<s:property value="extractionForm.bankName"/>
							</td>
							<td>
								<s:property value="extractionForm.bankShortName"/>
							</td>
							<td>
								<s:property value="extractionForm.bankCountryName"/>						
							</td>
						</tr>
					</table>
					
					<br/>
					
				</div><!-- /form-panel -->		
          	</div><!-- /col-lg-12 -->
          </div><!-- /row --> 	
          	
			<div class="row mt">
          		<div class="col-lg-12">
          			<div class="form-panel">
						<h4 class="mb">
							<i class="fa fa-cog"></i>
							<s:text name="header.extraction.setting.url"></s:text>
                  		</h4>
                  		
                  		<table style="width:100%;">
						<tr>
							<th style="width:33%;">
							</th>
							<th	style="width:33%;">
							</th>
							<th	style="width:34%;">
							</th>
						</tr>
						<tr>
							<th>
								<s:text name="label.extraction.type"/>
							</th>
							<th>
								<s:text name="label.extract.status"/>
							</th>
						</tr>
						<tr>
							<td>
								<s:property value="extractionForm.extractionTypeName"/>
							</td>
							<td>
								<s:property value="extractionForm.statusName"/>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								&nbsp;
							</td>
						</tr>
						<tr>
							<th>
								<s:text name="label.setting.url"/>
							</th>
						</tr>
						<tr>
							<td colspan="3">
								<s:property value="extractionForm.url"/>
								 	
							</td>
						</tr>
						<tr>
							<td colspan="3">
								&nbsp;
							</td>
						</tr>
						<tr>
							<th>
								<s:text name="label.setting.format.date"/>
							</th>
							<th colspan="2">
								<s:text name="label.setting.css.selector.for.check.date"/>
							</th>
						</tr>
						<tr>
							<td>
								<s:property value="extractionForm.formatDate"/>								
							</td>
							<td>
								<s:property value="extractionForm.cssDate"/>					
							</td>
						</tr>
						<tr>
							<td colspan="3">
								&nbsp;
							</td>
						</tr>
						<tr>
							<th>
								<s:text name="label.setting.type.of.currency"/>
							</th>
							<th>
								<s:text name="label.setting.date.to.extract"/>
							</th>
							<th>
								<s:text name="label.setting.type.of.header.on.webpage"/>
							</th>
						</tr>
						<tr>
							<td>
								<s:property value="extractionForm.pairCurrencyTypeName"/>								
							</td>
							<td>
								<s:property value="extractionForm.extractionDate"/>					
							</td>
							<td>
								<s:property value="extractionForm.pageTypeName"/>					
							</td>
						</tr>
						<tr>
							<td colspan="3">
								&nbsp;
							</td>
						</tr>
						<tr>
							<th>
								<s:text name="label.setting.time.to.extract"/>
								<s:text name="label.time.zone"/>
							</th>
						</tr>
						<tr>
							<td colspan="3">
								<table>
									<s:iterator value="extractionForm.extractionTimeFormList" status="sts">
									<s:if test="%{(#sts.index+1)%8 == 1}">
 										<tr>
 									</s:if>
									<td>
									<s:hidden
										name="extractionForm.extractionTimeFormList[%{#sts.index}].id"
									/>
									<s:checkbox
										name="extractionForm.extractionTimeFormList[%{#sts.index}].extractionTime"
 										id="extractionForm.extractionTimeFormList[%{#sts.index}].extractionTime"
 										fieldValue="%{extractionTime}"
 										value="%{chk}"
 									/>
 									<s:text
 										name="extractionTimeLabel" />
 										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 									</td>
 									
 									<s:if test="%{(#sts.index+1)%8 == 0}">
 										</tr>
 									</s:if>
									</s:iterator>
								</table>
							</td>
						</tr>
					</table>


					</div><!-- /form-panel -->		
          		</div><!-- /col-lg-12 -->
          	</div><!-- /row -->
          	
           	<div class="row mt">
          		<div class="col-lg-12">
          			<div class="form-panel">
						<h4 class="mb">
							<i class="fa fa-cog"></i>
							<s:text name="header.extraction.setting.extraction"></s:text>
                  		</h4>
                  		
                  		<table style="width:100%;">
                  		<tr>
                  			<th style="width:100%;"></th>
                  		</tr>
                  		</table>
<%--                   		<h5><strong><s:text  name="label.pair.currency"/> is :</strong> <s:property value="extractionForm.countryCode"/></h5> --%>
                  		<s:include value="view_setting_table_type1.jsp"/>
					</div><!-- /form-panel -->
          		</div><!-- /col-lg-12 -->
          	</div><!-- /row --> 

        	<table style="width:100%" align="center">
				<tr>
					<td align="center">
							<s:url var="cancleURL" action="prepareMangeExtraction"/>
				            <s:a href="%{cancleURL}">
								<button type="button" style="width:150px" class="btn btn-default btn-sm" title="<s:text name='btn.cancel'/>">
										<span class="glyphicon glyphicon-chevron-left"></span>
										<span><s:text name='btn.back'/></span>
								</button>
							</s:a>
							<modong:sys-permission function="EditScraping">
							<s:url var="editURL"   action="prepareEditExtraction"><s:param name="parm" value="%{extractionForm.id}" />  </s:url>        
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
			
			<br/>
			<br/>	
	 
<script src="<s:url value='/resources/assets/js/select2.min.js' />"></script>   
<script src="<s:url value="/resources/assets/moment-master/min/moment.min.js"/>"></script>
<script src="<s:url value="/resources/assets/js/bootbox.min.js"/>"></script> 
<script src="<s:url value="/resources/assets/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"/>"></script>

</body>
</html>