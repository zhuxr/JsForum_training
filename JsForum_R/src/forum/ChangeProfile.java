package forum;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ChangeProfile extends HttpServlet {

        DBConnectie db = new DBConnectie(Variable.getDb(),Variable.getDbLogin(),Variable.getDbPassword());

        public void doPost (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        	PrintWriter out = response.getWriter();
        	    try{ 
					
					HttpSession session = request.getSession(true);
					String sessionUsername = (String)session.getAttribute("username");
					String sessionType = (String)session.getAttribute("type");
					
					String avatar_list = request.getParameter("avatar_list");
					String link_avatar = request.getParameter("link_avatar");
                    String member_title = request.getParameter("member_title");
                    String signature = request.getParameter("signature");
                    
                    member_title = Filter.filterAll(member_title);
                    signature = Filter.filterAll(signature);
                    
                    String avatar = null;
                    if(link_avatar.equals("http://")){
                    	avatar = avatar_list;
                    }else{
                    	avatar = link_avatar;
                    }
                    	                  
	                db.connect();
                    
                    db.query("INSERT INTO forum_users(user_name) VALUES(\"" + sessionUsername + "\"");
    	            db.query("UPDATE forum_users SET type = \"" + sessionType + "\" WHERE user_name =\"" + sessionUsername + "\"");
    	            db.query("UPDATE forum_users SET avatar = \"" + avatar + "\" WHERE user_name =\"" + sessionUsername + "\"");
					db.query("UPDATE forum_users SET member_title = \"" + member_title + "\" WHERE user_name =\"" + sessionUsername + "\"");
					db.query("UPDATE forum_users SET signature = \"" + signature + "\" WHERE user_name =\"" + sessionUsername + "\"");
        	        
        	        response.sendRedirect(Variable.getForumPath() + "index.jsp?page=profile");
        	        db.close();
              	
                }catch(Exception e){out.println(e);}
                
             
        }
        public void doGet (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{ 
                doPost(request, response);
        }

}