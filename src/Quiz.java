import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Quiz {
    public static void PrintQuiz(Quiz q)
    {
        PrintQuizBrief(q);
        for(Question ques : q.questions)
        {
            Question.PrintQuestion(ques);
        }
    }

    public static void PrintQuizBrief(Quiz q)
    {
        System.out.println("Quiz Name: " + q.QuizName);
        System.out.println("Order Randomisation: " + (q.randomization ? "On" : "Off"));
    }

    public ArrayList<Question> questions;
    public String QuizName;
    public boolean randomization;
    public ArrayList<Submission> submissions;

    public Quiz() {
        this.questions = null;
        this.QuizName = "";
        this.randomization = false;
        this.submissions = new ArrayList<>();
    }

    public Quiz(File f, boolean randomization, Course course)
    {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(f));
        } catch (FileNotFoundException e) {
            return;
        }

        List<String> lines = reader.lines().toList();
        if(lines.size() < 3 || lines.size() % 2 == 0)
        {
            System.out.println("Incorrect Format for Quiz File");
        }

        this.QuizName = lines.get(0);
        this.submissions = new ArrayList<>();
        boolean isUnique = true;
        if(course != null)
        {
            for(Quiz q : course.quizzes)
            {
                if(q.QuizName.equals(this.QuizName))
                {
                    isUnique = false;
                    break;
                }
            }

            if(!isUnique)
            {
                this.QuizName = "71239";
                return;
            }
        }

        this.randomization = randomization;

        ArrayList<Question> questions= new ArrayList<>();
        for(int i = 1; i < lines.size(); i += 2)
        {
            Question thisQuestion = new Question(lines.get(i), lines.get(i+1));
            questions.add(thisQuestion);
        }

        this.questions = questions;
    }
}


