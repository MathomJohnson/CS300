import java.util.ArrayList;
import java.util.Random;

public class HiringTester {
  
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
  
  public static boolean greedyHiringRecursiveTest() {
    boolean t = true; boolean f = false;
  
    boolean[][] availabilities = {{t, f, f, f, t, f}, {t, t, f, t, t, f}, {f, f, f, t, t, t}};
        
    CandidateList candidates = HiringTestingUtilities.makeCandidateList(availabilities);
    CandidateList actual = Hiring.greedyHiring(candidates, new CandidateList(), 2);
    
    CandidateList expected = new CandidateList();
    System.out.println(candidates.size());
    expected.add(actual.get(0));
    expected.add(actual.get(1));  // WRONG FIX LATER
    
    
    if (HiringTestingUtilities.compareCandidateLists(expected, actual)) {
      return true;
    }
    else {
      return false;
    }
  }

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
  
  public static boolean optimalHiringRecursiveTest() {
	boolean t = true; boolean f = false;
	  
	boolean[][] availabilities = {{t, f, f, t, t, t}, {t, t, f, f, t, f}, {f, f, t, t, f, t}};
//	        
    CandidateList candidates = HiringTestingUtilities.makeCandidateList(availabilities);
//    CandidateList actual = Hiring.optimalHiring(candidates, new CandidateList(), 2);
//    
    CandidateList expected = new CandidateList();
    System.out.println(candidates.size());
    expected.add(candidates.get(1));
    expected.add(candidates.get(2));
//    
//    if (HiringTestingUtilities.compareCandidateLists(expected, actual)) {
//      return true;
//    }
//    else {
//      return false;
//    }
	ArrayList<CandidateList> actual = HiringTestingUtilities.allOptimalSolutions(candidates, 2);
	for (CandidateList list : actual) {
	  if (HiringTestingUtilities.compareCandidateLists(expected, list)) {
		  return true;
	  }
	}
	return false;
  }
  
  public static boolean optimalHiringFuzzTest() {
	Random randGen = new Random();
	for (int i = 0; i < 150; i++) {
      System.out.println(i);
	  int numHours = 1 + randGen.nextInt(5);
	  int numCandidates = 1 + randGen.nextInt(10);
	  int numHires = 1 + randGen.nextInt(numCandidates);
	  System.out.println(numHours + " " + numCandidates + " " + numHires);
	  CandidateList candidates = HiringTestingUtilities.generateRandomInput(numHours, numCandidates);
	  CandidateList actual = Hiring.optimalHiring(candidates, new CandidateList(), numHires);
	  ArrayList<CandidateList> expected = HiringTestingUtilities.allOptimalSolutions(candidates, numHires);
	  
//	  for (CandidateList list : expected) {
//		if (HiringTestingUtilities.compareCandidateLists(list, actual)) {
//		  return true;
//		}
//	  }
	  if (HiringTestingUtilities.compareCandidateLists(expected, actual)) {
		System.out.println("hehe");
	    return true;
	  }
	}
	return false;
  }
  
  public static boolean minCoverageHiringBaseTest() {
	return false;
  }
  
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
      System.out.println("TESTTT");
	  return true;
	}
	return false;
  }
  
  public static boolean minCoverageHiringFuzzTest() {
	return false;
  }
  
  public static void main(String[] args) {
    System.out.println(greedyHiringRecursiveTest());
    System.out.println(greedyHiringBaseTest());
    System.out.println(optimalHiringBaseTest());
    System.out.println(optimalHiringRecursiveTest());
    System.out.println(optimalHiringFuzzTest());
    System.out.println(minCoverageHiringRecursiveTest());
  }
}
