package org.WTT.visual;

import org.WTT.configuration.DatabaseConnection;
import org.WTT.entity.Nurses;
import org.WTT.entity.Patient;
import org.WTT.repository.NursesRepository;
import org.WTT.repository.PatientRepo;


import javax.swing.*;
import java.awt.*;
import java.sql.*;

@SuppressWarnings("BoundFieldAssignment")
public class DatabaseEditor extends JFrame {
    private JLabel firstNameL;
    private JLabel lastNameL;
    private JTextField firstNTextField;
    private JTextField lastNTextField;
    private JLabel userIdL;
    private JTextField userTextField;
    private JPanel mainPanel;
    private JLabel licenseLabel;
    private JTextField licensetextField;
    private JLabel licenseExpDateLabel;
    private JTextField licenseExpDateTextField;
    private JLabel certLabel;
    private JTextField certTextField;
    private JLabel certDateLabel;
    private JTextField CertDateTextField;
    private JLabel emailLabel;
    private JTextField emailTextField;
    private JButton addNurse;
    private JButton displayNurseButton;
    private JButton findbyidButton;
    private JButton updatebutton;
    private JButton deletebutton;

    private JButton addPatientButton;
    private JButton displayPatientsbutton;
    private JButton findpatientButton;
    private JButton updatePatientButton;
    private JButton deletePatientButton;
    private JLabel patientadminLabel;
    private JTextField adminDatetextField;
    private JLabel patientDoblabel;
    private JTextField dobtextField;
    private JRadioButton nurseToPatientRadioButton;
    public static Connection con;


    public DatabaseEditor() {
        con = DatabaseConnection.getConnection();
        // GUI Setup
        setContentPane(mainPanel);
        setTitle("Nurse and Patient UI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setSize(750, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        Nurses nurse = new Nurses();
        Patient patient = new Patient();



        NursesRepository nursesRepository = new NursesRepository();
        PatientRepo patientRepo = new PatientRepo();

        // Add Nurse
        addNurse.addActionListener(e -> {
            try {
                // Create a new Nurses object and populate it with data from the text fields
                int userId = Integer.parseInt(userTextField.getText());
                nurse.setUserId(userId);
                String firstName=firstNTextField.getText();
                nurse.setFirstN(firstName);
                String lastName = lastNTextField.getText();
                nurse.setLastN(lastName);

                String license = licensetextField.getText();
                nurse.setNurseLicense(license);
                String licenseExp = licenseExpDateTextField.getText();
                nurse.setLicenseExpDate(licenseExp);
                String cert = certTextField.getText();
                nurse.setCertification(cert);
                String certDate = CertDateTextField.getText();
                nurse.setCertExpDate(certDate);
                String email = emailTextField.getText();
                nurse.setEmail(email);


                // Parse and validate user ID
                try {
                    userId = Integer.parseInt(userTextField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(DatabaseEditor.this, "User ID must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                nurse.setUserId(userId);

                // Get and validate required fields
                firstName = firstNTextField.getText().trim();
                if (firstName.isEmpty()) {
                    JOptionPane.showMessageDialog(DatabaseEditor.this, "First Name is required.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                nurse.setFirstN(firstName);

                lastName = lastNTextField.getText().trim();
                if (lastName.isEmpty()) {
                    JOptionPane.showMessageDialog(DatabaseEditor.this, "Last Name is required.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                nurse.setLastN(lastName);

                email = emailTextField.getText().trim();
                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(DatabaseEditor.this, "Email is required.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                nurse.setEmail(email);

                // Optional fields
                nurse.setNurseLicense(licensetextField.getText().trim());
                nurse.setLicenseExpDate(licenseExpDateTextField.getText().trim());
                nurse.setCertification(certTextField.getText().trim());
                nurse.setCertExpDate(CertDateTextField.getText().trim());

                // Save nurse to the database
                boolean success =nursesRepository.newNurse(nurse);
                //con.close();

                if (success) {
                    JOptionPane.showMessageDialog(DatabaseEditor.this, "Nurse added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // Clear text fields after successful addition
                    clearTextFields();
                } else {
                    JOptionPane.showMessageDialog(DatabaseEditor.this, "Failed to add nurse. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(DatabaseEditor.this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        });
        // Add Nurse
        addPatientButton.addActionListener(e -> {
            try {
                // Create a new Nurses object and populate it with data from the text fields
                int userId = Integer.parseInt(userTextField.getText());
                patient.setPatientId(userId);
                String firstName=firstNTextField.getText();
                patient.setFirstN(firstName);
                String lastName = lastNTextField.getText();
                patient.setLastN(lastName);

                String adminDate = adminDatetextField.getText();
                patient.setAdmissionDate(adminDate);
                String patientDob = dobtextField.getText();
                patient.setPatientDob(patientDob);

                String email = emailTextField.getText();
                patient.setEmail(email);


                // Parse and validate user ID
                try {
                    userId = Integer.parseInt(userTextField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(DatabaseEditor.this, "patient ID must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                nurse.setUserId(userId);

                // Get and validate required fields
                firstName = firstNTextField.getText().trim();
                if (firstName.isEmpty()) {
                    JOptionPane.showMessageDialog(DatabaseEditor.this, "First Name is required.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                nurse.setFirstN(firstName);

                lastName = lastNTextField.getText().trim();
                if (lastName.isEmpty()) {
                    JOptionPane.showMessageDialog(DatabaseEditor.this, "Last Name is required.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                nurse.setLastN(lastName);

                email = emailTextField.getText().trim();
                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(DatabaseEditor.this, "Email is required.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                nurse.setEmail(email);


                // Save nurse to the database
                boolean success =patientRepo.newPatient(patient);
                //con.close();

                if (success) {
                    JOptionPane.showMessageDialog(DatabaseEditor.this, "Nurse added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // Clear text fields after successful addition
                    clearTextFields();
                } else {
                    JOptionPane.showMessageDialog(DatabaseEditor.this, "Failed to add nurse. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(DatabaseEditor.this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        });



        // Display Nurses
        displayNurseButton.addActionListener(e -> {

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/nurses_db", "root",
                    "5945");
                 Statement stmt = connection.createStatement();
                 ResultSet resultSet = stmt.executeQuery("SELECT * FROM Nurses")) {
                StringBuilder nurses = new StringBuilder();
                while (resultSet.next()) {
                    nurses.append("ID: ").append(resultSet.getInt("user_id")).append(", Name: ")
                            .append(resultSet.getString("First_Name")).append(" ").append(resultSet.getString("Last_name"))
                            .append(", License: ")
                            .append(resultSet.getString("Nurse_License_Type")).append(" ")
                            .append(", Expiration date: ")
                            .append(resultSet.getString("License_Expiration_Date")).append(" ")
                            .append(", Certification: ")
                            .append(resultSet.getString("Certification_Type")).append(" ")
                            .append(", expiration date: ")
                            .append(resultSet.getString("Certification_Expiration_Date")).append(" ")
                            .append(", email: ")
                            .append(resultSet.getString("email")).append(" ").append("\n");
                }
                // Create a text area for displaying data
                JTextArea textArea = new JTextArea(nurses.toString());
                textArea.setEditable(false); // Make it read-only

                // Add the text area to a scroll pane
                JScrollPane scrollPane = new JScrollPane(textArea);
                //scrollPane.setPreferredSize(new Dimension(500, 300)); // Set preferred size for the scroll pane

                // Display the scrollable dialog
                JOptionPane.showMessageDialog(DatabaseEditor.this, scrollPane, "Nurses Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(DatabaseEditor.this, "Error: " + ex.getMessage());
            }/*
            try{
                //patient.getPatientId();

                JOptionPane.showMessageDialog(null, nursesRepository.findNurses());} catch(Exception ex) {
                JOptionPane.showMessageDialog(DatabaseEditor.this, "Error: " + ex.getMessage());
            }*/
        });
        // Display Nurses
        displayPatientsbutton.addActionListener(e -> {
            /*try{
                //patient.getPatientId();

                JOptionPane.showMessageDialog(null, patientRepo.findPatient());} catch(Exception ex) {
                JOptionPane.showMessageDialog(DatabaseEditor.this, "Error: " + ex.getMessage());
            }*/
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/nurses_db", "root",
                    "5945");
                 Statement stmt = connection.createStatement();
                 ResultSet resultSet = stmt.executeQuery("SELECT * FROM patients")) {
                StringBuilder patients = new StringBuilder();
                while (resultSet.next()) {
                    patients.append("ID: ").append(resultSet.getInt("patient_id")).append(", Name: ")
                            .append(resultSet.getString("First_Name")).append(" ")
                            .append(resultSet.getString("Last_name"))
                            .append(resultSet.getString("admission_date")).append(" ")
                            .append(", date_of_birth: ").append(resultSet.getString("email"))
                            .append(" ").append("\n");
                }
                // Create a text area for displaying data
                JTextArea textArea = new JTextArea(patients.toString());
                textArea.setEditable(false); // Make it read-only

                // Add the text area to a scroll pane
                JScrollPane scrollPane = new JScrollPane(textArea);
                //scrollPane.setPreferredSize(new Dimension(500, 300)); // Set preferred size for the scroll pane

                // Display the scrollable dialog
                JOptionPane.showMessageDialog(DatabaseEditor.this, scrollPane, "Nurses Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(DatabaseEditor.this, "Error: " + ex.getMessage());
            }
        });
        //update nurse
        updatebutton.addActionListener(e -> {
            try {
                // Create a new Nurses object and populate it with data from the text fields
                int userId = Integer.parseInt(userTextField.getText());
                nurse.setUserId(userId);
                String firstName=firstNTextField.getText();
                nurse.setFirstN(firstName);
                String lastName = lastNTextField.getText();
                nurse.setLastN(lastName);

                String license = licensetextField.getText();
                nurse.setNurseLicense(license);
                String licenseExp = licenseExpDateTextField.getText();
                nurse.setLicenseExpDate(licenseExp);
                String cert = certTextField.getText();
                nurse.setCertification(cert);
                String certDate = CertDateTextField.getText();
                nurse.setCertExpDate(certDate);
                String email = emailTextField.getText();
                nurse.setEmail(email);
                nursesRepository.update(nurse);
                // con.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(DatabaseEditor.this, "Error: " + ex.getMessage());
            }


        });
        updatePatientButton.addActionListener(e -> {
            try {
                // Create a new Nurses object and populate it with data from the text fields
                int userId = Integer.parseInt(userTextField.getText());
                patient.setPatientId(userId);
                String firstName=firstNTextField.getText();
                patient.setFirstN(firstName);
                String lastName = lastNTextField.getText();
                patient.setLastN(lastName);


                String adminDate = adminDatetextField.getText();
                patient.setAdmissionDate(adminDate);

                String patientDob = dobtextField.getText();
                patient.setPatientDob(patientDob);
                String email = emailTextField.getText();
                patient.setEmail(email);
                patientRepo.update(patient);
                // con.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(DatabaseEditor.this, "Error: " + ex.getMessage());
            }


        });
        findbyidButton.addActionListener(e1 -> { try {
            int userId = Integer.parseInt(userTextField.getText());
            nurse.setUserId(userId);


            JOptionPane.showMessageDialog(null, nursesRepository.findById(nurse.getUserId()));


        } catch (Exception ex) {
            JOptionPane.showMessageDialog(DatabaseEditor.this, "Error: " + ex.getMessage());
        }
        });
        findpatientButton.addActionListener(e1 -> { try {
            int userId = Integer.parseInt(userTextField.getText());
            patient.setPatientId(userId);


            JOptionPane.showMessageDialog(null, patientRepo.findById(patient.getPatientId()));


        } catch (Exception ex) {
            JOptionPane.showMessageDialog(DatabaseEditor.this, "Error: " + ex.getMessage());
        }
        });
        nurseToPatientRadioButton.addActionListener(e -> {
            try{
                //patient.getPatientId();

            JOptionPane.showMessageDialog(null, "Successfull");} catch(Exception ex) {
                JOptionPane.showMessageDialog(DatabaseEditor.this, "Error: " + ex.getMessage());
            }


        });
        updatePatientButton.addActionListener(e -> {
            try {
                // Create a new Nurses object and populate it with data from the text fields
                int userId = Integer.parseInt(userTextField.getText());
                patient.setPatientId(userId);
                String firstName=firstNTextField.getText();
                patient.setFirstN(firstName);
                String lastName = lastNTextField.getText();
                patient.setLastN(lastName);

                String adminDate = adminDatetextField.getText();
                patient.setAdmissionDate(adminDate);
                String patientDob = dobtextField.getText();
                patient.setPatientDob(patientDob);
                String email = emailTextField.getText();
                patient.setEmail(email);
                patientRepo.update(patient);
                // con.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(DatabaseEditor.this, "Error: " + ex.getMessage());
            }

        });
        deletebutton.addActionListener(e -> {
            try {
                // Create a new Nurses object and populate it with data from the text fields
                int userId = Integer.parseInt(userTextField.getText());
                nurse.setUserId(userId);
                nursesRepository.deleteId(userId);
                //con.close();

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        });
        deletePatientButton.addActionListener(e -> {
            try {
                // Create a new Nurses object and populate it with data from the text fields
                int userId = Integer.parseInt(userTextField.getText());
                patient.setPatientId(userId);
                patientRepo.deleteId(userId);
                //con.close();

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        });


    }

    // Method to clear all text fields after successful operation
    private void clearTextFields() {
        userTextField.setText("");
        firstNTextField.setText("");
        lastNTextField.setText("");
        licensetextField.setText("");
        licenseExpDateTextField.setText("");
        certTextField.setText("");
        CertDateTextField.setText("");
        emailTextField.setText("");
    }

    private void createUIComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Initialize Components
        firstNameL = new JLabel("First Name:");
        firstNTextField = new JTextField(20);
        lastNameL = new JLabel("Last Name:");
        lastNTextField = new JTextField(20);
        userIdL = new JLabel("User ID:");
        userTextField = new JTextField(20);
        licenseLabel = new JLabel("License Type:");
        licensetextField = new JTextField(20);
        licenseExpDateLabel = new JLabel("License Expiration Date:");
        licenseExpDateTextField = new JTextField(20);
        certLabel = new JLabel("Certification Type:");
        certTextField = new JTextField(20);
        certDateLabel = new JLabel("Certification Expiration Date:");
        CertDateTextField = new JTextField(20);
        emailLabel = new JLabel("Email:");
        emailTextField = new JTextField(20);

        addNurse = new JButton("Add Nurse");
        displayNurseButton = new JButton("Display Nurses");
        findbyidButton = new JButton("Find by ID");
        updatebutton = new JButton("Update Nurse");
        deletebutton = new JButton("Delete Nurse");

        // Add components to mainPanel

        mainPanel.add(firstNameL);
        mainPanel.add(firstNTextField);
        mainPanel.add(lastNameL);
        mainPanel.add(lastNTextField);
        mainPanel.add(userIdL);
        mainPanel.add(userTextField);
        mainPanel.add(licenseLabel);
        mainPanel.add(licensetextField);
        mainPanel.add(licenseExpDateLabel);
        mainPanel.add(licenseExpDateTextField);
        mainPanel.add(certLabel);
        mainPanel.add(certTextField);
        mainPanel.add(certDateLabel);
        mainPanel.add(CertDateTextField);
        mainPanel.add(emailLabel);
        mainPanel.add(emailTextField);
        mainPanel.add(addNurse);
        mainPanel.add(displayNurseButton);
        mainPanel.add(findbyidButton);
        mainPanel.add(updatebutton);
        mainPanel.add(deletebutton);


    }


}