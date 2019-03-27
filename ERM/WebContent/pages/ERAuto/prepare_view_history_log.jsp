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
              <li><s:text name="menu.main.auto.histlog.scraped"/></li>
              <li><s:text name="actionMsg['actionTitle']"/></li>
            </ul>
            </div>
            <!--BREADCRUM END-->  
			<h3 class="subject"><i class="fa fa-dashboard"></i><span class="subject-text"><s:text name="actionMsg['actionTitle']"/></span></h3>
			    
			<div >
		      	<s:include value="view_detail_exchangeRate.jsp"/>
		    </div>
		    <div>
		    	<table style="width:100%" align="center">
									<tr>
										<td align="center">
												<s:url var="cancleURL" action="prepareManageExchangeRateAuto"/>

												<s:a href="%{cancleURL}" >       	 
								               	 	<button style="width:150px" 
													type="button" id="cancelBtn" 
													class="btn btn-default btn-sm" 
													title="<s:text name='btn.cancel'/>">
														<span class="glyphicon glyphicon-chevron-left"></span>
														<span><s:text name='btn.back'/></span>
													</button>	
								               	</s:a>
													 
										</td>
									</tr>
			   </table>	
		    </div>
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