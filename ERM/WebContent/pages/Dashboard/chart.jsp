<%@page import="java.util.List"%>
<%@page import="com.esynergy.erm.model.ob.Currency"%>
<%@page import="com.esynergy.erm.web.action.IPageContains"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<s:url value='/resources/assets/css/select2.min.css' />"
	rel="stylesheet" />
<link href="<s:url value='/resources/assets/css/c3.css' />"
	rel="stylesheet" />
</head>

<body>
	<!-- ADD EXCHANGE RATE -->
	<input type="hidden" id="apiUrl" value="<%=IPageContains.API_URL%>">
	<div class="row mt">
		<div class="col-md-6">
			<div class="form-panel">
				<h4>
					<span class="subject-text"><s:text
							name="header.chart.auto.process" /></span>
				</h4>
				<div id="chartAuto"></div>
				<table style="width: 100%">
					<tr>
						<td style="width: 2%">
						<td>
						<td align="center" style="width: 15%"><span
							id="legendSucChartAuto"></span></td>
						<td style="width: 2%">
						<td>
						<td align="center" style="width: 15%"><span
							id="legendFailChartAuto"></span></td>
						<td style="width: 2%">
						<td>
						<td align="center" style="width: 15%"><span
							id="legendRemChartAuto"></span></td>
						<td></td>
					</tr>
					<tr>
						<td style="width: 1%">
						<td>
						<td align="center" style="background-color: #2ecc71; width: 10%">
							<font color="white"><s:text name="label.success" /></font>
						</td>
						<td style="width: 1%">
						<td>
						<td align="center" style="background-color: firebrick; width: 10%">
							<font color="white"><s:text name="label.fail" /></font>
						</td>
						<td style="width: 1%">
						<td>
						<td align="center" style="background-color: #3498db; width: 10%">
							<font color="white"><s:text name="label.remaining" /></font>
						</td>
						<td style="width: 67%"></td>
					</tr>
				</table>
			</div>
			<!-- /form-panel -->
		</div>
		<!-- /col-lg-12 -->
		<div class="col-md-6">
			<div class="form-panel">
				<h4>
					<span class="subject-text"><s:text
							name="header.chart.manual.process" /></span>
				</h4>
				<div id="chartManual"></div>
				<table style="width: 100%">
					<tr>
						<td style="width: 2%">
						<td>
						<td align="center" style="width: 15%"><span
							id="legendSucChartManual"></span></td>
						<td style="width: 2%">
						<td>
						<td align="center" style="width: 15%"><span
							id="legendRemChartManual"></span></td>
						<td></td>
					</tr>
					<tr>
						<td style="width: 1%">
						<td>
						<td align="center" style="background-color: #2ecc71; width: 10%">
							<font color="white"><s:text name="label.success" /></font>
						</td>
						<td style="width: 1%">
						<td>
						<td align="center" style="background-color: #3498db; width: 10%">
							<font color="white"><s:text name="label.remaining" /></font>
						</td>
						<td style="width: 67%"></td>
					</tr>
				</table>
			</div>
		</div>
		<!-- /form-panel -->
	</div>
	<!-- /col-lg-12 -->
	</div>
	<!-- /row -->
	<script src="http://d3js.org/d3.v3.min.js" charset="utf-8"></script>
	<script src="<s:url value='/resources/assets/js/c3.js'/>"></script>
	<script src="<s:url value='/resources/assets/js/sparkline-chart.js' />"></script>
	<script src="<s:url value='/resources/assets/js/zabuto_calendar.js' />"></script>
	<script
		src="<s:url value='/resources/assets/js/chart-master/Chart.js' />"></script>
	<script
		src="<s:url value='/resources/assets/js/custom-util/dashboard-chartjs-conf.js' />"></script>
	<script
		src="<s:url value="/resources/assets/moment-master/min/moment.min.js"/>"></script>
	<script src="<s:url value="/resources/assets/js/bootbox.min.js"/>"></script>
	<script
		src="<s:url value="/resources/assets/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"/>"></script>


	<script type="text/javascript">
 //$('select').select2();
</script>
</body>
</html>