import java.util.ArrayList;
import java.util.Scanner;

public class Course
{
   private ArrayList<Quiz> quizzes;
   private String name;

 public void viewQuizzes()
 {
    for (int i = 0; i < quizzes.size(); i++)
    {
       quizzes.get(i).viewQuiz();
    }
 }
 public Course(String name)
 {
    this.name = name;
 }
   public Course(String name, ArrayList<Quiz> quizzes)
   {
      this.quizzes = quizzes;
      this.name = name;
   }
 public void removeQuiz(Scanner sc)
 {
    viewQuizzes();
    System.out.printf("%s\n", "Which quiz would you like to remove?");
    int quizNumber = Integer.parseInt(sc.nextLine());
    quizzes.remove(quizNumber);
    System.out.printf("%s\n", "Quiz Removed");

 }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public ArrayList<Quiz> getQuizzes() {
      return quizzes;
   }

   public void setQuizzes(ArrayList<Quiz> quizzes) {
      this.quizzes = quizzes;
   }

   public void createQuiz(Scanner sc) {
      ArrayList<Question> questions = new ArrayList<Question>();
      ArrayList<String> options = new ArrayList<String>();
      System.out.printf("%s\n", "What is the name of this quiz?");
      String QuizName = sc.nextLine();
      System.out.printf("%s\n", "How many questions are in this quiz?");
      int quizQs = Integer.parseInt(sc.nextLine()); // pretend this works
      System.out.printf("%s\n", "Would you like to enable randomization(y or n)?");
      String rando = sc.nextLine();
      boolean randomiziation = false;
      if(rando.equals("y"))
      {
         randomiziation = true;
      }
      else if(rando.equals("n"))
      {
         randomiziation = false;
      }
      for (int i = 0; i < quizQs; i++) {

         System.out.printf("%s\n", "What is the question to ask?");
         String answer = sc.nextLine();
         System.out.printf("%s\n", "How many answers are in this quiz question?");
         int quizAnswers = Integer.parseInt(sc.nextLine());


         for (int y = 0; y < quizAnswers; y++) {
            System.out.printf("%s\n", "What is the next quiz question option?");
            String option = sc.nextLine();
            options.add(option);
         }
         questions.add(new Question(answer, options));
      }

      quizzes.add(new Quiz(questions, QuizName, randomiziation));
   }

   public void TakeQuiz(Scanner sc, String studentID)
   {
      viewQuizzes();
      System.out.printf("%s\n", "Which quiz would you like to take?");
      int quizNumber = Integer.parseInt(sc.nextLine());
      quizzes.get(quizNumber).viewQuiz();
      System.out.printf("%s\n", "What is your answer?");
      int studentAnswer = Integer.parseInt(sc.nextLine());
      quizzes.get(quizNumber).addSubmission(studentID, studentAnswer);
   }


}