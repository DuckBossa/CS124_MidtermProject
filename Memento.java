import java.util.*;

public class Memento {
	
	HashMap<String, Table> tables;
	
	public Memento (HashMap<String, Table> t) {
		setState (t);
	}
	
	public HashMap<String, Table> getState () {
		return tables;
	}
	
	public void setState(HashMap<String, Table> t) {
		tables = t;
	}
}
