package org.WTT.visual;

import javax.swing.*;
import java.sql.*;

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

    public DatabaseEditor() {
        // GUI Setup
        setContentPane(mainPanel);
        setTitle("Nurse and Patient UI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setSize(750, 300);
        setLocationRelativeTo(null);
        setVisible(true);

        // Add Nurse
        addNurse.addActionListener(e -> {
            String firstName = firstNTextField.getText();
            String lastName = lastNTextField.getText();
            String userId = userTextField.getText();
            String license = licensetextField.getText();
            String licenseExp = licenseExpDateTextField.getText();
            String cert = certTextField.getText();
            String certDate = CertDateTextField.getText();
            String email = emailTextField.getText();

            // Add nurse to database
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_db", "root",
                    "5945");
                 PreparedStatement statement = connection.prepareStatement(
                         "INSERT INTO Nurses (user_id, First_Name, Last_name, Nurse_License_Type, License_Expiration_Date, Certification_Type, Certification_Expiration_Date, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
                statement.setInt(1, Integer.parseInt(userId));
                statement.setString(2, firstName);
                statement.setString(3, lastName);
                statement.setString(4, license);
                statement.setDate(5, Date.valueOf(licenseExp));
                statement.setString(6, cert);
                statement.setDate(7, Date.valueOf(certDate));
                statement.setString(8, email);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(DatabaseEditor.this, "Nurse added successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(DatabaseEditor.this, "Error: " + ex.getMessage());
            }
        });

        // Display Nurses
        displayNurseButton.addActionListener(e -> {
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_db", "root",
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
                JOptionPane.showMessageDialog(DatabaseEditor.this, nurses.toString());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(DatabaseEditor.this, "Error: " + ex.getMessage());
            }
        });


    }

    private Object getWarningString(String s) {
        return null;
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
