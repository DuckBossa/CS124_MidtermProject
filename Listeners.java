//Button Action Listeners

class AddButton implements ActionListener {
	public void actionPerformed (ActionEvent ae) {
		//open GUI Window
	}
}

class EditButton implements ActionListener {
	public void actionPerformed (ActionEvent ae) {
		//open GUI Window
	}
}

class DeleteButton implements ActionListener {
	public void actionPerformed (ActionEvent ae) {
		//open GUI Window
	}
}

class ImportButton implements ActionListener {
	public void actionPerformed (ActionEvent ae) {
		//open GUI Window
	}
}

class ExportButton implements ActionListener {
	public void actionPerformed (ActionEvent ae) {
		//open GUI Window
	}
}

class AddRow implements ActionListener {
	public void actionPerformed (ActionEvent ae) {
		moves.add(database); //database is current Eminem, add current database to stack before changing it
		if (dropdown.hasChosen("MM Pack")) { //choosing the table
			String id = idField.getText();
			String price = priceField.getText();
			String quantity = quantityField.getText();
			String size = sizeField.getText();
			String net_weight = netField.getText();
			String kind = kindField.getText();
			database.addRow(mmpack, new Row(new String [] {id, price, quantity, size, net_weight, kind}));
			//database is the current Eminem
			//addRow is a method weithin Eminem class
			//mmpack is a Table object
			//Row is a class
		}
		else if (dropdown.hasChosen("Sales")) {
			String country = countryField.getText();
			String profit = profitField.getText();
			database.addRow(sales, new Row(new String [] {country, profit}));
			//database is the current Eminem
			//addRow is a method weithin Eminem class
			//sales is a Table object
			//Row is a class
		}
		showInfo(database);
	}
}

class EditRow implements ActionListener {
	public void actionPerformed (ActionEvent ae) {
		moves.add(database); //add current database to stack before changing it
		if (dropdown.hasChosen("MM Pack")) { //choosing the table
			String id = idField.getText();
			String price = priceField.getText();
			String quantity = quantityField.getText();
			String size = sizeField.getText();
			String net_weight = netField.getText();
			String kind = kindField.getText();
			database.editRow(database.tables.get("MM Pack"), new Row(new String[]{id, price, quantity, size, net_weight, kind}));
		}
		else if (dropdown.hasChosen("Sales")) {
			String country = countryField.getText();
			String profit = profitField.getText();
			database.editRow(database.tables.get("Sales"), new Row(new String[]{country, profit}));
		}
		showInfo(database);
	}
}

class DeleteRow implements ActionListener {
	public void actionPerformed (ActionEvent ae) {
		moves.add(database); //add current database to stack before changing it
		if (dropdown.hasChosen("MM Pack")) { //choosing the table
			String id = idField.getText();
			database.deleteRow(database.tables.get("MM Pack"), id);
		}
		else if (dropdown.hasChosen("Sales")) {
			String country = countryField.getText();
			database.editRow(database.tables.get("Sales"), country);
		}
		showInfo(database);
	}
}

class ImportVersion implements ActionListener {
	public void actionPerformed (ActionEvent ae) {
		Eminem toImport = versions.get(chooser.getSelectedItem());
		database = toImport;
		showInfo(database);
		moves = new Stack<Eminem>();
	}
}

class ExportVersion implements ActionListener {
	public void actionPerformed (ActionEvent ae) {
		//export database to file
	}
}

class UndoMove implements ActionListener {
	public void actionPerformed (ActionEvent ae) {
		Eminem prev = moves.pop(); //moves is the name of the stack, Eminem is the class that represents a database
		database = prev; //database is the global Eminem variable with the current database information
		showInfo(database); //method that updates the information on the GUI using info from the Eminem in argument
	}
}

class SaveChanges implements ActionListener {
	public void actionPerformed (ActionEvent ae) {
		databaseMediator.save(database); //using the database mediator, the info in the Eminem variable is saved to the actual database
		moves = new Stack<Eminem>(); //empties the stack
	}
}
