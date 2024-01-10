//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    The Bus Stop Tree
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
import java.time.LocalTime;
import java.util.Iterator; 

/**
 * This class provides tester methods for various functionalities of the 
 * BusStopTree class.
 */
public class BusStopTreeTester {

  /**
   * Tests that compareTo returns the correct value when comparing a bus with a different arrival.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToDifferentArrivalTime() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    
    BusRoute route1 = 
        BusRoute.dummyRoute("Route 1", BusRoute.BusDirection.INCOMING, stopIds1, stopTimes1);
    
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route1);
    
    if (bus1.compareTo(bus2) >= 0) return false; 
    if (bus2.compareTo(bus1) <= 0) return false;
    return true;
  }

  /**
   * For two buses with the same arrival time but different routes, test that compareTo returns the
   * correct value.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToSameArrivalTimeDifferentRoute() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 2", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    
    if (bus1.compareTo(bus2) >= 0) return false;
    if (bus2.compareTo(bus1) <= 0) return false;
    return true;
  }


  /**
   * For two buses with the same arrival time and route name, but different directions, test that
   * compareTo returns the correct value.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToSameArrivalTimeSameRouteDifferentDirection() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.INCOMING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    
    if (bus1.compareTo(bus2) > 0) return true;
    return false;
  }

  /**
   * Tests that compareTo returns the correct value (0) when comparing a bus with the same arrival
   * time, route name, and direction.
   * 
   * @return true if the test passes, false otherwise.
   */
  private static boolean testBusCompareToSameBus() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);

    // compare bus1 to bus2 and vice versa
    boolean correctComparison1 = bus1.compareTo(bus2) == 0; // should return 0
    boolean correctComparison2 = bus2.compareTo(bus1) == 0; // should return 0

    // test passes if both comparisons return 0
    return correctComparison1 && correctComparison2;
  }

  /**
   * Tests that isValidBST returns true for an empty BST.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIsValidBSTEmpty() {
    BusStopTree bst = new BusStopTree(1);
  
    return BusStopTree.isValidBST(bst.getRoot());
  }


  /**
   * Tests that isValidBST returns false for an invalid BST.
   * 
   * Should use a tree with depth > 2. Make sure to include a case where the left subtree contains a
   * node that is greater than the right subtree. (See the example in the spec for more details.)
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIsValidBSTInvalid() {
    BusStopTree bst = new BusStopTree(1);
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes2 = {"02:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes3 = {"09:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes4 = {"12:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes5 = {"03:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes6 = {"01:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes7 = {"07:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes8 = {"11:00", "07:00", "09:00", "11:00", "13:00"};
    int[] stopIds = {1, 2, 3, 4, 5};
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes2);
    BusRoute route3 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes3);
    BusRoute route4 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes4);
    BusRoute route5 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes5);
    BusRoute route6 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes6);
    BusRoute route7 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes7);
    BusRoute route8 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes8);
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    Bus bus3 = new Bus(BusStop.getStop(1), route3);
    Bus bus4 = new Bus(BusStop.getStop(1), route4);
    Bus bus5 = new Bus(BusStop.getStop(1), route5);
    Bus bus6 = new Bus(BusStop.getStop(1), route6);
    Bus bus7 = new Bus(BusStop.getStop(1), route7);
    Bus bus8 = new Bus(BusStop.getStop(1), route8);
    bst.addBus(bus1);
    bst.getRoot().setLeft(new Node<Bus>(bus2));
    bst.getRoot().setRight(new Node<Bus>(bus4));
    Node<Bus> subL = bst.getRoot().getLeft();
    Node<Bus> subR = bst.getRoot().getRight();
    subL.setLeft(new Node<Bus>(bus6));
    subL.setRight(new Node<Bus>(bus3));
    subR.setLeft(new Node<Bus>(bus5));
    Node<Bus> subLR = subL.getRight();
    subLR.setLeft(new Node<Bus>(bus7));
    subLR.setRight(new Node<Bus>(bus8));
    
    if (bst.size() != 8) return false;
    if (BusStopTree.isValidBST(bst.getRoot())) return false;
    return true;
  }


  /**
   * Tests that isValidBST returns true for a valid BST.
   * 
   * Should use a tree with depth > 2.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIsValidBSTValid() {
    BusStopTree bst = new BusStopTree(1);
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes2 = {"02:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes3 = {"09:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes4 = {"12:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes5 = {"03:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes6 = {"01:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes7 = {"07:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes8 = {"11:00", "07:00", "09:00", "11:00", "13:00"};
    int[] stopIds = {1, 2, 3, 4, 5};
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes2);
    BusRoute route3 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes3);
    BusRoute route4 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes4);
    BusRoute route5 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes5);
    BusRoute route6 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes6);
    BusRoute route7 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes7);
    BusRoute route8 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes8);
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    Bus bus3 = new Bus(BusStop.getStop(1), route3);
    Bus bus4 = new Bus(BusStop.getStop(1), route4);
    Bus bus5 = new Bus(BusStop.getStop(1), route5);
    Bus bus6 = new Bus(BusStop.getStop(1), route6);
    Bus bus7 = new Bus(BusStop.getStop(1), route7);
    Bus bus8 = new Bus(BusStop.getStop(1), route8);
    bst.addBus(bus1);
    bst.addBus(bus8);
    bst.addBus(bus6);
    bst.addBus(bus4);
    bst.addBus(bus5);
    bst.addBus(bus3);
    bst.addBus(bus7);
    bst.addBus(bus2);
    if (bst.size() != 8) return false;
    if (bst.height() != 4) return false;
    if (!BusStopTree.isValidBST(bst.getRoot())) return false;
    
    return true;
  }


  /**
   * Tests that addBus correctly adds a bus to an empty BST.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBusEmpty() {
    BusStopTree bst = new BusStopTree(6);
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00", "14:00"};
    int[] stopIds1 = {1, 2, 3, 4, 5, 6};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus = new Bus(BusStop.getStop(6), route1);
    
    if (bst.getRoot() != null) return false;
    
    bst.addBus(bus);
    
    if (!bst.getRoot().getValue().equals(bus)) return false;
    return true;
  }

  /**
   * Tests that addBus correctly adds a bus to a non-empty BST.
   * 
   * Each time you add a bus, make sure that 1) addBus() returns true, 2) the BST is still valid, 3)
   * the BST size has been incremented.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBus() {
    BusStopTree bst = new BusStopTree(1);
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes2 = {"01:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes3 = {"09:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes4 = {"12:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes5 = {"03:00", "07:00", "09:00", "11:00", "13:00"};
    int[] stopIds = {1, 2, 3, 4, 5};
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes2);
    BusRoute route3 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes3);
    BusRoute route4 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes4);
    BusRoute route5 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes5);
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    Bus bus3 = new Bus(BusStop.getStop(1), route3);
    Bus bus4 = new Bus(BusStop.getStop(1), route4);
    Bus bus5 = new Bus(BusStop.getStop(1), route5);
    if (!BusStopTree.isValidBST(bst.getRoot())) return false;
    if (bst.size() != 0) return false;
    if (!bst.addBus(bus1)) return false;
    if (!BusStopTree.isValidBST(bst.getRoot())) return false;
    if (bst.size() != 1) return false;
    if (!bst.addBus(bus2)) return false;    
    if (!BusStopTree.isValidBST(bst.getRoot())) return false;
    if (bst.size() != 2) return false;
    if (!bst.addBus(bus3)) return false;   
    if (!BusStopTree.isValidBST(bst.getRoot())) return false;
    if (bst.size() != 3) return false;
    if (!bst.addBus(bus4)) return false;
    if (!BusStopTree.isValidBST(bst.getRoot())) return false;
    if (bst.size() != 4) return false;
    if (!bst.addBus(bus5)) return false;
    if (!BusStopTree.isValidBST(bst.getRoot())) return false;
    if (bst.size() != 5) return false;
    
    return true;
  }

  /**
   * Tests that addBus returns false when adding a duplicate bus. The BST should not be modified
   * (same size).
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBusDuplicate() {
    BusStopTree bst = new BusStopTree(1);
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes2 = {"01:00", "07:00", "09:00", "11:00", "13:00"};
    int[] stopIds = {1, 2, 3, 4, 5};
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes2);
    BusRoute route3 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes2);
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    Bus bus3 = new Bus(BusStop.getStop(1), route3);
    
    bst.addBus(bus1);
    bst.addBus(bus2);
    if (bst.size() != 2) return false;
    if (bst.addBus(bus3) != false) return false;
    if (bst.size() != 2) return false;
    
    return true;
  }


  /**
   * Tests that contains returns true when the BST contains the Bus, and false otherwise.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testContains() {
    BusStopTree bst = new BusStopTree(1);
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes2 = {"02:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes3 = {"09:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes4 = {"12:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes5 = {"03:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes6 = {"01:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes7 = {"07:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes8 = {"11:00", "07:00", "09:00", "11:00", "13:00"};
    int[] stopIds = {1, 2, 3, 4, 5};
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes2);
    BusRoute route3 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes3);
    BusRoute route4 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes4);
    BusRoute route5 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes5);
    BusRoute route6 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes6);
    BusRoute route7 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes7);
    BusRoute route8 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes8);
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    Bus bus3 = new Bus(BusStop.getStop(1), route3);
    Bus bus4 = new Bus(BusStop.getStop(1), route4);
    Bus bus5 = new Bus(BusStop.getStop(1), route5);
    Bus bus6 = new Bus(BusStop.getStop(1), route6);
    Bus bus7 = new Bus(BusStop.getStop(1), route7);
    Bus bus8 = new Bus(BusStop.getStop(1), route8);
    bst.addBus(bus1);
    bst.addBus(bus2);
    bst.addBus(bus3);
    bst.addBus(bus4);
    bst.addBus(bus6);
    bst.addBus(bus7);
    if (!bst.contains(bus2)) return false;
    if (!bst.contains(bus1)) return false;
    if (!bst.contains(bus4)) return false;
    if (!bst.contains(bus7)) return false;
    if (bst.contains(bus5)) return false;
    if (bst.contains(bus8)) return false;
    return true;
  }


  /**
   * Tests that getFirstNodeAfter returns the correct <code>Node<Bus></code> when the correct
   * <code>Node<Bus></code> is the node passed in as the root node parameter.
   * 
   * @return
   */
  public static boolean testGetFirstNodeAfterRoot() {
    BusStopTree bst = new BusStopTree(1);
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes2 = {"01:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes3 = {"09:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes4 = {"12:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes5 = {"03:00", "07:00", "09:00", "11:00", "13:00"};
    int[] stopIds = {1, 2, 3, 4, 5};
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes2);
    BusRoute route3 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes3);
    BusRoute route4 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes4);
    BusRoute route5 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes5);
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    Bus bus3 = new Bus(BusStop.getStop(1), route3);
    Bus bus4 = new Bus(BusStop.getStop(1), route4);
    Bus bus5 = new Bus(BusStop.getStop(1), route5);
    bst.addBus(bus1);
    bst.addBus(bus2);
    bst.addBus(bus3);
    bst.addBus(bus4);
    bst.addBus(bus5);
    Node<Bus> node1 = bst.getFirstNodeAfter(bus1.getArrivalTime(), bst.getRoot());
    Node<Bus> node2 = bst.getFirstNodeAfter(LocalTime.of(8, 0), bst.getRoot());
    if (node1.getValue().compareTo(bus1) != 0) return false;
    if (node2.getValue().compareTo(bus3) != 0) return false;
    return true;
  }

  /**
   * Tests that getFirstNodeAfter returns the correct <code>Node<Bus></code> when the correct
   * <code>Node<Bus></code> is in the left subtree.
   * 
   * @return
   */
  public static boolean testGetFirstNodeAfterLeftSubtree() {
    BusStopTree bst = new BusStopTree(1);
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes2 = {"01:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes3 = {"09:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes4 = {"12:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes5 = {"03:00", "07:00", "09:00", "11:00", "13:00"};
    int[] stopIds = {1, 2, 3, 4, 5};
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes2);
    BusRoute route3 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes3);
    BusRoute route4 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes4);
    BusRoute route5 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes5);
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    Bus bus3 = new Bus(BusStop.getStop(1), route3);
    Bus bus4 = new Bus(BusStop.getStop(1), route4);
    Bus bus5 = new Bus(BusStop.getStop(1), route5);
    bst.addBus(bus1);
    bst.addBus(bus2);
    bst.addBus(bus3);
    bst.addBus(bus4);
    bst.addBus(bus5);
    Node<Bus> node = bst.getFirstNodeAfter(LocalTime.of(2, 0), bst.getRoot());
    if (node.getValue().compareTo(bus5) != 0) return false;
    return true;
  }

  /**
   * Tests that getFirstNodeAfter returns the correct <code>Node<Bus></code> when the correct
   * <code>Node<Bus></code> is in the right subtree.
   * 
   * @return
   */
  public static boolean testGetFirstNodeAfterRightSubtree() {
    BusStopTree bst = new BusStopTree(1);
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes2 = {"01:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes3 = {"09:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes4 = {"12:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes5 = {"03:00", "07:00", "09:00", "11:00", "13:00"};
    int[] stopIds = {1, 2, 3, 4, 5};
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes2);
    BusRoute route3 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes3);
    BusRoute route4 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes4);
    BusRoute route5 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes5);
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    Bus bus3 = new Bus(BusStop.getStop(1), route3);
    Bus bus4 = new Bus(BusStop.getStop(1), route4);
    Bus bus5 = new Bus(BusStop.getStop(1), route5);
    bst.addBus(bus1);
    bst.addBus(bus2);
    bst.addBus(bus3);
    bst.addBus(bus4);
    bst.addBus(bus5);
    Node<Bus> node = bst.getFirstNodeAfter(LocalTime.of(8, 0), bst.getRoot());
    if (node.getValue().compareTo(bus3) != 0) return false;
    return true;
  }

  /**
   * Tests that removeBus correctly removes a Bus that is a LEAF NODE. Make sure that 1) removeBus
   * returns the removed Bus, 2) the BST is still valid, 3) the BST size has been decremented.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBusLeaf() {
    BusStopTree bst = new BusStopTree(1);
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes2 = {"01:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes3 = {"09:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes4 = {"12:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes5 = {"03:00", "07:00", "09:00", "11:00", "13:00"};
    int[] stopIds = {1, 2, 3, 4, 5};
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes2);
    BusRoute route3 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes3);
    BusRoute route4 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes4);
    BusRoute route5 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes5);
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    Bus bus3 = new Bus(BusStop.getStop(1), route3);
    Bus bus4 = new Bus(BusStop.getStop(1), route4);
    Bus bus5 = new Bus(BusStop.getStop(1), route5);
    bst.addBus(bus1);
    bst.addBus(bus2);
    bst.addBus(bus3);
    bst.addBus(bus4);
    bst.addBus(bus5);
    
//    System.out.println(bst.toString());
//    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//    bst.removeBus(bus4);
//    System.out.println(bst.toString());
//    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//    bst.removeBus(bus5);
//    System.out.println(bst.toString());
    return false;
  }

  /**
   * Tests that removeBus correctly removes a Bus that is a non-leaf node with ONE child. Make sure
   * that 1) removeBus returns the removed Bus, 2) the BST is still valid, 3) the BST size has been
   * decremented.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBusNodeOneChild() {
    BusStopTree bst = new BusStopTree(1);
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes2 = {"01:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes3 = {"09:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes4 = {"12:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes5 = {"03:00", "07:00", "09:00", "11:00", "13:00"};
    int[] stopIds = {1, 2, 3, 4, 5};
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes2);
    BusRoute route3 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes3);
    BusRoute route4 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes4);
    BusRoute route5 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes5);
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    Bus bus3 = new Bus(BusStop.getStop(1), route3);
    Bus bus4 = new Bus(BusStop.getStop(1), route4);
    Bus bus5 = new Bus(BusStop.getStop(1), route5);
    bst.addBus(bus1);
    bst.addBus(bus2);
    bst.addBus(bus3);
    bst.addBus(bus4);
    bst.addBus(bus5);
    
    System.out.println(bst.toString());
    bst.removeBus(bus3);
    System.out.println("!!!!!!!!!!!!!!!!!!!!!");
    System.out.println(bst.toString());
    return false;
  }

  /**
   * Tests that removeBus correctly removes a Bus that is a non-leaf node with TWO children. Make
   * sure that 1) removeBus returns the removed Bus, 2) the BST is still valid, 3) the BST size has
   * been decremented.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBusNodeTwoChildren() {
    return false;
  }


  /**
   * Tests that removeBus returns false when removing a Bus that is not in the BST. The BST should
   * not be modified.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBusNodeNotInBST() {
    // TODO: Default return value.
    return false;
  }

  /**
   * Tests the creation of an BusFilteredIterator where NONE of the buses go to the destination.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetNextBusesToEmpty() {
    BusStopTree bst = new BusStopTree(1);
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes2 = {"01:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes3 = {"09:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes4 = {"12:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes5 = {"03:00", "07:00", "09:00", "11:00", "13:00"};
    int[] stopIds = {1, 2, 4, 5};
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes2);
    BusRoute route3 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes3);
    BusRoute route4 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes4);
    BusRoute route5 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes5);
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    Bus bus3 = new Bus(BusStop.getStop(1), route3);
    Bus bus4 = new Bus(BusStop.getStop(1), route4);
    Bus bus5 = new Bus(BusStop.getStop(1), route5);
    bst.addBus(bus1);
    bst.addBus(bus2);
    bst.addBus(bus3);
    bst.addBus(bus4);
    bst.addBus(bus5);
    Iterator<Bus> iterator = bst.getNextBuses(LocalTime.of(1, 0));
    Iterator<Bus> filterIter = new BusFilteredIterator(iterator, BusStop.getStop(3));
    if (filterIter.hasNext()) return false;
    return true;
  }

  /**
   * Tests the creation of an BusFilteredIterator where SOME of the buses go to the destination.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetNextBusesToSome() {
    BusStopTree bst = new BusStopTree(1);
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes2 = {"01:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes3 = {"09:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes4 = {"12:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes5 = {"03:00", "07:00", "09:00", "11:00", "13:00"};
    int[] stopIds1 = {1, 2, 4, 5};
    int[] stopIds2 = {1, 2, 3, 4, 5};
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds2, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes2);
    BusRoute route3 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds2, stopTimes3);
    BusRoute route4 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes4);
    BusRoute route5 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds2, stopTimes5);
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    Bus bus3 = new Bus(BusStop.getStop(1), route3);
    Bus bus4 = new Bus(BusStop.getStop(1), route4);
    Bus bus5 = new Bus(BusStop.getStop(1), route5);
    bst.addBus(bus1);
    bst.addBus(bus2);
    bst.addBus(bus3);
    bst.addBus(bus4);
    bst.addBus(bus5);
    Iterator<Bus> iterator = new BusForwardIterator(bst.getRoot(), bst.getRoot().getLeft());
    Iterator<Bus> filterIter = new BusFilteredIterator(iterator, BusStop.getStop(3));
    Bus filteredBuses[] = {bus5, bus1, bus3};
    int i = 0;
    while (filterIter.hasNext()) {
      if (filterIter.next().compareTo(filteredBuses[i]) != 0) {
        return false;
      }
      i++;
    }
    if (i != 3) return false;
    
    return true;
  }

  /**
   * Tests the creation of an BusFilteredIterator where ALL of the buses go to the destination.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetNextBusesToAll() {
    BusStopTree bst = new BusStopTree(1);
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes2 = {"01:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes3 = {"09:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes4 = {"08:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes5 = {"03:00", "07:00", "09:00", "11:00", "13:00"};
    int[] stopIds = {1, 2, 3, 4, 5};
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes2);
    BusRoute route3 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes3);
    BusRoute route4 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes4);
    BusRoute route5 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes5);
    Bus bus1 = new Bus(BusStop.getStop(1), route1);
    Bus bus2 = new Bus(BusStop.getStop(1), route2);
    Bus bus3 = new Bus(BusStop.getStop(1), route3);
    Bus bus4 = new Bus(BusStop.getStop(1), route4);
    Bus bus5 = new Bus(BusStop.getStop(1), route5);
    bst.addBus(bus1);
    bst.addBus(bus2);
    bst.addBus(bus3);
    bst.addBus(bus4);
    bst.addBus(bus5);
    Iterator<Bus> iterator = bst.getNextBuses(LocalTime.of(1, 0));
    Iterator<Bus> filterIter = new BusFilteredIterator(iterator, BusStop.getStop(3));
    int count = 0;
    while (filterIter.hasNext()) {
      count++;
      filterIter.next();
    }
    if (count != 5) return false;
    
    return true;
  }

  public static void main(String[] args) {
    // Populate BusStop with dummy data. This only has to be done once.
    BusStop.createDummyStops();

    System.out
        .println("testBusCompareToDifferentArrivalTime: " + testBusCompareToDifferentArrivalTime());
    System.out.println("testBusCompareToSameArrivalTimeDifferentRoute: "
        + testBusCompareToSameArrivalTimeDifferentRoute());
    System.out.println("testBusCompareToSameArrivalTimeSameRouteDifferentDirection: "
        + testBusCompareToSameArrivalTimeSameRouteDifferentDirection());
    System.out.println("testBusCompareToSameBus: " + testBusCompareToSameBus());
    System.out.println("testIsValidBSTEmpty: " + testIsValidBSTEmpty());
    System.out.println("testIsValidBSTInvalid: " + testIsValidBSTInvalid());
    System.out.println("testIsValidBSTValid: " + testIsValidBSTValid());
    System.out.println("testAddBusEmpty: " + testAddBusEmpty());
    System.out.println("testAddBus: " + testAddBus());
    System.out.println("testAddBusDuplicate: " + testAddBusDuplicate());
    System.out.println("testRemoveBusLeaf: " + testRemoveBusLeaf());
    System.out.println("testRemoveBusNodeOneChild: " + testRemoveBusNodeOneChild());
    System.out.println("testRemoveBusNodeTwoChildren: " + testRemoveBusNodeTwoChildren());
    System.out.println("testRemoveBusNodeNotInBST: " + testRemoveBusNodeNotInBST());
    System.out.println("testContains: " + testContains());
    System.out.println("testGetFirstNodeAfterRoot: " + testGetFirstNodeAfterRoot());
    System.out.println("testGetFirstNodeAfterLeftSubtree: " + testGetFirstNodeAfterLeftSubtree());
    System.out.println("testGetFirstNodeAfterRightSubtree: " + testGetFirstNodeAfterRightSubtree());
    System.out.println("testGetNextBusesToEmpty: " + testGetNextBusesToEmpty());
    System.out.println("testGetNextBusesToSome: " + testGetNextBusesToSome());
    System.out.println("testGetNextBusesToAll: " + testGetNextBusesToAll());
  }

}
