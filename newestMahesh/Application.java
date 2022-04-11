import java.util.*;
import java.io.*;

public class Application {

    static int checkAvailability = 1; // Checks the availability of the username for SignUp method
    static int usernameCheckStudents = 0; // Check the availability for the Student username
    static int usernameCheckTeachers = 0; // Check the availability for the Teacher username
    static int signOutCheck = 0; // Checks if user decided to Sign Out or to Sign Up

    static ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    static ArrayList<Student> students = new ArrayList<Student>();
    static ArrayList<Course> courses = new ArrayList<Course>();

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
                System.out.println("Invalid option, try again\n");
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

                if (username.toUpperCase().equals(item.getUsername().toUpperCase())) {
                    usernameCheckTeachers = 1;
                    System.out.println("Enter your password");
                    String password = scanner.nextLine();
                    if (password.equals(item.getPassword())) {

                        System.out.println("Success!\n");

                        System.out.println("Menu\n");
                        System.out.println("[1]Change Password\n" +
                                "[2]Edit Username\n" + "[3]Delete Account\n" +
                                "[4]Sign Out\n" + "[5]Continue to your courses");
                        String menuAnswer = scanner.nextLine();
                        switch (menuAnswer) {
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
                            case "5":
                                loggedIn(true, username);
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
                                "[2]Edit Username\n" + "[3]Delete Account\n" +
                                "[4]Sign Out\n" + "[5]Continue to your courses");
                        String menuAnswer = scanner.nextLine();
                        switch (menuAnswer) {
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
                            case "5":
                                loggedIn(false, username);
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
                writeTeacher(firstName, lastName, username, password, true);
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
                writeStudent(firstName, lastName, username, password, true);
                signIn(scanner);
            }
            checkAvailability = 1;

        } else {
            System.out.println("Wrong answer, try again");
        }

    }

    public static void changePasswordTeacher(String username, Scanner scanner) throws Exception {

        BufferedWriter outputStream = null;
        BufferedReader inputStream = null;

        try {
            clearTheFile("teacher");
            for (Teacher item : teachers) {
                if (username.equals(item.getUsername())) {
                    System.out.println("Enter old password");
                    String oldPassword = scanner.nextLine();
                    if (oldPassword.equals(item.getPassword())) {
                        System.out.println("Enter new password");
                        String newPassword = scanner.nextLine();
                        item.setPassword(newPassword);
                        System.out.println("Password Changed!\n");
                    } else {
                        System.out.println("Wrong Password!");
                        break;
                    }
                }
                writeTeacher(item.getFirstName(), item.getLastName(), item.getUsername(), item.getPassword(), true);
            }
            signIn(scanner);

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }

    }

    public static void changePasswordStudent(String username, Scanner scanner) throws Exception {

        BufferedWriter outputStream = null;
        BufferedReader inputStream = null;

        try {
            clearTheFile("student");
            for (Student item : students) {
                if (username.equals(item.getUsername())) {
                    System.out.println("Enter old password");
                    String oldPassword = scanner.nextLine();
                    if (oldPassword.equals(item.getPassword())) {
                        System.out.println("Enter new password");
                        String newPassword = scanner.nextLine();
                        item.setPassword(newPassword);
                        System.out.println("Password Changed!\n");
                    } else {
                        System.out.println("Wrong Password!");
                        break;
                    }
                }
                writeStudent(item.getFirstName(), item.getLastName(), item.getUsername(), item.getPassword(), true);
            }
            signIn(scanner);

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }

    }

    public static void editUsernameTeacher(Scanner scanner) throws Exception {
        BufferedWriter outputStream = null;
        BufferedReader inputStream = null;

        try {
            System.out.println("Enter new username");
            String newUsername = scanner.nextLine();
            clearTheFile("teacher");
            for (Teacher item : teachers) {
                String oldUsername = item.getUsername();
                if (!oldUsername.equals(item.getUsername())) {
                    writeTeacher(item.getFirstName(), item.getLastName(), item.getUsername(), item.getPassword(), true);
                } else {
                    item.setUsername(newUsername);
                    writeTeacher(item.getFirstName(), item.getLastName(), item.getUsername(), item.getPassword(), true);
                }
            }
            System.out.println("Username Edited!\n");
            signIn(scanner);

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }

    }

    public static void editUsernameStudent(Scanner scanner) throws Exception {
        BufferedWriter outputStream = null;
        BufferedReader inputStream = null;

        try {
            System.out.println("Enter new username");
            String newUsername = scanner.nextLine();
            clearTheFile("student");
            for (Student item : students) {
                String oldUsername = item.getUsername();
                if (!oldUsername.equals(item.getUsername())) {
                    writeStudent(item.getFirstName(), item.getLastName(), item.getUsername(), item.getPassword(), true);
                } else {
                    item.setUsername(newUsername);
                    writeStudent(item.getFirstName(), item.getLastName(), item.getUsername(), item.getPassword(), true);
                }
            }
            System.out.println("Username Edited!\n");
            signIn(scanner);

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }


    }

    public static void deleteAccTeacher(String username, Scanner scanner) throws Exception {
        BufferedWriter outputStream = null;
        BufferedReader inputStream = null;
        String recordingTeacher = "";

        try {
            clearTheFile("teacher");
            for (Teacher item : teachers) {
                if (!username.equals(item.getUsername())) {
                    writeTeacher(item.getFirstName(), item.getLastName(), item.getUsername(), item.getPassword(), true);
                } else {
                    teachers.remove(item);
                }
            }

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
            clearTheFile("student");
            for (Student item : students) {
                if (!username.equals(item.getUsername())) {
                    writeStudent(item.getFirstName(), item.getLastName(), item.getUsername(), item.getPassword(), true);
                } else {
                    students.remove(item);
                }
            }

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

    public static void writeStudent(String firstName, String lastName, String username, String password, boolean append) throws IOException {
        PrintWriter outputStream = null;
        String recordingStudent = firstName + "," +
                lastName + "," + username + "," + password;

        try {

            outputStream = new PrintWriter(new FileWriter("Students.txt", append));
            outputStream.println(recordingStudent);
        } finally {
            outputStream.close();
        }
    }

    public static void writeTeacher(String firstName, String lastName, String username, String password, boolean append) throws IOException {
        PrintWriter outputStream = null;
        String recordingTeacher = firstName + "," +
                lastName + "," + username + "," + password;

        try {
            outputStream = new PrintWriter(new FileWriter("Teachers.txt", append));
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

    public static void addCourse(String courseAdd, ArrayList<String> currentCourses) throws FileNotFoundException {
        System.out.println("Added course!\n");

        FileOutputStream fos = new FileOutputStream("data.txt", true);
        PrintWriter pw = new PrintWriter(fos);

        String courseName = String.format("\n" + courseAdd);

        pw.write(courseName);
        pw.flush();
        pw.close();
    }

    public static void removeCourse(String courseRemove, ArrayList<String> currentCourses) throws FileNotFoundException {
        System.out.println("Removed course!\n");

        FileOutputStream fos = new FileOutputStream("data.txt", false);
        PrintWriter pw = new PrintWriter(fos);
        pw.write(currentCourses.get(0));
        for (int i = 1; i < currentCourses.size(); i++) {
            pw.write("\n");
            pw.write(currentCourses.get(i));
        }

        pw.flush();
        pw.close();
    }

    public static void loggedIn(boolean isTeacher, String studentID) throws IOException {
        BufferedReader br = null;
        ArrayList<String> list = new ArrayList<>();

        Scanner scan = new Scanner(System.in);

        try {
            br = new BufferedReader(new FileReader("data.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File Not found!!");
        }

        String line = br.readLine();
        while (line != null) {
            list.add(line);
            line = br.readLine();
        }

//        System.out.println(list);

        int outerLoop = 5;
        do {
            System.out.println("1. Show Courses");
            System.out.println("2. Add a Course");
            System.out.println("3. Remove a Course");
            System.out.println("4. Select a Course");
            System.out.println("5. Create a Course");
            System.out.println("Press 0 to Quit");

            try {
                outerLoop = scan.nextInt();
                scan.nextLine();
                if (outerLoop >= 0 && outerLoop <= 5) {
                    if (outerLoop == 1) {
                        System.out.println("\nCurrent courses:");
                        for (int i = 0; i < courses.size(); i++) {
                            System.out.println(courses.get(i).getName());
                        }
                        System.out.println();
                    } else if (outerLoop == 2) {

                        viewCourses();
                        System.out.println("Which course would you like to enroll to?");
                        int courseToAdd = Integer.parseInt(scan.nextLine()) - 1;

                        if (isTeacher == true) {
                            for (int i = 0; i < teachers.size(); i++) {
                                if (studentID.equals(teachers.get(i).getUsername())) {
                                    teachers.get(i).getEnrolledCourses().add(courseToAdd);
                                }
                            }
                        } else {
                            for (int i = 0; i < students.size(); i++) {
                                if (studentID.equals(students.get(i).getUsername())) {
                                    students.get(i).getEnrolledCourses().add(courseToAdd);
                                }
                            }
                        }


                    } else if (outerLoop == 3) {


                    } else if (outerLoop == 4) {

                        System.out.println("Select a course");
                        viewMyCourses(isTeacher, studentID);
                        int courseSelectTemp = Integer.parseInt(scan.nextLine()) - 1;
                        int courseSelect = 0;
                        if (isTeacher) {
                            courseSelect = teachers.get(studentArrayGetter(studentID)).getEnrolledCourses().get(courseSelectTemp);
                        } else {
                            courseSelect = students.get(studentArrayGetter(studentID)).getEnrolledCourses().get(courseSelectTemp);
                        }
                        //--------------------------------------------------------------------------------- --------------------------------------------------------------------------------------------------------
                        int exit = 5;
                        if (isTeacher == true) {
                            do {
                                System.out.println("1. Create Quiz");
                                System.out.println("2. Select Quiz to Edit Or Delete");
                                System.out.println("3. View Submissions");
                                System.out.println("4. Grade Submissions");
                                System.out.println("Press 0 to Quit");

                                exit = Integer.parseInt(scan.nextLine());
                                if (exit == 1) {
                                    courses.get(courseSelect).createQuiz(scan);
                                } else if (exit == 2) {
                                    courses.get(courseSelect).viewQuizzes();
                                    System.out.println("Select a quiz");
                                    int quizSelect = Integer.parseInt(scan.nextLine());
                                    System.out.println("Would you like to edit or delete the quiz");
                                    String editOrDelete = scan.nextLine();
                                    if (editOrDelete.equals("edit")) {
                                        courses.get(courseSelect).getQuizzes().get(quizSelect).editQuiz(scan);
                                    } else if (editOrDelete.equals("delete")) {
                                        courses.get(courseSelect).getQuizzes().remove(quizSelect);
                                    }
                                } else if (exit == 3) {
                                    System.out.println("Select a quiz");
                                    int quizSelect = Integer.parseInt(scan.nextLine());
                                    for (int i = 0; i < courses.get(courseSelect).getQuizzes().get(quizSelect).getSubmissions().size(); i++) {
                                        System.out.println(courses.get(courseSelect).getQuizzes().get(quizSelect).getSubmissions().get(i).getStudentID() + " submitted");
                                        for (int x = 0; x < courses.get(courseSelect).getQuizzes().get(quizSelect).getSubmissions().get(i).getSubmissions().size(); x++) {
                                            System.out.println(courses.get(courseSelect).getQuizzes().get(quizSelect).getSubmissions().get(i).getSubmissions().get(x));
                                        }
                                    }

                                } else if (exit == 4) {
                                    System.out.println("Select a quiz");
                                    int quizSelect = Integer.parseInt(scan.nextLine());
                                    courses.get(courseSelect).getQuizzes().get(quizSelect).viewQuiz();
                                    for (int i = 0; i < courses.get(courseSelect).getQuizzes().get(quizSelect).getSubmissions().size(); i++) {
                                        System.out.println(courses.get(courseSelect).getQuizzes().get(quizSelect).getSubmissions().get(i).getStudentID() + " submitted");
                                        for (int x = 0; x < courses.get(courseSelect).getQuizzes().get(quizSelect).getSubmissions().get(i).getSubmissions().size(); x++) {
                                            System.out.println(courses.get(courseSelect).getQuizzes().get(quizSelect).getSubmissions().get(i).getSubmissions().get(x));
                                        }
                                        System.out.println("What grade do you want to assign this user?");
                                        int grade = Integer.parseInt(scan.nextLine());
                                        courses.get(courseSelect).getQuizzes().get(quizSelect).getSubmissions().get(i).setScore(grade);
                                    }

                                }


                            } while (exit != 0);


                        } else {
                            // if they are a student do student stuff take quiz etc


                            do {
                                System.out.println("1. Select Quiz");
                                System.out.println("Press 0 to Quit");
                                exit = Integer.parseInt(scan.nextLine());
                                if (exit == 1) {
                                    courses.get(courseSelect).viewQuizzes();
                                    System.out.println("Select a quiz");
                                    int quizSelect = Integer.parseInt(scan.nextLine());
                                    System.out.println("Would you like to take or view submission for the quiz");
                                    String editOrDelete = scan.nextLine();
                                    if (editOrDelete.equals("take")) {
                                        courses.get(courseSelect).TakeQuiz(scan, studentID);
                                        // submission stuff-----------------------------------------------------------------------------------------------------------------------------

                                    } else if (editOrDelete.equals("view submission")) {
                                        // submission stuff-------------------------------------------------------------------------------------------------------
                                        // can do a for loop looking for names / then get submission

                                        for (int i = 0; i < courses.get(courseSelect).getQuizzes().get(quizSelect).getSubmissions().size(); i++) {
                                            if (studentID.equals(courses.get(courseSelect).getQuizzes().get(quizSelect).getSubmissions().get(i).getStudentID())) {
                                                for (int y = 0; y < courses.get(courseSelect).getQuizzes().get(quizSelect).getSubmissions().size(); y++) {
                                                    System.out.println("You put: " + courses.get(courseSelect).getQuizzes().get(quizSelect).getSubmissions().get(i).getSubmissions().get(y));

                                                }
                                                System.out.println("Your score is: " + courses.get(courseSelect).getQuizzes().get(quizSelect).getSubmissions().get(i).getScore());
                                            }
                                        }
                                    }
                                }

                            } while (exit != 0);
                        }
                    } else if (outerLoop == 5) {
                        System.out.println("What would you like the course to be named?");
                        String courseName = scan.nextLine();
                        courses.add(new Course(courseName));
                        System.out.println("Course created!");

                    }
                } else {
                    System.out.println("Please select a valid option (value out of range!)");
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid option!");
            }

        } while (outerLoop != 0);
    }

    public static void viewCourses() {
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ". " + courses.get(i).getName());
        }
    }

    public static void viewMyCourses(boolean isTeacher, String studentID) {


        if (isTeacher) {
            for (int i = 0; i < teachers.size(); i++) {
                if (studentID.equals(teachers.get(i).getUsername())) {
                    for (int x = 0; x < teachers.get(i).getEnrolledCourses().size(); x++) {
                        System.out.println((x + 1) + ". " + courses.get(teachers.get(i).getEnrolledCourses().get(x)).getName());
                    }
                }
            }
        } else {
            for (int i = 0; i < students.size(); i++) {
                if (studentID.equals(students.get(i).getUsername())) {
                    for (int x = 0; x < students.get(i).getEnrolledCourses().size(); x++) {
                        System.out.println((x + 1) + ". " + courses.get(students.get(i).getEnrolledCourses().get(x)).getName());
                    }

                }
            }
        }
    }


    public static int teacherArrayGetter(String studentID) {
        int toReturn = 0;
        for (int i = 0; i < teachers.size(); i++) {
            if (studentID.equals(teachers.get(i).getUsername())) {
                toReturn = i;
            }
        }
        return toReturn;
    }

    public static int studentArrayGetter(String studentID) {
        int toReturn = 0;
        for (int i = 0; i < students.size(); i++) {
            if (studentID.equals(students.get(i).getUsername())) {
                toReturn = i;
            }
        }
        return toReturn;
    }

    public static void clearTheFile(String file) throws IOException {
        if (file.equalsIgnoreCase("Teacher")) {
            FileWriter fw = new FileWriter("Teachers.txt", false);
            PrintWriter pw = new PrintWriter(fw, false);
            pw.write("");
            pw.flush();
            pw.close();
            fw.close();
        } else if (file.equalsIgnoreCase("Student")) {
            FileWriter fw = new FileWriter("Students.txt", false);
            PrintWriter pw = new PrintWriter(fw, false);

            pw.write("");
            pw.flush();
            pw.close();
            fw.close();
        }
    }
}