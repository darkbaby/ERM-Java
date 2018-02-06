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
<%--      <link href="<s:url value="/resources/assets/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css"/>" rel="stylesheet" /> --%>
 
</head>
 
 <body>	 
 <div class="row mt">
          		<div class="col-md-12">
          			<div class="form-panel02">
                  	  <h4 class="mb"><i class="fa fa-search"></i><span style="padding-left:5px;"><s:text  name="label.search"/></span></h4>
					  <s:form name="exchangeRateManualSearchForm"  id="exchangeRateManualSearchForm" theme="simple" class="form-inline" role="form">
									<div class="form-group">
										 <div><s:text name="label.date.from"/></div>
										<div class="input-group date" id="rateDateStartPicker">
												 <s:textfield name="exchangeRateManualSearchForm.rateDateStart" id="rateDateStart" value="%{exchangeRateManualSearchForm.rateDateStart}" class="form-control" />
												 <span class="input-group-addon">
						                        	<span class="glyphicon glyphicon-calendar"></span>
						                    	</span>
					                    </div>	
					                </div> 
									<div class="form-group">
										<div><s:text name="label.to"/></div>
							 			<div class="input-group date" id="rateDateEndPicker">
												<s:textfield name="exchangeRateManualSearchForm.rateDateEnd" id="rateDateEnd" value="%{exchangeRateManualSearchForm.rateDateEnd}" class="form-control" />
												 <span class="input-group-addon">
						                        	<span class="glyphicon glyphicon-calendar"></span>
						                    	</span>
					                    </div>
					                </div>	 
									<div class="form-group">
										<div><s:text name="label.last.update.by"/></div>
										<s:select   class="form-control" 
												    headerKey=""
													headerValue="---------ALL---------"
													list="userUpdateList"
													name="exchangeRateManualSearchForm.updateBy"
													id="updateBy" />
									</div>
										<div class="form-group">
											<br/>
											<button style="width:100%" type="button" class="btn btn-theme btn-sm" onclick="search()">Search</button>
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
<script src="<s:url value='/resources/assets/js/dateFormat.min.js' />"></script>
<script src="<s:url value='/resources/assets/js/jquery-dateFormat.min.js' />"></script>
<script src="<s:url value="/resources/assets/moment-master/min/moment.min.js"/>"></script> 
<%-- <script src="<s:url value="/resources/assets/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"/>"></script> --%>
 <script type ="text/javascript">
    function search(){
    	document.getElementById("exchangeRateManualSearchForm").action = "searchExchangeRateByManual.action";
    	document.getElementById("exchangeRateManualSearchForm").submit();
	}
    $('#resetBtn').click(function(){
    	$('#rateDateStart').val($.format.date(new Date(),"yyyy/MM/dd"));
    	$('#rateDateEnd').val($.format.date(new Date(),"yyyy/MM/dd"));
    	$('#updateBy').val("");
    	$('#select2-updateBy-container').text("---ALL---");
    	
    });
    
 </script>
    
<script type="text/javascript">
  $('select').select2();
</script>
</body>
</html>