public class Eminem {

	HashMap<String, Table> tables;
	ArrayList<String> names;
	
	public Eminem (ArrayList<Table> t) {
		tables = new Hashmap<String, Table>();
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
