package org.example.studyplanner;

import java.text.MessageFormat;

public class ToDo implements PlannerMaterial{
    private Integer id;
    private String title;
    private String description;
    private int priority;

    public ToDo(Integer id, String title, String description, int priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return MessageFormat.format("[(Priority:{3}) ToDo {0}: {1}, {2}]", id, title, description, priority);
    }

    public int fetchId() {
        return id;
    }

    public void updateId(Integer id) {
        this.id = id;
    }

    public String fetchTitle() {
        return title;
    }

    public void updateitle(String title) {
        this.title = title;
    }

    public String fetchDescription() {
        return description;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public int fetchPriority() {
        return priority;
    }

    public void updatePriority(int priority) {
        this.priority = priority;
    }
}
