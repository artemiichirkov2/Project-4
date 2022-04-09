import java.util.*;

public class Application {

    
    static int checkAvailability = 1;
    static int usernameCheckStudents = 0; 
    static int usernameCheckTeachers = 0;
    static int signOutCheck = 0;

    static ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    static ArrayList<Student> students = new ArrayList<Student>();

    public static void main(String[] args) throws Exception {
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
                System.out.println("Eror");
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
            System.out.println("Eror");
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

}
