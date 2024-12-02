1.1 Scope

This document outlines the CRUD (Create, Read, Update, Delete) operations for managing Nurse and Patient data in a healthcare management system. The operations are performed using Java and JDBC to interact with a MySQL database. The implementation is designed to:

    Support essential data operations for nurses and patients.
    Enable assignment and tracking of nurses to patients.
    Facilitate data entry, updates, and retrievals.
    Ensure data integrity and prevent invalid inputs.

This system serves as a backend and frontend solution for managing healthcare staff and patient information, suitable for small- to medium-scale medical institutions.
1.2 Constraints

    Database schema: The implementation assumes the existence of a predefined MySQL database schema with the following tables: Nurses, Patients, and NursePatientAssignment.
    Unique constraints:
        Nurses and patients must have unique email addresses.
        The user_id and patient_id fields are primary keys and must be unique.
    Data format:
        Dates must follow the YYYY-MM-DD format for both input and storage.
        The database fields are case-sensitive for string comparisons.
    Database size:
        The system is designed for small- to medium-scale institutions and may not be optimized for databases with millions of records.
    Error handling:
        Basic error handling is implemented. For production-grade applications, a more robust logging and error-handling mechanism is recommended.

1.3 Assumptions

    Pre-configured database:
        The MySQL database (nurses_db) and required tables (Nurses, Patients, NursePatientAssignment) are already set up and configured.
    Connection details:
        The correct database credentials (username and password) are provided.
    Data input:
        Valid data is provided by the user for fields like dates, email addresses, and names.
        Duplicate data, where constrained (e.g., email), will result in errors unless handled in the application layer.
    Application usage:
        The application is primarily accessed by administrative personnel responsible for managing nurse and patient records.
    Java environment:
        The system is running in a Java environment with the required dependencies (e.g., JDBC driver) installed.

Constraints and Assumptions: Practical Application
Category	Constraints	Assumptions
Database	Schema must match the documented structure.	Database connection is stable and accessible.
Input Data	Dates must use YYYY-MM-DD format; emails must follow valid email standards.	Users provide correct and complete data.
Concurrency	Single-threaded operations; no concurrency handling implemented.	Operations are performed sequentially without simultaneous updates to the same records.
Error Handling	Limited to logging SQLExceptions and console messages.	Administrators understand error messages and take corrective actions as needed.
1.4 Scope Extensions

Future extensions could include:

    Advanced search and filtering:
        Add functionality to query nurses and patients based on custom criteria (e.g., certifications, date of birth ranges).
    Bulk operations:
        Enable batch imports and exports for large datasets.
    Role-based access control:
        Implement user authentication and role-based permissions for secure data access.
    Concurrency handling:
        Support concurrent read/write operations with transaction management.
    Integration with front-end UI:
        Extend this backend solution to work with web-based user interface as it already has a GUI.
1.5 Prerequisites

    Database setup:
        Database: nurses_db
        Tables:
            Nurses:

CREATE TABLE Nurses (
    nurse_id INT PRIMARY KEY AUTO_INCREMENT,
    First_Name VARCHAR(75) NOT NULL,
    Last_name VARCHAR(75) NOT NULL,
    Nurse_License_Type VARCHAR(100),
    License_Expiration_Date DATE,
    Certification_Type VARCHAR(100),
    Certification_Expiration_Date DATE,
    email VARCHAR(100) UNIQUE NOT NULL
);

Patients:

CREATE TABLE Patients (
    patient_id INT PRIMARY KEY AUTO_INCREMENT,
    First_Name VARCHAR(75) NOT NULL,
    Last_name VARCHAR(75) NOT NULL,
    Admission_Date DATE,
    Date_of_birth DATE,
    email VARCHAR(100) UNIQUE NOT NULL
);

NursePatientAssignment:

        CREATE TABLE NursePatientAssignment (
            assignment_id INT PRIMARY KEY AUTO_INCREMENT,
            nurse_id INT NOT NULL,
            patient_id INT NOT NULL,
            FOREIGN KEY (nurse_id) REFERENCES Nurses(nurse_id),
            FOREIGN KEY (patient_id) REFERENCES Patients(patient_id)
        );

Dependencies:

    Add the MySQL JDBC driver to your project:

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.31</version>
    </dependency>

Database connection setup:

    Update connection details in the code:

        String url = "jdbc:mysql://localhost:3306/nurses_db";
        String user = "root";
        String password = "yourpassword";

2. CRUD Operations
2.1 Create
Nurses

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

Patients

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

2.2 Read
Fetch all nurses

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


Fetch all patients

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


2.3 Update
Update nurse by ID

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
2.4 Delete
Delete nurse by ID

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


Delete patient by ID

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
    
3. Sources
    Tutorials:
    https://www.geeksforgeeks.org/simplifying-crud-operation-with-jdbc/
   
    Oracle JDBC Documentation: Comprehensive documentation for JDBC API, covering database connectivity and SQL execution.
    https://docs.oracle.com/javase/tutorial/jdbc/overview/index.html

    MySQL Reference Manual: Official guide for MySQL, detailing SQL queries, table design, and foreign key constraints.
    https://dev.mysql.com/doc/refman/8.0/en/

    Random Assignments with Java Streams: Details on using Java Streams for efficient and readable coding practices.
    https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html

    PreparedStatement API Usage: Documentation explaining the use of PreparedStatement for executing parameterized SQL queries.
    https://docs.oracle.com/javase/8/docs/api/java/sql/PreparedStatement.html

    MySQL Foreign Key Constraints: Guide on setting up and using foreign key relationships in MySQL.
    https://dev.mysql.com/doc/refman/8.0/en/create-table-foreign-keys.html

    JDBC SQL Constraints: Overview of using constraints in SQL with JDBC.
    https://docs.oracle.com/javase/8/docs/api/java/sql/package-summary.html

    ResultSet API Usage: Reference for navigating and processing SQL query results using JDBC.
    https://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html

    Best Practices in Healthcare Database Design: Insights into designing efficient healthcare database systems.
    https://www.sciencedirect.com/topics/computer-science/database-design

    Error Handling in JDBC: Best practices for catching and managing SQL errors in JDBC.
    https://www.baeldung.com/java-jdbc-error-handling

    Java Random Class: Documentation on using the Random class for generating pseudorandom values.
    https://docs.oracle.com/javase/8/docs/api/java/util/Random.html

    Role-Based Assignment in Healthcare: Explores role-based workflows in healthcare IT systems.
    https://www.healthit.gov

    UI Design for Database Management Systems: User experience design guidelines for database management interfaces.
    https://youtu.be/whF_Qm1epQ8?si=Vp6jZFSXobHCGW0N
    https://www.nngroup.com/articles/database-design-ui/


5. Additional Notes

    Error handling:
        Use detailed logging and exception handling for production-grade applications.
    Validation:
        Ensure data validity before inserting or updating, especially for dates and unique fields like email.
