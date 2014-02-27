<%@ page contentType='text/html; charset=UTF-8' %>
<%@ page import='java.io.*' %>
<%@ page import='javax.servlet.*' %>
<%@ page import='javax.servlet.http.*' %>
<%@ page import='java.sql.*' %>
<%@ page import='java.sql.Connection' %>
<%@ page import='java.sql.Statement' %>
<%@ page import='java.sql.ResultSet' %>
<%@ page import='java.util.*' %>
<%@ page import='forum.*' %>

<% String sessionUsername = (String)session.getAttribute("username"); %>
<% String sessionPassword = (String)session.getAttribute("password"); %>
<% String sessionType = (String)session.getAttribute("type"); %>

<jsp:include page="./include/header.jsp" flush="true">
	<jsp:param name="title" value="Forum" /> 
</jsp:include>
<jsp:include page="./include/body.jsp" flush="true" />

<% if( sessionUsername != null){%>
	<table width="100%" height="20" border="0" cellpadding="1" cellspacing="1">
		<tr> 
			<td height="20" valign="top" class="pathBar">
				<a href="index.jsp" class="pathBarLink"><%= Variable.getForumName() %></a>
			</td>
		</tr>
	</table>   
	<jsp:include page="./include/table_start.jsp" flush="true" />                   
	<jsp:include page="./include/table_title.jsp" flush="true">
		<jsp:param name="title" value="Forum" /> 
		<jsp:param name="colspan" value="1" /> 
		<jsp:param name="align" value="middle" /> 
	</jsp:include>	               
	<jsp:include page="./include/table_title.jsp" flush="true">
		<jsp:param name="title" value="Topics" /> 
		<jsp:param name="colspan" value="1" /> 
		<jsp:param name="align" value="middle" /> 
	</jsp:include>	
	<jsp:include page="./include/table_title.jsp" flush="true">
		<jsp:param name="title" value="Replies" /> 
		<jsp:param name="colspan" value="1" /> 
		<jsp:param name="align" value="middle" /> 
	</jsp:include>	
	<jsp:include page="./include/table_title.jsp" flush="true">
		<jsp:param name="title" value="Last Post Info" /> 
		<jsp:param name="colspan" value="1" /> 
		<jsp:param name="align" value="middle" /> 
	</jsp:include>	   

	<% if(sessionType.equals("Admin")){ %>
		<jsp:include page="./include/table_title.jsp" flush="true">
			<jsp:param name="title" value="Delete Forum" /> 
			<jsp:param name="colspan" value="1" /> 
			<jsp:param name="align" value="middle" /> 
		</jsp:include>
	<% } %>	

	<%
		
		DBConnectie db = new DBConnectie(Variable.getDb(),Variable.getDbLogin(),Variable.getDbPassword());
			
		db.connect();

		ResultSet rs = db.selectQuery(
			"SELECT * FROM forum_forums ORDER BY title");

		while(rs.next()){
			String forum_id = rs.getString("forum_id");
			String title = Utilities.getforumTile(forum_id);
			String info = Utilities.getforumInfo(forum_id);
			String topics = Utilities.getTopics(forum_id);
			String forumReplies = Utilities.getforumReplies(forum_id);
			String lastPostInfo = Utilities.lastPostInfo(forum_id);
	%>						
		<jsp:include page="./include/table_start_body.jsp" flush="true" />
		<jsp:include page="./include/table_body.jsp" flush="true">
			<jsp:param name="width" value="0" /> 
		</jsp:include>						
		<a href="./index.jsp?page=thread&forum_id=<%= forum_id %>" class="forumLink"><%= title %></a>
		<br>
		<span class="forumInfo"><%= info %></span>
		<jsp:include page="./include/table_body.jsp" flush="true">
			<jsp:param name="width" value="0" /> 
		</jsp:include>	
		<span class="forumTopics"><%= topics %></span>
		<jsp:include page="./include/table_body.jsp" flush="true">
			<jsp:param name="width" value="0" /> 
		</jsp:include>
		<span class="forumReplies"><%= forumReplies %></span>
		<jsp:include page="./include/table_body.jsp" flush="true">
			<jsp:param name="width" value="0" /> 
		</jsp:include>					
		<span class="forumPostInfo"><%= lastPostInfo %></span>

		<% if(sessionType.equals("Admin")){ %>
			<jsp:include page="./include/table_body.jsp" flush="true">
				<jsp:param name="width" value="0" /> 
			</jsp:include>
			<a href="../servlet/forum.DeleteForum?forum_id=<%= forum_id %>" class="forumDelete">Delete</a>
		<% } %>	 
	<% } %>
	<jsp:include page="./include/table_close_body.jsp" flush="true" /> 				
	<%		


		ResultSet rs2 = db.selectQuery(
			"SELECT MAX(forum_id) forum_id FROM forum_forums");
						
		String lastforum_id = "null";
		while(rs2.next()){
			lastforum_id = rs2.getString("forum_id");
				
			if(lastforum_id == null){
				lastforum_id = "-1";
			}
		}
		
		db.close();       	    
	%>			

	<jsp:include page="./include/table_start_title.jsp" flush="true" /> 
	<jsp:include page="./include/table_title.jsp" flush="true">
		<jsp:param name="title" value="&nbsp;" /> 
		<jsp:param name="colspan" value="5" /> 
		<jsp:param name="align" value="left" /> 
	</jsp:include>			
	<jsp:include page="./include/table_close.jsp" flush="true" /> 
	<br>
	<% if(sessionType.equals("Admin")){ %>					
					
		<jsp:include page="./include/table_start.jsp" flush="true" /> 
		<jsp:include page="./include/table_title.jsp" flush="true">
			<jsp:param name="title" value="Add Forum" /> 
			<jsp:param name="colspan" value="2" /> 
			<jsp:param name="align" value="left" /> 
		</jsp:include>						
		<jsp:include page="./include/table_start_body.jsp" flush="true" />
		<jsp:include page="./include/table_body.jsp" flush="true">
			<jsp:param name="width" value="200" /> 
		</jsp:include>
		<form action="../servlet/forum.AddForum" method=POST name=form>
		<input type="hidden" name="lastforum_id" value="<%= lastforum_id %>"><br>
		Title:<br>
		<input type="text" name="title" size="62" maxlength="60"><br>
		Forum Information:<br>
		<textarea name="forum_info" cols="47" rows="10" maxlength="500"></textarea><br>
		<input type="submit" value="Add Forum"><br>
		</form>
		<br>
		<jsp:include page="./include/table_close_body.jsp" flush="true" />				 
		<jsp:include page="./include/table_close.jsp" flush="true" />
	<% } %>              
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
