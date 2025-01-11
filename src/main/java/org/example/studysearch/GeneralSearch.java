package org.example.studysearch;

import org.example.studycards.CardManager;
import org.example.studyplanner.HabitTracker;
import org.example.studyplanner.TodoTracker;
import org.example.studyregistry.StudyMaterial;
import org.example.studyregistry.StudyTaskManager;

import java.util.ArrayList;
import java.util.List;

public class GeneralSearch implements Search<String> {
    private SearchLog searchLog = new SearchLog("General Search");
    private SearchCoordinator searchCoordinator = new SearchCoordinator(searchLog);

    public GeneralSearch() {}

    @Override
    public List<String> search(String text) {
        List<String> results = searchCoordinator.handleSearch(text);
        searchLog.logSearch(text);
        results.add("\nLogged in: " + searchLog.getLogName());
        return results;
    }

    public SearchLog getSearchLog() {
        return searchLog;
    }
}
