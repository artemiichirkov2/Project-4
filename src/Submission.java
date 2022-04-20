import java.time.LocalDateTime;
import java.util.ArrayList;

public class Submission {
    public String studentID;
    public ArrayList<String> answers;
    public int score;
    public String stamp;

    public static void PrintSubmissionBrief(Submission sub, Quiz forQuiz)
    {
        int studentIndex = Student.GetStudentIndex(sub.studentID);
        if(studentIndex == -1) return;
        Student s = Student.LocalStudents.get(studentIndex);

        System.out.print(forQuiz.QuizName);
        System.out.print("\t\t\t");
        System.out.println((sub.score == -1 ? "Ungraded" : ("Score: " + sub.score)));
        System.out.println("Submitted at " + sub.stamp + " by " + s.firstName + " " + s.lastName + "(" + s.username + ")");
    }

    public static void PrintSubmission(Submission sub, Quiz forQuiz)
    {
        PrintSubmissionBrief(sub, forQuiz);
        for(int i = 0; i < sub.answers.size(); i++)
        {
            Question q = forQuiz.questions.get(i);
            Question.PrintQuestion(q);
            System.out.println("Answer:");
            System.out.println(sub.answers.get(i));
            System.out.println();
        }
    }

    public Submission(String studentID, ArrayList<String> submissions) {
        this.studentID = studentID;
        this.answers = submissions;
        this.score = -1;
        this.stamp = LocalDateTime.now().toString();
    }

    public Submission(String studentID, ArrayList<String> submissions, int score, String timestamp) {
        this.studentID = studentID;
        this.answers = submissions;
        this.score = score;
        this.stamp = timestamp;
    }
}
