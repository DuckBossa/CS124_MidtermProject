import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

public class Main extends JPanel {
	private JScrollPane sp1;
	private JScrollPane sp2;
    private JButton add;
    private JButton edit;
    private JButton del;
    private JButton imp;
    private JButton save;
    private JButton undo;
    private JList jcomp7;
    private JList jcomp8;
    private JButton exp;
    private JTable db1;
    private JTable db2;
    public Main() {
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
        edit = new JButton ("Edit");
        del = new JButton ("Delete");
        imp = new JButton ("Import ");
        save = new JButton ("Save Changes");
        undo = new JButton ("Undo");
        exp = new JButton ("Export");



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
    }


    public static void main (String[] args) {
        JFrame frame = new JFrame ("M&Ms");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new Main());
        frame.pack();
        frame.setVisible (true);
    }
}
