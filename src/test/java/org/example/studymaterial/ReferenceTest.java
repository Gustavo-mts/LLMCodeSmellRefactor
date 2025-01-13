package org.example.studymaterial;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReferenceTest {
    Reference tReference = null;
    @BeforeEach
    void setUp() {
        tReference = new TextReference("Book", "English", 500, "pdf", "Open");
    }

    @Test
    @DisplayName("Set Title Test")
    @Order(1)
    void setTitle() {
        this.tReference.updateTitle("Book 2");
        assertEquals("Book 2", this.tReference.fetchTitle());
    }

    @Test
    @DisplayName("Get Title Test")
    @Order(2)
    void getTitle() {
        assertEquals("Book", this.tReference.fetchTitle());
    }

    @Test
    @DisplayName("Set Description Test")
    @Order(3)
    void setDescription() {
        this.tReference.updateDescription("A short book");
        assertEquals("A short book", this.tReference.fetchDescription());
    }

    @Test
    @DisplayName("Get Description Test")
    @Order(4)
    void getDescription() {
        assertNull(this.tReference.fetchDescription());
        this.tReference.updateDescription("A long description");
        assertEquals("A long description", this.tReference.fetchDescription());
    }
}