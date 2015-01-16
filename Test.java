import java.sql.*;

public class Test{

	public static void main(String args[])
	{
		
		DB_Mediator a = new DB_Mediator();
		printResultsSales(a.search("SELECT * FROM sales;"));
		//printResultsPack(a.search("SELECT * FROM MMPack;"));
		Eminem e = a.saveEminem();
		a.executeUpdate("INSERT INTO Sales(`country`, `profit`) VALUES ('Timbuktu', 12312.31);");
		printResultsSales(a.search("SELECT * FROM sales;"));
		a.saveDatabase(e);
		printResultsSales(a.search("SELECT * FROM sales;"));
	}
	
	public static void printResultsSales(ResultSet rs)
	{
		try{
		while(rs.next())
		{
			System.out.println(
				"Country: "+rs.getString("country")+"\n"+
				"Profit : "+rs.getFloat("profit")+
				"\n----- ----- -----"
			);
		}
		}catch (Exception e)
		{
			e.printStackTrace();
		}
			System.out.println("--END--");
			
	}
	
	public static void printResultsPack(ResultSet rs)
	{
		try{
		while(rs.next())
		{
			System.out.println(
				"ID         : "+rs.getString("id")+"\n"+
				"Price      : "+rs.getFloat("price")+
				"Quantity   : "+rs.getString("quantity")+"\n"+
				"Size       : "+rs.getString("size")+"\n"+
				"Net Weight : "+rs.getString("net_weight")+"\n"+
				"Kind       : "+rs.getString("kind")+
				"\n----- ----- -----"
			);
		}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
			System.out.println("--END--");
	}

}