package org.WTT.repository;

import org.WTT.configuration.DatabaseConnection;
import org.WTT.entity.Patient;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PatientRepo {

    private static Connection con;

    public PatientRepo() {
        con = DatabaseConnection.getConnection();
    }

    public boolean newPatient(Patient patient) {
        //Creates new record  by using the connection object to create a new record in the database.
        String sql = "INSERT INTO patients (patient_id, First_Name, Last_name,Admission_Date, Date_of_birth, email) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            // Debugging print statement


            System.out.println("Setting patient ID: " + patient.getPatientId());
            statement.setInt(1, patient.getPatientId());
            System.out.println("Setting patient first name: " + patient.getFirstN());// replace with getFirstN
            statement.setString(2, patient.getFirstN());// replace with getFirstN
            System.out.println("Setting patient last name: " + patient.getLastN());
            statement.setString(3, patient.getLastN());
            System.out.println("Setting patient DOB: " + patient.getPatientDob());
            statement.setString(4, patient.getPatientDob());// reading as 4

            System.out.println("Setting patient admission date: " + patient.getAdmissionDate());
            statement.setString(5, patient.getAdmissionDate());
            System.out.println("Setting email: " + patient.getEmail());
            statement.setString(6, patient.getEmail());

            statement.executeUpdate();
            int row = statement.executeUpdate();

            if (row > 0) System.out.println("New Record created.");


        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public List<Patient> findPatient() {
        List<Patient> patient = new LinkedList<>();

        String sql = "SELECT * FROM patients";
        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while(resultSet.next()) {
                Patient patients = new Patient();
                patients.setPatientId(resultSet.getInt("patient_id"));
                patients.setFirstN(resultSet.getString("First_Name"));
                patients.setLastN(resultSet.getString("Last_name"));
                patients.setPatientDob(resultSet.getString("date_of_birth"));
                patients.setAdmissionDate(resultSet.getString("admission_date"));
                patients.setEmail(resultSet.getString("email"));
                patient.add(patients);
                // Display each record
                System.out.println("patient ID: " + patients.getPatientId());
                System.out.println("First Name: " + patients.getFirstN());
                System.out.println("Last Name: " + patients.getLastN());
                System.out.println("License type: " + patients.getPatientDob());

                System.out.println("certification type: " + patients.getAdmissionDate());
                System.out.println("Email: " + patients.getEmail());
                System.out.println("---------------------------");
            }
            // Close the resources
            //con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return patient;
    }



    public Patient findById(int id) {
        Patient patient = null;
        String sql = "SELECT * FROM Patients WHERE patient_id = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)){
            statement.setLong(1, id );
            try(ResultSet resultSet = statement.executeQuery()) {
                System.out.println("Enter patient id: " + resultSet.next());
                patient = new Patient();
                patient.setPatientId(resultSet.getInt("patient_id"));
                patient.setFirstN(resultSet.getString("First_Name"));
                patient.setLastN(resultSet.getString("Last_name"));
                patient.setPatientDob(resultSet.getString("date_of_birth"));
                patient.setAdmissionDate(resultSet.getString("admission_date"));
                patient.setEmail(resultSet.getString("email"));
                //patient.add(patients);
                // Displaying patient details in the console
                // Display each record
                System.out.println("patient ID: " + patient.getPatientId());
                System.out.println("First Name: " + patient.getFirstN());
                System.out.println("Last Name: " + patient.getLastN());
                System.out.println("License type: " + patient.getPatientDob());

                System.out.println("certification type: " + patient.getAdmissionDate());
                System.out.println("Email: " + patient.getEmail());
                System.out.println("---------------------------");

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return patient;
    }
    public void update (Patient patient){
        //Update record in database using an SQL UPDATE statement and execute it using the connection object.
        try {
            String sql = "UPDATE Patients SET (patient_id, First_Name, Last_name,Admission_Date, Date_of_birth, email)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, patient.getFirstN());
            statement.setString(2, patient.getLastN());
            statement.setString(3, patient.getPatientDob());
            statement.setString(4, patient.getAdmissionDate());

            statement.setString(5, patient.getEmail());

            statement.executeUpdate();
            // Displaying patient details in the console
            // Display each record
            System.out.println("patient ID: " + patient.getPatientId());
            System.out.println("First Name: " + patient.getFirstN());
            System.out.println("Last Name: " + patient.getLastN());
            System.out.println("License type: " + patient.getPatientDob());

            System.out.println("certification type: " + patient.getAdmissionDate());
            System.out.println("Email: " + patient.getEmail());
            System.out.println("---------------------------");

            System.out.println("Record updated.");
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
    public void deleteId (int id){
        //Deletes record using an SQL DELETE statement and execute it using the connection object.
        try {
            String sql = "DELETE FROM Patients WHERE patient_id = ?";
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