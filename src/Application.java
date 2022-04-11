import java.util.*;
import java.io.*;

public class Application {

    
    static int checkAvailability = 1; // Checks the availability of the username for SignUp method
    static int usernameCheckStudents = 0; // Check the availability for the Student username
    static int usernameCheckTeachers = 0; // Check the availability for the Teacher username
    static int signOutCheck = 0; // Checks if user decided to Sign Out or to Sign Up

    static ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    static ArrayList<Student> students = new ArrayList<Student>();

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        readStudents();
        readTeachers();

        System.out.println("Welcome to the Quiz app!\n");
        do {
            System.out.println("Do you want to Sign In or Sign Up?\n[1]Sign In\n[2]Sign Up");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                signIn(scanner);
            } else if (choice.equals("2")) {
                signUp(scanner);
            } else {
                System.out.println("Wrong answer, try again\n");
            }
        } while (signOutCheck == 0);


    }
    
    public static void signIn(Scanner scanner) throws Exception { 

        System.out.println("Sign In\n");
        System.out.println("Are you a Teacher or a Student?\n[1]Teacher\n[2]Student");
        String choice2 = scanner.nextLine();

        if (choice2.equals("1")) {
            System.out.println("Enter your username");
            String username = scanner.nextLine();

            for (Teacher item : teachers) {

                if (username.equals(item.getUsername())) {
                    usernameCheckTeachers = 1;
                    System.out.println("Enter your password");
                    String password = scanner.nextLine();
                    if (password.equals(item.getPassword())) {

                        System.out.println("Success!\n");

                        System.out.println("Menu\n");
                        System.out.println("[1]Change Password\n" + 
                            "[2]Edit Username\n" + "[3]Delete Account\n" + "[4]Sign Out");
                        String menuAnswer = scanner.nextLine();
                        switch(menuAnswer) {
                            case "1":
                                changePasswordTeacher(username, scanner);
                                break;
                            case "2":
                                editUsernameTeacher(scanner);
                                break;
                            case "3":
                                deleteAccTeacher(username, scanner);
                                break;
                            case "4":
                                signOut(scanner);
                                break;
                            default:
                                System.out.println("Incorrect answer, try again"); //Should call the method!
                        }
                        //menuTeacher(username, scanner);

                    } else {
                        System.out.println("Password doesn't match\n");
                    }
                }
            }
            if (usernameCheckTeachers != 1) {
                System.out.println("There is no such username in the system\n");
            }
            usernameCheckTeachers = 0;
        } else if (choice2.equals("2")) {
            System.out.println("Enter your username");

            String username = scanner.nextLine();

            for (Student item : students) {

                if (username.equals(item.getUsername())) {
                    usernameCheckStudents = 1;
                    System.out.println("Enter your password");
                    String password = scanner.nextLine();
                    if (password.equals(item.getPassword())) {

                        System.out.println("Success!\n");

                        System.out.println("Menu\n");
                        System.out.println("[1]Change Password\n" + 
                            "[2]Edit Username\n" + "[3]Delete Account\n" + "[4]Sign Out");
                        String menuAnswer = scanner.nextLine();
                        switch(menuAnswer) {
                            case "1":
                                changePasswordStudent(username, scanner);
                                break;
                            case "2":
                                editUsernameStudent(scanner);
                                break;
                            case "3":
                                deleteAccStudent(username, scanner);
                                break;
                            case "4":
                                signOut(scanner);
                                break;
                            default:
                                System.out.println("Incorrect answer, try again"); //Should call the method!
                        }
                        //menuStudent(username, scanner);

                    } else {
                        System.out.println("Password doesn't match");
                    }
                }
            }
            if (usernameCheckStudents != 1) {
                System.out.println("There is no such username in the system\n");
            }
            usernameCheckStudents = 0;
        } else {
            System.out.println("Wrong answer, try again\n");
        }
    }
    
    public static void signUp(Scanner scanner) throws Exception { 

        System.out.println("Sign Up\n");
        System.out.println("Are you a Teacher or a Student?\n[1]Teacher\n[2]Student");
        String choice2 = scanner.nextLine();


        if (choice2.equals("1")) {
            System.out.println("Enter your first name");
            String firstName = scanner.nextLine();
            System.out.println("Enter your last name");
            String lastName = scanner.nextLine();
            System.out.println("Enter the username");
            String username = scanner.nextLine();

            for (Teacher item : teachers) {

                if (username.equals(item.getUsername())) {
                    System.out.println("This username is already taken\n");
                    checkAvailability = 0;
                    break;
                }

            }

            if (checkAvailability == 1) {

                System.out.println("Enter the password");
                String password = scanner.nextLine();
                teachers.add(new Teacher(firstName, lastName, username, password));
                writeTeacher(firstName, lastName, username, password);
                signIn(scanner);
            
            }

            checkAvailability = 1;

        } else if (choice2.equals("2")) {
            System.out.println("Enter your first name");
            String firstName = scanner.nextLine();
            System.out.println("Enter your last name");
            String lastName = scanner.nextLine();
            System.out.println("Enter the username");
            String username = scanner.nextLine();

            for (Student item : students) {

                if (username.equals(item.getUsername())) {
                    System.out.println("This username is already taken\n");
                    checkAvailability = 0;
                    break;
                }
            }

            if (checkAvailability == 1) {

                System.out.println("Enter the password");
                String password = scanner.nextLine();
                students.add(new Student(firstName, lastName, username, password));
                writeStudent(firstName, lastName, username, password);
                signIn(scanner);
                
            }

            checkAvailability = 1;

        } else {
            System.out.println("Wronmg answer, try again");
        }

    }

    public static void changePasswordTeacher(String username, Scanner scanner) throws Exception {
        for (Teacher item : teachers) {
            if (username.equals(item.getUsername())) {
                System.out.println("Enter your old password");
                String oldPassword = scanner.nextLine();
                if (oldPassword.equals(item.getPassword())) {
                    System.out.println("Enter new password");
                    String newPassword = scanner.nextLine();
                    item.setPassword(newPassword);
                    signIn(scanner);
                } else {
                    System.out.println("Wrong password\n");
                    signIn(scanner);
                }
                
            }
        }
    }

    public static void changePasswordStudent(String username, Scanner scanner) throws Exception {
        for (Student item : students) {
            if (username.equals(item.getUsername())) {
                System.out.println("Enter your old password");
                String oldPassword = scanner.nextLine();
                if (oldPassword.equals(item.getPassword())) {
                    System.out.println("Enter new password");
                    String newPassword = scanner.nextLine();
                    item.setPassword(newPassword);
                    signIn(scanner);
                } else {
                    System.out.println("Wrong password\n");
                    signIn(scanner);
                }
                
            }
        }
    }

    public static void editUsernameTeacher(Scanner scanner) throws Exception {
        for (Teacher item : teachers) {
            System.out.println("Enter new username");
            String newUsername = scanner.nextLine();
            item.setUsername(newUsername);
            signIn(scanner); 
        }
    }

    public static void editUsernameStudent(Scanner scanner) throws Exception {
        for (Student item : students) {
            System.out.println("Enter new username");
            String newUsername = scanner.nextLine();
            item.setUsername(newUsername);
            signIn(scanner); 
        }
    }

    public static void deleteAccTeacher(String username, Scanner scanner) throws Exception {
        BufferedWriter outputStream = null;
        BufferedReader inputStream = null;
        String recordingTeacher = "";

        try {
            for (Teacher item : teachers) {
                if (username.equals(item.getUsername())) {
                    recordingTeacher = item.getFirstName() + "," +
                        item.getLastName() + "," + item.getUsername() +
                        "," + item.getPassword();
                    teachers.remove(item);
                    break;
                }
            }
            File tempFile = new File("tempFile.txt");
            File teachersFile = new File("Teachers.txt");
            outputStream = new BufferedWriter(new FileWriter(tempFile));
            inputStream = new BufferedReader(new FileReader(teachersFile));

            
            String line;
            while ((line = inputStream.readLine()) != null) {
                String trimLine = line.trim();

                if (trimLine.equals(recordingTeacher)) {
                    continue;
                }

                outputStream.write(line + System.getProperty("line.separator"));
            }

            boolean renaming = tempFile.renameTo(teachersFile); 
    
            System.out.println("Your account has been deleted\n");
            signUp(scanner);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
            
    }

    public static void deleteAccStudent(String username, Scanner scanner) throws Exception {
        BufferedWriter outputStream = null;
        BufferedReader inputStream = null;
        String recordingStudent = "";

        try {
            for (Student item : students) {
                if (username.equals(item.getUsername())) {
                    recordingStudent = item.getFirstName() + "," +
                        item.getLastName() + "," + item.getUsername() +
                        "," + item.getPassword();
                    students.remove(item);
                    break;
                }
            }
            File tempFile = new File("tempFile.txt");
            File studentsFile = new File("Students.txt");
            outputStream = new BufferedWriter(new FileWriter(tempFile));
            inputStream = new BufferedReader(new FileReader(studentsFile));

            
            String line;
            while ((line = inputStream.readLine()) != null) {
                String trimLine = line.trim();

                if (trimLine.equals(recordingStudent)) {
                    continue;
                }

                outputStream.write(line + System.getProperty("line.separator"));
            }

            boolean renaming = tempFile.renameTo(studentsFile); 
    
            System.out.println("Your account has been deleted\n");
            signUp(scanner);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    public static void writeStudent(String firstName, String lastName, String username, String password) throws IOException {
        PrintWriter outputStream = null;
        String recordingStudent = firstName + "," +
            lastName + "," + username + "," + password;

        try {
            
            outputStream = new PrintWriter(new FileWriter("Students.txt", true));
            outputStream.println(recordingStudent);
        } finally {
            outputStream.close();
        } 
    }

    public static void writeTeacher(String firstName, String lastName, String username, String password) throws IOException {
        PrintWriter outputStream = null;
        String recordingTeacher = firstName + "," +
            lastName + "," + username + "," + password;

        try {
            outputStream = new PrintWriter(new FileWriter("Teachers.txt", true));
            outputStream.println(recordingTeacher);
        } finally {
            outputStream.close();
        } 
    }

    public static void readStudents() throws IOException {
        BufferedReader inputStream = null;

        try {
            inputStream = new BufferedReader(new FileReader("Students.txt"));
            
            String line;
            if (inputStream != null) {
                while ((line = inputStream.readLine()) != null) {
                    String[] credentialsStudent = line.split(",");
                    students.add(new Student(credentialsStudent[0],
                        credentialsStudent[1], credentialsStudent[2], credentialsStudent[3]));
                }
            }
        } finally {
            inputStream.close(); 
        }
    }

    public static void readTeachers() throws IOException {
        BufferedReader inputStream = null;

        try {
            inputStream = new BufferedReader(new FileReader("Teachers.txt"));
            
            String line;
            if (inputStream != null) {
                while ((line = inputStream.readLine()) != null) {
                    String[] credentialsTeacher = line.split(",");
                    teachers.add(new Teacher(credentialsTeacher[0],
                        credentialsTeacher[1], credentialsTeacher[2], credentialsTeacher[3]));
                }
            }
        } finally {
            inputStream.close();
        }
    }

    public static void signOut(Scanner scanner) throws IOException {
        System.out.println("Signing out...");
        signOutCheck = 1;
    }

}
