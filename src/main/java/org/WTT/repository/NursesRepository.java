/*
*This class Creates, reads, updates, and delete data in nurse database
*Source 1: https://www.geeksforgeeks.org/simplifying-crud-operation-with-jdbc/
*Source 2: Binary Logic IT's Matthew code
*   */
package org.WTT.repository;
import org.WTT.configuration.DatabaseConnection;
import org.WTT.entity.Nurses;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class NursesRepository {

    private static Connection con;

    public NursesRepository() {
        con = DatabaseConnection.getConnection();
    }

    public void newNurse(Nurses nurse) {
        //Creates new record  by using the connection object to create a new record in the database.
        String sql = "INSERT INTO nurses (user_id, First_Name, Last_name, Nurse_License_Type, License_Expiration_Date, " +
                "Certification_Type, Certification_Expiration_Date, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            // Debugging print statement


            System.out.println("Setting user ID: " + nurse.getUserId());
            statement.setInt(1, nurse.getUserId());
            System.out.println("Setting nurse first name: " + nurse.getFirstN());// replace with getFirstN
            statement.setString(2, nurse.getFirstN());// replace with getFirstN
            System.out.println("Setting nurse last name: " + nurse.getLastN());
            statement.setString(3, nurse.getLastN());
            System.out.println("Setting nurse license type: " + nurse.getNurseLicense());
            statement.setString(4, nurse.getNurseLicense());//reading as 5
            System.out.println("Setting license exp date: " + nurse.getLicenseExpDate());
            statement.setString(5, nurse.getLicenseExpDate());// reading as 4
            System.out.println("Setting certification type: " + nurse.getCertification());
            statement.setString(6, nurse.getCertification());
            System.out.println("Setting cert exp date: " + nurse.getCertExpDate());
            statement.setString(7, nurse.getCertExpDate());
            System.out.println("Setting email: " + nurse.getEmail());
            statement.setString(8, nurse.getEmail());

            statement.executeUpdate();
            int row = statement.executeUpdate();

            if (row > 0) System.out.println("New Record created.");
            //DBTablePrinter.printTable(con, "nurses");

        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    public List<Nurses> findNurses() {
        List<Nurses> nurses = new LinkedList<>();

        String sql = "SELECT * FROM Nurses";
        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while(resultSet.next()) {
                Nurses nurse = new Nurses();
                nurse.setUserId(resultSet.getInt("user_id"));
                nurse.setFirstN(resultSet.getString("First_Name"));
                nurse.setLastN(resultSet.getString("Last_name"));
                nurse.setNurseLicense(resultSet.getString("Nurse_License_Type"));
                nurse.setLicenseExpDate(resultSet.getString("License_Expiration_Date"));
                nurse.setCertification(resultSet.getString("Certification_Type"));
                nurse.setCertExpDate(resultSet.getString("Certification_Expiration_Date"));
                nurse.setEmail(resultSet.getString("email"));
                nurses.add(nurse);
                //int columnsNumber = resultSet.getColumnCount();
                //nurse = null;
                // Display each record
                System.out.println("nurse ID: " + nurse.getUserId());
                System.out.println("First Name: " + nurse.getFirstN());
                System.out.println("Last Name: " + nurse.getLastN());
                System.out.println("License type: " + nurse.getNurseLicense());
                System.out.println("license expiration date " + nurse.getLicenseExpDate());
                System.out.println("certification type: " + nurse.getCertification());
                System.out.println("certification expiration date " + nurse.getCertExpDate());
                System.out.println("Email: " + nurse.getEmail());
                System.out.println("---------------------------");
            }
            // Close the resources
            resultSet.close();
            statement.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return nurses;
    }



    public Nurses findById(int id) {
        Nurses nurse = null;
        String sql = "SELECT * FROM Nurses WHERE user_id = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)){
            statement.setLong(1, id );
            try(ResultSet resultSet = statement.executeQuery()) {
                System.out.println("Enter User id: " + resultSet.next());
                nurse = new Nurses();
                nurse.setUserId(resultSet.getInt("user_id"));
                nurse.setFirstN(resultSet.getString("First_Name"));
                nurse.setLastN(resultSet.getString("Last_name"));
                nurse.setNurseLicense(resultSet.getString("Nurse_License_Type"));
                nurse.setLicenseExpDate(resultSet.getString("License_Expiration_Date"));
                nurse.setCertification(resultSet.getString("Certification_Type"));
                nurse.setCertExpDate(resultSet.getString("Certification_Expiration_Date"));
                nurse.setEmail(resultSet.getString("email"));
                // Displaying nurse details in the console
                System.out.println("Nurse Details:");
                System.out.println("User ID: " + nurse.getUserId());
                System.out.println("First Name: " + nurse.getFirstN());
                System.out.println("Last Name: " + nurse.getLastN());
                System.out.println("License Type: " + nurse.getNurseLicense());
                System.out.println("License Expiration Date: " + nurse.getLicenseExpDate());
                System.out.println("Certification Type: " + nurse.getCertification());
                System.out.println("Certification Expiration Date: " + nurse.getCertExpDate());
                System.out.println("Email: " + nurse.getEmail());

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return nurse;
    }
    public Nurses update (Nurses nurse){
        //Update record in database using an SQL UPDATE statement and execute it using the connection object.
        try {
            String sql = "UPDATE Nurses SET First_Name = ?, Last_name = ?, Nurse_License_Type = ?, " +
                    "License_Expiration_Date = ?, Certification_Type = ?, Certification_Expiration_Date = ?, " +
                    "email = ? WHERE user_id = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, nurse.getFirstN());
            statement.setString(2, nurse.getLastN());
            statement.setString(3, nurse.getNurseLicense());
            statement.setString(4, nurse.getLicenseExpDate());
            statement.setString(5, nurse.getCertification());
            statement.setString(6, nurse.getCertExpDate());
            statement.setString(7, nurse.getEmail());

            statement.executeUpdate();
            // Displaying nurse details in the console
            System.out.println("Nurse Details:");
            System.out.println("User ID: " + nurse.getUserId());
            System.out.println("First Name: " + nurse.getFirstN());
            System.out.println("Last Name: " + nurse.getLastN());
            System.out.println("License Type: " + nurse.getNurseLicense());
            System.out.println("License Expiration Date: " + nurse.getLicenseExpDate());
            System.out.println("Certification Type: " + nurse.getCertification());
            System.out.println("Certification Expiration Date: " + nurse.getCertExpDate());
            System.out.println("Email: " + nurse.getEmail());

            System.out.println("Record updated.");
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return nurse;
    }
    public void deleteId (int id){
        //Deletes record using an SQL DELETE statement and execute it using the connection object.
        try {
            String sql = "DELETE FROM Nurses WHERE user_id = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            int row = statement.executeUpdate();
            if(row>0) System.out.println("Record deleted.");
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }

        //return deleteId(id);
    }








}
