package org.example.studysearch;

import org.example.studycards.CardManager;
import org.example.studyplanner.HabitTracker;
import org.example.studyplanner.TodoTracker;
import org.example.studyregistry.StudyTaskManager;

import java.util.ArrayList;
import java.util.List;

public class RegistrySearch implements Search<String>{
    private SearchLog searchLog = new SearchLog("Registry Search");
    public RegistrySearch(){}

    @Override
    public List<String> search(String text) {
        return handleRegistrySearch(text);
    }

    public SearchLog getSearchLog() {
        return searchLog;
    }

    private List<String> handleRegistrySearch(String text) {
        SearchCoordinator coordinator = new SearchCoordinator();
        List<String> results = coordinator.aggregateSearchResults(text);

        // Delegate logging to SearchLog
        this.searchLog.logSearch(text);

        results.add("\nLogged in: " + this.searchLog.getLogName());
        return results;
    }

}
