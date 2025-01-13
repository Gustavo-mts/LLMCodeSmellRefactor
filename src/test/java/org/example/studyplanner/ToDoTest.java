package org.example.studyplanner;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ToDoTest {
    ToDo todo = null;

    @BeforeEach
    void setUp() {
        this.todo = new ToDo(1, "Task", "Task to be done", 3);
    }

    @Test
    @DisplayName("To String Test")
    @Order(1)
    void testToString() {
        String todoString = this.todo.toString();
        assertTrue(todoString.contains("Task"));
        assertTrue(todoString.contains("Task to be done"));
        assertTrue(todoString.contains("1"));
        assertTrue(todoString.contains("Priority:3"));

    }

    @Test
    @DisplayName("Get Id Test")
    @Order(1)
    void getId() {
        assertEquals(this.todo.fetchId(), 1);
    }

    @Test
    @DisplayName("Set Id Test")
    @Order(1)
    void setId() {
        this.todo.updateId(2);
        assertEquals(this.todo.fetchId(), 2);
    }

    @Test
    @DisplayName("Get Title Test")
    @Order(1)
    void getTitle() {
        assertEquals(this.todo.fetchId(), "Task");
    }
}