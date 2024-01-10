//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Course Enrollment & Management Software
// Course:   CS 300 Fall 2023
//
// Author:   Mathom Johnson
// Email:    mgjohnson8@wisc.edu
// Lecturer: Mark Mansi
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// 
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         None
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;

/**
 * This utility class implements unit tests to check the correctness of methods defined in the
 * CourseEnrollment class of the Course Enrollment System program.
 *
 */
public class CourseEnrollmentTester {


  /**
   * Ensures the correctness of the createEmptyList() method
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean createEmptyListTester() {
    String errMsg = "Bug detected: createEmptyList did not return the expected array.";
    // Create an empty list String[][] whose capacity is 5
    String[][] actual = CourseEnrollment.createEmptyList(5);
    String[][] expected = new String[5][];
    // same as expected = new String[][]{null, null, null, null, null}

    if (!Arrays.deepEquals(actual, expected)) {
      // Recommended: descriptive error messages to help locating the bug
      System.out.println(errMsg);
      System.out.println("Expected: " + Arrays.deepToString(expected));
      System.out.println("Actual: " + Arrays.deepToString(actual));
      return false;
    }


    // Try a different capacity: create an empty list String[][] whose capacity is 8
    actual = CourseEnrollment.createEmptyList(8);
    expected = new String[8][];
    // same as expected = new String[][]{null, null, null, null, null}

    if (!Arrays.deepEquals(actual, expected)) {
      // descriptive error messages to help locating the bug
      System.out.println(errMsg);
      System.out.println("Expected: " + Arrays.deepToString(expected));
      System.out.println("Actual: " + Arrays.deepToString(actual));
      return false;
    }

    return true; // expected behavior verified, no bugs detected!
  }

  /**
   * Ensures the correctness of the indexOf(String, String[][]) method
   * 
   * Expected behavior to be verified:<BR>
   * (+) Correct index returned for multiple cases (normal and edge cases)<BR>
   * (+) No changes made to the contents of the input list<BR>
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean indexOfPerfectSizeArrayTester() {
    // Define four test cases
    // (1) edge case: match found at index 0
    // (2) edge case: match found at index length-1 considering a full input array
    // (3) normal case: match found at the middle of the input array
    // (4) normal case: no match found, -1 should be returned

    // create array that will actually be passed into methods
    String[][] actualArray = new String[][] {{"Tim", "tim@wisc.edu", "7584938329"},
        {"John", "john@wisc.edu", "7498304918"}, {"Bob", "bob@wisc.edu", "6302938470"},
        {"Gary", "gary@wisc.edu", "9382740592"}};
    // create array that will be used to check if any methods altered the first list
    String[][] expectedArray = new String[][] {{"Tim", "tim@wisc.edu", "7584938329"},
        {"John", "john@wisc.edu", "7498304918"}, {"Bob", "bob@wisc.edu", "6302938470"},
        {"Gary", "gary@wisc.edu", "9382740592"}};

    // campusID of first student in array
    if (CourseEnrollment.indexOf("7584938329", actualArray) != 0) {
      return false;
    }

    // check if actualArray was altered
    boolean isNotAltered = assessDeepEqualPerfectSizeArraysHelper("indexOfPerfectSizeArrayTester",
        actualArray, expectedArray);
    if (!isNotAltered) {
      return false;
    }

    // uses campusId of last student in the array
    if (CourseEnrollment.indexOf("9382740592", actualArray) != actualArray.length - 1) {
      return false;
    }

    // check if actualArray was altered
    isNotAltered = assessDeepEqualPerfectSizeArraysHelper("indexOfPerfectSizeArrayTester",
        actualArray, expectedArray);
    if (!isNotAltered) {
      return false;
    }


    // CampusId of student in middle of array
    if (CourseEnrollment.indexOf("6302938470", actualArray) != 2) {
      return false;
    }

    // check if actualArray was altered
    isNotAltered = assessDeepEqualPerfectSizeArraysHelper("indexOfPerfectSizeArrayTester",
        actualArray, expectedArray);
    if (!isNotAltered) {
      return false;
    }


    // campusId of student that is not in the array
    if (CourseEnrollment.indexOf("6304938440", actualArray) != -1) {
      return false;
    }

    // check if actualArray was altered
    isNotAltered = assessDeepEqualPerfectSizeArraysHelper("indexOfPerfectSizeArrayTester",
        actualArray, expectedArray);
    if (!isNotAltered) {
      return false;
    }


    return true;
  }

  /**
   * Ensures the correctness of the indexOf(String, String[][], int) method
   * 
   * Expected behavior to be verified:<BR>
   * (+) Correct index returned for multiple cases (normal and edge cases)<BR>
   * (+) No changes made to the contents of the input list<BR>
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean indexOfOversizeSizeArrayTester() {
    int size = 4;
    // create array that will actually be passed into methods
    String[][] actualArray = new String[][] {{"Tim", "tim@wisc.edu", "7584938329"},
        {"John", "john@wisc.edu", "7498304918"}, {"Bob", "bob@wisc.edu", "6302938470"},
        {"Gary", "gary@wisc.edu", "9382740592"}, null, null, null};
    // create array which will check if first array was altered by methods
    String[][] expectedArray = new String[][] {{"Tim", "tim@wisc.edu", "7584938329"},
        {"John", "john@wisc.edu", "7498304918"}, {"Bob", "bob@wisc.edu", "6302938470"},
        {"Gary", "gary@wisc.edu", "9382740592"}, null, null, null};

    // campusId of first student in the list
    if (CourseEnrollment.indexOf("7584938329", actualArray, size) != 0) {
      return false;
    }

    // check if actualArray was altered
    boolean isNotAltered = assessDeepEqualOversizeArraysHelper("indexOfOversizeSizeArrayTester",
        actualArray, expectedArray, 4, 4);
    if (!isNotAltered) {
      return false;
    }

    // campusId of last student in the array
    if (CourseEnrollment.indexOf("9382740592", actualArray, size) != size - 1) {
      return false;
    }

    // check if actualArray was altered
    isNotAltered = assessDeepEqualOversizeArraysHelper("indexOfOversizeSizeArrayTester",
        actualArray, expectedArray, 4, 4);
    if (!isNotAltered) {
      return false;
    }

    // campusId of student in the middle of the array
    if (CourseEnrollment.indexOf("6302938470", actualArray, size) != 2) {
      return false;
    }

    // check if actualArray was altered
    isNotAltered = assessDeepEqualOversizeArraysHelper("indexOfOversizeSizeArrayTester",
        actualArray, expectedArray, 4, 4);
    if (!isNotAltered) {
      return false;
    }

    // campusId of student not on the list
    if (CourseEnrollment.indexOf("6304938440", actualArray, size) != -1) {
      return false;
    }

    // check if actualArray was altered
    isNotAltered = assessDeepEqualOversizeArraysHelper("indexOfOversizeSizeArrayTester",
        actualArray, expectedArray, 4, 4);
    if (!isNotAltered) {
      return false;
    }

    return true;
  }

  // Helper method to compare actual and expected oversize roster arrays
  /**
   * Helper method defined to help verifying the actual roster and waitlist arrays with respect to
   * the expected ones
   * 
   * @param methodName     name of the method being tested
   * @param actualRoster   actual roster
   * @param expectedRoster expected roster
   * @param actualSize     actual roster size
   * @param expectedSize   expected roster size
   * 
   * @return true if expected behavior satisfied, false if any bug is detected
   */
  private static boolean assessDeepEqualOversizeArraysHelper(String methodName,
      String[][] actualRoster, String[][] expectedRoster, int actualSize, int expectedSize) {
    // error messages
    String errMsgBadSize =
        "Bug detected: Your " + methodName + "() method did not return the expected size.";
    String errMsgBadRoster = "Bug detected: The contents of the roster array was not as expected "
        + "after " + "your " + methodName + "() method returned.";

    // check roster size
    if (actualSize != expectedSize) {
      System.out.println(errMsgBadSize);
      System.out.println("Expected size: " + expectedSize + ". Actual size: " + actualSize);
      return false;
    }

    // compare roster contents
    if (!Arrays.deepEquals(actualRoster, expectedRoster)) {
      System.out.println(errMsgBadRoster);
      System.out.println("Expected Roster: " + Arrays.deepToString(expectedRoster));
      System.out.println("Actual Roster: " + Arrays.deepToString(actualRoster));
      return false;
    }

    return true; // expected behavior satisfied, no bugs detected
  }

  // Helper method to compare actual and expected oversize roster arrays
  /**
   * Helper method defined to help verifying the actual roster and waitlist arrays with respect to
   * the expected ones
   * 
   * @param methodName       name of the method being tested
   * @param actualWaitlist   actual waitlist
   * @param expectedWaitlist expected waitlist
   * 
   * @return true if expected behavior satisfied, false if any bug is detected
   */
  private static boolean assessDeepEqualPerfectSizeArraysHelper(String methodName,
      String[][] actualWaitlist, String[][] expectedWaitlist) {
    // error message
    String errMsgBadWaitlist =
        "Bug detected: The contents of the waitlist array was not as expected after " + "your "
            + methodName + "() method returned";

    // compare waitlist contents
    if (!Arrays.deepEquals(actualWaitlist, expectedWaitlist)) {
      System.out.println(errMsgBadWaitlist);
      System.out.println("Expected Waitlist: " + Arrays.deepToString(expectedWaitlist));
      System.out.println("Actual Waitlist: " + Arrays.deepToString(actualWaitlist));
      return false;
    }
    return true; // expected behavior satisfied, no bugs detected
  }

  /**
   * Ensures the correctness of the enrollOneStudent() method when called to enroll one student
   * record in the course. The course did not reach its capacity and the course pre-requisites are
   * satisfied.
   * 
   * Expected behavior to be verified:<BR>
   * (+) Student record correctly added to the end of the roster array<BR>
   * (+) No changes made to the waitlist array<BR>
   * (+) Correct size returned
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean enrollOneStudentTester() {
    // You do not need to make changes to this method
    // create a waitlist array. We can consider a normal case: not-empty and not-full waitlist)
    String[][] actualWaitlist = new String[][] {{"Andy", "andy@wisc.edu", "9087654321"},
        {"Lilly", "lilly@wisc.edu", "9807645321"}, null, null};

    // No changes to the waitlist are expected
    String[][] expectedWaitlist = new String[][] {{"Andy", "andy@wisc.edu", "9087654321"},
        {"Lilly", "lilly@wisc.edu", "9807645321"}, null, null};

    // This method considers three test cases:
    // (1) edge case: adding to an empty roster
    // (2) normal case: adding to the end of a non-empty roster
    // (3) edge case: after adding the student record, the roster is full

    // To avoid code redundancy, we defined a helper method named verifyCorrectBehaviorHelper() to
    // help verifying the expected behavior for each of the above test cases.

    // --------------------------------------------------------------------------
    // (1) edge case Enroll a student considering an empty roster oversize array
    // enroll one student satisfying prerequisites
    // Create an empty roster
    String[][] actualRoster = new String[3][];
    int actualSize = 0;

    // Try to enroll George
    actualSize = CourseEnrollment.enrollOneStudent("George", "george@wisc.edu", "9780563421", true,
        actualRoster, actualSize, actualWaitlist);
    // expected roster and its size
    String[][] expectedRoster =
        new String[][] {{"George", "george@wisc.edu", "9780563421"}, null, null};
    int expectedSize = 1;
    boolean resultCase1 = assessDeepEqualOversizeArraysHelper("enrollOneStudent", actualRoster,
        expectedRoster, actualSize, expectedSize);

    // --------------------------------------------------------------------------
    // (2) normal case: adding to the end of a non-empty roster
    actualRoster = new String[][] {{"George", "george@wisc.edu", "9780563421"},
        {"Lilly", "lilly@wisc.edu", "9807645321"}, null, null};
    actualSize = 2;

    // Try to enroll Matt
    actualSize = CourseEnrollment.enrollOneStudent("Matt", "matt@wisc.edu", "9745632180", true,
        actualRoster, actualSize, actualWaitlist);
    // expected roster and its size
    expectedRoster = new String[][] {{"George", "george@wisc.edu", "9780563421"},
        {"Lilly", "lilly@wisc.edu", "9807645321"}, {"Matt", "matt@wisc.edu", "9745632180"}, null};
    expectedSize = 3;
    boolean resultCase2 = assessDeepEqualOversizeArraysHelper("enrollOneStudent", actualRoster,
        expectedRoster, actualSize, expectedSize);

    // --------------------------------------------------------------------------
    // (3) edge case: after adding the student record, the roster is full
    actualRoster = new String[][] {{"George", "george@wisc.edu", "9780563421"},
        {"Lilly", "lilly@wisc.edu", "9807645321"}, {"Matt", "matt@wisc.edu", "9745632180"}, null};
    actualSize = 3;

    // Try to enroll Lisa
    actualSize = CourseEnrollment.enrollOneStudent("Lisa", "lisa@wisc.edu", "9784563211", true,
        actualRoster, actualSize, actualWaitlist);
    // expected roster and its size
    expectedRoster = new String[][] {{"George", "george@wisc.edu", "9780563421"},
        {"Lilly", "lilly@wisc.edu", "9807645321"}, {"Matt", "matt@wisc.edu", "9745632180"},
        {"Lisa", "lisa@wisc.edu", "9784563211"}};
    expectedSize = 4;
    boolean resultCase3 = assessDeepEqualOversizeArraysHelper("enrollOneStudent", actualRoster,
        expectedRoster, actualSize, expectedSize);

    // Verify that all the above enrollOneStudent() method calls did not make any changes to the
    // contents of the input waitlist
    boolean assessWaitlistContents = assessDeepEqualPerfectSizeArraysHelper("enrollOneStudent",
        actualWaitlist, expectedWaitlist);

    // test passes only if each of the defined test scenarios passes
    return resultCase1 && resultCase2 && resultCase3 && assessWaitlistContents;
  }


  /**
   * Ensures the correctness of the enrollOneStudent() method when called to enroll one student
   * record in the course. The student record is in the waitlist, course pre-requisites are
   * satisfied, and there is room in the roster to enroll the student in the course.
   * 
   * Expected behavior to be verified:<BR>
   * (+) Student record correctly added to the end of the roster array<BR>
   * (+) Matching student correctly record removed off the waitlist<BR>
   * (+) Correct size returned
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean enrollOneStudentMoveFromWaitlistTester() {
    int size = 0;
    String[][] roster = CourseEnrollment.createEmptyList(3);
    String[][] waitlist = CourseEnrollment.createEmptyList(3);

    // add student to waitlist
    CourseEnrollment.addWaitlist("John", "john@wisc.edu", "0123456789", true, waitlist);

    // make sure the student added is in the waitlist at the first index
    if (CourseEnrollment.indexOf("0123456789", waitlist) != 0) {
      return false;
    }

    // enroll the student that is on the waitlist
    size = CourseEnrollment.enrollOneStudent("John", "john@wisc.edu", "0123456789", true, roster,
        size, waitlist);

    // make sure size of roster is updated
    if (size != 1) {
      return false;
    }

    // make sure that the student is now in the roster at the first index
    if (CourseEnrollment.indexOf("0123456789", roster, size) != 0) {
      return false;
    }

    // make sure student was removed from the waitlist
    if (CourseEnrollment.indexOf("0123456789", waitlist) != -1) {
      return false;
    }

    return true;
  }



  /**
   * Ensures the correctness of the dropCourse() method when called to remove an existing student
   * record from a course enrollment roster of the class.
   * 
   * Expected behavior to be verified:<BR>
   * (+) Matching student record correctly removed off the input roster array. Order of precedence
   * of the student records must be preserved.<BR>
   * (+) Correct size returned
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean successfulDropCourseTester() {
    int size = 4;
    String[][] actualArray = new String[][] {{"Tim", "tim@wisc.edu", "7584938329"},
        {"John", "john@wisc.edu", "7498304918"}, {"Bob", "bob@wisc.edu", "6302938470"},
        {"Gary", "gary@wisc.edu", "9382740592"}, null, null, null};

    // dropping student from roster at index 1
    size = CourseEnrollment.dropCourse("7498304918", actualArray, size);

    // make sure size is updated
    if (size != 3) {
      return false;
    }

    // make sure that student is gone from the roster
    if (CourseEnrollment.indexOf("7498304918", actualArray, size) != -1) {
      return false;
    }

    // make sure that student at index 0 remains there and that student at the index after the
    // removed student is shifted up one spot
    if (CourseEnrollment.indexOf("7584938329", actualArray, size) != 0
        || CourseEnrollment.indexOf("6302938470", actualArray, size) != 1) {
      return false;
    }

    return true;
  }

  /**
   * Ensures the correctness of the dropCourse() method when called to remove a non-existing student
   * record from a course enrollment roster of the class.
   * 
   * Expected behavior to be verified:<BR>
   * (+) No changes made to the input roster array: Fail to find a matching student record .<BR>
   * (+) Correct size returned (same size passed as input to the method)
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean unsuccessfulDropCourseTester() {
    int size = 4;
    String[][] actualArray = new String[][] {{"Tim", "tim@wisc.edu", "7584938329"},
        {"John", "john@wisc.edu", "7498304918"}, {"Bob", "bob@wisc.edu", "6302938470"},
        {"Gary", "gary@wisc.edu", "9382740592"}, null, null, null};
    String[][] expectedArray = new String[][] {{"Tim", "tim@wisc.edu", "7584938329"},
        {"John", "john@wisc.edu", "7498304918"}, {"Bob", "bob@wisc.edu", "6302938470"},
        {"Gary", "gary@wisc.edu", "9382740592"}, null, null, null};

    // campusId of student not in the course is being dropped
    size = CourseEnrollment.dropCourse("7489388820", actualArray, size);

    // check if actualArray was altered
    boolean isNotAltered = assessDeepEqualOversizeArraysHelper("indexOfOversizeSizeArrayTester",
        actualArray, expectedArray, 4, 4);
    if (!isNotAltered) {
      return false;
    }

    return true;
  }


  /**
   * Runs all the tester methods defined in this class.
   * 
   * @return true if no bugs are detected.
   */
  public static boolean runAllTests() {
    boolean createEmptyListTesterResult = createEmptyListTester();
    System.out
        .println("createEmptyListTester: " + (createEmptyListTesterResult ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------");
    boolean indexOfOversizeSizeArrayTesterResult = indexOfOversizeSizeArrayTester();
    System.out.println("indexOfOversizeSizeArrayTester: "
        + (indexOfOversizeSizeArrayTesterResult ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------");
    boolean indexOfPerfectSizeArrayTesterResult = indexOfPerfectSizeArrayTester();
    System.out.println("indexOfPerfectSizeArrayTester: "
        + (indexOfPerfectSizeArrayTesterResult ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------");
    boolean enrollOneStudentTesterResult = enrollOneStudentTester();
    System.out
        .println("enrollOneStudentTester: " + (enrollOneStudentTesterResult ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------");
    boolean enrollOneStudentMoveFromWaitlistTesterResult = enrollOneStudentMoveFromWaitlistTester();
    System.out.println("enrollOneStudentMoveFromWaitlistTester: "
        + (enrollOneStudentMoveFromWaitlistTesterResult ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------");
    boolean successfulDropCourseTesterResult = successfulDropCourseTester();
    System.out.println(
        "successfulDropCourseTester: " + (successfulDropCourseTesterResult ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------");
    boolean unsuccessfulDropCourseTesterResult = unsuccessfulDropCourseTester();
    System.out.println("unsuccessfulDropCourseTester: "
        + (unsuccessfulDropCourseTesterResult ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------");

    return createEmptyListTesterResult && indexOfOversizeSizeArrayTesterResult
        && indexOfPerfectSizeArrayTesterResult && enrollOneStudentTesterResult
        && enrollOneStudentMoveFromWaitlistTesterResult && successfulDropCourseTesterResult
        && unsuccessfulDropCourseTesterResult;
  }

  /**
   * Main method to run this tester class.
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("-----------------------------------------------");
    System.out.println("runAllTests: " + (runAllTests() ? "Pass" : "Failed!"));
  }

}
