import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Student {
    public static ArrayList<Student> LocalStudents;
    public String firstName;
    public String lastName;
    public String username;
    public String password;

    public Student(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public static void Initialise() throws IOException {
        LocalStudents = new ArrayList<>();

        BufferedReader inputStream = null;

        try {
            inputStream = new BufferedReader(new FileReader("Students.txt"));
        } catch (FileNotFoundException e) {
            File studentsFile = new File("Students.txt");
            studentsFile.createNewFile();
            inputStream = new BufferedReader(new FileReader("Students.txt"));
        }

        String line;
        while ((line = inputStream.readLine()) != null) {
            String[] credentialsStudent = line.split(",");
            LocalStudents.add(new Student(credentialsStudent[0], credentialsStudent[1], credentialsStudent[2], credentialsStudent[3]));
        }

        inputStream.close();
    }

    public static void InitialiseFromServer(BufferedReader reader, PrintWriter writer) throws IOException, InterruptedException {
        LocalStudents = new ArrayList<>();

        writer.write("readStudent"); // what to send to server
        writer.println();
        writer.flush(); // ensure data is sent to the server

   //     Thread.sleep(5000);
       int length = Integer.parseInt(reader.readLine());

        String line = "";
        for(int i = 0; i < length; i++){
            line = reader.readLine();
            String[] credentialsStudent = line.split(",");
            LocalStudents.add(new Student(credentialsStudent[0], credentialsStudent[1], credentialsStudent[2], credentialsStudent[3]));
        }

//        reader.close();
//        writer.close();
    }

    public static void Flush() throws IOException {
        FileWriter writer = new FileWriter("Students.txt");

        for(Student student : LocalStudents)
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

    public static int GetStudentIndex(String username)
    {
        int res = -1;
        for(int i =0; i < LocalStudents.size(); i++)
        {
            if(LocalStudents.get(i).username.equals(username))
            {
                res = i;
                break;
            }
        }

        return res;
    }


}
