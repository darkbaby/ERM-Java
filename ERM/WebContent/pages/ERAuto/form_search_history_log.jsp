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
</head>
 
 <body>	 
 <div class="row mt">
          		<div class="col-md-12">
          			<div class="form-panel02">
                  	  <h4 class="mb"><i class="fa fa-search"></i><span style="padding-left:5px;"><s:text  name="label.search"/></span></h4>
					  <s:form name="exchangeRateAutoSearchForm"  id="exchangeRateAutoSearchForm" theme="simple" class="form-inline" role="form">
							<div class="row mt">
									<div class="col-sm-2">
										 <div><s:text name="label.scraped.date.from"/></div>
										<div class="input-group date" id="dateStartPicker">
												 <s:textfield name="exchangeRateAutoSearchForm.dateStart" id="dateStart" value="%{exchangeRateAutoSearchForm.dateStart}" class="form-control" />
												 <span class="input-group-addon">
						                        	<span class="glyphicon glyphicon-calendar"></span>
						                    	</span>
					                    </div>	
					                </div> 
									<div class="col-sm-2">
										<div><s:text name="label.to"/></div>
							 			<div class="input-group date" id="dateEndPicker">
												<s:textfield name="exchangeRateAutoSearchForm.dateEnd" id="dateEnd" value="%{exchangeRateAutoSearchForm.dateEnd}" class="form-control" />
												 <span class="input-group-addon">
						                        	<span class="glyphicon glyphicon-calendar"></span>
						                    	</span>
					                    </div>
					                </div>
					                <div class="col-sm-3">
										<div><s:text name="label.bank.name"/></div>
										 <s:textfield 
										 	cssStyle="width:100%;" 
										 	name="exchangeRateAutoSearchForm.bankName" 
										 	id="bankName" 
										 	value="%{exchangeRateAutoSearchForm.bankName}" 
										 	class="form-control"
										 	onkeypress="onKeyPressed(event);" />
									</div>		 
									<div class="col-sm-1">
										<div><s:text name="label.status"/></div>
										<s:select   class="form-control"
													cssStyle="width:100%;"
												    headerKey=""
													headerValue="---ALL---"
													list="#{'SUCCESS':'SUCCESS','FAIL':'FAIL'}"
													name="exchangeRateAutoSearchForm.logStatust"
													id="logStatust" />
									</div>	
								<div class="col-sm-2">
									<br/>
									<button style="width:40%" type="button" id="searchBtn" class="btn btn-theme"><s:text name="btn.search"></s:text></button>
								
									<button style="width:40%" type="button" id="resetBtn" class="btn btn-warning"><s:text name="btn.clear"/></button>
								</div>

						  <div class="has-error"><s:property value="errors['searchError']"/></div>
					   	</div>
					    </s:form>
          			</div><!-- /form-panel -->
          		</div><!-- /col-lg-12 -->
          	</div><!-- /row -->
			<!-- SEARCH END-->
					               
  
<script src="<s:url value='/resources/assets/js/select2.min.js' />"></script>
<script src="<s:url value='/resources/assets/js/dateFormat.min.js' />"></script>
<script src="<s:url value='/resources/assets/js/jquery-dateFormat.min.js' />"></script>
<script src="<s:url value="/resources/assets/moment-master/min/moment.min.js"/>"></script> 
 
</body>
</html>