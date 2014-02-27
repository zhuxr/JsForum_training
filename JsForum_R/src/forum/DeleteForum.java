package forum;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class DeleteForum extends HttpServlet {

        DBConnectie db = new DBConnectie(Variable.getDb(),Variable.getDbLogin(),Variable.getDbPassword());

        public void doPost (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        	    try{    
					
					HttpSession session = request.getSession(true);
					String sessionType = (String)session.getAttribute("type");
					
					String forum_id = request.getParameter("forum_id");
					
					if(sessionType.equals("Admin")){

                    	db.connect();
                    
                    	db.query("DELETE FROM forum_message WHERE forum_id=\"" + forum_id + "\"");
                    	db.query("DELETE FROM forum_forums WHERE forum_id=\"" + forum_id + "\"");
					 					 
                    	db.close();
           		
	              		response.sendRedirect(Variable.getForumPath() + "index.jsp");
    				}          		
              	
                }catch(Exception e){}
                
             
        }
        public void doGet (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{ 
                doPost(request, response);
        }

}