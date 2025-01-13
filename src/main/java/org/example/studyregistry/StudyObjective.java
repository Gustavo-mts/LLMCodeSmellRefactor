package org.example.studyregistry;

import java.time.LocalDateTime;
import java.util.List;

public class StudyObjective extends Registry {
    private String title;
    private String description;
    private String topic;
    private Integer practicedDays;
    private LocalDateTime startDate;
    private Double duration;
    private String objectiveInOneLine;
    private String objectiveFullDescription;
    private String motivation;

    public String getTitle() {
        return title;
    }

    public String getTopic() {
        return topic;
    }

    public Integer getPracticedDays() {
        return practicedDays;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public Double getDuration() {
        return duration;
    }

    public String getObjectiveInOneLine() {
        return objectiveInOneLine;
    }

    public String getObjectiveFullDescription() {
        return objectiveFullDescription;
    }

    public String getMotivation() {
        return motivation;
    }

    @Override
    public String toString() {
        return "StudyObjective [title:" + title + ", description:" + description + (topic != null ? ", topic:" + topic : "")
                + (practicedDays != null ? ", practicedDays:" + practicedDays : "") + (duration != null ? ", duration:" + duration : "")
                + (objectiveInOneLine != null ? ", objective summary:" + objectiveInOneLine : "") + (objectiveFullDescription != null ? ", objective full description:" + objectiveFullDescription : "")
                + (motivation != null ? ", motivation:" + motivation : "") + "]";
    }

    public StudyObjective(String title, String description) {
        this.title = title;
        this.description = description;
        this.name = title;
    }

    public void handleSetRegistry(Integer id, String name, Integer priority, boolean isActive) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.isActive = isActive;
    }

    public void handleSetTextualInfo(TextualInfo textualInfo) {
        this.title = textualInfo.getTitle();
        this.description = textualInfo.getDescription();
        this.topic = textualInfo.getTopic();
        this.objectiveInOneLine = textualInfo.getObjectiveInOneLine();
        this.objectiveFullDescription = textualInfo.getObjectiveFullDescription();
        this.motivation = textualInfo.getMotivation();
    }

    public void handleSetTime(TimeDetails timeDetails) {
        this.practicedDays = timeDetails.getPracticedDays();
        this.duration = timeDetails.getDuration();
        this.startDate = LocalDateTime.of(timeDetails.getYear(), timeDetails.getMonth(), timeDetails.getDay(), 0, 0);
    }

    public void handleSetObjective(RegistryDetails registryDetails, TextualInfo textualInfo, TimeDetails timeDetails) {
        handleSetRegistry(registryDetails.getId(), registryDetails.getName(), registryDetails.getPriority(), registryDetails.isActive());
        handleSetTextualInfo(textualInfo);
        handleSetTime(timeDetails);
    }

    public int handleSetObjectiveAdapter(List<Integer> intProperties, List<String> stringProperties, Double duration, boolean isActive) {
        RegistryDetails registryDetails = new RegistryDetails(intProperties.get(0), stringProperties.get(0), intProperties.get(1), isActive);
        TextualInfo textualInfo = new TextualInfo(stringProperties.get(1), stringProperties.get(2), stringProperties.get(3), stringProperties.get(4), stringProperties.get(5), stringProperties.get(6));
        TimeDetails timeDetails = new TimeDetails(intProperties.get(2), intProperties.get(3), intProperties.get(4), intProperties.get(5), duration);

        handleSetObjective(registryDetails, textualInfo, timeDetails);
        return registryDetails.getId();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static class RegistryDetails {
        private final Integer id;
        private final String name;
        private final Integer priority;
        private final boolean isActive;

        public RegistryDetails(Integer id, String name, Integer priority, boolean isActive) {
            this.id = id;
            this.name = name;
            this.priority = priority;
            this.isActive = isActive;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Integer getPriority() {
            return priority;
        }

        public boolean isActive() {
            return isActive;
        }
    }

    public static class TextualInfo {
        private final String title;
        private final String description;
        private final String topic;
        private final String objectiveInOneLine;
        private final String objectiveFullDescription;
        private final String motivation;

        public TextualInfo(String title, String description, String topic, String objectiveInOneLine, String objectiveFullDescription, String motivation) {
            this.title = title;
            this.description = description;
            this.topic = topic;
            this.objectiveInOneLine = objectiveInOneLine;
            this.objectiveFullDescription = objectiveFullDescription;
            this.motivation = motivation;
        }

        public boolean isTitleValid() {
            return title != null && !title.trim().isEmpty();
        }

        public boolean isDescriptionLong() {
            return description != null && description.length() > 100;
        }

        public String summarize() {
            return "Title: " + title + ", Topic: " + topic + ", Objective: " + objectiveInOneLine;
        }

        public String getDetailedInfo() {
            return "Description: " + description + "\nFull Objective: " + objectiveFullDescription + "\nMotivation: " + motivation;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getTopic() {
            return topic;
        }

        public String getObjectiveInOneLine() {
            return objectiveInOneLine;
        }

        public String getObjectiveFullDescription() {
            return objectiveFullDescription;
        }

        public String getMotivation() {
            return motivation;
        }

    }

    public static class TimeDetails {
        private final Integer practicedDays;
        private final int day;
        private final int month;
        private final int year;
        private final Double duration;

        public TimeDetails(Integer practicedDays, int day, int month, int year, Double duration) {
            this.practicedDays = practicedDays;
            this.day = day;
            this.month = month;
            this.year = year;
            this.duration = duration;
        }

        public boolean isFutureDate() {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime startDate = LocalDateTime.of(year, month, day, 0, 0);
            return startDate.isAfter(now);
        }

        public long getDaysUntilStart() {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime startDate = LocalDateTime.of(year, month, day, 0, 0);
            return java.time.Duration.between(now, startDate).toDays();
        }

        public long daysSinceStart() {
            LocalDateTime startDate = LocalDateTime.of(year, month, day, 0, 0);
            return java.time.Duration.between(startDate, LocalDateTime.now()).toDays();
        }

        public Integer getPracticedDays() {
            return practicedDays;
        }

        public int getDay() {
            return day;
        }

        public int getMonth() {
            return month;
        }

        public int getYear() {
            return year;
        }

        public Double getDuration() {
            return duration;
        }
    }
}