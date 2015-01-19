import java.util.*;
import java.io.*;

public class Row implements Serializable{ 

	ArrayList<String> columns;
	
	public Row (String [] cols) {
		columns = new ArrayList<String>();
		for (int i=0; i<cols.length; i++)
			columns.add(cols[i]);
	} 
	
}
