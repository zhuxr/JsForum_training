
package forum;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddThread extends HttpServlet {

        DBConnectie db = new DBConnectie(Variable.getDb(),Variable.getDbLogin(),Variable.getDbPassword());

        public void doPost (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        	PrintWriter out = response.getWriter();
        	    try{    
                	String forum_id = request.getParameter("forum_id");
                    int lastThread_id = Integer.parseInt(request.getParameter("lastThread_id"));
					String thread_id = Integer.toString(lastThread_id + 1);
					
					String title = request.getParameter("title");
					
					
					if(title.equals("")){
						title = "No title";
					}else{
						title = Filter.filterAll(title);
					}
					
					String message = request.getParameter("message");
					message = Filter.filterAll(message);
					String user = request.getParameter("user");
                                   
                    db.connect();
                    
                    db.query(
                    	"INSERT INTO forum_threads(forum_id,thread_id,title) "+
                    	"VALUES(\"" + forum_id + 
                    	"\",\"" + thread_id + 
                    	"\",\"" + title + "\")");
                    
                    db.query(
                    	"INSERT INTO forum_message(forum_id,thread_id,reply_id,message,user,date_time) "+
                    	"VALUES(\"" + forum_id + 
                    	"\",\"" + thread_id + 
                    	"\",\"0" +
                    	"\",\"" + message + 
                    	"\",\"" + user +
                    	"\",SYSDATE())");
					 					 
                    db.close();
           		
              		response.sendRedirect(Variable.getForumPath() + "index.jsp?page=thread&forum_id=" + forum_id);
              		
              	
                }catch(Exception e){out.println(e);}
                
             
        }
        public void doGet (HttpServletRequest request, HttpServletResponse response)//{
        throws ServletException, IOException{ 
                doPost(request, response);
        }

}