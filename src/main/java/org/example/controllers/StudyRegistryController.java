package org.example.controllers;

import org.example.studymaterial.AudioReference;
import org.example.studymaterial.Reference;
import org.example.studymaterial.TextReference;
import org.example.studymaterial.VideoReference;
import org.example.studyregistry.*;
import org.example.studyregistry.StudyPlan.StepDetails;
import org.example.studyregistry.StudyPlan.*;
import java.time.LocalDateTime;
import java.util.*;

import static org.example.controllers.MainController.getInput;
import static org.example.controllers.MainController.validateInput;

public class StudyRegistryController {
    StudyTaskManager studyTaskManager = StudyTaskManager.getStudyTaskManager();
    StudyMaterial studyMaterial = StudyMaterial.getStudyMaterial();
    private Map<String, Runnable> actions = new HashMap<>();

    public StudyRegistryController() {
        assignActions();
    }

    void assignActions(){
        actions.put("1", this::handleAddStudyTask);
        actions.put("2", this::handleAddStudyGoal);
        actions.put("3", this::handleAddStudyMaterial);
        actions.put("4", this::handleAddStudyObjective);
        actions.put("5", this::handleAddStudyPlan);
        actions.put("6", this::handleSetUpWeek);
        actions.put("7", this::handleGetWeekResponsibilities);
    }

    private void handleMethodHeader(String header){
        System.out.println("~~~~" + header + "~~~~\n");
    }

    private Task getStudyTaskInfo(){
        System.out.println("Type the following info: title, description, author \n");
        String title = getInput();
        String description = getInput();
        String author = getInput();
        return new Task(title, description, author, LocalDateTime.now());
    }

    private void handleAddStudyTask(){
        Task task = getStudyTaskInfo();
        studyTaskManager.addRegistry(task);
    }

    private void handleSetObjective(StudyObjective objective) {
        handleMethodHeader("(Study Objective Edit)");
        StudyObjective.RegistryDetails registryDetails = getRegistryDetailsFromInput();
        StudyObjective.TextualInfo textualInfo = getTextualInfoFromInput();
        StudyObjective.TimeDetails timeDetails = getTimeDetailsFromInput();
        objective.handleSetObjective(registryDetails, textualInfo, timeDetails);
    }

    private StudyObjective.TextualInfo getTextualInfoFromInput() {
        System.out.println("Type the following info for Textual Info: title, description, topic, objectiveInOneLine, objectiveFullDescription, motivation\n");
        String title = getInput();
        String description = getInput();
        String topic = getInput();
        String objectiveInOneLine = getInput();
        String objectiveFullDescription = getInput();
        String motivation = getInput();
        return new StudyObjective.TextualInfo(title, description, topic, objectiveInOneLine, objectiveFullDescription, motivation);
    }
    private StudyObjective.TimeDetails getTimeDetailsFromInput() {
        System.out.println("Type the following info for Time Details: practicedDays, day, month, year, duration\n");
        int practicedDays = Integer.parseInt(getInput());
        int day = Integer.parseInt(getInput());
        int month = Integer.parseInt(getInput());
        int year = Integer.parseInt(getInput());
        double duration = Double.parseDouble(getInput());
        return new StudyObjective.TimeDetails(practicedDays, day, month, year, duration);
    }
    private StudyObjective.RegistryDetails getRegistryDetailsFromInput() {
        System.out.println("Type the following info for Registry Details: id, name, priority, isActive\n");
        int id = Integer.parseInt(getInput());
        String name = getInput();
        int priority = Integer.parseInt(getInput());
        boolean isActive = Boolean.parseBoolean(getInput());
        return new StudyObjective.RegistryDetails(id, name, priority, isActive);
    }

    private StudyObjective getStudyObjectiveInfo(){
        handleMethodHeader("(Study Objective Creation)");
        System.out.println("Type the following info: title, description \n");
        String title = getInput();
        String description = getInput();
        StudyObjective studyObjective = new StudyObjective(title, description);
        handleSetObjective(studyObjective);
        studyTaskManager.addRegistry(studyObjective);
        return studyObjective;
    }

    private StudyPlan getStudyPlanInfo(){
        handleMethodHeader("(Study Plan Creation)");
        System.out.println("Type the following info: name \n");
        String name = getInput();
        StudyObjective studyObjective = getStudyObjectiveInfo();
        StudyPlan plan = new StudyPlan(name, studyObjective,  new ArrayList<>());
        studyTaskManager.addRegistry(plan);
        return plan;
    }

    private void handleSetSteps(StudyPlan studyPlan) {
        handleMethodHeader("(Study Plan Edit)");
        displayInstructions();

        // Collect all inputs using a helper method
        List<String> inputs = collectInputs();

        // Create StepDetails object
        StepDetails details = createStepDetails(inputs);

        // Call assignSteps with the new StepDetails object
        studyPlan.assignSteps(details);
    }

    // Extracted method for creating StepDetails
    private StepDetails createStepDetails(List<String> inputs) {
        Integer numberOfSteps = getNumberOfSteps(inputs);
        boolean isImportant = getIsImportant(inputs);
        LocalDateTime createdAT = getCreatedAt();
        long daysFromNow = getDaysFromNow(inputs);
        LocalDateTime endDate = calculateEndDate(createdAT, daysFromNow);

        // Create StepInfo, GoalInfo, and TimeInfo instances
        StepInfo stepInfo = createStepInfo(inputs);
        GoalInfo goalInfo = createGoalInfo(inputs);
        TimeInfo timeInfo = createTimeInfo(createdAT, endDate);

        return new StepDetails(stepInfo, goalInfo, numberOfSteps, isImportant, timeInfo);
    }

// Extracted methods for parsing inputs and calculating values

    private Integer getNumberOfSteps(List<String> inputs) {
        return Integer.parseInt(inputs.get(8));
    }

    private boolean getIsImportant(List<String> inputs) {
        return Boolean.parseBoolean(inputs.get(9));
    }

    private LocalDateTime getCreatedAt() {
        return LocalDateTime.now();
    }

    private long getDaysFromNow(List<String> inputs) {
        return Long.parseLong(inputs.get(10));
    }

    private LocalDateTime calculateEndDate(LocalDateTime createdAT, long daysFromNow) {
        return createdAT.plusDays(daysFromNow);
    }

    // Move the instruction display logic to a separate method
    private void displayInstructions() {
        System.out.println("Type the following info: String firstStep, String resetStudyMechanism, String consistentStep, " +
                "String seasonalSteps, String basicSteps, String mainObjectiveTitle, String mainGoalTitle, String mainMaterialTopic, " +
                "String mainTask, @NotNull Integer numberOfSteps, boolean isImportant. " +
                "The Date to start is today, the date to end is x days from now, type the quantity of days\n");
    }

    // Move method to collect all inputs into a helper method
    private List<String> collectInputs() {
        List<String> inputNames = getInputNames();
        List<String> inputs = new ArrayList<>();
        for (String inputName : inputNames) {
            inputs.add(collectInput(inputName));
        }
        return inputs;
    }

    private List<String> getInputNames() {
        return Arrays.asList(
                "firstStep",
                "resetStudyMechanism",
                "consistentStep",
                "seasonalSteps",
                "basicSteps",
                "mainObjectiveTitle",
                "mainGoalTitle",
                "mainMaterialTopic",
                "mainTask",
                "numberOfSteps",
                "isImportant",
                "daysFromNow"
        );
    }


    // Move logic of collecting input into a single method to reduce code duplication
    private String collectInput(String fieldName) {
        System.out.println("Please enter " + fieldName + ": ");
        return getInput();
    }

    // Move the StepInfo object creation logic to a separate method
    private StepInfo createStepInfo(List<String> inputs) {
        return new StepInfo(inputs.get(0), inputs.get(1), inputs.get(2), inputs.get(3), inputs.get(4));
    }

    // Move the GoalInfo object creation logic to a separate method
    private GoalInfo createGoalInfo(List<String> inputs) {
        return new GoalInfo(inputs.get(5), inputs.get(6), inputs.get(7), inputs.get(8));
    }

    // Move the TimeInfo object creation logic to a separate method
    private TimeInfo createTimeInfo(LocalDateTime createdAT, LocalDateTime endDate) {
        return new TimeInfo(createdAT, endDate);
    }

    private StudyGoal getStudyGoalInfo(){
        handleMethodHeader("(Study Goal Creation)");
        System.out.println("Type the following info: name \n");
        String name = getInput();
        StudyPlan studyPlan = getStudyPlanInfo();
        handleSetSteps(studyPlan);
        StudyObjective studyObjective = studyPlan.getObjective();
        return new StudyGoal(name, studyObjective, studyPlan);
    }

    private void handleAddStudyGoal(){
        StudyGoal goal = getStudyGoalInfo();
        studyTaskManager.addRegistry(goal);
    }

    private void editAudio(AudioReference audioReference){
        handleMethodHeader("(Audio Edit)");
        System.out.println("Type the following info:  AudioReference. AudioQuality audioQuality, boolean isDownloadable, " +
                "String title, String description, String link, String accessRights, String license, String language, int rating, " +
                "int viewCount, int shareCount \n");
        AudioReference.AudioQuality quality =AudioReference.audioQualityAdapter(getInput());
        audioReference.editAudio(quality, Boolean.parseBoolean(getInput()), getInput(), getInput(), getInput(), getInput(),
                getInput(), getInput(), Integer.parseInt(getInput()), Integer.parseInt(getInput()), Integer.parseInt(getInput()));
    }

    // Extracted method for printing instructions
    private void printAudioInstructions() {
        System.out.println("Type the following info: AudioReference. AudioQuality audioQuality, boolean isDownloadable, " +
                "String title, String description, String link, String accessRights, String license, String language, int rating, " +
                "int viewCount, int shareCount \n");
    }


    // Extracted method to group metrics-related inputs
    private AudioMetrics getAudioMetrics() {
        int rating = Integer.parseInt(getInput());
        int viewCount = Integer.parseInt(getInput());
        int shareCount = Integer.parseInt(getInput());

        return new AudioMetrics(rating, viewCount, shareCount);
    }

    // AudioMetrics class to encapsulate related fields
    private static class AudioMetrics {
        private int rating;
        private int viewCount;
        private int shareCount;

        public AudioMetrics(int rating, int viewCount, int shareCount) {
            this.rating = rating;
            this.viewCount = viewCount;
            this.shareCount = shareCount;
        }

        public int getRating() {
            return rating;
        }

        public int getViewCount() {
            return viewCount;
        }

        public int getShareCount() {
            return shareCount;
        }
    }

// Extracted methods for collecting individual inputs
    private String getTitle() {
        return getInput();
    }

    private String getDescription() {
        return getInput();
    }

    private String getLink() {
        return getInput();
    }

    private String getAccessRights() {
        return getInput();
    }

    private String getLicense() {
        return getInput();
    }

    private String getLanguage() {
        return getInput();
    }

    private boolean getIsDownloadable() {
        return Boolean.parseBoolean(getInput());
    }

    private int getRating() {
        return Integer.parseInt(getInput());
    }

    private int getViewCount() {
        return Integer.parseInt(getInput());
    }

    private int getShareCount() {
        return Integer.parseInt(getInput());
    }

    private AudioReference addAudioReference(){
        handleMethodHeader("(Audio Reference Creation)");
        System.out.println("Type the following info: Audio Quality ( LOW | MEDIUM | HIGH | VERY_HIGH) \n");
        AudioReference audioReference = new AudioReference(AudioReference.audioQualityAdapter(getInput()));
        editAudio(audioReference);
        return audioReference;
    }

    private VideoReference addVideoReference(){
        handleMethodHeader("(Video Reference Creation)");
        System.out.println("Type the following info: boolean isAvailable, String title, " +
                "String description, String resolution, String frameRate, String videoFormat, String accessRights \n");
        return new VideoReference(Boolean.parseBoolean(getInput()), getInput(), getInput(), getInput(), getInput(),
                getInput(), getInput());
    }

    private TextReference addTextReference(){
        handleMethodHeader("(Text Reference Creation)");
        System.out.println("Type the following info:  String title, String language, int wordCount, String format, String accessRights \n");
        return new TextReference(getInput(), getInput(), Integer.parseInt(getInput()), getInput(),
                getInput());
    }

    private Reference addStudyMaterial(){
        handleMethodHeader("(Study Material Creation)");
        System.out.println("Type the following info: ( AUDIO | VIDEO | TEXT ) \n");
        String type = getInput();
        return switch (type.toLowerCase()) {
            case "audio" -> addAudioReference();
            case "video" -> addVideoReference();
            case "text" -> addTextReference();
            default -> null;
        };
    }

    private void handleAddStudyMaterial(){
        Reference reference = addStudyMaterial();
        if(reference != null){
            studyMaterial.addReference(reference);
        }
    }

    private void handleAddStudyObjective(){
        getStudyObjectiveInfo();
    }

    private void handleAddStudyPlan(){
        getStudyPlanInfo();
        System.out.println("Study Plan Added");
    }

    private void getWeekInfo() {
        printInstructions();

        List<String> inputs = collectInputs();

        // Call the handleSetUpWeek method with the collected inputs
        studyTaskManager.handleSetUpWeek(inputs);
    }

    private void printInstructions() {
        System.out.println("(Study Task Manager Week Set Up) Type the following info:");
        List<String> instructions = getInstructionsList();
        instructions.forEach(this::printInstruction);
    }

    // Extracted method to get the list of instructions
    private List<String> getInstructionsList() {
        return Arrays.asList(
                "1. Plan Name",
                "2. Objective Title",
                "3. Objective Description",
                "4. Material Topic",
                "5. Material Format",
                "6. Goal",
                "7. Reminder Title",
                "8. Reminder Description",
                "9. Main Task Title",
                "10. Main Habit",
                "11. Main Card Study"
        );
    }


    // Extracted method to handle the printing of each instruction
    private void printInstruction(String instruction) {
        System.out.println(instruction);
    }


    // Extracted method to collect the inputs
    private List<String> collectInput() {
        List<String> inputs = new ArrayList<>();
        for (int i = 1; i <= 11; i++) {
            System.out.print("Enter input " + i + ": ");
            inputs.add(getInput());
        }
        return inputs;
    }



    private void handleSetUpWeek(){
        getWeekInfo();
    }

    private void handleGetWeekResponsibilities(){
        List<String> responsibilities = studyTaskManager.getWeekResponsibilities();
        System.out.println(String.join(", ", responsibilities));
    }

    public void handleRegistryInput(){
        try{
            while(true){
                controllerOptions();
                String response = validateInput(actions);
                if(response == null) {return;}
                actions.get(response).run();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void controllerOptions(){
        System.out.println("""
                0 - return
                1 - add study task
                2 - add study goal
                3 - add study material (audio, video, text)
                4 - add study objective
                5 - add study plan
                6 - set up week
                7 - get week responsibilities
               """);
    }
}
