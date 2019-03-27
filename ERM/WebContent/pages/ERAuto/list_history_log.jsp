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
     <link rel="stylesheet" href="<s:url value='/resources/assets/css/dataTables.bootstrap.css' />"/>
</head>
 
 <body>	 
	<!--EXCHANGE RATE LIST -->
          	<div class="row mt">
          		<div class="col-lg-12">
          			<div class="form-panel">
                  	  <h4 class="mb"><i class="fa fa-cog"></i><span class="subject-text"><s:text name="header.list.exchange.rate.scraping.from.web"/></span></h4>
   			        
   			         <table class="table table-bordered table-hover" id="listRateTbl" style="display:none;">
				                      <thead>
				                  
										  <tr>
										  	<th><s:text name="label.bank.name"/></th>
										  	<th><s:text name="label.bank.country"/></th>
										    <th><s:text name="label.extract.status"/></th>
											<th><s:text name="label.extract.status.description"/></th>
											<th><s:text name="label.extract.date.time2"/></th>
											<th  style="text-align: center;"><s:text name="label.option"/></th>
											 
										  </tr>
				                      </thead>
				                      <tbody>
				                      	    <s:iterator value="logList" status="hdrsts">
				                      	    		 <%boolean isFail = false;%>
				                      	    		   <s:if test="logStatus=='FAIL'">
				                      	    		   		<%isFail = true;%>
				                      	    		   </s:if> 
				                      	    		<tr class='<%=isFail?"danger text-white":"" %>'>
				                      	    			<td><span class=""><s:property value="extraction.bank.bankName"/></span></td>
				                      	    			<td><span class=""><s:property value="extraction.bank.country.countryName" /></span></td>
						                      	    	<td><span class=""><s:property value="logStatus"/></span></td>
						                      	    	<td><span class=""><s:property value="logDescription" /></span></td>
						                      	    	<td><span class=""><s:date name="createdDate" format="yyyy/MM/dd HH:mm:ss" /></span></td>                      	    			
				                      	    			<!-- <td style="text-align: left;"> </td>
				                      	    			<td style="text-align: left;"> </td> -->
				                      	    			<td style="text-align: center;">
					                      	    			<%if(!isFail){%>

																  <modong:sys-permission function="PrepareViewExchangeRateAutoFromAuto">
						                      	    			  <s:url var="viewURL"   action="prepareViewExchangeRateAutoFromAuto">
<%-- 						                      	    			  	<s:param name="parmID" value="%{exchangeRateAuto.id}" /> --%>
						                      	    			  	<s:param name="parmID" value="%{idExchangeRateAuto}" />
						                      	    			  	<s:param name="parmBackAction">prepareManageExchangeRateAuto</s:param>     	    			  	
						                      	    			  </s:url>
						                      	    			    <s:a href="%{viewURL}">
						                      	    			    	<button class="btn btn-success btn-sm" 
						                      	    			    			title="<s:text name="label.view.detail"/>">
						                      	    			    		<i class="fa fa-file"></i>
						                      	    			    	</button>
						                      	    			    </s:a>
						                      	    			   </modong:sys-permission>
						                      	    			<%}%>      	    		     
				                      	    			</td>  
				                      	    		</tr>
				                      	    </s:iterator>
				                      </tbody>
				       </table>  
	
          			</div><!-- /panel -->
          		</div><!-- /col-lg-12 -->
          	</div><!-- /row --> 
 
<%--  <script src="<s:url value='/resources/assets/js/bootstrap.min.js' /> "></script>	 --%>				                
 <script type="text/javascript" src="<s:url value='/resources/assets/js/jquery.dataTables.js'/>"></script>
 <script type="text/javascript" src="<s:url value='/resources/assets/js/dataTables.bootstrap.js'/>"></script>
  
 <script type ="text/javascript">
     
 	 $(document).ready(function () {
 		 
 		$("#listRateTbl").dataTable({
 	    	searching: false,
 	    	order:[[4,"desc"]],
 	    	columnDefs:[{
 	    		orderable: false, targets: [5], visible: true
 	    	}]
 	    });
 		
 		$("#listRateTbl").css("display","");

 	});
   
  
 </script>
    
</body>
</html>