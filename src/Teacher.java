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
            String[] credentialsTeacher = line.split(",");
            LocalTeachers.add(new Teacher(credentialsTeacher[0], credentialsTeacher[1], credentialsTeacher[2], credentialsTeacher[3]));
        }

        inputStream.close();
    }


    public static void InitialiseFromServer(BufferedReader reader, PrintWriter writer) throws IOException, InterruptedException {
        LocalTeachers = new ArrayList<>();

        writer.write("readTeacher"); // what to send to server
        writer.println();
        writer.flush(); // ensure data is sent to the server

        //     Thread.sleep(5000);
        int length = Integer.parseInt(reader.readLine());


        String line = "";
        for(int i = 0; i < length; i++){
            line = reader.readLine();
            String[] credentialsTeacher = line.split(",");
            LocalTeachers.add(new Teacher(credentialsTeacher[0], credentialsTeacher[1], credentialsTeacher[2], credentialsTeacher[3]));
        }

//        reader.close();
//        writer.close();
    }

    public static void Flush() throws IOException {
        FileWriter writer = new FileWriter("Teachers.txt");

        for(Teacher teacher : LocalTeachers)
        {
            writer.write(teacher.firstName);
            writer.write(",");
            writer.write(teacher.lastName);
            writer.write(",");
            writer.write(teacher.username);
            writer.write(",");
            writer.write(teacher.password);
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


