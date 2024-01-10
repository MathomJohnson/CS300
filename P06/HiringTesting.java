//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Hiring tester class
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
import java.util.Random;

public class HiringTesting {
  /**
   *  responsible for testing the greedyHiring method in the case where
   *  no recursive calls are made
   * @return true of the test passes and false if it fails
   */
  public static boolean greedyHiringBaseTest() {
	CandidateList hired = new CandidateList();
	boolean t = true; boolean f = false;
	boolean[][] availabilities = {{t, f, f, f, t, f}, {t, t, f, t, t, f}, {f, f, f, t, t, t}};
	CandidateList candidates = HiringTestingUtilities.makeCandidateList(availabilities);
	
    if (Hiring.greedyHiring(candidates, hired, 0) != hired) {
      return false;
    }
    if (Hiring.greedyHiring(new CandidateList(), hired, 2) != hired) {
      return false;
    }
    return true;
  }
  
  /**
   * responsible for testing the
   * greedyHiring method in the case where recursive calls will be made
   * @return
   */
  public static boolean greedyHiringRecursiveTest() {
	boolean t = true; boolean f = false;
	  
	boolean[][] availabilities = {{t, f, f, t, t, t}, {t, f, f, f, t, f}, {f, f, t, t, f, t}};
		
	CandidateList candidates = HiringTestingUtilities.makeCandidateList(availabilities);
	    
	CandidateList expected = new CandidateList();
	expected.add(candidates.get(0));
	expected.add(candidates.get(2));
    
    CandidateList actual = Hiring.greedyHiring(candidates, new CandidateList(), 2);
    if (HiringTestingUtilities.compareCandidateLists(expected, actual)) {
      return true;
    }
    else {
      return false;
    }
  }
  
/**
 * responsible for testing the optimalHiring method in the case where
 * no recursive calls are made
 * @return true if the test passes
 */
  public static boolean optimalHiringBaseTest() {
	CandidateList hired = new CandidateList();
	boolean t = true; boolean f = false;
    boolean[][] availabilities = {{t, f, f, f, t, f}, {t, t, f, t, t, f}, {f, f, f, t, t, t}};
	CandidateList candidates = HiringTestingUtilities.makeCandidateList(availabilities);
		
	if (Hiring.optimalHiring(candidates, hired, 0) != hired) {
	  return false;
	}
	if (Hiring.optimalHiring(new CandidateList(), hired, 2) != hired) {
	  return false;
	}
	return true;
  }
  
  /**
   * responsible for testing the
   * optimalHiring method in the case where recursive calls will be made
   * @return
   */
  public static boolean optimalHiringRecursiveTest() {
	boolean t = true; boolean f = false;
	  
	boolean[][] availabilities = {{t, f, f, t, t, t}, {t, t, f, f, t, f}, {f, f, t, t, f, t}};
	
    CandidateList candidates = HiringTestingUtilities.makeCandidateList(availabilities);
    
    CandidateList expected = new CandidateList();
    expected.add(candidates.get(1));
    expected.add(candidates.get(2));

	ArrayList<CandidateList> actual = HiringTestingUtilities.allOptimalSolutions(candidates, 2);
	for (CandidateList list : actual) {
	  if (HiringTestingUtilities.compareCandidateLists(expected, list)) {
		  return true;
	  }
	}
	return false;
  }
  
  /**
  * Using many random inputs allows you to exercise a bunch of the different code paths in your
  * implementation  
  * @return true if the test passes
   */
  public static boolean optimalHiringFuzzTest() {
	Random randGen = new Random();
	for (int i = 0; i < 150; i++) {
	  int numHours = 1 + randGen.nextInt(5);
	  int numCandidates = 1 + randGen.nextInt(10);
	  int numHires = 1 + randGen.nextInt(numCandidates);
	  CandidateList candidates = HiringTestingUtilities.generateRandomInput(numHours, numCandidates);
	  CandidateList actual = Hiring.optimalHiring(candidates, new CandidateList(), numHires);
	  ArrayList<CandidateList> expected = HiringTestingUtilities.allOptimalSolutions(candidates, numHires);
	  
	  if (HiringTestingUtilities.compareCandidateLists(expected, actual)) {
	    return true;
	  }
	}
	return false;
  }
  /**
   * The minCoverageHiringBaseTest method is responsible for testing the minCoverageHiring method in the
case where no recursive calls are made
   * @return true if the test passes
   */
  public static boolean minCoverageHiringBaseTest() {
	CandidateList hired = new CandidateList();
	if (Hiring.minCoverageHiring(new CandidateList(), hired, 5) != hired) {
	  return false;
	}
	
	boolean t = true; boolean f = false;
	boolean[][] availabilities = {{t, t, f, f, t, f}, {f, f, t, t, f, t}};
	boolean[][] hiredAvailabilities = {{t, f, f, t, t, f}, {f, t, t, f, f, f}};
    CandidateList candidates = HiringTestingUtilities.makeCandidateList(availabilities);
	CandidateList hires = HiringTestingUtilities.makeCandidateList(hiredAvailabilities);
	
	
	if (Hiring.minCoverageHiring(candidates, hires, 5) != hires) {
	  return false;
	}
	
	return true;
  }
  
  /**
   * minCoverageHiringRecursiveTest method is responsible
   * for testing the minCoverageHiring method in the case where recursive calls will be made
   * @return
   */
  public static boolean minCoverageHiringRecursiveTest() {
	boolean t = true; boolean f = false;
	boolean[][] availabilities = {{t, f, f, t, t, t}, {t, t, f, f, t, f}, {f, f, t, t, f, t}, {f, t, t, f, f, f}};
	int[] payrates = {2, 5, 6, 3};
	
	CandidateList candidates = HiringTestingUtilities.makeCandidateList(availabilities, payrates);
	
	CandidateList expected = new CandidateList();
	expected.add(candidates.get(0));
	expected.add(candidates.get(3));
	
	ArrayList<CandidateList> actual = HiringTestingUtilities.allMinCoverageSolutions(candidates, 6);
	
	if (HiringTestingUtilities.compareCandidateLists(actual, expected)) {
	  return true;
	}
	return false;
  }
  /**
  * The minCoverageHiringFuzzTest method tests your implementation using random inputs and compares
  * the output against an iterative reference implementation.  
  * @return true if the test passes
  */
  public static boolean minCoverageHiringFuzzTest() {
	Random randGen = new Random();
    for (int i = 0; i < 150; i++) {
      int numHours = 1 + randGen.nextInt(5);
      int numCandidates = 1 + randGen.nextInt(10);
      int minHours = 1 + randGen.nextInt(numHours);
      
      CandidateList candidates = HiringTestingUtilities.generateRandomInput(numHours, numCandidates);
      CandidateList hired = new CandidateList(); // Initialize the hired list

      CandidateList actual = Hiring.minCoverageHiring(candidates, hired, minHours);

      ArrayList<CandidateList> expected = HiringTestingUtilities.allMinCoverageSolutions(candidates, minHours);
      
      for (CandidateList list : expected) {
        if (HiringTestingUtilities.compareCandidateLists(list, actual)) {
          return true;
        }
      }
    }
    return false;
  }
  
  public static void main(String[] args) {
    System.out.println(greedyHiringRecursiveTest());
    System.out.println(greedyHiringBaseTest());
    System.out.println(optimalHiringBaseTest());
    System.out.println(optimalHiringRecursiveTest());
    System.out.println(optimalHiringFuzzTest());
    System.out.println(minCoverageHiringBaseTest());
    System.out.println(minCoverageHiringRecursiveTest());
    System.out.println(minCoverageHiringFuzzTest());
  }
 
}

