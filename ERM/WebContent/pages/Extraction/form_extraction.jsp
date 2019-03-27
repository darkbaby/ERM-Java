<%@page import="java.util.List"%>
<%@page import="com.esynergy.erm.model.ob.Currency"%>
<%@page import="com.esynergy.erm.web.action.IPageContains"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
 <link href="<s:url value='/resources/assets/css/select2.min.css' />" rel="stylesheet" />
	<s:form name="extractionForm" enctype="multipart/form-data" id="extractionForm" theme="simple" class="form-inline" role="form">
		<s:hidden name="menuName"/>
		<div class="row mt">
			<div class="col-lg-12">
				<div class="form-panel">
					<h4 class="mb">
						<i class="fa fa-cog"></i>
						<s:text name="header.extraction.bank.create"></s:text>
                  	</h4>
                  	<s:if test="errors['isError']">
                  		<div class="alert alert-danger alert-dismissable fade in"  >
                  			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
							<h5>
								<span class="glyphicon glyphicon-exclamation-sign"></span>
								<s:property value="actionMsg['error']"/>
							</h5>
						</div> 
					</s:if> 
					
					<table style="width:100%;">
						<tr>
							<th style="display:none;">
								<s:hidden name="extractionForm.id"/>
							</th>
							<th style="width:40%;">
								<s:text name="label.bank.name"/>
								<span class="erm-required"></span>
							</th>
							<th style="width:15%;">
								<s:text name="label.bank.short.name"/>
								<span class="erm-required"></span>
							</th>
							<th	style="width:25%;">
								<s:text name="label.bank.country"/>
								<span class="erm-required"></span>
							</th>
							<th	style="width:20%;">
								<s:text name="label.base.currency"/>
								<span class="erm-required"></span>
							</th>
						</tr>
						<tr>
							<td style="display:none;">
								<s:hidden name="extractionForm.bankID"/>
							</td>
							<td>
								<s:textfield cssStyle="width:98%;" name="extractionForm.bankName" 
									id="bankName" value="%{extractionForm.bankName}"
									class="form-control" />
								<div class="has-error" id="bankNameError"><s:property value="errors['errorBankName']" /></div>	
							</td>
							<td>
								<s:textfield cssStyle="width:95%;" name="extractionForm.bankShortName" 
									id="bankShortName" value="%{extractionForm.bankShortName}"
									class="form-control" maxlength="5" />
								<div class="has-error" id="bankShortNameError"><s:property value="errors['errorBankShortName']" /></div>	
							</td>
							<td>
								<s:select 
								    class="form-control"
									cssStyle="width:95%;"
								    name="extractionForm.bankCountry"
								    id="countryComboBox"
									list="countryList"
									listKey="id"
									listValue="countryName"
									headerKey="-1"
									headerValue="---Please Select---"
								/>
								<div class="has-error" id="countryComboBoxError"><s:property value="errors['errorBankOfCountry']" /></div>			
							</td>
							<td>
								<s:select 
								    class="form-control"
									cssStyle="width:100%;"
								    name="extractionForm.baseCurrency"
								    id="baseCurrencyComboBox"
									list="currencyList"
									listKey="id"
									listValue="code"
									headerKey="-1"
									headerValue="---Please Select---"
								/>
								<div class="has-error" id="baseCurrencyComboBoxError"><s:property value="errors['errorBaseCurrency']" /></div>			
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
								<s:select 
								    class="form-control"
								    cssStyle="width:95%;"
								    name="extractionForm.extractionType"
								    id="extractionType"
									list="extractionTypeList"
									listKey="value"
									listValue="descriptionShort"
									onchange="onChangeExtractionType(this)"
								/>
							</td>
							<td>
								<s:if test="extractionForm.status == null">
									<s:radio 
									cssClass="radioMarginRight"
								    name="extractionForm.status"
								    id="statusRadioButton"
									list="statusList"
									listKey="value"
									listValue="descriptionShort"
									value="defaultStatusValue"
								/>
								</s:if>
								<s:else>
									<s:radio 
										cssClass="radioMarginRight"
									    name="extractionForm.status"
									    id="statusRadioButton"
										list="statusList"
										listKey="value"
										listValue="descriptionShort"
										value="%{extractionForm.status}"
									/>
								</s:else>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								&nbsp;
							</td>
						</tr>
						<tr>
							<th colspan="3">
								<s:text name="label.setting.url"/>
								<span class="erm-required"></span>
							</th>
						</tr>
						<tr>
							<td colspan="3">
								<s:textfield cssStyle="width:100%;" name="extractionForm.url" 
									id="url" value="%{extractionForm.url}" 
									class="form-control" />	
								<div class="has-error" id="urlError"><s:property value="errors['errorURL']" /></div>
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
								<span class="erm-required"></span>
							</th>
							<th colspan="2">
								<s:text name="label.setting.css.selector.for.check.date"/>
								<s:if test="extractionForm.extractionType <= 1">
								<span class="erm-required"></span>
								</s:if>
							</th>
						</tr>
						<tr>
							<td>
								<s:select 
								    class="form-control"
								    cssStyle="width:95%;"
								    name="extractionForm.formatDate"
								    id="formatDate"
									list="formatDateList"
									listKey="value"
									listValue="descriptionShort"
									headerKey="-1"
									headerValue="---Please Select---"
								/>
								<div class="has-error" id="formatDateError"><s:property value="errors['errorFormatDate']" /></div> 		
							</td>
							<td colspan="2">
								<s:if test="extractionForm.extractionType <= 1">
									<s:textfield cssStyle="width:100%;" name="extractionForm.cssDate" 
										id="cssDate" value="%{extractionForm.cssDate}" 
										class="form-control" />	
									<div class="has-error" id="cssDateError"><s:property value="errors['errorCSSSelector']" /></div> 
								</s:if>
								<s:else>
									<s:textfield cssStyle="width:100%;" 
										id="cssDate"
										class="form-control"
										disabled="true" />
									<s:hidden
										name="extractionForm.cssDate"
									/>	
									<div class="has-error" id="cssDateError"><s:property value="errors['errorCSSSelector']" /></div> 
								</s:else>
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
								<span class="erm-required"></span>
							</th>
							<th>
								<s:text name="label.setting.date.to.extract"/>
								<span class="erm-required"></span>
							</th>
							<th>
<%-- 								<s:text name="label.setting.type.of.header.on.webpage"/> --%>
<%-- 								<span class="erm-required"></span> --%>
							</th>
						</tr>
						<tr>
							<td>
								<s:select 
								    class="form-control"
								    cssStyle="width:95%;"
								    name="extractionForm.pairCurrencyType"
								    id="pairCurrencyType"
									list="currencyTypeList"
									listKey="value"
									listValue="descriptionShort"
									headerKey="-1"
									headerValue="---Please Select---"
								/>
								<div class="has-error" id="pairCurrencyTypeError"><s:property value="errors['errorTypeOfCurrency']" /></div>
							</td>
							<td>
								<s:select 
								    class="form-control"
								    cssStyle="width:95%;" 
								    name="extractionForm.extractionDate"
								    id="extractionDate"
									list="extractionDateList"
									listKey="value"
									listValue="descriptionShort"									
									headerKey="-1"
									headerValue="---Please Select---"
								/>
								<div class="has-error" id="extractionDateError"><s:property value="errors['errorDateToExtract']" /></div>				
							</td>
							<td>
								<s:hidden name="extractionForm.pageType" value="2"/>
<%-- 								<s:select  --%>
<%-- 								    class="form-control"  --%>
<%-- 								    cssStyle="width:100%;"  --%>
<%-- 								    name="extractionForm.pageType" --%>
<%-- 								    id="pageType" --%>
<%-- 									list="pageTypeList" --%>
<%-- 									listKey="value" --%>
<%-- 									listValue="descriptionShort" --%>
<%-- 									headerKey="-1" --%>
<%-- 									headerValue="-------- Please Select --------" --%>
<%-- 								/> --%>
<%-- 								<div class="has-error" id="pageTypeError"><s:property value="errors['errorTypeOfHeader']" /></div>			 --%>
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
								<span class="erm-required"></span>
								<s:text name="label.time.zone"/>
							</th>
						</tr>
						<tr>
							<td id="extractionTimeTD" colspan="3">
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
 									<s:property
 										value="extractionTimeLabel" />
 										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 									</td>
 									
 									<s:if test="%{(#sts.index+1)%8 == 0}">
 										</tr>
 									</s:if>
									</s:iterator>
								</table>
								<div class="has-error" id="extractionTimeTDError"><s:property value="errors['errorTimeToExtract']" /></div>
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
					
					<div id="settingTableArea">
<%-- 					<s:hidden name="extractionForm.countryCode" id="countryCode"/> --%>
<%-- 					<h5><strong><s:text  name="label.pair.currency"/> is :</strong> <span  id="pairCurrencyCode"></span></h5> --%>
					
					<s:if test="extractionForm.extractionType == 2">
						<div id="hideSettingTable1" style="display:none;">
							<s:include value="setting_table_type1.jsp"/>
						</div>
						<div id="hideSettingTable2" style="display:;">
							<s:include value="setting_table_type2.jsp"/>
						</div>
					</s:if>
					<s:else>
						<div id="hideSettingTable1" style="display:;">
							<s:include value="setting_table_type1.jsp"/>
						</div>
						<div id="hideSettingTable2" style="display:none;">
							<s:include value="setting_table_type2.jsp"/>
						</div>
					</s:else>
					
<%-- 					<s:if test="extractionForm.extractionType == 2"> --%>
<%-- 						<s:include value="setting_table_type2.jsp"/> --%>
<%-- 					</s:if>	 --%>
<%-- 					<s:elseif test="extractionFrom.extractionType == 3"> --%>
<%-- 						<s:include value="setting_table_type3.jsp"/> --%>
<%-- 					</s:elseif> --%>
<%-- 					<s:else> --%>
<%-- 						<s:include value="setting_table_type1.jsp"/> --%>
<%-- 					</s:else> --%>
					</div>
					
					<div>
						<s:iterator value="extractionForm.extractionDetailFormRemoveList" status="sts">
							<s:hidden
								name="extractionForm.extractionDetailFormRemoveList[%{#sts.index}]"
							/>
						</s:iterator>
					</div>

					</div><!-- /form-panel -->
          		</div><!-- /col-lg-12 -->
          	</div><!-- /row --> 

        	<table style="width:100%" align="center">
				<tr>
					<td align="center">
						<s:url var="cancleURL" action="prepareManageExtraction"/>
			            <s:a href="%{cancleURL}">
							<button type="button" style="width:150px" class="btn btn-default btn-sm" title="<s:text name='btn.cancel'/>">
									<span class="glyphicon glyphicon-chevron-left"></span>
									<span><s:text name='btn.cancel'/></span>
							</button>
						</s:a>
						<button style="width:150px" type="button" class="btn btn-primary btn-sm" onclick="save();" title="<s:text name='btn.save'/>">
							<span class="glyphicon glyphicon-floppy-disk"></span>
							<span><s:text name='btn.save'/></span>
						</button>
						<button style="width:150px" type="button" class="btn btn-theme03 btn-sm" onclick="preview();" title="<s:text name='btn.preview'/>">
							<span class="glyphicon glyphicon-zoom-in"></span>
							<span><s:text name='btn.preview'/></span>
						</button>
					</td>
				</tr>
			</table>	
			
			<br/>
			<br/>
	</s:form>
	
<script src="<s:url value='/resources/assets/js/select2.min.js' />"></script>   
<script src="<s:url value="/resources/assets/moment-master/min/moment.min.js"/>"></script>
<script src="<s:url value="/resources/assets/js/bootbox.min.js"/>"></script> 
<script src="<s:url value="/resources/assets/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"/>"></script>
<script src="<s:url value="/resources/assets/js/jquery-loading-overlay-1.5.3/src/loadingoverlay.min.js"/>"></script>

<script type ="text/javascript">
  	
 	var settingTableArea = "";
 	var settingTable = "";
 	var currentExtractionType = "";
 	var currentIndexRemoveDetail = 0;
//  	var ddlCurrencyAPIPool = "";
//  	var ddlSelectedCurrencyAPI = "";
 	var resultForTable = "";
 	
 	$(document).ready(function(){
 		settingTableArea = $("#settingTableArea");
    	currentExtractionType = $("#extractionType").val();
 		if(currentExtractionType == 1){
 	    	settingTable = $("#settingTable");
 		} 
 		else if(currentExtractionType == 2){
 	    	settingTable = $("#settingTable2");
 		}
//     	ddlCurrencyAPIPool = $("#ddlCurrencyAPIPool");
//     	ddlSelectedCurrencyAPI = $("#ddlSelectedCurrencyAPI");
    	resultForTable = $("#resultForTable").val();
    	$('select').select2();

 	});
 
 	function insertTableRow(table){ 		
 		
 		$("select").select2('destroy');
 		
 		var lastRow = $(table).find("tr:last")
 		var tempLastRow = $(lastRow).clone();
 		
 		$(tempLastRow).find("select").each(function(i,obj){
 			$(obj).val("-1");
 		});
 		
 		$(tempLastRow).find("input[type=text]").each(function(i,obj){
 			$(obj).val("");
 		});
 		
 		$(tempLastRow).find("input[type=checkbox]").each(function(i,obj){
 			$(obj).attr('checked', false);
 			$(obj).removeAttr("disabled");
 		});
 		
 		$(tempLastRow).find("input[type=hidden]").each(function(i,obj){
 			$(obj).val("0");
 		});
 		
 		$(tempLastRow).find("div").each(function(i,obj){
 			$(obj).html("");
 		});
 		
 		$(tempLastRow).insertAfter(lastRow);
 		
 		reconcileTableRow(table);
 		
 		$('select').select2();
 	}
 	
 	function deleteTableRow(table){
 		$(table).find("tr").each(function(i,obj){
			if(i > 1){
				var isChecked = $(obj).find("input[type=checkbox]").is(':checked');
				console.log(isChecked);
				if(isChecked){
		 			$('#extractionForm').attr('action','removeComposeExtractionDetailForm.action').submit();
				}
			}
 		});
 	}
 	
 	function reconcileTableRow(table){
 		var runningIndex = 0;
 		$(table).find("tr").each(function(i,obj){
			if(i > 1){
				var allTDsTag = $(obj).find("td");
				if($(allTDsTag).length == 8){
					allTDsTag.each(function(i2,obj2){
						var objectNewIndex = "extractionForm.extractionDetailFormList[" + runningIndex + "]";
						switch(i2){
						case 1: 
							objectNewIndex = objectNewIndex + ".currency";
							break;
						case 2:
							objectNewIndex = objectNewIndex + ".value";
							break;
						case 3:
							objectNewIndex = objectNewIndex + ".extractionCurrency";
							break;
						case 4:
							objectNewIndex = objectNewIndex + ".extractionBuyingRate"
							break;
						case 5:
							objectNewIndex = objectNewIndex + ".extractionSellingRate"
							break;
						case 6:
							objectNewIndex = objectNewIndex + ".chk"
							break;
						case 7:
							objectNewIndex = objectNewIndex + ".id"
							break;
						}
						$(obj2).children(":first").attr("name",objectNewIndex);
						$(obj2).children(":first").attr("id",objectNewIndex);
		 			});
					runningIndex++;
				}
				else if($(allTDsTag).length == 7){
					allTDsTag.each(function(i2,obj2){
						var objectNewIndex = "extractionForm.extractionDetailFormList[" + runningIndex + "]";
						switch(i2){
						case 1: 
							objectNewIndex = objectNewIndex + ".currency";
							break;
						case 2:
							objectNewIndex = objectNewIndex + ".value";
							break;
						case 3:
							objectNewIndex = objectNewIndex + ".extractionBuyingRate"
							break;
						case 4:
							objectNewIndex = objectNewIndex + ".extractionSellingRate"
							break;
						case 5:
							objectNewIndex = objectNewIndex + ".chk"
							break;
						case 6:
							objectNewIndex = objectNewIndex + ".id"
							break;
						}
						$(obj2).children(":first").attr("name",objectNewIndex);
						$(obj2).children(":first").attr("id",objectNewIndex);
		 			});
					runningIndex++;
				}
			}
 		});
 	}
 	
    function save(){

    	if(validateForm()){
        	$('#extractionForm').attr('action','composeExtraction.action').submit();
        	$.LoadingOverlay("show");
    	}
	}
    
    function validateForm(){
    	    	
 		$("select").select2('destroy');
 		
    	var bankNameVal = $("#bankName").val();
    	var bankShortNameVal = $("#bankShortName").val();
    	var countryComboBoxVal = $("#countryComboBox").val();
    	var baseCurrencyComboBoxVal = $("#baseCurrencyComboBox").val();
    	var urlVal = $("#url").val();
    	var formatDateVal = $("#formatDate").val();
    	var cssDateVal = $("#cssDate").val();
    	var pairCurrencyTypeVal = $("#pairCurrencyType").val();
    	var extractionDateVal = $("#extractionDate").val();
//     	var pageTypeVal = $("#pageType").val();
    	var extractionTimeTDVal = $("#extractionTimeTD").find("input[type=checkbox]");
    	var settingTableVal = $(settingTable).find("tr");

    	var isBankNameValid = true;
    	if(!bankNameVal || 0 === bankNameVal.trim().length){
    		isBankNameValid = false;
    		$("#bankNameError").html("Bank Name can't be empty");
    	}
    	else{
    		$("#bankNameError").html("");
    	}
    	
    	var isBankShortNameValid = true;
		if(!bankShortNameVal || 0 === bankShortNameVal.trim().length){
			isBankShortNameValid = false;
			$("#bankShortNameError").html("Bank Short Name can't be empty");
    	}
		else{
    		$("#bankShortNameError").html("");
    	}
		
		var isCountryComboBoxValid = true;
		if(!countryComboBoxVal || -1 == countryComboBoxVal){
			isCountryComboBoxValid = false;
			$("#countryComboBoxError").html("Country must be selected");
    	}
		else{
    		$("#countryComboBoxError").html("");
    	}
		
		var isBaseCurrencyComboBoxValid = true;
		if(!baseCurrencyComboBoxVal || -1 == baseCurrencyComboBoxVal){
			isBaseCurrencyComboBoxValid = false;
			$("#baseCurrencyComboBoxError").html("Base Currency must be selected");
    	}
		else{
    		$("#baseCurrencyComboBoxError").html("");
    	}
		
		var isUrlValid = true;
		if(!urlVal || 0 === urlVal.trim().length){
			isUrlValid = false;
			$("#urlError").html("Url can't be empty");
    	}
		else{
    		$("#urlError").html("");
    	}
		
		var isFormatDateValid = true;
		if(!formatDateVal || -1 == formatDateVal){
			isFormatDateValid = false;
			$("#formatDateError").html("Format Date must be selected");
    	}
		else{
    		$("#formatDateError").html("");
    	}
		
		var isCssDateValid = true;
		if(!$("#cssDate").is(":disabled") && (!cssDateVal || 0 === cssDateVal.trim().length)){
			isCssDateValid = false;
			$("#cssDateError").html("CSS Date can't be empty");
    	}
		else{
    		$("#cssDateError").html("");
    	}
		
		var isPairCurrencyTypeValid = true;
		if(!pairCurrencyTypeVal || -1 == pairCurrencyTypeVal){
			isPairCurrencyTypeValid = false;
			$("#pairCurrencyTypeError").html("Pair Currency Type must be selected");
    	}
		else{
    		$("#pairCurrencyTypeError").html("");
    	}
		
		var isExtractionDateValid = true;
		if(!extractionDateVal || -1 == extractionDateVal){
			isExtractionDateValid = false;
			$("#extractionDateError").html("Extraction Date must be selected");
    	}
		else{
    		$("#extractionDateError").html("");
    	}
		
// 		var isPageTypeValid = true;
// 		if(!pageTypeVal || -1 == pageTypeVal){
// 			isPageTypeValid = false
// 			$("#pageTypeError").html("Page Type must be selected");
//     	}
// 		else{
//     		$("#pageTypeError").html("");
//     	}
		
		var isExtractionTimeValid = true;
		var isChecked = false;
		$(extractionTimeTDVal).each(function(i,obj){
			if($(obj).is(":checked")){
				isChecked = true;
				return -1;
			}
		});
		if(!isChecked){
			isExtractionTimeValid = false;
			$("#extractionTimeTDError").html("Extraction time must be checked at least one");
		}
		else{
    		$("#extractionTimeTDError").html("");
    	}
		
		var isSettingTableValid = true;
		$(settingTableVal).each(function(i,obj){
			if(i > 1){
				var tdLength = $(obj).find("td").length;
				if(tdLength == 8){
					var isCurrencyValid = true;
					var isValueValid = true;
					var isCssCurrencyValid = true;
					var isBuyingRateValid = true;
					var isSellingRateValid = true;
					$(obj).find("td").each(function(i2,obj2){
						switch(i2){
						case 1:
							var inputField = $(obj2).find("select");
							var errorDiv = $(obj2).find("div.has-error");
							if(!$(inputField).val() || $(inputField).val() == -1){
								isCurrencyValid = false;
								$(errorDiv).html("Currency must be selected");
							}
							else{
								$(errorDiv).html("");
							}
							break;
						case 2:
							var inputField = $(obj2).find("select");
							var errorDiv = $(obj2).find("div.has-error");
							if(!$(inputField).val() || $(inputField).val() == -1){
								isValueValid = false
								$(errorDiv).html("Value must be selected");
							}
							else{
								$(errorDiv).html("");
							}
							break;
						case 3:
// 							var inputField = $(obj2).find("input[type=text]");
// 							var errorDiv = $(obj2).find("div.has-error");
// 							if(!$(inputField).val() || $(inputField).val().trim().length === 0){
// 								isCssCurrencyValid = false;
// 								$(errorDiv).html("CSS Selector for Currency cant be empty");
// 							}
// 							else{
// 								$(errorDiv).html("");
// 							}
							break;
						case 4:
							var inputField = $(obj2).find("input[type=text]");
							var errorDiv = $(obj2).find("div.has-error");
							if(!$(inputField).val() || $(inputField).val().trim().length === 0){
								isBuyingRateValid = false;
// 								$(errorDiv).html("CSS Selector for Buying Rate cant be empty");
							}
							else{
								$(errorDiv).html("");
							}
							break;
						case 5:
							var inputField = $(obj2).find("input[type=text]");
							var errorDiv = $(obj2).find("div.has-error");
							if(!$(inputField).val() || $(inputField).val().trim().length === 0){
								isSellingRateValid = false;
// 								$(errorDiv).html("CSS Selector for Selling Rate cant be empty");
							}
							else{
								$(errorDiv).html("");
							}
							break;
						}
					});
					
					if(!isCurrencyValid || !isValueValid || !isCssCurrencyValid ){
						isSettingTableValid = false;
					}
					else if(!isBuyingRateValid && !isSellingRateValid){
						var errorDiv = $(obj).find("div.has-error:last");
						$(errorDiv).html("CSS Selector for Buying Rate cant be empty in association with Selling Rate");
						isSettingTableValid = false;
					}
					
				}
				else if(tdLength == 7){
					var isCurrencyValid = true;
					var isValueValid = true;
					var isBuyingRateValid = true;
					var isSellingRateValid = true;
					$(obj).find("td").each(function(i2,obj2){
						switch(i2){
						case 1: 
							var inputField = $(obj2).find("select");
							var errorDiv = $(obj2).find("div.has-error");
							if(!$(inputField).val() || $(inputField).val() == -1){
								isCurrencyValid = false;
								$(errorDiv).html("Currency must be selected");
							}
							else{
								$(errorDiv).html("");
							}
							break;
						case 2:
							var inputField = $(obj2).find("select");
							var errorDiv = $(obj2).find("div.has-error");
							if(!$(inputField).val() || $(inputField).val() == -1){
								isValueValid = false
								$(errorDiv).html("Value must be selected");
							}
							else{
								$(errorDiv).html("");
							}
							break;
						case 3:
							var inputField = $(obj2).find("input[type=text]");
							var errorDiv = $(obj2).find("div.has-error");
							if(!$(inputField).val() || $(inputField).val().trim().length === 0){
								isSellingRateValid = false;
// 								$(errorDiv).html("CSS Selector for Selling Rate cant be empty");
							}
							else{
								$(errorDiv).html("");
							}
							break;
						case 4:
							var inputField = $(obj2).find("input[type=text]");
							var errorDiv = $(obj2).find("div.has-error");
							if(!$(inputField).val() || $(inputField).val().trim().length === 0){
								isSellingRateValid = false;
// 								$(errorDiv).html("CSS Selector for Selling Rate cant be empty");
							}
							else{
								$(errorDiv).html("");
							}
							break;
						}
					});
					
					if(!isCurrencyValid || !isValueValid){
						isSettingTableValid = false;
					}
					else if(!isBuyingRateValid && !isSellingRateValid){
						var errorDiv = $(obj).find("div.has-error:last");
						$(errorDiv).html("CSS Selector for Buying Rate cant be empty in association with Selling Rate");
						isSettingTableValid = false;					
					}
				}
				else{
					isSettingTableValid = false;
				}
			}
		});
		
//     	console.log("bankName : " + bankNameVal)
//     	console.log("bankShortNameName : " + bankShortNameVal)
//     	console.log("countryComboBox : " + countryComboBoxVal)
//     	console.log("url : " + urlVal)
//     	console.log("formatDate : " + formatDateVal)
//     	console.log("cssDate : " + cssDateVal)
//     	console.log("pairCurrencyType : " + pairCurrencyTypeVal)
//     	console.log("extractionDate : " + extractionDateVal)
//     	console.log("pageType : " + pageTypeVal)
//     	console.log("extractionTimeTD : " + extractionTimeTDVal)

 		$('select').select2();
    	
//     	if(isBankNameValid && isBankShortNameValid && isCountryComboBoxValid && isBaseCurrencyComboBoxValid
//     			&& isUrlValid && isFormatDateValid && isCssDateValid
//     			&& isPairCurrencyTypeValid && isExtractionDateValid && isPageTypeValid
//     			&& isExtractionTimeValid && isSettingTableValid){
		if(isBankNameValid && isBankShortNameValid && isCountryComboBoxValid && isBaseCurrencyComboBoxValid
    			&& isUrlValid && isFormatDateValid && isCssDateValid
    			&& isPairCurrencyTypeValid && isExtractionDateValid
    			&& isExtractionTimeValid && isSettingTableValid){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    function onChangeExtractionType(obj){
    	
    	currentExtractionType = $("#extractionType").val();
 		if(currentExtractionType == 1){
 			$("#cssDate").removeAttr("disabled");
 	    	settingTable = $("#settingTable");
 	    	$("#hideSettingTable1").css("display","");
	    	$("#hideSettingTable2").css("display","none");
 		} 
 		else if(currentExtractionType == 2){
 			$("#cssDate").val("");
 			$("#cssDate").attr("disabled","disabled");
 	    	settingTable = $("#settingTable2");
 	    	$("#hideSettingTable1").css("display","none");
 	    	$("#hideSettingTable2").css("display","");
 		}
    	
 		$("select").select2('destroy');
 		$("select").select2();
 		
//     	$('#extractionForm').attr('action','changeExtractionType.action').submit();
    	
//     	$.LoadingOverlay("show");
    }
        
    function preview(){

    	$('#extractionForm').attr('action','previewExtraction.action').submit();
    	
    	$.LoadingOverlay("show");
    }

	function onClickAPIAddButton(pool,selectedPool){
		var allOptionSelected = $("#" + pool.id + " option:selected");
		var selectedCurrencyValue = $(allOptionSelected).map(function(){
			return $(this).val();
		}).get();
		var selectedCurrencyText = $(allOptionSelected).map(function(){
			return $(this).text();
		}).get();
		
		for(i=0;i<selectedCurrencyValue.length;i++){
			$(selectedPool).append($('<option>',{value : selectedCurrencyValue[i], text : selectedCurrencyText[i]}));
		}
		
		$(allOptionSelected).each(function(){
			$(this).remove();
		});
		
		console.log(selectedCurrencyValue);
		console.log(selectedCurrencyText)

	}

	function onClickAPIAddAllButton(pool,selectedPool){
		var allOptions = $("#" + pool.id + " option");
		var selectedCurrencyValue = $(allOptions).map(function(){
			return $(this).val();
		}).get();
		var selectedCurrencyText = $(allOptions).map(function(){
			return $(this).text();
		}).get();
		
		for(i=0;i<selectedCurrencyValue.length;i++){
			$(selectedPool).append($('<option>',{value : selectedCurrencyValue[i], text : selectedCurrencyText[i]}));
		}
		
		$(allOptions).each(function(){
			$(this).remove();
		});
		
		console.log(selectedCurrencyValue);
		console.log(selectedCurrencyText)
	}
	
	function onClickAPIRemoveButton(pool,selectedPool){
		var allOptionSelected = $("#" + selectedPool.id + " option:selected");
		var selectedCurrencyValue = $(allOptionSelected).map(function(){
			return $(this).val();
		}).get();
		var selectedCurrencyText = $(allOptionSelected).map(function(){
			return $(this).text();
		}).get();
		
		for(i=0;i<selectedCurrencyValue.length;i++){
			$(pool).append($('<option>',{value : selectedCurrencyValue[i], text : selectedCurrencyText[i]}));
		}
		
		$(allOptionSelected).each(function(){
			$(this).remove();
		});
		
		console.log(selectedCurrencyValue);
		console.log(selectedCurrencyText)
	}
	
	function onClickAPIRemoveAllButton(pool,selectedPool){
		var allOptions = $("#" + selectedPool.id + " option");
		var selectedCurrencyValue = $(allOptions).map(function(){
			return $(this).val();
		}).get();
		var selectedCurrencyText = $(allOptions).map(function(){
			return $(this).text();
		}).get();
		
		for(i=0;i<selectedCurrencyValue.length;i++){
			$(pool).append($('<option>',{value : selectedCurrencyValue[i], text : selectedCurrencyText[i]}));
		}
		
		$(allOptions).each(function(){
			$(this).remove();
		});
		
		console.log(selectedCurrencyValue);
		console.log(selectedCurrencyText)
	}
	
	function sortAPICurrencyPool(pool, selectedPool){
		var allOptionsPool = $("#" + pool.id + " option");
		var allOptionsSelectedPool = $("#" + selectedPool.id + " option");

		var arr = allOptionsPool.map(function(_, o) { return { t: $(o).text(), v: o.value }; }).get();
		arr.sort(function(o1, o2) { return o1.t > o2.t ? 1 : o1.t < o2.t ? -1 : 0; });
		allOptionsPool.each(function(i, o) {
		  o.value = arr[i].v;
		  $(o).text(arr[i].t);
		});
		
		var arr = allOptionsSelectedPool.map(function(_, o) { return { t: $(o).text(), v: o.value }; }).get();
		arr.sort(function(o1, o2) { return o1.t > o2.t ? 1 : o1.t < o2.t ? -1 : 0; });
		allOptionsSelectedPool.each(function(i, o) {
		  o.value = arr[i].v;
		  $(o).text(arr[i].t);
		});
	}
	
	
	if($("#baseCurrencyComboBox").val() == '-1'){
       	$("[data-id=baseCurrencyCode]").text(""); 
	}
	else{
		$("[data-id=baseCurrencyCode]").text($("#baseCurrencyComboBox").children("option").filter(":selected").text());
	}
// 	$("[data-id=baseCurrencyCode]").text($("#baseCurrencyComboBox").children("option").filter(":selected").text());
	$('#countryComboBox').on('change',function(e){
	    var countryId = $(this).val();
	    if(countryId == '-1'){
	    	
	    }
	    else{
	    	jQuery.ajax({
		    	type:"GET",
		    	url:"erm/countryByIdAPIAction?parm="+countryId,
		    	contentType: "application/json; charset=utf-8",
		        dataType: "json",
		        success: function (dataApi, status, jqXHR) {
//	 	        	console.log(dataApi);
//	 	        	$("#countryCode").val(dataApi.currency.code);
		        	$("#baseCurrencyComboBox").select2('destroy');
		        	$("#baseCurrencyComboBox").val(dataApi.currency.id);
		        	$("#baseCurrencyComboBox").select2();
		        	$("[data-id=baseCurrencyCode]").text(dataApi.currency.code); 
		        }
		    });
	    }  
	});
	$('#baseCurrencyComboBox').on('change',function(e){
		if($(this).val() == '-1'){
	       	$("[data-id=baseCurrencyCode]").text(""); 
		}
		else{
	       	$("[data-id=baseCurrencyCode]").text($(this).children("option").filter(":selected").text()); 
		}
//        	$("#countryCode").val($(this).children("option").filter(":selected").text());
	});
	 
 </script>