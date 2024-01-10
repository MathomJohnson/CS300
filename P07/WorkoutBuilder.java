import java.util.NoSuchElementException;

public class WorkoutBuilder implements ListADT <Exercise> {
  
  private int cooldownCount;
  private LinkedExercise head;
  private int primaryCount;
  private int size;
  private LinkedExercise tail;
  private int warmupCount;
  
  public int size() {
    return size;
  }
  
  public int getWarmupCount() {
    return warmupCount;
  }
  
  public int getPrimaryCount() {
    return primaryCount;
  }
  
  public int getCooldownCount() {
    return cooldownCount;
  }
  
  public boolean isEmpty() {
    return size == 0 && (head == null && tail == null);
  }
  
  public void clear() {
    head = null;
    tail = null;
    size = 0;
    warmupCount = 0;
    primaryCount = 0;
    cooldownCount = 0;
  }
  
  public int indexOf(Exercise findObject) {
    LinkedExercise current = head;
    int i = 0;
    
    while (current.getExercise() != null) {
      if (current.getExercise().equals(findObject)) {
        return i;
      }
      i++;
      current = current.getNext();
    }
    
    return -1;
  }

  public Exercise get(int index) {
    if (index >= size || index < 0) {
      throw new IndexOutOfBoundsException("That index is out of bounds for this list!");
    }
    LinkedExercise current = head;
    Exercise answer = null;
    
    for (int i = 0; i < index + 1; i++) {
      if (index == i) answer = current.getExercise();
      current = current.getNext();
    }
    
    return answer;
  }
  
  public void add(Exercise newObject) {
    WorkoutType type = newObject.getType();
    
    if (type == WorkoutType.WARMUP) {
      addAtIndex(0, newObject);
      size++;
      warmupCount++;
    }
    
    if (type == WorkoutType.COOLDOWN) {
      addAtIndex(size, newObject);
      cooldownCount++;
      size++;
    }
    
    if (type == WorkoutType.PRIMARY) {
      int index = warmupCount;
      addAtIndex(index, newObject);
      primaryCount++;
      size++;
    }
  }
  
  private void addAtIndex(int index, Exercise newObject) {
    LinkedExercise newExercise = new LinkedExercise(newObject);
    if (index == 0 && size == 0) {
      head = newExercise;
      tail = newExercise;
      return;
    }
    if (index == 0) {
      newExercise.setNext(head);
      head = newExercise;
      return;
    }
    if (index == size) {
      tail.setNext(newExercise);
      tail = newExercise;
      return;
    }
    LinkedExercise parent = head;
    for (int i = 0; i < index - 1; i++) {
      parent = parent.getNext();
    }
    LinkedExercise successor = parent.getNext();
    parent.setNext(newExercise);
    newExercise.setNext(successor);
  }

  public Exercise removeExercise(WorkoutType type) 
      throws NoSuchElementException {
    if (type == WorkoutType.WARMUP) {
      if (warmupCount < 1) {
        throw new NoSuchElementException();
      }
      warmupCount--;
      return removeAtIndex(0);
    }
    
    if (type == WorkoutType.COOLDOWN) {
      if (cooldownCount < 1) {
        throw new NoSuchElementException();
      }
      cooldownCount--;
      return removeAtIndex(size - 1);
    }
    
    int removeIndex = warmupCount;
    primaryCount--;
    return removeAtIndex(removeIndex);
  }
  
  /**
   * Helper method for removeExercise.
   * Removes the element at the given index in the linked list.
   * 
   * @param index
   * @return the exercise that was removed.
   */
  private Exercise removeAtIndex(int index) {
    if (index == 0) {
      Exercise removeExercise = head.getExercise();
      LinkedExercise newHead = head.getNext();
      if (size == 1) tail = newHead;
      head = newHead;
      size--;
      return removeExercise;
    }
    
    if (index == size - 1) {
      LinkedExercise current = head;
      for (int i = 0; i < size - 2; i++) {
        current = current.getNext();
      }
      Exercise removeExercise = current.getNext().getExercise();
      current.setNext(null);
      tail = current;
      size--;
      return removeExercise;
    }
    
    LinkedExercise parent = null;
    LinkedExercise current = head;
    for (int i = 0; i < index - 1; i++) {
      current = current.getNext();
    }
    parent = current;
    Exercise removeExercise = parent.getNext().getExercise();
    LinkedExercise successor = parent.getNext().getNext();
    parent.setNext(successor);
    size--;
    return removeExercise;
  }
  
  public Exercise removeExercise(int exerciseID)
      throws NoSuchElementException {
    LinkedExercise current = head;
    int i = 0;
    while (current != null) {
      if (current.getExercise().getExerciseID() == exerciseID) {
        return removeAtIndex(i);
      }
      current = current.getNext();
      i++;
    }
    throw new NoSuchElementException();
  }
  
  public String toString() {
    String message = "";
    LinkedExercise current = head;
    
    if (current == null) return message;
    
    while (current != null) {
      message = message.concat(current.toString());
      current = current.getNext();
    }
    
    return message;
  }

}
