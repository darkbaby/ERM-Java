<%@page import="java.util.List"%>
<%@page import="com.esynergy.erm.model.ob.Currency"%>
<%@page import="com.esynergy.erm.web.action.IPageContains"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@ taglib prefix="modong" uri="/WEB-INF/ModongTag.tld" %>
 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <link rel="stylesheet" href="<s:url value='/resources/assets/css/dataTables.bootstrap.css' />"/>
</head>      
             
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
			
			<table style="width:100%;">
				<tr>
					<th style="width:50%;">
						<s:text name="label.name"/>
					</th>
				</tr>
				<tr>
					<td style="width:50%;">
						<s:property value="generateRateForm.profileName"/>
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
							<s:checkbox
								name="generateRateForm.timeFormList[%{#sts.index}].generateTime"
 								fieldValue="%{generateTime}"
 								value="%{chk}"
 								disabled="true"
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
					</td>
				</tr>
			</table>
			
			<br>
			
			<table class="table table-striped table-advance table-hover" style="width:100%">
				<thead>
					<tr>
						<th>
							<s:text name="label.type" />
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
					</tr>
				</thead>
				<tbody>
					<s:iterator value="generateRateForm.detailFormList" status="sts" >
					<tr>
						<td>
							<s:property
								value="type"
							/>
						</td>
						<td>
							<s:property value="baseCurrency" />
						</td>
						<td>
							<s:property value="pairCurrency" />
						</td>
						<td>
							<s:property value="bankForm.bankName" />
						</td>
						<td>
							<s:if test='rateType.equals("1")'>
							<s:text name="label.buying.rate"/>
							</s:if>
       	    				<s:if test='rateType.equals("2")'>
       	    				<s:text name="label.selling.rate"/>
       	    				</s:if>
       	    				<s:if test='rateType.equals("3")'>
       	    				<s:text name="label.average.rate"/>
       	    				</s:if>
						</td>
					</tr>
					</s:iterator>
					
				</tbody>
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
							<i class="fa fa-file-o"></i>
							<span class="subject-text">
								<s:text name="header.text.file"/>
							</span>
						</h4>
					</td>
				</tr>
			</table>		
			
			<table id="textFileTable" class="table table-striped table-advance table-hover" style="width:100%;display:none;">
				<thead>
					<tr>
						<th>
							#
						</th>
						<th>
							<s:text name="label.date" />
						</th>
						<th>
							<s:text name="label.file.name" />
						</th>
						<th>
							<s:text name="label.download" />
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="generateRateForm.fileFormList" status="sts" >
					<tr>
						<td>
							<s:property value="#sts.index+1" />
						</td>
						<td>
							<s:date name="addDate" format="yyyy/MM/dd HH:mm:ss" />
						</td>
						<td>
							<s:property value="fileName" />
						</td>
						<td>
							<s:url var="donwloadFileUrl" action="downloadGenerateRate">
								<s:param name="parmDownload" value="id"/>
								<s:param name="parmUrl" value="url"/>
							</s:url>
							<s:a href="%{donwloadFileUrl}" target="_blank" >       	 
       	    				<button class="btn btn-success btn-xs">
       	    					<i class="fa fa-download"></i>
       	    				</button>
       	    				</s:a>
						</td>
					</tr>
					</s:iterator>
					
				</tbody>
			</table>
	  		
		</div>			
	</div><!-- /col-lg-12 -->
</div><!-- /row -->      

<section class="wrapper">


<table style="width:100%">
				<tr>
					<td align="left">
						<s:url var="backURL" action="prepareManageGenerateRate"></s:url>
						<s:a href="%{backURL}">
							<button style="width:150px" class="btn btn-theme" title="<s:text name='btn.back'/>">
								<span><s:text name='btn.back'/></span>
							</button>
						</s:a>
						
					</td>
					<td align="right">
						<modong:sys-permission function="PrepareEditGenerateRate">
						<s:url var="editURL" action="prepareEditGenerateRate">
							<s:param name="parm" value="%{generateRateForm.id}" />
						</s:url>
						<s:a href="%{editURL}">
							<button style="width:150px" class="btn btn-theme02" title="<s:text name='btn.edit'/>">
								<span><s:text name='btn.edit'/></span>
							</button>
						</s:a>
						</modong:sys-permission>
					</td>
				</tr>
			</table>
</section>
		
			


<script type="text/javascript" src="<s:url value='/resources/assets/js/jquery.dataTables.js'/>"></script>                          
<script type="text/javascript" src="<s:url value='/resources/assets/js/dataTables.bootstrap.js'/>"></script>
                          
<script type ="text/javascript">

	$(document).ready(function(){
		
		$('#textFileTable').dataTable({
	 		searching: false,
	 		pageLength:15,
	 		aLengthMenu:[15,25,35],
	 		order:[[1,"desc"]],
	 		columnDefs:[{
	 			orderable: false, targets: [0,2,3], visible: true
	 		}]
	 	});
		
		$('#textFileTable').css("display","");
		
		reDrawNumberGenerateRateTable();
		
// 		$('#textFileTable').on('order.dt',function(){
// 			reDrawNumberGenerateRateTable();
// 	   	})
	   	
	   	$('#textFileTable').on('draw.dt',function(){
			reDrawNumberGenerateRateTable();
	   	})
	});
	
	function reDrawNumberGenerateRateTable(){
		
		var sizeAr = "<s:property value="generateRateForm.fileFormList.size()"/>";
		
		if(sizeAr == '0'){
			return false;
		}
		
  		var runningNumber = 1;
  		$('#textFileTable').find("tr").each(function(i,obj){
  			if(i > 0){
  				$(obj).find("td:nth-child(1)").html(runningNumber);
			  	runningNumber++;
		  	} 
	   	});
  	}

</script>