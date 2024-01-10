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
 * This class models a collection of scores which are worth a given percentage 
 * of a student's grade. No adjustments are made to these scores.
 */
import java.util.ArrayList;

public class AssignmentGroup {

  private ArrayList<SimpleAssignment> assignments;
  public final double PERCENT_OF_TOTAL;
  
  public AssignmentGroup(double percent) {
    assignments = new ArrayList<SimpleAssignment>();
    PERCENT_OF_TOTAL = percent;
  }
  
  /**
   * Accesses the number of assignments currently stored in this AssignmentGroup
   * 
   * @return the number of assignments present in this AssignmentGroup
   */
  public int getNumAssignments() {
    return assignments.size();
  }
  
  /**
   * Accesses the total number of earned points across all assignments in 
   * this AssignmentGroup
   * 
   * @return the sum of all earned points of all assignments in this 
   * AssignmentGroup
   */
  public double getPoints() {
    double total = 0;
    for (int i = 0; i < assignments.size(); i++) {
      total += assignments.get(i).getPoints();
    }
    return total;
  }
  
  /**
   * Accesses the total number of points possible across all assignments 
   * in this AssignmentGroup. Be careful - not all assignments in this 
   * group are required to have the same number of points possible.
   * 
   * @return the sum of all possible points of all assignments in this 
   * AssignmentGroup
   */
  public int getTotalPossible() {
    int total = 0;
    for (int i = 0; i < assignments.size(); i++) {
      total += assignments.get(i).POINTS_POSSIBLE;
    }
    return total;
  }
  
  /**
   * Determines whether all assignments currently in this AssignmentGroup 
   * have been completed.
   * 
   * @return true if ALL assignments in this AssignmentGroup have been completed; 
   * false otherwise
   */
  public boolean isComplete() {
    for (int i = 0; i < assignments.size(); i++) {
      if (!assignments.get(i).isComplete()) {
        return false;
      }
    }
    return true;
  }
  
  /**
   * Retrieves an assignment at the given index in the AssignmentGroup
   * 
   * @param i     the index of the assignment to access (0-based)
   * @return the assignment at the given index, or null if the index 
   * is out of bounds
   */
  public SimpleAssignment getAssignment(int i) {
    try {
      return assignments.get(i);
    }
    catch (IndexOutOfBoundsException e) {
      return null;
    }
  }
  
  /**
   * Adds a single assignment object to this AssignmentGroup
   * 
   * @param assignment     the SimpleAssignment to add
   */
  public void addAssignment(SimpleAssignment assignment) {
    assignments.add(assignment);
  }
  
  /**
   * Adds a batch of assignments to this AssignmentGroup in the order 
   * they appear
   * 
   * @param assignmentBatch     a perfect-size array of SimpleAssignments 
   * to add; you may assume there are no null values present in this array
   */
  public void addAssignments(SimpleAssignment[] assignmentBatch) {
    for (int i = 0; i < assignmentBatch.length; i++) {
      if (assignmentBatch[i] != null) {
        assignments.add(assignmentBatch[i]);
      }
    }
  }
  
  /**
   * Creates a String representation of this AssignmentGroup. 
   * Each assignment is listed by number (1-based) and its String representation.
   * 
   * @return a String containing all of the assignments in this AssignmentGroup
   */
  public String toString() {
    String str = "";
    for (int i = 0; i < assignments.size(); i++) {
      str += (i + 1) + ". " + assignments.get(i).toString() + ", ";
    }
    return str;
  }
}
