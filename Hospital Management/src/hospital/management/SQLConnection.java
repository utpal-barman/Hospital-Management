/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.management;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Utpal Barman
 */
public class SQLConnection {
    Connection connection;
    String url = "jdbc:mysql://localhost/hospital";
    
    public Connection getDatabaseConnection(){
        try {
            connection = DriverManager.getConnection(url,"root","");
            System.out.println("Connectecd to Server");
            return connection;
        } catch (Exception e) {
            System.out.println("Not Connected to Server.");
            JOptionPane.showMessageDialog(null, "Cannot connect to server. Check XAMPP connection!", "Error",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return null;
        }
    }

}
