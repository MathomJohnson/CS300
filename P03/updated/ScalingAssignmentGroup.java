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
 * A wrapper class for an AssignmentGroup object, this class models a 
 * collection of scores which are worth a given percentage of a student's grade. 
 * When calculating the point values for this type of assignment group, points 
 * are scaled to a given percentage of possible points, and everything over that 
 * cap is considered full credit.
 */
public class ScalingAssignmentGroup {

   private AssignmentGroup group;
   public final double PERCENT_OF_TOTAL;
   private double scalingFactor;
   
   public ScalingAssignmentGroup(double percent, double scale) {
     PERCENT_OF_TOTAL = percent;
     scalingFactor = scale;
     group = new AssignmentGroup(percent);
   }
   
   /**
    * Retrieves the total earned points of the assignments in this group, 
    * and returns either the actual number of earned points or, if the total 
    * is higher than the scaled points possible, returns the maximum number of 
    * points possible.
    * 
    * @return the number of earned points across all assignments in this group, 
    * which cannot exceed the number of possible points when accounting for the 
    * scalingFactor
    */
   public double getPoints() {
     double scaledTotalPossible = getTotalPossible();
     double earnedPoints = group.getPoints();
     
     if (earnedPoints > scaledTotalPossible) {
       return scaledTotalPossible;
     }
     return earnedPoints;
   }
   
   /**
    * Retrieves the total number of possible points of the assignments in this group, 
    * multiplied by this ScalingAssignmentGroup's scalingFactor value. That is, 
    * if there are 100 points possible with a scalingFactor of .8, this method 
    * would return the value 80.
    * 
    * @return the total number of points possible, scaled by the scalingFactor
    */
   public double getTotalPossible() {
     return group.getTotalPossible() * scalingFactor;
   }
   
   /**
    * Reports whether all assignments in this group are completed
    * 
    * @return true if all assignments in this group are completed, false otherwise
    */
   public boolean isComplete() {
     return group.isComplete();
   }
   
   /**
    * Accesses the number of assignments currently stored in this 
    * ScalingAssignmentGroup
    * 
    * @return the number of assignments present in this ScalingAssignmentGroup
    */
   public int getNumAssignments() {
     return group.getNumAssignments();
   }
   
   /**
    * Retrieves an assignment at the given index in the ScalingAssignmentGroup
    * 
    * @param i          the index of the assignment to access (0-based)
    * @return the assignment at the given index, or null if the index is out of bounds
    */
   public SimpleAssignment getAssignment(int i) {
     return group.getAssignment(i);
   }
   
   /**
    * Adds a single assignment object to this ScalingAssignmentGroup
    * 
    * @param assignment        the SimpleAssignment to add
    */
   public void addAssignment(SimpleAssignment assignment) {
     group.addAssignment(assignment);
   }
   
   /**
    * Adds a batch of assignments to this ScalingAssignmentGroup in the order 
    * they appear
    * 
    * @param assignmentBatch      a perfect-size array of SimpleAssignments to add; 
    * you may assume there are no null values present in this array
    */
   public void addAssignments(SimpleAssignment[] assignmentBatch) {
     group.addAssignments(assignmentBatch);
   }
   
   /**
    * Creates a String representation of this ScalingAssignmentGroup. 
    * This is given as a percent of possible points, calculated as 
    * getPoints()/getTotalPossible(). 
    * For any total earned point value over the ceiling percent of 
    * total possible points, this will be 1.
    * 
    * @return a String containing the percent of points earned for this 
    * ScalingAssignmentGroup, scaled by the given scalingFactor
    */
   public String toString() {
     return getPoints() + "/" + getTotalPossible();
   }
}
