<%@page import="com.esynergy.erm.model.IUser"%>
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
<%
	//HttpSession session =  request.getSession();
	IUser user =(IUser)session.getAttribute(IPageContains.SESSION_USER);
%>
</head>
 
 <body>	 
	<!-- ADD EXCHANGE RATE -->
	<s:form name="exchangeRateManualForm" method="POST" enctype="multipart/form-data" id="exchangeRateManualForm" theme="simple" class="form-inline" role="form">
	
          	<div class="row mt">
          		<div class="col-lg-12">
          			<div class="form-panel">
                  	  <h4 class="mb"><i class="fa fa-cog"></i><span class="subject-text"><s:text name="header.exchange.rate"/></span></h4>
   			 
	                 <s:if test="errors['isError']">
	                  	       <div class="alert alert-danger alert-dismissable fade in"  >
								    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
								    <h5><span class="glyphicon glyphicon-exclamation-sign"></span>
								      <s:property value="actionMsg['error']"/> </h5>
							   </div> 
                  	  </s:if> 
                      
                        <table style="width:100%">
							<tr>
								<td width="18%"><s:hidden name="exchangeRateManualForm.id"/>
									<s:hidden name="exchangeRateManualForm.createdDate"/>
									<s:hidden name="exchangeRateManualForm.createdUser"/>
									   <div>
									   		<strong><s:text name="label.date"/><span class="erm-required"></span></strong>
									   </div>
									   <div class="input-group date" id="rateDatePicker">
											<s:textfield name="exchangeRateManualForm.rateDate" id="rateDate" value="%{exchangeRateManualForm.rateDate}" class="form-control" />
											 <span class="input-group-addon">
					                        	<span class="glyphicon glyphicon-calendar"></span>
					                    	</span>
				                    	</div>	 
									<div class="has-error"><s:property value="errors['rateDateError']" /></div>  
								</td>
								<td>
								<div><strong><s:text name="label.setting.type.of.currency"/><span class="erm-required"></span></strong></div>
								<s:select 
								    class="form-control"
								    name="exchangeRateManualForm.pairCurrencyType"
								    id="exchangeRateManualForm.pairCurrencyType"
									list="currencyTypeList"
									listKey="value"
									listValue="descriptionShort"
									headerKey="-1"
									headerValue="-------- Please Select --------"
								/>
								<div class="has-error"><s:property value="errors['errorTypeOfCurrency']" /></div>
							</td>
							</tr>
						</table>
                      <br>
                     <div><table align="right" style="margin-bottom:5px">
                      <tr>
                      	<td  align="right" >
                      		<button style="width: 100px;" type="button" class="btn btn-theme" onclick="createExchangeRateInputRow('rateTbl','<%=IPageContains.API_URL%>','<%=user.getCountry().getCurrency().getId()%>')">
                      			<span class="glyphicon glyphicon-plus-sign"></span>
                      			<span ><s:text name="btn.add"/></span>
                      		</button>
                      		<button style="width: 100px;" type="button" class="btn btn-danger" onclick="removeFormList()" >
                      			<span class="glyphicon glyphicon-minus-sign"></span>
                      			<span ><s:text name="btn.remove"/></span>
                      		</button>
                      	</td>
                      </tr>
                      </table></div>	 
 						<table id="rateTbl" class="table table-striped table-advance table-hover" style="width:100%">
						  <tr>
						  	<th></th>
							<th style="text-align: center" colspan="2"><s:text name="label.currency"/></th>
                            <th style="text-align: center" rowspan="2"><s:text name="label.value"/></th>
                            <th style="text-align: center" colspan="7"><s:text name="label.exchenge.rate"/></th>
						  </tr>
						  <tr>
						    <th>#</th>
						    <th><s:text name="label.first.currency"/></th>
						    <th><s:text name="label.pair.currency"/></th>
							<th><s:text name="label.buying.rate"/></th>
							<th><s:text name="label.selling.rate"/></th>
							<th><s:text name="label.option"/></th>
						  </tr>
						  <s:iterator value="exchangeRateManualForm.exchangeRateDetailList" status="sts">
						  <tr>
						  			<td><s:property value="#sts.index+1" /></td>
						  			
									<td>
											<%-- <s:select 
												disabled="true"
											    class="form-control" 
											    headerKey=""
												headerValue="Plese Select"
												list="currencyList"
												listKey="id"
												name="exchangeRateManualForm.exchangeRateDetailList[%{#sts.index}].pairCurrency"
												id="exchangeRateManualForm.exchangeRateDetailList[%{#sts.index}].pairCurrency"
												listValue="code"
												value="pairCurrency" /> --%>
												<%-- onchange= "populatePairCurrency(this,'exchangeRateManualForm.exchangeRateDetailList[%{#sts.index}].firstCurrency')"  --%>
												 
												 <%-- <div class="has-error"><s:property value="errors['pairCurrencyError'+#sts.index]" /></div>
												 <div class="has-error"><s:property value="errors['currencyError'+#sts.index]" /></div> --%>
									       		<%=user.getCountry().getCurrency().getCode()%>
										</td>
									<TD>
										<s:select 
										    class="form-control" 
										    headerKey=""
											headerValue="Plese Select"
											list="currencyList"
											listKey="id"
											name="exchangeRateManualForm.exchangeRateDetailList[%{#sts.index}].pairCurrency"
											id="exchangeRateManualForm.exchangeRateDetailList[%{#sts.index}].pairCurrency"
											listValue="code"
											value="pairCurrency" 
											onclick="populateBaseCurrency('exchangeRateManualForm.exchangeRateDetailList[%{#sts.index}].pairCurrency')" />
											<%-- onchange= "populatePairCurrency(this,'exchangeRateManualForm.exchangeRateDetailList[%{#sts.index}].pairCurrency')"  --%>	
											 <div class="has-error"><s:property value="errors['pairCurrencyError'+#sts.index]" /></div>
											 <div class="has-error"><s:property value="errors['rateDupplicate'+#sts.index]" /></div>			 		
									 </td>
									
									<td>
									   <s:select 
									   		name="exchangeRateManualForm.exchangeRateDetailList[%{#sts.index}].value" 
									   		id="exchangeRateManualForm.exchangeRateDetailList[%{#sts.index}].value" 
									   		class="form-control"
									   		list="#{'1.00':'1.00','100.00':'100.00'}"
									   		value="value"
									   		required="true"/>
									</td>
									<td>
									   <s:textfield class="form-control text-right"
									   				name="exchangeRateManualForm.exchangeRateDetailList[%{#sts.index}].buyingRate"
									   				id="exchangeRateManualForm.exchangeRateDetailList[%{#sts.index}].buyingRate"/>
										<div class="has-error"><s:property value="errors['rateRequire'+#sts.index]" /></div>
										<div class="has-error"><s:property value="errors['buyingRateError'+#sts.index]"/></div>
									</td>  
					 			    <td>
										<s:textfield class="form-control text-right" 
													name="exchangeRateManualForm.exchangeRateDetailList[%{#sts.index}].sellingRate"
													id="exchangeRateManualForm.exchangeRateDetailList[%{#sts.index}].sellingRate"/>
										<div class="has-error"><s:property value="errors['sellingRateError'+#sts.index]"/></div>
									</td>  
		 						    <td><s:hidden name="exchangeRateManualForm.exchangeRateDetailList[%{#sts.index}].id"/>
								  	 	 <label class="custom-control custom-checkbox">
								  	 	<s:checkbox class="custom-control-input" 
								  	 				name="exchangeRateManualForm.exchangeRateDetailList[%{#sts.index}].chk" 
								  	 				 id="exchangeRateManualForm.exchangeRateDetailList[%{#sts.index}].chk" />
										</label>
									</td>
								  </tr>
							 </s:iterator>
						</table>
						<br/>
						<br/>
						<div class="has-error"><s:property value="errors['rateDetailError']" /></div> 
					</div><!-- /form-panel -->		
          		</div><!-- /col-lg-12 -->
          	</div><!-- /row --> 	
          	
			<div class="row mt">
          		<div class="col-lg-12">
          			<div class="form-panel">
					<div> 
						<h4 class="mb"><i class="glyphicon glyphicon-paperclip"></i><span class="subject-text"><s:text name="header.attract.file"/></span></h4>
							<p class="bg-info"><s:text name="label.file.note"/></p>
							<table class="table table-striped table-advance table-hover table-bordered" >
							<thead>
								<tr>
									<th>#</th>
									<th>
										<s:text name="label.file"/>
									</th>
									<th style="text-align:center;">
										<s:text name="label.option"/>
									</th>
								</tr>
							</thead>
						
							<tbody>
								<s:iterator value="exchangeRateManualForm.fileUploadERManualList" status="st">
								<tr>
										<td><s:property value="#st.index+1"/></td>
									<s:if test="id > 0">
										<td> 
										   <s:hidden name="exchangeRateManualForm.fileUploadERManualList[%{#st.index}].id"/>
										   <s:hidden name="exchangeRateManualForm.fileUploadERManualList[%{#st.index}].url"/>
										   <s:hidden name="exchangeRateManualForm.fileUploadERManualList[%{#st.index}].name"/>	               
							               <s:property value="name"/>
										</td>
										<td style="text-align:center;">
												<s:url var="donwloadFileUrl" action="downloadFileERByManual">
									               	  	<s:param name="parm" value="id"/>
									            </s:url>
												<s:a href="%{donwloadFileUrl}" >       	 
								               	 		<span class="glyphicon glyphicon-download-alt" 
								               	 			 title="<s:text name='label.download'/>" 
								               	 			 style="padding: 2px 10px 3px 10px;">
								               	 		</span>	             	 
								               	</s:a>					                
								               		   <span class="glyphicon glyphicon-trash text-danger"   
								               		   		 title="<s:text name='label.remove'/>"   
								               		   		 style="padding: 2px 10px 3px 10px;"
								               		   		 onclick="removeFile('<s:property value='#st.index'/>')">
								               		   	</span> 
										</td>
									</s:if>
									 <s:if test="id == 0">
										<td>
											<div style="padding-bottom: 10px;">
												  <div style="display: none;">  
															<s:file name="exchangeRateManualForm.fileUploadERManualList[%{#st.index}].file" 
																	id="exchangeRateManualForm.fileUploadERManualList[%{#st.index}].file" />
												  </div>  
												  <s:textfield size="50%"     name="exchangeRateManualForm.fileUploadERManualList[%{#st.index}].name" 
												 					id="exchangeRateManualForm.fileUploadERManualList[%{#st.index}].name"
												 					readonly="true"/>
												 <span id='<s:property value='nameFileId'/>'>
												 	<s:property value="exchangeRateManualForm.fileUploadERManualList[%{#st.index}].name"  />
												 </span>
											</div> 
										</td>
										<td style="text-align:center;">
											<s:set var="fileUploadId" value="%{'exchangeRateManualForm.fileUploadERManualList['+#st.index+'].file'}"/>
											<s:set var="fileInputName" value="%{'exchangeRateManualForm.fileUploadERManualList['+#st.index+'].name'}"/>
 											<s:set var="removeFileBtn" value="%{'remove['+#st.index+']'}"/>
 											<span   title="<s:text name='label.browse'/>" 
 													onclick="browseFile('<s:property value='fileUploadId'/>','<s:property value='fileInputName'/>','<s:property value='nameFileId'/>','<s:property value='removeFileBtn'/>')"> 	 
											    <a><span class="glyphicon glyphicon-folder-open" style="padding: 2px 10px 3px 10px;"></span></a>
											    
											</span>
												<span class="glyphicon glyphicon-trash text-danger"  id="remove[<s:property value='#st.index'/>]" 
									               		   		 title="<s:text name='label.remove'/>"   
									               		   		 style="padding: 2px 10px 3px 10px; display:none; "
									               		   		 onclick="removeFile('<s:property value='#st.index'/>')">
									             </span> 
								              
										</td>
									</s:if>
								</tr>
								</s:iterator>
								</tbody>
								</table>
					</div><!-- /form-panel -->		
          		</div><!-- /col-lg-12 -->
          	</div><!-- /row --> 

          					<table style="width:100%" align="center">
								<tr>
									<td align="center">
										<s:url var="cancelURL"   action="searchExchangeRateByManual"/>
											<s:a href="%{cancelURL}">	 
												<button type="button" id="cancelBtn" style="width:150px" class="btn btn-default btn-sm" title="<s:text name='btn.cancel'/>">
														<span class="glyphicon glyphicon-chevron-left"></span>
														<span><s:text name='btn.cancel'/></span>
												</button>
											</s:a>
										<s:if test="exchangeRateManualForm.id != 0">
											<button style="width:150px" type="button" class="btn btn-danger btn-sm" onclick="remove();" title="<s:text name='btn.remove'/>">
												<span class="glyphicon glyphicon-trash"></span>
												<span><s:text name='btn.remove'/></span>
											</button>
										</s:if>
											<button style="width:150px" type="button" class="btn btn-primary btn-sm" onclick="save();" title="<s:text name='btn.save'/>">
												<span class="glyphicon glyphicon-floppy-disk"></span>
												<span><s:text name='btn.save'/></span>
											</button>
								            
									</td>
								</tr>
							</table>	
					</div>
					<br/>
					<br/>	
	</s:form>
<script src="<s:url value='/resources/assets/js/select2.min.js' />"></script>   
<script src="<s:url value='/resources/assets/js/custom-util/exchangeRateByManual-detail.js' />"></script>   
<script src="<s:url value="/resources/assets/moment-master/min/moment.min.js"/>"></script>
<script src="<s:url value="/resources/assets/js/bootbox.min.js"/>"></script> 
<script src="<s:url value="/resources/assets/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"/>"></script>

 <script type ="text/javascript">
 
 //$('#exchangeRateManualForm.exchangeRateDetailList[0].chk').prop( "disabled", true );
 $( "input[type='checkbox' ][name='exchangeRateManualForm.exchangeRateDetailList[0].chk']" ).prop({
  disabled: true
});
 var rateTbl = document.getElementById("rateTbl");
 var rowCount = rateTbl.rows.length;
	if(rowCount==2){
		 for(var i=0;i<3;i++){
			 createExchangeRateInputRow("rateTbl","<%=IPageContains.API_URL%>","<%=user.getCountry().getCurrency().getId()%>");
		 }
	}
	 
	if($('#rateDate').val()==""){
		$(function () {
		      $('#rateDatePicker').datetimepicker({
		    	  maxDate: moment(),
		    	  showClear:true,
		    	  showTodayButton: true,
		    	  allowInputToggle:true,
		    	  format:'<s:text name='ui.js.date.format'/>',
		    	  useCurrent: true

			}); 
		});
	}else{
		$(function () {
		      $('#rateDatePicker').datetimepicker({
		    	  maxDate: moment(),
		    	  showClear:true,
		    	  showTodayButton: true,
		    	  allowInputToggle:true,
		    	  format:'<s:text name='ui.js.date.format'/>',
		    	  useCurrent: false

			}); 
		});	
	}
	 
	/* $('#cancelBtn').click(function(){
		window.history.back();
	}); */
	function createExchangeRateInputRow(tableID,api_url,pairCurrId) {

		 var table = document.getElementById(tableID);
		 var rowCount = table.rows.length;
		 var row = table.insertRow(rowCount); //to insert blank row
		 var exchangeRateDetailList = "exchangeRateManualForm.exchangeRateDetailList["+(rowCount-2)+"]";
		 var styleClass= "form-control";
		 
		 var cell0 = row.insertCell(0);   // to insert 5th column
		 var indexEl= document.createElement("SPAN");
		 var indexText = document.createTextNode(rowCount-1);
		 indexEl.appendChild(indexText);  
		 cell0.appendChild(indexEl); 
		 
		 var cell1Name = exchangeRateDetailList+".firstCurrency";
		 var cell2Name = exchangeRateDetailList+".pairCurrency";
		 var cell1OnChangePm = "javascript:populatePairCurrency(this,'"+cell2Name+"');";
		 var cell2OnChangePm = "javascript:populatePairCurrency(this,'"+cell1Name+"');";
		 
		 var cell1 = row.insertCell(1);   //to insert first column
		 var firstCurrencyCol = document.createElement("span");
		 firstCurrencyCol.name=cell1Name;
		 firstCurrencyCol.id = cell1Name;
		 cell1.appendChild(firstCurrencyCol);
		 var firstCurrencySelect = document.getElementById(cell1Name);
		 var firstCurrencyOption = null;
		 
		 var cell2 = row.insertCell(2)
		 var pairCurrencyCol = document.createElement("select");
		 pairCurrencyCol.name=cell2Name;
		 pairCurrencyCol.id = cell2Name;
		 pairCurrencyCol.className  = styleClass;
		 cell2.appendChild(pairCurrencyCol);
		 var pairCurrencySelect = document.getElementById(cell2Name);
		 var pairCurrencyOption = null;
		 
		 pairCurrencyOption = document.createElement("option");
		 pairCurrencyOption.value = "";
		 pairCurrencyOption.innerHTML = "Plese Select";
		 pairCurrencySelect.appendChild(pairCurrencyOption);
		 
		 
		 
		 
		 
		 
// 		 var firstCurrencyCol = document.createElement("select");
// 		 /* snoCol.type = "text"; */
// 		 firstCurrencyCol.name=cell1Name;
// 		 firstCurrencyCol.id = cell1Name;
// 		 firstCurrencyCol.className  = styleClass;
// 		 /* firstCurrencyCol.setAttribute('onchange',cell1OnChangePm ); */
// 		 cell1.appendChild(firstCurrencyCol);
// 		 var firstCurrencySelect = document.getElementById(cell1Name);
// 		 var firstCurrencyOption = null;
		 
// 		 var cell2 = row.insertCell(2); //to insert second column
// 		 var pairCurrencyCol = document.createElement("span");//document.createElement("select");
		
// 		 //pairCurrencyCol.className  = styleClass;
// 		 pairCurrencyCol.name=cell2Name;
// 		 pairCurrencyCol.id = cell2Name;
// 		 /* pairCurrencyCol.setAttribute('onchange',cell2OnChangePm); */
// 		 cell2.appendChild(pairCurrencyCol);
// 		 var pairCurrencySelect = document.getElementById(cell2Name);
// 		 var pairCurrencyOption = null;
		 
		 
// 		 firstCurrencyOption = document.createElement("option");
// 		 firstCurrencyOption.value = "";
// 		 firstCurrencyOption.innerHTML = "Plese Select";
// 		 firstCurrencySelect.appendChild(firstCurrencyOption);
	  	 
// 		 /* pairCurrencyOption = document.createElement("option");
// 		 pairCurrencyOption.value = "";
// 		 pairCurrencyOption.innerHTML = "Plese Select"; 
// 		 pairCurrencySelect.appendChild(pairCurrencyOption);
// 		 pairCurrencySelect.value = pairCurrId;
// 		 pairCurrencySelect.disabled = true; */
		 
// 		 /* pairCurrencyOption = document.createElement("span");
// 		 pairCurrencySelect.appendChild(pairCurrencyOption); */

		 
	     jQuery.ajax({
	         type: "GET",
	         url: "/erm/currencyAPIAction",
	         contentType: "application/json; charset=utf-8",
	         dataType: "json",
	         success: function (data, status, jqXHR) {
	          	 $.each(data, function() {
					 /* pairCurrencyOption = document.createElement("option");
					 pairCurrencyOption.value = this.id;
					 pairCurrencyOption.innerHTML = this.code;  */
					 if(this.id == pairCurrId){
						 /* pairCurrencyOption.selected  = true; */
						 firstCurrencyCol.textContent = this.code;
	          		 }else{
	          			pairCurrencyOption = document.createElement("option");
	          			pairCurrencyOption.value = this.id;
	          			pairCurrencyOption.innerHTML = this.code;
	          			pairCurrencySelect.appendChild(pairCurrencyOption);
	          			 
	          		 }
					/*  pairCurrencySelect.appendChild(pairCurrencyOption); */
					 
					 
				}); 
		 
	         },

	         error: function (jqXHR, status) {
	             // error handler
	         }
	     });
		   		
		   		
		 var cell3 = row.insertCell(3); // to insert 3rd column
		 var valueRateCol = document.createElement("select");
		 valueRateCol.className = styleClass;
		 valueRateCol.name=exchangeRateDetailList+".value";
		 valueRateCol.id = exchangeRateDetailList+".value";
		 var optionValue1 = document.createElement("option");
		 optionValue1.value = "1.00";
		 optionValue1.innerHTML = "1.00";
		 valueRateCol.appendChild(optionValue1);
		 
		 var optionValue100 = document.createElement("option");
		 optionValue100.value = "100.00";
		 optionValue100.innerHTML = "100.00";
		 valueRateCol.appendChild(optionValue100);
		 
		 cell3.appendChild(valueRateCol);
		 
		 //var widthRateValueTextBox = "100%";
		 
		 var cell4 = row.insertCell(4);
		 var buyingRateCol = document.createElement("input");
		 var cell4Name = exchangeRateDetailList+".buyingRate";
		 buyingRateCol.type = "text";
		// buyingRateCol.style.width = widthRateValueTextBox;
		 buyingRateCol.className = styleClass+" text-right";
		 buyingRateCol.name = cell4Name;
		 buyingRateCol.id = cell4Name;
		 cell4.appendChild(buyingRateCol);

		 var cell5 = row.insertCell(5);
		 var sellingRateCol = document.createElement("input");
		 var cell5Name = exchangeRateDetailList+".sellingRate";
		 sellingRateCol.type = "text";
		 //sellingRateCol.style.width = widthRateValueTextBox;
		 sellingRateCol.className = styleClass+" text-right";;
		 sellingRateCol.name = cell5Name;
		 sellingRateCol.id = cell5Name;
		 cell5.appendChild(sellingRateCol);
		 
		 var cell6 = row.insertCell(6);   // to insert 5th column
		 var rowRemoveCol = document.createElement("input");
		 rowRemoveCol.type = "checkbox";
		 rowRemoveCol.name=exchangeRateDetailList+".chk";
		 rowRemoveCol.id=exchangeRateDetailList+".chk";
		 rowRemoveCol.value = "on";
		 cell6.appendChild(rowRemoveCol);
		 if("exchangeRateManualForm.exchangeRateDetailList[0]"==exchangeRateDetailList){
			 rowRemoveCol.disabled = true;
		 }
		/* $('select').select2();*/ 
	}	
	function populatePairCurrency(val,pairId){
		 var first_val = val.value;
	 	 var pairCurrencySelect = document.getElementById(pairId);
	 	 var lng = pairCurrencySelect.options.length;
	 	 for(var i=0;i<lng;i++){
		 		var pair = pairCurrencySelect.options[i].value;
		 		 if(pair==first_val){
		 			pairCurrencySelect.options[i].style.display = 'none';
		 		 }else{
		 			pairCurrencySelect.options[i].style.display = 'block'; 
		 		 }
		 	 }
}
	function populateBaseCurrency(baseId){
		 var pair_val = '<%=user.getCountry().getCurrency().getId()%>';
		 var baseCurrencySelect = document.getElementById(baseId);
		 var lng = baseCurrencySelect.options.length;
		 for(var i=0;i<lng;i++){
		 		var base_val = baseCurrencySelect.options[i].value;
		 		 if(base_val==pair_val){
		 			//baseCurrencySelect.options[i].style.display = 'none';
		 			baseCurrencySelect.remove(i);
		 			lng--;
// 		 			i--;
		 		 }else{
		 			baseCurrencySelect.options[i].style.display = 'block'; 
		 		 }
		 	 }
	}
 </script>
    
 <script type="text/javascript">
 //$('select').select2();
</script>  
</body>
</html>