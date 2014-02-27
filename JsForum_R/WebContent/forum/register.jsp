<jsp:include page="./include/header.jsp" flush="true">
	<jsp:param name="title" value="Forum" /> 
</jsp:include>
<jsp:include page="./include/body.jsp" flush="true" />
<br>
<jsp:include page="./include/table_start.jsp" flush="true" /> 
<jsp:include page="./include/table_title.jsp" flush="true">
	<jsp:param name="title" value="Register" /> 
	<jsp:param name="colspan" value="1" /> 
	<jsp:param name="align" value="left" /> 
</jsp:include>						
<jsp:include page="./include/table_start_body.jsp" flush="true" />
<jsp:include page="./include/table_body.jsp" flush="true">
	<jsp:param name="width" value="0" /> 
</jsp:include>

<form action="../servlet/forum.AddUser" method=POST name=form>
	Username:<br>
	<input type="text" name="user" size="62" maxlength="60"><br>
	Password:<br>
	<input type="password" name="password" size="62" maxlength="60"><br>
	Retype Password:<br>
	<input type="password" name="password2" size="62" maxlength="60"><br>
	E-mail:<br>
	<input type="text" name="email" size="62" maxlength="60"><br>
	<input type="submit" value="Register"><br>
</form>

<jsp:include page="./include/table_close_body.jsp" flush="true" />				 
<jsp:include page="./include/table_close.jsp" flush="true" />
<jsp:include page="./include/footer.jsp" flush="true" />