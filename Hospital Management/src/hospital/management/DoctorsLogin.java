/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.management;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Utpal Barman
 */
public class DoctorsLogin extends JFrame{
    JLabel l_title = new JLabel("RUET Hospital");
    JLabel l_user = new JLabel("Select Username: ");
    JComboBox jc_user = new JComboBox(new Object[]{"Dr. Abdul (Kidney Specialist)", "Dr. Barman (Child Specialist)", "Dr. Karim (Medicin)", "Dr. Mahmud (Heart Specialist)"});
    JLabel l_password = new JLabel("Enter Password: ");
    JButton btn_login = new JButton("Login");
    JButton btn_cancel = new JButton("Cancel");
    JPasswordField pf_password = new JPasswordField(20);
    
    String doc;

    
    public DoctorsLogin(){
        setTitle("Doctors Login");
        setSize(500,400);
        setResizable(false);
        setVisible(true);
        
        JPanel top = new JPanel();
        l_title.setFont(new Font("myFont", Font.BOLD, 30));
        top.setBackground(new Color(30, 130, 30));
        l_title.setForeground(Color.WHITE);
        top.add(l_title);
        
        JPanel center_left = new JPanel();
        center_left.setLayout(new GridLayout(2, 1, 1, 20));
        center_left.add(l_user);
        center_left.add(l_password);
        
        JPanel center_right = new JPanel();
        center_right.setLayout(new GridLayout(2, 1, 1, 20));
        center_right.add(jc_user);
        center_right.add(pf_password);
        
        JPanel center = new JPanel();
        center.setBorder(BorderFactory.createTitledBorder("DOCTOR's LOGIN"));
        center.setLayout(new FlowLayout(FlowLayout.LEFT,50,10));
        center.add(center_left);
        center.add(center_right);
        
        JPanel bottom = new JPanel();
        bottom.setBackground(new Color(100,50,100));
        btn_login.setBackground(Color.GREEN);
        btn_cancel.setBackground(Color.yellow);
        bottom.add(btn_login);
        bottom.add(btn_cancel);
        
        
        JPanel main_panel = new JPanel();
        main_panel.setLayout(new GridLayout(3, 1, 1, 1));
        main_panel.add(top);
        main_panel.add(center);
        main_panel.add(bottom);
        
        add(main_panel);
        
        setActionlistener();
        
    }

    public void setActionlistener() {
        btn_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jc_user.getSelectedItem().equals("Dr. Abdul (Kidney Specialist)") && pf_password.getText().equals("abdul")){
                    doc = "Dr. Abdul (Kidney Specialist)";
                    HospitalManagement.doct = doc;
                    changeActivity();
                }
                else if(jc_user.getSelectedItem().equals("Dr. Barman (Child Specialist)") && pf_password.getText().equals("barman")){
                    doc = "Dr. Barman (Child Specialist)";
                    HospitalManagement.doct = doc;
                    changeActivity();
                 }
                else if(jc_user.getSelectedItem().equals("Dr. Karim (Medicin)") && pf_password.getText().equals("karim")){
                    doc = "Dr. Karim (Medicin)";
                    HospitalManagement.doct = doc;
                    changeActivity();
                }
                else if(jc_user.getSelectedItem().equals("Dr. Mahmud (Heart Specialist)") && pf_password.getText().equals("mahmud")){
                    doc = "Dr. Mahmud (Heart Specialist)";
                    HospitalManagement.doct = doc;
                    changeActivity();
                }
        
                else
                    JOptionPane.showMessageDialog(null, "Password is not matched");
            }

            private void changeActivity() {
                try{    
                    DoctorsIndex index = new DoctorsIndex();
                    index.setVisible(true);
                    dispose();
                } catch(Exception ex){
                    Logger.getLogger(DoctorsLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }


    
}
