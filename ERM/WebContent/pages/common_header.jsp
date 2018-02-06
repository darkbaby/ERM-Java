<%@page import="com.esynergy.erm.web.action.IPageContains"%>
<%@page import="com.esynergy.erm.model.IUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

    <title>EXCHANGE RATE MANAGEMENT</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="<s:url value='/resources/assets/css/bootstrap.css' />" >
    <!--external css-->
    <link rel="stylesheet" type="text/css" href="<s:url value='resources/assets/font-awesome/css/font-awesome.css' />"  />
    <link rel="stylesheet" type="text/css" href="<s:url value='/resources/assets/css/zabuto_calendar.css' /> "/>
    <link rel="stylesheet" type="text/css" href="<s:url value='/resources/assets/js/gritter/css/jquery.gritter.css' />" />
    <link rel="stylesheet" type="text/css" href="<s:url value='/resources/assets/lineicons/style.css' /> "/>    
    
    <!-- Custom styles for this template -->
    <link rel="stylesheet" type="text/css" href="<s:url value='/resources/assets/css/style.css' />" />
    <link rel="stylesheet" type="text/css" href="<s:url value='/resources/assets/css/style-responsive.css' /> " />
</head>
<body>
<%-- <s:include value="loginCheck.jsp"></s:include> --%>
      <!-- **********************************************************************************************************************************************************
      TOP BAR CONTENT & NOTIFICATIONS
      *********************************************************************************************************************************************************** -->
      <!--header start-->
      <%
      	IUser iUser = (IUser)request.getSession().getAttribute(IPageContains.SESSION_USER);
      %>
      <header class="header black-bg">
      	    <s:url var="prepareDasboardUrl" action="prepareDasboard.action"></s:url>
      		<s:a href="%{prepareDasboardUrl}"
      			 cssClass="logo">
      			<b>
	         		EXCHANGE RATE MANAGEMENT
	         	</b>
      		</s:a>
<!--             <a href="menu.html" class="logo"><b>EXCHANGE RATE MANAGEMENT</b></a> -->	
          <%--   <div class="top-menu">
            	<ul class="nav pull-right top-menu">
                    <li><s:url var="logOutUrl" action="logOut"/>
                    	<s:a cssClass="logout" href="%{logOutUrl}"   >Logout</s:a></li>
            	</ul>
            	 
            </div> --%>
            <div class="top-menu">
            	 
            	 <ul class="nav pull-right top-menu navbar-right" style="padding: 15px 0px 0px 3px;">
                    <li class="dropdown">
                        <a href="#" data-toggle="dropdown" class="dropdown-toggle"><i class="glyphicon glyphicon-user" style="padding: 2px 2px 2px 2px;"></i><b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><s:url var="changePwdUrl" action="prepareChangePwd"/>
                            	<s:a href="%{changePwdUrl}">Change Password</s:a></li>
                            <li class="divider"></li>
                           <li><s:url var="logOutUrl" action="logOut"/>
                    			<s:a cssClass="logout" href="%{logOutUrl}" >Logout</s:a></li>
                        </ul>
                    </li>
                </ul>  
               
                <span class="pull-right"  style="padding: 28px 0px 0px 3px;">
                	<%=iUser.getLogOnId()%>:<%=iUser.getAuthorizeGroup().getGroupName() %></span>
			</div>
            
      </header>
      <!--header end-->
      
    <!-- js placed at the end of the document so the pages load faster -->
    <%-- <script src="<s:url value='/resources/assets/js/jquery.js'/> "></script> --%>
    <script src="<s:url value='/resources/assets/js/jquery-1.8.3.min.js' /> "></script> 
    <script src="<s:url value='/resources/assets/js/jquery.min.js' /> "></script>
    <script src="<s:url value='/resources/assets/js/bootstrap.min.js' /> "></script>
    <script src="<s:url value='/resources/assets/js/jquery.dcjqaccordion.2.7.js' /> "></script>
    <%-- <script src="<s:url value='/resources/assets/js/jquery.scrollTo.min.js' /> "></script> --%>
    <%-- <script src="<s:url value='/resources/assets/js/jquery.nicescroll.js' /> "></script> --%>
    <script src="<s:url value='/resources/assets/js/jquery.sparkline.js' /> "></script>


    <!--common script for all pages-->
    <script src="<s:url value='/resources/assets/js/common-scripts.js' />"></script>
    
    <script type="text/javascript" src="<s:url value='/resources/assets/js/gritter/js/jquery.gritter.js' />"></script>
    <script type="text/javascript" src="<s:url value='/resources/assets/js/gritter-conf.js' />"></script>

    <!--script for this page-->
 
	<script src="<s:url value='/resources/assets/js/zabuto_calendar.js' />"></script>
 
</body>
</html>