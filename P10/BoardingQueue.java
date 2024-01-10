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

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Array-based min-heap implementation of a priority boarding queue storing elements of type
 * Passenger. This class guarantees the min-heap invariant, so that:
 * 
 * The Passenger at the root should be the smallest Passenger in the queue, which corresponds to the
 * passenger having the highest priority.
 * 
 * Children always are greater than their parent.
 * 
 * The Passenger at the root of this min-heap priority queue must be dequeued (board the airplane)
 * next, meaning removed and returned by the dequeue method.
 * 
 * The BoardingQueue.peekBest() must return without removing the Passenger at the root of this
 * min-heap queue, if the queue is not empty.
 * 
 * We rely on the Passenger.compareTo() method to compare Passengers.
 * 
 * The root of a non-empty queue is always at index 0 of this array-heap.
 */
public class BoardingQueue implements PriorityQueueADT<Passenger> { // TODO Define this class to implement the PriorityQueueADT interface
  // This BoardingQueue stores elements of type Passenger, ONLY.

  // oversize array
  private Passenger[] heap;// array min-heap of Passengers representing this priority queue
  private int size; // size of this priority queue
  // The heap is an oversize array, meaning that the following should be ensured:
  // heap[i] == null for all valid indexes and size == 0 when the heap is EMPTY
  // If the heap is NOT empty:
  // heap[i] != null when i >= 0 && i < size
  // heap[i] == null when i >= size && i < heap.length

  /**
   * Constructs an empty BoardingQueue with the given capacity
   * 
   * @param capacity Capacity of this boarding queue
   * @throws IllegalArgumentException with a descriptive error message if the capacity is not a
   *                                  positive integer (greater than zero)
   */
  public BoardingQueue(int capacity) {
    // TODO implement this constructor of this class according to the details provided in its
    // javadocs
    if (capacity < 1) {
      throw new IllegalArgumentException("Capacity must be greater than 0!");
    }
    heap = new Passenger[capacity];
    size = 0;
  }


  /**
   * Returns the capacity of this BoardingQueue
   * 
   * @return the capacity of this BoardingQueue
   */
  public int capacity() {
    // TODO implement this method

    return heap.length; // default return statement
  }



  /**
   * Removes all the elements from this Boarding Queue
   */
  public void clear() {
    for (int i = 0; i < size; i++) {
      heap[i] = null;
    }
    size = 0;
  }


  /**
   * Checks whether this BoardingQueue is full
   * 
   * @return true if this boarding queue is full
   */
  public boolean isFull() {
    return size == heap.length;
  }


  // toArray() method PROVIDED
  /**
   * Returns a deep copy of the array-heap of this BoardingQueue. This method can be used for
   * testing purposes.
   * 
   * This method can be used for testing purposes.
   * 
   * @return a deep copy of the array-heap storing the Passengers in this queue
   * @throws a NullPointerException if the heap array of this BoardingQueue is null
   */
  public Passenger[] toArray() {
    return Arrays.copyOf(this.heap, this.heap.length);
  }


  /**
   * Returns a deep copy of this BoardingQueue containing all of its elements in the same order.
   * This method does not return the deepest copy, meaning that you do not need to duplicate
   * Passengers. Only the instance of the heap (including the array and its size) will be
   * duplicated.
   * 
   * @return a deep copy of this BoardingQueue. The returned new boarding queue (the deep copy) has
   *         the same length and size as this queue.
   */
  public BoardingQueue deepCopy() {
    BoardingQueue bq = new BoardingQueue(heap.length);
    bq.heap = toArray();
    return bq;
  }


  // toString() method PROVIDED, but commented out
  // TODO uncomment the implementation details of the below BoardingQueue.toString() method after
  // you implement the isEmpty() and dequeue() methods
  /**
   * Returns a String representing this boarding queue, where each Passenger in the queue is listed
   * on a separate line, in order from smallest to greatest, meaning in their boarding order.
   * 
   * @return a String representing this boarding queue, and an empty String "" if this queue is
   *         empty.
   */
   @Override
   public String toString() {
   String s = "";
   BoardingQueue deepCopy = this.deepCopy();
   while (!deepCopy.isEmpty()) {
   s += deepCopy.dequeue().toString() + "\n";
   }
   return s.trim();
  
   }

  /**
   * Restores the min-heap of the priority queue by percolating its root down the tree. If the
   * element at the given index does not violate the min-heap ordering property (it is smaller than
   * its smallest child), then this method does not modify the heap. Otherwise, if there is a heap
   * violation, then swap the element with the correct child and continue percolating the element
   * down the heap.
   * 
   * We assume that index is in bounds (greater or equal to zero and less than size).
   * 
   * @param index index of the element in the heap to percolate downwards
   */
  protected void percolateDown(int index) {
    // TODO implement this method with respect to the details provided in its javadocs style method
    // header
    // We recommend implementing a recursive version of this method to get more practice on
    // recursive thinking
    if (leftChildIdx(index) > size() - 1) {
      return;
    }
    else if (rightChildIdx(index) > size() - 1) {
      if (heap[index].compareTo(heap[leftChildIdx(index)]) < 0) {
        return;
      }
    }
    else {
      if (heap[index].compareTo(heap[leftChildIdx(index)]) < 0
          && heap[index].compareTo(heap[rightChildIdx(index)]) < 0) {
        return;
      }
    }
    
    Passenger left = heap[leftChildIdx(index)];
    Passenger right = heap[rightChildIdx(index)];
    int smallestIdx = -1;
    
    if (left != null) {
      if (left.compareTo(right) < 0) smallestIdx = leftChildIdx(index);
      else if (right != null) smallestIdx = rightChildIdx(index);
    } 
    else smallestIdx = rightChildIdx(index);
    
    Passenger parent = heap[index];
    heap[index] = heap[smallestIdx];
    heap[smallestIdx] = parent;
    
    percolateDown(smallestIdx);
  }

  // TODO
  /**
   * Restores the min-heap invariant of this priority queue by percolating a leaf up the heap. If
   * the element at the given index does not violate the min-heap invariant (it is greater than its
   * parent), then this method does not modify the heap. Otherwise, if there is a heap violation,
   * swap the element with its parent and continue percolating the element up the heap. We assume
   * that index is in bounds (greater or equal to zero and less than size).
   * 
   * @param index index of the element in the heap to percolate upwards
   */
  protected void percolateUp(int index) {
    // TODO implement this method with respect to the details provided in its javadocs style method
    // header
    // We recommend implementing a recursive version of this method to get more practice on
    // recursive thinking
    if (heap[index].compareTo(heap[parentIdx(index)]) > 0
        || index == 0) {
      return;
    }
    
    Passenger parent = heap[parentIdx(index)];
    heap[parentIdx(index)] = heap[index];
    heap[index] = parent;
    
    percolateUp(parentIdx(index));
  }

  private static int parentIdx(int childIdx) {
    return (childIdx - 1) / 2;
  }
  
  private static int leftChildIdx(int parentIdx) {
    return (parentIdx * 2) + 1;
  }
  
  private static int rightChildIdx(int parentIdx) {
    return (parentIdx * 2) + 2;
  }
  
  /**
   * checks if the priority queue is empty
   * 
   * @return true if the priority queue is empty, false otherwise
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Returns the number of elements in this priority queue
   * 
   * @return the number of elements in this priority queue
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Returns without removing the element with the highest priority
   * 
   * @return the element with the highest priority
   * @throws NoSuchElementException if this priority queue is empty
   */
  @Override
  public Passenger peekBest() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return heap[0];
  }

  /**
   * Inserts a new element e into this priority queue. This method returns false if the queue is
   * already full.
   * 
   * @param e the new element to insert into this priority queue
   * 
   * @return true if the element e was added to this queue, else false
   * @throws NullPointerException if the element e is null
   */
  @Override
  public boolean enqueue(Passenger e) {
    if (e == null) {
      throw new NullPointerException();
    }
    if (size == heap.length) return false;
    heap[size] = e;
    size++;
    percolateUp(size - 1);
    return true;
  }

  /**
   * Removes and returns the element with the highest priority
   * 
   * @return the removed element
   * @throws NoSuchElementException if this priority queue is empty
   */
  @Override
  public Passenger dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    Passenger dequeued = heap[0];
    if (size() == 1) {
      size--;
      heap[0] = null;
      return dequeued;
    }
    heap[0] = heap[size - 1];
    heap[size - 1] = null;
    percolateDown(0);
    size--;
    return dequeued;
  }

  // TODO implement the abstract methods defined in the PriorityQueueADT with respect to the details
  // in their javadocs.
  // Be sure that the enqueue() method takes an input of type Passenger
  // The return type of peekBest() and dequeue() methods MUST be Passenger
  // The details of what every method is supposed to do is provided in the details of the javadocs
  // style method headers of the abstract methods defined in the PriorityQueueADT generic interface.


}
