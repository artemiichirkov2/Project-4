import org.junit.Test;
import org.junit.After;
import java.lang.reflect.Field;
import org.junit.Assert;
import org.junit.Before;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.*;
import static org.hamcrest.CoreMatchers.containsString;

import static org.junit.Assert.*;

/**
 * A framework to run public test cases.
 *
 * <p>Purdue University -- CS18000 -- Spring 2022</p>
 *
 * @author Purdue CS
 * @version January 10, 2022
 */
public class RunLocalTest {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestCase.class);
        if (result.wasSuccessful()) {
            System.out.println("Excellent - Test ran successfully");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

    public static class TestCase {
        private final InputStream originalInput = System.in;
        private final PrintStream originalOutput = System.out;
        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayInputStream testIn;
        private final String TA_ERROR_MESSAGE = "You bumped into an error! Please contact a TA immediately.";
        private ByteArrayOutputStream testOut;

        @Before
        public void outputStart() {
            testOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOut));
        }

        @Test(timeout = 1000)
        public void logInTest() {

            String input = "1\n1\nethan\nthackery\n";

            receiveInput(input);

            try {
                Application.main(new String[0]);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            String out = getOutput();

            String expectedFull = "Welcome to the Quiz app!\n\nDo you want to Sign In or Sign Up?\n" +
                    "[1]Sign In\n[2]Sign Up\nSign In\n\nAre you a Teacher or a Student?\n[1]Teacher\n" +
                    "[2]Student\nEnter your username\nEnter your password\nSuccess!\n\nMenu\n\n[1]Change Password" +
                    "\n[2]Edit Username\n[3]Delete Account\n[4]Sign Out\n[5]Continue to your courses\n";

            assertEquals("different than expected", expectedFull, out);

        }

//        @Test(timeout = 1000)
//        public void logInTest() {
//
//            String input = "1\n1\nethan\nthackery\n";
//
//            receiveInput(input);
//
//            try {
//                Application.main(new String[0]);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//            String out = getOutput();
//
//            String expectedFull = "Welcome to the Quiz app!\n\nDo you want to Sign In or Sign Up?\n" +
//                    "[1]Sign In\n[2]Sign Up\nSign In\n\nAre you a Teacher or a Student?\n[1]Teacher\n" +
//                    "[2]Student\nEnter your username\nEnter your password\nSuccess!\n\nMenu\n\n[1]Change Password" +
//                    "\n[2]Edit Username\n[3]Delete Account\n[4]Sign Out\n[5]Continue to your courses\n";
//
//            assertEquals("different than expected", expectedFull, out);
//
//        }



        /**
         * UTILITY METHODS BELOW
         */

        private void receiveInput(String str) {
            testIn = new ByteArrayInputStream(str.getBytes());
            System.setIn(testIn);
        }

        private String getOutput() {
            return testOut.toString();
        }

        @After
        public void restoreInputAndOutput() {
            System.setIn(originalInput);
            System.setOut(originalOutput);
        }
    }
}

