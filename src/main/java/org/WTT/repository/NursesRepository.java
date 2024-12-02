/*
 *This class Creates, reads, updates, and delete data in nurse database
 *Source 1: https://www.geeksforgeeks.org/simplifying-crud-operation-with-jdbc/
 *Source 2: Binary Logic IT's Matthew code
 *   */
package org.WTT.repository;
import org.WTT.configuration.DatabaseConnection;
import org.WTT.entity.Nurses;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NursesRepository {

    private static Connection con;

    public NursesRepository() {
        con = DatabaseConnection.getConnection();
    }

    public boolean newNurse(Nurses nurse) {
        //Creates new record  by using the connection object to create a new record in the database.
        // Add nurse to database
        try (
                PreparedStatement statement = con.prepareStatement(
                        "INSERT INTO Nurses (user_id, First_Name, Last_name, Nurse_License_Type, License_Expiration_Date," +
                                " Certification_Type, Certification_Expiration_Date, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            System.out.println("Setting user ID: " + nurse.getUserId());
            statement.setInt(1, nurse.getUserId());
            System.out.println("Setting nurse first name: " + nurse.getFirstN());//
            statement.setString(2, nurse.getFirstN());
            System.out.println("Setting nurse last name: " + nurse.getLastN());
            statement.setString(3, nurse.getLastN());
            System.out.println("Setting nurse license type: " + nurse.getNurseLicense());
            statement.setString(4, nurse.getNurseLicense());
            System.out.println("Setting license exp date: " + nurse.getLicenseExpDate());
            statement.setDate(5, Date.valueOf(nurse.getLicenseExpDate()));
            System.out.println("Setting certification type: " + nurse.getCertification());
            statement.setString(6, nurse.getCertification());
            System.out.println("Setting cert exp date: " + nurse.getCertExpDate());
            statement.setDate(7, Date.valueOf(nurse.getCertExpDate()));
            System.out.println("Setting email: " + nurse.getEmail());
            statement.setString(8, nurse.getEmail());
            statement.executeUpdate();
            System.out.println("Nurse added successfully!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    public Object assignNursesToPatients() {
        try { /*
            // Fetch all nurses
            List<Integer> nurseIds = getIds("Nurses", "user_id");

            // Fetch all patients
            List<Integer> patientIds = getIds("Patients", "patient_id");

            if (nurseIds.isEmpty() || patientIds.isEmpty()) {
                System.out.println("No nurses or patients available for assignment.");
                return;
            }

            Random random = new Random();
            for (Integer patientId : patientIds) {
                // Randomly select a nurse
                int randomNurseId = nurseIds.get(random.nextInt(nurseIds.size()));

                // Assign nurse to patient
                assignNurseToPatient(randomNurseId, patientId);
            }*/
            String query = """
                SELECT n.user_id, n.First_Name AS NurseFirstName, n.Last_name AS NurseLastName,
                       p.patient_id, p.First_Name AS PatientFirstName, p.Last_name AS PatientLastName
                FROM NursePatientAssignment np
                JOIN Nurses n ON np.nurse_id = n.user_id
                JOIN Patients p ON np.patient_id = p.patient_id
                ORDER BY n.user_id;
                """;

            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nurses_db", "root", "5945");
                 PreparedStatement statement = con.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                System.out.println("Nurses Assigned to Patients:");
                System.out.println("----------------------------------------------------------------------------------------------------------------");
                System.out.printf("%-10s %-20s %-20s %-10s %-20s %-20s%n",
                        "Nurse ID", "Nurse First Name", "Nurse Last Name",
                        "Patient ID", "Patient First Name", "Patient Last Name");
                System.out.println("---------------------------------------------------------------------------------------------------------------");

                while (resultSet.next()) {
                    int nurseId = resultSet.getInt("user_id");
                    System.out.println("Nurse ID:");
                    String nurseFirstName = resultSet.getString("NurseFirstName");
                    String nurseLastName = resultSet.getString("NurseLastName");
                    int patientId = resultSet.getInt("patient_id");
                    String patientFirstName = resultSet.getString("PatientFirstName");
                    String patientLastName = resultSet.getString("PatientLastName");


                    System.out.printf("%-10d %-20s %-20s %-10d %-20s %-20s%n",
                            nurseId, nurseFirstName, nurseLastName,
                            patientId, patientFirstName, patientLastName);


                    System.out.println("-------------------------------------------------------------------------------------------------------------");

                } }



            System.out.println("Nurses assigned to patients successfully.");
        } catch (SQLException e) {
            System.out.println("Error during assignment: " + e.getMessage());
        }

        return null;
    }

    private List<Integer> getIds(String tableName, String idColumn) throws SQLException {
        List<Integer> ids = new ArrayList<>();
        String query = "SELECT " + idColumn + " FROM " + tableName;
        try (PreparedStatement statement = con.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                ids.add(resultSet.getInt(idColumn));
            }
        }
        return ids;
    }

    private void assignNurseToPatient(int nurseId, int patientId) throws SQLException {
        String insertQuery = "INSERT INTO NursePatientAssignment (nurse_id, patient_id) VALUES (?, ?)";
        try (PreparedStatement statement = con.prepareStatement(insertQuery)) {
            statement.setInt(1, nurseId);
            statement.setInt(2, patientId);
            statement.executeUpdate();
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
            //con.close();
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
    public void update (Nurses nurse){
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
            statement.setInt(8, nurse.getUserId());


            // Execute the update
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                // Display nurse details in the console
                System.out.println("Nurse Details Updated:");
                System.out.println("User ID: " + nurse.getUserId());
                System.out.println("First Name: " + nurse.getFirstN());
                System.out.println("Last Name: " + nurse.getLastN());
                System.out.println("License Type: " + nurse.getNurseLicense());
                System.out.println("License Expiration Date: " + nurse.getLicenseExpDate());
                System.out.println("Certification Type: " + nurse.getCertification());
                System.out.println("Certification Expiration Date: " + nurse.getCertExpDate());
                System.out.println("Email: " + nurse.getEmail());
            } else {
                System.out.println("No record found with User ID: " + nurse.getUserId());
            }
        } catch (SQLException e) {
            System.out.println("Error updating nurse: " + e.getMessage());
        }
    }
    public void deleteId (int id){
        //Deletes record using an SQL DELETE statement and execute it using the connection object.
        try {
            String sql = "DELETE FROM Nurses WHERE user_id = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);

            int rowDeleted = statement.executeUpdate();
            if(rowDeleted>0) {
                System.out.println("Nurse record with User ID " + id + " has been deleted successfully.");
            } else {
                System.out.println("No record found with User ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting nurse: " + e.getMessage());
        }
    }








}
