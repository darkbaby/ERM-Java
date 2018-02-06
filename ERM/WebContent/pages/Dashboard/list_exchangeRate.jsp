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
                  	  <h4 class="mb"><i class="fa fa-cog"></i><span class="subject-text"><s:text name="header.list.exchange.rate"/></span><span class="subject-text"><s:date name="dateOfRate" format='%{getText("ui.struts.date.format")}'/></span></h4>
   			           <s:if test="%{exchangeSuccessList.size() > 0 }">
   			           		<s:include value="list_exchangeRate_success.jsp"/>
   			           </s:if>
   			           <s:else>
   			           		<s:text name="msg.empty.result"/>
   			           </s:else>
          			</div><!-- /panel -->
          		</div><!-- /col-lg-12 -->
          	</div><!-- /row --> 
          	
          	
          	<div class="row mt">
          		
          		<div class="col-lg-8">
          			<div class="form-panel">
                  	  <h4 class="mb"><i class="fa fa-cog"></i><span class="subject-text"><s:text name="header.list.exchange.rate"/></span><span class="subject-text"><s:date name="dateOfRate" format='%{getText("ui.struts.date.format")}'/></span></h4>
   			       		<s:if test="%{exchangeRateFailList.size() > 0 }">
   			           		<s:include value="list_exchangeRate_fail.jsp"/>
						</s:if>
						<s:else>
							<s:text name="msg.empty.result"/>
						</s:else>
          			</div><!-- /panel -->
          		</div><!-- /col-lg-8 -->
          		
          		<div class="col-lg-4">
          			<div class="form-panel">
                  	  <h4 class="mb"><i class="fa fa-cog"></i><span class="subject-text"><s:text name="header.list.exchange.rate"/></span><span class="subject-text"><s:date name="dateOfRate" format='%{getText("ui.struts.date.format")}'/></span></h4>
   			           <s:if test="%{remainList.size() > 0 }">		
   			           		<s:include value="list_exchangeRate_remain.jsp"/>
   			           </s:if>
   			           <s:else>
							<s:text name="msg.empty.result"/>
						</s:else>
          			</div><!-- /panel -->
          		</div><!-- /col-lg-8 -->
          	</div><!-- /row --> 
          	<div align="right">
				       		<div class="btn-group">
				       			<s:url var="previousDateURL" action="exchangRatePreviousDate">
				       			</s:url>
				       			<s:url var="nextDateURL" action="exchangRateNextDate">
				       			</s:url>
				       			<s:a href="%{previousDateURL}">
				       				<button type="button" class="btn btn-default" id="previousDateBtn"><s:text name="btn.previous.date"/></button>
				       			</s:a>
				       			<s:if test="%{isDateNextIsDisplay()==true}">
					       			<s:a href="%{nextDateURL}">
					       				<button type="button" class="btn btn-default" id="nextdateBtn"><s:text name="btn.next.date"/></button>
					       			</s:a>
				       			</s:if>
				       		</div>
			</div>
<script type="text/javascript" src="<s:url value='/resources/assets/js/jquery.dataTables.js'/>"></script>
<script type="text/javascript" src="<s:url value='/resources/assets/js/dataTables.bootstrap.js'/>"></script>    
</body>
</html>