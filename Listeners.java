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
		moves.add(database.createMemento()); //database is current Eminem, add current database to stack before changing it
		if (dropdown.hasChosen("MMPack")) { //choosing the table
			String id = idField.getText();
			String price = priceField.getText();
			String quantity = quantityField.getText();
			String size = sizeField.getText();
			String net_weight = netField.getText();
			String kind = kindField.getText();
			database.addRow(database.tables.get("MMPack"), new Row(new String [] {id, price, quantity, size, net_weight, kind}));
			//database is the current Eminem
			//addRow is a method weithin Eminem class
			//mmpack is a Table object
			//Row is a class
		}
		else if (dropdown.hasChosen("Sales")) {
			String country = countryField.getText();
			String profit = profitField.getText();
			database.addRow(database.tables.get("Sales"), new Row(new String [] {country, profit}));
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
		moves.add(database.createMemento()); //add current database to stack before changing it
		if (dropdown.hasChosen("MMPack")) { //choosing the table
			String id = idField.getText();
			String price = priceField.getText();
			String quantity = quantityField.getText();
			String size = sizeField.getText();
			String net_weight = netField.getText();
			String kind = kindField.getText();
			database.editRow(database.tables.get("MMPack"), new Row(new String[]{id, price, quantity, size, net_weight, kind}));
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
		moves.add(database.createMemento()); //add current database to stack before changing it
		if (dropdown.hasChosen("MMPack")) { //choosing the table
			String id = idField.getText();
			database.deleteRow(database.tables.get("MMPack"), id);
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
		database.setMemento(versions.get(chooser.getSelectedItem()));
		showInfo(database);
		moves = new Stack<Memento>();
	}
}

class ExportVersion implements ActionListener {
	public void actionPerformed (ActionEvent ae) {
		//export database to file
	}
}

class UndoMove implements ActionListener {
	public void actionPerformed (ActionEvent ae) {
		database.setMemento(moves.pop());
		//database is the global Eminem variable with the current database information
		//moves is the stack of memento
		showInfo(database); //method that updates the information on the GUI using info from the Eminem in argument
	}
}

class SaveChanges implements ActionListener {
	public void actionPerformed (ActionEvent ae) {
		databaseMediator.saveDatabase(database); //using the database mediator, the info in the Eminem variable is saved to the actual database
		moves = new Stack<Memento>(); //empties the stack
	}
}
