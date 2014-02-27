package forum;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout extends HttpServlet {
	
        public void doPost (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        
        PrintWriter out = response.getWriter();
        try{ 
			//Cookie[] cookies = request.getCookies();
			HttpSession session = request.getSession(true);
			
			session.removeAttribute("username");
			session.removeAttribute("password");
			session.removeAttribute("type");
			/*
			if (cookies != null){
		 		Cookie cookie;
				for(int i=0;i<cookies.length;i++){
					cookie = cookies[i];
					if(cookie.getName().equals("username")){
						cookie.setMaxAge(0);
						response.addCookie(cookie);
						session.removeAttribute("username");
					}else if(cookie.getName().equals("password")){
						cookie.setMaxAge(0);
						response.addCookie(cookie);
						session.removeAttribute("password");
					}else if(cookie.getName().equals("type")){
						cookie.setMaxAge(0);
						response.addCookie(cookie);
						session.removeAttribute("type");
					}
					
				}					
			}*/
                    	
			response.sendRedirect(Variable.getForumPath() + "index.jsp");
			
			}catch(Exception e){out.println(e);}
        }
        public void doGet (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{ 
                doPost(request, response);
        }

}