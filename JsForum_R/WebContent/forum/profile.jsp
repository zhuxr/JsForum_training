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
	
<%

DBConnectie db = new DBConnectie(Variable.getDb(),Variable.getDbLogin(),Variable.getDbPassword());

db.connect();

ResultSet rs = db.selectQuery(
	"SELECT * FROM forum_users " +
    "WHERE user_name =\"" + sessionUsername + "\"");
                    	
String avatar = "no Avatar";
String member_title = "no Custom member title";
String signature = "no Signature";
					
while(rs.next()){
	avatar = rs.getString("avatar");
	member_title = rs.getString("member_title");
	signature = rs.getString("signature");
}
			
if(avatar == null){
	avatar = "";
}
if(member_title == null){
	member_title = "";
}
if(signature == null){
	signature = "";
}

db.close();
%>			 
<jsp:include page="./include/header.jsp" flush="true">
	<jsp:param name="title" value="Profile" /> 
</jsp:include>
<jsp:include page="./include/body.jsp" flush="true" />
<% if( sessionUsername != null){%>
	<table width="100%" height="20" border="0" cellpadding="1" cellspacing="1">
		<tr> 
			<td height="20" valign="top" class="pathBar">
				<a href="./index.jsp" class="pathBarLink"><%= Variable.getForumName() %></a>
				<span class="pathBarSeperator"> > </span>
				<a href="./index.jsp?page=profile" class="pathBarLink">Profile</a>
			</td>
		</tr>
	</table>   
	<jsp:include page="./include/table_start.jsp" flush="true" />        
	<jsp:include page="./include/table_title.jsp" flush="true">
		<jsp:param name="title" value="Your Profile" /> 
		<jsp:param name="colspan" value="2" /> 
		<jsp:param name="align" value="left" /> 
	</jsp:include>	    
	<jsp:include page="./include/table_start_body.jsp" flush="true" />
	<jsp:include page="./include/table_body.jsp" flush="true">
		<jsp:param name="width" value="0" /> 
	</jsp:include>
	<b>Change Avatar:</b>
	<form action="../servlet/forum.ChangeProfile" method="POST" name="profile">

	<jsp:include page="./include/avatars.jsp" flush="true" />
	<img src="<%= avatar %>" name="img" width="65" height="65" border="0" hspace="15">
	<br>
	<br>
	<b>Link to avatar</b>
	<br>
	The following file types are allowed:
	<br>
	<i>.gif .jpeg .jpg .png</i>
	<br>
	<input type="text" name="link_avatar" value="<%= avatar %>" size="30">
	<jsp:include page="./include/table_body.jsp" flush="true">
		<jsp:param name="width" value="0" /> 
	</jsp:include>
	<b>Custom member title:</b>
	<br>
	<input type="text" name="member_title" value="<%= member_title %>" maxlenght="30" size="30">
	<br>
	<br>
	<b>Signature:</b>
	<br>
	<textarea name="signature" maxlenght="1000" cols="23" rows="5"><%= signature %></textarea>
	<br>
	<jsp:include page="./include/table_close_body.jsp" flush="true" />		
	<jsp:include page="./include/table_start_body.jsp" flush="true" />
	<jsp:include page="./include/table_body.jsp" flush="true">
		<jsp:param name="width" value="0" /> 
	</jsp:include>
	<input type="submit" value="Change">
	</form>
	<jsp:include page="./include/table_body.jsp" flush="true">
		<jsp:param name="width" value="0" /> 
	</jsp:include>
	<jsp:include page="./include/table_close_body.jsp" flush="true" />	
	<jsp:include page="./include/table_start_title.jsp" flush="true" />
	<jsp:include page="./include/table_title.jsp" flush="true">
		<jsp:param name="title" value="<a href=javascript:history.go(-1);>back</a>" /> 
		<jsp:param name="colspan" value="2" /> 
		<jsp:param name="align" value="left" /> 
	</jsp:include>
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