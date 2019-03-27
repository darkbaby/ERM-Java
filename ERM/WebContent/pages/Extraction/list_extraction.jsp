<%@page import="java.util.List"%>
<%@page import="com.esynergy.erm.model.ob.Currency"%>
<%@page import="com.esynergy.erm.web.action.IPageContains"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@ taglib prefix="modong" uri="/WEB-INF/ModongTag.tld" %> 


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="<s:url value='/resources/assets/css/dataTables.bootstrap.css' />"/>
 </head>
 
 <body>	 
	<!--EXCHANGE RATE LIST -->
          	<div class="row mt">
          		<div class="col-lg-12">
          			<div class="form-panel">
                  	  <h4 class="mb"><i class="fa fa-list-ul"></i><span class="subject-text"><s:text name="header.extraction.lists"/></span></h4>
                  	  <table align="right" style="margin-bottom:5px">
	                      <tr>
	                      	<td  align="right" >
	                      	
<%-- 	                        	<modong:sys-permission function="ScrapExtraction"> --%>
<!-- 	                      		<button style="width:100px;" type="button" class="btn btn-danger"  -->
<%-- 	                      			title="<s:text name='btn.scrap.all'/>" --%>
<!-- 	                      			onclick="onClickScrapAll();"> -->
<%-- 		                      		<span class="glyphicon glyphicon-cog"></span> --%>
<%-- 		                      		<span ><s:text name="btn.scrap.all"/></span> --%>
<!-- 		                      	</button> -->
<%-- 	                      		</modong:sys-permission> --%>
	                      	
	                      		<modong:sys-permission function="PrepareCreateExtraction">
				                 <s:url var="addURL" action="prepareCreateExtraction"></s:url>
	                      		<s:a href="%{addURL}">
		                      		<button style="width:100px;" type="button" class="btn btn-theme" 
		                      		title="<s:text name='msg.add.new.extraction'/>">
		                      			<span class="glyphicon glyphicon-plus-sign"></span>		                      		
		                      			<span ><s:text name="btn.add"/></span>
		                      		</button>
	                      		</s:a>
           	    			    </modong:sys-permission>
	                      	</td>
	                      </tr>
                      </table>
   			           <table class="table table-bordered table-striped  table-hover" id="listExtracTbl" style="display:none;">
				                      <thead style="background:#ffd777;">
										  <tr>
										  	<th>#</th>
										  	<th><s:text name="label.bank.name"/></th>
										    <th><s:text name="label.bank.country"/></th>
											<th><s:text name="label.extraction.type"/></th>
											<th><s:text name="label.extract.status"/></th>
											<th style="text-align: center;"><s:text name="label.manage"/></th>
										  </tr>
				                      </thead>
				                      <tbody>
				                      	      <s:iterator value="extractionList" status="hdrsts">
				                      	     
				                      	    		<tr>
				                      	    			<td style="text-align: left;"><s:property value="#hdrsts.index+1"/>
				                      	    		 	 <td style="text-align: left;"><s:property value="bank.bankName"/>  
				                      	    			  <td style="text-align: left;"><s:property value="bank.country.countryName" /></td>
				                      	    			<td style="text-align: left;">
				                      	    				<s:if test="extractionType == 1"><s:text name="msg.use.css.selector"/></s:if>
				                      	    				<s:if test="extractionType == 2"><s:text name="msg.use.specific.tag.id"/></s:if>
				                      	    				<s:if test="extractionType == 3"><s:text name="msg.use.api"/></s:if>
				                      	    			</td>
				                      	    			<td style="text-align: left;">
				                      	    				<s:if test='status.equals("A")'>
				                      	    					<s:text name="msg.status.acive"/>
				                      	    				</s:if>
				                      	    				<s:if test='status.equals("S")'>
				                      	    					<s:text name="msg.status.inactive"/>
				                      	    				</s:if>
				                      	    			 
				                      	    			</td>
				                      	    			<td style="text-align: center;"> 
				                      	    			  <s:url var="editURL"   action="prepareEditExtraction"><s:param name="parm" value="%{id}" />  </s:url>
				                      	    			  <s:url var="viewURL"   action="prepareViewExtraction"><s:param name="parm" value="%{id}" />  </s:url>
				                      	    			  <s:url var="scrapURL"   action="scrapExtraction"><s:param name="parm" value="%{id}" />  </s:url>
				                      	    			    
				                      	    			    <modong:sys-permission function="PrepareViewExtraction">
				                      	    			    <s:a href="%{viewURL}">
				                      	    			    	<button class="btn btn-success btn-sm" 
				                      	    			    			title="<s:text name="label.view.detail"/>">
				                      	    			    		<i class="fa fa-file"></i>
				                      	    			    	</button>
				                      	    			    </s:a>
				                      	    			    </modong:sys-permission>
				                      	    			    <modong:sys-permission function="PrepareEditExtraction">
				                      	    			    <s:a href="%{editURL}">
				                      	    			    	<button class="btn btn-primary btn-sm" 
				                      	    			    			title="<s:text  name="msg.edit"/>">
				                      	    			    		<i class="glyphicon glyphicon-edit"></i>
				                      	    			    	</button>
				                      	    			    </s:a>
				                      	    			    </modong:sys-permission>
				                      	    			    <modong:sys-permission function="RemoveExtraction">
				                      	    			    <s:if test='status.equals("S")'>
				                      	    			    	<button class="btn btn-danger btn-sm" onclick="onClickDelete('<s:property value="id"/>');"
				                      	    			    			title="<s:text  name="label.remove"/>">
				                      	    			    		<i class="fa fa-trash-o"></i>
				                      	    			    	</button>
				                      	    			    </s:if>
				                      	    			    </modong:sys-permission>
<%-- 				                      	    			    <modong:sys-permission function="ScrapExtraction"> --%>
<%-- 				                      	    			    	<s:if test='status.equals("A")'> --%>
<!-- 				                      	    			    	<button class="btn btn-warning btn-sm"  -->
<%-- 			                      	    			    			title="<s:text name="btn.scrap"/>" --%>
<%-- 			                      	    			    			onclick="onClickScrap('<s:property value="id"/>');" --%>
<!-- 			                      	    			    			> -->
<!-- 			                      	    			    			<i class="fa fa-cog"></i> -->
<!-- 			                      	    			    		</button> -->
<%-- 			                      	    			    		</s:if> --%>
<%-- 				                      	    			    </modong:sys-permission> --%>
				                      	    			</td>     
				                      	    		</tr>
				                      	    </s:iterator>  
				                      </tbody>
				       </table> 
	
          			</div><!-- /panel -->
          		</div><!-- /col-lg-12 -->
          	</div><!-- /row --> 
					                
 <script type="text/javascript" src="<s:url value='/resources/assets/js/jquery.dataTables.js'/>"></script>
 <script type="text/javascript" src="<s:url value='/resources/assets/js/dataTables.bootstrap.js'/>"></script>
 <script src="<s:url value="/resources/assets/js/bootbox.min.js"/>"></script> 
 <script type ="text/javascript">
 
  	$(document).ready(function(){
 	    var extractionTable = $('#listExtracTbl').DataTable({
 	    	searching: false,
	 		order:[[1,"asc"]],
 	    	columnDefs:[{
 	    		orderable: false, targets: [0,5], visible: true
 	    	}],
 	    	drawCallback: function(setting){
//  	    		console.log(setting._iDisplayStart);
 	    		reDrawNumberExtractionTable(setting._iDisplayStart);
 	    	}
 	    });
 	    
 	    $("#listExtracTbl").css("display","");
		
//  	    var info = extractionTable.page.info();
 	    
 	   reDrawNumberExtractionTable(0);
	   
// 	   $('#listExtracTbl').on('order.dt',function(){
// 		   reDrawNumberExtractionTable();
//    	   })
   	   
//    	   $('#listExtracTbl').on('length.dt',function(){
// 		   reDrawNumberExtractionTable();
//    	   })
   	   
//    	   $('#listExtracTbl').on('page.dt',function(){
// 		   reDrawNumberExtractionTable();
//    	   })
 	  
//  	  $('#listExtracTbl').on('page.dt',function(){
//  		  console.log(info.page + " " + info.length + " " + info.pages);
//  	  })
 	  
 	});
  	
  	function reDrawNumberExtractionTable(beginIndex){
		var sizeAr = "<s:property value='extractionList.size()'/>";
		
		if(sizeAr == "0"){
			return false;
		}
		
  		var runningNumber = beginIndex + 1;
  		$('#listExtracTbl').find("tr").each(function(i,obj){
  			if(i > 0){
  				$(obj).find("td:nth-child(1)").html(runningNumber);
			  	runningNumber++;
		  	}
	   	});
  	}
  	
  	function onClickDelete(id){
  		  		
  		bootbox.confirm({
	        title: "Confirm Remove!",
	        message: "Do you want to remove this Record?",
	        buttons: {
	            cancel: {
	                label: '<i class="fa fa-times"></i> Cancel'
	            },
	            confirm: {
	                label: '<i class="fa fa-check"></i> Confirm'
	            }
	        },
	        callback: function (result) {
	            if(result){
	         		//$('#extractionForm').attr('action','removeExtraction.action').submit();
	            	$(location).attr('href','removeExtraction?parm=' + id);
	            }
	        }
	    });
  		
  		
  	}
  	
  	function onClickScrapAll(){
	  		
  		bootbox.confirm({
	        title: "Confirm Scrap All!",
	        message: "Do you want to scrap all these extractions?",
	        buttons: {
	            cancel: {
	                label: '<i class="fa fa-times"></i> Cancel'
	            },
	            confirm: {
	                label: '<i class="fa fa-check"></i> Confirm'
	            }
	        },
	        callback: function (result) {
	            if(result){
	            	$(location).attr('href','scrapExtraction');
	            }
	        }
	    });
  		
  		
  	}
  	
  	function onClickScrap(id){
	  		
  		bootbox.confirm({
	        title: "Confirm Scrap!",
	        message: "Do you want to scrap this extraction?",
	        buttons: {
	            cancel: {
	                label: '<i class="fa fa-times"></i> Cancel'
	            },
	            confirm: {
	                label: '<i class="fa fa-check"></i> Confirm'
	            }
	        },
	        callback: function (result) {
	            if(result){
	            	$(location).attr('href','scrapExtraction?parm=' + id);
	            }
	        }
	    });
  		
  		
  	}
  
 </script>

</body>
</html>