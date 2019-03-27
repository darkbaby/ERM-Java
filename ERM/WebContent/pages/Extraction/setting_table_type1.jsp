<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div>
					<table align="right" style="margin-bottom:5px">
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
                    </table>
                      
                    </div>

    
<table id="settingTable" class="table table-striped table-advance table-hover" style="width:100%">
	  <tr>
	  	<th style="text-align: center; width:7%; " rowspan="2"><s:text name="label.base.currency"/></th>
	  	<th style="text-align: center; " rowspan="2"><s:text name="label.pair.currency"/></th>
	  	<th style="text-align: center; " rowspan="2"><s:text name="label.value"/></th>
		<th style="text-align: center;" colspan="3"><s:text name="label.css.selector"/></th>
	  	<th style="text-align: center; width:5%;" rowspan="2"><s:text name="label.manage"/></th>
	  	<th style="display:none"></th>
	  </tr>
	  <tr>
		<th style="text-align: center; width:24%;"><s:text name="label.pair.currency"/></th>
		<th style="text-align: center; width:23%;"><s:text name="label.buying.rate"/></th>
		<th style="text-align: center; width:23%;"><s:text name="label.selling.rate"/></th>
	  </tr>
	  <s:iterator value="extractionForm.extractionDetailFormList" status="sts">	
	  <tr>
	  	<td style="text-align: center;">
			<span data-id="baseCurrencyCode"></span>
		</td>
		<td style="text-align: center;">
			<s:select 
			    class="form-control"
			    name="extractionForm.extractionDetailFormList[%{#sts.index}].currency"
			    id="extractionForm.extractionDetailFormList[%{#sts.index}].currency"
				list="currencyList"
				listKey="id"
				listValue="code"
				value="%{currency}"
				headerKey="-1"
				headerValue="---Please Select---"
			/>
			<div class="has-error"><s:property value="errors['s1errorCurrency'+#sts.index]" /></div>
			<div><s:property value="errors['s1Currency'+#sts.index]" /></div>
		</td>
		<td style="text-align: center;">
			<s:select
			    class="form-control"
				name="extractionForm.extractionDetailFormList[%{#sts.index}].value"
			    id="extractionForm.extractionDetailFormList[%{#sts.index}].value"
				list="valueList"
				listKey="value"
				listValue="descriptionShort"
				value="%{value}"
				headerKey="-1"
				headerValue="---Please Select---"
			/>
			<div class="has-error"><s:property value="errors['s1errorValue'+#sts.index]" /></div>
			<div><s:property value="errors['s1Value'+#sts.index]" /></div>
		</td>
		<td style="text-align: center;">
			<s:textfield
				class="form-control"
				cssStyle="width:100%;"
				name="extractionForm.extractionDetailFormList[%{#sts.index}].extractionCurrency"
			    id="extractionForm.extractionDetailFormList[%{#sts.index}].extractionCurrency"
			/>
			<div class="has-error"><s:property value="errors['s1errorExtractionCurrency'+#sts.index]" /></div>
			<div><s:property value="errors['s1ExtractionCurrency'+#sts.index]" /></div>
		</td>
		<td style="text-align: center;">
			<s:textfield
				class="form-control"
				cssStyle="width:100%;"
				name="extractionForm.extractionDetailFormList[%{#sts.index}].extractionBuyingRate"
			    id="extractionForm.extractionDetailFormList[%{#sts.index}].extractionBuyingRate"
			/>
			<div class="has-error"><s:property value="errors['s1errorExtractionBuyingRate'+#sts.index]" /></div>
			<div><s:property value="errors['s1ExtractionBuyingRate'+#sts.index]" /></div>
		</td>
		<td style="text-align: center;">
			<s:textfield
				class="form-control"
				cssStyle="width:100%;"
				name="extractionForm.extractionDetailFormList[%{#sts.index}].extractionSellingRate"
			    id="extractionForm.extractionDetailFormList[%{#sts.index}].extractionSellingRate"
			/>
			<div class="has-error"><s:property value="errors['s1errorExtractionSellingRate'+#sts.index]" /></div>
			<div><s:property value="errors['s1ExtractionSellingRate'+#sts.index]" /></div>
		</td>
		<s:if test="#sts.index == 0">
		<td style="text-align: center;">
			<s:checkbox
				class="custom-control-input"
				name="extractionForm.extractionDetailFormList[%{#sts.index}].chk"
			    id="extractionForm.extractionDetailFormList[%{#sts.index}].chk"
			    disabled="true"
			/>
		</td>
		</s:if>
		<s:else>
		<td style="text-align: center;">
			<s:checkbox
				class="custom-control-input"
				name="extractionForm.extractionDetailFormList[%{#sts.index}].chk"
			    id="extractionForm.extractionDetailFormList[%{#sts.index}].chk"
			/>
		</td>
		</s:else>
		<td style="display:none;">
			<s:hidden name="extractionForm.extractionDetailFormList[%{#sts.index}].id"/>
		</td>
	  </tr>
	  </s:iterator>
</table>
<div class="has-error"><s:property value="errors['errorSettingTable1-1']" /></div>
<div class="has-error"><s:property value="errors['errorSettingTable1-2']" /></div>
<div class="has-error"><s:property value="errors['errorTotal']" /></div>