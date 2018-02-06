<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<table id="settingTable1" class="table table-striped table-advance table-hover table-bordered"  style="width:100%">
	  <tr>
	  	<th style="text-align: center;  width:2%" rowspan="2">#</th>
	  	<th style="text-align: center;  width:5%" rowspan="2"><s:text name="label.base.currency"/></th>
	  	<th style="text-align: center;  width:5%" rowspan="2"><s:text name="label.pair.currency"/></th>
	  	<th style="text-align: center; width:5%" rowspan="2"><s:text name="label.value"/></th>
		<th style="text-align: center; width:70%;" colspan="3"><s:text name="label.css.selector"/></th>
	  </tr>
	  
	  <tr>
	    <s:if test="extractionForm.extractionType==1">
			<th style="text-align: center;"><s:text name="label.pair.currency"/></th>
	    </s:if>
		<th style="text-align: center;"><s:text name="label.buying.rate"/></th>
		<th style="text-align: center;"><s:text name="label.selling.rate"/></th>			
	  </tr>
	 
	  <s:iterator value="extractionForm.extractionDetailFormList" status="sts" >	
	   <tr>
	   	<td style="text-align:left;">
			<s:property value="#sts.index+1"/>
		</td>
		<td style="text-align:left;">
			<s:property value="extractionForm.baseCurrencyName"/>
		</td>
		<td style="text-align:left;">
			<s:property value="currencyCode"/>
		</td>
		<td style="text-align:right;">
			<s:property value="value"/>
		</td>
		<s:if test="extractionForm.extractionType==1">
			<td style="text-align: left;">
				<s:property value="extractionCurrency"/>
			</td>
		</s:if>
		<td style="text-align: left;">
			<s:property value="extractionBuyingRate"/>
		</td>
		<td style="text-align: left;">
			<s:property value="extractionSellingRate"/>
		</td>
	  </tr>
	  </s:iterator>
  
	</table>