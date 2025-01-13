package org.example.studyregistry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudyTaskManager {
    private static StudyTaskManager instance;
    private List<Registry> registryList;
    private List<String> weekResponsibilities;
    private WeekManager weekManager;

    private StudyTaskManager() {
        this.registryList = new ArrayList<>();
        this.weekResponsibilities = new ArrayList<>();
        this.weekManager = new WeekManager();
    }

    public static StudyTaskManager getStudyTaskManager() {
        if (instance == null) {
            instance = new StudyTaskManager();
        }
        return instance;
    }

    public List<String> getWeekResponsibilities() {
        return weekResponsibilities;
    }

    class Objective {
        private String title;
        private String description;

        public Objective(String title, String description) {
            this.title = title;
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }
    }

    class Material {
        private String topic;
        private String format;

        public Material(String topic, String format) {
            this.topic = topic;
            this.format = format;
        }

        public String getTopic() {
            return topic;
        }

        public String getFormat() {
            return format;
        }
    }

    class Reminder {
        private String title;
        private String description;

        public Reminder(String title, String description) {
            this.title = title;
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }
    }


    // Encapsulating the week setup data
    class WeekSetup {
        private String planName;
        private Objective objective;
        private Material material;
        private String goal;
        private Reminder reminder;
        private String mainTaskTitle;
        private String mainHabit;
        private String mainCardStudy;

        public WeekSetup(String planName, Objective objective, Material material, String goal, Reminder reminder,
                         String mainTaskTitle, String mainHabit, String mainCardStudy) {
            this.planName = planName;
            this.objective = objective;
            this.material = material;
            this.goal = goal;
            this.reminder = reminder;
            this.mainTaskTitle = mainTaskTitle;
            this.mainHabit = mainHabit;
            this.mainCardStudy = mainCardStudy;
        }

        public List<String> toResponsibilitiesList() {
            return Arrays.asList(
                    planName,
                    objective.getTitle(),
                    objective.getDescription(),
                    material.getTopic(),
                    material.getFormat(),
                    goal,
                    reminder.getTitle(),
                    reminder.getDescription(),
                    mainTaskTitle,
                    mainHabit,
                    mainCardStudy
            );
        }
    }

    // Manager for week responsibilities
    private class WeekManager {
        public void setUpWeek(WeekSetup weekSetup) {
            weekResponsibilities = new ArrayList<>(weekSetup.toResponsibilitiesList());
        }
    }

    public void handleSetUpWeek(List<String> stringProperties) {
        if (stringProperties == null || stringProperties.size() < 11) {
            throw new IllegalArgumentException("Insufficient properties to set up a week.");
        }

        // Construct smaller objects from the input list
        Objective objective = new Objective(
                stringProperties.get(1), // objectiveTitle
                stringProperties.get(2)  // objectiveDescription
        );

        Material material = new Material(
                stringProperties.get(3), // materialTopic
                stringProperties.get(4)  // materialFormat
        );

        Reminder reminder = new Reminder(
                stringProperties.get(6), // reminderTitle
                stringProperties.get(7)  // reminderDescription
        );

        // Create the WeekSetup object using the new constructor
        WeekSetup weekSetup = new WeekSetup(
                stringProperties.get(0), // planName
                objective,
                material,
                stringProperties.get(5), // goal
                reminder,
                stringProperties.get(8), // mainTaskTitle
                stringProperties.get(9), // mainHabit
                stringProperties.get(10) // mainCardStudy
        );

        // Delegate to the weekManager
        weekManager.setUpWeek(weekSetup);
    }


    public void addRegistry(Registry registry) {
        registryList.add(registry);
    }

    public void removeRegistry(Registry registry) {
        registryList.remove(registry);
    }

    public List<Registry> getRegistryList() {
        return registryList;
    }

    public List<String> searchInRegistries(String text) {
        List<String> response = new ArrayList<>();
        for (Registry registry : registryList) {
            String mix = (registry.getName() != null ? registry.getName() : "");
            if (mix.toLowerCase().contains(text.toLowerCase())) {
                response.add(registry.getName());
            }
        }
        return response;
    }
}
