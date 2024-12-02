/*
*This class establish a connection to the database.
*it does this by loading the JDBC driver and creating a connection object.
 */
package org.WTT.configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection implements AutoCloseable{
    private static Connection con;


 public static Connection getConnection(){
  //geeksForGeeks modified code
  //Check if connection is valid
  try {
   con = DriverManager.getConnection(
           "jdbc:mysql://localhost:3306/nurses_db", "root",
           "5945");
   System.out.println("Connection established.");
  }
  catch (SQLException e) {
   //e.printStackTrace();
   System.out.println(e.getMessage());
  }

     return con;
 }


 @Override
    public void close() throws Exception {
  System.out.println("CLOSING CONNECTION");
  if(getConnection() != null && !getConnection().isClosed()){
   getConnection().close();

  }
 }

}

