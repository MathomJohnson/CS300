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
 * 
 * This class models a basic assignment with points possible, points earned, 
 * and an optional bonus of 5% of possible points.
 * 
 */
import java.util.Random;

public class SimpleAssignment {

  private boolean bonusAvailable;
  private boolean isComplete;
  public final int POINTS_POSSIBLE;
  private double pointsEarned;
  
  public SimpleAssignment(int points) {
    this(points, false);
  }
  
  public SimpleAssignment(int points, boolean bonus) {
    if (points < 1) {
      POINTS_POSSIBLE = 1;
    }
    else {
      POINTS_POSSIBLE = points;
    }
    bonusAvailable = bonus;
    isComplete = false;
  }
  
  /**
   * Returns the number of points earned on the assignment. 
   * If the assignment has not yet been completed, returns 0.
   * 
   * @return the number of points earned on the assignment
   */
  public double getPoints() {
    if (isComplete) {
      return pointsEarned;
    }
    return 0;
  }
  
  /**
   * Reports whether this assignment has been completed yet
   *
   * @return true if this assignment has been completed, false otherwise
   */
  public boolean isComplete() {
    return isComplete;
  }
  
  /**
   * Completes the assignment and records the provided score. 
   * If this assignment has already been completed, 
   * this method does nothing.
   * 
   * @param score: the score to record for this assignment. 
   * If less than 0, recorded score is 0. 
   * If greater than the number of points possible, 
   * recorded score is the number of points possible.
   */
  public void complete(double score) {
    if (!isComplete) {
      isComplete = true;
      if (score < 0) {
        pointsEarned = 0;
      }
      else if (score > POINTS_POSSIBLE) {
        pointsEarned = POINTS_POSSIBLE;
      }
      else {
        pointsEarned = score;
      }
    }
  }
  
  /**
   * If the assignment has been completed and there is a bonus available, 
   * adds 5% of possible points to the earned points total, 
   * up to a maximum of the number of possible points. 
   * Once the bonus has been recorded, it is no longer available.
   */
  public void awardBonus() {
    if (isComplete && bonusAvailable) {
      double newPointsTotal = pointsEarned + (POINTS_POSSIBLE * 0.05);
      
      if (newPointsTotal > POINTS_POSSIBLE) {
        pointsEarned = POINTS_POSSIBLE;
      }
      else {
        pointsEarned = newPointsTotal;
      }
      
      bonusAvailable = false;
    }
  }
  
  /**
   * Creates and returns a String representation of this SimpleAssignment. 
   * This includes the score as a number of points earned out of points possible, 
   * or an asterisk out of points possible if the assignment has not yet been 
   * completed.
   * 
   * @return a String containing the score, 
   * e.g. "45.0/50" or a star indicating the assignment is not yet complete, 
   * e.g. "asterisk/50"
   */
  public String toString() {
    if (isComplete) {
      return pointsEarned + "/" + POINTS_POSSIBLE;
    }
    return "*/" + POINTS_POSSIBLE;
  }
  
  /**
  * Creates random assignment scores with a mean of 80% and a standard
  * deviation
  * of 15%.
  * @param n the number of assignment scores to create
  * @param maxScore the maximum score value to create
  * @return an array of the SimpleAssignments created under those requirements
  */
  public static SimpleAssignment[] makeRandomAssignments(int n, int maxScore) {
    Random rand = new Random();
    SimpleAssignment[] result = new SimpleAssignment[n];
    double mean = 0.80;
    double std = 0.15;
    
    for (int i=0; i<n; i++) {
      double pctScore = rand.nextGaussian(mean, std);
      result[i] = new SimpleAssignment(maxScore);
      result[i].complete(pctScore*maxScore);
    }
    
    return result;
  }
}
