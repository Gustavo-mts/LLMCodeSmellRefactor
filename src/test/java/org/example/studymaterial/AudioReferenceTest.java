package org.example.studymaterial;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AudioReferenceTest {
    AudioReference audioReference = new AudioReference(AudioReference.AudioQuality.LOW);

    @BeforeEach
    void setUp() {
        List<String> properties = List.of("Title Test", "Description Test", "Link Test", "AccessRights Test", "License Test", "Language Test");
        List<Integer> intProperties = List.of(10, 2000, 500);
        editAudioReferenceTest(properties, intProperties, AudioReference.AudioQuality.LOW, true);
    }

    void editAudioReferenceTest(List<String> properties, List<Integer> intProperties, AudioReference.AudioQuality quality, boolean isDownloadable) {

        this.audioReference.editAudioAdapter(properties, intProperties, quality, isDownloadable);
    }

    @Test
    @Order(1)
    @DisplayName("Edit Audio String Properties Adapter Test")
    void editAudioStringPropertiesAdapter() {
        assertEquals("AccessRights Test", audioReference.fetchAccessRights());
        assertEquals("Description Test", audioReference.fetchDescription());
        assertEquals("Language Test", audioReference.fetchLanguage());
        assertEquals("License Test", audioReference.fetchLicense());
        assertEquals("Title Test", audioReference.fetchTitle());
        assertEquals("Link Test", audioReference.fetchLink());

    }

    @Test
    @Order(2)
    @DisplayName("Edit Audio Integer, Boolean And Quality Properties Adapter Test")
    void editAudioIntegerBooleanQualityAdapter() {
        assertEquals(AudioReference.AudioQuality.LOW, audioReference.getAudioQuality());
        assertEquals(2000, audioReference.fetchViewCount());
        assertEquals(500, audioReference.fetchShareCount());
        assertEquals(10, audioReference.fetchRating());
        assertTrue(audioReference.checkIfDownloadable());
    }

    public void changeEditTest(){
        List<String> properties = List.of("Title2 Test", "Description2 Test", "Link2 Test", "AccessRights2 Test", "License2 Test", "Language2 Test");
        List<Integer> intProperties = List.of(20, 4000, 1000);
        editAudioReferenceTest(properties, intProperties, AudioReference.AudioQuality.VERY_HIGH, false);
    }


    @Test
    @Order(3)
    @DisplayName("Change Edit Audio String Properties Adapter Test")
    void changeEditAudioIntegerAndBooleanAdapter() {
        changeEditTest();
        assertEquals("Title2 Test", audioReference.fetchTitle());
        assertEquals("Description2 Test", audioReference.fetchDescription());
        assertEquals("Link2 Test", audioReference.fetchLink());
        assertEquals("AccessRights2 Test", audioReference.fetchAccessRights());
        assertEquals("License2 Test", audioReference.fetchLicense());
        assertEquals("Language2 Test", audioReference.fetchLanguage());
    }

    @Test
    @Order(4)
    @DisplayName("Change Edit Audio Integer, Boolean And Quality Properties Adapter Test")
    void changeEditAudioIntegerBooleanQualityAdapter() {
        changeEditTest();
        assertEquals(AudioReference.AudioQuality.VERY_HIGH, audioReference.getAudioQuality());
        assertEquals(4000, audioReference.fetchViewCount());
        assertEquals(1000, audioReference.fetchShareCount());
        assertEquals(20, audioReference.fetchRating());
        assertFalse(audioReference.checkIfDownloadable());
    }
}