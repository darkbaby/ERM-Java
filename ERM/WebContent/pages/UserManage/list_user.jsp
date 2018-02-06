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
   <link rel="stylesheet" href="<s:url value='/resources/assets/css/dataTables.bootstrap.css' />"/>
</head>
 
 <body>	 
	<!--EXCHANGE RATE LIST -->
          	<div class="row mt">
          		<div class="col-lg-12">
          			<div class="form-panel">
                  	  <h4 class="mb"><i class="fa fa-cog"></i><span class="subject-text"><s:text name="header.user.list"/></span></h4>
                  	  <table align="right" style="margin-bottom:5px">
	                      <tr>
	                      	<td  align="right" >
	                      		<s:url var="addURL" action="prepareCreateUser"></s:url>
	                      		<s:a href="%{addURL}">
		                      		<button type="button" class="btn btn-theme" style="width: 100px" title="<s:text name='msg.add.new.exchange.rate'/>">
		                      			<span class="glyphicon glyphicon-plus-sign"></span>
		                      			<span ><s:text name="btn.add"/></span>
		                      		</button>
	                      		</s:a>
	                      	</td>
	                      </tr>
                      </table>
                      <div>
                       
                      </div>
   			          <table class="table table-bordered table-striped  table-hover" id="listUserTbl">
				                      <thead>
				                  
										  <tr>
										  	<th><s:text name="user.logon.id"/></th>
										  	<th><s:text name="user.first.name"/></th>
										  	<th><s:text name="user.last.name"/></th>
										  	<th><s:text name="label.status"/></th>
										  	<th><s:text name="user.email"/></th>
										  	<th><s:text name="user.country"/></th>
										  	<th><s:text name="user.authorize.group"/></th>    
											<th><s:text name="label.last.update"/></th>
											<th><s:text name="label.last.update.by"/></th>
											<th style="text-align: center;"><s:text name="label.option"/></th>
										  </tr>
				                      </thead>
				                      <tbody>				                       
				                      	    <s:iterator value="userList" status="sts">
				                      	    		<tr>	    		   
				                      	    		 	<td style="text-align: left;"><s:property value="logOnId" /></td>				                      	    		 	
				                      	    			<td style="text-align: left;"><s:property value="firstName" /></td>            	    					                      	    			
				                      	    			<td style="text-align: left;"><s:property value="lastName"/></td>
				                      	    			<td style="text-align: left;">
				                      	    			    <s:if test='%{userList[#sts.index].recordStatus == "A"}'> 
				                      	    					<%=IPageContains.RECORD_STS_ACTIVE_CONTAIN %>
				                      	    				</s:if>
				                      	    				<s:if test='%{userList[#sts.index].recordStatus == "S"}'> 
				                      	    					<%=IPageContains.RECORD_STS_INACTIVE_CONTAIN %>
				                      	    				</s:if>
				                      	    			</td>	  
				                      	    			<td style="text-align: left;"><s:property value="emailAddress"/></td>
				                      	    			<td style="text-align: left;"><s:property value="country.countryName"/></td>
				                      	    			<td style="text-align: left;"><s:property value="authorizeGroup.groupName"/></td>
				                      	    			<td style="text-align: left;"><s:date name="lastUpdateDate" format="yyyy/MM/dd HH:mm:ss"/></td>
				                      	    			<td style="text-align: left;"><s:property value="lastUpdateUser" /></td>
				                      	    			<td style="text-align: center;"> 
				                      	    			  <s:url var="editURL"   action="prepareEditUser"><s:param name="parm" value="%{id}" />  </s:url>
															<s:if test='%{userList[#sts.index].recordStatus == "A"}'> 
																<s:url var="resetPwdURL"   action="prepareResetPwdUser"><s:param name="parm" value="%{id}" />  </s:url>
					                      	    			    <s:a href="%{resetPwdURL}">
					                      	    			    	<button class="btn btn-warning btn-sm" 
					                      	    			    			title="<s:text name="label.reser.pwd"/>">
					                      	    			    		<i class="glyphicon glyphicon-repeat"></i>
					                      	    			    	</button>
					                      	    			    </s:a>
					                      	    			</s:if>
				                      	    			    <s:a href="%{editURL}">
				                      	    			    	<button class="btn btn-primary btn-sm" 
				                      	    			    			title="<s:text  name="msg.edit"/>">
				                      	    			    		<i class="glyphicon glyphicon-edit"></i>
				                      	    			    	</button>
				                      	    			    </s:a>
				                      	    			    
				                      	    			</td>
				                      	    		</tr>
				                      	    	
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
 	    $('#listUserTbl').dataTable({
 	    	searching: false,
 	    	 "order": [[ 4, "desc" ]]
 	    	});
 	    
 	    $('#listRateTbl2').dataTable({
 	    	searching: false,
 	    	 "order": [[ 4, "desc" ]]
 	    	});
 	});
  	
  	function edit(val){
  		
  	}
  
 </script>
    
</body>
</html>