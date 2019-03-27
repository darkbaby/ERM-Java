<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring and Struts Integration Demo</title>
    
     <link href="<s:url value='/resources/assets/css/app.css' />" rel="stylesheet"></link>
	<style>
		.radioMarginRight{
			margin-right: 0px !important;
		}
		label.radioMarginRight{
			margin-right: 20px !important;
		}  
	</style>
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
		    <!--BREADCRUM START-->
		  <section class="wrapper">
            <ul class="breadcrumb">
              <li><s:text name="menu.sub.auto.process"/></li>
              <li><s:a href="%{prepareManageExtractionUrl}"><s:text name="header.mangement.extraction"/></s:a></li>
              <li><s:text name="header.extraction"/> </li>
            </ul>
            <!--BREADCRUM END-->  
			<h3 class="subject"><s:property value="menuName"/></h3>
			
			<div style="width:100%;">
		      	<s:include value="form_extraction.jsp"/>
		    </div>
		 </section>
      </section>	
      <s:include value="../common_footer.jsp"/>    
		  
</section>		
</body>
</html>