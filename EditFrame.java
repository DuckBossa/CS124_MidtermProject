import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

public class EditFrame extends JFrame{
	private JButton edit;
	//private Eminem database;
	private JPanel dbSet;
	public EditFrame(boolean ifDB1){ // add here the database + the specific row to be edited
		edit = new JButton("Edit");
		edit.addActionListener(new ActionListener(){ // Change this to actual Listener
			public void actionPerformed(ActionEvent e){
				System.out.println("Edit Code Here");
			}
		});
		//this.database = database;

		if(ifDB1){
			dbSet = new TableOneAdd();
		}
		else{
			dbSet = new TableTwoAdd();
		}

		setSize (new Dimension (261, 300));
		setResizable(false);
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(edit,BorderLayout.SOUTH);
		add(dbSet,BorderLayout.CENTER);
	}

	class TableOneAdd extends JPanel {
	    private JLabel priceLabel;
	    private JLabel quantityLabel;
	    private JLabel sizeLabel;
	    private JLabel kindLabel;
	    private JLabel netLabel;
	    public JTextField priceField;
	    public JTextField quantityField;
	    public JTextField netField;
	    public JTextField kindField;
	    public JComboBox sizeField;
	    public JLabel idField;
	    private JLabel idJLabel;

	    public TableOneAdd() {
	        String[] sizeFieldItems = {"Small", "Medium", "Large", "Party"};

	        priceLabel = new JLabel ("Price:");
	        quantityLabel = new JLabel ("Quantity:");
	        sizeLabel = new JLabel ("Size:");
	        kindLabel = new JLabel ("Kind:");
	        netLabel = new JLabel ("Net Weight:");
	        priceField = new JTextField (5);
	        quantityField = new JTextField (5);
	        netField = new JTextField (5);
	        kindField = new JTextField (5);
	        idJLabel = new JLabel ("ID:");
	        idField = new JLabel ("");
	        sizeField = new JComboBox (sizeFieldItems);

	        setPreferredSize (new Dimension (261, 205));
	        setLayout (null);

	        add (priceLabel);
	        add (quantityLabel);
	        add (sizeLabel);
	        add (kindLabel);
	        add (netLabel);
	        add (priceField);
	        add (quantityField);
	        add (netField);
	        add (kindField);
	        add (idJLabel);
	        add (idField);
	        add (sizeField);

	        priceLabel.setBounds (20, 50, 100, 25);
	        quantityLabel.setBounds (20, 80, 100, 25);
	        sizeLabel.setBounds (20, 110, 100, 25);
	        kindLabel.setBounds (20, 170, 100, 25);
	        netLabel.setBounds (20, 140, 100, 25);
	        priceField.setBounds (90, 50, 155, 25);
	        quantityField.setBounds (90, 80, 155, 25);
	        netField.setBounds (90, 140, 155, 25);
	        kindField.setBounds (90, 170, 155, 25);
	        idJLabel.setBounds (20, 25, 100, 25);
	        idField.setBounds (95, 25, 100, 25);
	        sizeField.setBounds (90, 110, 155, 25);
	    }
	}

	class TableTwoAdd extends JPanel {
	    private JLabel countryLabel;
	    private JLabel profitLabel;
	    public JTextField countryField;
	    public JTextField profitField;
	    public TableTwoAdd() {
	        countryLabel = new JLabel ("Country:");
	        countryField = new JTextField (5);
	        profitLabel = new JLabel ("Profit:");
	        profitField = new JTextField (5);
	        //set text
	        countryField.setEditable(false);
	        setPreferredSize (new Dimension (261, 205));
	        setLayout (null);

	        add (countryLabel);
	        add (countryField);
	        add (profitLabel);
	        add (profitField);

	        countryLabel.setBounds (30, 50, 100, 25);
	        countryField.setBounds (100, 50, 100, 25);
	        profitLabel.setBounds (30, 80, 100, 25);
	        profitField.setBounds (100, 80, 100, 25);
	    }
	}


}