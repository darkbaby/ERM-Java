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
     <link href="<s:url value="/resources/assets/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css"/>" rel="stylesheet" />

</head>
 
 <body>	 
	<!-- ADD EXCHANGE RATE -->
          	<div class="row mt">
          		<div class="col-lg-12">
          			<div class="form-panel">
                  	  <h4 class="mb"><i class="glyphicon glyphicon-usd"></i><span class="subject-text"><s:text name="header.exchange.rate"/></span></h4>
                        <table style="width:100%">
							<tr>
								<td style="width:10%">
								<s:hidden name="exchangeRateAuto.id"/>
								 <label class="col-sm-5 control-label"><strong><s:text name="label.extract.date.time2"/> : </strong></label>
									<s:date name="exchangeRateAuto.createdDate" format='%{getText("ui.struts.datetime.format")}'/>
								</td>
								<td style="width:5%">
								 <label class="col-sm-5 control-label"><strong><s:text name="label.rate.date"/> : </strong></label>
									<s:date name="exchangeRateAuto.rateDate" format='%{getText("ui.struts.date.format")}'/>
								</td>
							</tr>
							<tr>
								<td style="width:10%"> 
									<label class="col-sm-5 control-label"><strong><s:text name="label.bank.name"/> : </strong></label>
									<s:property value="%{exchangeRateAuto.bankOfRate.iterator.next.bank.bankName}" />
									 
								</td>
								<td style="width:6%"> 
									<label class="col-sm-6 control-label"><strong><s:text name="label.bank.country"/> : </strong></label>
									<s:property value="%{exchangeRateAuto.bankOfRate.iterator.next.bank.country.countryName}" />
								</td>
								<td style="width:10%"> </td>
							</tr>
						</table>
                      <br>
                     <div><table align="right" style="margin-bottom:5px">
                   
                      </table></div>	 
 						<table id="rateTbl" class="table table-striped table-advance table-hover table-bordered" style="width:100%">
						  <tr>
						  	 
						  	<th style="text-align: center" rowspan="2">#</th>
							<th style="text-align: center" rowspan="2"><s:text name="label.currency"/></th>
                            <th style="text-align: center" rowspan="2"><s:text name="label.value"/></th>
                            <th style="text-align: center" colspan="2"><s:text name="label.exchenge.rate"/></th>
						  </tr>
						  <tr>
						    
						     
							<th style="text-align: center"><s:text name="label.buying.rate"/></th>
							<th style="text-align: center"><s:text name="label.selling.rate"/></th>
						  </tr>
						  <s:iterator value="exchangeRateAutoForm.exchangeRateAutoDetails" status="sts">
						  <tr>
						  			<td style="text-align:center;"><s:property value="#sts.index+1" /></td>
									<td style="text-align:center;"><s:property value="currencyCode"/></td>
									<td style="text-align:right;" ><s:property value="value"/></td>
									<td style="text-align:right;" >
										<s:property value="buyingRate"/></td>
									
								 	<td style="text-align:right;" >
										<s:property value="sellingRate"/>
								 	</td>
								  </tr>
							 </s:iterator>
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
								<s:iterator value="exchangeRateAuto.fileUploadERAutoList" status="st">
								<tr>
										<td style="text-align:center;"><s:property value="#st.index+1"/>    </td>
									<s:if test="id > 0">
										<td> 
										                  
							               <s:property value="name"/>
										</td>
										<td style="text-align:center;">
												<s:url var="donwloadFileUrl" action="downloadFileERByAuto">
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

</body>
</html>