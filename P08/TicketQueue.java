import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The class uses a queue data structure which keeps track of all of the 
 * ticket site users. It allows gives us functions to manipulate the queue.
 */
public class TicketQueue implements QueueADT<TicketSiteUser>, Iterable<TicketSiteUser> {

  //the linked node at the front of the queue
  private LinkedNode<TicketSiteUser> front;
  //the linked node at the back of the queue
  private LinkedNode<TicketSiteUser> back;
  //the number of TicketSiteUsers in the queue
  private int size;
  //the number of TicketSiteUsers in the queue
  private int capacity;
  
  /**
   * Creates an empty queue of TicketSiteUsers with the given capacity.
   * 
   * @param capacity the amount of spots in the queue
   */
  public TicketQueue(int capacity) {
    if (capacity < 1) throw new IllegalArgumentException();
    this.capacity = capacity;
    size = 0;
  }

  /**
   * Reports whether or not this queue is full.
   * 
   * @return true if queue is full; else false
   */
  public boolean isFull() {
    return size >= capacity;
  }

  /**
   * Reports if this queue is empty.
   * 
   * @return true if queue is empty; else false
   */
  public boolean isEmpty() {
    return size <= 0;
  }

  /**
   * Reports the current size of the queue.
   * 
   * @return the size of the queue
   */
  public int size() {
    return size;
  }

  /**
   * Reports the capacity of the queue.
   * 
   * @return the capacity of the array
   */
  public int capacity() {
    return capacity;
  }

  /**
   * Changes the capacity of the queue to the new capacity.
   * 
   * @param newCapacity the capacity to change the array to.
   */
  public void setCapacity(int newCapacity) {
    if (capacity < 1) throw new IllegalArgumentException();
    capacity = newCapacity;
  }

  /**
   * Adds the given TicketSiteUser to the back of the queue.
   * 
   * @param newObject the new TicketSiteUser object to add to the queue
   */
  public void enqueue(TicketSiteUser newObject) {
    if (isFull()) throw new IllegalStateException();
    if (!newObject.canBuyTicket()) throw new IllegalArgumentException();
    
    LinkedNode<TicketSiteUser> newUser = new LinkedNode<>(newObject);
    
    if (size <= 0) {
      back = newUser;
      front = newUser;
      size++;
    }
    else {
      back.setNext(newUser);
      back = newUser;
      size++;
    }
  }

  /**
   * Removes and returns the TicketSiteUser from the front of the queue.
   * 
   * @return the TicketSiteUser object that was removed
   */
  public TicketSiteUser dequeue() {
    if (isEmpty()) throw new NoSuchElementException();
    TicketSiteUser userRemoved = front.getData();
    front = front.getNext();
    size--;
    return userRemoved;
  }

  /**
   * Returns the TicketSiteUser from the front of the queue without removing it.
   * 
   * @return the TicketSiteUser at the front of the queue
   */
  public TicketSiteUser peek() {
    if (isEmpty()) throw new NoSuchElementException();
    return front.getData();
  }
  
  /**
   * Creates and returns and instance of a TicketQueueIterator for this queue.
   * 
   * @return an instance of a TicketQueueIterator for this queue
   */
  public Iterator<TicketSiteUser> iterator() {
    return new TicketQueueIterator(this);
  }
  
  /**
   * Creates and returns a deep copy (not the deepest copy) of this TicketQueue.
   * 
   * @return a deep copy of this TicketQueue
   */
  public TicketQueue deepCopy() {
    TicketQueue copy = new TicketQueue(capacity);
    for (TicketSiteUser user : this) {
      copy.enqueue(user);
    }
    return copy;
//    Iterator<TicketSiteUser> iterator = this.iterator();
//    while (iterator.hasNext()) {
//      copy.enqueue(iterator.next());
//    }
//    return copy;
  }

  @Override
  public String toString() {
    String s = "";
    LinkedNode<TicketSiteUser> runner = this.front;
    while (runner != null) {
      s += runner.getData() +"\n";
      runner = runner.getNext();
    }
    return s;
  }
}
