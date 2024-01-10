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
 * This tester class tests methods from the AssignmentGroup, DropAssignmentGroup 
 * and ScalingAssignmentGroup classes making sure they produce the correct output.
 */
public class AssignmentGroupTester {

  /**
   * Uses the getNumAssignments() and getAssignment(i) accessor methods
   * to verify that the addAssignment() method works properly in AssignmentGroup
   * 
   * @return true if test passes; false otherwise.
   */
  public static boolean testAddOneAssignment() {
    AssignmentGroup cs = new AssignmentGroup(80);
    
    SimpleAssignment p01 = new SimpleAssignment(15);
    SimpleAssignment p02 = new SimpleAssignment(30, true);
    
    cs.addAssignment(p01);
    cs.addAssignment(p02);
    
    if (cs.getNumAssignments() != 2) {
      return false;
    }
    else if (cs.getAssignment(0) != p01 || cs.getAssignment(1) != p02) {
      return false;
    }
    return true;
  }
  
  /**
   * Uses the getNumAssignments() and getAssignment(i) accessor methods
   * to verify that the addAssignments() method works properly in AssignmentGroup
   * 
   * @return true if test passes; false otherwise.
   */
  public static boolean testAddManyAssignments() {
    AssignmentGroup cs = new AssignmentGroup(80);

    SimpleAssignment p01 = new SimpleAssignment(15);
    SimpleAssignment p02 = new SimpleAssignment(15);
    SimpleAssignment p03 = new SimpleAssignment(15);

    SimpleAssignment[] batch = {p01, p02, p03};
    
    cs.addAssignments(batch);
    
    if (cs.getNumAssignments() != 3) {
      return false;
    }
    if (cs.getAssignment(0) != p01 || cs.getAssignment(1) != p02 
        || cs.getAssignment(2) != p03) {
      return false;
    }
   
    return true;
  }
  
  /**
   * Verifies that the getTotalPossible() method returns the expected value
   * in EACH of the classes which implements the method
   * 
   * @return true if all tests pass; false otherwise.
   */
  public static boolean testGetTotal() {
    boolean result = testGroupTotal();
    result &= testDropTotal();
    result &= testScaledTotal();
    
    return result;
  }
  
  /**
   * Verifies that getTotalPossible() works as expected in AssignmentGroup
   * 
   * @return true if test passes; false otherwise.
   */
  private static boolean testGroupTotal() {
    AssignmentGroup cs = new AssignmentGroup(80);

    SimpleAssignment p01 = new SimpleAssignment(20);
    SimpleAssignment p02 = new SimpleAssignment(15);
    SimpleAssignment p03 = new SimpleAssignment(10);

    SimpleAssignment[] batch = {p01, p02, p03};
    
    cs.addAssignments(batch);

    int total = cs.getTotalPossible();
    
    if (total != 45) {
      return false;
    }
    return true;
  }
  
  /**
   * Verifies that getTotalPossible() works as expected in AssignmentGroup
   * 
   * @return true if test passes; false otherwise.
   */
  private static boolean testDropTotal() {
    DropAssignmentGroup group = new DropAssignmentGroup(80, 2);
      
    SimpleAssignment p01 = new SimpleAssignment(20);
    SimpleAssignment p02 = new SimpleAssignment(15);
    SimpleAssignment p03 = new SimpleAssignment(10);

    p01.complete(18);
    p02.complete(10);
    p03.complete(10);
    
    SimpleAssignment[] batch = {p01, p02, p03};
    
    group.addAssignments(batch);
    
    if (group.getTotalPossible() != 20) {
      return false;
    }
   
    return true;
  }
  
  /**
   * Verifies that getTotalPossible() works as expected in ScalingAssignmentGroup
   * 
   * @return true if test passes; false otherwise.
   */
  private static boolean testScaledTotal() {
    ScalingAssignmentGroup group = new ScalingAssignmentGroup(80, .6);
    
    SimpleAssignment p01 = new SimpleAssignment(20);
    SimpleAssignment p02 = new SimpleAssignment(15);
    SimpleAssignment p03 = new SimpleAssignment(10);

    p01.complete(18);
    p02.complete(10);
    p03.complete(10);
    
    SimpleAssignment[] batch = {p01, p02, p03};
    
    group.addAssignments(batch);
    
    if (group.getTotalPossible() != 27) {
      return false;
    }
    return true;
  }
  
  /**
   * Verifies that the getPoints() method returns the expected value in EACH
   * of the classes which implements the method
   * 
   * @return true if all tests pass; false otherwise.
   */
  public static boolean testGetPoints() {
    boolean result = testGroupPoints();
    result &= testDropPoints();
    result &= testScaledPoints();
    
    return result;
  }
  
  /**
   * Verifies that getPoints() works as expected in AssignmentGroup
   * 
   * @return true if test passes; false otherwise.
   */
  private static boolean testGroupPoints() {
    AssignmentGroup cs = new AssignmentGroup(80);

    SimpleAssignment p01 = new SimpleAssignment(20);
    SimpleAssignment p02 = new SimpleAssignment(15);
    SimpleAssignment p03 = new SimpleAssignment(10);
    
    p01.complete(20);
    p02.complete(10);
    p03.complete(10);

    SimpleAssignment[] batch = {p01, p02, p03};
    
    cs.addAssignments(batch);
    
    if (cs.getPoints() != 40) {
      return false;
    }
    
    return true;
  }
  
  /**
   * Verify that getPoints() works as expected in DropAssignmentGroup
   * 
   * @return true if test passes; false otherwise.
   */
  private static boolean testDropPoints() {
    DropAssignmentGroup group = new DropAssignmentGroup(80, 2);
    
    SimpleAssignment p01 = new SimpleAssignment(20);
    SimpleAssignment p02 = new SimpleAssignment(15);
    SimpleAssignment p03 = new SimpleAssignment(10);

    p01.complete(18);
    p02.complete(10);
    p03.complete(10);
    
    SimpleAssignment[] batch = {p01, p02, p03};
    
    group.addAssignments(batch);
    
    if (group.getPoints() != 18) {
      return false;
    }
    
    return true;
  }
  
  /**
   * Verifies that getPoints() works as expected in ScalingAssignmentGroup
   * 
   * @return true if test passes; false otherwise.
   */
  private static boolean testScaledPoints() {
    ScalingAssignmentGroup group = new ScalingAssignmentGroup(80, .6);
    
    SimpleAssignment p01 = new SimpleAssignment(20);
    SimpleAssignment p02 = new SimpleAssignment(15);
    SimpleAssignment p03 = new SimpleAssignment(10);

    p01.complete(18);
    p02.complete(10);
    p03.complete(10);
    
    SimpleAssignment[] batch = {p01, p02, p03};
    
    group.addAssignments(batch);
    
    if (group.getPoints() != 27) {
      return false;
    }
    return true;
  }
  
  /**
   * Verifies that the isComplete() accessor method works as expected in AssignmentGroup
   * 
   * @return true if test passes; false otherwise.
   */
  public static boolean testComplete() {
    AssignmentGroup group = new AssignmentGroup(80);
    
    SimpleAssignment p01 = new SimpleAssignment(20);
    SimpleAssignment p02 = new SimpleAssignment(15);
    SimpleAssignment p03 = new SimpleAssignment(10);

    p01.complete(18);
    p02.complete(10);
    p03.complete(10);
    
    SimpleAssignment[] batch = {p01, p02, p03};
    
    group.addAssignments(batch);
    
    if (group.isComplete()) {
      return true;
    }
    return false;
  }
  
  public static void main(String[] args) {
    if (SimpleAssignmentTester.runAllTests()) {
      System.out.println("add one: "+(testAddOneAssignment()?"PASS":"fail"));
      System.out.println("add many: "+(testAddManyAssignments()?"PASS":"fail"));
      System.out.println("get total: "+(testGetTotal()?"PASS":"fail"));
      System.out.println("get points: "+(testGetPoints()?"PASS":"fail"));
      System.out.println("complete: "+(testComplete()?"PASS":"fail"));
    }
    else {
      System.out.println("Your SimpleAssignment implementation does NOT pass its tests!\n"
          + "Do NOT continue with AssignmentGro until you have passed all SimpleAssignment tests.");
    }
  }
  
}
