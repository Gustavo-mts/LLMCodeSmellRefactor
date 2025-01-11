package org.example.studysearch;

import org.example.studyregistry.StudyMaterial;

import java.util.ArrayList;
import java.util.List;

public class MaterialSearch implements Search<String>{


    private SearchLog searchLog = new SearchLog("Material Search");

    public MaterialSearch() {}

    @Override
    public List<String> search(String text) {
        return StudyMaterial.getStudyMaterial().searchWithLog(text, searchLog);
    }

    public SearchLog getSearchLog() {
        return searchLog;
    }

}
