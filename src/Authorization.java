import javax.swing.*;
import java.io.IOException;
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

    public static Authorization SignIn(Scanner scanner) {
        Authorization auth = new Authorization();
        AuthType type = AuthType.Student;

        while (!auth.authorized) {
            boolean isFine = false;
            while (!isFine) {
                System.out.println("1: Sign In as Teacher\n2: Sign In as Student");
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
            System.out.println("Enter Username: ");
            String un = scanner.nextLine();
            System.out.println("Enter Password: ");
            String pass = scanner.nextLine();

            auth = Authorization.TrySignIn(type, un, pass);

            if (!auth.authorized) System.out.println("Incorrect login type, username, or password.");
        }

        System.out.println("Logged in as " + auth.username);
        return auth;
    }

    public static Authorization SignUp(Scanner scanner) throws IOException {
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


    public static Authorization GUISignUp(String fn, String ln, String un, String pass, String isTeacher) throws IOException {
        Authorization auth = new Authorization();
        AuthType type = AuthType.Student;
        boolean isFine = false;
        if(isTeacher.equals("1") || isTeacher.equals("2")) {


                if (isTeacher.equals("1")) {
                    type = AuthType.Teacher;
                    isFine = true;
                } else if (isTeacher.equals("2")) {
                    type = AuthType.Student;
                    isFine = true;
                }


                isFine = true;

                if (type == AuthType.Student) {
                    for (Student s : Student.LocalStudents) {
                        if (s.username.equals(un)) {
                            isFine = false;
                            break;
                        }
                    }

                    if (!isFine) {
                        JOptionPane.showMessageDialog(null, "Incorrect login type, username, or password.", "Darkspace",
                                JOptionPane.ERROR_MESSAGE);
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
                        JOptionPane.showMessageDialog(null, "Incorrect login type, username, or password.", "Darkspace",
                                JOptionPane.ERROR_MESSAGE);
                    }

                    Teacher.LocalTeachers.add(new Teacher(fn, ln, un, pass));
                }
                if(isFine) {
                    auth = Authorization.TrySignIn(type, un, pass);
                }



            if(isFine) {
                Student.Flush();
                Teacher.Flush();
                JOptionPane.showMessageDialog(null, "Logged in as " + auth.username, "Darkspace",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please enter a valid value for teacher selector", "Darkspace",
                    JOptionPane.ERROR_MESSAGE);
        }
        if(isFine) {
            return auth;
        }
        else {
            return null;
        }
    }




    public static Authorization GUISignIn(String un, String pass, String isTeacher) {
        Authorization auth = new Authorization();
        AuthType type = AuthType.Student;
        if(isTeacher.equals("1") || isTeacher.equals("2")) {


                if (isTeacher.equals("1")) {
                    type = AuthType.Teacher;
                } else if (isTeacher.equals("2")) {
                    type = AuthType.Student;
                }


                auth = Authorization.TrySignIn(type, un, pass);

                if (!auth.authorized) {
                    JOptionPane.showMessageDialog(null, "Incorrect login type, username, or password.", "Darkspace",
                            JOptionPane.ERROR_MESSAGE);
                }
                else {


                    JOptionPane.showMessageDialog(null, "Logged in as " + auth.username, "Darkspace",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            return auth;
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please enter a valid value for teacher selector", "Darkspace",
                    JOptionPane.ERROR_MESSAGE);
        }
        return  auth;
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
}

