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
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
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
            <ul class="breadcrumb">
              <li><a href="#"><s:property value="getText('header.dashboard')"/></a></li>
            </ul>
			<h3><i class="fa fa-dashboard"></i> Dashboard</h3>
			<!--BREADCRUM END-->      
			<div >
		          <div class="panel panel-default">
		                <!-- Default panel contents -->
		              <div class="panel-heading"><span class="lead">
 
		             <s:property value="getText('user.email.valid')" /> </span></div>
		              <div class="tablecontainer">
		                 <s:if test="exchangeRateManuals.size()>0">
		                 
		                 
				                  <table class="table table-hover">
				                      <thead>
				                          <tr>
				                              <th>id</th>
				                              <th>rateDate</th>
				                              <th>createdUser</th>
				                               
				                              <th width="20%"></th>
				                          </tr>
				                      </thead>
				                      <tbody>
				                      	<s:iterator value="exchangeRateManuals" status="userStatus" >
				                      		<s:iterator value="exchangeRateDetailList">
					                          <tr>
					                              <td><s:property value="[1].id"/>  </td>
					                              <td><s:property value="[1].rateDate"/>  </td>
					                              <td><s:property value="[1].createdUser"/>  </td>
					                              <td><s:property value="firstCurrency"/>  </td>
					                              <td><s:property value="pairCurrency"/>  </td>
					                              <td>
					                             <!--  <button type="button" ng-click="ctrl.edit(u.id)" class="btn btn-success custom-width">Edit</button>  <button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width">Remove</button> -->
					                              </td>
					                          </tr>
					                         </s:iterator>
				                        </s:iterator>
				                      </tbody>
				                  </table>
				            </s:if>
		              </div>
		          </div>
		      </div>
          </section>
      </section>	
		 
</section>		
</body>
</html>