import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Authorization {
    public static Authorization TrySignIn(AuthType type, String username, String password)
    {
        Authorization result = new Authorization();

        result.username = username;
        result.type = type;

        if(type == AuthType.Student)
        {
            for(Student s : Student.LocalStudents)
            {
                if(s.username.equals(username) && s.password.equals(password)) result.authorized = true;
            }
        }
        else
        {
            for(Teacher t : Teacher.LocalTeachers)
            {
                if(t.username.equals(username) && t.password.equals(password)) result.authorized = true;
            }
        }

        return result;
    }
    public static Authorization SignIn(Scanner scanner, BufferedReader reader, PrintWriter writer) throws IOException {
        Authorization auth = new Authorization();
        AuthType type = AuthType.Student;

        while (!auth.authorized) {
            boolean isFine = false;
            while (!isFine) {
                clientFlush("signInMenu", writer);
//                System.out.println("1: Sign In as Teacher\n2: Sign In as Student");
                String option = reader.readLine();

                if (option.equals("1")) {
                    type = AuthType.Teacher;
                    isFine = true;
                } else if (option.equals("2")) {
                    type = AuthType.Student;
                    isFine = true;
                } else {
                    clientFlush("error", writer);
                }
            }
            clientFlush("username", writer);
            String un = reader.readLine();
            System.out.println(un);

            clientFlush("password", writer);
            String pass = reader.readLine();
            System.out.println(pass);

            auth = Authorization.TrySignIn(type, un, pass);

            if (!auth.authorized) clientFlush("incorrectLogin", writer);
        }

        System.out.println("Logged in as " + auth.username);
        return auth;
    }

    public static Authorization SignUp(Scanner scanner, BufferedReader reader, PrintWriter writer) throws IOException {
        Authorization auth = new Authorization();
        AuthType type = AuthType.Student;

        while (!auth.authorized) {
            System.out.println("1: Sign Up as Teacher\n2: Sign Up as Student");

            boolean isFine = false;
            while (!isFine) {
                String option = scanner.nextLine();

                if (option.equals("1")) {
                    type = AuthType.Teacher;
                    isFine = true;
                } else if (option.equals("2")) {
                    type = AuthType.Student;
                    isFine = true;
                } else {
                    System.out.println("Please enter a valid option.");
                }
            }
            System.out.println("Enter First Name: ");
            String fn = scanner.nextLine();
            System.out.println("Enter Last Name: ");
            String ln = scanner.nextLine();
            System.out.println("Enter Username: ");
            String un = scanner.nextLine();
            System.out.println("Enter Password: ");
            String pass = scanner.nextLine();

            isFine = true;

            if (type == AuthType.Student) {
                for (Student s : Student.LocalStudents) {
                    if (s.username.equals(un)) {
                        isFine = false;
                        break;
                    }
                }

                if (!isFine) {
                    System.out.println("Duplicate Username");
                    continue;
                }

                Student.LocalStudents.add(new Student(fn, ln, un, pass));
            } else {
                for (Teacher t : Teacher.LocalTeachers) {
                    if (t.username.equals(un)) {
                        isFine = false;
                        break;
                    }
                }

                if (!isFine) {
                    System.out.println("Duplicate Username");
                    continue;
                }

                Teacher.LocalTeachers.add(new Teacher(fn, ln, un, pass));
            }

            auth = Authorization.TrySignIn(type, un, pass);

            if (!auth.authorized) System.out.println("Incorrect login type, username, or password.");
        }

        Student.Flush();
        Teacher.Flush();
        System.out.println("Logged in as " + auth.username);
        return auth;
    }

    public String username;
    public AuthType type;
    public boolean authorized;

    public Authorization()
    {
        username = "";
        type = AuthType.Teacher;
        authorized = false;
    }

    public static void clientFlush(String message, PrintWriter writer) {
        writer.write(message);
        writer.println();
        writer.flush();
    }
}

