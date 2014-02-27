<jsp:include page="./include/header.jsp" flush="true">
	<jsp:param name="title" value="Forum" /> 
</jsp:include>
<jsp:include page="./include/body.jsp" flush="true" />
<br>
<jsp:include page="./include/table_start.jsp" flush="true" /> 
<jsp:include page="./include/table_title.jsp" flush="true">
	<jsp:param name="title" value="Info" /> 
	<jsp:param name="colspan" value="1" /> 
	<jsp:param name="align" value="left" /> 
</jsp:include>						
<jsp:include page="./include/table_start_body.jsp" flush="true" />
<jsp:include page="./include/table_body.jsp" flush="true">
	<jsp:param name="width" value="0" /> 
</jsp:include>
<% String action = request.getParameter("action"); %>
<% if(action.equals("regcomplete")){ %>
	Your registration has been succesfull completed
	<br><br>
	<a href=".\index.jsp">To the forum</a>
<% }else if(action.equals("wrongpass")){ %>
	The password doesn't match
	<br><br>
<a href="javascript:history.go(-1)">Back</a>
<% }else if(action.equals("userexists")){ %>
	Username exists in database
	<br><br>
	<a href=javascript:history.go(-1)>Back</a>
<% }else{ %>
	Wrong Action
<% } %>

<jsp:include page="./include/table_close_body.jsp" flush="true" />				 
<jsp:include page="./include/table_close.jsp" flush="true" />
<jsp:include page="./include/footer.jsp" flush="true" />