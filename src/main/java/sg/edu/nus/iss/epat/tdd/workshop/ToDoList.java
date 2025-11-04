package sg.edu.nus.iss.epat.tdd.workshop;
import java.util.Collection;
import java.util.HashMap;

public class ToDoList {
   private HashMap<String, Task> tasks = new HashMap<String, Task>();

   public void addTask(Task task) {
      if (task == null) return;
      tasks.put(task.getDescription(), task);
   }

   public void completeTask(String description) {
      Task task = tasks.get(description);
      if (task != null) {
         task.setComplete(true);
      }
   }

   public boolean getStatus(String description) {
      Task task = tasks.get(description);
      return task != null && task.isComplete();
   }

   public Task getTask(String description) {
      return tasks.get(description);
   }

   public Task removeTask(String description) {
      return tasks.remove(description);
   }

   public Collection<Task> getAllTasks() {
      return tasks.values();
   }

   public Collection<Task> getCompletedTasks() {
      java.util.ArrayList<Task> completed = new java.util.ArrayList<>();
      for (Task task : tasks.values()) {
         if (task != null && task.isComplete()) {
            completed.add(task);
         }
      }
      return completed;
   }
}

