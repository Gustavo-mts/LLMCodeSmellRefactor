package org.example.studyplanner;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TodoTrackerTest {
    static TodoTracker todoTracker = TodoTracker.getInstance();
    static List<Integer> toDoIds = new ArrayList<>();

    @BeforeAll
    static void setUpBeforeAll(){
        insertTodos();
    }
    static void insertTodos(){
        toDoIds.add(todoTracker.addToDo("Test 1", "Test 1 Description", 1));
        toDoIds.add(todoTracker.addToDo("Test 2", "Test 2 Description", 2));
        toDoIds.add(todoTracker.addToDo("Test 3", "Test 3 Description", 3));
    }

    @Test
    @Order(1)
    @DisplayName("Add ToDo Test")
    void addToDo() {
        Integer id = todoTracker.addToDo("Test 4", "Test 4 Description", 1);
        ToDo todo = todoTracker.getToDoById(id);
        assertEquals("Test 4", todo.fetchTitle());
        assertEquals("Test 4 Description", todo.fetchDescription());
        assertEquals(1, todo.fetchPriority());
    }

    void verifyTestToString(int id, String response){
        ToDo todo = todoTracker.getToDoById(id);
        assertTrue(response.contains(todo.fetchTitle()));
        assertTrue(response.contains(todo.fetchDescription()));
        assertTrue(response.contains(String.valueOf("(Priority:"+todo.fetchPriority()+")")));
    }

    @Test
    @Order(2)
    @DisplayName("To String Test")
    void toStringTest() {
        String response = todoTracker.toString();
        for(Integer id : toDoIds){
            verifyTestToString(id, response);
        }
    }

    int addToDoToSearch(String title, String description, int priority){
        return todoTracker.addToDo(title, description, priority);
    }

    boolean verifySearchResponse(String searching, List<String> response) {
        for(String object : response){
            if(object.contains(searching)){
                return true;
            }
        }
        return false;
    }

    @Test
    @Order(3)
    @DisplayName("Search Title In ToDos Test")
    void searchTitleInTodos() {
        int id = addToDoToSearch("Test ToDo Title", "Test ToDo", 3);
        List<String> response = todoTracker.searchInTodos("ToDo Title");
        assertTrue(verifySearchResponse("ToDo Title", response));
    }

    @Test
    @Order(3)
    @DisplayName("Search Description In ToDos Test")
    void searchDescriptionInTodos() {
        int id = addToDoToSearch("Test ToDo", "Test ToDo Description", 3);
        List<String> response = todoTracker.searchInTodos("ToDo Description");
        assertTrue(verifySearchResponse("ToDo Description", response));
    }
}