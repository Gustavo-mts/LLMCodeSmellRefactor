package org.example.studyregistry;

import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TaskTest {
    Task task = null;

    @BeforeEach
    void setUp() {
        task = new Task("Task", "Task to do", "Pedro", LocalDateTime.of(2025, 1, 1, 1, 1, 1));
    }

    @Test
    @Order(1)
    @DisplayName("Get Title Test")
    void getTitle() {
        assertEquals("Task", task.fetchTitle());
    }

    @Test
    @Order(1)
    @DisplayName("Set Title Test")
    void setTitle() {
        task.updateTitle("Task");
        assertEquals("Task", task.fetchTitle());
    }

    @Test
    @Order(1)
    @DisplayName("Get Description Test")
    void getDescription() {
        assertEquals("Task to do", task.fetchDescription());
    }

    @Test
    @Order(1)
    @DisplayName("Set Description Test")
    void setDescription() {
        task.updateDescription("Task");
        assertEquals("Task", task.fetchDescription());
    }
}