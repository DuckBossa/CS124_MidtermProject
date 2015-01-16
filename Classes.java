class Memento {
	HashMap<String, ArrayList<Table>> tables;
	public Eminem (HashMap<String, ArrayList<Table>> t) {
		setState (t);
	}
	public HashMap<String, ArrayList<Table>> getState () {
		return tables;
	}
	public void setState(HashMap<String, ArrayList<Table>> t) {
		tables = t;
	}
}

class Eminem {
	HashMap<String, ArrayList<Table>> tables;
	ArrayList<String> names;
	public Eminem (ArrayList<Table> t) {
		tables = new Hashmap<String, ArrayList<Table>>();
		names = new ArrayList<String>();
		for (int i=0; i<t.size(); i++) {
			tables.put(t.get(i).name, t.get(i));
			names.add(t.get(i).name);
		}
	}
	public void addTable (Table t) {
		tables.put(t.name, t);
	}
	public void addRow (Table t, Row r) {
		Table needed = tables.get(t.name);
		needed.addRow(r);
	}
	public void editRow (Table t, Row r) {
		Table needed = tables.get(t.name);
		needed.editRow(r);
	}
	public void deleteRow (Table t, String n) {
		Table needed = tables.get(t.name);
		needed.deleteRow(n);
	}
	public Memento createMemento () {
		Memento version = new Memento (tables);
		return version;
	}
	public void setMemento (Memento m) {
		tables = m.getState();
	}
}

class Table {
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

class Row {
	ArrayList<String> columns;
	public Row (String [] cols) {
		columns = new ArrayList<String>();
		for (int i=0; i<cols.length; i++)
			columns.add(cols[i]);
	} 
}
