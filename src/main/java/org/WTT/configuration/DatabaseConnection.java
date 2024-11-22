/*
*This class establish a connection to the database.
*it does this by loading the JDBC driver and creating a connection object.
 */
package org.WTT.configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection implements AutoCloseable{
    //String dbURL = "jdbc:mysql://localhost:3306/mydb";//database url
    //String username = "root";
    //String password = "5945";
    private static Connection con;


 public static Connection getConnection(){
  //geeksForGeeks modified code
  try {
   con = DriverManager.getConnection(
           "jdbc:mysql://localhost:3306/test_db", "root",
           "5945");
   System.out.println("Connection established.");
  }
  catch (SQLException e) {
   //e.printStackTrace();
   System.out.println(e.getMessage());
  }

     return con;
 }
 /*public static Connection getConnection() {
  try {
   // Replace these with your actual database details
   String url = "jdbc:mysql://localhost:3306/test_db";
   String user = "root";
   String password = "5945";

   Connection connection = DriverManager.getConnection(url, user, password);

   // Check if connection is valid
   if (connection != null && connection.isValid(2)) {
    return connection;
   }
  } catch (SQLException e) {
   e.printStackTrace();
  }
  return null; // In case of an error, return null
 }
 *
  */







 @Override
    public void close() throws Exception {
  System.out.println("CLOSING CONNECTION");
  if(getConnection() != null && !getConnection().isClosed()){
   getConnection().close();

  }
 }

}

