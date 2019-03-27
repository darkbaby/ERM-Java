<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<div><table align="right" style="margin-bottom:5px">
                      <tr>
                      	<td  align="right" >
                      		<button type="button" class="btn btn-theme" onclick="insertTableRow(settingTable)">
                      			<span class="glyphicon glyphicon-plus-sign"></span>
                      			<span ><s:text name="btn.add"/></span>
                      		</button>
                      		<button type="button" class="btn btn-danger" onclick="deleteTableRow(settingTable)" >
                      			<span class="glyphicon glyphicon-minus-sign"></span>
                      			<span ><s:text name="btn.remove"/></span>
                      		</button>
                      	</td>
                      </tr>
                      </table></div>    
    
<table id="settingTable2" class="table table-striped table-advance table-hover" style="width:100%">
	  <tr>
	  	<th style="text-align: center; width:7%; " rowspan="2"><s:text name="label.base.currency"/></th>
	  	<th style="text-align: center;" rowspan="2"><s:text name="label.pair.currency"/></th>
	  	<th style="text-align: center;" rowspan="2"><s:text name="label.value"/></th>
		<th style="text-align: center;" colspan="2"><s:text name="label.number.of.tag.id"/></th>
	  	<th style="text-align: center; width:5%" rowspan="2"><s:text name="label.manage"/></th>
	  	<th style="display:none"></th>
	  </tr>
	  <tr>
		<th style="text-align: center; width:35%;"><s:text name="label.buying.rate"/></th>
		<th style="text-align: center; width:35%;"><s:text name="label.selling.rate"/></th>			
	  </tr>
	  <s:iterator value="extractionForm.extractionDetailFormList2" status="sts">	
	  <tr>
	  	<td style="text-align: center;">
			<span data-id="baseCurrencyCode"></span>
		</td>
		<td style="text-align: center;">
			<s:select 
			    class="form-control"
			    name="extractionForm.extractionDetailFormList2[%{#sts.index}].currency"
			    id="extractionForm.extractionDetailFormList2[%{#sts.index}].currency"
				list="currencyList"
				listKey="id"
				listValue="code"
				headerKey="-1"
				headerValue="---Please Select---"
			/>
			<div class="has-error"><s:property value="errors['s2errorCurrency'+#sts.index]" /></div>
		</td>
		<td style="text-align: center;">
			<s:select
			    class="form-control"
				name="extractionForm.extractionDetailFormList2[%{#sts.index}].value"
			    id="extractionForm.extractionDetailFormList2[%{#sts.index}].value"
				list="valueList"
				listKey="value"
				listValue="descriptionShort"
				headerKey="-1"
				headerValue="---Please Select---"
			/>
			<div class="has-error"><s:property value="errors['s2errorValue'+#sts.index]" /></div>
		</td>
		<td style="text-align: center;">
			<s:textfield
				class="form-control"
				cssStyle="width:100%;"
				name="extractionForm.extractionDetailFormList2[%{#sts.index}].extractionBuyingRate"
			    id="extractionForm.extractionDetailFormList2[%{#sts.index}].extractionBuyingRate"
			/>
			<div class="has-error"><s:property value="errors['s2errorExtractionBuyingRate'+#sts.index]" /></div>
			<div><s:property value="errors['s2ExtractionBuyingRate'+#sts.index]" /></div>
		</td>
		<td style="text-align: center;">
			<s:textfield
				class="form-control"
				cssStyle="width:100%;"
				name="extractionForm.extractionDetailFormList2[%{#sts.index}].extractionSellingRate"
			    id="extractionForm.extractionDetailFormList2[%{#sts.index}].extractionSellingRate"
			/>
			<div class="has-error"><s:property value="errors['s2errorExtractionSellingRate'+#sts.index]" /></div>
			<div><s:property value="errors['s2ExtractionSellingRate'+#sts.index]" /></div>
		</td>
		<s:if test="#sts.index == 0">
		<td style="text-align: center;">
			<s:checkbox
				class="custom-control-input"
				name="extractionForm.extractionDetailFormList2[%{#sts.index}].chk"
			    id="extractionForm.extractionDetailFormList2[%{#sts.index}].chk"
			    disabled="true"
			/>
		</td>
		</s:if>
		<s:else>
		<td style="text-align: center;">
			<s:checkbox
				class="custom-control-input"
				name="extractionForm.extractionDetailFormList2[%{#sts.index}].chk"
			    id="extractionForm.extractionDetailFormList2[%{#sts.index}].chk"
			/>
		</td>
		</s:else>
		<td style="display:none;">
			<s:hidden name="extractionForm.extractionDetailFormList2[%{#sts.index}].id"/>
		</td>
	  </tr>
	  </s:iterator>
	</table>
	<div class="has-error"><s:property value="errors['errorSettingTable2-1']" /></div>
	<div class="has-error"><s:property value="errors['errorSettingTable2-2']" /></div>