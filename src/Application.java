import java.util.*;
import java.io.*;

public class Application {
    static Authorization auth = new Authorization();

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        Student.Initialise();
        Teacher.Initialise();
        Course.Initialise();

        while (!auth.authorized) {
            System.out.println("Welcome to the Quiz app!\n");
            System.out.println("Do you want to Sign In or Sign Up?\n[1]Sign In\n[2]Sign Up");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    auth = Authorization.SignIn(scanner);
                    break;
                case "2":
                    auth = Authorization.SignUp(scanner);
                    break;
                default:
                    System.out.println("Not allowed");
                    break;
            }
        }

        switch (auth.type)
        {
            case Student -> StudentOptions(scanner);
            case Teacher -> TeacherOptions(scanner);
        }

        System.out.println("Signed out.");
    }

    public static int FindCourseIndex(String courseName)
    {
        int index = -1;

        for(int i = 0; i < Course.LocalCourses.size(); i++)
        {
            if(Course.LocalCourses.get(i).name.equals(courseName))
            {
                index = i;
                break;
            }
        }

        return index;
    }

    public static int FindQuizIndexInCourse(Course course, String quizName)
    {
        int index = -1;

        for(int i = 0; i < course.quizzes.size(); i++)
        {
            if(course.quizzes.get(i).QuizName.equals(quizName))
            {
                index = i;
                break;
            }
        }

        return index;
    }

    private static int AddQuizFromFile(Scanner scanner, Course thisCourse, int courseIndex) throws IOException {
        String choice;
        System.out.println("Enter Path to Quiz File (First Line Quiz Name, Then every two consecutive lines contain questions and answer options: ");
        String quizFileName = scanner.nextLine();

        File file = new File(quizFileName);
        if(!file.exists())
        {
            System.out.println("File " + file.getAbsolutePath() + " Does Not Exist");
            return 0;
        }

        System.out.println("Should Question Order be Random? 1 for yes and 2 for no");
        choice = scanner.nextLine();

        boolean random = choice.equals("1");

        Quiz q = new Quiz(file, random, thisCourse);
        if(q.QuizName.equals("71239")) {
            return 1;
        }
        thisCourse.quizzes.add(q);
        Course.Flush();
        return 2;
    }

    private static int RemoveQuiz(Course course, String quizName) throws IOException {
        int index = FindQuizIndexInCourse(course, quizName);

        if(index == -1)
        {
            return 0;
        }

        course.quizzes.remove(index);
        Course.Flush();
        return 1;
    }

    public static void TeacherOptions(Scanner scanner) throws IOException {
        while(auth.authorized)
        {
            System.out.println("1. Create Course\n" +
                    "2. Remove Course\n" +
                    "3. Create Quiz\n" +
                    "4. Edit Quiz\n" +
                    "5. Remove Quiz\n" +
                    "6. View/Grade Quiz Submissions\n" +
                    "7. View Quizzes\n" +
                    "8. View Courses\n" +
                    "9. Sign Out");

            String choice = scanner.nextLine();

            switch (choice)
            {
                case "1":
                    boolean isUnique = false;
                    String courseName = "";

                    System.out.println("Enter Course Name (unique): ");
                    courseName = scanner.nextLine();

                    if(FindCourseIndex(courseName) != -1)
                    {
                        System.out.println("Course Already Exists");
                        break;
                    }

                    Course.LocalCourses.add(new Course(courseName));
                    Course.Flush();
                    break;
                case "2":
                    System.out.println("Enter Course Name: ");
                    courseName = scanner.nextLine();

                    int index = FindCourseIndex(courseName);

                    if(index == -1)
                    {
                        System.out.println("No such course exists");
                        break;
                    }

                    Course.LocalCourses.remove(index);
                    Course.Flush();
                    System.out.println("Course Removed");
                    break;
                case "3":
                    System.out.println("Enter Course Name: ");
                    courseName = scanner.nextLine();

                    index = FindCourseIndex(courseName);

                    if(index == -1)
                    {
                        System.out.println("No such course exists");
                        break;
                    }

                    Course thisCourse = Course.LocalCourses.get(index);

                    int status = AddQuizFromFile(scanner, thisCourse, index);
                    if(status == 1)
                    {
                        System.out.println("Duplicate Quiz Already Exists");
                    }
                    else if(status == 2){
                        System.out.println("Added Quiz");
                    }
                    break;
                case "4":
                    System.out.println("Enter Course Name (Where The Quiz Is): ");
                    courseName = scanner.nextLine();

                    index = FindCourseIndex(courseName);

                    if(index == -1)
                    {
                        System.out.println("No such course exists");
                        break;
                    }

                    thisCourse = Course.LocalCourses.get(index);

                    System.out.println("Enter Quiz Name: ");
                    String quizName = scanner.nextLine();
                    status = RemoveQuiz(thisCourse, quizName);

                    if(status == 0)
                    {
                        System.out.println("No such quiz found in this course");
                        break;
                    }

                    status = AddQuizFromFile(scanner, thisCourse, index);
                    if(status == 1)
                    {
                        System.out.println("Duplicate Quiz Already Exists");
                    }
                    else if(status == 2){
                        System.out.println("Added Quiz");
                    }
                    break;
                case "5":
                    System.out.println("Enter Course Name (Where The Quiz Is): ");
                    courseName = scanner.nextLine();

                    index = FindCourseIndex(courseName);

                    if(index == -1)
                    {
                        System.out.println("No such course exists");
                        break;
                    }

                    thisCourse = Course.LocalCourses.get(index);

                    System.out.println("Enter Quiz Name: ");
                    quizName = scanner.nextLine();
                    status = RemoveQuiz(thisCourse, quizName);

                    if(status == 0) System.out.println("No such quiz found"); else System.out.println("Quiz deleted");
                    break;
                case "6":
                    System.out.println("Enter Course Name: ");
                    courseName = scanner.nextLine();

                    index = FindCourseIndex(courseName);

                    if(index == -1)
                    {
                        System.out.println("No such Course Exists");
                        break;
                    }

                    thisCourse = Course.LocalCourses.get(index);

                    System.out.println("Enter Quiz Name: ");
                    quizName = scanner.nextLine();

                    index = FindQuizIndexInCourse(thisCourse, quizName);

                    if(index == -1)
                    {
                        System.out.println("No such quiz exists. ");
                        break;
                    }

                    Quiz thisQuiz = thisCourse.quizzes.get(index);

                    int i = 1;
                    for(Submission s : thisQuiz.submissions)
                    {
                        System.out.println(i+":");
                        Submission.PrintSubmissionBrief(s, thisQuiz);
                        i += 1;
                        System.out.println();
                    }

                    Submission sub;
                    i -= 1;

                    if(i == 0)
                    {
                        System.out.println("No Submissions Yet");
                        break;
                    }

                    int choiceNum;

                    while(true)
                    {
                        System.out.println("Select Submission (1 to " + i + ") to View More Closely (or press 0 to exit)");
                        choice = scanner.nextLine();

                        try
                        {
                            choiceNum = Integer.parseInt(choice);
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("That is not a number");
                            continue;
                        }

                        if(choiceNum == 0)
                        {
                            break;
                        }

                        if(choiceNum < 1 || choiceNum > i)
                        {
                            System.out.println("PLease Choose a Valid Option");
                            continue;
                        }

                        break;
                    }

                    if(choiceNum == 0)
                    {
                        break;
                    }
                    else
                    {
                        sub = thisQuiz.submissions.get(choiceNum-1);
                    }

                    Submission.PrintSubmission(sub, thisQuiz);

                    while(true)
                    {
                        System.out.println("Enter Score (enter -1 to ungrade): ");
                        choice = scanner.nextLine();

                        try
                        {
                            choiceNum = Integer.parseInt(choice);
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("Not a number");
                            continue;
                        }

                        break;
                    }

                    sub.score = choiceNum;
                    Course.Flush();
                    break;
                case "7":
                    for(Course c : Course.LocalCourses)
                    {
                        System.out.println("Course: " + c.name);
                        for(Quiz q : c.quizzes)
                        {
                            Quiz.PrintQuiz(q);
                            System.out.println();
                        }
                        System.out.println();
                    }
                    break;
                case "8":
                    for(Course c : Course.LocalCourses)
                    {
                        Course.PrintCourse(c);
                        System.out.println();
                    }
                    break;
                case "9":
                    auth.authorized = false;
                    break;
                default:
                    System.out.println("Please enter a valid option.");
                    break;
            }
        }
    }

    public static void StudentOptions(Scanner scanner) throws IOException
    {
        while(auth.authorized)
        {
            System.out.println("1. View Courses and Quizzes\n2. Submit Solutions\n3. View My Submissions\n4. Sign Out");

            String choice = scanner.nextLine();

            switch (choice)
            {
                case "1":
                    for(Course c : Course.LocalCourses)
                    {
                        System.out.println("Course: " + c.name);
                        for(Quiz q : c.quizzes)
                        {
                            Quiz.PrintQuizBrief(q);
                            System.out.println();
                        }
                        if(c.quizzes.size() == 0) System.out.println();
                    }
                    break;
                case "2":
                    System.out.println("Enter Course Name: ");
                    String courseName = scanner.nextLine();

                    int index = FindCourseIndex(courseName);

                    if(index == -1)
                    {
                        System.out.println("No Such Course Exists");
                        break;
                    }

                    Course course = Course.LocalCourses.get(index);

                    System.out.println("Enter Quiz Name: ");
                    String quizName = scanner.nextLine();

                    index = FindQuizIndexInCourse(course, quizName);

                    if(index == -1)
                    {
                        System.out.println("No Such Quiz Exists");
                        break;
                    }

                    Quiz quiz = course.quizzes.get(index);
                    ArrayList<Question> questionsCopy = new ArrayList<>(quiz.questions);
//                    System.out.println(questionsCopy.size());
                    if(quiz.randomization) Collections.shuffle(questionsCopy);

                    ArrayList<String> answers = new ArrayList<>();
                    for(int i = 0; i < questionsCopy.size(); i++)
                    {
                        answers.add("");
                    }

                    for(int i = 0; i < questionsCopy.size();i++)
                    {
                        Question thisQuestion = questionsCopy.get(i);
                        int indexOfThisQuestionReally = quiz.randomization ? quiz.questions.indexOf(thisQuestion) : i;
                        Question.PrintQuestion(thisQuestion);
                        System.out.println("Answer (Enter Submits):");
                        answers.set(indexOfThisQuestionReally, scanner.nextLine());
                    }

                    Submission thisSubmission = new Submission(auth.username, answers);
                    quiz.submissions.add(thisSubmission);
                    Course.Flush();
                    break;
                case "3":
                    for(Course c : Course.LocalCourses)
                    {
                        for(Quiz q : c.quizzes)
                        {
                            for(Submission s : q.submissions)
                            {
                                if(s.studentID.equals(auth.username))
                                {
                                    Submission.PrintSubmission(s, q);
                                }
                            }
                        }
                    }
                    break;
                case "4":
                    auth.authorized = false;
                    break;
                default:
                    System.out.println("Please enter a valid option.");
                    break;
            }
        }
    }
}