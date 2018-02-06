<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring and Struts Integration Demo</title>
    <style>
      .username.ng-valid {
          background-color: lightgreen;
      }
      .username.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .username.ng-dirty.ng-invalid-minlength {
          background-color: yellow;
      }

      .email.ng-valid {
          background-color: lightgreen;
      }
      .email.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .email.ng-dirty.ng-invalid-email {
          background-color: yellow;
      }

    </style>
   
     <link href="<s:url value='/resources/assets/css/app.css' />" rel="stylesheet"></link>

</head>
 
 <body ng-app="myApp" class="ng-cloak">
  <section id="container" >
  	<s:include value="common_header.jsp"/>
  	<s:include value="common_sidebar_menu.jsp"/>
      <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">
		    <!--BREADCRUM START-->
            <ul class="breadcrumb">
              <li><a href="#"><s:property value="getText('header.dashboard')"/></a></li>
            </ul>
			<h3><i class="fa fa-dashboard"></i> Dashboard</h3>
			<!--BREADCRUM END-->      
			<div  ng-controller="UserController as ctrl">
		          <div class="panel panel-default">
		                <!-- Default panel contents -->
		              <div class="panel-heading"><span class="lead">
 
		             <s:property value="getText('user.email.valid')" /> </span></div>
		              <div class="tablecontainer">
		                  <table class="table table-hover">
		                      <thead>
		                          <tr>
		                              <th>Logon ID.</th>
		                              <th>First Name</th>
		                              <th>Last Name</th>
		                               
		                              <th width="20%"></th>
		                          </tr>
		                      </thead>
		                      <tbody>
		                          <tr ng-repeat="u in ctrl.users">
		                              <td><span ng-bind="u.logOnId"></span></td>
		                              <td><span ng-bind="u.firstName"></span></td>
		                              <td><span ng-bind="u.lsatName"></span></td>
		                             
		                              <td>
		                             <!--  <button type="button" ng-click="ctrl.edit(u.id)" class="btn btn-success custom-width">Edit</button>  <button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width">Remove</button> -->
		                              </td>
		                          </tr>
		                      </tbody>
		                  </table>
		              </div>
		          </div>
		      </div>
          </section>
      </section>	
		      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
		      <script src="<s:url value='/resources/assets/js/app.js' />"></script>
		      <script src="<s:url value='/resources/assets/js/service/user_service.js' />"></script>
		      <script src="<s:url value='/resources/assets/js/controller/user_controller.js' />"></script>	  
</section>		
</body>
</html>