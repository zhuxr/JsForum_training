<% String requestPage = request.getParameter("page"); %>
<% 
if(requestPage == null){
	requestPage = "0"; 
}
%>
<% if(requestPage.equals("thread")){ %>
	<% String forum_id = request.getParameter("forum_id"); %>
	<jsp:include page="thread.jsp" flush="true" >
		<jsp:param name="forum_id" value="<%= forum_id %>" />
	</jsp:include>
<% }else if(requestPage.equals("message")){ %>
	<% String forum_id = request.getParameter("forum_id"); %>
	<% String thread_id = request.getParameter("thread_id"); %>
	<% String start = request.getParameter("start"); %>

	<jsp:include page="message.jsp" flush="true" >
		<jsp:param name="forum_id" value="<%= forum_id %>" />
		<jsp:param name="thread_id" value="<%= thread_id %>" />
		<jsp:param name="start" value="<%= start %>" />
	</jsp:include>
<% }else if(requestPage.equals("editmessage")){ %>
	<% String forum_id = request.getParameter("forum_id"); %>
	<% String thread_id = request.getParameter("thread_id"); %>
	<% String reply_id = request.getParameter("reply_id"); %>
	<% String start = request.getParameter("start"); %>

	<jsp:include page="editmessage.jsp" flush="true" >
		<jsp:param name="forum_id" value="<%= forum_id %>" />
		<jsp:param name="thread_id" value="<%= thread_id %>" />
		<jsp:param name="reply_id" value="<%= reply_id %>" />
		<jsp:param name="start" value="<%= start %>" />
	</jsp:include>
<% }else if(requestPage.equals("profile")){ %>
	<jsp:include page="profile.jsp" flush="true" />
<% }else{ %>
	<jsp:include page="forum.jsp" flush="true" />
<% } %>



