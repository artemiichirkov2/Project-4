import com.sun.source.tree.Scope;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1111);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        Scanner scanner = new Scanner(System.in);

        boolean run = true;

        serverFlush("ping", reader, writer);

        String data = reader.readLine();
        do {
            if (data.equals("ping")) {
                System.out.println("Connected to server");
                data = "waiting";
                data = reader.readLine();
            }
            if (data.equals("welcome")) {
                System.out.println("Welcome to the Quiz app!\n");
                System.out.println("Do you want to Sign In or Sign Up?\n[1]Sign In\n[2]Sign Up");
                String choice = scanner.nextLine();
                serverFlush(choice, reader, writer);
                data = "waiting";
                data = reader.readLine();
            }
            if (data.equalsIgnoreCase("error")) {
                System.out.println("Error!");
                data = reader.readLine();
            }
            if (data.equalsIgnoreCase("studentMenu")) {
                System.out.println("1. View Courses and Quizzes\n2. Submit Solutions\n3. View My Submissions\n4. Sign Out");
                String choice = scanner.nextLine();
                data = reader.readLine();
            }
            if (data.equalsIgnoreCase("signInMenu")) {
                System.out.println("1: Sign In as Teacher\n2: Sign In as Student");
                String option = scanner.nextLine();
                serverFlush(option, reader, writer);
                data = reader.readLine();
            }
            if (data.equalsIgnoreCase("username")) {
                System.out.println("Enter Username: ");
                String username = scanner.nextLine();
                serverFlush(username, reader, writer);
                data = reader.readLine();
            }
            if (data.equalsIgnoreCase("password")) {
                System.out.println("Enter Password: ");
                String password = scanner.nextLine();
                serverFlush(password, reader, writer);
                data = reader.readLine();
            }
            if (data.equalsIgnoreCase("incorrectLogin")) {
                System.out.println("Incorrect login type, username, or password.");
                data = reader.readLine();
            }


        } while (run);

    }

    public static void serverFlush(String request, BufferedReader reader, PrintWriter writer) throws IOException {
        writer.write(request);
        writer.println();
        writer.flush();
    }
}
