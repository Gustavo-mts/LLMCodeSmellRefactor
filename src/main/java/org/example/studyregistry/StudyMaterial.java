package org.example.studyregistry;
import org.example.studymaterial.AudioReference;
import org.example.studymaterial.Reference;
import org.example.studymaterial.TextReference;
import org.example.studymaterial.VideoReference;
import org.example.studysearch.SearchLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudyMaterial{
    List<Reference> references;
    private static StudyMaterial studyMaterial;
    private Map<String, Integer> referenceCount;

    private StudyMaterial(){
        references = new ArrayList<Reference>();
    }

    public static StudyMaterial getStudyMaterial(){
        if(studyMaterial == null){
            studyMaterial = new StudyMaterial();
        }
        return studyMaterial;
    }

    public void addReference(Reference ref){
        references.add(ref);
    }

    List<Reference> getReferences(){
        return references;
    }

    public List<Reference> getTypeReference(Reference type){
        List<Reference> response = new ArrayList<>();
        for(Reference reference : references){
            if(reference.getClass() == type.getClass()){
                response.add(reference);
            }
        }
        return response;
    }

    public void setReferenceCount(Map<String, Integer> referenceCount) {
        this.referenceCount = referenceCount;
    }

    public List<String> searchInMaterials(String text){
        List<String> response = new ArrayList<>();
        for(Reference reference : references){
            String mix = (reference.getTitle() != null ? reference.getTitle() : "") + (reference.getDescription() != null ? reference.getDescription() : "");
            if (mix.toLowerCase().contains(text.toLowerCase())){
                response.add(reference.getTitle());
            }
        }
        return response;
    }

    public Map<String, Integer> getReferenceCountMap() {
        Map<String, Integer> response = initializeReferenceMap();

        for (Reference reference : references) {
            processAudioReference(reference, response);
            processVideoReference(reference, response);
            processTextReference(reference, response);
        }

        setReferenceCount(response);
        return response;
    }

    private Map<String, Integer> initializeReferenceMap() {
        Map<String, Integer> response = new HashMap<>();
        response.put("Audio References", 0);
        response.put("Video References", 0);
        response.put("Text References", 0);
        return response;
    }

    private void processAudioReference(Reference reference, Map<String, Integer> response) {
        if (reference.getClass() == AudioReference.class) {
            Integer audioCount = response.get("Audio References");
            response.put("Audio References", audioCount + 1);
        }
    }

    private void processVideoReference(Reference reference, Map<String, Integer> response) {
        if (reference.getClass() == VideoReference.class) {
            if (((VideoReference) reference).handleStreamAvailability()) {
                Integer videoCount = response.get("Video References");
                response.put("Video References", videoCount + 1);
            }
        }
    }

    private void processTextReference(Reference reference, Map<String, Integer> response) {
        if (reference.getClass() == TextReference.class) {
            if (((TextReference) reference).handleTextAccess()) {
                Integer textCount = response.get("Text References");
                response.put("Text References", textCount + 1);
            }
        }
    }


    public List<String> searchWithLog(String text, SearchLog searchLog) {
        List<String> results = searchInMaterials(text);
        return searchLog.appendLogDetails(results, text);
    }
}
