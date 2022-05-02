**How to run the Project:**
1. Clone the repository from GitHub.
2. Open the cloned project in an editor of your choice (Example: IntelliJ)
3. Run Application.java

**Application.java**
This class is the main application for this project, and is used to get data from the user as well as display the data to the user.

**Authorization.java**
This class handles all of the Sign In and Sign Up functions. 
The SignUp method signs up a user, stores their data locally, and logs them in.
The SignIn method allows the Teacher or Student to SignIn to the application and view their menus.
The TrySignIn method checks if the sign in values that are entered by the user are valid.
This class interacts with the Student.java and Teacher.java classes to store user data and is called by the Application.java class 
whenever a user wants to either SignUp or SignIn


**Course.java**
This class handles all the Courses data, it allows the user to add new courses, and delete courses.
It interacts with the Quiz.java class to help the correspond to the right quiz.
It also interacts with the Application.java class for course manipulation or accessing a course.

**Quiz.java**
This class is used for all quiz related data.
It is used in the Application class to display quizzes, Course class for creation of new quizzes and quiz manipulation, as well as the Submission class for grading purposes.

**Question.java**
This class is used for fetching and creating questions.
It is used in the Course class for creating a new question, in the quiz class to display questions, and in the Submission class to print a brief of the submission.

**Teacher.java**
Stores login information for teachers.
This is used in the Application class for initialization and reading the existing database of teachers into memory.
Also used in the Authorization class for verifying login information, appending new data to the database as well as checking for duplicate usernames .

**Student.java**
Stores login information for students.
This is used in the Application class for initialization and reading the existing database of teachers into memory.
Also used in the Authorization class for verifying login information, appending new data to the database as well as checking for duplicate usernames.

**Ethan Thackery** - Submitted Report on Brightspace
**Ethan Thackery** - Submitted Vocareum Workspace
