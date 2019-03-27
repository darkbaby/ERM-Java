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
                  	  <h4 class="mb"><i class="fa fa-search"></i><span class="subject-text"><s:text name="label.search"/></span></h4>
					  <s:form name="generateRateForm"  id="generateRateForm" theme="simple" class="" role="form">
						  <table style="width:100%">
						  	 
							<tr>
								<td>
									<strong><s:text name="label.bank.name"/></strong><br/>
									<s:textfield 
										name="generateRateForm.generateRateSearchForm.bankName" 
										id="col_1_filter" 
										class="form-control" />
								</td>
								<td>
									<strong><s:text name="label.rate.type"/></strong><br/>
									<select class="form-control" style="width:95%"
											name="rateTypeSearch" 
											id="col_3_filter">
										<option value="">---All---</option>
										<option value="<%=IPageContains.ER_ORIGIN_AUTO%>"><%=IPageContains.ER_ORIGIN_AUTO%></option>
										<option value="<%=IPageContains.ER_ORIGIN_MANUAL%>"><%=IPageContains.ER_ORIGIN_MANUAL%></option>
									</select>
								</td>
								<td> 
									<strong><s:text name="label.base.currency"/></strong><br/>
									<s:select   class="form-control" 
												cssStyle="width:95%"
											    headerKey=""
												headerValue="---All---"
												list="currencyList"
												listKey="code"
												listValue="code"
												name="generateRateForm.generateRateSearchForm.baseCurrencyID"
												id="col_4_filter" />	 
								</td>
								<td> 
									  <strong><s:text name="label.pair.currency"/></strong><br/>
									  <s:select   class="form-control" 
  												  cssStyle="width:95%"
											      headerKey=""
												  headerValue="---All---"
												  name="generateRateForm.generateRateSearchForm.pairCurrencyID"
											      list="currencyList"
												  listKey="code"
												  listValue="code"													  
												  id="col_5_filter" />
								</td>
								
								<td>
								</td>
								<td align="rigth"> <br/>
									<button style="width:48%" type="button" class="btn btn-theme" id="searchBtn">Search</button>
									&nbsp;
									<button style="width:48%" type="button" class="btn btn-warning" onclick="onClickClear();">Clear</button>
								</td>
							</tr>
						  </table>
					    </s:form>
          			</div><!-- /form-panel -->
          		</div><!-- /col-lg-12 -->
          	</div><!-- /row -->
			<!-- SEARCH END-->
					               
  
<script src="<s:url value='/resources/assets/js/select2.min.js' />"></script>
<script src="<s:url value="/resources/assets/js/jquery-loading-overlay-1.5.3/src/loadingoverlay.min.js"/>"></script>

<script type ="text/javascript">
	$('#col_3_filter').select2();
	$('#col_4_filter').select2();
 	$('#col_5_filter').select2();
    
 	function onClicksearch(){
    	$('#generateRateForm').attr('action','searchGenerateRate.action').submit();
    	
    	$.LoadingOverlay("show");
	}
 	
 	function onClickClear(){ 		
 		$("#col_3_filter").select2('destroy');
 		$("#col_4_filter").select2('destroy');
 		$("#col_5_filter").select2('destroy');

 		$("#col_3_filter").val("");
 		$("#col_4_filter").val("");
 		$("#col_5_filter").val("");
 		$("#col_1_filter").val("");
 		
 		$('#col_3_filter').select2();
 		$('#col_4_filter').select2();
 		$('#col_5_filter').select2();
  	}
   
</script>
</body>
</html>