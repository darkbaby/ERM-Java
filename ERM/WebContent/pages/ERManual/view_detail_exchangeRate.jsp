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
     <link href="<s:url value='/resources/assets/css/select2.min.css' />" rel="stylesheet" />
      <link rel="stylesheet" href="<s:url value='/resources/assets/css/dataTables.bootstrap.css' />"/>
</head>
 
 <body>	 
	<!-- ADD EXCHANGE RATE -->
          	<div class="row mt">
          		<div class="col-lg-12">
          			<div class="form-panel">
                  	  <h4 class="mb"><i class="glyphicon glyphicon-usd"></i><span class="subject-text"><s:text name="header.exchange.rate"/></span></h4>
                        <table style="width:100%">
							<tr>
								<th style="width:30%"><s:text name="label.date"/> </th>
								<th></th>
							</tr>
							<tr>
								<td><s:hidden name="exchangeRateManual.id"/>
									<s:date name="exchangeRateManual.rateDate" format='%{getText("ui.struts.date.format")}'/>
								</td>
							</tr>
						</table>
                      <br>
                     <div><table align="right" style="margin-bottom:5px">
                   
                      </table></div>	 
 						<table id="rateTbl" class="table table-striped table-advance table-hover table-bordered" style="width:100%">
						   <thead>
						   <tr>
						  	<th></th>
							<th style="text-align: center" colspan="2"><s:text name="label.currency"/></th>
                            <th style="text-align: center" rowspan="2"><s:text name="label.value"/></th>
                            <th style="text-align: center" colspan="7"><s:text name="label.exchenge.rate"/></th>
						  </tr>
						  <tr>
						    <th>#</th>
						    <th style="text-align: center"><s:text name="label.first.currency"/></th>
						    <th style="text-align: center"><s:text name="label.pair.currency"/></th>				    
							<th style="text-align: center"><s:text name="label.buying.rate"/></th>
							<th style="text-align: center"><s:text name="label.selling.rate"/></th>
						  </tr> 
						  </thead> 
						  <tbody>
						  <s:iterator value="exchangeRateManual.exchangeRateDetails" status="sts">
						  		<tr>
						  			<td><s:property value="#sts.index+1" /></td>
						  			<td><s:property value="firstCurrency.code"/></td>
						  			<td><s:property value="pairCurrency.code"/></td>									
									<td style="text-align:right;" ><s:property value="value"/></td>
									<td style="text-align:right;" >
										<s:text name="format.number.exchange.rate">
											<s:param name="value" value="buyingRate"/>
										</s:text>
<%-- 									<s:property value="buyingRate"/> --%>
									</td>
								 	<td style="text-align:right;" >
								 		<s:text name="format.number.exchange.rate">
											<s:param name="value" value="sellingRate"/>
										</s:text>
<%-- 								 		<s:property value="sellingRate"/> --%>
								 	</td>
					 			     
								</tr>
							 </s:iterator>
						  </tbody>
						</table>
					 
						<h4 class="mb"><i class="glyphicon glyphicon-paperclip"></i><span class="subject-text"><s:text name="header.attract.file"/></span></h4>
							<table class="table table-striped table-advance table-hover table-bordered" >
							<thead>
								<tr>
									<th style="text-align: center">#</th>
									<th style="text-align: center">
										<s:text name="label.file"/>
									</th>
									<th style="text-align: center">
										<s:text name="label.option"/>
									</th>
								</tr>
							</thead>
						
							<tbody>
								<s:iterator value="exchangeRateManual.fileUploadERManualList" status="st">
								<tr>
										<td><s:property value="#st.index+1"/>    </td>
									<s:if test="id > 0">
										<td> 
										                  
							               <s:property value="name"/>
										</td>
										<td>
												<s:url var="donwloadFileUrl" action="downloadFileERByManual">
									               	  	 <s:param name="parm" value="id"/>
									            </s:url>
												<s:a href="%{donwloadFileUrl}" >       	 
							               	 		<span class="glyphicon glyphicon-download-alt" 
							               	 			 title="<s:text name='label.download'/>" 
							               	 			 style="padding: 2px 10px 3px 10px;">
							               	 		</span>
								               	</s:a>					                
										</td>
									</s:if>
								</tr>
								</s:iterator>
								</tbody>
								</table>
								
          			</div><!-- /form-panel -->
          		</div><!-- /col-lg-12 -->
          	</div><!-- /row --> 
<script src="<s:url value="/resources/assets/js/bootbox.min.js"/>"></script>
<script type="text/javascript" src="<s:url value='/resources/assets/js/jquery.dataTables.js'/>"></script>
<script type="text/javascript" src="<s:url value='/resources/assets/js/dataTables.bootstrap.js'/>"></script>
<script type="text/javascript">
    $(document).ready(function(){
 	   $('#rateTbl').dataTable({
 	    	searching: false,
 	    	 "order": [[ 1, "asc" ]],
 	    	 "lengthChange": false,
 	    	 "paging": false,
 	    	 "fnDrawCallback": function ( oSettings ) {
 	            /* Need to redo the counters if filtered or sorted */
 	            if ( oSettings.bSorted || oSettings.bFiltered )
 	            {
 	                for ( var i=0, iLen=oSettings.aiDisplay.length ; i<iLen ; i++ )
 	                {
 	                    $('td:eq(0)', oSettings.aoData[ oSettings.aiDisplay[i] ].nTr ).html( i+1 );
 	                }
 	            }
 	        },
 	    });
 	 
 	});  
  /*   $('#cancelBtn').click(function(){
    	
		window.history.back();
		
	}); */
</script>

</body>
</html>