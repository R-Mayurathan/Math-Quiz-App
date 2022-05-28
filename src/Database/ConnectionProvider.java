/*
 * This class i sto get database connection
 * @author  Rasu Mayurathan
 * @version 1.0
 * @since   2021-11-22
 */
package Database;

import java.sql.*;

/**
 *
 * @author Mayu
 */
public class ConnectionProvider {
    
    /***
     * This method is to connect database
     * @return 
     */
    public static Connection getConnection()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/qems?zeroDateTimeBehavior=convertToNull","root","");
            return con;
            
        } catch (Exception e) 
        {
            return null;
        }
    }
           
    
}
