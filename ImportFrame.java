import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;


public class ImportFrame extends JFrame {
    private JButton importB;
    private JButton cancel;
    public JComboBox importList;
    private JPanel panel;
	private Eminem database;
	private Main window;
	private ArrayList<Memento> versions;
    public ImportFrame(Eminem database, Main window) {
		this.database = database;
		this.window = window;
		versions = window.getVersions();
        String[] importListItems = new String [versions.size()];
		for (int i=0; i<importListItems.length; i++)
			importListItems[i] = "Version " + (i+1);
        panel = new JPanel();
        importB = new JButton ("Import");
		importB.addActionListener(new ImportVersion());
        cancel = new JButton ("Cancel");
        cancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });
        importList = new JComboBox (importListItems);

        //adjust size and set layout
        panel.setPreferredSize(new Dimension (375, 100));
        panel.setLayout (null);
		
		JLabel nothing = new JLabel ("No Previous Versions Found");

        //add components
		panel.add(cancel);
		panel.add(importB);
		if (importListItems.length>0) {
			panel.add(importList);
		}
		else {
			importB.setEnabled(false);
			panel.add(nothing);
		}

        //set component bounds (only needed by Absolute Positioning)
        importB.setBounds (70, 60, 100, 25);
        cancel.setBounds (190, 60, 100, 25);
        importList.setBounds (65, 20, 240, 30);
		nothing.setBounds(65,20,240,30);

        setDefaultCloseOperation (JFrame.HIDE_ON_CLOSE);
        add(panel);
        pack();
    }
	
	class ImportVersion implements ActionListener {
		public void actionPerformed (ActionEvent ae) {
			database.setMemento(versions.get(importList.getSelectedIndex()));
			window.showInfo(database);
			window.emptyStack();
			dispose();
		}
	}

}
