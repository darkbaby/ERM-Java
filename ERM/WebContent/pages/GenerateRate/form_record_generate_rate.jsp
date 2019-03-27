<%@page import="java.util.List"%>
<%@page import="com.esynergy.erm.model.ob.Currency"%>
<%@page import="com.esynergy.erm.web.action.IPageContains"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@ taglib prefix="m" uri="../../WEB-INF/ModongTag.tld" %>
  
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <link rel="stylesheet" href="<s:url value='/resources/assets/css/dataTables.bootstrap.css' />"/>
   <link href="<s:url value='/resources/assets/css/select2.min.css' />" rel="stylesheet" />
</head>  

<s:form name="generateRateForm"  id="generateRateForm" theme="simple" class="" role="form">
<s:hidden name="menuName"/>
<div class="row mt">
          		<div class="col-md-12">
          			<div class="form-panel02">
                  	  <h4 class="mb"><i class="fa fa-search"></i><span class="subject-text"><s:text name="label.search"/></span></h4>
						  <table style="width:100%">
						  	 
							<tr>
								<td>
									<strong><s:text name="label.bank.name"/></strong><br/>
									<s:textfield 
										name="generateRateForm.generateRateSearchForm.bankName" 
										id="col_1_filter" 
										class="form-control"
										style="width:95%"
										onkeypress="onKeyPressed(event);" />
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
												name="generateRateForm.generateRateSearchForm.baseCurrency"
												id="col_4_filter" />	 
								</td>
								<td> 
									  <strong><s:text name="label.pair.currency"/></strong><br/>
									  <s:select   class="form-control" 
  												  cssStyle="width:95%"
											      headerKey=""
												  headerValue="---All---"
												  name="generateRateForm.generateRateSearchForm.pairCurrency"
											      list="currencyList"
												  listKey="code"
												  listValue="code"													  
												  id="col_5_filter" />
								</td>
								
								<td>
								</td>
								<td align="rigth"> <br/>
									<button style="width:30%" type="button" class="btn btn-theme" id="searchBtn">Search</button>
									&nbsp;
									<button style="width:30%" type="button" class="btn btn-warning" onclick="onClickClear();">Clear</button>
								</td>
							</tr>
						  </table>
<%-- 					    </s:form> --%>
          			</div><!-- /form-panel -->
          		</div><!-- /col-lg-12 -->
          	</div><!-- /row -->  
  
  
<div class="row mt">
	<div class="col-lg-12">
		<br>
		<div class="form-panel">
			
			<table style="width:100%;">
				<tr>
					<td style="width:50%;">
						<h4 class="mb">
							<i class="fa fa-cogs"></i>
							<span class="subject-text">
								<s:text name="header.search.results.exchange.rate"/>
							</span>
						</h4>
					</td>
				</tr>
			</table>
			
			<table style="width:100%;display:none;" class="table table-striped  table-hover" id="recordTable">
				<thead>
					<tr align="center">
						<th rowspan="2" style="width:4%"><s:text name="label.select"/></th>
						<th rowspan="2" style="width:40%"><s:text name="label.bank.name"/></th>
						<th rowspan="2" style="width:20%"><s:text name="label.setting.type.of.currency"/></th>
						<th rowspan="2" style="width:20%"><s:text name="label.rate.type"/></th>
						<th colspan="2" ><s:text name="label.currency"/></th>
	  				</tr>
	  				<tr align="center">
	  					<th style="width:8%"><s:text name="label.first"/></th>
						<th style="width:8%"><s:text name="label.pair"/></th>
	  				</tr>
	  			</thead>
	  			<tbody>
	  				<s:iterator value="generateRateResultExchangeRateList" status="sts">
	  				<tr align="center">
	  					<td>
		  					<s:checkbox
		  						name="test"			
		  					/>
	  					</td>
	  					<td>
	  						<s:property value="bankName"/>
	  					</td>
	  					<td>
	  						<s:if test="pairCurrencyType == 1">
	  							<s:text name="label.direct"/>
	  						</s:if>
	  						<s:else>
	  							<s:text name="label.indirect"/>
	  						</s:else>
	  					</td>
	  					<td>
	  						<s:property value="type"/>
	  					</td>
	  					<td>
	  						<s:property value="baseCurrency.getCode()"/>
	  					</td>
	  					<td>
	  						<s:property value="pairCurrency.getCode()"/>
	  					</td>
	  				</tr>
	  				</s:iterator>
	  			</tbody>
	  		</table>
	  		<table align="center" style="margin-bottom:5px">
	            <tr>
	            	<td  align="center" >
	             		<button type="button" class="btn btn-theme" onclick="onClickAdd();">
	             			<span class="glyphicon glyphicon-plus-sign"></span>
	             			<span ><s:text name="btn.add"/></span>
	             		</button>
	            	</td>
	            </tr>
          	</table>
	  		
	  		
	  
	  		
		</div>			
	</div><!-- /col-lg-12 -->
</div><!-- /row --> 
             
             
             
             
<div class="row mt">
	<div class="col-lg-12">
		<br>
		<div class="form-panel">	
			<table style="width:100%;">
				<tr>
					<td style="width:50%;">
						<h4 class="mb">
							<i class="fa fa-list-ul"></i>
							<span class="subject-text">
								<s:text name="header.exchange.rate.list.for.generate"/>
							</span>
						</h4>
					</td>
				</tr>
			</table>
			
<%-- 			<s:form name="generateRateForm" enctype="multipart/form-data" id="generateRateForm" theme="simple" class="form-inline" role="form">   --%>
		
			
			<table style="width:100%;">
				<tr>
					<th style="width:50%;">
						<s:text name="label.name"/>
						<span class="erm-required"></span>
					</th>
				</tr>
				<tr>
					<td style="width:50%;">
						<s:hidden name="generateRateForm.id" />
						<s:textfield
							name="generateRateForm.profileName"
							cssClass="form-control"
						/>
						<div class="has-error">
							<s:property value="errors['errorProfileName']" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<th style="width:50%;">
						<s:text name="label.time.to.generate"/>
						<span class="erm-required"></span>
						<s:text name="label.time.zone"/>
					</th>
				</tr>
				<tr>
					<td style="width:100%;">
						<table>
							<s:iterator value="generateRateForm.timeFormList" status="sts">
							<s:if test="%{(#sts.index+1)%8 == 1}">
								<tr>
							</s:if>
							
							<td>
							<s:hidden
								name="generateRateForm.timeFormList[%{#sts.index}].id"
							/>
							<s:checkbox
								name="generateRateForm.timeFormList[%{#sts.index}].generateTime"
 								id="generateRateForm.timeFormList[%{#sts.index}].generateTime"
 								fieldValue="%{generateTime}"
 								value="%{chk}"
 							/>
 							<s:property
 								value="generateTimeLabel" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
								
							<s:if test="%{(#sts.index+1)%8 == 0}">
								</tr>
							</s:if>
							</s:iterator>
						</table>
					
						<div class="has-error">
							<s:property value="errors['errorTime']" />
						</div>
					</td>
				</tr>
			</table>
			
			<br>
			
			<table id="formTable" class="table table-striped table-advance table-hover" style="width:100%">
				<thead>
					<tr>
						<th>
							<s:text name="label.type" />
							<span class="erm-required"></span>
						</th>
						<th>
							<s:text name="label.base.currency" />
						</th>
						<th>
							<s:text name="label.pair.currency" />
						</th>
						<th>
							<s:text name="label.bank.name" />
						</th>
						<th>
							<s:text name="label.exchange.rate" />
						</th>
						<th>
							<s:text name="label.manage" />
						</th>
					</tr>
				</thead>
				<tbody>
					<tr style="display:none;"> 
						<td>
							<s:textfield
								cssClass="form-control"
								maxlength="5"
							/>
						</td>
						<td>
							<s:hidden
								
							/>
						</td>
						<td>
							<s:hidden
								
							/>
						</td>
						<td>							 
							<s:hidden
								
							/>
						</td>
						<td>
							<s:select
							    class="form-control"
								list="#{'1': 'Buying Rate', '2':'Selling Rate', '3':'Average Rate'}"
								value="1"
							/>
						</td>
						<td style="display:none;">
							<s:hidden
								
							/>
						</td>
						<td style="display:none;">
							<s:hidden
								
							/>
						</td>
						<td>
							<button style="margin-top:2px;" type="button" class="btn btn-inverse btn-s" onclick="removeDetail(this);">
								<span class="fa fa-times"></span>
							</button>
						</td>
					</tr>
					
					<s:iterator value="generateRateForm.detailFormList" status="sts" >
					<tr>
						<td>
							<s:textfield
								cssClass="form-control"
								name="generateRateForm.detailFormList[%{#sts.index}].type"
								maxlength="5"
							/>
							<div class="has-error">
								<s:property value="errors['errorType'+#sts.index]" />
							</div>
						</td>
						<td>
							<s:hidden
								name="generateRateForm.detailFormList[%{#sts.index}].baseCurrency"
							/>
							<s:property value="baseCurrency" />
						</td>
						<td>
							<s:hidden
								name="generateRateForm.detailFormList[%{#sts.index}].pairCurrency"
							/>
							<s:property value="pairCurrency" />
						</td>
						<td>
							<s:hidden
								name="generateRateForm.detailFormList[%{#sts.index}].bankForm.bankName"
							/>							 
							<s:property value="bankForm.bankName" />
						</td>
						<td>
							<s:select
							    class="form-control"
								list="#{'1': 'Buying Rate', '2':'Selling Rate', '3':'Average Rate'}"
								
								name="generateRateForm.detailFormList[%{#sts.index}].rateType"
							/>
						</td>
						<td style="display:none;">
							<s:hidden
								name="generateRateForm.detailFormList[%{#sts.index}].pairCurrencyType"
							/>
						</td>
						<td style="display:none;">
							<s:hidden
								name="generateRateForm.detailFormList[%{#sts.index}].id"
							/>
						</td>
						<td>
							<button style="margin-top:2px;" type="button" class="btn btn-inverse btn-s" onclick="removeDetail(this);">
								<span class="fa fa-times"></span>
							</button>
						</td>	
					</tr>
					</s:iterator>
				</tbody>
			</table>
			<div class="has-error">
				<s:property value="errors['errorDetail']" />
			</div>
			
			
			<table style="width:100%" align="center">
				<tr>
					<td align="center">
						<button style="width:150px" type="button" class="btn btn-theme" onclick="save();" title="<s:text name='btn.save'/>">
							<span><s:text name='btn.submit'/></span>
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<s:url var="cancelURL" action="prepareManageGenerateRate"></s:url>
						<s:a href="%{cancelURL}">
							<button type="button" style="width:150px" class="btn btn-theme04" title="<s:text name='btn.cancel'/>">
								<span><s:text name='btn.cancel'/></span>
							</button>
						</s:a>
						
					</td>
				</tr>
			</table>	
			
			
			<s:iterator value="generateRateForm.detailFormRemoveList" status="sts" >
				<s:hidden
					name="generateRateForm.detailFormRemoveList[%{#sts.index}]"
				/>
			</s:iterator>
			
	  		
	  
	  		
		</div>			
	</div><!-- /col-lg-12 -->
</div><!-- /row -->      

</s:form> 

<script src="<s:url value='/resources/assets/js/select2.min.js' />"></script>
<script type="text/javascript" src="<s:url value='/resources/assets/js/jquery.dataTables.js'/>"></script>                          
<script type="text/javascript" src="<s:url value='/resources/assets/js/dataTables.bootstrap.js'/>"></script>
                          
<script type ="text/javascript">

	$(document).ready(function(){
		$('#recordTable').dataTable({
//  	    	searching: true,
			sDom:"lrtip",
 	    	order:[[1,"asc"]],
 	    	columnDefs:[{
 	    		orderable: false, targets: [0], visible: true
 	    	}],
 	    	bAutoWidth:false
 	    });
		
		$("#searchBtn").click();
		
		$('#recordTable').css("display","");
	});
		
	function onClickAdd(){
		
		$("#recordTable").find("tbody tr").each(function(i,obj){
			var curChk = $(obj).find("input[type=checkbox]").filter(":first");
			if($(curChk).is(":checked")){
				
				$(curChk).prop('checked',false);
				
				var bankNameVal = $(obj).find("td:nth-child(2)").html().trim();
				var typeOfCurrencyVal = $(obj).find("td:nth-child(3)").html().trim();
				if(typeOfCurrencyVal == 'Direct'){
					typeOfCurrencyVal = 1;
				}
				else{
					typeOfCurrencyVal = 2;
				}
				var bankNameVal = $(obj).find("td:nth-child(2)").html().trim();
				var rateTypeVal = $(obj).find("td:nth-child(4)").html().trim();
				var baseCurrencyVal = $(obj).find("td:nth-child(5)").html().trim();
				var pairCurrencyVal = $(obj).find("td:nth-child(6)").html().trim();
				 				
		 		var lastRow = $("#formTable").find("tbody tr:last");
				var tempRow = $("#formTable").find("tbody tr:first").clone();
		 		$(tempRow).css("display","");
				$(tempRow).find("input[type=text]:first").val("");
				$(tempRow).find("td:nth-child(2) input[type=hidden]:first").val(baseCurrencyVal);
				$(tempRow).find("td:nth-child(2) input[type=hidden]:first").after(baseCurrencyVal);
				$(tempRow).find("td:nth-child(3) input[type=hidden]:first").val(pairCurrencyVal);
				$(tempRow).find("td:nth-child(3) input[type=hidden]:first").after(pairCurrencyVal);
				$(tempRow).find("td:nth-child(4) input[type=hidden]:first").val(bankNameVal);
				$(tempRow).find("td:nth-child(4) input[type=hidden]:first").after(bankNameVal);
				$(tempRow).find("td:nth-child(6) input[type=hidden]:first").val(typeOfCurrencyVal);
				$(tempRow).find("td:nth-child(7) input[type=hidden]:first").val(0);

		 		$(tempRow).insertAfter(lastRow);
			}
		});
		
		reconcileFormTable();
	}
	
	function reconcileFormTable(){
		var runningIndex = 0;
		$("#formTable").find("tbody tr").each(function(i,obj){
			if(i>0){
				var allTDsTag = $(obj).find("td");
				allTDsTag.each(function(i2,obj2){
					var objectNewIndex = "generateRateForm.detailFormList[" + runningIndex + "]";
					switch(i2){
					case 0: 
						objectNewIndex = objectNewIndex + ".type";
						break;
					case 1:
						objectNewIndex = objectNewIndex + ".baseCurrency";
						break;
					case 2:
						objectNewIndex = objectNewIndex + ".pairCurrency";
						break;
					case 3:
						objectNewIndex = objectNewIndex + ".bankForm.bankName";
						break;
					case 4:
						objectNewIndex = objectNewIndex + ".rateType";
						break;
					case 5:
						objectNewIndex = objectNewIndex + ".pairCurrencyType";
						break;
					case 6:
						objectNewIndex = objectNewIndex + ".id";
						break;
					}
					$(obj2).children(":first").attr("name",objectNewIndex);
	 			});
				runningIndex++;
			}
		});
	}
	
	function save(){
		$("#generateRateForm").attr('action','composeGenerateRate.action')
		$("#generateRateForm").submit();
	}
	
	function removeDetail(obj){
		var tdElementStoreID = $(obj).parent().prev();
		var storedID = $(tdElementStoreID).find("input[type=hidden]:first").val();
		if(storedID > 0){
			$("#generateRateForm").attr('action','removeGenerateRateDetailForm.action?removeID=' + storedID)
			$("#generateRateForm").submit();
		}
		else{
			$(tdElementStoreID).parent().remove();
		}
	}
	
	$('#col_3_filter').select2();
	$('#col_4_filter').select2();
 	$('#col_5_filter').select2();
	
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
	
	function filterColumn ( i ) {
		console.log('filterColumn value == '+$('#col_'+i+'_filter').val());
	    $('#recordTable').DataTable().column(i).search(
	        $('#col_'+i+'_filter').val()
	    ).draw();
	}
	
	function onKeyPressed(event){
 		if(event.keyCode == 13){
 	 		$('#searchBtn').click();
 		}
 	}
	
	$('#searchBtn').click(function(){
		$('#recordTable').DataTable();
	   	filterColumn(1);
	   	filterColumn(3);
	   	filterColumn(4);
	   	filterColumn(5);  
	});

</script>