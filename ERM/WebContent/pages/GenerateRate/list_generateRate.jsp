<%@page import="java.util.List"%>
<%@page import="com.esynergy.erm.model.ob.Currency"%>
<%@page import="com.esynergy.erm.web.action.IPageContains"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@ taglib prefix="modong" uri="/WEB-INF/ModongTag.tld" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<s:url value='/resources/assets/css/dataTables.bootstrap.css' />"/>


</head>

<div class="row mt">
	<div class="col-lg-12">
		<div class="form-panel">
			<h4 class="mb">
				<i class="fa fa-list-ul"></i>
				<span class="subject-text">
					<s:text name="header.list.generaet.text.file"/>
				</span>
			</h4>
			<table align="right" style="margin-bottom:5px">
				<tr>
					<td align="right" >
					<s:url var="addURL" action="prepareCreateGenerateRate"></s:url>
	                <s:a href="%{addURL}">
	                	<button style="width:180px;margin-right:10px;" type="button" class="btn btn-theme" title="<s:text name='msg.add.new.generate.rate'/>">
	                		<span class="glyphicon glyphicon-plus-sign"></span>
		                    <span>
		                    	<s:text name="msg.add.new.generate.rate"/>
		                    </span>
		                </button>
		            </s:a>
	                </td>
	            </tr>
	        </table>
	        
	        <table class="table table-bordered table-striped  table-hover" id="listGenerateRate">
	        	<thead>
	        		<tr>
	        			<th style="width:5%;">#</th>
						<th style="width:30%;"><s:text name="label.name"/></th>
						<th style="width:25%;"><s:text name="label.last.update"/></th>
						<th style="width:25%;"><s:text name="label.last.update.by"/></th>
						<th style="width:15%;text-align: center;"><s:text name="label.manage"/></th>					 
					</tr>
				</thead>
				<tbody>
					<s:iterator value="generateRateManageList" status="hdrsts">
					<tr>
						<td style="text-align: left;"><s:property value="#hdrsts.index+1"/>
             	    	<td style="text-align: left;"><s:property value="fileName"/>  
             	    	<td style="text-align: left;"><s:date name="changeDate" format="yyyy/MM/dd HH:mm:ss" /></td>
             	    	<td style="text-align: left;"><s:property value="changeUser" /></td>
				        <td style="text-align: center;"> 
				        <modong:sys-permission function="ViewGenerateRate">
				        <s:url var="viewURL" action="prepareViewGenerateRate"><s:param name="parm" value="%{id}" /></s:url>
				        <s:a href="%{viewURL}">
				            <button class="btn btn-success btn-sm" title="<s:text name="label.view.detail"/>">
				            	<i class="fa fa-file"></i>
				            </button>
						</s:a>
						</modong:sys-permission>
						<modong:sys-permission function="EditGenerateRate">
						<s:url var="editURL" action="prepareEditGenerateRate"><s:param name="parm" value="%{id}" /></s:url>
				        <s:a href="%{editURL}">
				           	<button class="btn btn-primary btn-sm" title="<s:text name="msg.edit"/>">
				           		<i class="glyphicon glyphicon-edit"></i>
				           	</button>
				        </s:a>
				        </modong:sys-permission>
				        <modong:sys-permission function="DeleteGenerateRate">
				        	<button class="btn btn-danger btn-sm" onclick="onClickDelete('<s:property value="id"/>')" title="<s:text  name="msg.remove"/>">
				            	<i class="fa fa-trash-o"></i>
				            </button>
				        </modong:sys-permission>		    
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
		
		$('#listGenerateRate').dataTable({
	 		searching: false,
	 		order:[[1,"asc"]],
	 		columnDefs:[{
	 			orderable: false, targets: [0,4], visible: true
	 		}]
	 	});
		
		reDrawNumberGenerateRateTable();
		   
	   $('#listGenerateRate').on('order.dt',function(){
		   reDrawNumberGenerateRateTable();
   	   })
	});
	
	function reDrawNumberGenerateRateTable(){
		
		var sizeAr = "<s:property value="generateRateManageList.size()"/>";
		
		if(sizeAr == '0'){
			return false;
		}
		
  		var runningNumber = 1;
  		$('#listGenerateRate').find("tr").each(function(i,obj){
  			if(i > 0){
  				$(obj).find("td:nth-child(1)").html(runningNumber);
			  	runningNumber++;
		  	} 
	   	});
  	}
	
	function onClickDelete(id){
		bootbox.confirm({
	        title: "Confirm Remove!",
	        message: "Do you want to remove this Record? This cannot be undone.",
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
	            	$(location).attr('href','removeGenerateRate?parm=' + id);
	            }
	        }
	    });
	}
	
</script>
</body>
</html>