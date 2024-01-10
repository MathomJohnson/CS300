import java.util.NoSuchElementException;

public class WorkoutBuilderTester {
  
  // checks for the correctness of the WorkoutBuilder.clear() method
 public static boolean testClear() {
   Exercise.resetIDNumbers();
   WorkoutBuilder workout = new WorkoutBuilder();
   workout.add(new Exercise(WorkoutType.PRIMARY, "bench press"));
   workout.add(new Exercise(WorkoutType.COOLDOWN, "jog"));
   workout.add(new Exercise(WorkoutType.WARMUP, "swim"));
   workout.clear();
   if (workout.isEmpty()) return true;
   return false;
 }

 // checks for the correctness of the WorkoutBuilder.add() method
 public static boolean testAddExercises() {
   Exercise.resetIDNumbers();
   WorkoutBuilder workout = new WorkoutBuilder();
   Exercise exercise1 = new Exercise(WorkoutType.PRIMARY, "bench press");
   Exercise exercise2 = new Exercise(WorkoutType.COOLDOWN, "jog");
   Exercise exercise3 = new Exercise(WorkoutType.WARMUP, "swim");
   workout.add(exercise1);
   workout.add(exercise2);
   workout.add(exercise3);
   if (!(workout.get(0).equals(exercise3))) return false;
   if (!(workout.get(1).equals(exercise1))) return false;
   if (!(workout.get(2).equals(exercise2))) return false;
   if (workout.getCooldownCount() != 1 || workout.getWarmupCount() != 1 
       || workout.getPrimaryCount() != 1) return false;
   return true;
 }

 // checks for the correctness of BOTH of the WorkoutBuilder.removeExercise() methods
 public static boolean testRemoveExercises() {
   Exercise.resetIDNumbers();
   WorkoutBuilder workout = new WorkoutBuilder();
   Exercise exercise1 = new Exercise(WorkoutType.PRIMARY, "bench press");
   Exercise exercise2 = new Exercise(WorkoutType.COOLDOWN, "jog");
   Exercise exercise3 = new Exercise(WorkoutType.WARMUP, "swim");
   workout.add(exercise1);
   workout.add(exercise2);
   workout.add(exercise3);
   if (!workout.removeExercise(1).equals(exercise1)) return false;
   if (workout.size() != 2 || !workout.get(1).equals(exercise2)) return false;
   if (!workout.removeExercise(WorkoutType.COOLDOWN).equals(exercise2)) return false;
   if (workout.size() != 1 || !workout.get(0).equals(exercise3)) return false;
   if (!workout.removeExercise(3).equals(exercise3)) return false;
   if (workout.size() != 0) return false;
   return true;
 }

 // checks for the correctness of the WorkoutBuilder.get() method
 public static boolean testGetExercises() {
   Exercise.resetIDNumbers();
   WorkoutBuilder workout = new WorkoutBuilder();
   Exercise exercise1 = new Exercise(WorkoutType.PRIMARY, "bench press");
   Exercise exercise2 = new Exercise(WorkoutType.COOLDOWN, "jog");
   Exercise exercise3 = new Exercise(WorkoutType.WARMUP, "swim");
   workout.add(exercise1);
   workout.add(exercise2);
   workout.add(exercise3);
   if (workout.get(0).equals(exercise3) && workout.get(1).equals(exercise1) 
       && workout.get(2).equals(exercise2)) {
     return true;
   }
   return false;
 }

 // a test suite method to run all your test methods
 public static boolean runAllTests() {
   boolean clear = testClear(), 
       add = testAddExercises(), 
       remove = testRemoveExercises(),
       get = testGetExercises();
   
   System.out.println("test clear: "+(clear?"pass":"FAIL"));
   System.out.println("test add: "+(add?"pass":"FAIL"));
   System.out.println("test remove: "+(remove?"pass":"FAIL"));
   System.out.println("test get: "+(get?"pass":"FAIL"));
   
   // TODO: add calls to any other test methods you write
   
   return clear && add && remove && get; // TODO: replace this with the correct value
 }

 public static void main(String[] args) {
   runAllTests();
   demo();
 }

 /**
  * Helper method to display the size and the count of different boxes stored in a list of boxes
  * 
  * @param list a reference to an InventoryList object
  * @throws NullPointerException if list is null
  */
 private static void displaySizeCounts(WorkoutBuilder list) {
   System.out.println("  Size: " + list.size() + ", warmupCount: " + list.getWarmupCount()
       + ", primaryCount: " + list.getPrimaryCount() + ", cooldownCount: " + list.getCooldownCount());
 }

 /**
  * Demo method showing how to use the implemented classes in P07 Inventory Storage System
  * 
  * @param args input arguments
  */
 public static void demo() {
   Exercise.resetIDNumbers();
   // Create a new empty WorkoutBuilder object
   WorkoutBuilder list = new WorkoutBuilder();
   System.out.println(list); // prints list's content
   displaySizeCounts(list); // displays list's size and counts
   // Add a primary exercise to an empty list
   list.add(new Exercise(WorkoutType.PRIMARY, "5k run")); // adds PRIMARY: 5k run (1)
   System.out.println(list); // prints list's content
   list.add(new Exercise(WorkoutType.WARMUP, "stretch")); // adds WARMUP: stretch (2) at the head of the list
   System.out.println(list); // prints list's content
   list.add(new Exercise(WorkoutType.PRIMARY, "bench press")); // adds PRIMARY: bench press (3)
   System.out.println(list); // prints list's content
   list.add(new Exercise(WorkoutType.WARMUP, "upright row")); // adds WARMUP: upright row (4) at the head of the list
   System.out.println(list); // prints list's content
   list.add(new Exercise(WorkoutType.WARMUP, "db bench")); // adds WARMUP: db bench (5) at the head of the list
   System.out.println(list); // prints list's content
   displaySizeCounts(list); // displays list's size and counts
   // Add more exercises to list and display its contents
   list.add(new Exercise(WorkoutType.COOLDOWN, "stretch")); // adds COOLDOWN: stretch (6) at the end of the list
   System.out.println(list); // prints list's content
   displaySizeCounts(list); // displays list's size and counts
   list.add(new Exercise(WorkoutType.COOLDOWN, "sit-ups")); // adds COOLDOWN: sit-ups (7) at the end of the list
   System.out.println(list); // prints list's content
   displaySizeCounts(list); // displays list's size and counts
   list.removeExercise(WorkoutType.COOLDOWN); // removes COOLDOWN: sit-ups (7) from the list
   System.out.println(list); // prints list's content
   displaySizeCounts(list); // displays list's size and counts
   list.add(new Exercise(WorkoutType.PRIMARY, "deadlift")); // adds PRIMARY: deadlift (8)
   System.out.println(list); // prints list's content
   displaySizeCounts(list); // displays list's size and counts
   list.removeExercise(WorkoutType.COOLDOWN); // removes COOLDOWN: stretch (6) from the list
   System.out.println(list); // prints list's content
   displaySizeCounts(list); // displays list's size and counts
   list.removeExercise(WorkoutType.WARMUP); // removes WARMUP: db bench (5)
   System.out.println(list); // prints list's content
   list.removeExercise(3); // removes PRIMARY: bench press (3) from the list
   System.out.println(list); // prints list's content
   displaySizeCounts(list); // displays list's size and counts
   try {
     list.removeExercise(25); // tries to remove box #25
   } catch (NoSuchElementException e) {
     System.out.println(e.getMessage());
   }
   System.out.println(list); // prints list's content
   displaySizeCounts(list); // displays list's size and counts
   // remove all warm-ups
   while (list.getWarmupCount() != 0) {
     list.removeExercise(WorkoutType.WARMUP);
   }
   System.out.println(list); // prints list's content
   displaySizeCounts(list); // displays list's size and counts
   list.removeExercise(1); // removes PRIMARY: 5k run (1) from the list -> empty list
   System.out.println(list); // prints list's content
   displaySizeCounts(list); // displays list's size and counts
   list.add(new Exercise(WorkoutType.COOLDOWN, "walk")); // adds COOLDOWN: walk (9) to the list
   System.out.println(list); // prints list's content
   displaySizeCounts(list); // displays list's size and counts
   list.removeExercise(8); // removes PRIMARY: deadlift (8) from the list
   System.out.println(list); // prints list's content
   displaySizeCounts(list); // displays list's size and counts
   list.removeExercise(WorkoutType.COOLDOWN); // removes COOLDOWN: walk (9) from the list
   System.out.println(list); // prints list's content
   displaySizeCounts(list); // displays list's size and counts
   list.add(new Exercise(WorkoutType.WARMUP, "pull-up")); // adds WARMUP: pull-up (10) to the list
   System.out.println(list); // prints list's content
   displaySizeCounts(list); // displays list's size and counts
   list.removeExercise(10); // removes WARMUP: pull-up (10) from the list
   System.out.println(list); // prints list's content
   displaySizeCounts(list); // displays list's size and counts
 }

}
