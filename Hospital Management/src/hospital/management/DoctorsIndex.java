package hospital.management;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Utpal DrBarman
 */
public class DoctorsIndex extends JFrame {
    SQLConnection instance = new SQLConnection();
    Connection connection = instance.getDatabaseConnection();
   
    String name = HospitalManagement.doct;
      
    JButton reject = new JButton("Reject Request");
    JButton accept = new JButton("Accept Request");
    JButton text = new JButton(name+"'s Requested Appointments");
    JTable table;
    DefaultTableModel model = new DefaultTableModel();;
       
    public DoctorsIndex(){
        setSize(900,700);
        setVisible(true);
        setResizable(false);
    
        try{
            String sql = "SELECT * FROM info WHERE Doctor = '"+name+"' AND (Status = 'Pending' OR Status = 'Accepted')";
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery(sql);
        
            //getting table model by resultset
            ResultSetMetaData metaData = resultset.getMetaData();
            // names of columns
            Vector<String> columnNames = new Vector<>();
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }
            // data of the table
        Vector<Vector<Object>> data = new Vector<>();
        while (resultset.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(resultset.getObject(columnIndex));
            }
            data.add(vector);
         }
        //Here is final table model from database
        model =  new DefaultTableModel(data, columnNames);
        
        table = new JTable(model);        
    }catch(SQLException ex){
        ex.printStackTrace();
    }
       
    JScrollPane sp_table = new JScrollPane();
    sp_table.add(table);
    sp_table.setViewportView(table);
        

    
    JPanel panel_button = new JPanel();
    panel_button.setBackground(Color.GRAY);
    accept.setBackground(Color.GREEN);
    reject.setBackground(Color.orange);
    panel_button.add(accept);
    panel_button.add(reject);
    
    
    JPanel mypanel = new JPanel();
    mypanel.setLayout(new BorderLayout());
    text.setFont(new Font("myfont", Font.BOLD, 15));
    text.setBackground(Color.GRAY);
    text.setForeground(Color.WHITE);
    text.setFont(new Font("myfont", Font.BOLD,25));
    mypanel.add(text, BorderLayout.NORTH);
    mypanel.add(sp_table, BorderLayout.CENTER);
    mypanel.add(panel_button, BorderLayout.SOUTH);
    
    
    
    add(mypanel);
    
    setActionListener();
  
    }
    
    
    private void setActionListener() {
        accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Thread.sleep(500);
                    
                }   catch (InterruptedException ex) {
                        Logger.getLogger(DoctorsIndex.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    //update table
                int column = 0;
                int row = table.getSelectedRow();
                   
                if(row == -1){
                    JOptionPane.showMessageDialog(null, "No Row is Selected to accecpt patient request!");
                }
                else{
                    try {
                        String value = table.getModel().getValueAt(row, column).toString();
                        String sql = "UPDATE info SET Status = 'Accepted' WHERE Mobile = '"+value+"'";
                        Statement statement = connection.createStatement();
                        statement.executeUpdate(sql);
                        model.setValueAt("Accepted", row, 4);
                        System.out.println("SQL: "+sql);
                        model.fireTableDataChanged();

                    }   catch(Exception ex){
                            ex.printStackTrace();
                        }            
                }
                     
            //end of updating
            }
        });
        
        reject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Thread.sleep(500);
                }   catch (InterruptedException ex) {
                        Logger.getLogger(DoctorsIndex.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    //update table
                int column = 0;
                int row = table.getSelectedRow();
                   
                if(row == -1){
                    JOptionPane.showMessageDialog(null, "No Row is Selected to reject patient request!");
                }
                else{
                    try {
                        String value = table.getModel().getValueAt(row, column).toString();
                        String sql = "UPDATE info SET Status = 'Rejected' WHERE Mobile = '"+value+"'";
                        Statement statement = connection.createStatement();
                        statement.executeUpdate(sql);
                        System.out.println("SQL: "+sql);
                        model.removeRow(row);
                    }   catch(Exception ex){
                            ex.printStackTrace();
                        }            
                }
                     
            //end of updating
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
