package forum;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ChangeMessage extends HttpServlet {
	
	DBConnectie db = new DBConnectie(Variable.getDb(),Variable.getDbLogin(),Variable.getDbPassword());
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
 	throws ServletException, IOException{
 		
 		PrintWriter out = response.getWriter();
 		
 		try{
			
			HttpSession session = request.getSession(true);
			String sessionUsername = (String)session.getAttribute("username");
			String sessionType = (String)session.getAttribute("type");
						
			String forum_id = request.getParameter("forum_id");
			String start = request.getParameter("start");
			String reqThread_id = request.getParameter("thread_id");
			String reqReply_id = request.getParameter("reply_id");
			String message = request.getParameter("message");
			message = Filter.filterAll(message);
						
			int changeDifference = (((100 * message.length()) / Utilities.getMessageLength(forum_id,reqThread_id,reqReply_id)));
			
						
			java.util.Date date_time = new java.util.Date();
			
			
			message += "<!-- begin --!><BR><BR><I>Edited by " + sessionUsername + " - " + date_time + " (" + changeDifference + "%)</I><!-- end --!>";

			db.connect();
			
			if(sessionType.equals("Admin")){
				db.query(
					"UPDATE forum_message " +
            		"SET message =\"" + message + "\"" +
            		"WHERE forum_id=\"" + forum_id + "\" AND thread_id =\"" + reqThread_id + "\" AND reply_id=\"" + reqReply_id + "\"");	
			}else{
				db.query(
					"UPDATE forum_message " +
            		"SET message =\"" + message + "\"" +
            		"WHERE forum_id=\"" + forum_id + "\" AND thread_id =\"" + reqThread_id + "\" AND reply_id=\"" + reqReply_id + "\" AND user=\""+ sessionUsername + "\"");
			}
			
			response.sendRedirect(Variable.getForumPath() + "index.jsp?page=message&forum_id=" + forum_id + "&thread_id=" + reqThread_id +"&start=" + start);
				
            db.close();
    	}catch(Exception e){out.println(e);}
	}
}