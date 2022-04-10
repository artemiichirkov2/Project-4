import java.io.*;
import java.util.*;

public class Application {

    
    static int checkAvailability = 1;
    static int usernameCheckStudents = 0; 
    static int usernameCheckTeachers = 0;
    static int signOutCheck = 0;



    static ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    static ArrayList<Student> students = new ArrayList<Student>();
    static ArrayList<Course> courses = new ArrayList<Course>();

    public static void main(String[] args) throws Exception {
        courses.add(new Course("CHEM"));
        courses.add(new Course("EAPS"));
        courses.add(new Course("CS"));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Quiz app!");
        do {
            System.out.println("Do you want to Sign In or Sign Up? [1] for Sign In, [2] for Sign Up");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                signIn(scanner);
            } else if (choice.equals("2")) {
                signUp(scanner);
            } else {
                System.out.println("Error");
            }
        } while (signOutCheck == 0);


    }
    
    public ArrayList<Teacher> getTeachers() {

        return teachers;
    }
    
    public ArrayList<Student> getStudents() {

        return students;
    }
    
    public static void signIn(Scanner scanner) throws Exception { 

        System.out.println("Sign In");
        System.out.println("Are you a teacher or a student? [1] for teacher, [2] for student");
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
                        //menuTeacher(username, scanner);
                        loggedIn(true, username);

                    } else {
                        System.out.println("Password doesn't match");
                    }
                }
            }
            if (usernameCheckTeachers == 0) {
                System.out.println("There is no such username in the system");
            }
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
                        //menuStudent(username, scanner);
                        loggedIn(false, username);

                    } else {
                        System.out.println("Password doesn't match");
                    }
                }
            }
            if (usernameCheckStudents == 0) {
                System.out.println("There is no such username in the system");
            }
        } else {
            System.out.println("Eror");
        }
    }
    
    public static void signUp(Scanner scanner) throws Exception { 

        System.out.println("Sign Up");
        System.out.println("Are you a teacher or a student? [1] for teacher, [2] for student");
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
                    System.out.println("This username is already taken");
                    checkAvailability = 0;
                    break;
                }

            }

            if (checkAvailability == 1) {

                checkAvailability = 0;
                System.out.println("Enter the password");
                String password = scanner.nextLine();
                teachers.add(new Teacher(firstName, lastName, username, password));
                signIn(scanner);
            
            }

        } else if (choice2.equals("2")) {
            System.out.println("Enter your first name");
            String firstName = scanner.nextLine();
            System.out.println("Enter your last name");
            String lastName = scanner.nextLine();
            System.out.println("Enter the username");
            String username = scanner.nextLine();

            for (Student item : students) {

                if (username.equals(item.getUsername())) {
                    System.out.println("This username is already taken");
                    checkAvailability = 0;
                    break;
                }
            }

            if (checkAvailability == 1) {

                checkAvailability = 0;
                System.out.println("Enter the password");
                String password = scanner.nextLine();
                students.add(new Student(firstName, lastName, username, password));
                signIn(scanner);
                
                }

        } else {
            System.out.println("Error");
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
                    System.out.println("Wrong password");
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
                    System.out.println("Wrong password");
                    signIn(scanner);
                }
                
            }
        }
    }

    public static void editUsernameTeacher(Scanner scanner) throws Exception {
        for (Teacher item : teachers) {
            System.out.println("Enter new usertname");
            String newUsername = scanner.nextLine();
            item.setUsername(newUsername);
            signIn(scanner); 
        }
    }

    public static void editUsernameStudent(Scanner scanner) throws Exception {
        for (Student item : students) {
            System.out.println("Enter new usertname");
            String newUsername = scanner.nextLine();
            item.setUsername(newUsername);
            signIn(scanner); 
        }
    }

    public static void deleteAccTeacher(String username, Scanner scanner) throws Exception {
        for (Teacher item : teachers) {
            if (username.equals(item.getUsername())) {
                teachers.remove(item);
                System.out.println("Your account has been deleted\n");
                System.out.println("Do you want to Sign Up or Sign Out? [1] for Sign Up, [2] for Sign Out");
                String signUpOrSignOut = scanner.nextLine();

                if(signUpOrSignOut.equals("1")) {
                    signUp(scanner);
                } else {
                    signOut(scanner);
                }
            }
        }
    }

    public static void deleteAccStudent(String username, Scanner scanner) throws Exception {
        for (Student item : students) {
            if (username.equals(item.getUsername())) {
                students.remove(item);
                System.out.println("Your account has been deleted\n");
                System.out.println("Do you want to Sign Up or Sign Out? [1] for Sign Up, [2] for Sign Out");
                String signUpOrSignOut = scanner.nextLine();

                if(signUpOrSignOut.equals("1")) {
                    signUp(scanner);
                } else {
                    signOut(scanner);
                }
            }
        }
    }

    public static void signOut(Scanner scanner) {
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
            System.out.println("Press 0 to Quit");

            try {
                outerLoop = scan.nextInt();
                scan.nextLine();
                if (outerLoop >= 0 && outerLoop <= 4) {
                    if (outerLoop == 1) {
                        System.out.println("\nCurrent courses:");
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println(list.get(i));
                        }
                        System.out.println();
                    } else if (outerLoop == 2) {
                        System.out.println("Enter course name to add (without spaces)");
                        String courseAdd;
                        do {
                            courseAdd = scan.nextLine();
                            courseAdd = courseAdd.toUpperCase();
                            if (courseAdd.contains(" ")) {
                                System.out.println("Course name should not contain spaces!");
                            } else {
                                if (!list.contains(courseAdd)) {
                                    list.add(courseAdd);
                                    addCourse(courseAdd, list);
                                } else {
                                    System.out.println("Course already exists!");
                                }
                            }
                        } while (courseAdd.contains(" "));
                    } else if (outerLoop == 3) {
                        System.out.println("Enter course to be removed!");
                        String courseRemove;
                        do {
                            courseRemove = scan.nextLine();
                            courseRemove = courseRemove.toUpperCase();
                            if (list.contains(courseRemove)){
                                try {
                                    list.remove(courseRemove);
                                    removeCourse(courseRemove, list);
                                } catch (Exception e) {
                                    System.out.println("There are no courses!");
                                }
                            } else if (courseRemove == "0") {
                                break;
                            } else {
                                System.out.println("Please enter a valid course name or enter 0 to return to the previous menu");
                            }
                        } while (courseRemove.contains(" "));

                    }
                    else if (outerLoop == 4)
                    {
                        viewCourses();
                        System.out.println("Select a course");
                        int courseSelect = Integer.parseInt(scan.nextLine());
                        //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                        int exit = 5;
                        if(isTeacher == true)
                        {
                        do{
                            System.out.println("1. Create Quiz");
                            System.out.println("2. Select Quiz to Edit Or Delete");
                            System.out.println("3. View Submissions");
                            System.out.println("4. Grade Submissions");
                            System.out.println("Press 0 to Quit");

                            exit = Integer.parseInt(scan.nextLine());
                            if(exit == 1)
                            {
                                courses.get(courseSelect).createQuiz(scan);
                            }
                            else if(exit == 2)
                            {
                                courses.get(courseSelect).viewQuizzes();
                                System.out.println("Select a quiz");
                                int quizSelect = Integer.parseInt(scan.nextLine());
                                System.out.println("Would you like to edit or delete the quiz");
                                String editOrDelete = scan.nextLine();
                                if(editOrDelete.equals("edit"))
                                {
                                    courses.get(courseSelect).getQuizzes().get(quizSelect).editQuiz(scan);
                                }
                                else if(editOrDelete.equals("delete"))
                                {
                                    courses.get(courseSelect).getQuizzes().remove(quizSelect);
                                }
                            }
                            else if(exit == 3)
                            {
                                System.out.println("WIP");
                                //--------------------------------------------------------------------------------------------------
                            }


                        }while(exit != 0);



                        }
                        else
                        {
                        // if they are a student do student stuff take quiz etc


                            do {
                                System.out.println("1. Select Quiz");
                                System.out.println("Press 0 to Quit");
                                exit = Integer.parseInt(scan.nextLine());
                                if (exit == 1)
                                {
                                    courses.get(courseSelect).viewQuizzes();
                                    System.out.println("Select a quiz");
                                    int quizSelect = Integer.parseInt(scan.nextLine());
                                    System.out.println("Would you like to take or view submission for the quiz");
                                    String editOrDelete = scan.nextLine();
                                    if(editOrDelete.equals("take"))
                                    {
                                        courses.get(courseSelect).TakeQuiz(scan, studentID);
                                        // submission stuff--------------------------------------------------------------------------------

                                    }
                                    else if(editOrDelete.equals("view submission"))
                                    {
                                        // submission stuff--------------------------------------------------------------------------------
                                        // can do a for loop looking for names / then get submission
                                    }
                                }

                            }while(exit != 0);
                        }
                    }
                } else {
                    System.out.println("Please select a valid option (value out of range!)");
                }

            } catch (InputMismatchException e){
                System.out.println("Please enter a valid option!");
            }

        } while (outerLoop != 0);
    }
    public static void viewCourses()
    {
        for(int i = 0; i < courses.size(); i++)
        {
            System.out.println((i + 1) + ". " + courses.get(i).getName());
        }
    }
}

