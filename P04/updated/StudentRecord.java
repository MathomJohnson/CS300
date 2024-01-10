//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Student Record class
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

import java.util.zip.DataFormatException;


/**
 * Creates a single student record consisting of name, email, campusID and if the pre reqs for the enrollment is satisfied
 */
public class StudentRecord {

  private String campusID;
  private String email;
  private boolean isPreReqSatisfied;
  private String name;
  
  /**
   * Constructor for a student record object. Assigns values to all fields.
   * 
   * @param name     the name of the student
   * @param email    the email of the student
   * @param campusID the campusID of the student
   * @param preReq   the boolean representing if the student satisfies the prerequisites
   * @throws DataFormatException with message "Bad name, email, or campusID!" if name or email or
   *                             campusID are NOT valid
   */
  public StudentRecord(String name, String email, String campusID, boolean preReq)
      throws DataFormatException {
    
    //check if the name, email and campusID are of valid format and assign the value, or else throw exception
    if (isValidName(name) && isValidEmail(email) && isValidCampusID(campusID)) {
      this.campusID = campusID;
      this.email = email;
      isPreReqSatisfied = preReq;
      this.name = name;
    }
    else {
      throw new DataFormatException("Bad name, email, or campusID!");
    }
  }

  /**
   * Validator method for a student's name
   * 
   * @param name the student's name
   * @return true if and only if the name is not null and not blank
   */
  public static boolean isValidName(String name) {
    if (name == null || name == "" ) {
      return false;
    }
    
	return true; 
  }

  /**
   * Validator method for a student's email
   * 
   * @param email the student's email
   * @return true if and only if the email is not null, has one @ symbol, ends with .edu, is between
   *         0 and 40 characters (EXCLUSIVE, that is, 0 and 40 are not allowed lengths but 1 and 39
   *         are), and has at least two characters before the @ symbol.
   */
  public static boolean isValidEmail(String email) {
    if (email.length() < 1 || email.length() > 39) {
      return false;
    }
    
    //check if email is null, or has less than 2 characters before domain name and has @ character
    if (email == null || !email.contains("@") || email.indexOf('@') < 2) {
      return false;
    }
    
    int len = email.length();
    //check if the email ends with .wisc
    if (email.charAt(len - 1) != 'u' || email.charAt(len - 2) != 'd' || email.charAt(len - 3) != 'e' 
        || email.charAt(len - 4) != '.') {
      return false;
    }
    
	return true; 
  }

  /**
   * Validator method for a student's id
   * 
   * @param campusID the student's campusID
   * @return true if and only if the campusID is not null and can be parsed to a long with
   *         10-digits. Extra leading and trailing whitespace should be disregarded.
   */
  public static boolean isValidCampusID(String campusID) {

    //check if the campusID is null or is a less than a 10 digit number
    if (campusID == null || campusID.trim().length() != 10) {
      return false;
    }
    
    try {
      Long.parseLong(campusID);
    }
    catch (NumberFormatException e) {
      e.getMessage();
      return false;
    }
    
	return true;
  }

  /**
   * Getter method for a student's name
   * 
   * @return the student's name
   */
  public String getName() {
	return name;
  }

  /**
   * Getter method for a student's email
   * 
   * @return the student's email
   */
  public String getEmail() {
	return email;
  }

  /**
   * Getter method for a student's campusID
   * 
   * @return the student's campusID
   */
  public String getCampusID() {
	return campusID;
  }

  /**
   * Returns true if this student record satisfies the pre-requisites of the course
   * 
   * @return true if this student record satisfies the pre-requisites of the course
   */
  public boolean isPrerequisiteSatisfied() {
	return isPreReqSatisfied; 
  }

  /**
   * Compared this StudentRecord to the specified object
   * 
   * @return true if anObject is instanceof StudentRecord and has the same campusID as this
   *         StudentRecord.
   */
  public boolean equals(Object other) {
    if (!(other instanceof StudentRecord)) {
      return false;
    }
    
    StudentRecord otherRecord = (StudentRecord) other;
    if (!(this.campusID.equals(otherRecord.campusID))) {
      return false;
    }
    
	return true; 
  }


  /**
   * Returns a string representation of this student record in the following format (comma followed
   * by a space ", " separated): <BR>
   * name, email, campusID, preReqValue
   * 
   * @return the string representation of a studentRecord as described above
   */
  @Override
  public String toString() {
    return name + ", " + email + ", " + campusID + ", " + isPreReqSatisfied;
  }
}
