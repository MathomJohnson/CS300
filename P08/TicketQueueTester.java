import java.util.NoSuchElementException;

public class TicketQueueTester {
  
  public static boolean testPeek() {
    TicketSiteUser user1 = new TicketSiteUser("Mathom", "12345", "1111111111111111");
    TicketSiteUser user2 = new TicketSiteUser("Gunter", "12345", "1111111111111111");
    TicketSiteUser user3 = new TicketSiteUser("Johnson", "12345", "1111111111111111");
    user1.login("Mathom", "12345");
    user2.login("Gunter", "12345");
    user3.login("Johnson", "12345");

    TicketQueue queue = new TicketQueue(3);
    
    queue.enqueue(user1);
    if (queue.peek() != user1) return false;
    
    queue.enqueue(user2);
    if (queue.peek() != user1) return false;
    
    queue.dequeue();
    if (queue.peek() != user2) return false;
    
    queue.enqueue(user3);
    if (queue.peek() != user2) return false;
    
    queue.dequeue();
    if (queue.peek() != user3) return false;
    
    return true;
  }
  
  public static boolean testEnqueue() {
    TicketSiteUser user1 = new TicketSiteUser("Mathom", "12345", "1111111111111111");
    TicketSiteUser user2 = new TicketSiteUser("Gunter", "12345", "1111111111111111");
    TicketSiteUser user3 = new TicketSiteUser("Johnson", "12345", "1111111111111111");
    TicketSiteUser user4 = new TicketSiteUser("Tim", "12345", "1111111111111111");

    user1.login("Mathom", "12345");
    user3.login("Johnson", "12345");
    user4.login("Tim", "12345");
    
    TicketQueue queue = new TicketQueue(3);
    
    queue.enqueue(user1);
    if (queue.size() != 1) return false;
    
    try {
      queue.enqueue(user2);
      return false;
    } catch (IllegalArgumentException e) {}
    
    user2.login("Gunter", "12345");
    queue.enqueue(user2);
    queue.enqueue(user3);
    if (queue.size() != 3) return false;
    
    try {
      queue.enqueue(user4);
      return false;
    } catch (IllegalStateException e) {}
    
    return true;
  }
  
  public static boolean testDequeue() {
    TicketSiteUser user1 = new TicketSiteUser("Mathom", "12345", "1111111111111111");
    TicketSiteUser user2 = new TicketSiteUser("Gunter", "12345", "1111111111111111");
    TicketSiteUser user3 = new TicketSiteUser("Johnson", "12345", "1111111111111111");

    user1.login("Mathom", "12345");
    user2.login("Gunter", "12345");
    user3.login("Johnson", "12345");
    
    TicketQueue queue = new TicketQueue(3);
    
    try {
      queue.dequeue();
      return false;
    } catch (NoSuchElementException e) {}
    
    queue.enqueue(user1);
    queue.enqueue(user2);
    queue.enqueue(user3);
    if (queue.size() != 3) return false;
    
    queue.dequeue();
    if (queue.size() != 2) return false;
    
    queue.dequeue();
    queue.dequeue();
    if (queue.size() != 0) return false;
    
    return true;
  }
  
  public static boolean testConstructor() {
    try {
      TicketQueue queue = new TicketQueue(0);
      return false;
    } catch (IllegalArgumentException e) {}
    
    TicketQueue queue = new TicketQueue(3);
    
    if (!queue.isEmpty() || queue.isFull()) return false;
    if (queue.size() != 0 || queue.capacity() != 3) return false;
    if (!queue.toString().equals("")) return false;
    
    TicketSiteUser user1 = new TicketSiteUser("Mathom", "12345", "1111111111111111");
    TicketSiteUser user2 = new TicketSiteUser("Gunter", "12345", "1111111111111111");
    TicketSiteUser user3 = new TicketSiteUser("Johnson", "12345", "1111111111111111");
    user1.login("Mathom", "12345");
    user2.login("Gunter", "12345");
    user3.login("Johnson", "12345");
    queue.enqueue(user1);
    queue.enqueue(user2);
    queue.enqueue(user3);
    
    if (queue.size() != 3 || queue.capacity() != 3) return false;
    if (queue.isEmpty() || !queue.isFull()) return false;
    String msg = "Mathom: *\nGunter: *\nJohnson: *\n";
    if (!queue.toString().equals(msg)) return false;
    
    return true;
  }
  
  public static boolean testIterator() {
    TicketQueue queue = new TicketQueue(3);
    TicketSiteUser user1 = new TicketSiteUser("Mathom", "12345", "1111111111111111");
    TicketSiteUser user2 = new TicketSiteUser("Gunter", "12345", "1111111111111111");
    TicketSiteUser user3 = new TicketSiteUser("Johnson", "12345", "1111111111111111");
    user1.login("Mathom", "12345");
    user2.login("Gunter", "12345");
    user3.login("Johnson", "12345");
    queue.enqueue(user1);
    queue.enqueue(user2);
    queue.enqueue(user3);
    
    TicketSiteUser[] expected = {user1, user2, user3};
    int i = 0;
    for (TicketSiteUser user : queue) {
      if (!user.equals(expected[i])) return false;
      i++;
    }
    if (queue.size()+1 != i) return false;
    String expectedString = "Mathom: *\\nGunter: *\\nJohnson: *\\n";
    if (queue.size() != 3 || !queue.toString().equals(expectedString)) {
      return false;
    }
    
    return true;
  }
  
  private static boolean runAllTests() {
    return testPeek() && testEnqueue() && testDequeue() 
        && testConstructor() && testIterator();
  }
  
  public static void main(String[] args) {
    System.out.println(runAllTests());
  }
}
