//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P03 Grade Policies
// Course:   CS 300 Fall 2023
//
// Author:   Mathom Johnson
// Email:    mgjohnson8@wisc.edu
// Lecturer: Mark Mansi
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         None
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This tester class tests methods from the SimpleAssignment class making sure 
 * that each method produces the correct output.
 */
public class SimpleAssignmentTester {
  
  /**
   * This method tests the constructors of the SimpleAssignment class, 
   * as well as the getPoints() and isComplete() methods.
   * 
   * @return true if all tests are passed; false otherwise.
   */
  public static boolean testAccessors() {
    SimpleAssignment mathHomework = new SimpleAssignment(50);
    SimpleAssignment csHomework = new SimpleAssignment(95, true);
    
    if (mathHomework.getPoints() != 0 || mathHomework.isComplete() != false 
        || !mathHomework.toString().equals("*/50")) {
      return false;
    }
    else if (csHomework.getPoints() != 0 || csHomework.isComplete() != false 
        || !csHomework.toString().equals("*/95")) {
      return false;
    }
    
    return true;
  }
  
  /**
   * This method groups together many test methods in this class regarding the 
   * awardBonus() method from SimpleAssignment.
   * 
   * @return true if all methods return true; false otherwise.
   */
  public static boolean testSimpleBonus() {
    boolean result = testAwardBonus();
    result &= testBonusTwice();
    result &= testNoBonus();
    result &= testBonus105();
    
    return result;
  }
  
  /**
   * Tests that a completed assignment that scores less than 95% has the correct bonus value 
   * added to it when awardBonus() is called
   * 
   * @return true if the test is passed; false otherwise
   */
  private static boolean testAwardBonus() {
    SimpleAssignment cs = new SimpleAssignment(100, true);
    
    cs.complete(91);
    cs.awardBonus();
    
    if (cs.getPoints() != 96) {
      return false;
    }
    return true;
  }
  
  /**
   * Verifies that calling the awardBonus() method a second time does not modify the earned 
   * points result
   * 
   * @return true if test passes; false otherwise.
   */
  private static boolean testBonusTwice() { 
    SimpleAssignment cs = new SimpleAssignment(100, true);
    
    cs.complete(91);
    cs.awardBonus();
    cs.awardBonus();

    if (cs.getPoints() != 96) {
      return false;
    }
    return true;
  }
  
  /**
   * Verifies that calling the awardBonus() method on an assignment with NO bonus available 
   * does NOT result in a bonus being applied to the earned points result
   * 
   * @return true is test passes; false otherwise.
   */
  private static boolean testNoBonus() {
    SimpleAssignment cs = new SimpleAssignment(100);
    
    cs.complete(93);
    cs.awardBonus();
    
    if (cs.getPoints() != 93) {
      return false;
    }
    return true;
  }
  
  /**
   * Verifies that calling the awardBonus() method on an assignment whose earned points are > 95% 
   * of possible points does NOT result in a score that exceeds the total possible points
   * 
   * @return true if test passes; false otherwise.
   */
  private static boolean testBonus105() {
    SimpleAssignment cs = new SimpleAssignment(100, true);
    
    cs.complete(97);
    cs.awardBonus();
    
    if (cs.getPoints() != 100) {
      return false;
    }
    return true;
  }
  
  /**
   * This method calls a number of shorter helper methods, all of which test error cases
   * in the SimpleAssignment class.
   * 
   * @return true if all tests pass; false otherwise.
   */
  public static boolean testSimpleErrorCases() {
    boolean result = testBadConstructorInput();
    result &= testBonusIncomplete();
    result &= testBadPointsEarned();
    result &= testCompleteAssignmentTwice();
    
    return result;
  }
  
  /**
   * Tests the SimpleAssignment constructor with bad input
   * 
   * @return true if test passes; false otherwise.
   */
  private static boolean testBadConstructorInput() {
    SimpleAssignment cs = new SimpleAssignment(-4);
    SimpleAssignment cs1 = new SimpleAssignment(0, true);

    if (cs.POINTS_POSSIBLE != 1 || cs1.POINTS_POSSIBLE != 1) {
      return false;
    }
    return true;
  }
  
  /**
   * Tests the awardBonus() method on an assignment that has bonus available but is not
   * yet completed
   * 
   * @return true if test passes; false otherwise.
   */
  private static boolean testBonusIncomplete() {
    SimpleAssignment cs = new SimpleAssignment(100, true);
    
    cs.awardBonus();
    
    if (cs.getPoints() != 0) {
      return false;
    }
    return true;
  }
  
  /**
   * Tests the complete() method with input values outside of the allowed range and make
   * sure that the points returned are what you expect for the given error case
   * 
   * @return true if test passes; false otherwise.
   */
  private static boolean testBadPointsEarned() {
    SimpleAssignment cs = new SimpleAssignment(100, true);
    SimpleAssignment cs1 = new SimpleAssignment(100, true);

    cs.complete(120);
    cs1.complete(-4);
    
    if (cs.getPoints() != 100 || cs1.getPoints() != 0) {
      return false;
    }
    return true;
  }
  
  /**
   * Test calling complete() twice with different values, and make sure that the earned point
   * value is NOT updated after the assignment has been completed
   * 
   * @return true if test passes; false otherwise.
   */
  private static boolean testCompleteAssignmentTwice() {
    SimpleAssignment cs = new SimpleAssignment(100, true);

    cs.complete(98);
    cs.complete(93);
    
    if (cs.getPoints() != 98) {
      return false;
    }
    return true;
  }
  
  /**
   * This method reports whether all provided SimpleAssignmentTester methods
   * have passed.
   * 
   * @return true if all testers are passes; false otherwise.
   */
  public static boolean runAllTests() {
    return testAccessors() && testSimpleBonus() && testSimpleErrorCases();
  }

  public static void main(String[] args) {
    System.out.println("basic: "+(testAccessors()?"PASS":"fail"));
    System.out.println("bonus: "+(testSimpleBonus()?"PASS":"fail"));
    System.out.println("edge cases: "+(testSimpleErrorCases()?"PASS":"fail"));
  }

}
