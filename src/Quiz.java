import java.util.ArrayList;
import java.util.Scanner;

public class Quiz {
    private ArrayList<Question> questions = new ArrayList<Question>();

    public Quiz(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public Quiz() {
        this.questions = null;
    }


    public void createQuiz(Scanner sc) {

        System.out.printf("%s\n", "How many questions are in this quiz?");
        int quizQs = Integer.parseInt(sc.nextLine()); // pretend this works
        for (int i = 0; i < quizQs; i++) {

            System.out.printf("%s\n", "What is the question to ask?");
            String answer = sc.nextLine();
            System.out.printf("%s\n", "How many answers are in this quiz question?");
            int quizAnswers = Integer.parseInt(sc.nextLine());
            ArrayList<String> options = new ArrayList<String>();
            for (int y = 0; y < quizAnswers; i++) {
                System.out.printf("%s\n", "What is the next option?");
                String option = sc.nextLine();
                options.add(option);
            }
            questions.add(new Question(answer, options));
        }

        // returns quiz object
    }

    public void editQuiz(Scanner sc) {

        viewQuizzes();
        System.out.printf("%s\n", "Which quiz would you like to edit?");
        int quizToEdit = Integer.parseInt(sc.nextLine());

        // how should this work, does the teacher select which question they want to edit

    }

    public void viewQuizzes() {
        for (int i = 0; i < questions.size(); i++) {
            System.out.println("Question " + i + 1 + ".\n");
            questions.get(i).toString();
        }
    }

    public void gradeQuiz() {

    }
}

