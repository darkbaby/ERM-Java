<%@page import="java.util.List"%>
<%@page import="com.esynergy.erm.model.ob.Currency"%>
<%@page import="com.esynergy.erm.web.action.IPageContains"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <link href="<s:url value='/resources/assets/css/select2.min.css' />" rel="stylesheet" />
     <link href="<s:url value="/resources/assets/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css"/>" rel="stylesheet" />
 
</head>
 
 <body>	 
 <div class="row mt">
          		<div class="col-md-12">
          			<div class="form-panel02">
                  	  <h4 class="mb"><i class="fa fa-search"></i><span style="padding-left:5px;"><s:text  name="label.search"/></span></h4>
					  <s:form name="exchangeRateSearchForm"  id="exchangeRateSearchForm" theme="simple" class="form-inline" role="form">
									<div class="form-group">
									
										<div><s:text name="label.first.currency"/></div>
							 			<s:select   class="form-control" 
												    headerKey="0"
													headerValue="---ALL---"
													list="currencyList"
													listKey="id"
													listValue="code"
													name="exchangeRateSearchForm.baseCurrencyId"
													id="baseCurrencyId" 
													value="exchangeRateSearchForm.baseCurrencyId"/>
					                </div>
					                <div class="form-group">
										 <div><s:text name="label.pair.currency"/></div>
										<s:select   class="form-control" 
												    headerKey="0"
													headerValue="---ALL---"
													list="currencyList"
													listKey="id"
													listValue="code"
													name="exchangeRateSearchForm.pairCurrencyId"
													id="pairCurrencyId" 
													value="exchangeRateSearchForm.pairCurrencyId"/>
					                </div> 
										 
									<div class="form-group">
										<div><s:text name="label.bank.country"/></div>
										<s:select   class="form-control" 
												    headerKey="0"
													headerValue="---ALL---"
													list="countryList"
													listKey="id"
													listValue="countryName"
													name="exchangeRateSearchForm.countryOfBank"
													id="countryOfBank" 
													value="exchangeRateSearchForm.countryOfBank"/>
									</div>
									<div class="form-group">
										<div><s:text name="label.type"/></div>
										<s:select   class="form-control" 
												    headerKey=""
													headerValue="---ALL---"
													list="#{'AUTO':'AUTO','MANUAL':'MANUAL'}"
													name="exchangeRateSearchForm.origin"
													id="origin" 
													value="exchangeRateSearchForm.origin"/>
									</div>	
								<div class="form-group">
									<br/>
									<button style="width:100%" type="button" id="searchBtn"  class="btn btn-theme btn-sm"><s:text name="btn.search" /></button>
								</div>
								<div class="form-group">
									<br/>
									<button style="width:100%" type="button" id="resetBtn" class="btn btn-warning btn-sm"><s:text name="btn.clear" /></button>
								</div>

						  <div class="has-error"><s:property value="errors['searchError']"/></div>
					    </s:form>
          			</div><!-- /form-panel -->
          		</div><!-- /col-lg-12 -->
          	</div><!-- /row -->
			<!-- SEARCH END-->
					               
  
<script src="<s:url value='/resources/assets/js/select2.min.js' />"></script>
 
  <script type ="text/javascript">
   $("#searchBtn").click(function(){
    	document.getElementById("exchangeRateSearchForm").action = "searchExchangeRate.action";
    	document.getElementById("exchangeRateSearchForm").submit();
	});
    $("#resetBtn").click(function(){
    	$("#pairCurrencyId").val('0');
    	$("#baseCurrencyId").val('0');
    	$("#countryOfBank").val('0');
    	$("#origin").val('');
    	$("#select2-pairCurrencyId-container").text("---ALL---");
    	$("#select2-baseCurrencyId-container").text("---ALL---");
    	$("#select2-countryOfBank-container").text("---ALL---");
    	$("#select2-origin-container").text("---ALL---");
    });
 </script>
    
<script type="text/javascript">
  $('select').select2();
</script>
</body>
</html>