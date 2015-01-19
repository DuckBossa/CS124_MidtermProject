import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.io.*;

public class Main extends JPanel {
	private JScrollPane sp1;
	private JScrollPane sp2;
    private JButton add;
    private JButton edit;
    private JButton del;
    private JButton imp;
    private JButton save;
    public JButton undo;
    private JList jcomp7;
    private JList jcomp8;
    private JButton exp;
    private JTable db1;
    private JTable db2;
    private DB_Mediator dbmed;
	private Eminem database;
	private Stack<Memento> moves;
	private static ArrayList<Memento> versions;
    public Main() {
        dbmed = new DB_Mediator();
		database = dbmed.saveEminem();
		moves = new Stack<Memento>();
		versions = new ArrayList<Memento>();
		readVersions();
        db1 = new JTable(0,6);
        db1.getColumnModel().getColumn(0).setHeaderValue("ID");
        db1.getColumnModel().getColumn(1).setHeaderValue("Price");
        db1.getColumnModel().getColumn(2).setHeaderValue("Quantity");
        db1.getColumnModel().getColumn(3).setHeaderValue("Size");
        db1.getColumnModel().getColumn(4).setHeaderValue("Net Weight");
        db1.getColumnModel().getColumn(5).setHeaderValue("Kind");
        db2 = new JTable(0,2);
        db2.getColumnModel().getColumn(0).setHeaderValue("Country");
        db2.getColumnModel().getColumn(1).setHeaderValue("Profit");
        sp1 = new JScrollPane(db1);
        sp2 = new JScrollPane(db2);
        add = new JButton ("Add");
        add.addActionListener(new AddButton(database, this));
        edit = new JButton ("Edit");
        edit.addActionListener(new EditButton(database, this));
        del = new JButton ("Delete");
        del.addActionListener(new DeleteButton(database, this));
        imp = new JButton ("Import ");
        imp.addActionListener(new ImportButton(database, this));
        save = new JButton ("Save Changes");
		save.addActionListener(new SaveChanges(dbmed, database, this));
        undo = new JButton ("Undo");
		undo.addActionListener(new UndoMove());
        exp = new JButton ("Export");
        exp.addActionListener(new ExportVersion(database));


        undo.setEnabled (false);
        setPreferredSize (new Dimension (667, 367));
        setLayout (null);

        add (add);
        add (edit);
        add (del);
        add (imp);
        add (save);
        add (undo);
        add (sp1);
        add (sp2);
        add (exp);

        //set component bounds (only needed by Absolute Positioning)
        add.setBounds (55, 50, 115, 20);
        edit.setBounds (55, 75, 115, 20);
        del.setBounds (55, 100, 115, 20);
        imp.setBounds (55, 125, 115, 20);
        save.setBounds (55, 200, 115, 20);
        undo.setBounds (55, 175, 115, 20);
        sp1.setBounds (195, 30, 425, 125);
        sp2.setBounds (195, 165, 425, 125);
        exp.setBounds (55, 150, 115, 20);
		
		showInfo(database);
    }


    public static void main (String[] args) {
        JFrame frame = new JFrame ("M&Ms");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new SavingEverything());
        frame.getContentPane().add (new Main());
        frame.pack();
        frame.setVisible (true);
    }
	
	public void addMove (Memento m) {
		moves.add(m);
		undo.setEnabled(true);
	}
	
	public void emptyStack () {
		moves = new Stack<Memento>();
		undo.setEnabled(false);
	}
	
	public ArrayList<Memento> getVersions () {
		return versions;
	}
	
	//http://www.mkyong.com/java/how-to-read-an-object-from-file-in-java/
	public void readVersions () {
		try{
			FileInputStream fin = new FileInputStream("mementos.out");
			ObjectInputStream ois = new ObjectInputStream(fin);
			Memento m = (Memento) ois.readObject();
			while (m!=null) {
				versions.add(m);
				m = (Memento) ois.readObject();
			}
			ois.close();
			System.out.println("read versions");
		}catch(Exception ex){
			ex.printStackTrace();
		} 
	}
		
	public void showInfo (Eminem e) {
		Table t1 = e.tables.get(e.names.get(0));
		DefaultTableModel m1 = (DefaultTableModel) db1.getModel();
		m1.setRowCount(0);
		for (int i=0; i<t1.rows.size(); i++) {
			Row r = t1.rows.get(i);
			int n = r.columns.size();
			String s [] = new String [n];
			for (int k=0; k<n; k++) {
				s[k] = r.columns.get(k);
			}
			m1.addRow(s);
		}

		Table t2 = e.tables.get(e.names.get(1));
		DefaultTableModel m2 = (DefaultTableModel) db2.getModel();
		m2.setRowCount(0);
		for (int i=0; i<t2.rows.size(); i++) {
			Row r = t2.rows.get(i);
			int n = r.columns.size();
			String s [] = new String [n];
			for (int k=0; k<n; k++) {
				s[k] = r.columns.get(k);
			}
			m2.addRow(s);
		}
	}
	
	static class SavingEverything implements WindowListener {
		public void windowClosing(WindowEvent e) {
			try {
				FileOutputStream fout = new FileOutputStream("mementos.out");
				ObjectOutputStream oos = new ObjectOutputStream(fout);   
				for (int i=0; i<versions.size(); i++)
					oos.writeObject(versions.get(i));
				oos.close();
				System.out.println("saved versions");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		public void windowClosed(WindowEvent e) {}
		public void windowOpened(WindowEvent e) {}
		public void windowIconified(WindowEvent e) {}
		public void windowDeiconified(WindowEvent e) {}
		public void windowActivated(WindowEvent e) {}
		public void windowDeactivated(WindowEvent e) {}
		public void windowGainedFocus(WindowEvent e) {}
		public void windowLostFocus(WindowEvent e) {}
		public void windowStateChanged(WindowEvent e) {}
	}

    class AddButton implements ActionListener {
		Main window;
		Eminem database;
		public AddButton (Eminem database, Main window) {
			this.database = database;
			this.window = window;
		}
		public void actionPerformed (ActionEvent ae) {
			JFrame addFrame = new AddFrame(database, window);
			addFrame.setVisible(true);
		}
	}

	class EditButton implements ActionListener {
		Main window;
		Eminem database;
		public EditButton (Eminem database, Main window) {
			this.database = database;
			this.window = window;
		}
		public void actionPerformed (ActionEvent ae) {
			JFrame editFrame = new EditFrame(database, window);
			editFrame.setVisible(true);
		}
	}
	class DeleteButton implements ActionListener {
		Main window;
		Eminem database;
		public DeleteButton (Eminem database, Main window) {
			this.database = database;
			this.window = window;
		}
		public void actionPerformed (ActionEvent ae) {
			JFrame deleteFrame = new DeleteFrame(database, window);
			deleteFrame.setVisible(true);
		}
	}

	class ImportButton implements ActionListener {
		Main window;
		Eminem database;
		public ImportButton (Eminem database, Main window) {
			this.database = database;
			this.window = window;
		}
		public void actionPerformed (ActionEvent ae) {
			JFrame importFrame = new ImportFrame(database, window);
			importFrame.setVisible(true);
		}
	}
	
	//http://stackoverflow.com/questions/2885173/java-how-to-create-and-write-to-a-file
	class ExportVersion implements ActionListener {
		private Eminem database;
		public ExportVersion (Eminem database) {
			this.database = database;
		}
		public void actionPerformed (ActionEvent ae) {
			Writer writer = null;
			try {
				writer = new BufferedWriter(new OutputStreamWriter(
					  new FileOutputStream("Database-LatestVersion.csv")));
				writer.write("ID,Price,Quantity,Size,Net Weight,Kind\n");
				Table t = database.tables.get("MMPack");
				for (int i=0; i<t.rows.size(); i++) {
					Row r = t.rows.get(i);
					String info = "";
					for (int k=0; k<r.columns.size(); k++) {
						if (k>0) info += ",";
						info+=r.columns.get(k);
					}
					writer.write(info + "\n");
				}

				writer.write("\n");
				writer.write("Country,Profit\n");
				t = database.tables.get("Sales");
				for (int i=0; i<t.rows.size(); i++) {
					Row r = t.rows.get(i);
					String info = "";
					for (int k=0; k<r.columns.size(); k++) {
						if (k>0) info += ",";
						info+=r.columns.get(k);
					}
					writer.write(info + "\n");
				}
				
				writer.close();
			} catch (IOException ex) {
				System.out.println ("Failure");
			}
		}
	}
	
	class UndoMove implements ActionListener {
		public void actionPerformed (ActionEvent ae) {
			Memento prev = moves.pop();
			database.setMemento(prev);
			showInfo(database);
			if (moves.isEmpty()) undo.setEnabled(false);
		}
	}

	class SaveChanges implements ActionListener {
		private DB_Mediator dbmed;
		private Eminem database;
		private Main window;
		public SaveChanges (DB_Mediator dbmed, Eminem database, Main window) {
			this.dbmed = dbmed;
			this.database = database;
			this.window = window;
		}
		public void actionPerformed (ActionEvent ae) {
			dbmed.saveDatabase(database);
			versions.add(database.createMemento());
			window.emptyStack();
		}
	}

}
