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
              <li><s:text name="menu.sub.auto.process"/></li>
              <li><s:text name="menu.adjust.exchange.rate"/></li>
              <li><s:property value="menuName"/></li>
            </ul>
            </div>
            <!--BREADCRUM END-->  
			 <s:include value="view_exchangeRate.jsp"/>
		 </section>
      </section>	
      <s:include value="../common_footer.jsp"/>
		   
</section>	
<script type="text/javascript">
// 	$('#cancelBtn').click(function (){
// 		window.history.back();
// 	});
	 
</script>	
</body>
</html>