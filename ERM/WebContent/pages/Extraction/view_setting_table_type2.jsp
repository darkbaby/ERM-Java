<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<table id="settingTable2" class="table table-striped table-advance table-hover" style="width:100%">
	  <tr>
	  	<th style="text-align: center;" rowspan="2"><s:text name="label.currency"/></th>
	  	<th style="text-align: center;" rowspan="2"><s:text name="label.value"/></th>
		<th style="text-align: center; width:70%;" colspan="2"><s:text name="label.number.of.tag.id"/></th>
	  	<th style="text-align: center;" rowspan="2"><s:text name="label.manage"/></th>
	  </tr>
	  <tr>
		<th style="text-align: center;"><s:text name="label.buying.rate"/></th>
		<th style="text-align: center;"><s:text name="label.selling.rate"/></th>			
	  </tr>
	 
	  
	  <s:iterator value="extractionForm.extractionDetailFormList" status="sts">	
	  <tr>
		<td style="text-align: center;">
			<s:property value="currencyCode"/>
		</td>
		<td style="text-align: center;">
			<s:property value="value"/>
		</td>
		<td style="text-align: center;">
			<s:property value="extractionBuyingRate"/>
		</td>
		<td style="text-align: center;">
			<s:property value="extractionSellingRate"/>
		</td>

	  </tr>
	  </s:iterator>
	 
	</table>