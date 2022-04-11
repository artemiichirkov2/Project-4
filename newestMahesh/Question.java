import java.util.ArrayList;

public class Question {
    private String question;
    private ArrayList<String> answers = new ArrayList<String>();

    public Question(String question, ArrayList<String> answers)
    {
        this.question = question;
        this.answers = answers;
    }

    @Override
    public String toString() {
        String answersToString = "";
        for(int i = 0; i < answers.size(); i++)
        {
            answersToString = answersToString + "\n" + i + 1  + ". " + answers.get(i);
        }
        return "Question: " + question + " " + answersToString + "\n";
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}


