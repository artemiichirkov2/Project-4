import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.*;
import java.io.*;

public class Application extends JComponent implements Runnable {

    //Welcome Page Definitions
    JButton WelcomeTitle;
    JButton SignIn;
    JButton SignUp;
    //Sign Up Page Definitions
    JTextField signupTitle;
    JTextField firstName;
    JTextField lastName;
    JTextField username;
    JTextField password;
    JTextField isTeacher;
    JButton signupEnter;
    JButton signupBack;
    // sign in page definitions
    JTextField loginUsername;
    JTextField loginPassword;
    JTextField isTeacherSignIn;
    JButton loginEnter;
    JButton loginBack;
    JTextField loginTitle;
    //teacher page defitions
    JButton createCourse;
    JButton removeCourse;
    JButton createQuiz;
    JButton editQuiz;
    JButton removeQuiz;
    JButton quizSubmissions;
    JButton viewQuizzes;
    JButton viewCourses;
    JButton signOut;
    //student page defs
    JButton viewCoursesQuiz;
    JButton takeQuizzes;
    JButton viewSubmissions;
    JButton signOutStudent;


    @Override
    public void run() {


        try {
            String hostName = "";
            int port = 0;
            String portTemp = "";




            Socket socket = new Socket("localhost", 4245);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            JOptionPane.showMessageDialog(null, "Connection with" +
                            " server successfully established!", "Darkspace",
                    JOptionPane.INFORMATION_MESSAGE);


            // method to initalize data from server


//            Student.InitialiseFromServer(reader, writer);
//            Teacher.InitialiseFromServer(reader, writer);
//            Course.InitalizeFromServer(reader, writer);
            Student.Initialise();
            Teacher.Initialise();
            Course.Initialise();


            JFrame frame = new JFrame("Darkspace");
            frame.pack();
            Container content = frame.getContentPane();
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            JPanel loginPanel = new JPanel();
            JPanel teacherMenu = new JPanel();
            JPanel welcomePanel = new JPanel();
            JPanel studentMenu = new JPanel();
            JPanel signupPanel = new JPanel();


            // Welcome Pane UI Building
            WelcomeTitle = new JButton();
            WelcomeTitle.setText("Welcome! Please either select Sign Up or Sign In!");
            SignIn = new JButton("Sign In");
            SignUp = new JButton("Sign Up");
            WelcomeTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
            SignIn.setAlignmentX(Component.CENTER_ALIGNMENT);
            SignUp.setAlignmentX(Component.CENTER_ALIGNMENT);
            BoxLayout welcomeBoxLayout = new BoxLayout(welcomePanel, BoxLayout.Y_AXIS);
            welcomePanel.setLayout(welcomeBoxLayout);
            welcomePanel.add(WelcomeTitle);
            welcomePanel.add(SignUp);
            welcomePanel.add(SignIn);
            content.add(welcomePanel, BorderLayout.CENTER);
            // Welcome Pane UI Building


            //welcome pane event handlers

            //event handler for sign up button
            SignUp.addActionListener(new ActionListener() {
                boolean SignUpAgain = false;
                boolean SignUpAgainTeacher = false;
                boolean SignUpAgainStudent = false;

                public void actionPerformed(ActionEvent e) {
                    welcomePanel.setVisible(false);
                    signupPanel.setVisible(true);

                    if (!SignUpAgain) {
                        // signupPanel Pane UI Building
                        signupTitle = new JTextField("Welcome! Please enter your information!", 5);
                        firstName = new JTextField("firstname", 5);
                        lastName = new JTextField("lastname", 5);
                        username = new JTextField("username", 5);
                        password = new JTextField("password", 5);
                        isTeacher = new JTextField("Are you a teacher enter 1 for yes or 2 for no", 5);
                        signupEnter = new JButton();
                        signupEnter.setText("Enter");
                        signupBack = new JButton();
                        signupBack.setText("Back");
                        BoxLayout signUpLayout = new BoxLayout(signupPanel, BoxLayout.Y_AXIS);
                        // defining boxlayout
                        signupPanel.setLayout(signUpLayout);
                        signupPanel.add(signupTitle);
                        signupPanel.add(firstName);
                        signupPanel.add(lastName);
                        signupPanel.add(username);
                        signupPanel.add(password);
                        signupPanel.add(isTeacher);
                        signupPanel.add(signupEnter);
                        signupPanel.add(signupBack);
                        content.add(signupPanel, BorderLayout.CENTER);
                        // signupPanel Pane UI Building
                    }
                    updateUI();
                    SignUpAgain = true;


                    signupBack.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            signupPanel.setVisible(false);
                            welcomePanel.setVisible(true);

                        }
                    });

                    signupEnter.addActionListener(new ActionListener() {
                        

                        public void actionPerformed(ActionEvent e) {
                            try {

                                Authorization signup = Authorization.GUISignUp(firstName.getText(), lastName.getText(),
                                        username.getText(), password.getText(), isTeacher.getText());
                                if (signup != null) {
                                    if (signup.authorized) {
                                        signupPanel.setVisible(false);
                                        if (isTeacher.getText().equals("1")) {
                                            teacherMenu.setVisible(true);

                                            if(!SignUpAgainTeacher) {
                                                System.out.println("TEST");
                                                SignUpAgainTeacher = true;
                                                // teacher menu stuff
                                                //teacherPanel pane UI
                                                createCourse = new JButton("Create Course");
                                                removeCourse = new JButton("Remove Course");
                                                createQuiz = new JButton("Create Quiz");
                                                editQuiz = new JButton("Edit Quiz");
                                                removeQuiz = new JButton("Remove Quiz");
                                                quizSubmissions = new JButton("Grade Quiz Submissions");
                                                viewQuizzes = new JButton("View Quizzes");
                                                viewCourses = new JButton("View Courses");
                                                signOut = new JButton("Sign Out");
                                                GridLayout teacherLayout = new GridLayout(3, 3);
                                                teacherMenu.setLayout(teacherLayout);
                                                teacherMenu.add(createCourse);
                                                teacherMenu.add(removeCourse);
                                                teacherMenu.add(createQuiz);
                                                teacherMenu.add(editQuiz);
                                                teacherMenu.add(removeQuiz);
                                                teacherMenu.add(quizSubmissions);
                                                teacherMenu.add(viewQuizzes);
                                                teacherMenu.add(viewCourses);
                                                teacherMenu.add(signOut);
                                                content.add(teacherMenu, BorderLayout.CENTER);
                                                //teacherPanel pane UI

                                            }



                                            signOut.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    teacherMenu.setVisible(false);
                                                    welcomePanel.setVisible(true);

                                                }
                                            });

                                            createCourse.addActionListener(new ActionListener() {

                                                public void actionPerformed(ActionEvent e) {
                                                    try {
                                                        boolean isUnique = false;
                                                        String courseName = JOptionPane.showInputDialog(null, "Enter Course Name (unique): ", "Darkspace",
                                                                JOptionPane.QUESTION_MESSAGE);
                                                        if (FindCourseIndex(courseName) != -1) {
                                                            JOptionPane.showMessageDialog(null, "Course Already Exists", "Darkspace",
                                                                    JOptionPane.INFORMATION_MESSAGE);
                                                        } else {
                                                            Course.LocalCourses.add(new Course(courseName));
                                                            JOptionPane.showMessageDialog(null, "Course Added!", "Darkspace",
                                                                    JOptionPane.INFORMATION_MESSAGE);
                                                            Course.Flush();
                                                        }
                                                    } catch (IOException f) {
                                                        JOptionPane.showMessageDialog(null, "Error", "Darkspace",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                    }
                                                }
                                            });

                                            removeCourse.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    try {
                                                        String courseName = JOptionPane.showInputDialog(null, "Enter Course Name: ", "Darkspace",
                                                                JOptionPane.QUESTION_MESSAGE);
                                                        int index = FindCourseIndex(courseName);

                                                        if (index == -1) {
                                                            JOptionPane.showInputDialog(null, "No such course exists", "Darkspace",
                                                                    JOptionPane.QUESTION_MESSAGE);
                                                        } else {
                                                            Course.LocalCourses.remove(index);
                                                            Course.Flush();
                                                            JOptionPane.showMessageDialog(null, "Course Removed", "Darkspace",
                                                                    JOptionPane.INFORMATION_MESSAGE);
                                                        }
                                                    } catch (IOException f) {
                                                        JOptionPane.showMessageDialog(null, "Error", "Darkspace",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                    }

                                                }
                                            });

                                            createQuiz.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    try {
                                                        String courseName = JOptionPane.showInputDialog(null, "Enter Course Name: ", "Darkspace",
                                                                JOptionPane.QUESTION_MESSAGE);
                                                        int index = FindCourseIndex(courseName);
                                                        if (index == -1) {
                                                            JOptionPane.showInputDialog(null, "No such course exists", "Darkspace",
                                                                    JOptionPane.INFORMATION_MESSAGE);
                                                        } else {
                                                            Course thisCourse = Course.LocalCourses.get(index);

                                                            int status = GUIAddQuizInLine(thisCourse, index);
                                                            if (status == 1) {
                                                                JOptionPane.showInputDialog(null, "Duplicate Quiz Already Exists", "Darkspace",
                                                                        JOptionPane.INFORMATION_MESSAGE);
                                                            } else if (status == 2) {
                                                                JOptionPane.showMessageDialog(null, "Added Quiz", "Darkspace",
                                                                        JOptionPane.INFORMATION_MESSAGE);
                                                            }
                                                        }
                                                    } catch (IOException f) {
                                                        JOptionPane.showMessageDialog(null, "Error", "Darkspace",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                    }
                                                }
                                            });

                                            editQuiz.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    try {
                                                        String courseName = JOptionPane.showInputDialog(null, "Enter Course Name: ", "Darkspace",
                                                                JOptionPane.QUESTION_MESSAGE);
                                                        int index = FindCourseIndex(courseName);
                                                        if (index == -1) {
                                                            JOptionPane.showMessageDialog(null, "No such course exists", "Darkspace",
                                                                    JOptionPane.INFORMATION_MESSAGE);
                                                        } else {
                                                            Course thisCourse = Course.LocalCourses.get(index);


                                                            String quizName = JOptionPane.showInputDialog(null, "Enter Quiz Name: ", "Darkspace",
                                                                    JOptionPane.QUESTION_MESSAGE);
                                                            int status = RemoveQuiz(thisCourse, quizName);

                                                            if (status == 0) {
                                                                JOptionPane.showMessageDialog(null, "No such quiz found in this course", "Darkspace",
                                                                        JOptionPane.INFORMATION_MESSAGE);
                                                            } else {

                                                                status = GUIAddQuizInLine(thisCourse, index);
                                                                if (status == 1) {
                                                                    JOptionPane.showMessageDialog(null, "Duplicate Quiz Already Exists", "Darkspace",
                                                                            JOptionPane.INFORMATION_MESSAGE);
                                                                } else if (status == 2) {
                                                                    JOptionPane.showMessageDialog(null, "Added Quiz", "Darkspace",
                                                                            JOptionPane.INFORMATION_MESSAGE);
                                                                }
                                                            }
                                                        }
                                                    } catch (IOException f) {
                                                        JOptionPane.showMessageDialog(null, "Error", "Darkspace",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                    }
                                                }
                                            });
                                            removeQuiz.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    try {
                                                        String courseName = JOptionPane.showInputDialog(null, "Enter Course Name (Where The Quiz Is): ", "Darkspace",
                                                                JOptionPane.QUESTION_MESSAGE);
                                                        int index = FindCourseIndex(courseName);

                                                        if (index == -1) {
                                                            JOptionPane.showMessageDialog(null, "No such course exists", "Darkspace",
                                                                    JOptionPane.INFORMATION_MESSAGE);

                                                        } else {
                                                            Course thisCourse = Course.LocalCourses.get(index);

                                                            String quizName = JOptionPane.showInputDialog(null, "Enter Quiz Name: ", "Darkspace",
                                                                    JOptionPane.QUESTION_MESSAGE);
                                                            int status = RemoveQuiz(thisCourse, quizName);

                                                            if (status == 0) {
                                                                JOptionPane.showMessageDialog(null, "No such quiz found", "Darkspace",
                                                                        JOptionPane.INFORMATION_MESSAGE);
                                                            } else {
                                                                JOptionPane.showMessageDialog(null, "Quiz deleted", "Darkspace",
                                                                        JOptionPane.INFORMATION_MESSAGE);
                                                            }
                                                        }
                                                    } catch (IOException f) {
                                                        JOptionPane.showMessageDialog(null, "Error", "Darkspace",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                    }
                                                }
                                            });

                                            quizSubmissions.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    String courseName = JOptionPane.showInputDialog(null, "Enter Course Name (Where The Quiz Is): ", "Darkspace",
                                                            JOptionPane.QUESTION_MESSAGE);
                                                    int index = FindCourseIndex(courseName);

                                                    if (index == -1) {
                                                        JOptionPane.showMessageDialog(null, "No such Course Exists", "Darkspace",
                                                                JOptionPane.ERROR_MESSAGE);
                                                    } else {
                                                        Course thisCourse = Course.LocalCourses.get(index);
                                                        String quizName = JOptionPane.showInputDialog(null, "Enter Quiz Name: ", "Darkspace",
                                                                JOptionPane.QUESTION_MESSAGE);

                                                        index = FindQuizIndexInCourse(thisCourse, quizName);

                                                        if (index == -1) {
                                                            JOptionPane.showMessageDialog(null, "No such quiz exists. ", "Darkspace",
                                                                    JOptionPane.INFORMATION_MESSAGE);
                                                        } else {

                                                            Quiz thisQuiz = thisCourse.quizzes.get(index);

                                                            int i = 1;
                                                            String toPrint = "";
                                                            for (Submission s : thisQuiz.submissions) {
                                                                toPrint = toPrint + i + ":" + Submission.ReturnSubmissionBrief(s, thisQuiz) + "\n";
                                                                i += 1;
                                                            }


                                                            Submission sub;
                                                            i -= 1;

                                                            if (i == 0) {
                                                                JOptionPane.showMessageDialog(null, "No Submissions Yet", "Darkspace",
                                                                        JOptionPane.INFORMATION_MESSAGE);
                                                            } else {
                                                                int choiceNum;

                                                                while (true) {

                                                                    String choice = JOptionPane.showInputDialog(null, "Select Submission (1 to " + i + ") to View More Closely (or press 0 to exit)", "Darkspace",
                                                                            JOptionPane.QUESTION_MESSAGE);

                                                                    try {
                                                                        choiceNum = Integer.parseInt(choice);
                                                                    } catch (NumberFormatException f) {
                                                                        JOptionPane.showMessageDialog(null, "That is not a number", "Darkspace",
                                                                                JOptionPane.INFORMATION_MESSAGE);
                                                                        continue;
                                                                    }
                                                                    if (choiceNum != 0) {

                                                                        if (choiceNum < 1 || choiceNum > i) {
                                                                            JOptionPane.showMessageDialog(null, "PLease Choose a Valid Option", "Darkspace",
                                                                                    JOptionPane.INFORMATION_MESSAGE);
                                                                            continue;
                                                                        }
                                                                        if (choiceNum == 0) {
                                                                            break;
                                                                        } else {
                                                                            sub = thisQuiz.submissions.get(choiceNum - 1);
                                                                        }

                                                                        System.out.println("before");
                                                                        JOptionPane.showMessageDialog(null, Submission.ReturnSubmission(sub, thisQuiz), "Darkspace",
                                                                                JOptionPane.INFORMATION_MESSAGE);
                                                                        System.out.println("after");


                                                                        while (true) {

                                                                            choice = JOptionPane.showInputDialog(null, "Enter Score (enter -1 to ungrade): ", "Darkspace",
                                                                                    JOptionPane.QUESTION_MESSAGE);

                                                                            try {
                                                                                choiceNum = Integer.parseInt(choice);
                                                                            } catch (NumberFormatException f) {
                                                                                JOptionPane.showMessageDialog(null, "Not a number", "Darkspace",
                                                                                        JOptionPane.INFORMATION_MESSAGE);
                                                                                continue;
                                                                            }

                                                                            break;
                                                                        }

                                                                        try {
                                                                            sub.score = choiceNum;
                                                                            Course.Flush();
                                                                            break;
                                                                        } catch (IOException f) {
                                                                            JOptionPane.showMessageDialog(null, "Error", "Darkspace",
                                                                                    JOptionPane.INFORMATION_MESSAGE);
                                                                        }
                                                                    }

                                                                }


                                                            }
                                                        }

                                                    }


                                                }
                                            });


                                            viewQuizzes.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    String courseNames = "";
                                                    for (Course c : Course.LocalCourses) {
                                                        courseNames = courseNames + "Course: " + c.name + "\n";
                                                        for (Quiz q : c.quizzes) {
                                                            courseNames = courseNames + Quiz.GUIPrintQuizBrief(q) + "\n";

                                                        }
                                                    }
                                                    JOptionPane.showMessageDialog(null, courseNames, "Darkspace",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                }
                                            });
                                            viewCourses.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    String courseNames = "";
                                                    for (Course c : Course.LocalCourses) {
                                                        courseNames = courseNames + "Course: " + c.name + "\n";
                                                    }
                                                    JOptionPane.showMessageDialog(null, courseNames, "Darkspace",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                }
                                            });


                                        } else {
                                            studentMenu.setVisible(true);



                                            if(!SignUpAgainStudent) {
                                                // student menu stuff
                                                //studentPanel pane UI
                                                viewCoursesQuiz = new JButton("View Courses and Quizzes");
                                                takeQuizzes = new JButton("Take Quiz");
                                                viewSubmissions = new JButton("View Submissions/Grades");
                                                signOutStudent = new JButton("Sign Out");
                                                GridLayout studentLayout = new GridLayout(2, 2);
                                                studentMenu.setLayout(studentLayout);
                                                studentMenu.add(viewCoursesQuiz);
                                                studentMenu.add(takeQuizzes);
                                                studentMenu.add(viewSubmissions);
                                                studentMenu.add(signOutStudent);
                                                content.add(studentMenu, BorderLayout.CENTER);
                                                //studentPanel pane UI
                                                SignUpAgainStudent = true;
                                            }


                                            signOutStudent.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    studentMenu.setVisible(false);
                                                    welcomePanel.setVisible(true);

                                                }
                                            });
                                            viewCoursesQuiz.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    String courseNames = "";
                                                    for (Course c : Course.LocalCourses) {
                                                        courseNames = courseNames + "Course: " + c.name + "\n";
                                                        for (Quiz q : c.quizzes) {
                                                            courseNames = courseNames + Quiz.GUIPrintQuizBrief(q) + "\n";

                                                        }
                                                    }
                                                    JOptionPane.showMessageDialog(null, courseNames, "Darkspace",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                }
                                            });
                                            takeQuizzes.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {
                                                    try {
                                                        String courseName = JOptionPane.showInputDialog(null, "Enter Course Name: ", "Darkspace",
                                                                JOptionPane.QUESTION_MESSAGE);
                                                        int index = FindCourseIndex(courseName);

                                                        if (index == -1) {
                                                            JOptionPane.showMessageDialog(null, "No such Course Exists", "Darkspace",
                                                                JOptionPane.ERROR_MESSAGE);
                                                        } else {
                                                            Course course = Course.LocalCourses.get(index);
                                                            String quizName = JOptionPane.showInputDialog(null, "Enter Quiz Name: ", "Darkspace",
                                                                    JOptionPane.QUESTION_MESSAGE);


                                                            index = FindQuizIndexInCourse(course, quizName);

                                                            if (index == -1) {
                                                                JOptionPane.showMessageDialog(null, "No Such Quiz Exists", "Darkspace",
                                                                        JOptionPane.INFORMATION_MESSAGE);
                                                            } else {
                                                                Quiz quiz = course.quizzes.get(index);
                                                                ArrayList<Question> questionsCopy = new ArrayList<>(quiz.questions);
                                                                if (quiz.randomization)
                                                                    Collections.shuffle(questionsCopy);

                                                                ArrayList<String> answers = new ArrayList<>();
                                                                for (int i = 0; i < questionsCopy.size(); i++) {
                                                                    answers.add("");
                                                                }

                                                                for (int i = 0; i < questionsCopy.size(); i++) {
                                                                    Question thisQuestion = questionsCopy.get(i);
                                                                    int indexOfThisQuestionReally = quiz.randomization ? quiz.questions.indexOf(thisQuestion) : i;
                                                                    String answer = JOptionPane.showInputDialog(null, Question.ReturnQuestion(thisQuestion) + "\n" + "Answer (Enter Submits):", "Darkspace",
                                                                            JOptionPane.QUESTION_MESSAGE);
                                                                    answers.set(indexOfThisQuestionReally, answer);
                                                                }

                                                                Submission thisSubmission = new Submission(auth.username, answers);
                                                                quiz.submissions.add(thisSubmission);
                                                                Course.Flush();

                                                            }
                                                        }
                                                    } catch (IOException f) {
                                                        JOptionPane.showMessageDialog(null, "Error", "Darkspace",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                    }
                                                }
                                            });
                                            viewSubmissions.addActionListener(new ActionListener() {
                                                public void actionPerformed(ActionEvent e) {

                                                    for (Course c : Course.LocalCourses) {
                                                        for (Quiz q : c.quizzes) {
                                                            for (Submission s : q.submissions) {
                                                                if (s.studentID.equals(auth.username)) {
                                                                    JOptionPane.showMessageDialog(null, Submission.ReturnSubmission(s, q), "Darkspace",
                                                                            JOptionPane.INFORMATION_MESSAGE);

                                                                }
                                                            }
                                                        }
                                                    }

                                                }
                                            });


                                        }
                                    }
                                }


                            } catch (IOException f) {
                                JOptionPane.showMessageDialog(null, "A Fatal Error Occurred", "Darkspace",
                                        JOptionPane.ERROR_MESSAGE);
                                f.printStackTrace();
                            }
                        }
                    });


                }
            });

            //event handler for sign in button
            SignIn.addActionListener(new ActionListener() {
                boolean SignInAgain = false;
                boolean SignInAgainTeacher = false;
                boolean SignInAgainStudent = false;

                public void actionPerformed(ActionEvent e) {

                    welcomePanel.setVisible(false);
                    loginPanel.setVisible(true);
                    if (!SignInAgain) {
                        // loginPanel Pane UI Building
                        loginTitle = new JTextField("Welcome! Please enter your information!", 5);
                        loginUsername = new JTextField("username", 5);
                        loginPassword = new JTextField("password", 5);
                        isTeacherSignIn = new JTextField("Are you a teacher enter 1 for yes or 2 for no");
                        loginEnter = new JButton();
                        loginEnter.setText("Enter");
                        loginBack = new JButton();
                        loginBack.setText("Back");
                        BoxLayout loginLayout = new BoxLayout(loginPanel, BoxLayout.Y_AXIS);
                        // defining boxlayout
                        loginPanel.setLayout(loginLayout);
                        loginPanel.add(loginTitle);
                        loginPanel.add(loginUsername);
                        loginPanel.add(loginPassword);
                        loginPanel.add(isTeacherSignIn);
                        loginPanel.add(loginEnter);
                        loginPanel.add(loginBack);
                        content.add(loginPanel, BorderLayout.CENTER);
                        // loginPanel Pane UI Building
                    }
                    updateUI();
                    SignInAgain = true;

                    loginBack.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            loginPanel.setVisible(false);
                            welcomePanel.setVisible(true);

                        }
                    });

                    loginEnter.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            Authorization login = Authorization.GUISignIn(loginUsername.getText(),
                                    loginPassword.getText(), isTeacherSignIn.getText());

                            if (login.authorized) {
                                loginPanel.setVisible(false);
                                if (isTeacherSignIn.getText().equals("1")) {
                                    teacherMenu.setVisible(true);
                                    if(!SignInAgainTeacher) {


                                        // teacher menu stuff
                                        //teacherPanel pane UI
                                        createCourse = new JButton("Create Course");
                                        removeCourse = new JButton("Remove Course");
                                        createQuiz = new JButton("Create Quiz");
                                        editQuiz = new JButton("Edit Quiz");
                                        removeQuiz = new JButton("Remove Quiz");
                                        quizSubmissions = new JButton("View or Grade Quiz Submissions");
                                        viewQuizzes = new JButton("View Quzzies");
                                        viewCourses = new JButton("View Courses");
                                        signOut = new JButton("Sign Out");
                                        GridLayout teacherLayout = new GridLayout(3, 3);
                                        teacherMenu.setLayout(teacherLayout);
                                        teacherMenu.add(createCourse);
                                        teacherMenu.add(removeCourse);
                                        teacherMenu.add(createQuiz);
                                        teacherMenu.add(editQuiz);
                                        teacherMenu.add(removeQuiz);
                                        teacherMenu.add(quizSubmissions);
                                        teacherMenu.add(viewQuizzes);
                                        teacherMenu.add(viewCourses);
                                        teacherMenu.add(signOut);
                                        content.add(teacherMenu, BorderLayout.CENTER);
                                        //teacherPanel pane UI
                                    }
                                    SignInAgainTeacher = true;
                                        signOut.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                teacherMenu.setVisible(false);
                                                welcomePanel.setVisible(true);

                                            }
                                        });

                                        createCourse.addActionListener(new ActionListener() {

                                            public void actionPerformed(ActionEvent e) {
                                                try {
                                                    boolean isUnique = false;
                                                    String courseName = JOptionPane.showInputDialog(null, "Enter Course Name (unique): ", "Darkspace",
                                                            JOptionPane.QUESTION_MESSAGE);
                                                    if (FindCourseIndex(courseName) != -1) {
                                                        JOptionPane.showMessageDialog(null, "Course Already Exists", "Darkspace",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                    } else {
                                                        Course.LocalCourses.add(new Course(courseName));
                                                        JOptionPane.showMessageDialog(null, "Course Added!", "Darkspace",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                        Course.Flush();
                                                    }
                                                } catch (IOException f) {
                                                    JOptionPane.showMessageDialog(null, "Error", "Darkspace",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                }
                                            }
                                        });

                                        removeCourse.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                try {
                                                    String courseName = JOptionPane.showInputDialog(null, "Enter Course Name: ", "Darkspace",
                                                            JOptionPane.QUESTION_MESSAGE);
                                                    int index = FindCourseIndex(courseName);

                                                    if (index == -1) {
                                                        JOptionPane.showInputDialog(null, "No such course exists", "Darkspace",
                                                                JOptionPane.QUESTION_MESSAGE);
                                                    } else {
                                                        Course.LocalCourses.remove(index);
                                                        Course.Flush();
                                                        JOptionPane.showMessageDialog(null, "Course Removed", "Darkspace",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                    }
                                                } catch (IOException f) {
                                                    JOptionPane.showMessageDialog(null, "Error", "Darkspace",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                }

                                            }
                                        });

                                        createQuiz.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                try {
                                                    String courseName = JOptionPane.showInputDialog(null, "Enter Course Name: ", "Darkspace",
                                                            JOptionPane.QUESTION_MESSAGE);
                                                    int index = FindCourseIndex(courseName);
                                                    if (index == -1) {
                                                        JOptionPane.showInputDialog(null, "No such course exists", "Darkspace",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                    } else {
                                                        Course thisCourse = Course.LocalCourses.get(index);

                                                        int status = GUIAddQuizInLine(thisCourse, index);
                                                        if (status == 1) {
                                                            JOptionPane.showInputDialog(null, "Duplicate Quiz Already Exists", "Darkspace",
                                                                    JOptionPane.INFORMATION_MESSAGE);
                                                        } else if (status == 2) {
                                                            JOptionPane.showMessageDialog(null, "Added Quiz", "Darkspace",
                                                                    JOptionPane.INFORMATION_MESSAGE);
                                                        }
                                                    }
                                                } catch (IOException f) {
                                                    JOptionPane.showMessageDialog(null, "Error", "Darkspace",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                }
                                            }
                                        });

                                        editQuiz.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                try {
                                                    String courseName = JOptionPane.showInputDialog(null, "Enter Course Name: ", "Darkspace",
                                                            JOptionPane.QUESTION_MESSAGE);
                                                    int index = FindCourseIndex(courseName);
                                                    if (index == -1) {
                                                        JOptionPane.showMessageDialog(null, "No such course exists", "Darkspace",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                    } else {
                                                        Course thisCourse = Course.LocalCourses.get(index);


                                                        String quizName = JOptionPane.showInputDialog(null, "Enter Quiz Name: ", "Darkspace",
                                                                JOptionPane.QUESTION_MESSAGE);
                                                        int status = RemoveQuiz(thisCourse, quizName);

                                                        if (status == 0) {
                                                            JOptionPane.showMessageDialog(null, "No such quiz found in this course", "Darkspace",
                                                                    JOptionPane.INFORMATION_MESSAGE);
                                                        } else {

                                                            status = GUIAddQuizInLine(thisCourse, index);
                                                            if (status == 1) {
                                                                JOptionPane.showMessageDialog(null, "Duplicate Quiz Already Exists", "Darkspace",
                                                                        JOptionPane.INFORMATION_MESSAGE);
                                                            } else if (status == 2) {
                                                                JOptionPane.showMessageDialog(null, "Added Quiz", "Darkspace",
                                                                        JOptionPane.INFORMATION_MESSAGE);
                                                            }
                                                        }
                                                    }
                                                } catch (IOException f) {
                                                    JOptionPane.showMessageDialog(null, "Error", "Darkspace",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                }
                                            }
                                        });
                                        removeQuiz.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                try {
                                                    String courseName = JOptionPane.showInputDialog(null, "Enter Course Name (Where The Quiz Is): ", "Darkspace",
                                                            JOptionPane.QUESTION_MESSAGE);
                                                    int index = FindCourseIndex(courseName);

                                                    if (index == -1) {
                                                        JOptionPane.showMessageDialog(null, "No such course exists", "Darkspace",
                                                                JOptionPane.INFORMATION_MESSAGE);

                                                    } else {
                                                        Course thisCourse = Course.LocalCourses.get(index);

                                                        String quizName = JOptionPane.showInputDialog(null, "Enter Quiz Name: ", "Darkspace",
                                                                JOptionPane.QUESTION_MESSAGE);
                                                        int status = RemoveQuiz(thisCourse, quizName);

                                                        if (status == 0) {
                                                            JOptionPane.showMessageDialog(null, "No such quiz found", "Darkspace",
                                                                    JOptionPane.INFORMATION_MESSAGE);
                                                        } else {
                                                            JOptionPane.showMessageDialog(null, "Quiz deleted", "Darkspace",
                                                                    JOptionPane.INFORMATION_MESSAGE);
                                                        }
                                                    }
                                                } catch (IOException f) {
                                                    JOptionPane.showMessageDialog(null, "Error", "Darkspace",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                }
                                            }
                                        });

                                        quizSubmissions.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                String courseName = JOptionPane.showInputDialog(null, "Enter Course Name (Where The Quiz Is): ", "Darkspace",
                                                        JOptionPane.QUESTION_MESSAGE);
                                                int index = FindCourseIndex(courseName);

                                                if (index == -1) {
                                                    JOptionPane.showMessageDialog(null, "No such Course Exists", "Darkspace",
                                                                JOptionPane.ERROR_MESSAGE);
                                                } else {
                                                    Course thisCourse = Course.LocalCourses.get(index);
                                                    String quizName = JOptionPane.showInputDialog(null, "Enter Quiz Name: ", "Darkspace",
                                                            JOptionPane.QUESTION_MESSAGE);

                                                    index = FindQuizIndexInCourse(thisCourse, quizName);

                                                    if (index == -1) {
                                                        JOptionPane.showMessageDialog(null, "No such quiz exists. ", "Darkspace",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                    } else {

                                                        Quiz thisQuiz = thisCourse.quizzes.get(index);

                                                        int i = 1;
                                                        String toPrint = "";
                                                        for (Submission s : thisQuiz.submissions) {
                                                            toPrint = toPrint + i + ":" + Submission.ReturnSubmissionBrief(s, thisQuiz) + "\n";
                                                            i += 1;
                                                        }


                                                        Submission sub;
                                                        i -= 1;

                                                        if (i == 0) {
                                                            JOptionPane.showMessageDialog(null, "No Submissions Yet", "Darkspace",
                                                                    JOptionPane.INFORMATION_MESSAGE);
                                                        } else {
                                                            int choiceNum;

                                                            while (true) {

                                                                String choice = JOptionPane.showInputDialog(null, "Select Submission (1 to " + i + ") to View More Closely (or press 0 to exit)", "Darkspace",
                                                                        JOptionPane.QUESTION_MESSAGE);

                                                                try {
                                                                    choiceNum = Integer.parseInt(choice);
                                                                } catch (NumberFormatException f) {
                                                                    JOptionPane.showMessageDialog(null, "That is not a number", "Darkspace",
                                                                            JOptionPane.INFORMATION_MESSAGE);
                                                                    continue;
                                                                }
                                                                if (choiceNum != 0) {

                                                                    if (choiceNum < 1 || choiceNum > i) {
                                                                        JOptionPane.showMessageDialog(null, "PLease Choose a Valid Option", "Darkspace",
                                                                                JOptionPane.INFORMATION_MESSAGE);
                                                                        continue;
                                                                    }
                                                                    if (choiceNum == 0) {
                                                                        break;
                                                                    } else {
                                                                        sub = thisQuiz.submissions.get(choiceNum - 1);
                                                                    }

                                                                    JOptionPane.showMessageDialog(null, Submission.ReturnSubmission(sub, thisQuiz), "Darkspace",
                                                                            JOptionPane.INFORMATION_MESSAGE);


                                                                    while (true) {

                                                                        choice = JOptionPane.showInputDialog(null, "Enter Score (enter -1 to ungrade): ", "Darkspace",
                                                                                JOptionPane.QUESTION_MESSAGE);

                                                                        try {
                                                                            choiceNum = Integer.parseInt(choice);
                                                                        } catch (NumberFormatException f) {
                                                                            JOptionPane.showMessageDialog(null, "Not a number", "Darkspace",
                                                                                    JOptionPane.INFORMATION_MESSAGE);
                                                                            continue;
                                                                        }

                                                                        break;
                                                                    }

                                                                    try {
                                                                        sub.score = choiceNum;
                                                                        Course.Flush();
                                                                        break;
                                                                    } catch (IOException f) {
                                                                        JOptionPane.showMessageDialog(null, "Error", "Darkspace",
                                                                                JOptionPane.INFORMATION_MESSAGE);
                                                                    }
                                                                }

                                                            }


                                                        }
                                                    }

                                                }


                                            }
                                        });


                                        viewQuizzes.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                String courseNames = "";
                                                for (Course c : Course.LocalCourses) {
                                                    courseNames = courseNames + "Course: " + c.name + "\n";
                                                    for (Quiz q : c.quizzes) {
                                                        courseNames = courseNames + Quiz.GUIPrintQuizBrief(q) + "\n";

                                                    }
                                                }
                                                JOptionPane.showMessageDialog(null, courseNames, "Darkspace",
                                                        JOptionPane.INFORMATION_MESSAGE);
                                            }
                                        });
                                        viewCourses.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                String courseNames = "";
                                                for (Course c : Course.LocalCourses) {
                                                    courseNames = courseNames + "Course: " + c.name + "\n";
                                                }
                                                JOptionPane.showMessageDialog(null, courseNames, "Darkspace",
                                                        JOptionPane.INFORMATION_MESSAGE);
                                            }
                                        });


                                } else {
                                    studentMenu.setVisible(true);
                                    if(!SignInAgainStudent) {

                                        // student menu stuff
                                        //studentPanel pane UI
                                        viewCoursesQuiz = new JButton("View Courses and Quizzes");
                                        takeQuizzes = new JButton("Take Quiz");
                                        viewSubmissions = new JButton("View Submissions/Grades");
                                        signOutStudent = new JButton("Sign Out");
                                        GridLayout studentLayout = new GridLayout(2, 2);
                                        studentMenu.setLayout(studentLayout);
                                        studentMenu.add(viewCoursesQuiz);
                                        studentMenu.add(takeQuizzes);
                                        studentMenu.add(viewSubmissions);
                                        studentMenu.add(signOutStudent);
                                        content.add(studentMenu, BorderLayout.CENTER);
                                        //studentPanel pane UI
                                    }
                                    SignInAgainStudent = true;

                                    signOutStudent.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            studentMenu.setVisible(false);
                                            welcomePanel.setVisible(true);

                                        }
                                    });
                                    viewCoursesQuiz.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            String courseNames = "";
                                            for (Course c : Course.LocalCourses) {
                                                courseNames = courseNames + "Course: " + c.name + "\n";
                                                for (Quiz q : c.quizzes) {
                                                    courseNames = courseNames + Quiz.GUIPrintQuizBrief(q) + "\n";

                                                }
                                            }
                                            JOptionPane.showMessageDialog(null, courseNames, "Darkspace",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                        }
                                    });
                                    takeQuizzes.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            try {
                                                String courseName = JOptionPane.showInputDialog(null, "Enter Course Name: ", "Darkspace",
                                                        JOptionPane.QUESTION_MESSAGE);
                                                int index = FindCourseIndex(courseName);

                                                if (index == -1) {
                                                    JOptionPane.showMessageDialog(null, "No such Course Exists", "Darkspace",
                                                                JOptionPane.ERROR_MESSAGE);
                                                } else {
                                                    Course course = Course.LocalCourses.get(index);
                                                    String quizName = JOptionPane.showInputDialog(null, "Enter Quiz Name: ", "Darkspace",
                                                            JOptionPane.QUESTION_MESSAGE);


                                                    index = FindQuizIndexInCourse(course, quizName);

                                                    if (index == -1) {
                                                        JOptionPane.showMessageDialog(null, "No Such Quiz Exists", "Darkspace",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                    } else {
                                                        Quiz quiz = course.quizzes.get(index);
                                                        ArrayList<Question> questionsCopy = new ArrayList<>(quiz.questions);
                                                        if (quiz.randomization) Collections.shuffle(questionsCopy);

                                                        ArrayList<String> answers = new ArrayList<>();
                                                        for (int i = 0; i < questionsCopy.size(); i++) {
                                                            answers.add("");
                                                        }

                                                        for (int i = 0; i < questionsCopy.size(); i++) {
                                                            Question thisQuestion = questionsCopy.get(i);
                                                            int indexOfThisQuestionReally = quiz.randomization ? quiz.questions.indexOf(thisQuestion) : i;
                                                            String answer = JOptionPane.showInputDialog(null, Question.ReturnQuestion(thisQuestion) + "\n" + "Answer (Enter Submits):", "Darkspace",
                                                                    JOptionPane.QUESTION_MESSAGE);
                                                            answers.set(indexOfThisQuestionReally, answer);
                                                        }

                                                        Submission thisSubmission = new Submission(auth.username, answers);
                                                        quiz.submissions.add(thisSubmission);
                                                        Course.Flush();

                                                    }
                                                }
                                            } catch (IOException f) {
                                                JOptionPane.showMessageDialog(null, "Error", "Darkspace",
                                                        JOptionPane.INFORMATION_MESSAGE);
                                            }
                                        }
                                    });
                                    viewSubmissions.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {

                                            for (Course c : Course.LocalCourses) {
                                                for (Quiz q : c.quizzes) {
                                                    for (Submission s : q.submissions) {
                                                        if (s.studentID.equals(auth.username)) {
                                                            JOptionPane.showMessageDialog(null, Submission.ReturnSubmission(s, q), "Darkspace",
                                                                    JOptionPane.INFORMATION_MESSAGE);

                                                        }
                                                    }
                                                }
                                            }

                                        }
                                    });

                                }
                            }
                        }
                    });
                }
            });
            //welcome pane event handlers
        } catch (Exception F) {
            JOptionPane.showMessageDialog(null, "Error, cannot establish connection with host.",
                    "Darkspace",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }


    static Authorization auth = new Authorization();

    public static void main(String[] args) throws IOException {
//
//        Student.Initialise();
//        Teacher.Initialise();
//        Course.Initialise();

        SwingUtilities.invokeLater(new Application());

//        Scanner scanner = new Scanner(System.in);
//
//
//        while (!auth.authorized) {
//            System.out.println("Welcome to the Quiz app!\n");
//            System.out.println("Do you want to Sign In or Sign Up?\n[1]Sign In\n[2]Sign Up");
//            String choice = scanner.nextLine();
//
//            switch (choice) {
//                case "1":
//                    auth = Authorization.SignIn(scanner);
//                    break;
//                case "2":
//                    auth = Authorization.SignUp(scanner);
//                    break;
//                default:
//                    System.out.println("Not allowed");
//                    break;
//            }
//        }
//
//        switch (auth.type) {
//            case Student -> StudentOptions(scanner);
//            case Teacher -> TeacherOptions(scanner);
//        }
//
//        System.out.println("Signed out.");
    }


    public static int FindCourseIndex(String courseName) {
        int index = -1;

        for (int i = 0; i < Course.LocalCourses.size(); i++) {
            if (Course.LocalCourses.get(i).name.equals(courseName)) {
                index = i;
                break;
            }
        }

        return index;
    }

    public static int FindQuizIndexInCourse(Course course, String quizName) {
        int index = -1;

        for (int i = 0; i < course.quizzes.size(); i++) {
            if (course.quizzes.get(i).QuizName.equals(quizName)) {
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
        if (!file.exists()) {
            System.out.println("File " + file.getAbsolutePath() + " Does Not Exist");
            return 0;
        }

        System.out.println("Should Question Order be Random? 1 for yes and 2 for no");
        choice = scanner.nextLine();

        boolean random = choice.equals("1");

        Quiz q = new Quiz(file, random, thisCourse);
        if (q.QuizName.equals("71239")) {
            return 1;
        }
        thisCourse.quizzes.add(q);
        Course.Flush();
        return 2;
    }

    private static int GUIAddQuizFromFile(Course thisCourse, int courseIndex) throws IOException {
        String choice;
        String quizFileName = JOptionPane.showInputDialog(null, "Enter Path to Quiz File (First Line Quiz Name, Then every two consecutive lines contain questions and answer options: ", "Darkspace",
                JOptionPane.QUESTION_MESSAGE);

        File file = new File(quizFileName);
        if (!file.exists()) {
            JOptionPane.showMessageDialog(null, "File " + file.getAbsolutePath() + " Does Not Exist", "Darkspace",
                    JOptionPane.INFORMATION_MESSAGE);
            return 0;
        }

        choice = JOptionPane.showInputDialog(null, "Should Question Order be Random? 1 for yes and 2 for no", "Darkspace",
                JOptionPane.QUESTION_MESSAGE);

        boolean random = choice.equals("1");

        Quiz q = new Quiz(file, random, thisCourse);
        if (q.QuizName.equals("71239")) {
            return 1;
        }
        thisCourse.quizzes.add(q);
        Course.Flush();
        return 2;
    }


    private static int GUIAddQuizInLine(Course thisCourse, int courseIndex) throws IOException {
        String choice;

        File file = new File("quiz1.txt");
        if (!file.exists()) {
            System.out.println("File " + file.getAbsolutePath() + " Does Not Exist");
            return 0;
        }

        choice = JOptionPane.showInputDialog(null, "Should Question Order be Random? 1 for yes and 2 for no", "Darkspace",
                JOptionPane.QUESTION_MESSAGE);

        boolean random = choice.equals("1");


        String quizName = JOptionPane.showInputDialog(null, "Enter quiz name: ", "Darkspace",
                JOptionPane.QUESTION_MESSAGE);

        BufferedReader br = new BufferedReader(new FileReader(file));
        PrintWriter pw = new PrintWriter(file);

        pw.write(quizName);
        pw.println();
        pw.flush();

        int questionCount = 0;
        boolean addQuestion = true;
        String question;
        do {
            do {

                question = JOptionPane.showInputDialog(null, "Enter question:", "Darkspace",
                        JOptionPane.QUESTION_MESSAGE);

                if (question.isEmpty()) {
                    JOptionPane.showInputDialog(null, "Enter question:", "Darkspace",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    questionCount += 1;
                    break;
                }
            } while (true);

            pw.write("Q" + questionCount + ")");
            pw.write(question);
            pw.println();
            pw.flush();

            String answer;
            do {
                answer = JOptionPane.showInputDialog(null, "Enter options in the format below if " +
                                "mutliple choice or enter -1 if it is a long-answer question:\n" + "A) Option-a B) Option-b C) Option-c D) Option-d", "Darkspace",
                        JOptionPane.QUESTION_MESSAGE);

                if (answer.isEmpty()) {
                    JOptionPane.showInputDialog(null, "You cannot leave the answer field empty!", "Darkspace",
                            JOptionPane.ERROR_MESSAGE);
                } else if (answer.equals("-1")) {
//                    System.out.println("Long-answer question");
                    answer = "Subjective question";
                    break;
                } else {
                    break;
                }
            } while (true);

            pw.write(answer);
            pw.println();
            pw.flush();

            do {


                String moreQuestions = JOptionPane.showInputDialog(null, "Would you like to add another question to the quiz? (y/n)", "Darkspace",
                        JOptionPane.QUESTION_MESSAGE);

                if (moreQuestions.equalsIgnoreCase("y")) {
                    break;
                } else if (moreQuestions.equalsIgnoreCase("n")) {
                    addQuestion = false;
                    break;
                } else {
                    JOptionPane.showInputDialog(null, "Enter a valid option", "Darkspace",
                            JOptionPane.ERROR_MESSAGE);
                }
            } while (true);

        } while (addQuestion);


        Quiz q = new Quiz(file, random, thisCourse);
        if (q.QuizName.equals("71239")) {
            return 1;
        }
        thisCourse.quizzes.add(q);
        Course.Flush();
        return 2;
    }


    private static int RemoveQuiz(Course course, String quizName) throws IOException {
        int index = FindQuizIndexInCourse(course, quizName);

        if (index == -1) {
            return 0;
        }

        course.quizzes.remove(index);
        Course.Flush();
        return 1;
    }

    public static void TeacherOptions(Scanner scanner) throws IOException {
        while (auth.authorized) {
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

            switch (choice) {
                case "1":
                    boolean isUnique = false;
                    String courseName = "";

                    System.out.println("Enter Course Name (unique): ");
                    courseName = scanner.nextLine();

                    if (FindCourseIndex(courseName) != -1) {
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

                    if (index == -1) {
                        JOptionPane.showMessageDialog(null, "No such Course Exists", "Darkspace",
                                                                JOptionPane.ERROR_MESSAGE);
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

                    if (index == -1) {
                        JOptionPane.showMessageDialog(null, "No such Course Exists", "Darkspace",
                                                                JOptionPane.ERROR_MESSAGE);
                        break;
                    }

                    Course thisCourse = Course.LocalCourses.get(index);

                    int status = AddQuizFromFile(scanner, thisCourse, index);
                    if (status == 1) {
                        System.out.println("Duplicate Quiz Already Exists");
                    } else if (status == 2) {
                        System.out.println("Added Quiz");
                    }
                    break;
                case "4":
                    System.out.println("Enter Course Name (Where The Quiz Is): ");
                    courseName = scanner.nextLine();

                    index = FindCourseIndex(courseName);

                    if (index == -1) {
                        JOptionPane.showMessageDialog(null, "No such Course Exists", "Darkspace",
                                                                JOptionPane.ERROR_MESSAGE);
                        break;
                    }

                    thisCourse = Course.LocalCourses.get(index);

                    System.out.println("Enter Quiz Name: ");
                    String quizName = scanner.nextLine();
                    status = RemoveQuiz(thisCourse, quizName);

                    if (status == 0) {
                        System.out.println("No such quiz found in this course");
                        break;
                    }

                    status = AddQuizFromFile(scanner, thisCourse, index);
                    if (status == 1) {
                        System.out.println("Duplicate Quiz Already Exists");
                    } else if (status == 2) {
                        System.out.println("Added Quiz");
                    }
                    break;
                case "5":
                    System.out.println("Enter Course Name (Where The Quiz Is): ");
                    courseName = scanner.nextLine();

                    index = FindCourseIndex(courseName);

                    if (index == -1) {
                        JOptionPane.showMessageDialog(null, "No such Course Exists", "Darkspace",
                                                                JOptionPane.ERROR_MESSAGE);
                        break;
                    }

                    thisCourse = Course.LocalCourses.get(index);

                    System.out.println("Enter Quiz Name: ");
                    quizName = scanner.nextLine();
                    status = RemoveQuiz(thisCourse, quizName);

                    if (status == 0) System.out.println("No such quiz found");
                    else System.out.println("Quiz deleted");
                    break;
                case "6":
                    System.out.println("Enter Course Name: ");
                    courseName = scanner.nextLine();

                    index = FindCourseIndex(courseName);

                    if (index == -1) {
                        JOptionPane.showMessageDialog(null, "No such Course Exists", "Darkspace",
                                                                JOptionPane.ERROR_MESSAGE);
                        break;
                    }

                    thisCourse = Course.LocalCourses.get(index);

                    System.out.println("Enter Quiz Name: ");
                    quizName = scanner.nextLine();

                    index = FindQuizIndexInCourse(thisCourse, quizName);

                    if (index == -1) {
                        System.out.println("No such quiz exists. ");
                        break;
                    }

                    Quiz thisQuiz = thisCourse.quizzes.get(index);

                    int i = 1;
                    for (Submission s : thisQuiz.submissions) {
                        System.out.println(i + ":");
                        Submission.PrintSubmissionBrief(s, thisQuiz);
                        i += 1;
                        System.out.println();
                    }

                    Submission sub;
                    i -= 1;

                    if (i == 0) {
                        System.out.println("No Submissions Yet");
                        break;
                    }

                    int choiceNum;

                    while (true) {
                        System.out.println("Select Submission (1 to " + i + ") to View More Closely (or press 0 to exit)");
                        choice = scanner.nextLine();

                        try {
                            choiceNum = Integer.parseInt(choice);
                        } catch (NumberFormatException e) {
                            System.out.println("That is not a number");
                            continue;
                        }

                        if (choiceNum == 0) {
                            break;
                        }

                        if (choiceNum < 1 || choiceNum > i) {
                            System.out.println("PLease Choose a Valid Option");
                            continue;
                        }

                        break;
                    }

                    if (choiceNum == 0) {
                        break;
                    } else {
                        sub = thisQuiz.submissions.get(choiceNum - 1);
                    }

                    Submission.PrintSubmission(sub, thisQuiz);

                    while (true) {
                        System.out.println("Enter Score (enter -1 to ungrade): ");
                        choice = scanner.nextLine();

                        try {
                            choiceNum = Integer.parseInt(choice);
                        } catch (NumberFormatException e) {
                            System.out.println("Not a number");
                            continue;
                        }

                        break;
                    }

                    sub.score = choiceNum;
                    Course.Flush();
                    break;
                case "7":
                    for (Course c : Course.LocalCourses) {
                        System.out.println("Course: " + c.name);
                        for (Quiz q : c.quizzes) {
                            Quiz.PrintQuiz(q);
                            System.out.println();
                        }
                        System.out.println();
                    }
                    break;
                case "8":
                    for (Course c : Course.LocalCourses) {
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

    public static void StudentOptions(Scanner scanner) throws IOException {
        while (auth.authorized) {
            System.out.println("1. View Courses and Quizzes\n2. Submit Solutions\n3. View My Submissions\n4. Sign Out");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    for (Course c : Course.LocalCourses) {
                        System.out.println("Course: " + c.name);
                        for (Quiz q : c.quizzes) {
                            Quiz.PrintQuizBrief(q);
                            System.out.println();
                        }
                        if (c.quizzes.size() == 0) System.out.println();
                    }
                    break;
                case "2":
                    System.out.println("Enter Course Name: ");
                    String courseName = scanner.nextLine();

                    int index = FindCourseIndex(courseName);

                    if (index == -1) {
                        JOptionPane.showMessageDialog(null, "No such Course Exists", "Darkspace",
                                                                JOptionPane.ERROR_MESSAGE);
                        break;
                    }

                    Course course = Course.LocalCourses.get(index);

                    System.out.println("Enter Quiz Name: ");
                    String quizName = scanner.nextLine();

                    index = FindQuizIndexInCourse(course, quizName);

                    if (index == -1) {
                        System.out.println("No Such Quiz Exists");
                        break;
                    }

                    Quiz quiz = course.quizzes.get(index);
                    ArrayList<Question> questionsCopy = new ArrayList<>(quiz.questions);
//                    System.out.println(questionsCopy.size());
                    if (quiz.randomization) Collections.shuffle(questionsCopy);

                    ArrayList<String> answers = new ArrayList<>();
                    for (int i = 0; i < questionsCopy.size(); i++) {
                        answers.add("");
                    }

                    for (int i = 0; i < questionsCopy.size(); i++) {
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
                    for (Course c : Course.LocalCourses) {
                        for (Quiz q : c.quizzes) {
                            for (Submission s : q.submissions) {
                                if (s.studentID.equals(auth.username)) {
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



