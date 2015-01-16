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
	
	// import states and save states
	
	public Eminem saveEminem()
	{
	
		Eminem e = null;
		ArrayList<Table> arrTable = new ArrayList<Table>();
		try{
		
			ResultSet rs = search("SELECT * FROM MMPack");
			ArrayList<Row> MMrows = new ArrayList<Row>();
			while(rs.next())
			{
				String s[] = new String[]{re.getString("id"), rs.getString("price"), rs.getString("quantity"), rs.getString("size"), rs.getString("net_weight"), rs.getString("kind")};
				MMrows.add(new Row(s));
			}
			
			arrTable.add(new Table("MM",MMrows));
			
			rs = search("SELECT * FROM Sales");
			ArrayList<Row> SalesRows = new ArrayList<Row>();
			while(rs.next())
			{
				String s[] = new String[]{re.getString("country"), rs.getString("profit")};
				SalesRows.add(new Row(s));
			}
			
			arrTable.add(new Table("Sales",SalesRows));
			
			e = new Eminem(arrTable);
			
		
		} catch(Exception e)
		{
			System.out.println("failed...");
			e.printStackTrace();
		}
		
		return e;
	}
	
	public void importState(Eminem b)
	{
	
		clearAllTables();
		
		for(String s : b.names)
		{
		
			Table t = b.get(s);
			
			if(s.equals("MMPack"))
				{
				
				String update = "INSERT INTO " + s + "(`id`,`price`,`quantity`,`size`,`net_weight`,`kind`) VALUES ";
				int len = t.rows.size();
				
				for(int i = 0; i< len; i++)
				{
				
					update +="('"+r.get(0)+"','"+r.get(1)+"','"+r.get(2)+"','"+r.get(3)+"','"+r.get(4)+"','"+r.get(5)+"')";
					
					if(i != len-1)
					{
						update+=",";
					}
			
				}
				
				update+=";";
		
				} else
				{
					
					String update = "INSERT INTO " + s + "(`country`,`profit`) VALUES ";
					int len = t.rows.size();
					
					for(int i = 0; i< len; i++)
					{					
					
					update +="('"+r.get(0)+"','"+r.get(1)+"')";
					
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