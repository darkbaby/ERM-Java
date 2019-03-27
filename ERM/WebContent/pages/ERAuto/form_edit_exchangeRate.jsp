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
          	<div class="row mt">
          		<div class="col-lg-12">
          			<div class="form-panel">
                  	  <h4 class="mb"><i class="glyphicon glyphicon-usd"></i><span class="subject-text"><s:text name="header.exchange.rate"/></span></h4>
                        <s:form name="exchangeRateAutoForm" id="exchangeRateAutoForm">
                        <s:hidden name="menuName" />
                        <table style="width:100%">
							<tr>
								<td style="width:25%">
								
								<s:hidden name="exchangeRateAutoForm.id"/>
								  <label class="col-sm-6  control-label"><strong><s:text name="label.extract.date.time2"/> : </strong></label>
<%-- 									<s:date name="exchangeRateAutoForm.createdDate" format='%{getText("ui.struts.datetime.format")}'/> --%>
									<s:property value="exchangeRateAutoForm.createDateStr"/>
									<s:hidden name="exchangeRateAutoForm.createDateStr" />
								 
<%-- 								 <label class="col-sm-5 control-label"><strong><s:text name="label.date"/> : </strong></label> --%>
<%-- 									<s:property value="exchangeRateAutoForm.rateDate" /> --%>
								</td>
								<td style="width:20%">
								 <label class="col-sm-5 control-label"><strong><s:text name="label.rate.date"/> : </strong></label>
<%-- 									<s:date name="exchangeRateAutoForm.rateDate" format='%{getText("ui.struts.date.format")}'/> --%>
									<s:property value="exchangeRateAutoForm.rateDateStr"/>
									<s:hidden name="exchangeRateAutoForm.rateDateStr" />
								</td>
								<td style="width:10%">
									<label class="col-sm-12  control-label"><strong><s:text name="label.ref.date"/> : </strong></label>
																
								</td>
								<td style="width:15%">
								<div class="input-group date" id="rateDateEndPicker">
<%-- 									<s:date name="exchangeRateAutoForm.refDate" id="refDateID" format='%{getText("ui.struts.date.format")}'/> --%>
<%-- 									<s:property value="exchangeRateAutoForm.refDateStr"/> --%>
									<s:textfield 
										name="exchangeRateAutoForm.refDateStr" 
										id="exchangeRateAutoForm.refDateStr"
										cssClass="form-control text-right"
									>
									</s:textfield>
									
									 <span class="input-group-addon">
										 <span class="glyphicon glyphicon-calendar"></span>
			                    	</span>
			                    	<div class="has-error"><s:property value="errors['refDateRequireError']"/></div>
			                    		
					            </div>
								</td>
								<td>
								</td>
								
							</tr>
							<tr>
								<td style="width:5%"> 
									<label class="col-sm-5 control-label"><strong><s:text name="label.bank.name"/> : </strong></label>
									<s:property value="exchangeRateAutoForm.bankName" />
									<s:hidden name="exchangeRateAutoForm.bankName"/>
								</td>
								<td style="width:5%"> 
									<label class="col-sm-6 control-label"><strong><s:text name="label.bank.country"/> : </strong></label>
									<s:property value="exchangeRateAutoForm.countryOfBank" />
									<s:hidden name="exchangeRateAutoForm.countryOfBank"/>
								</td>
								<td style="width:10%"> </td>
							</tr>
						</table>
                      <br>
                     <div><table align="right" style="margin-bottom:5px">
                   
                      </table></div>	 
 						<table id="rateTbl" class="table table-striped table-advance table-hover table-bordered" style="width:100%">
						  <tr>
						  	 
						  	<th style="text-align: center" rowspan="2">#</th>
							<th style="text-align: center" rowspan="2"><s:text name="label.currency"/></th>
                            <th style="text-align: center" rowspan="2"><s:text name="label.value"/></th>
                            <th style="text-align: center" colspan="2"><s:text name="label.exchenge.rate"/></th>
						  </tr>
						  <tr>
						    
						     
							<th style="text-align: center"><s:text name="label.buying.rate"/></th>
							<th style="text-align: center"><s:text name="label.selling.rate"/></th>
						  </tr>
						  <s:iterator value="exchangeRateAutoForm.exchangeRateAutoDetails" status="sts">
						  	<tr>
						  			<td  style="text-align:center;width:5%;">
						  				<s:property value="#sts.index+1" />
						  				<s:hidden name="exchangeRateAutoForm.exchangeRateAutoDetails[%{#sts.index}].id" />
						  			</td>
									<td style="text-align:center;width:15%;">
										<s:property value="currencyCode"/>
										<s:hidden name="exchangeRateAutoForm.exchangeRateAutoDetails[%{#sts.index}].currencyCode" />
									</td>
									<td style="text-align:right;width:10%;" >
										<s:property value="value"/>
										<s:hidden name="exchangeRateAutoForm.exchangeRateAutoDetails[%{#sts.index}].value" />
									</td>
									<td style="text-align:right;width:35%;" >
										<s:textfield name="exchangeRateAutoForm.exchangeRateAutoDetails[%{#sts.index}].buyingRate" 
														id="exchangeRateAutoForm.exchangeRateAutoDetails[%{#sts.index}].buyingRate" 
														class="form-control text-right"/>
										<div class="has-error"><s:property value="errors['buyingRateRequireError'+#sts.index]"/></div>
										<div class="has-error"><s:property value="errors['buyingRateError'+#sts.index]"/></div>
									</td>
								 	<td style="text-align:right;width:35%;" >
								 		<s:textfield name="exchangeRateAutoForm.exchangeRateAutoDetails[%{#sts.index}].sellingRate" 
								 						id="exchangeRateAutoForm.exchangeRateAutoDetails[%{#sts.index}].sellingRate" 
								 						class="form-control text-right"/>
								 		<div class="has-error"><s:property value="errors['sellingRateRequireError'+#sts.index]"/></div>
								 		<div class="has-error"><s:property value="errors['sellingRateError'+#sts.index]"/></div>
								 	</td>
							</tr>
						 </s:iterator>
						</table>
					 
						<h4 class="mb"><i class="glyphicon glyphicon-paperclip"></i><span class="subject-text"><s:text name="header.attract.file"/></span></h4>
							<table class="table table-striped table-advance table-hover table-bordered" >
							<thead>
								<tr>
									<th style="text-align: center">#</th>
									<th style="text-align: center">
										<s:text name="label.file"/>
									</th>
									<th style="text-align: center">
										<s:text name="label.option"/>
									</th>
								</tr>
							</thead>
						
							 <tbody>
								<s:iterator value="exchangeRateAutoForm.fileUploadList" status="st">
								<tr>
									<td style="text-align:center;"><s:property value="#st.index+1"/>    </td>
									<s:if test="id > 0">
										<td> 
										   <s:hidden name="exchangeRateAutoForm.fileUploadList[%{#st.index}].id" />
										   <s:hidden name="exchangeRateAutoForm.fileUploadList[%{#st.index}].name" />
										   <s:hidden name="exchangeRateAutoForm.fileUploadList[%{#st.index}].url" />
							               <s:property value="name"/>
										</td>
										<td style="text-align: center">
												<s:url var="donwloadFileUrl" action="downloadFileERByAuto">
									               	  	 <s:param name="parmID" value="exchangeRateAutoForm.id"/>
									               	  	 <s:param name="parmURL" value="url"/>
									            </s:url>
												<s:a href="%{donwloadFileUrl}" target="_blank">       	 
								               	 		<span class="glyphicon glyphicon-download-alt" 
								               	 			 title="<s:text name='label.download'/>" 
								               	 			 style="padding: 2px 10px 3px 10px;">
								               	 		</span>	             	 
								               	</s:a>					                
										</td>
									</s:if>
								</tr>
								</s:iterator>
								</tbody>
								</table>
								<table style="width:100%" align="center">
									<tr>
										<td align="center">
													<s:set var="targetAction" value="%{backAction}" />
													<s:url var="cancleURL" action="%{#targetAction}"/>
<%-- 													<s:url var="cancleURL" action="prepareAutoRateAdjust"/> --%>
				           							<s:a href="%{cancleURL}">
													<button type="button" id="cancelBtn" style="width:150px" class="btn btn-default btn-sm" title="<s:text name='btn.cancel'/>">
															<span class="glyphicon glyphicon-chevron-left"></span>
															<span><s:text name='btn.cancel'/></span>
													</button>
												 	</s:a>
												 
									                 <button  id="saveBtn" style="width:150px" type="button" class="btn btn-primary btn-sm"   title="<s:text name='btn.save'/>">
														<span class="glyphicon glyphicon-floppy-disk"></span>
														<span><s:text name='btn.save'/></span>
													</button>
										</td>
									</tr>
								</table>
						</s:form>	
          			</div><!-- /form-panel -->
          		</div><!-- /col-lg-12 -->
          	</div><!-- /row --> 

<script src="<s:url value="/resources/assets/js/bootbox.min.js"/>"></script>
<script src="<s:url value="/resources/assets/moment-master/min/moment.min.js"/>"></script> 
<script src="<s:url value="/resources/assets/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"/>"></script>

<script type="text/javascript">
	$('#saveBtn').click(function(){
		$('#exchangeRateAutoForm').attr('action','saveExchangeRateAuto').submit();
	});
	$('#cancelBtn').click(function(){
		window.history.back();
	});
	
	$('#rateDateEndPicker').datetimepicker({
  	  showTodayButton: true,
  	  allowInputToggle:true,
  	  format:'YYYY/MM/DD'
    });
//     $('#rateDateEndPicker').on("dp.change",function(e){
//   	  $('#rateDateStartPicker').data("DateTimePicker").maxDate(e.date);
//     });
	 
</script>

</body>
</html>