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
					  <s:form name="extractionSearchForm"  id="extractionSearchForm" theme="simple" class="" role="form">
						  <table id="searchTable" >
							<tr>
								<td style="width:20%">
										 <div class="form-group">
<!-- 										 	<label for="bankName"> -->
										 		<s:text name="label.bank.name"/>
<!-- 										 	</label> -->
											<s:textfield name="forHide" style="display:none;" />
											
											<s:textfield 
												name="extractionSearchForm.bankName" 
												id="bankName" 
												value="%{extractionSearchForm.bankName}" 
												class="form-control"
												onkeypress="onKeyPressed(event);" />
					                    </div>	 
								</td>
								 <td style="width:1%"></td>
								<td style="width:20%"> 
									<div class="form-group"> 
										<s:text name="label.bank.country"/>
										<s:select   class="form-control" 
												    headerKey="-1"
													headerValue="---ALL---"
													list="countryList"
													listKey="id"
													listValue="countryName"
													name="extractionSearchForm.bankCountry"
													id="bankCountry" />	 
									</div>
								</td>
								<td style="width:1%"></td>
								<td style="width:20%"> 
									<div class="form-group"> 
										 <s:text name="label.extraction.type"/>
										  <s:select   class="form-control" 
												      headerKey="-1"
													  headerValue="---ALL---"
													  name="extractionSearchForm.typeOfSetting"
												      list="extractionTypeList"
													  listKey="value"
													  listValue="descriptionShort"													  
													  id="typeOfSetting" />
									</div>
								</td>
								<td style="width:1%"></td>
								<td style="width:20%"> 
									<div class="form-group"> 
										 <s:text name="label.extract.status"/>
										  <s:select   class="form-control" 
												      headerKey="-1"
													  headerValue="---ALL---"
													  name="extractionSearchForm.status"
													  list="statusList"
													  listKey="value"
													  listValue="descriptionShort"
													  id="status" />
									</div>
									
								</td>
								<td style="width:1%"></td>
								<td style="width:5%"  align="right"> 
									<button style="width:100%" type="button" class="btn btn-theme" id="searchBtn">Search</button>
								</td>
								<td style="width:1%"></td>
								<td style="width:5%"  align="right"> 
									<button style="width:100%" type="button" class="btn btn-warning" id="clearBtn">Clear</button>
								</td>
							</tr>
						  </table>
						  <div class="has-error"><s:property value="errors['searchError']"/></div>
					    </s:form>
          			</div><!-- /form-panel -->
          		</div><!-- /col-lg-12 -->
          	</div><!-- /row -->
			<!-- SEARCH END-->
					               
  
<script src="<s:url value='/resources/assets/js/select2.min.js' />"></script>
 <script type ="text/javascript">
 	
 	function onKeyPressed(event){
 		if(event.keyCode == 13){
 	 		$('#searchBtn').click();
 		}
 	}
 	
    $('#searchBtn').click(function(){
    	$('#extractionSearchForm').attr('action','searchExtraction.action').submit();
	});
    
    $('#clearBtn').click(function(){
    	
    	$("#searchTable").find("input[type=text]").each(function(i,obj){
 			$(obj).val("");
 		});
    	
    	$("#searchTable").find("select").each(function(i,obj){
    		$(obj).val("-1").trigger('change');
 		});
    	
//     	$('#extractionSearchForm').attr('action','searchExtraction.action').submit();
	});
   
 </script>
    
<script type="text/javascript">
 $('select').select2();
</script>
</body>
</html>