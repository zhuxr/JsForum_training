package forum;


public class Variable {
	
	static String dbDatabase = "forum";
	static String dbLogin = "root";
	static String dbPassword = "";
	static String forumPath = "..\\forum\\";
	static String forumName = "My Forum";
	static String messagePerPage = "10";
	
	
	
	public static String getDb(){
		return dbDatabase;
	}
	
	public static String getDbLogin(){
		return dbLogin;
	}	
	
	public static String getDbPassword(){
		return dbPassword;
	}

	public static String getForumPath(){
		return forumPath;
	}
	
	public static String getForumName(){
		return forumName;
	}
	
	public static String getMessagePerPage(){
		return messagePerPage;
	}
}