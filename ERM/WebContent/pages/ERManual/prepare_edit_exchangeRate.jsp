<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
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
              <li><s:a href="%{prepareManageExchangeRateManualUrl}"><s:text name="menu.sub.manual.process"/></s:a> </li>
              <li><s:text name="header.edit.exchange.rate"/></li>
            </ul>
            </div>
            <!--BREADCRUM END-->  
			<h3 class="subject"><span class="subject-text"><s:property value="menuName"/></span></h3>
			    
			<div >
		      	<s:include value="form_edit_exchangeRate.jsp"/>
		    </div>
		  </section>
      </section>	
      <s:include value="../common_footer.jsp"/>
		     
</section>		
</body>
</html>