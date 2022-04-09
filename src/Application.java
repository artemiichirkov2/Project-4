import java.util.*;

public class Application {

    static ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    static ArrayList<Student> students = new ArrayList<Student>();
    static int usernameStatus = 1;
    static int check1 = 0; 
    static int check2 = 0;
    static int check3 = 0;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Quiz app!");
        do {
            System.out.println("Do you want to Sign In or Sign Up?");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                signIn(scanner);
            } else if (choice.equals("2")) {
                register(scanner);
            } else {
                System.out.println("Eror");
            }
        } while (check3 == 0);


    }
    
    public ArrayList<Teacher> getTeachers() {

        return teachers;
    }
    
    public ArrayList<Student> getStudents() {

        return students;
    }
    
    public static void signIn(Scanner scanner) throws Exception { 

        System.out.println("Sign In");
        System.out.println("Are you a teacher or a student?");
        String choice2 = scanner.nextLine();

        if (choice2.equals("1")) {
            System.out.println("Enter your username");
            String username = scanner.nextLine();

            for (Teacher item : teachers) {

                if (username.equals(item.getUsername())) {
                    check2 = 1;
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
            if (check2 == 0) {
                System.out.println("There is no such username in the system");
            }
        } else if (choice2.equals("2")) {
            System.out.println("Enter your username");

            String username = scanner.nextLine();

            for (Student item : students) {

                if (username.equals(item.getUsername())) {
                    check1 = 1;
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
            if (check1 == 0) {
                System.out.println("There is no such username in the system");
            }
        } else {
            System.out.println("Eror");
        }
    }
    
    public static void register(Scanner scanner) throws Exception { 

        System.out.println("Sign Up");
        System.out.println("Are you a teacher or a student?");
        String choice2 = scanner.nextLine();


        if (choice2.equals("1")) {
            System.out.println("Enter your first name");
            scanner.nextLine();
            String firstName = scanner.nextLine();
            System.out.println("Enter your last name");
            String lastName = scanner.nextLine();
            System.out.println("Enter the username");
            String username = scanner.nextLine();

            for (Teacher item : teachers) {

                if (username.equals(item.getUsername())) {
                    System.out.println("This username is already taken");
                    usernameStatus = 0;
                    break;
                }
            }

            if (usernameStatus == 1) {
                System.out.println();
                String password = scanner.nextLine();
                teachers.add(new Teacher(firstName, lastName, username, password));
                signIn(scanner);
            }
        } else if (choice2.equals("2")) {
            System.out.println("Enter your first name");
            scanner.nextLine();
            String firstName = scanner.nextLine();
            System.out.println("Enter your last name");
            String lastName = scanner.nextLine();
            System.out.println("Enter the username");
            String username = scanner.nextLine();

            for (Student item : students) {

                if (username.equals(item.getUsername())) {
                    System.out.println("This username is already taken");
                    usernameStatus = 0;
                    break;
                }
            }

            if (usernameStatus == 1) {
                System.out.println("Enter the password");
                String password = scanner.nextLine();
                students.add(new Student(firstName, lastName, username, password));
                signIn(scanner);
            }
        } else {
            System.out.println("Eror");
        }

    }
}