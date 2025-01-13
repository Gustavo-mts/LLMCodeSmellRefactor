package org.example.studyregistry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudyPlan extends Registry{
    private StudyObjective objective;
    private List<String> steps;

    public StudyPlan(String planName, StudyObjective objective, List<StudyMaterial> materials) {
        this.name = planName;
        this.objective = objective;
        this.steps = new ArrayList<>();
    }

    @Override
    public String toString(){
        return "Plan: " + name + ",\nObjective: " + objective.getDescription() + ",\nSteps: " + String.join(", ", steps);
    }

    public List<String> getSteps() {
        return steps;
    }

    public StudyObjective getObjective() {
        return objective;
    }

    public void assignObjective(StudyObjective objective) {
        this.objective = objective;
    }

    public void addSingleStep(String toAdd){
        steps.add(toAdd);
    }

    // Step information class
    public static class StepInfo {
        private String firstStep;
        private String resetStudyMechanism;
        private String consistentStep;
        private String seasonalSteps;
        private String basicSteps;

        // Constructor accepting grouped information
        public StepInfo(String firstStep, String resetStudyMechanism, String consistentStep, String seasonalSteps, String basicSteps) {
            this.firstStep = firstStep;
            this.resetStudyMechanism = resetStudyMechanism;
            this.consistentStep = consistentStep;
            this.seasonalSteps = seasonalSteps;
            this.basicSteps = basicSteps;
        }

        // Getters for all fields
        public String getFirstStep() { return firstStep; }
        public String getResetStudyMechanism() { return resetStudyMechanism; }
        public String getConsistentStep() { return consistentStep; }
        public String getSeasonalSteps() { return seasonalSteps; }
        public String getBasicSteps() { return basicSteps; }

        // Extracted method to format all steps as a readable string
        public String formatSteps() {
            return String.format("First Step: %s, Reset Mechanism: %s, Consistent Step: %s, Seasonal Steps: %s, Basic Steps: %s",
                    firstStep, resetStudyMechanism, consistentStep, seasonalSteps, basicSteps);
        }

        // Extracted method to check if all fields are filled (validation)
        public boolean isComplete() {
            return firstStep != null && !firstStep.isEmpty() &&
                    resetStudyMechanism != null && !resetStudyMechanism.isEmpty() &&
                    consistentStep != null && !consistentStep.isEmpty() &&
                    seasonalSteps != null && !seasonalSteps.isEmpty() &&
                    basicSteps != null && !basicSteps.isEmpty();
        }

        // Extracted method to generate a concise description of the steps
        public String getConciseDescription() {
            return String.format("Step 1: %s, Step 2: %s", firstStep, consistentStep);
        }
    }


    // Goal information class
    public static class GoalInfo {
        private String mainObjectiveTitle;
        private String mainGoalTitle;
        private String mainMaterialTopic;
        private String mainTask;

        // Constructor accepting grouped information
        public GoalInfo(String mainObjectiveTitle, String mainGoalTitle, String mainMaterialTopic, String mainTask) {
            this.mainObjectiveTitle = mainObjectiveTitle;
            this.mainGoalTitle = mainGoalTitle;
            this.mainMaterialTopic = mainMaterialTopic;
            this.mainTask = mainTask;
        }

        // Getters for all fields
        public String getMainObjectiveTitle() { return mainObjectiveTitle; }
        public String getMainGoalTitle() { return mainGoalTitle; }
        public String getMainMaterialTopic() { return mainMaterialTopic; }
        public String getMainTask() { return mainTask; }

        // Extracted method to format goal summary
        public String formatGoalSummary() {
            return String.format("Objective: %s, Goal: %s, Material Topic: %s, Task: %s",
                    mainObjectiveTitle,
                    mainGoalTitle,
                    mainMaterialTopic,
                    mainTask);
        }

        // Extracted method to check if any field is missing (e.g., to validate if the goal info is complete)
        public boolean isComplete() {
            return mainObjectiveTitle != null && !mainObjectiveTitle.isEmpty() &&
                    mainGoalTitle != null && !mainGoalTitle.isEmpty() &&
                    mainMaterialTopic != null && !mainMaterialTopic.isEmpty() &&
                    mainTask != null && !mainTask.isEmpty();
        }

        // Extracted method to get a concise description of the goal
        public String getConciseDescription() {
            return String.format("Goal: %s - %s", mainGoalTitle, mainObjectiveTitle);
        }
    }


    // Time information class
    public static class TimeInfo {
        private LocalDateTime startDate;
        private LocalDateTime endDate;

        public TimeInfo(LocalDateTime startDate, LocalDateTime endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }

        // Getters and setters
        public LocalDateTime getStartDate() { return startDate; }
        public LocalDateTime getEndDate() { return endDate; }
    }

    public static class StepDetails {
        private StepInfo stepInfo;
        private GoalInfo goalInfo;
        private Integer numberOfSteps;
        private boolean isImportant;
        private TimeInfo timeInfo;

        // Constructor accepting grouped information
        public StepDetails(StepInfo stepInfo, GoalInfo goalInfo, Integer numberOfSteps, boolean isImportant, TimeInfo timeInfo) {
            this.stepInfo = stepInfo;
            this.goalInfo = goalInfo;
            this.numberOfSteps = numberOfSteps;
            this.isImportant = isImportant;
            this.timeInfo = timeInfo;
        }

        // Getters for all fields
        public StepInfo getStepInfo() { return stepInfo; }
        public GoalInfo getGoalInfo() { return goalInfo; }
        public Integer getNumberOfSteps() { return numberOfSteps; }
        public boolean isImportant() { return isImportant; }
        public TimeInfo getTimeInfo() { return timeInfo; }

        // Extract method to format a summary for this step details
        public String formatStepSummary() {
            return String.format("Step: %s, Goal: %s, Time: %s",
                    formatStepInfo(),
                    formatGoalInfo(),
                    formatTimeInfo());
        }

        // Extracted method to format StepInfo
        private String formatStepInfo() {
            return String.format("First Step: %s, Reset Mechanism: %s, Consistent Step: %s, Seasonal Steps: %s, Basic Steps: %s",
                    stepInfo.getFirstStep(),
                    stepInfo.getResetStudyMechanism(),
                    stepInfo.getConsistentStep(),
                    stepInfo.getSeasonalSteps(),
                    stepInfo.getBasicSteps());
        }

        // Extracted method to format GoalInfo
        private String formatGoalInfo() {
            return String.format("Objective Title: %s, Goal Title: %s, Material Topic: %s, Task: %s",
                    goalInfo.getMainObjectiveTitle(),
                    goalInfo.getMainGoalTitle(),
                    goalInfo.getMainMaterialTopic(),
                    goalInfo.getMainTask());
        }

        // Extracted method to format TimeInfo
        private String formatTimeInfo() {
            return String.format("Start: %s, End: %s",
                    timeInfo.getStartDate().toString(),
                    timeInfo.getEndDate().toString());
        }
    }



    // Method to assign steps using the updated StepDetails object
    public void assignSteps(StepDetails stepDetails) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        // Collecting all necessary data from StepDetails object
        this.steps = new ArrayList<>(Arrays.asList(
                stepDetails.getStepInfo().getFirstStep(),
                stepDetails.getStepInfo().getResetStudyMechanism(),
                stepDetails.getStepInfo().getConsistentStep(),
                stepDetails.getStepInfo().getSeasonalSteps(),
                stepDetails.getStepInfo().getBasicSteps(),
                "Number of steps: " + stepDetails.getNumberOfSteps().toString(),
                "Is it important to you? " + stepDetails.isImportant(),
                stepDetails.getTimeInfo().getStartDate().format(formatter),
                stepDetails.getTimeInfo().getEndDate().format(formatter),
                stepDetails.getGoalInfo().getMainObjectiveTitle(),
                stepDetails.getGoalInfo().getMainGoalTitle(),
                stepDetails.getGoalInfo().getMainMaterialTopic(),
                stepDetails.getGoalInfo().getMainTask()
        ));
    }

    // Refactored handleAssignSteps method to match the updated structure
    public void handleAssignSteps(List<String> stringProperties, Integer numberOfSteps, boolean isImportant, LocalDateTime startDate, LocalDateTime endDate) {
        // Step 1: Group the parameters into StepInfo, GoalInfo, and TimeInfo

        // Create StepInfo instance from the list of string properties
        StepInfo stepInfo = new StepInfo(
                stringProperties.get(0),  // firstStep
                stringProperties.get(1),  // resetStudyMechanism
                stringProperties.get(2),  // consistentStep
                stringProperties.get(3),  // seasonalSteps
                stringProperties.get(4)   // basicSteps
        );

        // Create GoalInfo instance from the list of goal-related string properties
        GoalInfo goalInfo = new GoalInfo(
                stringProperties.get(5),  // mainObjectiveTitle
                stringProperties.get(6),  // mainGoalTitle
                stringProperties.get(7),  // mainMaterialTopic
                stringProperties.get(8)   // mainTask
        );

        // Create TimeInfo instance with start and end dates
        TimeInfo timeInfo = new TimeInfo(startDate, endDate);

        // Step 2: Create the StepDetails object using the grouped data
        StepDetails details = new StepDetails(stepInfo, goalInfo, numberOfSteps, isImportant, timeInfo);

        // Step 3: Call assignSteps with the new StepDetails object
        assignSteps(details);
    }
}
