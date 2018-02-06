<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>   
<%@ taglib prefix="modong" uri="/WEB-INF/ModongTag.tld" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     
     <link href="<s:url value='/resources/assets/css/app.css' />" rel="stylesheet"></link>

</head>
 
 <body>
  <section id="container" >
  	<s:include value="../common_header.jsp"/>
  	<s:include value="../common_sidebar_menu.jsp"/>
      <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
      <!--main content start-->
      <section id="main-content">
      	<section class="wrapper">
		    <!--BREADCRUM START-->
            <div class="row">
              
             <ul class="breadcrumb">
              <li><s:a href="%{prepareMangeExchangeRateManualUrl}"><s:text name="menu.sub.manual.process"/></s:a> </li>
              <li><s:text name="header.view.exchange.rate"/></li>
            </ul>
            </div>
            <!--BREADCRUM END-->  
			<h3 class="subject"><span class="subject-text"><s:text name="actionMsg['actionTitle']"/></span></h3>
			    
			<div >
		      	<s:include value="view_detail_exchangeRate.jsp"/>
		    </div>
		    <div>
		    	<table style="width:100%" align="center">
									<tr>
										<td align="center">
 									             	<s:url var="cancelURL"   action="searchExchangeRateByManual"/>
													<s:a href="%{cancelURL}">
														<button style="width:150px" id="cancelBtn" type="button" class="btn btn-default btn-sm" title="<s:text name='btn.cancel'/>">
															<span class="glyphicon glyphicon-chevron-left"></span>
															<span><s:text name='btn.back'/></span>
														</button>
													</s:a>
												<modong:sys-permission function="EditExchangeRateManual" user="${exchangeRateManual.createdUser}"> 
													<s:url var="editURL"   action="prepareEditExchangeRateByManualAction"><s:param name="parm" value="%{exchangeRateManual.id}" />  </s:url>        
										            <s:a href="%{editURL}">
										                  <button style="width:150px"  class="btn btn-primary btn-sm" 
										                      	    	title="<s:text  name="msg.edit"/>">
										                      	<i class="glyphicon glyphicon-edit"></i>
										                      	<span><s:text name='btn.edit'/></span>
										                 </button>
										            </s:a>
									            </modong:sys-permission>
										</td>
									</tr>
								</table>	
		    </div>
		 </section>
      </section>	
      <s:include value="../common_footer.jsp"/>
		   
</section>		
</body>
</html>