package org.WTT;
import org.WTT.configuration.DatabaseConnection;
import org.WTT.entity.Nurses;
import org.WTT.entity.Patient;
import org.WTT.repository.NursesRepository;
import org.WTT.repository.PatientRepo;
import org.WTT.visual.DatabaseEditor;

import javax.swing.*;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;



public class Main extends JFrame {

    public static void main(String[] args) {


        try {
            if (DatabaseConnection.getConnection().isValid(30)) {
                System.out.println("Database Connected");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        Nurses nurse = new Nurses();
        Patient patient = new Patient();
        NursesRepository nursesRepository = new NursesRepository();
        PatientRepo patientRepository = new PatientRepo();


        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("hello how would you like to use program:");
            System.out.println("1- UI ");
            System.out.println("2- Terminal");
            System.out.println("3- exit program");
            int environmentChoice = input.nextInt();


            //
            if (environmentChoice == 1) {
                new DatabaseEditor();
            }  else if (environmentChoice == 2) {
                //switch (environmentChoice) {
                //case 1:

                System.out.println("""
                        *=Nurse-patient-management-app==========*
                        |   Welcome to the nurse and patient    |
                        |         Management System             |
                        |  The following options are available  |
                        | 1) Add new nurse                      |
                        | 2) Add patient                        |
                        | 3) display all nurses                 |
                        | 4) display all patients               |
                        | 5) Find nurse by id                   |
                        | 6) Find patient by id                 |
                        | 7) Update nurse record                |
                        | 8) Update patient record              |
                        | 9) Delete nurse record                |
                        | 10) Delete patient record             |
                        | 11) Nurse/patient assignment          |
                        | 12) Exit the program                  |
                        *=======================================*
                        """);

                input = new Scanner(System.in);
                int answer = input.nextInt();

                switch (answer) {
                    case 1:
                        input.nextLine();
                        System.out.println("Setting user ID: ");
                        nurse.setUserId(input.nextInt());

                        input.nextLine(); // noticed pattern that after nextInt one has to add this at the next line for inputs to work properly
                        System.out.println("Enter nurse first name: ");// replace with getFirstN
                        nurse.setFirstN(input.nextLine()); // first name


                        System.out.println("Enter nurse last name: ");
                        nurse.setLastN(input.nextLine()); // last name
                        System.out.println("Enter nurse license type: ");
                        nurse.setNurseLicense(input.nextLine());// License type

                        //input.nextLine();
                        System.out.println("Enter license exp date: ");
                        nurse.setLicenseExpDate(input.nextLine());// license expiration date
                        //input.nextLine();
                        System.out.println("Enter certification type: ");
                        nurse.setCertification(input.nextLine());// certification type
                        //input.nextLine();

                        System.out.println("Enter cert exp date: ");
                        nurse.setCertExpDate(input.nextLine());// certification expiration date

                        //input.nextLine();
                        System.out.println("Enter email: ");
                        nurse.setEmail(input.nextLine());


                        //Nurses nurse = new Nurses();
                        nurse = new Nurses(nurse.getUserId(),
                                nurse.getFirstN(),
                                nurse.getLastN(),
                                nurse.getLicenseExpDate(),
                                nurse.getNurseLicense(),
                                nurse.getCertification(),
                                nurse.getCertExpDate(),
                                nurse.getEmail());


                        nursesRepository.newNurse(nurse);

                        nursesRepository.findNurses().forEach(System.out::println);


                        break;
                    case 2:
                        input.nextLine();
                        System.out.println("Setting patient ID: ");
                        patient.setPatientId(input.nextInt());

                        input.nextLine();
                        System.out.println("Enter Patient first name: ");// replace with getFirstN
                        patient.setFirstN(input.nextLine()); // first name
                        System.out.println("Enter patient last name: ");
                        patient.setLastN(input.nextLine()); // last name
                        System.out.println("Enter patient date of birth: ");
                        patient.setPatientDob(input.nextLine());

                        //input.nextLine();
                        System.out.println("Enter admission date: ");
                        patient.setAdmissionDate(input.nextLine());
                        //input.nextLine();
                        System.out.println("Enter email: ");
                        patient.setEmail(input.nextLine());


                        //Nurses nurse = new Nurses();


                        patientRepository.newPatient(patient);

                        patientRepository.findPatient().forEach(System.out::println);


                        break;

                    case 3:


                        nursesRepository.findNurses().forEach(System.out::println);// table data visualization
                        nursesRepository.findNurses();
                        break;
                    case 4:


                        //patientRepository.findPatient().forEach(System.out::println);// table data visualization
                        patientRepository.findPatient();
                        break;
                    case 5:

                        System.out.println("Enter user id of nurse you want to find: ");

                        nurse.setUserId(input.nextInt());


                        nursesRepository.findById(patient.getPatientId());

                        break;
                    case 6:

                        System.out.println("Enter user id of patient you want to find: ");

                        patient.setPatientId(input.nextInt());


                        patientRepository.findById(patient.getPatientId());

                        break;
                    case 7:

                        System.out.println("Enter user id of nurse you want to update: ");
                        int userId = input.nextInt();
                        input.nextLine(); // Consume the newline character

                        //Nurses update = new Nurses();
                        nurse.setUserId(userId);

                        nurse.setFirstN(input.nextLine());
                        nurse.setLastN(input.nextLine());
                        // Optional fields (uncomment if required)
                        System.out.println("Enter nurse license type (or press Enter to skip): ");
                        nurse.setNurseLicense(input.nextLine());
                        if (!nurse.getNurseLicense().isEmpty()) {
                            nurse.setNurseLicense(nurse.getNurseLicense());
                        }

                        System.out.println("Enter license expiration date (YYYY-MM-DD) (or press Enter to skip): ");
                        nurse.setLicenseExpDate(input.nextLine());
                        if (!nurse.getLicenseExpDate().isEmpty()) {
                            nurse.setLicenseExpDate(nurse.getLicenseExpDate());
                        }

                        System.out.println("Enter certification type (or press Enter to skip): ");
                        nurse.setCertification(input.nextLine());
                        if (!nurse.getCertification().isEmpty()) {
                            nurse.setCertification(nurse.getCertification());
                        }

                        System.out.println("Enter certification expiration date (YYYY-MM-DD) (or press Enter to skip): ");
                        nurse.setCertExpDate(input.nextLine());
                        if (!nurse.getCertExpDate().isEmpty()) {
                            nurse.setCertExpDate(nurse.getCertExpDate());
                        }

                        System.out.println("Enter email address (or press Enter to skip): ");
                        nurse.setEmail(input.nextLine());
                        if (!nurse.getEmail().isEmpty()) {
                            nurse.setEmail(nurse.getEmail());
                        }

                        nursesRepository.update(nurse);
                        System.out.println("Nurse details updated successfully.");
                        // displays updated nurse info
                        nursesRepository.findById(nurse.getUserId());


                        break;
                    case 8:

                        System.out.println("Enter user id of patient you want to update: ");
                        int patientId = input.nextInt();
                        input.nextLine(); // Consume the newline character

                        //Nurses update = new Nurses();
                        patient.setPatientId(patientId);

                        patient.setFirstN(input.nextLine());
                        patient.setLastN(input.nextLine());


                        System.out.println("Enter admission date (YYYY-MM-DD) (or press Enter to skip): ");
                        patient.setAdmissionDate(input.nextLine());
                        if (!patient.getAdmissionDate().isEmpty()) {
                            patient.setAdmissionDate(nurse.getLicenseExpDate());
                        }


                        System.out.println("Enter patient DOB (YYYY-MM-DD) (or press Enter to skip): ");
                        patient.setPatientDob(input.nextLine());
                        if (!patient.getPatientDob().isEmpty()) {
                            patient.setPatientDob(nurse.getCertExpDate());
                        }

                        System.out.println("Enter email address (or press Enter to skip): ");
                        patient.setEmail(input.nextLine());
                        if (!patient.getEmail().isEmpty()) {
                            patient.setEmail(nurse.getEmail());
                        }

                        patientRepository.update(patient);
                        System.out.println("patient details updated successfully.");
                        // displays updated nurse info
                        patientRepository.findById(patient.getPatientId());


                        break;
                    case 9:

                        System.out.println("enter user id to delete nurse record:");
                        nurse.setUserId(input.nextInt());

                        //Nurses delete =
                        nursesRepository.deleteId(nurse.getUserId());
                        //nursesRepository.findById(nurse.getUserId());
                        break;
                    case 10:
                        System.out.println("enter user id to delete patient record:");
                        patient.setPatientId(input.nextInt());

                        //Nurses delete =
                        patientRepository.deleteId(patient.getPatientId());
                        //nursesRepository.findById(nurse.getUserId());
                        break;
                    case 11:

                            NursesRepository assignment = new NursesRepository();

                            assignment.assignNursesToPatients();;


                        break;
                    case 12:
                        // Display message
                        System.out.println(
                                "\nThank you for using the program. Goodbye!\n");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Please select an option from the menu");
                }


                //input.close();

            } else if (environmentChoice ==3) {
                System.out.println("\nGoodbye!\n");
                System.exit(0);

            } else System.out.println("please enter 1, 2, or 3");
        }



       }

    }