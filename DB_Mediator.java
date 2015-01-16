import java.sql.*;

public class DB_Mediator {
	
	Connection conn;
	Statement statement;
	
	public DB_Mediator()
	{
		establishConnection("root", "", "MM");
	}
	
	public ResultSet search(String query)
	{
		try{
		return statement.executeQuery(query);
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public void executeUpdate(String query)
	{
		try{
		statement.executeUpdate(query);
		System.out.println("Update was successfully executed!");
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void clearSalesTable()
	{
		try{
		statement.executeUpdate("DELETE FROM Sales;");
		System.out.println("Successfully cleared Sales table!");
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void clearPackTable()
	{
		try{
		statement.executeUpdate("DELETE FROM MMPack;");
		System.out.println("Successfully cleared MMPack Table!");
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void clearAllTables()
	{
		clearSalesTable();
		clearPackTable();
	}
	
	
	public void establishConnection(String username, String password, 
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
			statement = conn.createStatement();
			System.out.println("Connection Established!");
			
		} 
		catch(Exception e)
		{
			System.out.println("Connection Failed!");
			e.printStackTrace();
		}
		
	}

}