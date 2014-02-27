package forum;

import java.sql.ResultSet;

public class Utilities {
    
	public static String getTopics(String forum_id){
  		
		String topics = null;
		try{
			DBConnectie db = new DBConnectie(Variable.getDb(),Variable.getDbLogin(),Variable.getDbPassword());
			db.connect();
	
			ResultSet rs = db.selectQuery(
				"SELECT count(*) topics FROM forum_message " + 
				"WHERE forum_id =\"" + forum_id + "\" " +
				"AND reply_id=\"0\"");
						
			while(rs.next()){
				topics = rs.getString("topics");
			}
			db.close();
		}catch(Exception e){}
		
		return 	topics;											
  	}
  	
  	public static String getforumReplies(String forum_id){
  		
		String replies = null;
		try{
			DBConnectie db = new DBConnectie(Variable.getDb(),Variable.getDbLogin(),Variable.getDbPassword());
			db.connect();
	
			ResultSet rs = db.selectQuery(
				"SELECT count(*)-" + getTopics(forum_id) + " replies FROM forum_message " + 
				"WHERE forum_id =\"" + forum_id + "\"");
						
			while(rs.next()){
				replies = rs.getString("replies");
			}
			db.close();
		}catch(Exception e){}
		
		return 	replies;											
  	}

  	public static String getReplies(String forum_id,String thread_id){
  		
		String replies = null;
		try{
			DBConnectie db = new DBConnectie(Variable.getDb(),Variable.getDbLogin(),Variable.getDbPassword());
			db.connect();
	
			ResultSet rs = db.selectQuery(
				"SELECT count(*)-1 replies FROM forum_message " + 
				"WHERE forum_id=\"" +  forum_id + "\" AND thread_id =\"" + thread_id + "\"");
						
			while(rs.next()){
				replies = rs.getString("replies");
			}
			db.close();
		}catch(Exception e){}
		
		return 	replies;											
  	}

  	public static String getMorePages(String replies,String forum_id,String thread_id,boolean pages)
  	{	

  	  String morePages = "";
  		if(!((Integer.parseInt(replies)+1) < (Integer.parseInt(Variable.getMessagePerPage()) + 1))){
			
			if(pages){morePages += "&nbsp;( ";}
			
			for(int i=0;i< (Integer.parseInt(replies)+1);i += (Integer.parseInt(Variable.getMessagePerPage())) ){
				morePages +=
					"<a href=message.jsp?forum_id=" + forum_id + "&thread_id=" + thread_id + 
					"&start="+ i +
					" class=\"pathBarLink\">" + ((i/(Integer.parseInt(Variable.getMessagePerPage())))+1)+ "</a> ";
			}
			if(pages){morePages += " )</span>";}
		}
		return 	morePages;
	}
	
	public static String getforumTile(String forum_id){
		
		String forumTitle = null;
		try{
			DBConnectie db = new DBConnectie(Variable.getDb(),Variable.getDbLogin(),Variable.getDbPassword());
			db.connect();
	
			ResultSet rs = db.selectQuery(
				"SELECT title FROM forum_forums "+
				"WHERE forum_id=\""+ forum_id + "\"");
						
			while(rs.next()){
				forumTitle = rs.getString("title");
			}
			db.close();
		}catch(Exception e){}
		
		return 	forumTitle;
	}

	public static String getThreadTile(String forum_id,String thread_id){
		
		String threadTitle = null;
		try{
			DBConnectie db = new DBConnectie(Variable.getDb(),Variable.getDbLogin(),Variable.getDbPassword());
			db.connect();
	
			ResultSet rs = db.selectQuery(
				"SELECT title FROM forum_threads "+
				"WHERE forum_id=\""+ forum_id + "\" " + 
				"AND thread_id=\"" + thread_id + "\"");
						
			while(rs.next()){
				threadTitle = rs.getString("title");
			}
			db.close();
		}catch(Exception e){}
		
		return 	threadTitle;
	}
		
	public static String getforumInfo(String forum_id){
		
		String forumInfo = null;
		try{
			DBConnectie db = new DBConnectie(Variable.getDb(),Variable.getDbLogin(),Variable.getDbPassword());
			db.connect();
	
			ResultSet rs = db.selectQuery(
				"SELECT forum_info FROM forum_forums "+
				"WHERE forum_id=\""+ forum_id + "\"");
						
			while(rs.next()){
				forumInfo = rs.getString("forum_info");
			}
			db.close();
		}catch(Exception e){}
		
		if(forumInfo.equals(null)){
			forumInfo = "";	
		}
		
		return 	forumInfo;
	}
	

	public static String lastPostInfo(String forum_id){
		
		String lastPostInfo = null;
		String thread_id = null;
		String title = null;
		String date_time = null;
		String user = null;
		
		try{
			DBConnectie db = new DBConnectie(Variable.getDb(),Variable.getDbLogin(),Variable.getDbPassword());
			db.connect();
	
			ResultSet rs = db.selectQuery(
				"SELECT MAX(date_time) date_time FROM forum_message "+
				"WHERE forum_id=\""+ forum_id + "\"");
						
			while(rs.next()){
				date_time = rs.getString("date_time");
			}
				
			ResultSet rs2 = db.selectQuery(
				"SELECT thread_id,user FROM forum_message "+
				"WHERE forum_id=\""+ forum_id + "\" "+
				"AND date_time=\"" + date_time + "\"");
						
			while(rs2.next()){
				thread_id = rs2.getString("thread_id");
				user = rs2.getString("user");
			}				
				
				
									
			ResultSet rs3 = db.selectQuery(
				"SELECT * FROM forum_threads "+
				"WHERE thread_id=\""+ thread_id + "\" "+
				"AND forum_id=\"" + forum_id + "\"");
						
			while(rs3.next()){
				title = rs3.getString("title");
			}			
			
			if(date_time == null){ 
				date_time = "No info";	
			}
			if(user == null){
				user = "No info";		
			}
			if(title == null){
				title = "No info";	
			}
			
			lastPostInfo = date_time + "<br>In: " + title + "<br>By: " + user;
			
			
			db.close();
		}catch(Exception e){}
				
		return 	lastPostInfo;
	}
	
	public static String lastActionInfo(String forum_id,String thread_id){
		
		String lastPostInfo = null;
		String date_time = null;
		String user = null;
		
		try{
			DBConnectie db = new DBConnectie(Variable.getDb(),Variable.getDbLogin(),Variable.getDbPassword());
			db.connect();
	
			ResultSet rs = db.selectQuery(
				"SELECT MAX(date_time) date_time FROM forum_message "+
				"WHERE forum_id=\""+ forum_id + "\" "+ 
				"AND thread_id=\"" + thread_id + "\"");
						
			while(rs.next()){
				date_time = rs.getString("date_time");	
			}
			
			ResultSet rs2 = db.selectQuery(
				"SELECT user FROM forum_message "+
				"WHERE forum_id=\""+ forum_id + "\" "+ 
				"AND thread_id=\"" + thread_id + "\" "+
				"AND date_time=\"" + date_time + "\"");
						
			while(rs2.next()){
				user = rs2.getString("user");	
			}
						
			if(date_time == null){ 
				date_time = "No info";	
			}
			if(user == null){
				user = "No info";		
			}
			
			lastPostInfo = date_time + "<br>Last post by: " + user;
			
			
			db.close();
		}catch(Exception e){}
				
		return 	lastPostInfo;
	}
	
	public static void addView(String forum_id,String thread_id){

		try{
			DBConnectie db = new DBConnectie(Variable.getDb(),Variable.getDbLogin(),Variable.getDbPassword());
			db.connect();
	
			db.query(
				"UPDATE forum_threads SET views = views + 1 "+
				"WHERE forum_id =\"" + forum_id + "\" "+
				"AND thread_id=\"" + thread_id + "\"");

			db.close();
		}catch(Exception e){}
	}
	
	public static String getViews(String forum_id,String thread_id){
		
		String views = null;
		try{
			DBConnectie db = new DBConnectie(Variable.getDb(),Variable.getDbLogin(),Variable.getDbPassword());
			db.connect();
	
			ResultSet rs = db.selectQuery(
				"SELECT views FROM forum_threads "+
				"WHERE forum_id=\""+ forum_id + "\" "+
				"AND thread_id=\"" + thread_id + "\"");
						
			while(rs.next()){
				views = rs.getString("views");
			}
			db.close();
		}catch(Exception e){}
		
		return 	views;
	}	
	
	public static int getMessageLength(String forum_id,String thread_id,String reply_id)
	{
		int messageLength = 0;
		try{
			DBConnectie db = new DBConnectie(Variable.getDb(),Variable.getDbLogin(),Variable.getDbPassword());
			db.connect();
			
			ResultSet rs = db.selectQuery(
				"SELECT message FROM forum_message "+
				"WHERE forum_id=\""+ forum_id + "\" "+
				"AND thread_id=\"" + thread_id + "\" "+
				"AND reply_id=\"" + reply_id + "\"");
						
			while(rs.next()){
				messageLength = rs.getString("message").length();
			} 
			db.close();
		}catch(Exception e){}
		
		return 	messageLength;
	
	}
}