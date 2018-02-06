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
 </head>
 
 <body>	 
	<!--EXCHANGE RATE LIST -->
	<div class="bg-success"  ><h3 class="text-white"><i class="subject-text glyphicon glyphicon-ok"></i><span class="subject-text"><s:text name="label.extraction.success" /></span></h3></div>
   			          <table class="table table-striped table-advance table-bordered table-hover"  id="listRateTbl">
				                      <thead>
				                  
										  <tr>
										   	<th><s:text name="label.first.currency"/></th>
										  	<th><s:text name="label.pair.currency"/></th>
										    <th><s:text name="label.type"/></th>
											<th><s:text name="label.bank.name"/></th>
											<th><s:text name="label.bank.country"/></th>
											<%-- <th><s:text name="label.value"/></th>
											<th><s:text name="label.buying.rate"/></th>
											<th><s:text name="label.selling.rate"/></th>
											<th><s:text name="label.avg.rate"/></th> --%>
										  </tr>
				                      </thead>
				                      <tbody>
				                      	    <s:iterator value="exchangeSuccessList" status="hdrsts">
				                      	    	  <s:iterator value="exchangeRateDetails" status="dtlsts">  
				                      	    		<tr>
				                      	    			<s:if test="#dtlsts.index==0">
				                      	    				<td style="text-align: left;" rowspan='<s:property value="%{[1].exchangeRateDetails.size()}"/>'><s:property value="%{[1].firstCurrency.code}" /></td>
					                      	    			<td style="text-align: left;" rowspan='<s:property value="%{[1].exchangeRateDetails.size()}"/>'><s:property value="%{[1].pairCurrency.code}" /></td>
				                      	    			</s:if>
				                      	    			<%-- <s:else>
				                      	    			   <td style="text-align: left;border: none;"> </td>
					                      	    		   <td style="text-align: left;border: none;"> </td>  
					                      	    			 
					                      	    		</s:else> --%>
					                      	    		<td style="text-align: left;"><s:property value="type" /></td>
				                      	    			 <s:if test="type==getText('msg.auto')">
				                      	    				 
				                      	    				<td style="text-align: left;"><s:property value="bank.bankName"/></td>
				                      	    				<td style="text-align: left;"><s:property value="bank.country.countryName"/></td>
					              
				                      	    			</s:if>
				                      	    			<s:else>
				                      	    				 
				                      	    				<td>-</td>
				                      	    				<td>-</td>
				                      	    			</s:else>
				                      	    			<%-- <td style="text-align: right;">
				                      	    					<s:property value="value"/>
				                      	    			</td>
				                      	    			<td style="text-align: right;">
				                      	    				<s:text name="format.number.exchange.rate">
				                      	    					<s:param name="value" value="buyingRate"/>
				                      	    				</s:text>
				                      	    			</td>
				                      	    			<td style="text-align: right;">
				                      	    				<s:text name="format.number.exchange.rate">
				                      	    					<s:param name="value" value="sellingRate"/>
				                      	    				</s:text>
				                      	    			</td>
				                      	    			<td style="text-align: right;">
				                      	    				<s:text name="format.number.exchange.rate">
				                      	    					<s:param name="value" value="avgRate"/>
				                      	    				</s:text>  
				                      	    			</td>  --%>
				                      	    		</tr>
				                      	    	  </s:iterator>  
				                      	    </s:iterator>
				                      </tbody>
				       </table>
 
</body>
</html>