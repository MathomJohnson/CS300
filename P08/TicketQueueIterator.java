import java.util.Iterator;

/**
 * This class creates an iterator for our ticket queues.
 */
public class TicketQueueIterator implements Iterator<TicketSiteUser>{

  //deep copy of a TicketQueue
  private TicketQueue userQueue;
  
  /**
   * Constructor for a TicketQueueIterator that sets the data field to be a deep copy of the given queue.
   * 
   * @param queue the queue to create an iterator for
   */
  public TicketQueueIterator(TicketQueue queue) {
    if (queue == null) throw new IllegalArgumentException();
    userQueue = queue.deepCopy();
  }
  
  /**
   * Determines whether or not there is another TicketSiteUser in the queue.
   * 
   * @return true if queue has a next element; else false
   */
  public boolean hasNext() {
    return userQueue.size() > 0;
  }
  
  /**
   * Returns the next TicketSiteUser in the queue, based on the order from front to back.
   * 
   * @return the TicketSiteUser that was just dequeued
   */
  public TicketSiteUser next() {
    return userQueue.dequeue();
  }

}
