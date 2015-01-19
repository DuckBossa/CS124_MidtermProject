import java.util.*;
import java.io.*;

public class Table implements Serializable{

	ArrayList<Row> rows;
	String name;
	
	public Table (String n, ArrayList<Row> r) {
		name = n;
		rows = new ArrayList<Row>();
		for (int i=0; i<r.size(); i++)
			rows.add(r.get(i));
	}
	
	public void addRow (Row r) {
		if (name.equals("MMPack")) {
			String cols [] = new String [r.columns.size()+1];
			int lastID = Integer.parseInt(rows.get(rows.size()-1).columns.get(0));
			cols[0] = (lastID+1) + "";
			for (int i=0; i<r.columns.size(); i++)
				cols[i+1] = r.columns.get(i);
			rows.add(new Row(cols));
		}
		else
			rows.add(r);
	}
	
	public void editRow (Row r) {
		String key = r.columns.get(0);
		for (int i=0; i<rows.size(); i++) {
			Row toCheck = rows.get(i);
			if (key.equals(toCheck.columns.get(0))) {
				rows.set(i, r);
				break;
			}
		}
	}
	
	public void deleteRow (String n) {
		for (int i=0; i<rows.size(); i++) {
			Row toCheck = rows.get(i);
			if (n.equals(toCheck.columns.get(0))) {
				rows.remove(i);
				break;
			}
		}
	}
}
