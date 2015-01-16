public void showInfo (Eminem e) {
	Table t1 = e.tables.get(e.names.get(0));
	DefaultTableModel m1 = (DefaultTableModel) db1.getModel();
	db1.setRowCount(0);
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
	db2.setRowCount(0);
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
