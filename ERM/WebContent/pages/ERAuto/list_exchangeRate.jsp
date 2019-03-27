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
</head>
 
 <body>	 
	<!--EXCHANGE RATE LIST -->
<!--           	exchange rate auto table -->
		
          	<div class="row mt">
          		<div class="col-lg-12">
          			<div class="form-panel">
                  	  <h4 class="mb"><i class="fa fa-cog"></i>
                  	  <span class="subject-text"><s:text name="header.list.exchange.rate.scraping.from.web"/></span>
                  	  </h4>
                  	  
   			          <table class="table table-bordered table-striped  table-hover" id="listRateTbl2" style="display:none;">
				                      <thead>
				                  
<!-- 										  <tr> -->
<%-- 										  	<th><s:text name="label.scraping.date"/></th> --%>
<%-- 										  	<th><s:text name="label.rate.date"/></th> --%>
<%-- 										  	<th><s:text name="label.bank.name"/></th> --%>
<%-- 										    <th><s:text name="label.bank.country"/></th> --%>
<%-- 											<th><s:text name="label.last.update"/></th> --%>
<%-- 											<th><s:text name="label.last.update.by"/></th> --%>
<%-- 											<th style="text-align: center;"><s:text name="label.option"/></th> --%>
											 
<!-- 										  </tr> -->
										  <tr>
										  	<th><s:text name="label.rate.date"/></th>
										  	<th><s:text name="label.ref.date"/></th>
										  	<th><s:text name="label.base.currency"/></th>
										  	<th><s:text name="label.pair.currency"/></th>
										  	<th><s:text name="label.buying.rate"/></th>
										  	<th><s:text name="label.selling.rate"/></th>
										  	<th><s:text name="label.type"/></th>
										  	<th><s:text name="label.bank.name"/></th>
											<th><s:text name="label.last.update"/></th>
											<th><s:text name="label.last.update.by"/></th>
											<th style="text-align: center;"><s:text name="label.option"/></th>
											 
										  </tr>
				                      </thead>
				                      <tbody>
				                      	<s:iterator var="hdr" value="rateList" status="hd">
				                      		<s:iterator var="dtl" value="#hdr.ExchangeRateDetails" status="hd2">
				                      			<%boolean isFail = false;%>
		                      	    		    <s:if test="%{#hdr.rateDate.getDate() != #hdr.refDate.getDate() 
		                      	    		    	|| #hdr.rateDate.getMonth() != #hdr.refDate.getMonth()
		                      	    		    	|| #hdr.rateDate.getYear() != #hdr.refDate.getYear()}">
		                      	    		   		<%isFail = true;%>
		                      	    		   	</s:if> 
				                      			<tr class='<%=isFail?"danger text-white":"" %>'>
                    	    		   					<td><span><s:date name="#hdr.rateDate" format="yyyy/MM/dd"/></span></td>
                    	    		   					<td><span><s:date name="#hdr.refDate" format="yyyy/MM/dd"/></span></td>
                    	    		   					<td><span><s:property value="#dtl.firstCurrency.code"/></span></td>
                    	    		   					<td><span><s:property value="#dtl.pairCurrency.code"/></span></td>
                    	    		   					<td><span><s:property value="#dtl.buyingRate"/></span></td>
                    	    		   					<td><span><s:property value="#dtl.sellingRate"/></span></td>
                    	    		   					<td><span><s:property value="#hdr.ownerType" /></span></td>
                    	    		   					<td><span><s:property value="#hdr.bankOfRate.iterator().next().bank.bankName"/></span></td>
                    	    		   					<td><span><s:date name="#hdr.lastUpdateDate" format="yyyy/MM/dd HH:mm:ss" /></span></td>
                    	    		   					<td><span><s:property value="#hdr.lastUpdateUser" /></span></td>
                      	    		   					<td style="text-align: center;">
			                      	    		    		<s:url var="editURL"   action="prepareEditExchangeRateAuto">
			                      	    		    	 		<s:param name="parmID" value="%{#hdr.id}" />   
			                      	    		    	 	</s:url>
				                      	    			  	<s:url var="viewURL"   action="prepareViewExchangeRateAutoFromAuto">
				                      	    			  		<s:param name="parmID" value="%{#hdr.id}" /> 
				                      	    			  	</s:url>
				                      	    			  	<s:url var="editURLM"   action="prepareEditExchangeRateByManualAction">
				                      	    			  		<s:param name="parmID" value="%{#hdr.id}" />  
				                      	    			  	</s:url>
				                      	    			  	<s:url var="viewURLM"   action="prepareViewExchangeRateByManualAction">
				                      	    			  		<s:param name="parmID" value="%{#hdr.id}" />  
				                      	    			  	</s:url>
				                      	    			  	
				                      	    			  	<s:if test='%{#hdr.ownerType == "AUTO"}'>
					                      	    			  	<modong:sys-permission function="PrepareViewExchangeRateAutoFromAuto" user="">
					                      	    			    <s:a href="%{viewURL}">
					                      	    			    	<button class="btn btn-success btn-sm" 
					                      	    			    			title="<s:text name="label.view.detail"/>">
					                      	    			    		<i class="fa fa-file"></i>
					                      	    			    	</button>
					                      	    			    </s:a>
					                      	    			    </modong:sys-permission>
					                      	    			    <modong:sys-permission function="PrepareEditExchangeRateAuto">
						                      	    			    <s:a href="%{editURL}">
						                      	    			    	<button class="btn btn-primary btn-sm" 
						                      	    			    			title="<s:text  name="msg.edit"/>">
						                      	    			    		<i class="glyphicon glyphicon-edit"></i>
						                      	    			    	</button>
						                      	    			    </s:a>
					                      	    			    </modong:sys-permission>
				                      	    			  	</s:if>
				                      	    			  	<s:else>
					                      	    			  	<s:a href="%{viewURLM}">
					                      	    			    	<button class="btn btn-success btn-sm" 
					                      	    			    			title="<s:text name="label.view.detail"/>">
					                      	    			    		<i class="fa fa-file"></i>
					                      	    			    	</button>
					                      	    			    </s:a>
					                   
					                      	    			    <modong:sys-permission function="PrepareEditExchangeRateByManualAction" user="${createdUser}" >
						                      	    			    <s:a href="%{editURLM}">
						                      	    			    	<button class="btn btn-primary btn-sm" 
						                      	    			    			title="<s:text  name="msg.edit"/>">
						                      	    			    		<i class="glyphicon glyphicon-edit"></i>
						                      	    			    	</button>
						                      	    			    </s:a>
					                      	    			    </modong:sys-permission>
				                      	    			  	</s:else>
				                      	    			     
		                      	    					</td>
		                      	    			</tr>
				                      		
				                      		</s:iterator>
				                      	
				                      	</s:iterator>
				                      
				                      
<%-- 				                      	    <s:iterator var="log" value="logList" status="hdrsts"> --%>
<%-- 				                      	    		   <s:if test="#log.logStatus=='FAIL'"> --%>
<%-- 				                      	    		   </s:if> --%>
<%-- 				                      	    		   <s:else> --%>
<!-- 				                      	    		   		<tr> -->
<%-- 						                      	    			<td><span class=""> <s:date name="createdDate" format="yyyy/MM/dd"/></span></td> --%>
<%-- 						                      	    			<td><span class=""> <s:date name="exchangeRateAuto.rateDate" format="yyyy/MM/dd"/></span></td> --%>
<%-- 						                      	    			<td><span class=""><s:property value="extraction.bank.bankName" /></span></td> --%>
<%-- 								                      	    	<td><span class=""><s:property value="extraction.bank.country.countryName"/></span></td> --%>
<%-- 								                      	    	<td><span class=""><s:date name="exchangeRateAuto.lastUpdateDate" format="yyyy/MM/dd HH:mm:ss"/></span></td> --%>
<%-- 								                      	    	<td><span class=""><s:property value="exchangeRateAuto.lastUpdateUser" /></span></td>                      	    			 --%>
<!-- 						                      	    			<td style="text-align: left;"> </td>
<!-- 						                      	    			<td style="text-align: left;"> </td> -->
<!-- 						                      	    			<td style="text-align: center;"> -->
<%-- 						                      	    		    	 <s:url var="editURL"   action="prepareEditExchangeRateAuto"> --%>
<%-- 						                      	    		    	 	<s:param name="parm" value="%{exchangeRateAuto.id}" />    --%>
<%-- 						                      	    		    	 </s:url> --%>
<%-- 							                      	    			  <s:url var="viewURL"   action="prepareViewExchangeRateAuto"> --%>
<%-- 							                      	    			  	<s:param name="parm" value="%{exchangeRateAuto.id}" />  --%>
<%-- 							                      	    			  </s:url> --%>
<%-- 							                      	    			  	<modong:sys-permission function="ViewExchangeRateAuto" user=""> --%>
<%-- 							                      	    			    <s:a href="%{viewURL}"> --%>
<!-- 							                      	    			    	<button class="btn btn-success btn-sm"  -->
<%-- 							                      	    			    			title="<s:text name="label.view.detail"/>"> --%>
<!-- 							                      	    			    		<i class="fa fa-file"></i> -->
<!-- 							                      	    			    	</button> -->
<%-- 							                      	    			    </s:a> --%>
<%-- 							                      	    			    </modong:sys-permission> --%>
<%-- 							                      	    			    <modong:sys-permission function="EditExchangeRateAuto"> --%>
<%-- 								                      	    			    <s:a href="%{editURL}"> --%>
<!-- 								                      	    			    	<button class="btn btn-primary btn-sm"  -->
<%-- 								                      	    			    			title="<s:text  name="msg.edit"/>"> --%>
<!-- 								                      	    			    		<i class="glyphicon glyphicon-edit"></i> -->
<!-- 								                      	    			    	</button> -->
<%-- 								                      	    			    </s:a> --%>
<%-- 							                      	    			    </modong:sys-permission> --%>
<!-- 						                      	    			</td>   -->
<!-- 						                      	    		</tr> -->
				                      	    		   
				                      	    		   
				                      	    		   		
<%-- 				                      	    		   </s:else>  --%>
<%-- 				                      	    </s:iterator> --%>
				                      </tbody>
				       </table>
	
          			</div><!-- /panel -->
          		</div><!-- /col-lg-12 -->
          	</div><!-- /row -->			          
          	
 <script type="text/javascript" src="<s:url value='/resources/assets/js/jquery.dataTables.js'/>"></script>
 <script type="text/javascript" src="<s:url value='/resources/assets/js/dataTables.bootstrap.js'/>"></script>
 <script type ="text/javascript">
  	
 	
 
 	$(document).ready(function(){
 		$('#listRateTbl2').dataTable({
 	  		searching: false,
 	  	 	"order": [[ 8, "desc" ]]
 	 	});	
 	    $('#listRateTbl2').css("display","");
 	});
  	
//   	function edit(val){
  		
//   	}
  
 </script>
    
</body>
</html>