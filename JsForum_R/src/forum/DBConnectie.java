package forum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnectie{
	
	String DBName, DBUser, DBPass, SQLQuery;
    Connection conn;
    Statement stmt;
    ResultSet result;	
	
	public DBConnectie(String DBName, String DBUser, String DBPass){
    	this.DBName = DBName;
        this.DBUser = DBUser;
        this.DBPass = DBPass;
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception e){}
    }	
	
	public void connect() {
    	try {
        	conn = DriverManager.getConnection("jdbc:mysql://localhost/" + DBName + "?user=" + DBUser + "&password=" + DBPass);
        }catch(Exception e){}
    }
	
    public ResultSet selectQuery(String SQLQuery){
    	this.SQLQuery = SQLQuery;

        try {
        	stmt = conn.createStatement();
            result = stmt.executeQuery( SQLQuery );
        }
        catch( Exception e ){}
    	return result;
    }
    
    public void query(String SQLQuery){
    	this.SQLQuery = SQLQuery;
        try {
        	stmt = conn.createStatement();
            stmt.executeQuery( SQLQuery );
        }
        catch( Exception e ){}
    }
    
    public void close(){
    	try {
        	stmt.close();
            conn.close();
        }
        catch(Exception e){}
    }
}

