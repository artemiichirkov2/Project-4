import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(4245);

            Socket socket = serverSocket.accept();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            int connection = 0;
            String message = "";
            while(connection == 0)
            {
                try {
                    message = reader.readLine();
                    if (message.equals("readStudent")) {
                        readStudent(writer);


                    } else if (message.equals("readTeacher")) {

                        readTeacher(writer);

                    } else if (message.equals("readCourses")) {
                        readCourses(writer);

                    } else if (message.equals("done")) {
                    connection = 1;
                    }// writing methods, dw about right now
                    else {
                    connection = 0;
                    }


                }catch(NullPointerException n)
                {

                }


            }




        }catch(IOException f)
        {
        }

    }


    public static void readStudent(PrintWriter writer) throws IOException
    {
        BufferedReader inputStream = null;
        BufferedReader inputStream1 = null;

        try {
            inputStream1 = new BufferedReader(new FileReader("Students.txt"));
        } catch (FileNotFoundException e) {
            File studentsFile = new File("Students.txt");
            studentsFile.createNewFile();
            inputStream1 = new BufferedReader(new FileReader("Students.txt"));
        }

        int length = 0;
        String line1;
        while((line1 = inputStream1.readLine()) != null)
        {
        length++;
        }

        try {
            inputStream = new BufferedReader(new FileReader("Students.txt"));
        } catch (FileNotFoundException e) {
            File studentsFile = new File("Students.txt");
            studentsFile.createNewFile();
            inputStream = new BufferedReader(new FileReader("Students.txt"));
        }

        writer.write(length + "");
        writer.println();
        writer.flush();


        String line;
        while ((line = inputStream.readLine()) != null) {

            writer.write(line);
            writer.println();
            writer.flush();
        }
        inputStream.close();
    }



    public static void readTeacher(PrintWriter writer) throws IOException
    {
        BufferedReader inputStream = null;
        BufferedReader inputStream1 = null;

        try {
            inputStream1 = new BufferedReader(new FileReader("Teachers.txt"));
        } catch (FileNotFoundException e) {
            File teachersFile = new File("Teachers.txt");
            teachersFile.createNewFile();
            inputStream1 = new BufferedReader(new FileReader("Teachers.txt"));
        }

        int length = 0;
        String line1;
        while((line1 = inputStream1.readLine()) != null)
        {
            length++;
        }

        try {
            inputStream = new BufferedReader(new FileReader("Teachers.txt"));
        } catch (FileNotFoundException e) {
            File teachersFile = new File("Teachers.txt");
            teachersFile.createNewFile();
            inputStream = new BufferedReader(new FileReader("Teachers.txt"));
        }

        writer.write(length + "");
        writer.println();
        writer.flush();


        String line;
        while ((line = inputStream.readLine()) != null) {
            writer.write(line);
            writer.println();
            writer.flush();
        }
        inputStream.close();
    }








    public static void readCourses(PrintWriter writer) throws IOException
    {
        BufferedReader inputStream = null;

        try {
            inputStream = new BufferedReader(new FileReader("Courses.txt"));
        } catch (FileNotFoundException e) {
            File studentsFile = new File("Courses.txt");
            studentsFile.createNewFile();
            inputStream = new BufferedReader(new FileReader("Courses.txt"));
        }

        String quizzes = inputStream.readLine();
        writer.write(quizzes);
        writer.println();
        writer.flush();


        BufferedReader quizReader = null;

        try {
            quizReader = new BufferedReader(new FileReader("Quizzes.txt"));
        } catch (FileNotFoundException e) {
            File quizFile = new File("Quizzes.txt");
            quizFile.createNewFile();
            quizReader = new BufferedReader(new FileReader("Quizzes.txt"));
        }

        String line;
        while ((line = quizReader.readLine()) != null) {
            writer.write(line);
            writer.println();
            writer.flush();
        }

    }

}
