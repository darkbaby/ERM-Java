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
          	<div class="row mt">
          		<div class="col-lg-12">
          			<div class="form-panel">
                  	  <h4 class="mb"><i class="fa fa-cog"></i><span class="subject-text"><s:text name="header.list.exchange.rate"/></span></h4>
                  	  <modong:sys-permission function="CreateExchangeRateManual">
	                  	  <table align="right" style="margin-bottom:5px">
		                      <tr>
		                      	<td  align="right" >
		                      		<s:url var="addURL" action="prepareCreateExchangeRateManualAction"></s:url>
		                      		<s:a href="%{addURL}">
			                      		<button type="button" class="btn btn-theme" style="width: 100px" title="<s:text name='msg.add.new.exchange.rate'/>">
			                      			<span class="glyphicon glyphicon-plus-sign"></span>
			                      			<span ><s:text name="btn.add"/></span>
			                      		</button>
		                      		</s:a>
		                      	</td>
		                      </tr>
	                      </table>
                      </modong:sys-permission>
                      <div>
                       
                      </div>
   			          <table class="table table-bordered table-striped  table-hover" id="listRateTbl">
				                      <thead>
				                  
										  <tr>
										  	<th><s:text name="label.date"/></th>
										  	<th><s:text name="label.first.currency"/></th>
										  	<th><s:text name="label.pair.currency"/></th>	    
											<th><s:text name="label.last.update"/></th>
											<th><s:text name="label.last.update.by"/></th>
											<th style="text-align: center;"><s:text name="label.option"/></th>
											 
										  </tr>
				                      </thead>
				                      <tbody>
				                      	    <s:iterator value="exchangeRateManualList" status="hdrsts">
				                      	    	<s:iterator value="exchangeRateDetails" status="dtlsts">
				                      	    		<tr>
				                      	    		 <td style="text-align: left;"><s:date name="[1].rateDate" format="yyyy/MM/dd"/></td>
				                      	    		 	<td style="text-align: left;"><s:property value="firstCurrency.code" /></td>
				                      	    			<td style="text-align: left;"><s:property value="pairCurrency.code" /></td>            	    					                      	    			
				                      	    			<td style="text-align: left;"><s:date name="[1].lastUpdateDate" format="yyyy/MM/dd HH:mm:ss"/></td>
				                      	    			<td style="text-align: left;"><s:property value="[1].lastUpdateUser"/></td>
				                      	    			<td style="text-align: center;"> 
				                      	    			  <s:url var="editURL"   action="prepareEditExchangeRateByManualAction"><s:param name="parm" value="%{[1].id}" />  </s:url>
				                      	    			  <s:url var="viewURL"   action="prepareViewExchangeRateByManualAction"><s:param name="parm" value="%{[1].id}" />  </s:url>
				                      	    			    <s:a href="%{viewURL}">
				                      	    			    	<button class="btn btn-success btn-sm" 
				                      	    			    			title="<s:text name="label.view.detail"/>">
				                      	    			    		<i class="fa fa-file"></i>
				                      	    			    	</button>
				                      	    			    </s:a>
				                      	    			  
				                      	    			    <modong:sys-permission function="EditExchangeRateManual" user="${createdUser}" >
					                      	    			    <s:a href="%{editURL}">
					                      	    			    	<button class="btn btn-primary btn-sm" 
					                      	    			    			title="<s:text  name="msg.edit"/>">
					                      	    			    		<i class="glyphicon glyphicon-edit"></i>
					                      	    			    	</button>
					                      	    			    </s:a>
				                      	    			    </modong:sys-permission>
				                      	    			    
				                      	    			</td>
				                      	    		</tr>
				                      	    	
				                      	    	</s:iterator>
				                      	    </s:iterator>
				                      </tbody>
				       </table>
	
          			</div><!-- /panel -->
          		</div><!-- /col-lg-12 -->
          	</div><!-- /row -->
          			                
 <script type="text/javascript" src="<s:url value='/resources/assets/js/jquery.dataTables.js'/>"></script>
 <script type="text/javascript" src="<s:url value='/resources/assets/js/dataTables.bootstrap.js'/>"></script>
 <script type ="text/javascript">
  	$(document).ready(function(){
 	    $('#listRateTbl').dataTable({
 	    	searching: false,
 	    	 "order": [[ 3, "desc" ]]
 	    	});
 	});
  	
  	function edit(val){
  		
  	}
  
 </script>
    
</body>
</html>