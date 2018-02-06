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
	<div class="bg-danger"><h3 class="text-white"><i class="subject-text glyphicon glyphicon-remove"></i><span class="subject-text"><s:text name="label.extraction.fail" /></span></h3></div>
   			          <table class="table table-bordered  table-striped table-hover" id="failTbl">
				                      <thead>
				                  
										  <tr>
										    <th rowspan="2"  ><s:text name="label.bank.name"/></th>
											<th rowspan="2"  ><s:text name="label.bank.country"/></th>
											<th rowspan="2"  ><s:text name="label.fail.description"/></th>
										  	<%-- <th rowspan="2"  ><s:text name="label.pair.currency"/></th> --%>
										  </tr>
				                      </thead>
				                      <tbody>
				                      	    <s:iterator value="exchangeRateFailList" status="hdrsts">
				                      	    	 
				                      	         
				                      	    	 <s:iterator value="extraction.extractionDetailList" status="dtlsts">  
				                      	    		<tr>
				                      	    			<s:if test="#dtlsts.index==0">
					                      	    			 <td style="text-align: left;" rowspan='<s:property value="%{[1].extraction.extractionDetailList.size()}"/>'>
					                      	    			 	<s:url var="viewExtractionUrl" action="prepareViewExtraction">
					                      	    			 		<s:param name="parm" value="%{[1].extraction.id}"/>
					                      	    			 	</s:url>
					                      	    			 	<s:a href="%{viewExtractionUrl}"> <s:property value="[1].extraction.bank.bankName"  /> </s:a>
					                      	    			 </td>
							                      	    	 <td style="text-align: left;" rowspan='<s:property value="%{[1].extraction.extractionDetailList.size()}"/>'><s:property value="[1].extraction.bank.country.countryName"  /></td>
							                      	         <td style="text-align: left;" rowspan='<s:property value="%{[1].extraction.extractionDetailList.size()}"/>'><s:property value="[1].logDescription" /></td>
							                      	         <%-- <td style="text-align: left;" rowspan='<s:property value="%{[1].extractionDetailList.size()}"/>'><s:property value="currency.code" /></td> --%>
				                      	    			</s:if>
				                      	    			<%-- <s:else>
				                      	    				<!--  <td style="text-align: left;border: 0;"> </td>
							                      	    	 <td style="text-align: left;border: 0;"> </td>
							                      	         <td style="text-align: left;border: 0;"> </td> -->
							                      	         <td style="text-align: left;border: 0;"> </td>
							                      	         <td style="text-align: left;border: 0;"> </td>
							                      	         <td style="text-align: left;border: 0;"> </td>
							                      	         
				                      	    			</s:else> --%>
				                      	    			<%-- <td style="text-align: left;"><s:property value="currency.code" /></td> --%>
				                      	    		    
					                      	    		 
				                      	    		</tr>
				                      	    	  </s:iterator>
				                      	    </s:iterator>
				                      </tbody>
				       </table>
					                
  
    
</body>
</html>