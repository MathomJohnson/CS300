//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Exceptional Course Enrollment tester class
// Course:   CS 300 Fall 2023
//
// Author:   Mathom Johnson
// Email:    mgjohnson8@wisc.edu
// Lecturer: Mark Mansi
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Ojasvini Sharma
// Partner Email:   osharma5@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   X Write-up states that pair programming is allowed for this assignment.
//   X We have both read and understand the course Pair Programming Policy.
//   X We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;

/**
 * This utility class implements unit tests to check the correctness of methods defined in the
 * ExceptionalCourseEnrollment class of the Exceptional Course Enrollment System program.
 *
 */
public class ExceptionalCourseEnrollmentTester {

  /**
   * Ensures the correctness of the StudentRecord.equals() method.
   * 
   * Defines at least two StudentRecord objects and checks for the following test cases:<BR>
   * (1) StudentRecord.equals() is expected to return true when passed a StudentRecord with the same
   * campusID as the current one. You can compare a student record to itself.<BR>
   * (2) StudentRecord.equals() is expected to return false when passed a StudentRecord with
   * campusID different from the campusID of the current student record. (3) StudentRecord.equals()
   * is expected to return false when passed a String as input (4) StudentRecord.equals() is
   * expected to return false when passed the reference null as input
   * 
   * 
   * @return true if and only if the tester verifies a correct functionality and false if at least
   *         one bug is detected
   */
  public static boolean studentRecordEqualsTester() {
    try{
    //creating two cases: student1 and student2
    StudentRecord student1 = new StudentRecord("Ojasvini Sharma", "osharma5@wisc.edu", "1234567890", false);
    StudentRecord student2 = new StudentRecord("Mathom Johnson", "mathom@wisc.edu", "9098765724", true);

    //testing if the .equals returns true, if two records with same campusID are compared
    boolean test1 = student1.equals(student1);

    //testing if .equals returns false when two different records are compared
    boolean test2 = student1.equals(student2);

    //test when string is passed as an input
    boolean test3 = student1.equals("matthew");

    //test when null reference is passed as an input
    boolean test4 = student1.equals(null);

    //checking if all the tests resulted as expected
    if(test1 && !test2 && !test3 && !test4){
      return true;
    }
  }

  catch(DataFormatException e){
    }

    System.out.println("studenRecordEqualsTester : failed");
    return false; 
  }

  /**
   * Ensures the correctness of the constructor of the StudentRecord class when called with VALID
   * input
   * 
   * @return true if and only if the tester verifies a correct functionality and false if at least
   *         one bug is detected
   */
  public static boolean studentRecordConstructorSuccessful() {
    try{
    StudentRecord student = new StudentRecord("Ojasvini Sharma", "ojtab2@wisc.edu", "1234567890", true);

    //if no exception is thrown 
      return true;
    }

  catch(DataFormatException e){
    return false;
  }

   catch(Exception e){
      return false;
    } 
    
  }

  /**
   * Ensures the correctness of the constructor of the StudentRecord class when called with one
   * INVALID input
   * 
   * @return true if and only if the tester verifies a correct functionality and false if at least
   *         one bug is detected
   */
  public static boolean studentRecordConstructorUnSuccessful() {
    try{
    StudentRecord student = new StudentRecord("Mathom", "mathomwisc.edu","1234323456", false);

    //if exception is not thrown with bad input in the constructor
    return false;
    }

    catch(DataFormatException e){
      return true;
    }

    catch(Exception e){
      return false;
    } 
  }

  /**
   * Ensures the correctness of the searchById() method
   * 
   * Creates an ArrayList which contains at least 2 student records, and defines at least two cases:
   * 
   * (1) successful search<BR>
   * (2) unsuccessful search<BR>
   * 
   * 
   * @throws NoSuchElementException if the search result is not found
   * @return true if and only if the tester verifies a correct functionality and false if at least
   *         one bug is detected
   */
  public static boolean searchByIdTester() {
    String errMsg = "Bug detected: search did not return the expected result.";
    try {
      // Create an arraylist which contains 3 student records
      ArrayList<StudentRecord> records = new ArrayList<StudentRecord>();
      StudentRecord s1 = new StudentRecord("Rob", "rob@wisc.edu", "1234567890", true);
      StudentRecord s2 = new StudentRecord("Joey", "joey@wisc.edu", "1233367890", true);
      StudentRecord s3 = new StudentRecord("NotHere", "no@wisc.edu", "1111167890", true);
      records.add(s1);
      records.add(s2);

      // Finds a student in the arraylist

      StudentRecord r1 = ExceptionalCourseEnrollment.searchById(s1.getCampusID(), records);
      if (r1 != s1) {
        return false;
      }
      // Does'nt find a student not in the array
      try {
        ExceptionalCourseEnrollment.searchById(s3.getCampusID(), records);
        return false; // a NoSuchElementException was not thrown as expected
      } catch (NoSuchElementException e) {
        // check for the error message
        String expectedErrorMessage = "No student record found!";
        if (!e.getMessage().equals(expectedErrorMessage)) {
          System.out
              .println("The NoSuchElementException did not contain the expected error message!");
          return false;
        }
      }

    } catch (Exception e) {
      System.out.println(errMsg);
      return false;
    }


    try {

    } catch (Exception e) {
      return true;
    }
    return true;

  }

  /**
   * Runs all the tester methods defined in this class.
   * 
   * @return true if no bugs are detected.
   */
  public static boolean runAllTests() {
    boolean searchTesterOutput = searchByIdTester();
    System.out.println("searchTester: " + (searchTesterOutput ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------");
    boolean studentRecordEqualsTesterOutput = studentRecordEqualsTester();
    System.out.println(
        "studentRecordEqualsTester: " + (studentRecordEqualsTesterOutput ? "Pass" : "Failed!"));

    System.out.println("-----------------------------------------------");
    boolean studentRecordConstructorSuccessfulOutput = studentRecordConstructorSuccessful();
    System.out.println("studentRecordConstructorSuccessful: "
        + (studentRecordConstructorSuccessfulOutput ? "Pass" : "Failed!"));
    
    System.out.println("-----------------------------------------------");
    boolean studentRecordConstructorUnSuccessfulOutput = studentRecordConstructorUnSuccessful();
    System.out.println("studentRecordConstructorUnSuccessful: "
        + (studentRecordConstructorUnSuccessfulOutput ? "Pass" : "Failed!"));
    System.out.println("-----------------------------------------------");
    return searchTesterOutput && studentRecordEqualsTesterOutput
        && studentRecordConstructorSuccessfulOutput;
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
