package forum;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.*;

public class Login extends HttpServlet {

        DBConnectie db = new DBConnectie(Variable.getDb(),Variable.getDbLogin(),Variable.getDbPassword());

        public void doPost (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        	
        PrintWriter out = response.getWriter();
        
        	    try{ 
        	     
					String loginUser = request.getParameter("username");
                    String loginPass = request.getParameter("password");
                    
                    db.connect();
                    
                    String password = "null";
                    String password2 = "zero";
                    String type = "null";
                    
                    ResultSet rs = db.selectQuery(
                    	"SELECT * "+
                    	"FROM forum_users "+
                    	"WHERE user_name =\"" + loginUser + "\"");
                    while(rs.next()){
                    	password = rs.getString("password");
                    	type = rs.getString("type");
                    }
                    
                    
                    ResultSet rs2 = db.selectQuery(
                    	"SELECT "+
                    	"password(\""+ loginPass +"\") password");
                    while(rs2.next()){
                    	password2 = rs2.getString("password");	
					}

					
                    if(password2.equals(password)){
                    	HttpSession session = request.getSession(true);
                    	
                    	//Cookie cookie = new Cookie("username",loginUser);
                    	session.setAttribute("username",loginUser);
                    	//cookie.setMaxAge(60*60*24*365);
                    	//cookie.setPath("/");
                    	//response.addCookie(cookie);
                    	//cookie = new Cookie("password",password);
                    	session.setAttribute("password",password);
                    	//cookie.setMaxAge(60*60*24*365);
                    	//cookie.setPath("/");
                    	//response.addCookie(cookie);                    	
                    	//cookie = new Cookie("type",type);
                    	session.setAttribute("type",type);
                    	//cookie.setMaxAge(60*60*24*365);
                    	//cookie.setPath("/");
                    	//response.addCookie(cookie);
                    
                    	response.sendRedirect(Variable.getForumPath() + "index.jsp");
                    }else{
						response.sendRedirect(Variable.getForumPath() + "info.jsp?action=wrongpass");
                    }
                    
 					
                    db.close();
                    
                }catch(Exception e){out.println(e);}
                
             
        }
        public void doGet (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{      
                doPost(request, response);
        }

}