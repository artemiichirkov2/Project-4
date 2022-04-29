import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Course {
    public static ArrayList<Course> LocalCourses;

    public static void PrintCourse(Course c)
    {
        System.out.print(c.name);
        System.out.println("(" + c.quizzes.size() + " quizzes)");
    }

    public static void Initialise() throws IOException {
        LocalCourses = new ArrayList<>();

        BufferedReader inputStream = null;

        try {
            inputStream = new BufferedReader(new FileReader("Courses.txt"));
        } catch (FileNotFoundException e) {
            File studentsFile = new File("Courses.txt");
            studentsFile.createNewFile();
            inputStream = new BufferedReader(new FileReader("Courses.txt"));
        }

        String line = inputStream.readLine();
        if(line == null) return;
        String[] courseNames = line.split(",");
        for(String courseName : courseNames)
        {
            Course thisCourse = new Course(courseName);

            ArrayList<Quiz> quizzes = new ArrayList<>();

            BufferedReader quizReader = null;

            try {
                quizReader = new BufferedReader(new FileReader("Quizzes.txt"));
            } catch (FileNotFoundException e) {
                File quizFile = new File("Quizzes.txt");
                quizFile.createNewFile();
                quizReader = new BufferedReader(new FileReader("Quizzes.txt"));
            }

            String qLine;
            while ((qLine = quizReader.readLine()) != null)
            {
                Quiz thisQuiz = new Quiz();
                String[] qLineSplit = qLine.split(";");
                if(qLineSplit.length < 4) break;
                if(!qLineSplit[0].equals(courseName)) continue;

                String quizName = qLineSplit[1];
                boolean randomized = qLineSplit[2].equals("t");

                ArrayList<Question> questions = new ArrayList<>();
                String[] questionStrings = qLineSplit[3].split("~");

                for(int i = 0; i < questionStrings.length; i++)
                {
                    String[] questionAnswers = questionStrings[i].split("`");
                    questions.add(new Question(questionAnswers[0], questionAnswers[1]));
                }

                BufferedReader subReader = null;

                try {
                    subReader = new BufferedReader(new FileReader("Submissions.txt"));
                } catch (FileNotFoundException e) {
                    File subFile = new File("Submissions.txt");
                    subFile.createNewFile();
                    subReader = new BufferedReader(new FileReader("Submissions.txt"));
                }

                ArrayList<Submission> submissions = new ArrayList<>();
                String subLine;
                while((subLine = subReader.readLine()) != null)
                {
                    String[] props = subLine.split(";");
                    if(props.length < 6) continue;

                    String subCourseName = props[0];
                    String subQuizName = props[1];

                    if(!(thisCourse.name.equals(subCourseName) && quizName.equals(subQuizName))) continue;

                    String submitter = props[2];
                    int score = Integer.parseInt(props[3]);

                    String stamp = props[4];

                    String answersString = props[5];
                    List<String> answers = Arrays.asList(answersString.split("~"));
                    if(answers.size() != questions.size()) continue;

                    ArrayList<String> answersAsArrList = new ArrayList<>(answers);
                    submissions.add(new Submission(submitter, answersAsArrList, score, stamp));
                }

                subReader.close();
                thisQuiz.questions = questions;
                thisQuiz.QuizName = quizName;
                thisQuiz.randomization = randomized;
                thisQuiz.submissions = submissions;
                quizzes.add(thisQuiz);
            }

            quizReader.close();
            thisCourse.quizzes = quizzes;
            LocalCourses.add(thisCourse);
        }

        inputStream.close();
    }

    public static void Flush() throws IOException {
        FileWriter writer = new FileWriter("Courses.txt");
        FileWriter quizWriter = new FileWriter("Quizzes.txt");
        FileWriter submissionWriter = new FileWriter("Submissions.txt");

        for(Course course : LocalCourses)
        {
            writer.write(course.name);
            writer.write(',');

            for(Quiz q : course.quizzes)
            {
                quizWriter.write(course.name);
                quizWriter.write(";");
                quizWriter.write(q.QuizName);
                quizWriter.write(";");
                quizWriter.write(q.randomization ? "t" : "f");
                quizWriter.write(";");
                for(Question ques : q.questions)
                {
                    quizWriter.write(ques.question);
                    quizWriter.write("`");
                    quizWriter.write(ques.answers);
                    quizWriter.write("~");
                }
                quizWriter.write("\n");

                for(Submission sub : q.submissions)
                {
                    submissionWriter.write(course.name);
                    submissionWriter.write(";");
                    submissionWriter.write(q.QuizName);
                    submissionWriter.write(";");
                    submissionWriter.write(sub.studentID);
                    submissionWriter.write(";");
                    submissionWriter.write("" + sub.score);
                    submissionWriter.write(";");
                    submissionWriter.write(sub.stamp);
                    submissionWriter.write(";");
                    for(String answer : sub.answers)
                    {
                        submissionWriter.write(answer);
                        submissionWriter.write("~");
                    }
                    submissionWriter.write("\n");
                }
            }
        }

        writer.write("\n");
        writer.close();
        quizWriter.close();
        submissionWriter.close();
    }

    public static ArrayList<Quiz> quizzes;
    public String name;

    public static ArrayList<Quiz> getQuizzes() {
        return quizzes;
    }

    public Course(String name) {
        this.name = name;
        this.quizzes = new ArrayList<>();
    }
}