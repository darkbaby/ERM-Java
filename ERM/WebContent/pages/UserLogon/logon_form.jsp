<%@page import="com.esynergy.erm.web.action.IPageContains"%>
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
	  <div id="login-page">
	  	<div class="container">
	  	 
		      <s:form name="userLogonForm"  id="userLogonForm" theme="simple" class="form-login" role="form">
		        <h2 class="form-login-heading">sign in now</h2>
		        <div class="login-wrap">
		            
<%-- 		            <s:textfield cssClass="form-control" name="userLogonForm.userLogonId" autocomplete="username"/> --%>
		            <s:textfield cssClass="form-control" name="userLogonForm.userLogonId" />
		            <br>
<%-- 		            <s:password cssClass="form-control" name="userLogonForm.pwd" autocomplete="current-password"/> --%>
		            <s:password cssClass="form-control" name="userLogonForm.pwd"/>
		            <div class="has-error"><s:property value="errors['logonError']"/></div>
		            <label class="checkbox">
		                <span class="pull-right">
		                    <a data-toggle="modal" href="login.html#myModal"> Forgot Password?</a>
		
		                </span>
		            </label>
		            <button class="btn btn-theme btn-block" id="submitBtn" type="submit"><i class="fa fa-lock"></i> SIGN IN</button>
		            <hr>
		        </div>
		
		          <!-- Modal -->
		          <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                         <!--  <h4 class="modal-title">Forgot Password ?</h4> -->
		                      </div>
		                      <div class="modal-body">
		                          <p>Enter your e-mail address below to reset your password.</p>
<!-- 		                          <input type="text" name="email" id="email" placeholder="Email" autocomplete="email" class="form-control placeholder-no-fix"> -->
		                          <input type="text" name="email" id="email" placeholder="Email" autocomplete="off" class="form-control placeholder-no-fix">
		                          <span id="resetMsg"></span>
		                      </div>
		                      <div class="modal-footer">
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Close</button>
		                          <button class="btn btn-theme" type="button" id="forgetSubmitBtn"  data-loading-text="<i class='fa fa-spinner fa-spin'></i> Processing..">Submit</button>
		                      </div>
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
		
		      </s:form>	  	
	  	
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
	$('#submitBtn').click(function(){
		$('#userLogonForm').attr('action','logon').submit();
	});
	$('#forgetSubmitBtn').click(function(){
		  var $this = $(this);
		  $this.button('loading'); 
		var emailAddr = $('#email').val();
		
		if(emailAddr == ""){
			//setTimeout(function() {
			       $this.button('reset');
			  // }, 500);  
			$('#resetMsg').html("<div class='has-error'>require</div>");
		}else{
			var key = '<%=IPageContains.USER_API_KEY%>';
			var json = { "emailAddress":emailAddr};  
			var json1 = JSON.stringify(json);
			var dataObj = {"data" : {"header":{"key":key},"detail":json1}};
			var data1 = JSON.stringify(dataObj);
			jQuery.ajax({
		         type: "POST",
		         url: "/erm/userByEmailApiAction",
		         data:data1,
		         contentType: "application/json; charset=utf-8",
		         dataType: "json",
		         success: function (data, status, jqXHR,loader) {
		        	// setTimeout(function() {
					       $this.button('reset');
					  // }, 500);  
		          	if(data !=null){ 
						if(data.result == "error"){
							$('#resetMsg').html("<div class='has-error'>"+data.desc+"</div>");
						}else{
						  if(data.result == "success"){
							
							  $('#resetMsg').html("<div class='has-success'>"+data.desc+"</div>");
							}else{
								$('#resetMsg').html("<div class='has-warning'> result not found!</div>"); 
							}
						}
						
		          	}else{
		          		 
		          	}
		         },
		         error: function (jqXHR, status) {
		             // error handler
		        	  
		         }
		     });
		}
		
		
 	});
	
</script>
  </body>
</html>