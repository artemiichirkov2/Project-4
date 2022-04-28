import java.util.ArrayList;

public class Question {
    public String question;
    public String answers;

    public static void PrintQuestion(Question q)
    {
        System.out.println(q.question);
        System.out.println(q.answers);
    }

    public Question(String question, String answers) {
        this.question = question;
        this.answers = answers;
    }
}