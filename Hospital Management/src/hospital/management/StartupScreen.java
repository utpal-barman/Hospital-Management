
package hospital.management;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
/**
 *
 * @author Utpal Barman
 */
public class StartupScreen extends JFrame{
    
    ImageIcon icon = new ImageIcon("icon.png");
    JLabel l_hos = new JLabel("Project Name: Hospital Management");
    JLabel l_image = new JLabel(icon);
    JLabel l_myinfo = new JLabel("Developed By Utpal BARMAN");
    
    JButton b_patient = new JButton("Patient's Zone (For Appointment Only)");
    JButton b_doct = new JButton("Doctors Login");    
    JButton b_admin = new JButton("Admin Login");
    JButton b_pat = new JButton("All appointments");
    JButton b_aboutMe = new JButton("About Me");
    
    
    public StartupScreen(){
        setTitle("Login Form");
        setSize(650,650);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        
        JPanel top = new JPanel();
        JPanel mainPanel = new JPanel();
        JPanel btnPanel = new JPanel();
        JPanel imagePanel = new JPanel();
        JPanel bottom = new JPanel();
        JPanel rootPanel = new JPanel();
        
        top.add(l_hos);
        top.setBackground(new Color(100, 50, 110));
        l_hos.setForeground(Color.WHITE);
        l_hos.setFont(new Font("font", Font.BOLD, 30));
        
        btnPanel.setLayout(new GridLayout(5, 1, 1, 10));
        btnPanel.add(b_patient);
        btnPanel.add(b_doct);
        btnPanel.add(b_admin);
        btnPanel.add(b_pat);
        btnPanel.add(b_aboutMe);
        
        imagePanel.add(l_image);
        
        mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 10));
        mainPanel.add(btnPanel);
        mainPanel.add(imagePanel);
        
        bottom.add(l_myinfo);
        bottom.setBackground(new Color(47, 95, 113));
        l_myinfo.setForeground(Color.WHITE);
        l_myinfo.setFont(new Font("font", Font.BOLD, 13));
        
        rootPanel.setLayout(new GridLayout(3,1,1,20));
        rootPanel.add(top);
        rootPanel.add(mainPanel);
        rootPanel.add(bottom);
        
        add(rootPanel);
        
        setActionListener();
        
        
    }

    private void setActionListener() {
        b_patient.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AppointADoctor dc = new AppointADoctor();
                dc.setVisible(true);
                
            }
        });
        
        b_doct.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    DoctorsLogin dl = new DoctorsLogin();
                    dl.setVisible(true);
                }catch(Exception ex){
                    Logger.getLogger(StartupScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        b_admin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AdminLogin li = new AdminLogin();
                li.setVisible(true);
            }
        });
        
        b_pat.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AllAppointments op = new AllAppointments();
                    op.setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(StartupScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        b_aboutMe.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AboutMe ab = new AboutMe();
                ab.setVisible(true);
            }
        });
        
    
    }
}