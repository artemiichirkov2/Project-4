import java.util.ArrayList;

public class Submission {

    private String studentID;
    private ArrayList<String> submissions;
    private int score;

    public Submission(String studentID, ArrayList<String> submissions) {
        this.studentID = studentID;
        this.submissions = submissions;
        this.score = 0;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public ArrayList<String> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(ArrayList<String> submissions) {
        this.submissions = submissions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
