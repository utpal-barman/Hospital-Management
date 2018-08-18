package hospital.management;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.MaskFormatter;


/**
 *
 * @author Utpal Barman
 */
public class AppointADoctor extends JFrame {
    SQLConnection instance = new SQLConnection();
    Connection connection = instance.getDatabaseConnection();
    
    
    public String drName="Empty", appTime="Empty", day="Empty";
    String doc[] = {"Dr. Abdul (Kidney Specialist)",
                    "Dr. Barman (Child Specialist)",
                    "Dr. Mahmud (Heart Specialist)",
                    "Dr. Karim (Medicin)"
                   };
    
    String ap_day[] = {"Saturday","Sunday","Monday","Tuesday","Wednesday"};
    String ap_time[] = {"7:00pm","8:00pm","8:30pm","9:00pm"};
    
    JLabel l_mobile = new JLabel("Phone Number:");
    JTextField tf_mobile = new JTextField(20);
    
    JLabel l_doctorList = new JLabel("Select A Doctor:");
    JComboBox combo_doc = new JComboBox(doc);
    
    JLabel l_appoinDay = new JLabel("Choose a Day:");
    JComboBox combo_day = new JComboBox(ap_day);
    
    JLabel l_appoinTime = new JLabel("Choose a Time:");
    JComboBox combo_time = new JComboBox(ap_time);
    
    JButton ok = new JButton("OK");
    JButton back = new JButton("BACK TO HOME");
    
    JLabel l_label = new JLabel("MEET A DOCTOR");
    
    
    public AppointADoctor(){
        setTitle("Meet a doctor!!!");
        setSize(800,500);
        setResizable(false);
        setVisible(true);
        
        try {
            MaskFormatter mf = new MaskFormatter("+88###########");
            tf_mobile = new JFormattedTextField(mf);
        } catch (ParseException ex) {
            Logger.getLogger(AppointADoctor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        l_label.setFont(new Font("MyFont", Font.BOLD, 30));
        
        JPanel pHead = new JPanel();
        JPanel pFoot = new JPanel();
        
        pHead.add(l_label);
        pFoot.add(ok);
        pFoot.add(back);
        
        
        
        JPanel p_selectLabels = new JPanel();
        p_selectLabels.setLayout(new GridLayout(4,1,1,20));
        p_selectLabels.add(l_mobile);
        p_selectLabels.add(l_doctorList);
        p_selectLabels.add(l_appoinDay);
        p_selectLabels.add(l_appoinTime);
        
        JPanel p_selectCombo = new JPanel();
        p_selectCombo.setLayout(new GridLayout(4,1,1,10));
        p_selectCombo.add(tf_mobile);
        p_selectCombo.add(combo_doc);
        p_selectCombo.add(combo_day);
        p_selectCombo.add(combo_time);
        
        JPanel p_select = new JPanel();
        p_select.setLayout(new FlowLayout(FlowLayout.LEFT, 150, 10));
        p_select.add(p_selectLabels);
        p_select.add(p_selectCombo);
        
        
        add(p_select);
        
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.add(pHead);
        panel.add(p_select);
        panel.add(pFoot);
        
        add(panel);
        
        
        setActionListener();
       
    }
    
    
    
 
    private void setActionListener() {
        ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                                
                try {
                 
                    //inserting data to mySQL database
                    System.out.println("Inserting records into the table...");
                    
                    String mobiles = tf_mobile.getText();
                    String docs = (String) combo_doc.getSelectedItem();
                    String days = (String) combo_day.getSelectedItem();
                    String times = (String) combo_time.getSelectedItem();
                    
                    String sql = "INSERT INTO info VALUES ('"+mobiles+"','"+docs+"', '"+days+"', '"+times+"','Pending')";
                    System.out.println("SQL: "+sql);
                    
                    
                    Statement statement = connection.createStatement();
                    System.out.println("Instance is ready to execute");
                    statement.executeUpdate(sql);
                    System.out.println("Executed to database!");
                    
                    tf_mobile.setText("");
                    
                    String showOnPane = "PENDING REQUEST...\n"+(String)combo_doc.getSelectedItem()+" will receive your appointment.\n"
                            + " You have appointed him at "+(String)combo_time.getSelectedItem()
                            + " on "+(String)combo_day.getSelectedItem();
                    JOptionPane.showMessageDialog(null, showOnPane, "SUCCESS!", JOptionPane.INFORMATION_MESSAGE);
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Problem in mySQL insertion");
                }
          
            }
        });
        
        
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }    
}
