import java.sql.*;

public class DB_Mediator {
	
	Connection conn;
	
	public DB_Mediator()
	{
		establishConnection("root", "", "MM");
	}
	
	public Connection establishConnection(String username, String password, 
	String db)
	{	
		
		String Driver = "com.mysql.jdbc.Driver";
		String URL = "jdbc:mysql://localhost/"+db;
		//Connection conn = null;
			
		try{

			if(!username.equals(""))
			{
				System.out.println("something");
				conn = DriverManager.getConnection(URL, username, password);
				
			}
			else
			{
			
				conn = DriverManager.getConnection(URL);
				
			}
			
			System.out.println("Connection Established!");
			return conn;
		} 
		catch(Exception e)
		{
			System.out.println("Connection Failed!");
			e.printStackTrace();
			return null;
		}
		
	}

}