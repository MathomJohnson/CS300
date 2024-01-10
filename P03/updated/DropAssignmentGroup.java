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
 * of a student's grade. When calculating the point values for this type of 
 * assignment group, the lowest N scores are dropped, which is facilitated 
 * using a pair of static utility methods.
 */
import java.util.ArrayList;

public class DropAssignmentGroup {
  
  private ArrayList<SimpleAssignment> assignments;
  private int numDropped;
  public final double PERCENT_OF_TOTAL;
  
  public DropAssignmentGroup(double percent, int drops) {
    assignments = new ArrayList<SimpleAssignment>();
    PERCENT_OF_TOTAL = percent;
    if (drops < 1) {
      numDropped = 1;
    }
    else {
      numDropped = drops;
    }
  }
  
  /**
   * Accesses the number of assignments currently stored in this 
   * DropAssignmentGroup, NOT accounting for drops
   * 
   * @return the number of assignments present in this DropAssignmentGroup
   */
  public int getNumAssignments() {
    return assignments.size();
  }

  /**
   * Without modifying the input ArrayList, creates a NEW ArrayList which 
   * contains all but the lowest- scoring N (numDropped) assignments from 
   * the input ArrayList. If the input ArrayList contains N (numDropped) 
   * or fewer assignments, the returned ArrayList will be empty.
   * 
   * @param assignments     an ArrayList containing all assignments
   * @param n               the number of assignments to drop
   * @return a COPY of the input ArrayList which contains all but the 
   * lowest-scoring n (NOT numDropped) assignments
   */
  public static ArrayList<SimpleAssignment> dropLowestN(ArrayList<SimpleAssignment> assignments, 
      int n) {
    ArrayList<SimpleAssignment> copy = new ArrayList<SimpleAssignment>();
    
    if (n > assignments.size()) {
      return copy;
    }
    
    for (int k = 0; k < assignments.size(); k++) {
      copy.add(assignments.get(k));
    }
    
    for (int i = 0; i < n; i++) {
      copy.remove(getLowestScoreIndex(copy));
    }
    
    return copy;
  }
  
  /**
   * Accesses the total number of earned points across all assignments in this DropAssignmentGroup, 
   * after dropping the lowest N (numDropped)
   * 
   * @return the sum of all earned points of all non-dropped assignments in this DropAssignmentGroup
   */
  public double getPoints() {  
    ArrayList<SimpleAssignment> tempArr = dropLowestN(assignments, numDropped);
    double earnedPoints = 0;
    for (int i = 0; i < tempArr.size(); i++) {
      earnedPoints += tempArr.get(i).getPoints();
    }
    
    return earnedPoints;
  }
  
  /**
   * Accesses the total number of points possible across all assignments in this DropAssignmentGroup, 
   * after dropping the lowest N (numDropped). Be careful - not all assignments in this group are 
   * required to have the same number of points possible.
   * 
   * @return the sum of all possible points of all non-dropped assignments in this DropAssignmentGroup
   */
  public int getTotalPossible() {
    ArrayList<SimpleAssignment> tempArr = dropLowestN(assignments, numDropped);
    int total = 0;
    
    for (int i = 0; i < tempArr.size(); i++) {
      total += tempArr.get(i).POINTS_POSSIBLE;
    }
    return total;
  }
  
  /**
   * Determines whether all assignments currently in this DropAssignmentGroup have been completed, 
   * after dropping the lowest N (numDropped).
   * 
   * @return true if ALL non-dropped assignments in this DropAssignmentGroup have been completed; 
   * false otherwise
   */
  public boolean isComplete() {
    ArrayList<SimpleAssignment> tempArr = dropLowestN(assignments, numDropped);

    for (int i = 0; i < tempArr.size(); i++) {
      if (!tempArr.get(i).isComplete()) {
        return false;
      }
    }
    return true;
  }
  
  /**
   * Retrieves an assignment at the given index in the DropAssignmentGroup, NOT accounting for drops
   * 
   * @param i       the index of the assignment to access (0-based)
   * @return the assignment at the given index, or null if the index is out of bounds
   */
  public SimpleAssignment getAssignment(int i) {
    return assignments.get(i);
  }
  
  /**
   * Adds a single assignment object to this DropAssignmentGroup
   * 
   * @param assignment      the SimpleAssignment to add
   */
  public void addAssignment(SimpleAssignment assignment) {
    assignments.add(assignment);
  }
  
  /**
   * Adds a batch of assignments to this DropAssignmentGroup in the order they appear
   * 
   * @param assignmentBatch       a perfect-size array of SimpleAssignments to add; 
   * you may assume there are no null values present in this array
   */
  public void addAssignments(SimpleAssignment[] assignmentBatch) {
    for (int i = 0; i < assignmentBatch.length; i++) {
      assignments.add(assignmentBatch[i]);
    }
  }
  
  /**
   * Finds the index of the lowest scoring assignment in the given ArrayList. 
   * In the case of ties, this method should prefer the assignment with the lower index. 
   * No other form of tie-breaking (e.g. points possible, completeness, etc) should be implemented.
   * 
   * @param assignments          an ArrayList containing the assignments to analyze
   * @return the index (0-based) of the lowest scoring assignment
   */
  public static int getLowestScoreIndex(ArrayList<SimpleAssignment> assignments) {
    double lowest = assignments.get(0).getPoints();
    int dropIndex = 0;
    
    for (int j = 1; j < assignments.size(); j++) {
      if (assignments.get(j).getPoints() < lowest) {
        lowest = assignments.get(j).getPoints();
        dropIndex = j;
      }
    }
    
    return dropIndex;
  }
  
  /**
   * Creates a String representation of this DropAssignmentGroup. 
   * Each assignment is listed by number (1-based) and its String representation.
   * 
   * @return a String containing all of the non-dropped assignments in this DropAssignmentGroup
   */
  public String toString() {
    ArrayList<SimpleAssignment> tempArr = dropLowestN(assignments, numDropped);

    String str = "";
    for (int i = 0; i < tempArr.size(); i++) {
      str += (i + 1) + ". " + tempArr.get(i).toString() + ", ";
    }
    return str;
  }
}
