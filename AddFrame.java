import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

public class AddFrame extends JFrame{
	private JButton enter;
	private	JComboBox tableJCB;
	private JPanel dbSet;
	private Eminem database;
	private Main window;
	
	public JTextField countryField;
	public JTextField profitField;
	public JTextField priceField;
	public JTextField quantityField;
	public JTextField netField;
	public JTextField kindField;
	public JComboBox sizeField;
	public JLabel idField;
	public String table;
	
	public AddFrame( Eminem database, Main window ){
		this.database = database;
		this.window = window;
		table = "MMPack";
		dbSet = new TableOneAdd();
		final String[] choices = {"MMPack","Sales"};
		enter = new JButton("Enter");
		tableJCB = new JComboBox(choices);
		tableJCB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				remove(dbSet);
				String temp = (String) tableJCB.getSelectedItem();
				table = temp;
				if(temp.equals(choices[0])){
					dbSet = new TableOneAdd();
				}
				else{
					dbSet = new TableTwoAdd();
				}
				add(dbSet,BorderLayout.CENTER);
				dbSet.repaint();
				dbSet.revalidate();
			}
		});
		
		enter.addActionListener(new AddRow());
		setSize (new Dimension (261, 300));
		setResizable(false);
		setDefaultCloseOperation (JFrame.HIDE_ON_CLOSE);
		setLayout(new BorderLayout());
		add(enter,BorderLayout.SOUTH);
		add(dbSet,BorderLayout.CENTER);//default db1
		add(tableJCB,BorderLayout.NORTH);
	}

	class TableOneAdd extends JPanel {
	    private JLabel priceLabel;
	    private JLabel quantityLabel;
	    private JLabel sizeLabel;
	    private JLabel kindLabel;
	    private JLabel netLabel;
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
	    public TableTwoAdd() {
	        countryLabel = new JLabel ("Country:");
	        countryField = new JTextField (5);
	        profitLabel = new JLabel ("Profit:");
	        profitField = new JTextField (5);

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

	class AddRow implements ActionListener {
		public void actionPerformed (ActionEvent ae) {
			window.addMove(database.createMemento());
			if(table.equals("MMPack")) {
				String price = priceField.getText();
				String quantity = quantityField.getText();
				String size = (String)sizeField.getSelectedItem();
				String net_weight = netField.getText();
				String kind = kindField.getText();
				database.addRow(database.tables.get("MMPack"), new Row(new String [] {price, quantity, size, net_weight, kind}));
			}
			else if (table.equals("Sales")) {
				String country = countryField.getText();
				String profit = profitField.getText();
				database.addRow(database.tables.get("Sales"), new Row(new String [] {country, profit}));
			}
			window.showInfo(database);
		}
	}
}
