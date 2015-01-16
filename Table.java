public class Table {

	ArrayList<Row> rows;
	String name;
	
	public Table (String n, ArrayList<Row> r) {
		name = n;
		rows = new ArrayList<Row>();
		for (int i=0; i<r.size(); i++)
			rows.add(r.get(i));
	}
	
	public void addRow (Row r) {
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
				rows.set(i, null);
				break;
			}
		}
	}
}