<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@page import="com.esynergy.erm.web.action.IPageContains"%>
<%
if(session.getAttribute(IPageContains.SESSION_USER)==null){
%>
	<script type="text/javascript">
	window.location = 'prepareLogon';
</script>
<%
}
%>