<% String sessionUsername = (String)session.getAttribute("username"); %>
<% String sessionType = (String)session.getAttribute("type"); %>
<Body>

<center><img src="./images/logo.jpg"></center>
<jsp:include page="table_start.jsp" flush="true" /> 
<jsp:include page="table_title.jsp" flush="true">
	<jsp:param name="title" value="Control Bar" /> 
	<jsp:param name="colspan" value="1" /> 
	<jsp:param name="align" value="left" /> 
</jsp:include>						
<jsp:include page="table_start_body.jsp" flush="true" />
<jsp:include page="table_body.jsp" flush="true">
	<jsp:param name="width" value="0" /> 
</jsp:include>
	<table width="100%" height="0" border="0" cellpadding="0" cellspacing="0" class="infobar">
		<tr> 
			<td width="50%" height="0" align="left" valign="top">
				<% if(sessionUsername != null){ %>
					<a href="./index.jsp?page=profile">Change Forum Profile</a>
				<% }else{ %>
					<form action="../servlet/forum.Login" method=POST name=form>
							Username:<input type="text" name="username" size="10" maxlength="60">
							Password:<input type="password" name="password" size="10" maxlength="60">
							<input type="submit" value="Login">
					</form>
				<% } %>
			</td>
			<td width="50%" height="0" align="right" valign="top">
				<% if(sessionUsername != null){ %>
					Logged in as: <%= sessionUsername %> Type: <%= sessionType %>
					&nbsp;&nbsp;[<a href="../servlet/forum.Logout">Logout</a>]		
				<% } %>
			</td>
		</tr>
	</table>
<jsp:include page="table_close_body.jsp" flush="true" />				 
<jsp:include page="table_close.jsp" flush="true" />

