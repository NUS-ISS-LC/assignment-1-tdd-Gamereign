package sg.edu.nus.iss.epat.tdd.workshop;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class ToDoListTest  {
    // Define Test Fixtures

    public ToDoListTest() {
        super();
    }

    @Before
    public void setUp() throws Exception {
        // Initialise Test Fixtures
        Task task = new Task("Buy milk");
        task.setComplete(false);
    }

    @After
    public void tearDown() throws Exception {
        // Uninitialise test Fixtures
    }

    @Test
    public void testAddTask() {
        sg.edu.nus.iss.epat.tdd.workshop.ToDoList list = new sg.edu.nus.iss.epat.tdd.workshop.ToDoList();
        sg.edu.nus.iss.epat.tdd.workshop.Task t = new sg.edu.nus.iss.epat.tdd.workshop.Task("Buy milk");
        list.addTask(t);

        org.junit.Assert.assertNotNull("Task should be retrievable after add", list.getTask("Buy milk"));
        org.junit.Assert.assertEquals("Description should match", "Buy milk", list.getTask("Buy milk").getDescription());

        int count = 0;
        for (sg.edu.nus.iss.epat.tdd.workshop.Task task : list.getAllTasks()) count++;
        org.junit.Assert.assertEquals("There should be exactly 1 task in the list", 1, count);
    }

    @Test
    public void testGetStatus() {
        sg.edu.nus.iss.epat.tdd.workshop.ToDoList list = new sg.edu.nus.iss.epat.tdd.workshop.ToDoList();
        list.addTask(new sg.edu.nus.iss.epat.tdd.workshop.Task("A")); // default not complete
        list.addTask(new sg.edu.nus.iss.epat.tdd.workshop.Task("B", true)); // constructed as complete

        org.junit.Assert.assertFalse("A should be not completed", list.getStatus("A"));
        org.junit.Assert.assertTrue("B should be completed", list.getStatus("B"));
        org.junit.Assert.assertFalse("Unknown task should return false status", list.getStatus("Unknown"));
    }

    @Test
    public void testRemoveTask() {
        sg.edu.nus.iss.epat.tdd.workshop.ToDoList list = new sg.edu.nus.iss.epat.tdd.workshop.ToDoList();
        list.addTask(new sg.edu.nus.iss.epat.tdd.workshop.Task("Rm"));
        int before = 0;
        for (sg.edu.nus.iss.epat.tdd.workshop.Task task : list.getAllTasks()) before++;

        sg.edu.nus.iss.epat.tdd.workshop.Task removed = list.removeTask("Rm");
        org.junit.Assert.assertNotNull("removeTask should return the removed Task", removed);
        org.junit.Assert.assertEquals("Removed task description should match", "Rm", removed.getDescription());

        org.junit.Assert.assertNull("getTask should return null after removal", list.getTask("Rm"));
        int after = 0;
        for (sg.edu.nus.iss.epat.tdd.workshop.Task task : list.getAllTasks()) after++;
        org.junit.Assert.assertEquals("Number of tasks should decrease by 1 after removal", before - 1, after);
    }

    @Test
    public void testGetCompletedTasks() {
        sg.edu.nus.iss.epat.tdd.workshop.ToDoList list = new sg.edu.nus.iss.epat.tdd.workshop.ToDoList();
        list.addTask(new sg.edu.nus.iss.epat.tdd.workshop.Task("a"));
        list.addTask(new sg.edu.nus.iss.epat.tdd.workshop.Task("b", true));
        list.addTask(new sg.edu.nus.iss.epat.tdd.workshop.Task("c"));
        list.addTask(new sg.edu.nus.iss.epat.tdd.workshop.Task("d", true));
        // Also mark one via completeTask
        list.addTask(new sg.edu.nus.iss.epat.tdd.workshop.Task("e"));
        list.completeTask("e");

        java.util.Collection<sg.edu.nus.iss.epat.tdd.workshop.Task> completed = list.getCompletedTasks();
        org.junit.Assert.assertNotNull("getCompletedTasks should not be null", completed);

        int compCount = 0;
        java.util.Set<String> compDescs = new java.util.HashSet<>();
        for (sg.edu.nus.iss.epat.tdd.workshop.Task task : completed) {
            compCount++;
            compDescs.add(task.getDescription());
            org.junit.Assert.assertTrue("Each returned task should be marked complete", task.isComplete());
        }

        org.junit.Assert.assertEquals("Should have 3 completed tasks (b, d, e)", 3, compCount);
        org.junit.Assert.assertTrue("Should contain b", compDescs.contains("b"));
        org.junit.Assert.assertTrue("Should contain d", compDescs.contains("d"));
        org.junit.Assert.assertTrue("Should contain e", compDescs.contains("e"));
    }
}