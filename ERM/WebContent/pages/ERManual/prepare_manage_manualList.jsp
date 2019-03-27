<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<%@ taglib prefix="modong" uri="../../WEB-INF/ModongTag.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
     <link href="<s:url value='/resources/assets/css/app.css' />" rel="stylesheet"></link>
     <link href="<s:url value="/resources/assets/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css"/>" rel="stylesheet" />
 
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
              <li><s:text name="menu.sub.manual.process"/></li>
              <li><s:text name="menu.main.manual.list.maintain"/></li>
            </ul>
            </div>
            <!--BREADCRUM END-->  
			<h3 class="subject"><span class="subject-text"><s:property value="menuName"/></span></h3>
 			<div >
               <s:if test="errors['haveResult']">
	                  	       <div class="alert alert-success alert-dismissable fade in" id="success-alert" >
								    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
								    <h5><span class="glyphicon glyphicon-ok"></span>
								      <s:property value="actionMsg['resultMessage']"/> </h5>
								      
							   </div> 
                 </s:if>
                <s:include value="form_search_manualList.jsp"/> 
		      	<s:include value="listManualList.jsp"/>
		    </div>
		 </section>
      </section> 
      <s:include value="../common_footer.jsp"/>    
</section>	
<script src="<s:url value="/resources/assets/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"/>"></script>
<script type="text/javascript">
     $("#success-alert").fadeTo(2000, 500).slideUp(500, function(){
     	$("#success-alert").slideUp(500);
     });
</script>	
</body>
</html>