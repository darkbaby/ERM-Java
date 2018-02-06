<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <!-- Bootstrap core CSS -->
    
    <link rel="stylesheet" type="text/css" href="<s:url value='/resources/assets/css/bootstrap.css' /> " />
    
    <!--external css-->
    <link rel="stylesheet" type="text/css" href="<s:url value='/resources/assets/font-awesome/css/font-awesome.css' /> " />
        
    <!-- Custom styles for this template -->
    <link rel="stylesheet" type="text/css" href="<s:url value='/resources/assets/css/style.css' /> " />
    <link rel="stylesheet" type="text/css" href="<s:url value='/resources/assets/css/style-responsive.css' /> " />
	<link rel="stylesheet" type="text/css" href="<s:url value='/resources/assets/css/app.css' /> " />
  </head>

  <body>

      <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
	 <header class="header black-bg">
	      	    <s:url var="prepareDasboardUrl" action="prepareDasboard.action"></s:url>
	      		<s:a href="%{prepareDasboardUrl}"
	      			 cssClass="logo">
	      			<b>
		         		EXCHANGE RATE MANAGEMENT
		         	</b>
	      		</s:a>
	<!--             <a href="menu.html" class="logo"><b>EXCHANGE RATE MANAGEMENT</b></a> -->
	     <div class="top-menu">
            	<ul class="nav pull-right top-menu">
                    <li><img class="img-circle" alt="Cinque Terre" src="<s:url value='/resources/assets/img/rcl_logo.jpg' />"> </li>
            	</ul>
            </div>        
	</header>
	 
	  	<div class="container" style="padding-top: 50px">
	  	 
		      
	  	<div class="panel panel-warning">
		    <div class="panel-body">
		       <h4> This the user not have permission </h4>
		    	<table style="width:100%" align="center">
								<tr>
									<td align="center">
											 
												<button type="button" id="cancelBtn" style="width:150px" class="btn btn-default btn-sm" title="<s:text name='btn.back'/>">
														<span class="glyphicon glyphicon-chevron-left"></span>
														<span><s:text name='btn.back'/></span>
												</button>
									</td>
								</tr>
				</table>	
		    
		    </div>
		</div>
	  	</div>
	 

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="<s:url value='/resources/assets/js/jquery.js' /> "></script> 
    <script src="<s:url value='/resources/assets/js/bootstrap.min.js' /> "></script> 

    <!--BACKSTRETCH-->
    <!-- You can use an image of whatever size. This script will stretch to fit in any screen size.-->
    <script src="<s:url value='/resources/assets/js/jquery.backstretch.min.js' /> "></script> 
   <script>
/*         $.backstretch("<s:url value='/resources/assets/img/login-bg.jpg' />", {speed: 500});
 */    </script>

<script type="text/javascript">
 
	$('#cancelBtn').click(function(){
		window.history.back();
	});
</script>
  </body>
</html>