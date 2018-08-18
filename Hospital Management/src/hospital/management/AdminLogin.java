package hospital.management;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class AdminLogin extends JFrame {
    JLabel l_hospitalName = new JLabel("RUET Hospital");
    JLabel l_user = new JLabel("Username:   ");
    JLabel l_password = new JLabel("Password:   ");
    
    JTextField tf_user = new JTextField(20);
    JPasswordField tf_password = new JPasswordField(20);
    
    JButton btn_login = new JButton("Login");
    JButton btn_clear = new JButton("Clear");
    JButton btn_back = new JButton("Back");
    
    public AdminLogin(){
        setTitle("Login Form");
        setSize(500,350);
        setResizable(false);
        setVisible(true);

        JPanel top = new JPanel();
        top.setBackground(new Color(150, 150, 150));
        l_hospitalName.setFont(new Font("NewFont", Font.BOLD, 25));
        l_hospitalName.setForeground(Color.WHITE);
        top.add(l_hospitalName);
        
        JPanel center_left = new JPanel();
        center_left.setBackground(new Color(250, 250, 200));
        center_left.setLayout(new GridLayout(2, 1, 1, 8));
        center_left.add(l_user);
        center_left.add(l_password);
        
        JPanel center_right = new JPanel();
        center_right.setBackground(new Color(250, 250, 200));
        center_right.setLayout(new GridLayout(2, 1, 1, 8));
        center_right.add(tf_user);
        center_right.add(tf_password);
        
        JPanel center = new JPanel();
        center.setBorder(BorderFactory.createTitledBorder("ADMIN LOGIN"));
        center.setBackground(new Color(250, 250, 200));
        center.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 15));
        center.add(center_left);
        center.add(center_right);
        
        JPanel bottom = new JPanel();
        btn_login.setBackground(Color.GREEN);
        btn_clear.setBackground(Color.ORANGE);
        btn_back.setBackground(Color.YELLOW);
        bottom.add(btn_login);
        bottom.add(btn_clear);
        bottom.add(btn_back);
        
        JPanel main_panel = new JPanel();
        main_panel.setLayout(new GridLayout(3, 1, 1, 1));
        main_panel.add(top);
        main_panel.add(center);
        main_panel.add(bottom);
        
        add(main_panel);
        
        setActionListener();
        
    }

    private void setActionListener() {
        // ActionListener for "AdminLogin" button
        btn_login.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tf_user.getText().equals("admin") && tf_password.getText().equals("utpal")){
                    PatientTable table = new PatientTable();
                    table.setVisible(true);
                    dispose();
                }
                else
                    JOptionPane.showMessageDialog(null, "Sorry Username and Password didn't match!!");
                
            }
        });
        
        
        btn_clear.addActionListener(new ActionListener() {
            // ActionListener for "Clear" button
            @Override
            public void actionPerformed(ActionEvent e) {
                tf_user.setText("");
                tf_password.setText("");
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
