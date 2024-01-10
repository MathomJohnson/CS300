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

/**
 * This class models Passenger objects ready to board an airplane.
 *
 */
public class Passenger implements Comparable<Passenger>{ // TODO Define the Passenger class to implement the Comparable interface.
  // A Passenger MUST be compared to another Passenger, ONLY.

  // Data fields
  private static int orderGen = 1; // generator of arrival orders of passengers
  private String name; // name of this passenger
  private final int ARRIVAL_ORDER; // arrival order of this passenger
  private Group group; // boarding group assigned to this passenger
  private boolean isCheckedIn; // indicates whether this passenger was checked in before boarding
                               // the airplane


  // CONSTRUCTOR - PROVIDED
  /**
   * Constructs a new Passenger given their name, boarding group, and checkedin status
   * 
   * @param name        name to be assigned to this Passenger
   * @param group       boarding group to be assigned to this Passenger
   * @param isCheckedIn indicates whether this passenger was checked in, or not
   * @throws IllegalArgumentException if name is null or blank or if group is null
   */
  public Passenger(String name, Group group, boolean isCheckedIn) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("name is null or blank!");
    }

    if (group == null) {
      throw new IllegalArgumentException("boarding group is null!");
    }

    this.name = name;
    this.group = group;
    this.isCheckedIn = isCheckedIn;
    this.ARRIVAL_ORDER = nextOrder();
  }

  // nextOrder() PROVIDED
  /**
   * Generates and returns the arrival order to be assigned to the next Passenger object to be
   * created
   * 
   * @return the next generated order
   */
  private static int nextOrder() {
    return orderGen++;
  }

  // resetPassengerOrder() PROVIDED
  /**
   * Resets the arrival order generated to 1. This method can be used for testing purposes, or to
   * reset the system.
   */
  protected static void resetPassengerOrder() {
    orderGen = 1;
  }

  // name() PROVIDED
  /**
   * Gets the name of this passenger
   * 
   * @return the name of this passenger
   */
  public String name() {
    return name;
  }

  // isCheckedIn() PROVIDED
  /**
   * Determines whether this passenger was checked in before boarding the airplane
   * 
   * @return true if this passenger was checked in
   */
  public boolean isCheckedIn() {
    return this.isCheckedIn;
  }


  // toString() PROVIDED
  /**
   * Returns a String representation of this Passenger in the following format:
   * 
   * <name> from Group <group>
   * 
   * @return a String representation of this passenger
   */
  public String toString() {
    return this.name + " from Group " + this.group;
  }



  /**
   * Indicates whether some other object is "equal to" this Passenger.
   * 
   * @param obj the reference object with which to compare.
   * @return true if obj is an instance of Passenger and has the exact same name, group, and arrival
   *         order as this Passenger, otherwise return false.
   */
  @Override
  public boolean equals(Object obj) {
//    Passenger p = (Passenger) obj;
//    if (this.compareTo(p) == 0) return true;
//    return false;
    if (obj instanceof Passenger) {
      Passenger other = (Passenger) obj;
      return this.name.equals(other.name) && this.compareTo(other) == 0;
    }
    return false;
  }


  // TODO Add and implement the Passenger.compareTo() method as part of implementation of the
  // Comparable interface.
  // Passenger.compareTo() method MUST take an input parameter of type Passenger.
  //
  // Passengers are compared with respect to the increasing order of their boarding groups.
  // You can use Group.compareTo() method to compare boarding group constants.
  //
  // For instance, if we consider two Passengers p1 and p2 such as: 
  // the boarding group of p1 is Group.A
  // the boarding group of p2 is Group.B
  // Then, we the following statements are true:
  // p1.compareTo(p2) < 0
  // p2.compareTo(p1) > 0
  
  // If two passengers have the same boarding groups, they are compared with respect to the
  // increasing order of their arrival orders. If we consider two Passengers p1 and p2 such as:
  // Both p1 and p2 have the boarding group Group.B, for instance
  // the arrival order of p1 is 3
  // the arrival order of p2 is 7
  // Then, we the following statements are true:
  // p1.compareTo(p2) < 0
  // p2.compareTo(p1) > 0
  // 
  // The Passenger.compareTo method returns:
  // (*) zero if this Passenger and the other input Passenger have the same boarding group and the same
  // arrival order.
  // (*) a negative integer if this Passenger is less than the other input Passenger and, 
  // (*) a positive integer if this Passenger is greater than the other input Passenger.
  public int compareTo(Passenger other) {
    if (other == null) return -1;
    if (this.group.compareTo(other.group) > 0) return 1;
    if (this.group.compareTo(other.group) < 0) return -1;
    if (this.ARRIVAL_ORDER > other.ARRIVAL_ORDER) return 1;
    if (this.ARRIVAL_ORDER < other.ARRIVAL_ORDER) return -1;
    return 0;
  }


}
