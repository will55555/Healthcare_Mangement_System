package org.WTT;
import org.WTT.configuration.DatabaseConnection;
import org.WTT.entity.Nurses;
import org.WTT.repository.NursesRepository;
import org.WTT.visual.DatabaseEditor;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Scanner;



public class Main extends JFrame{

    public static void main(String[] args) {


        try {
            if(DatabaseConnection.getConnection().isValid(30)){
                System.out.println("Database Connected");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        Nurses nurse = new Nurses();
        NursesRepository nursesRepository = new NursesRepository();



        while (true) {
            Scanner input = new Scanner(System.in);

            System.out.println("hello how would you like to use program:");
            System.out.println("1- UI ");
            System.out.println("2- Terminal");
            int environmentChoice = input.nextInt();
            if(environmentChoice==1)
            {
                DatabaseEditor GUI = new DatabaseEditor();

        }
            if (environmentChoice ==2) {
                System.out.println("""
                    *==========Horrorcore-Software==========*
                    |   Welcome to the Alien Management     |
                    |         System                        |
                    | The following options are available   |
                    | for use                               |
                    | 1) Add nurse                          |
                    | 2) display all nurses                |
                    | 3) Find nurse by id                   |
                    | 4) Update nurse record                |
                    | 5) Delete nurse record                |
                    | 6) Exist the program                  |
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

                        //input.nextLine();
                        //Nurses nurse = new Nurses();
                        nurse = new Nurses(nurse.getUserId(),
                                nurse.getFirstN(),
                                nurse.getLastN(),
                                nurse.getLicenseExpDate(),
                                nurse.getNurseLicense(),
                                nurse.getCertification(),
                                nurse.getCertExpDate(),
                                nurse.getEmail());

                        //nursesRepository.newNurse(nurse);

                        nursesRepository.findNurses().forEach(System.out::println);


                        break;

                    case 2:


                        nursesRepository.findNurses().forEach(System.out::println);// table data visualization
                        nursesRepository.findNurses();
                        break;
                    case 3:

                        System.out.println("Enter user id of nurse you want to find: ");

                        nurse.setUserId(input.nextInt());
                        //input.nextLine();


                        nursesRepository.findById(1);
                        Nurses find = nursesRepository.findById(nurse.getUserId());
                        break;
                    case 4:
                        //figure out the methodology for update
                        System.out.println("Enter user id of nurse you want to update: ");

                        nurse.setUserId(input.nextInt());
                        //input.nextLine();
                        nursesRepository.findById(nurse.getUserId());

                        //System.out.println("which would you like to edit: ");
                        System.out.println("""
                            *==========Horrorcore-Software==========*
                            |   which would you like to edit:       |
                            |                                       |
                            | The following options are available   |
                            | for use                               |
                            | 1) first name                         |
                            | 2) last name                          |
                            | 3) license type                       |
                            | 4) license expiration date            |
                            | 5) certification type                 |
                            | 6) certification exp date             |
                            | 7) email address                      |
                            *=======================================*
                            """);
                        Nurses update = nursesRepository.update(nurse);
                        int updateChoice = input.nextInt();
                        input.nextLine();
                        if (updateChoice == 1) {
                            System.out.println("Enter nurse first name: ");// replace with getFirstN
                            update.setFirstN(input.nextLine()); // first na

                        }

                        if (updateChoice == 2) {
                            System.out.println("Enter nurse last name: ");
                            update.setLastN(input.nextLine()); // last name


                        }
                        if (updateChoice == 3) {
                            System.out.println("Enter nurse license type: ");
                            update.setNurseLicense(input.nextLine());// License type


                        }
                        if (updateChoice == 4) {
                            //input.nextLine();
                            System.out.println("Enter license exp date: ");
                            update.setLicenseExpDate(input.nextLine());// license expiration date

                        }
                        if (updateChoice == 5) {
                            //input.nextLine();
                            System.out.println("Enter new certification type: ");
                            update.setCertification(input.nextLine());// certification type
                            //input.nextLine();
                        }
                        if (updateChoice == 6) {
                            System.out.println("Enter cert exp date: ");
                            update.setCertExpDate(input.nextLine());// certification expiration date


                        }
                        if (updateChoice == 7) {
                            //input.nextLine();
                            System.out.println("Enter email: ");
                            update.setEmail(input.nextLine());
                        }


                        //nurse.setUserId(input.nextInt());
                        //input.nextLine();


                        //nursesRepository.update(nurse);
                        break;
                    case 5:
                        int id = nurse.getUserId();
                        System.out.println("enter user id to delete user:");
                        nurse.setUserId(input.nextInt());
                        input.nextLine();

                        //Nurses delete =
                        nursesRepository.deleteId(1);
                        break;
                    case 6:
                        // Display message
                        System.out.println(
                                "\nThank you for using the program. Goodbye!\n");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Please select an option from the menu");
            }



            }
            else {System.out.println("please enter 1 or 3");}

            //input.close();

        }


            }
        }


