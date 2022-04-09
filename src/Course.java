import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Course {
    public static void main(String[] args) throws IOException {
        BufferedReader br = null;
        ArrayList<String> list = new ArrayList<>();

        Scanner scan = new Scanner(System.in);

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

//        System.out.println(list);

        int outerLoop = 5;
        do {
            System.out.println("1. Show Courses");
            System.out.println("2. Add a Course");
            System.out.println("3. Remove a Course");
            System.out.println("Press 0 to Quit");

            try {
                outerLoop = scan.nextInt();
                scan.nextLine();
                if (outerLoop >= 0 && outerLoop <= 3) {
                    if (outerLoop == 1) {
                        System.out.println("\nCurrent courses:");
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println(list.get(i));
                        }
                        System.out.println();
                    } else if (outerLoop == 2) {
                        System.out.println("Enter course name to add (without spaces)");
                        String courseAdd;
                        do {
                            courseAdd = scan.nextLine();
                            courseAdd = courseAdd.toUpperCase();
                            if (courseAdd.contains(" ")) {
                                System.out.println("Course name should not contain spaces!");
                            } else {
                                if (!list.contains(courseAdd)) {
                                    list.add(courseAdd);
                                    addCourse(courseAdd, list);
                                } else {
                                    System.out.println("Course already exists!");
                                }
                            }
                        } while (courseAdd.contains(" "));
                    } else if (outerLoop == 3) {
                        System.out.println("Enter course to be removed!");
                        String courseRemove;
                        do {
                            courseRemove = scan.nextLine();
                            courseRemove = courseRemove.toUpperCase();
                            if (list.contains(courseRemove)){
                                try {
                                    list.remove(courseRemove);
                                    removeCourse(courseRemove, list);
                                } catch (Exception e) {
                                    System.out.println("There are no courses!");
                                }
                            } else if (courseRemove == "0") {
                                break;
                            } else {
                                System.out.println("Please enter a valid course name or enter 0 to return to the previous menu");
                            }
                        } while (courseRemove.contains(" "));

                    }
                } else {
                    System.out.println("Please select a valid option (value out of range!)");
                }

            } catch (InputMismatchException e){
                System.out.println("Please enter a valid option!");
            }

        } while (outerLoop != 0);
    }

    public static void addCourse(String courseAdd, ArrayList<String> currentCourses) throws FileNotFoundException {
        System.out.println("Added course!\n");

        FileOutputStream fos = new FileOutputStream("data.txt", true);
        PrintWriter pw = new PrintWriter(fos);

        String courseName = String.format("\n" + courseAdd);

        pw.write(courseName);
        pw.flush();
        pw.close();
    }

    public static void removeCourse(String courseRemove, ArrayList<String> currentCourses) throws FileNotFoundException {
        System.out.println("Removed course!\n");

        FileOutputStream fos = new FileOutputStream("data.txt", false);
        PrintWriter pw = new PrintWriter(fos);
        pw.write(currentCourses.get(0));
        for (int i = 1; i < currentCourses.size(); i++) {
            pw.write("\n");
            pw.write(currentCourses.get(i));
        }

        pw.flush();
        pw.close();
    }
}
