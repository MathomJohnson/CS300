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
import java.util.Iterator;

/**
 * An iterator that only returns buses from another iterator that 
 * go to a particular destination.
 */
public class BusFilteredIterator implements Iterator<Bus> {
  
  //The iterator we are filtering.
  private Iterator<Bus> baseIterator;
  //The destination BusStop we are filtering by.
  private BusStop destination;
  //The next Bus to be returned, or null if there aren't any more.
  private Bus next;
  
  /**
   * Construct a new BusFilteredIterator that filters the given iterator 
   * to return only Bus-es that stop at the given destination.
   * @param iterator the iterator that we want to filter
   * @param destination the BusStop we are filtering for.
   */
  BusFilteredIterator(Iterator<Bus> iterator, BusStop destination) {
    baseIterator = iterator;
    this.destination = destination;
    advanceToNext();
  }
  
  /**
   * Private helper method that advances this iterator. It will iterate 
   * over `this.iterator` until it reaches a Bus that stops at destination. 
   * Then, it will store that Bus in `next`.
   */
  private void advanceToNext() {
    while (baseIterator.hasNext()) {
      Bus nextIteration = baseIterator.next();
      if (nextIteration.goesTo(destination)) {
//        System.out.println(nextIteration);
//        System.out.println(nextIteration + " goes to " + destination);
        this.next = nextIteration;
//        System.out.println("current next: " + this.next);
        return;
      }
    }
    this.next = null;
  }

  /**
   * Returns true if there is another Bus (that goes to the destination) 
   * in this iterator, or false otherwise. This method should not change 
   * any of the fields of the iterator.
   * 
   * @return true if a call to next() will return another Bus; false otherwise.
   */
  public boolean hasNext() {
    return this.next != null;
  }

  /**
   * Returns the `next` bus and advances the iterator until the next bus 
   * it will return.
   * 
   * @return Buses from the iterator baseIterator that go to the destination stop.
   * @throws NoSuchElementException - if called when there is no next Bus. 
   * Note that you get this for free from the baseIterator. 
   * You do not need to import anything or throw anything yourself.
   */
  public Bus next() {
    Bus nextBus = this.next;
    advanceToNext();
    return nextBus;
  }

}
