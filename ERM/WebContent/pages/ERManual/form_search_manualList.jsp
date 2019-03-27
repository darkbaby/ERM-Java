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
					  <s:form name="manualTargetSearchForm"  id="manualTargetSearchForm" theme="simple" class="form-inline" role="form">
								<div class="row mt">
									<div class="col-sm-1">
										<div><s:text name="label.base.currency"/></div>
											
											<s:select   class="form-control"
													cssStyle="width:100%;"
												    headerKey="-1"
													headerValue="---ALL---"
													list="currencyList"
													listKey="id"
													listValue="code"
													name="manualTargetSearchForm.baseCurrency"
													id="baseCurrencyCdt" />	 
					                </div>
									<div class="col-sm-1">
										<div><s:text name="label.pair.currency"/></div>
											<s:select   class="form-control" 
													cssStyle="width:100%;"
												    headerKey="-1"
													headerValue="---ALL---"
													list="currencyList"
													listKey="id"
													listValue="code"
													name="manualTargetSearchForm.pairCurrency"
													id="pairCurrencyCdt" />	 
					                </div>
					                 
<!-- 									<div class="form-group"> -->
<%-- 										<div><s:text name="label.last.update.by"/></div> --%>
<%-- 										<s:select   class="form-control"  --%>
<%-- 												    headerKey="" --%>
<%-- 													headerValue="---------ALL---------" --%>
<%-- 													list="userUpdateList" --%>
<%-- 													name="exchangeRateManualSearchForm.updateBy" --%>
<%-- 													id="updateBy" /> --%>
<!-- 									</div> -->
										<div class="col-sm-2">
											<br/>
											<button style="width:40%" type="button" class="btn btn-theme" onclick="search()">Search</button>
										
											<button style="width:40%" type="button" id="resetBtn" class="btn btn-warning"><s:text name="btn.clear" /></button>
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
 <script type ="text/javascript">
    function search(){
    	document.getElementById("manualTargetSearchForm").action = "searchManualTarget.action";
    	document.getElementById("manualTargetSearchForm").submit();
	}
    $('#resetBtn').click(function(){
//     	$('#rateDateStart').val($.format.date(new Date(),"yyyy/MM/dd"));
//     	$('#rateDateEnd').val($.format.date(new Date(),"yyyy/MM/dd"));
//     	$('#updateBy').val("");

		$("#manualTargetSearchForm").find("select").each(function(i,obj){
    		$(obj).val("-1").trigger('change');
 		});
    });
    
 </script>
    
</body>
</html>