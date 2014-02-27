<%@ page contentType='text/html; charset=UTF-8' %>
<%@ page import='java.io.*' %>
<%@ page import='javax.servlet.*' %>
<%@ page import='javax.servlet.http.*' %>
<%@ page import='java.sql.*' %>
<%@ page import='java.sql.Connection' %>
<%@ page import='java.sql.Statement' %>
<%@ page import='java.sql.ResultSet' %>
<%@ page import='forum.*' %>

<% String sessionUsername = (String)session.getAttribute("username"); %>
<% String sessionPassword = (String)session.getAttribute("password"); %>
<% String sessionType = (String)session.getAttribute("type"); %>

<% String forum_id = request.getParameter("forum_id"); %> 

 <jsp:include page="./include/header.jsp" flush="true">
	<jsp:param name="title" value="Read Thread" /> 
</jsp:include>	 
<jsp:include page="./include/body.jsp" flush="true" />
<% if( sessionUsername != null){%>
	<table width="100%" height="20" border="0" cellpadding="1" cellspacing="1">
		<tr> 
			<td height="20" valign="top" class="pathBar">
				<a href="./index.jsp" class="pathBarLink"><%= Variable.getForumName() %></a><span class="pathBarSeperator"> > </span>
				<a href="./index.jsp?page=thread&forum_id=<%= forum_id %>" class="pathBarLink"><%= Utilities.getforumTile(forum_id) %></a>
			</td>
		</tr>
	</table>
	<jsp:include page="./include/table_start.jsp" flush="true" />                   
	<jsp:include page="./include/table_title.jsp" flush="true">
		<jsp:param name="title" value="Topic Title" /> 
		<jsp:param name="colspan" value="1" /> 
		<jsp:param name="align" value="middle" /> 
	</jsp:include>	               
	<jsp:include page="./include/table_title.jsp" flush="true">
		<jsp:param name="title" value="Topic Starter" /> 
		<jsp:param name="colspan" value="1" /> 
		<jsp:param name="align" value="middle" /> 
	</jsp:include>	
	<jsp:include page="./include/table_title.jsp" flush="true">
		<jsp:param name="title" value="Post On" /> 
		<jsp:param name="colspan" value="1" /> 
		<jsp:param name="align" value="middle" /> 
	</jsp:include>	
	<jsp:include page="./include/table_title.jsp" flush="true">
		<jsp:param name="title" value="Replies" /> 
		<jsp:param name="colspan" value="1" /> 
		<jsp:param name="align" value="middle" /> 
	</jsp:include>
	<jsp:include page="./include/table_title.jsp" flush="true">
		<jsp:param name="title" value="Views" /> 
		<jsp:param name="colspan" value="1" /> 
		<jsp:param name="align" value="middle" /> 
	</jsp:include>
	<jsp:include page="./include/table_title.jsp" flush="true">
		<jsp:param name="title" value="Last Action" /> 
		<jsp:param name="colspan" value="1" /> 
		<jsp:param name="align" value="middle" /> 
	</jsp:include>	   	    	                   
	<% if(sessionType.equals("Admin")){ %>
		<jsp:include page="./include/table_title.jsp" flush="true">
			<jsp:param name="title" value="Delete Message" /> 
			<jsp:param name="colspan" value="1" /> 
			<jsp:param name="align" value="middle" /> 
		</jsp:include>
	<% } %>	 

	<%
	DBConnectie db = new DBConnectie(Variable.getDb(),Variable.getDbLogin(),Variable.getDbPassword());
			
	db.connect();

	ResultSet rs = db.selectQuery("SELECT DISTINCT thread_id FROM forum_message WHERE forum_id=\""+ forum_id +"\" ORDER BY thread_id DESC");

	while(rs.next()){
		String thread_id = rs.getString("thread_id");
				
		ResultSet rs2 = db.selectQuery(
			"SELECT * "+
			"FROM forum_message "+
			"WHERE forum_id=\"" + forum_id + "\" " + 
			"AND thread_id=\""+ thread_id + "\" " +
			"AND reply_id =\"0\"");
								
		while(rs2.next()){
			String title = Utilities.getThreadTile(forum_id,thread_id);
			String user = rs2.getString("user");
			String date_time = rs2.getString("date_time");
	%>
			<jsp:include page="./include/table_start_body.jsp" flush="true" />
			<jsp:include page="./include/table_body.jsp" flush="true">
				<jsp:param name="width" value="0" /> 
			</jsp:include>							
			<a href="./index.jsp?page=message&forum_id=<%= forum_id %>&thread_id=<%= thread_id %>&start=0"><%= title %></a><%= Utilities.getMorePages(Utilities.getReplies(forum_id,thread_id),forum_id,thread_id,true) %>
			<jsp:include page="./include/table_body.jsp" flush="true">
				<jsp:param name="width" value="0" /> 
			</jsp:include>								
			<%= user %>	
				<jsp:include page="./include/table_body.jsp" flush="true">
					<jsp:param name="width" value="0" /> 
				</jsp:include>								
			<%= date_time %>	
			<jsp:include page="./include/table_body.jsp" flush="true">
				<jsp:param name="width" value="0" /> 
			</jsp:include>								
			<%= Utilities.getReplies(forum_id,thread_id) %>								
			<jsp:include page="./include/table_body.jsp" flush="true">
				<jsp:param name="width" value="0" /> 
			</jsp:include>								
			<%= Utilities.getViews(forum_id,thread_id) %>
			<jsp:include page="./include/table_body.jsp" flush="true">
				<jsp:param name="width" value="0" /> 
			</jsp:include>								
			<%= Utilities.lastActionInfo(forum_id,thread_id) %>
										
			<% if(sessionType.equals("Admin")){ %>
				<jsp:include page="./include/table_body.jsp" flush="true">
					<jsp:param name="width" value="0" /> 
				</jsp:include>
				<a href="../servlet/forum.DeleteThread?forum_id=<%= forum_id %>&thread_id=<%= thread_id %>">Delete</a>
			<% } %>
		<% } %>
	<% } %>
	<jsp:include page="./include/table_close_body.jsp" flush="true" /> 
	<%		
		ResultSet rs3 = db.selectQuery(
			"SELECT MAX(thread_id) thread_id FROM forum_threads");
						
		String lastThread_id = "null";
		while(rs3.next()){
			lastThread_id = rs3.getString("thread_id");
						
			if(lastThread_id == null){
				lastThread_id = "-1";
			}
		}
		
		db.close();       	    
	%>							

	<jsp:include page="./include/table_start_title.jsp" flush="true" /> 
	<jsp:include page="./include/table_title.jsp" flush="true">
		<jsp:param name="title" value="<a href=\"./index.jsp\">Back</a>" /> 
		<jsp:param name="colspan" value="7" /> 
		<jsp:param name="align" value="left" /> 
	</jsp:include>			
	<jsp:include page="./include/table_close.jsp" flush="true" /> 
	<br>
	<jsp:include page="./include/table_start.jsp" flush="true" /> 
	<jsp:include page="./include/table_title.jsp" flush="true">
		<jsp:param name="title" value="Add Thread" /> 
		<jsp:param name="colspan" value="3" /> 
		<jsp:param name="align" value="left" /> 
	</jsp:include>					
	<jsp:include page="./include/table_start_body.jsp" flush="true" />
	<jsp:include page="./include/table_body.jsp" flush="true">
		<jsp:param name="width" value="200" /> 
	</jsp:include>
	<form action="../servlet/forum.AddThread" method="POST" name="formmessage">
	<input type="hidden" name="forum_id" value="<%= forum_id %>">
	<input type="hidden" name="lastThread_id" value="<%= lastThread_id %>">
	<input type="hidden" name="user" value="<%= sessionUsername %>">
	Title:<br>
	<input type="text" name="title" size="62" maxlength="60"><br>
	Message:<br>
	<textarea name="message" cols="47" rows="10" maxlength=3000></textarea><br>
	<input type="submit" value="submit"><br>
	</form>
	<jsp:include page="./include/table_body.jsp" flush="true">
		<jsp:param name="width" value="200" /> 
	</jsp:include>
	<jsp:include page="./include/textStyle.jsp" flush="true" />

	<jsp:include page="./include/table_body.jsp" flush="true">
		<jsp:param name="width" value="200" /> 
	</jsp:include>
	<jsp:include page="./include/emoticons.jsp" flush="true" />
	<jsp:include page="./include/table_close_body.jsp" flush="true" />				 
	<jsp:include page="./include/table_close.jsp" flush="true" /> 
<% }else { %>
	<br>
	<jsp:include page="./include/table_start.jsp" flush="true" /> 
	<jsp:include page="./include/table_title.jsp" flush="true">
		<jsp:param name="title" value="Error" /> 
		<jsp:param name="colspan" value="1" /> 
		<jsp:param name="align" value="left" /> 
	</jsp:include>						
	<jsp:include page="./include/table_start_body.jsp" flush="true" />
	<jsp:include page="./include/table_body.jsp" flush="true">
		<jsp:param name="width" value="200" /> 
	</jsp:include>
	You have to login first !
	<br>
	<br>
	<a href="./register.jsp">Register</a>
	<jsp:include page="./include/table_close_body.jsp" flush="true" />				 
	<jsp:include page="./include/table_close.jsp" flush="true" />
<% } %>   
<jsp:include page="./include/footer.jsp" flush="true" />