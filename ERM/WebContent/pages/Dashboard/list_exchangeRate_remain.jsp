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
 </head>
 
 <body>	 
	<!--EXCHANGE RATE LIST -->
	 
						<div class="bg-primary"><h3 class="text-white"><i class="subject-text glyphicon glyphicon-time"></i><span class="subject-text"><s:text name="label.manual.remaining" /></span></h3></div>
   			          <table class="table table-bordered  table-striped table-hover" id="remainTbl">
				                      <thead>
										  <tr>
											<th rowspan="2"  ><s:text name="label.base.currency"/></th>
										  	<th rowspan="2"  ><s:text name="label.pair.currency"/></th>
										  </tr>
				                      </thead>
				                      <tbody>
				                      	    <s:iterator value="remainList" status="hdrsts">
				                      	    		<tr>
							                      	       <td style="text-align: left;"><s:property value="baseCode" /></td>
							                      	       <td style="text-align: left;"><s:property value="pairCode" /></td>
				                      	    		</tr>
				                      	    </s:iterator>
				                      </tbody>
				       </table>
	 
					                
  
    
</body>
</html>