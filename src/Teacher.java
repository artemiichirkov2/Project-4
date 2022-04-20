import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Teacher {
    public static ArrayList<Teacher> LocalTeachers;

    public static void Initialise() throws IOException {
        LocalTeachers = new ArrayList<>();

        BufferedReader inputStream = null;

        try {
            inputStream = new BufferedReader(new FileReader("Teachers.txt"));
        } catch (FileNotFoundException e) {
            File teachersFile = new File("Teachers.txt");
            teachersFile.createNewFile();
            inputStream = new BufferedReader(new FileReader("Teachers.txt"));
        }

        String line;
        while ((line = inputStream.readLine()) != null) {
            String[] credentialsStudent = line.split(",");
            LocalTeachers.add(new Teacher(credentialsStudent[0], credentialsStudent[1], credentialsStudent[2], credentialsStudent[3]));
        }

        inputStream.close();
    }

    public static void Flush() throws IOException {
        FileWriter writer = new FileWriter("Teachers.txt");

        for(Teacher student : LocalTeachers)
        {
            writer.write(student.firstName);
            writer.write(",");
            writer.write(student.lastName);
            writer.write(",");
            writer.write(student.username);
            writer.write(",");
            writer.write(student.password);
            writer.write("\n");
        }

        writer.close();
    }

    public String firstName;
    public String lastName;
    public String username;
    public String password;
    public ArrayList<Integer> enrolledCourses;

    public Teacher(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.enrolledCourses = new ArrayList<Integer>();
    }
}


