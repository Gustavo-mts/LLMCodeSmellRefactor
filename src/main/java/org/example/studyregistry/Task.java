package org.example.studyregistry;

import java.time.LocalDateTime;

public class Task extends Registry {
    private String title;
    private String description;
    private String author;
    private LocalDateTime date;

    public Task(String title, String description, String author, LocalDateTime date) {
        this.title = title;
        this.name = title;
        this.description = description;
        this.author = author;
        this.date = date;
    }



    // Specific method to update the title
    public void updateTitle(String newTitle) {
        if (newTitle != null && !newTitle.isEmpty()) {
            this.title = newTitle;
        }
    }

    // Specific method to update the description
    public void updateDescription(String newDescription) {
        if (newDescription != null && !newDescription.isEmpty()) {
            this.description = newDescription;
        }
    }

    // Specific method to assign an author
    public void assignAuthor(String newAuthor) {
        if (newAuthor != null && !newAuthor.isEmpty()) {
            this.author = newAuthor;
        }
    }

    // Specific method to set the date
    public void scheduleFor(LocalDateTime newDate) {
        if (newDate != null && newDate.isAfter(LocalDateTime.now())) {
            this.date = newDate;
        }
    }

    // Specific method to check if the task is scheduled
    public boolean isScheduled() {
        return date != null;
    }

    // Specific method to get a summary of the task
    public String generateTaskSummary() {
        return String.format("Task: %s%nDescription: %s%nAuthor: %s%nScheduled Date: %s",
                title, description, author, date != null ? date.toString() : "Not scheduled");
    }

    public String fetchTitle() {
        return title;
    }

    public String fetchDescription() {
        return description;
    }
}
