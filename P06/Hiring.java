//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Hiring class
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

public class Hiring {

  /**
   * Given a set of `candidates` that we can hire, a list of candidates we've already hired, and a
   * maximum number of tas to hire, return the set of hires made using a greedy strategy that always
   * chooses the candidate that increases hours covered the most. In this function, we will ignore
   * pay rates.
   * 
   * @param candidates - the set of available qcandidates to hire from (excluding those already
   *                   hired)
   * @param hired      - the list of those currently hired
   * @pram hiresLeft - the maximum number of candidates to hire
   * @return a list of hired candidates
   */
  public static CandidateList greedyHiring(CandidateList candidates, CandidateList hired, int hiresLeft) {
    if (hiresLeft < 1 || candidates.size() < 1) { //base case
      return hired;
    }
    
    int bestHours = 0; //variable that stores the value of most hours covered 
    Candidate bestCandidate = null; //most suitable candidate to hire
  
    for (Candidate c : candidates) {
      CandidateList testList = hired.withCandidate(c);
      if (testList.numCoveredHours() > bestHours) {
        bestHours = testList.numCoveredHours();
        bestCandidate = c;
      }
    }
    
    hired.add(bestCandidate); //updating the candidates and hired list after hiring
    candidates.remove(bestCandidate);
    hiresLeft -= 1;
    return greedyHiring(candidates, hired, hiresLeft); //recursive call
  }
  
   /**
   * Given a set of `candidates` that we can hire, a list of candidates we've already hired, and a
   * maximum number of tas to hire, return the set of hires that maximizes number of scheduled
   * hours. In this function, we will ignore pay rates.
   * 
   * @param candidates - the set of available candidates to hire from (excluding those already
   *                   hired)
   * @param hired      - the list of those currently hired
   * @param hiresLeft  - the maximum number of candidates to hire
   * @return a list of hired candidates
   */
  public static CandidateList optimalHiring(CandidateList candidates, CandidateList hired, int hiresLeft) {
    if (hiresLeft < 1 || candidates.size() < 1) {//base case
      return hired;
    }
    
    CandidateList optimal = new CandidateList();
    int bestHours = 0;
    for (Candidate c : candidates) { //iterating the loop for all candidates
      CandidateList newHired = hired.withCandidate(c);
      CandidateList newCandidates = candidates.withoutCandidate(c);
      CandidateList result = optimalHiring(newCandidates, newHired, hiresLeft - 1); //recursive call
      int totalHours = result.numCoveredHours();
      
      //checking if there is a better possible candidate that we can hire
      if (totalHours > bestHours) {
    	bestHours = totalHours;
    	optimal = result;
      }
    }
    return optimal;
  }
  
  /**
   * Knapsack dual problem: find the minimum-budget set of hires to achieve a threshold number of
   * hours. That is, given a set of candidates, a set of already-hired candidates, and a minimum
   * number of hours we want covered, what is the cheapest set of candidates we can hire that cover
   * at least that minimum number of hours specified.
   * 
   * @param candidates - the set of available candidates to hire from (excluding those already
   *                   hired)
   * @param hired      - the set of candidates already hired
   * @param minHours   - the minimum number of hours we want to cover total
   * @return a list of hired candidates or null if no set of candidates achieves the requested
   *         number of hours
   */
  public static CandidateList minCoverageHiring(CandidateList candidates, CandidateList hired,
		int minHours) {
	if (hired.numCoveredHours() == minHours || candidates.size() == 0) {
	  return hired;
	}
	

    ArrayList<CandidateList> meetsHours = new ArrayList<CandidateList>();

	int bestPrice = 5000;
	int idxBestPrice = 0;
	
	for (Candidate c : candidates) {
	  CandidateList newHired = hired.withCandidate(c);
	  CandidateList newCandidates = candidates.withoutCandidate(c);
	  CandidateList result = minCoverageHiring(newCandidates, newHired, minHours);
	  int totalHours = result.numCoveredHours();
	  int totalCost = result.totalCost();
	  if (totalHours >= minHours) {
	    meetsHours.add(result);
	    if (totalCost < bestPrice) {
	      bestPrice = totalCost;
	      idxBestPrice = meetsHours.indexOf(result);
	    }
	  } else {
		return null;
	  }
	  
	}
	return meetsHours.get(idxBestPrice);
  }
}
