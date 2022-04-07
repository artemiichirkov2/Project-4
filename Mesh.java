import java.util.ArrayList;
import java.util.Scanner;

public class Mesh {
    private ArrayList<Teacher> teacherSignIn = new ArrayList<Teacher>();
    private ArrayList<Teacher> teacherSignUp = new ArrayList<Teacher>();
    private ArrayList<Student> studentSignIn = new ArrayList<Student>();
    private ArrayList<Student> studentSignUp = new ArrayList<Student>();

    public String students;

    public Mesh(ArrayList<Student> students) {
        this.students = students;
    }

    public Mesh () {
        this.students = null;
    }


}