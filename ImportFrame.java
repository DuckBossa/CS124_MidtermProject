import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class ImportFrame extends JFrame {
    private JButton importB;
    private JButton cancel;
    private JComboBox importList;
    private JPanel panel;
    public ImportFrame() {
        //construct preComponents
        String[] importListItems = {"Item 1", "Item 2", "Item 3"};//needs to be replaced with names of the Serialized Memento Object
        panel = new JPanel();
        //construct components
        importB = new JButton ("Import");
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

        //add components
        panel.add(importB);
        panel.add(cancel);
        panel.add(importList);

        //set component bounds (only needed by Absolute Positioning)
        importB.setBounds (70, 60, 100, 25);
        cancel.setBounds (190, 60, 100, 25);
        importList.setBounds (65, 20, 240, 30);

        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        add(panel);
        pack();
        setVisible(true);
    }

}