
public class LinkedExercise {

  private Exercise exercise;
  private LinkedExercise next;
  
  public LinkedExercise(Exercise data, LinkedExercise next) {
    exercise = data;
    this.next = next;
  }
  
  public LinkedExercise(Exercise data) {
    exercise = data;
    next = null;
  }
  
  public LinkedExercise getNext() {
    return next;
  }
  
  public void setNext(LinkedExercise node) {
    next = node;
  }
  
  public Exercise getExercise() {
    return exercise;
  }
  
  public String toString() {
    if (next == null) {
      return exercise.toString() + " -> END";
    }
    return exercise.toString() + " -> ";
  }
}
