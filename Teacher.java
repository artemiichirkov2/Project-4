import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

    public class Teacher {
        private String firstName;
        private String lastName;
        private String username;
        private String password;

        public Teacher(String firstName, String lastName, String username, String password) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.username = username;
            this.password = password;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public static void main(String[] args) throws IOException {

            Scanner s = new Scanner(System.in);
            Teacher[]teachers = teachers.scanner.nextLine();
            //Student credentials, does it match up with old account, if so, continue. If not, reenter
        }
    }


