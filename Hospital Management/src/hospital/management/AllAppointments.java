package hospital.management;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;



/**
 *
 * @author Utpal Barman
 */
public class AllAppointments extends JFrame {
    SQLConnection instance = new SQLConnection();
    Connection connection = instance.getDatabaseConnection();
    private DefaultTableModel model;
    private JButton btn_back = new JButton("Back to Home");
    private JTextField tf_search = new JTextField(30);
    private JButton title = new JButton("All Apointments List");
    private JLabel l_search = new JLabel("Search Box  :");
    
    public AllAppointments() {
        
        setTitle("All Appointments");
        setSize(1000,600);
        setVisible(true);
        setResizable(false);
    
    
        model = new DefaultTableModel();
        JTable table = new JTable(model);
    
        
    
    
     
    
 
        try{
            String sql = "SELECT * FROM info";
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery(sql);
            
            //Search Box
            
            table = new JTable(buildTableModel(resultset));
        
            TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
            table.setRowSorter(rowSorter);
         
            tf_search.getDocument().addDocumentListener(new DocumentListener(){

                @Override
                public void insertUpdate(DocumentEvent e) {
                    String text = tf_search.getText();

                    if (text.trim().length() == 0) {
                        rowSorter.setRowFilter(null);
                    } else {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    String text = tf_search.getText();

                    if (text.trim().length() == 0) {
                        rowSorter.setRowFilter(null);
                    } else {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            
            
            //End of Search Box

            while(resultset.next()){
               System.out.println(resultset.getString(1)+" "+resultset.getString(2)+" "+resultset.getString(3)+" "+resultset.getString(4)+" "+resultset.getString(5));
            }
        } catch(SQLException ex){
            ex.printStackTrace();
          }
        
        title.setFont(new Font("myfont", Font.BOLD, 30));
        title.setBackground(Color.DARK_GRAY);
        title.setForeground(Color.WHITE);

        
        JPanel bottom_panel = new JPanel();
        bottom_panel.setLayout(new FlowLayout(FlowLayout.LEFT, 100, 10));
        bottom_panel.setBackground(Color.GRAY);
        btn_back.setBackground(Color.ORANGE);
        l_search.setForeground(Color.WHITE);
        bottom_panel.add(btn_back);
        bottom_panel.add(l_search);
        bottom_panel.add(tf_search);
    
         
        JScrollPane sp_table = new JScrollPane();
        sp_table.add(table);
        sp_table.setViewportView(table); 
        JPanel p_table = new JPanel();
        p_table.setLayout(new BorderLayout());
        p_table.add(title, BorderLayout.NORTH);
        p_table.add(sp_table,BorderLayout.CENTER);
        p_table.add(bottom_panel, BorderLayout.SOUTH);
    
    
    add(p_table);
    setActionListener();

    
    }
    
    

    private void setActionListener() {
        btn_back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    
    
   
    
    
    
    
    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        // names of columns
        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }
        // data of the table
        Vector<Vector<Object>> data = new Vector<>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
        }
        data.add(vector);
        }
    return new DefaultTableModel(data, columnNames);
    }
}