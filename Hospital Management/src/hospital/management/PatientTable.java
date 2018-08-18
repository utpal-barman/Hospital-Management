package hospital.management;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


class PatientTable extends JFrame{
    JTabbedPane tab = new JTabbedPane();    //Tab pane
    JPanel add_patient = new JPanel(new GridLayout(2, 1)); // Panel for adding information
    JPanel show_patient = new JPanel();   // Panel for showing patient
    
    
    // Swing Components for "Add patient" Tab
    // All labels
    JLabel l_patientName = new JLabel("Patient's Name: ");
    JLabel l_contact = new JLabel("Contact: ");
    JLabel l_address = new JLabel("Address:   ");
    
    // All textfield
    JTextField tf_patientName = new JTextField(20);
    JTextField tf_contact = new JTextField(20);
    JTextField tf_address = new JTextField(20);
    
    // All buttons
    JButton btn_add = new JButton("Add");
    JButton btn_back = new JButton("Go Back");
    
    JTable table;
    
    
    
    public PatientTable(){
        setTitle("Patient Information");
        setSize(600,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        // Add Patient Tab
        JPanel panel_left = new JPanel();
        panel_left.setBackground(new Color(250, 250, 200));
        panel_left.setLayout(new GridLayout(3, 1, 1, 8));
        panel_left.add(l_patientName);
        panel_left.add(l_contact);
        panel_left.add(l_address);
        
        JPanel panel_right = new JPanel();
        panel_right.setBackground(new Color(250, 250, 200));
        panel_right.setLayout(new GridLayout(3, 1, 1, 8));
        panel_right.add(tf_patientName);
        panel_right.add(tf_contact);
        panel_right.add(tf_address);
        
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Add Patient"));
        panel.setBackground(new Color(250, 250, 200));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 15));
        panel.add(panel_left);
        panel.add(panel_right);
        
        JPanel btn_panel = new JPanel();
        btn_panel.setBackground(Color.LIGHT_GRAY);
        btn_panel.add(btn_add);
        btn_panel.add(btn_back);
        
      
        add_patient.setBackground(Color.GRAY);
        add_patient.add(panel);
        add_patient.add(btn_panel);
        
        //Show Patient tab
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Contact");
        model.addColumn("Address");
        
        table = new JTable(model);
        
        JScrollPane sp_table = new JScrollPane();
        sp_table.add(table);
        sp_table.setViewportView(table);
        
        JPanel p_table = new JPanel();
        p_table.add(sp_table);
        
        show_patient.add(p_table);
        
        //Adding two tabs to total tab pane
        tab.addTab("Add Patient",null,add_patient,null);
        tab.addTab("Show Patients",null,show_patient,null);
        
        add(tab);
        
        setActionListener();
        
    }

    private void setActionListener() {
        btn_add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel)table.getModel();
                model.addRow(new Object[]{tf_patientName.getText(),tf_contact.getText(),tf_address.getText()});
                tf_patientName.setText("");
                tf_contact.setText("");
                tf_address.setText("");
            }
        });
        
        btn_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    
}
