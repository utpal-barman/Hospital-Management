package hospital.management;

import java.sql.Connection;

/**
 *
 * @author Utpal Barman
 */
public class HospitalManagement {
    static String doct;

    /**
     * @param args the command line arguments
     */
    Connection connection;
    public static void main(String[] args) {
        // TODO code application logic here
        
        StartupScreen login = new StartupScreen(); // When program starts it will show Login class
        login.setVisible(true);
        
    }
    
}
