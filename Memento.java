import java.util.*;
import java.io.*;

public class Memento implements Serializable{
	
	Table one;
	Table two;
	
	public Memento (Table one, Table two) {
		setState (one, two);
	}
	
	public HashMap<String, Table> getState () {
		HashMap<String, Table> hm = new HashMap<String, Table>();
		hm.put("MMPack", one);
		hm.put("Sales", two);
		return hm;
	}
	
	public void setState(Table one, Table two) {
		ArrayList<Row> first = new ArrayList<Row>();
		for (int i=0; i<one.rows.size(); i++) {
			Row cur = one.rows.get(i);
			String cols [] = new String [cur.columns.size()];
			for (int k=0; k<cols.length; k++)
				cols[k] = cur.columns.get(k);
			first.add(new Row(cols));
		}
		this.one = new Table("MMPack", first);
		
		ArrayList second = new ArrayList<Row>();
		for (int i=0; i<two.rows.size(); i++) {
			Row cur = two.rows.get(i);
			String cols [] = new String [cur.columns.size()];
			for (int k=0; k<cols.length; k++)
				cols[k] = cur.columns.get(k);
			second.add(new Row(cols));
		}
		this.two = new Table("Sales", second);
	}
}
