import java.sql.*;
import java.util.*;

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
	
	// import states and save states
	
	public Eminem saveEminem()
	{
	
		Eminem em = null;
		ArrayList<Table> arrTable = new ArrayList<Table>();
		try{
		
			ResultSet rs = search("SELECT * FROM MMPack");
			ArrayList<Row> MMrows = new ArrayList<Row>();
			while(rs.next())
			{
				String s[] = new String[]{rs.getString("id"), ""+rs.getFloat("price"),""+rs.getInt("quantity"), rs.getString("size"), ""+rs.getFloat("net_weight"), rs.getString("kind")};
				MMrows.add(new Row(s));
			}
			
			arrTable.add(new Table("MMPack",MMrows));
			
			rs = search("SELECT * FROM Sales");
			ArrayList<Row> SalesRows = new ArrayList<Row>();
			while(rs.next())
			{
				String s[] = new String[]{rs.getString("country"), ""+rs.getFloat("profit")};
				SalesRows.add(new Row(s));
			}
			
			arrTable.add(new Table("Sales",SalesRows));
			
			em = new Eminem(arrTable);
			System.out.println("Saved");
		
		} catch(Exception e)
		{
			System.out.println("failed...");
			e.printStackTrace();
		}
		
		return em;
	}
	
	public void saveDatabase(Eminem b)
	{
	
		clearAllTables();
		
		for(String s : b.names)
		{
		
			Table t = b.tables.get(s);
			String update="";
			
			if(s.equals("MMPack"))
				{
				
				update += "INSERT INTO " + s + "(`id`,`price`,`quantity`,`size`,`net_weight`,`kind`) VALUES ";
				int len = t.rows.size();
				
				for(int i = 0; i< len; i++)
				{
					Row r = t.rows.get(i);
					update +="('"+r.columns.get(0)+"','"+r.columns.get(1)+"','"+r.columns.get(2)+"','"+r.columns.get(3)+"','"+r.columns.get(4)+"','"+r.columns.get(5)+"')";
					
					if(i != len-1)
					{
						update+=",";
					}
			
				}
				
				update+=";";
		
				} else
				{
					
					update += "INSERT INTO " + s + "(`country`,`profit`) VALUES ";
					int len = t.rows.size();
					
					for(int i = 0; i< len; i++)
					{					
					Row r = t.rows.get(i);
					update +="('"+r.columns.get(0)+"','"+r.columns.get(1)+"')";
					
					if(i != len-1)
					{
						update+=",";
					}
				}
				
				}
			
			executeUpdate(update);
			
		}
	}

}