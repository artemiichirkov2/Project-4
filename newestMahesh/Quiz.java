import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Quiz {
    private ArrayList<Question> questions;
    private String QuizName;
    private boolean randomiziation;
    public ArrayList<Submission> submissions;

    public Quiz(ArrayList<Question> questions, String QuizName, boolean randomiziation) {

        this.questions = questions;
        this.QuizName = QuizName;
        this.randomiziation = randomiziation;
        this.submissions = new ArrayList<Submission>();
    }

    public ArrayList<Submission> getSubmissions() {
        return submissions;
    }

//    public void addSubmission(String studentID, int sumbmission) {
//    submissions.add(new Submission(studentID, sumbmission));
//        }

    public Quiz() {
        this.questions = null;
        this.QuizName = "";
        this.randomiziation = false;
    }

    public String getQuizName() {
        return QuizName;
    }

    public void setQuizName(String quizName) {
        QuizName = quizName;
    }


    //this should prolly be moved to the course class
//    public void createQuiz(Scanner sc) {
//
//        System.out.printf("%s\n", "What is the name of this quiz?");
//        this.QuizName = sc.nextLine();
//        System.out.printf("%s\n", "How many questions are in this quiz?");
//        int quizQs = Integer.parseInt(sc.nextLine()); // pretend this works
//        System.out.printf("%s\n", "Would you like to enable randomization(y or n)?");
//        String rando = sc.nextLine();
//        if(rando.equals("y"))
//        {
//            this.randomiziation = true;
//        }
//        else if(rando.equals("n"))
//        {
//            this.randomiziation = false;
//        }
//        for (int i = 0; i < quizQs; i++) {
//
//            System.out.printf("%s\n", "What is the question to ask?");
//            String answer = sc.nextLine();
//            System.out.printf("%s\n", "How many answers are in this quiz question?");
//            int quizAnswers = Integer.parseInt(sc.nextLine());
//            ArrayList<String> options = new ArrayList<String>();
//            for (int y = 0; y < quizAnswers; i++) {
//                System.out.printf("%s\n", "What is the next quiz question option?");
//                String option = sc.nextLine();
//                options.add(option);
//            }
//            questions.add(new Question(answer, options));
//        }
//
//        // returns quiz object
//    }

    public void gradingMethod(){}


    public void editQuiz(Scanner sc) {

        viewQuiz();
        System.out.printf("%s\n", "Which question would you like to edit?");
        int questionToEdit = Integer.parseInt(sc.nextLine());
        System.out.printf("%s\n", "Would you like to enable randomization(y or n)?");
        String rando = sc.nextLine();
        if(rando.equals("y"))
        {
            this.randomiziation = true;
        }
        else if(rando.equals("n"))
        {
            this.randomiziation = false;
        }

        System.out.printf("%s\n", "Would you like to edit the question it's asking (y or n)?");
        String questionOrAnswers = sc.nextLine();
        if(questionOrAnswers.equals("y"))
        {
            System.out.printf("%s\n", "What would you like to set the new question to?");
            String newQuestion = sc.nextLine();
            questions.get(questionToEdit).setQuestion(newQuestion);
        }
        else if(questionOrAnswers.equals("n"))
        {
            System.out.printf("%s\n", "Which answer would you like to edit?");
            int answerToEdit = Integer.parseInt(sc.nextLine());
            System.out.printf("%s\n", "What would you like the new answer to be?");
            String newAnswer = sc.nextLine();
            questions.get(questionToEdit).getAnswers().set(answerToEdit, newAnswer);

        }




    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public void viewQuiz() {
        randomize();
        for (int i = 0; i < questions.size(); i++) {
            System.out.println("Question " + i + 1 + ".\n");
            questions.get(i).toString();
        }
    }

    public void randomize()
    {
        if(randomiziation == true)
        {
            Collections.shuffle(questions);
        }
    }


}


