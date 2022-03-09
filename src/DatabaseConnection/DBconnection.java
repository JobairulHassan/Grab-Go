/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseConnection;

import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author FUJITSU
 */


//



//


public class  DBconnection {
   private  Statement stmt = null;
    private static Connection connection ;
    
    public static Connection connectionDatabase() throws ClassNotFoundException, SQLException{
        System.out.println("DB connection Starting..");
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionUrl = "jdbc:sqlserver://localhost:1433;"
                + "user=sa;password=usafarifa;" 
                + "databaseName=GrabandGo;";
        connection = DriverManager.getConnection(connectionUrl);
        System.out.println("Connected database successfully.........");
        java.sql.Statement stmt = connection.createStatement();
        return connection;
      }

    

    
    public void connectToDB() throws ClassNotFoundException, SQLException{
        System.out.println("DB connecting..............");
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");   //
        

        String connectionUrl = "jdbc:sqlserver://localhost:1433;user=sa;password=usafarifa;" + "databaseName=GrabandGo;";


      
        connection = DriverManager.getConnection(connectionUrl);
        System.out.println("Connected database successfully.........");
        java.sql.Statement stmt = connection.createStatement();
         
            }
    
    public void disconnectFromDB(){

    try{
       // if (rs != null){rs.close();}
        if (stmt != null){stmt.close();}
        if (connection != null){connection.close();}
    }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Unable to close connection");
        }
    }

    public boolean insertDataToDB(String query)
     {
        try
        {
            java.sql.Statement stmt=connection.createStatement();
            stmt.executeUpdate(query);
            return true;
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Unable to Insert Data");
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } 
        
     }
        public boolean DeleteDataToDB(String query)
     {
        try
        {
            java.sql.Statement stmt=connection.createStatement();
            stmt.executeUpdate(query);
            return true;
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Unable to Delete Data");
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }        
     }
    
    public ResultSet queryToDB(String query)
     {
        try
        {
            java.sql.Statement stmt=connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs;

            
        }
        catch (SQLException ex)
        {
           
           return null;
        }        
        
     }

}
