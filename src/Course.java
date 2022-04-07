import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Course {
    public static void main(String[] args) throws IOException {

        BufferedReader br = null;
        ArrayList<String> list = new ArrayList<>();

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

        System.out.println(list);
//        br.close();

    }
}
