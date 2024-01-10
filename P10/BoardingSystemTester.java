//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Airplane Boarding System
// Course:   CS 300 Fall 2023
//
// Author:   Mathom Johnson
// Email:    mgjohnson8@wisc.edu
// Lecturer: Mark Mansi
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         None
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;

/**
 * This is a Utility class which implements tester methods to ensure the correctness of the
 * implementation of the main operations defined in cs300 fall 2023 p10 Airplane Boarding System.
 *
 */
public class BoardingSystemTester {


  /**
   * Ensures the correctness of Passenger.compareTo() method when called to compare two Passenger
   * objects having different boarding groups.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPassengerCompareToDifferentGroup() {
    // TODO complete the implementation of this tester method
    // [HINT] You can consider at least two Passenger objects, and ensure at least the following:
    // p1.compareTo(p2) < 0, if p1 has a boarding group less than the boarding group of p2.
    // p2.compareTo(p1) > 0
    // where p1, and p2 are references to Passenger objects with different boarding groups.
    // Recall that we defined three boarding groups A, B, and C such that A < B < C.
    Passenger.resetPassengerOrder();
    Passenger p1 = new Passenger("Mathom", Group.B, true);
    Passenger p2 = new Passenger("Johnson", Group.A, true);

    if (!(p1.compareTo(p2) > 0)) return false;
    if (!(p2.compareTo(p1) < 0)) return false;
    return true;
  }

  /**
   * Ensures the correctness of Passenger.compareTo() method when called to compare two Passenger
   * objects having the same boarding group, and different arrival orders.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPassengerCompareToSameGroupDifferentArrival() {
    // TODO complete the implementation of this tester method
    // [Hint] You can consider at least two Passenger objects having the SAME boarding group, and
    // ensure at least the following:
    // p1.compareTo(p2) < 0, if p1.ARRIVAL_ORDER is less than p2.ARRIVAL_ORDER
    // p2.compareTo(p1) > 0
    Passenger.resetPassengerOrder();
    Passenger p1 = new Passenger("Mathom", Group.C, true);
    Passenger p2 = new Passenger("Johnson", Group.C, true);
    
    if (!(p1.compareTo(p2) < 0)) return false;
    if (!(p2.compareTo(p1) > 0)) return false;
    return true; // default return statement
  }

  /**
   * Ensures two passengers having the SAME boarding group and with the SAME order of arrival are
   * equal (compareTo should return 0).
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPassengerCompareToSameGroupSameArrival() {
    // TODO complete the implementation of this tester method
    // Even though this case will not be possible in your priority queue, it is required for testing
    // the full functionality of the compareTo() method.
    // [Hint] You can use the resetPassengerOrder() to create equivalent passengers.
    Passenger.resetPassengerOrder();
    Passenger p1 = new Passenger("Mathom", Group.A, true);
    Passenger.resetPassengerOrder();
    Passenger p2 = new Passenger("Johnson", Group.A, true);
    
    if (p1.compareTo(p2) != 0) return false;
    if (p2.compareTo(p1) != 0) return false;
    return true;
  }

  /**
   * Ensures the correctness of the constructor for BoardingQueue class.
   * 
   * This tester should implement at least the following test cases:
   *
   * - Calling the constructor of the BoardingQueue class with an invalid capacity should throw an
   * IllegalArgumentException - Calling the constructor or the BoardingQueue class with a valid
   * capacity should not throw any errors, and should result in a new BoardingQueue object which is
   * empty, has size 0, a capacity equal to the capacity that was passed as a parameter, and the
   * heap array contains only null references.
   *
   * @return true if the constructor of the BoardingQueue functions properly, false otherwise
   */
  public static boolean testBoardingQueueConstructor() {
    // TODO complete the implementation of this tester method
    // [HINT] you can get a copy of the heap array by calling BoardingQueue.toArray() method
    Passenger.resetPassengerOrder();
    try {
      BoardingQueue bq = new BoardingQueue(0);
      return false;
    } catch (IllegalArgumentException e) {}
    
    BoardingQueue bq1 = new BoardingQueue(5);
    if (!bq1.isEmpty()) return false;
    if (bq1.size() != 0) return false;
    if (bq1.capacity() != 5) return false;
    Passenger[] array = bq1.toArray();
    for (int i = 0; i < bq1.capacity(); i++) {
      if (array[i] != null) return false;
    }
    
    return true; 
  }

  /**
   * Tests the functionality of BoardingQueue.peekBest() method by calling peekBest on an empty
   * queue and verifying it throws a NoSuchElementException.
   * 
   * @return true if BoardingQueue.peekBest() verifies a correct functionality, false otherwise
   */
  public static boolean testPeekBestEmptyQueue() {
    // TODO complete the implementation of this tester method
    Passenger.resetPassengerOrder();
    BoardingQueue bq = new BoardingQueue(5);
    try {
      bq.peekBest();
      return false;
    } catch (NoSuchElementException e) {
      return true;
    }
  }

  /**
   * Tests the functionality of BoardingQueue.peekBest() method by calling peekBest on a non-empty
   * queue and ensures it
   * 
   * 1) returns the Passenger having the highest priority (the minimum), and 2) does not remove that
   * Passenger from the boarding queue.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPeekBestNonEmptyQueue() {
    Passenger.resetPassengerOrder();
    BoardingQueue bq = new BoardingQueue(5);
    Passenger p1 = new Passenger("A", Group.A, true);
    Passenger p2 = new Passenger("B", Group.B, true);
    Passenger p3 = new Passenger("C", Group.B, true);
    bq.enqueue(p1);
    bq.enqueue(p2);
    bq.enqueue(p3);
    Passenger[] array = bq.toArray();
    if (bq.peekBest() != p1) return false;
    if (array[0] != p1) return false;
    if (bq.size() != 3) return false;
    return true;
  }

  /**
   * Tests the functionality of the BoardingQueue.enqueue() method by calling enqueue() on an empty
   * queue and ensuring the method 1) adds the Passenger and 2) increments the size.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testEnqueueToEmptyQueue() {
    // TODO complete the implementation of this tester method
    Passenger.resetPassengerOrder();
    BoardingQueue bq = new BoardingQueue(5);
    Passenger p1 = new Passenger("A", Group.A, true);
    Passenger p2 = new Passenger("B", Group.B, true);
    Passenger p3 = new Passenger("C", Group.B, true);
    Passenger p4 = new Passenger("D", Group.C, true);
    Passenger p5 = new Passenger("E", Group.A, true);
    if (bq.size() != 0) return false;
    bq.enqueue(p1);
    bq.enqueue(p2);
    bq.enqueue(p3);
    bq.enqueue(p4);
    bq.enqueue(p5);
    if (bq.size() != 5) return false;
    Passenger[] array = bq.toArray();
    if (array[0] != p1) return false;
    if (array[1] != p5) return false;
    if (array[2] != p3) return false;
    if (array[3] != p4) return false;
    if (array[4] != p2) return false;

    return true;
  }


  /**
   * Tests the functionality of the BoardingQueue.enqueue() method by calling enqueue() on a
   * non-empty queue and ensuring it
   * 
   * 1) inserts the Passenger at the proper position of the heap, increments the size by one, and
   * returns true, if the queue was not already full.
   * 
   * 2) returns false, without making any changes to the size of the queue or the array heap, if the
   * method is called on a full queue.
   * 
   * Try adding at least 5 Passengers.
   * 
   * @return true if tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testEnqueueToNonEmptyQueue() {
    // TODO complete the implementation of this tester method
    // [HINT] you can get a copy of the heap array by calling BoardingQueue.toArray() method
    Passenger.resetPassengerOrder();
    BoardingQueue bq = new BoardingQueue(7);
    Passenger p1 = new Passenger("A", Group.A, true);
    Passenger p2 = new Passenger("B", Group.B, true);
    Passenger p3 = new Passenger("C", Group.B, true);
    Passenger p4 = new Passenger("D", Group.C, true);
    Passenger p5 = new Passenger("E", Group.A, true);
    Passenger p6 = new Passenger("F", Group.C, true);
    Passenger p7 = new Passenger("G", Group.A, true);
    Passenger p8 = new Passenger("H", Group.B, true);
    if (!bq.enqueue(p1)) return false;
    if (!bq.enqueue(p2)) return false;
    if (!bq.enqueue(p3)) return false;
    if (bq.size() != 3) return false;
    if (!bq.enqueue(p4)) return false;
    if (!bq.enqueue(p5)) return false;
    if (!bq.enqueue(p6)) return false;
    if (!bq.enqueue(p7)) return false;
    if (bq.size() != 7) return false;
    if (bq.enqueue(p8)) return false;
    if (bq.size() != 7) return false;
    Passenger[] array = bq.toArray();
    if (array[0] != p1) return false;
    if (array[1] != p5) return false;
    if (array[2] != p7) return false;
    if (array[3] != p4) return false;
    if (array[4] != p2) return false;
    if (array[5] != p6) return false;
    if (array[6] != p3) return false;

    return true;
  }

  /**
   * Tests the functionality of BoardingQueue.dequeue() method by calling dequeue() on an empty
   * queue and ensures a NoSuchElementException is thrown in that case.
   * 
   * @return true if tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testDequeueEmpty() {
    // TODO complete the implementation of this tester method
    Passenger.resetPassengerOrder();
    BoardingQueue bq = new BoardingQueue(7);
    try {
      bq.dequeue();
      return false;
    } catch (NoSuchElementException e) {
      return true;
    }
  }


  /**
   * Tests the functionality of BoardingQueue.dequeue() method by calling dequeue() on a queue of
   * size one and ensures the method call returns the correct Passenger, size is zero, and the array
   * heap contains null references, only.
   * 
   * @return true if tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testDequeueBoardingQueueSizeOne() {
    // TODO complete the implementation of this tester method
    // [HINT] you can get a copy of the heap array by calling BoardingQueue.toArray() method
    Passenger.resetPassengerOrder();
    BoardingQueue bq = new BoardingQueue(3);
    Passenger p1 = new Passenger("A", Group.B, true);
    bq.enqueue(p1);
    if (bq.size() != 1) return false;
    if (bq.dequeue() != p1) return false;
    if (bq.size() != 0) return false;
    Passenger[] array = bq.toArray();
    for (int i = 0; i < array.length; i++) {
      if (array[i] != null) return false;
    }
    return true;
  }

  /**
   * Tests the functionality of BoardingQueue.dequeue() when called on a non-empty boarding queue.
   * 
   * This tests ensures the dequeue() method removes, and returns the passenger with the highest
   * boarding priority in the queue, the size of the queue must be decremented by one, and the
   * contents of the heap array is as expected.
   * 
   * @return true if PriorityCareAdmissions.dequeue() returns the correct Passenger
   *         each time it is called and size is appropriately decremented, false otherwise
   */
  public static boolean testDequeueBoardingQueueNonEmpty() {
    // TODO complete the implementation of this tester method
    // [HINT] Try considering calling dequeue from a boarding queue whose size is at least 6.
    // Consider cases where percolate-down recurses on left and right.
    // You can call dequeue multiple times to cover multiple operational cases of percolate down
    // method (for instance percolate down can reach the bottom level of the heap or not)
    Passenger.resetPassengerOrder();
    BoardingQueue bq = new BoardingQueue(7);
    Passenger p1 = new Passenger("A", Group.A, true);
    Passenger p2 = new Passenger("B", Group.B, true);
    Passenger p3 = new Passenger("C", Group.B, true);
    Passenger p4 = new Passenger("D", Group.C, true);
    Passenger p5 = new Passenger("E", Group.A, true);
    Passenger p6 = new Passenger("F", Group.C, true);
    Passenger p7 = new Passenger("G", Group.A, true);
    bq.enqueue(p1);
    bq.enqueue(p2);
    bq.enqueue(p3);
    bq.enqueue(p4);
    bq.enqueue(p5);
    bq.enqueue(p6);
    bq.enqueue(p7);
    if (bq.size() != 7) return false;
    if (bq.dequeue() != p1) return false;
    if (bq.dequeue() != p5) return false;
    if (bq.dequeue() != p7) return false;
    if (bq.dequeue() != p2) return false;
    if (bq.dequeue() != p3) return false;
    if (bq.dequeue() != p4) return false;
    if (bq.size() != 1) return false;
    
    return true;
  }

  /**
   * Tests the functionality of the clear() method. Should implement at least the following
   * scenarios:
   * 
   * - clear can be called on an empty queue with no errors.
   * 
   * - clear can be called on a non-empty queue with no errors.
   * 
   * After calling clear(), this tester ensures that the queue is empty, meaning its size is zero
   * and its heap array contains only null references.
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testBoardingQueueClear() {
    Passenger.resetPassengerOrder();
    BoardingQueue bq = new BoardingQueue(7);
    try {
      bq.clear();
    } catch (Exception e) {
      return false;
    }
    if (bq.size() != 0) return false;
    Passenger[] array1 = bq.toArray();
    for (int i = 0; i < bq.capacity(); i++) {
      if (array1[i] != null) return false;
    }
    Passenger p1 = new Passenger("A", Group.A, true);
    Passenger p2 = new Passenger("B", Group.B, true);
    Passenger p3 = new Passenger("C", Group.B, true);
    Passenger p4 = new Passenger("D", Group.C, true);
    Passenger p5 = new Passenger("E", Group.A, true);
    Passenger p6 = new Passenger("F", Group.C, true);
    Passenger p7 = new Passenger("G", Group.A, true);
    bq.enqueue(p1);
    bq.enqueue(p2);
    bq.enqueue(p3);
    bq.enqueue(p4);
    bq.enqueue(p5);
    bq.enqueue(p6);
    bq.enqueue(p7);
    try {
      bq.clear();
    } catch (Exception e) {
      return false;
    }
    if (bq.size() != 0) return false;
    Passenger[] array2 = bq.toArray();
    for (int i = 0; i < bq.capacity(); i++) {
      if (array2[i] != null) return false;
    }
    return true;
  }

  /**
   * Main method to run this tester class.
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testPassengerCompareToDifferentGroup: "
        + (testPassengerCompareToDifferentGroup() ? "Pass" : "Failed!"));
    System.out.println("testPassengerCompareToSameGroupDifferentArrival: "
        + (testPassengerCompareToSameGroupDifferentArrival() ? "Pass" : "Failed!"));
    System.out.println("testPassengerCompareToSameGroupSameArrival: "
        + (testPassengerCompareToSameGroupSameArrival() ? "Pass" : "Failed!"));
    System.out.println(
        "testBoardingQueueConstructor: " + (testBoardingQueueConstructor() ? "Pass" : "Failed!"));
    System.out
        .println("testPeekBestEmptyQueue: " + (testPeekBestEmptyQueue() ? "Pass" : "Failed!"));
    System.out.println(
        "testPeekBestNonEmptyQueue: " + (testPeekBestNonEmptyQueue() ? "Pass" : "Failed!"));
    System.out
        .println("testEnqueueToEmptyQueue: " + (testEnqueueToEmptyQueue() ? "Pass" : "Failed!"));
    System.out.println(
        "testEnqueueToNonEmptyQueue: " + (testEnqueueToNonEmptyQueue() ? "Pass" : "Failed!"));
    System.out.println("testDequeueEmpty: " + (testDequeueEmpty() ? "Pass" : "Failed!"));
    System.out.println("testDequeueBoardingQueueSizeOne: "
        + (testDequeueBoardingQueueSizeOne() ? "Pass" : "Failed!"));
    System.out.println("testDequeueBoardingQueueNonEmpty: "
        + (testDequeueBoardingQueueNonEmpty() ? "Pass" : "Failed!"));
    System.out
        .println("testBoardingQueueClear: " + (testBoardingQueueClear() ? "Pass" : "Failed!"));
  }

}
