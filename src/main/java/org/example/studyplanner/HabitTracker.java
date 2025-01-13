package org.example.studyplanner;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.format.DateTimeFormatter;

import org.example.studyplanner.HabitTracker.HabitDetails;
import org.example.studyplanner.HabitTracker.TimeDetails;

public class HabitTracker {
    private List<Habit> habits;
    private Map<Integer, List<LocalDateTime>> tracker;
    private Integer nextId;

    private static HabitTracker instance;

    public static HabitTracker getHabitTracker() {
        if (instance == null) {
            instance = new HabitTracker();
        }
        return instance;
    }

    private HabitTracker() {
        this.habits = new ArrayList<>();
        this.tracker = new HashMap<>();
        this.nextId = 1;
    }

    // Buscar um hábito pelo ID
    public Habit getHabitById(Integer id) {
        return this.habits.stream()
                .filter(habit -> Objects.equals(habit.getId(), id))
                .findFirst().orElse(null);
    }

    // Adicionar um hábito com HabitDetails e TimeDetails
    public int addHabit(HabitDetails details, TimeDetails timeDetails, boolean isConcluded) {
        Habit habit = new Habit(
                details.getName(),
                details.getMotivation(),
                timeDetails.getDailyDedicationTime(),
                this.nextId,
                timeDetails.getStartDateTime(),
                isConcluded
        );
        this.habits.add(habit);
        tracker.put(nextId, new ArrayList<>());
        return nextId++;
    }


    // Adicionar hábito com valores padrão para tempo
    public int addHabit(String name, String motivation) {
        LocalDateTime now = LocalDateTime.now();
        HabitDetails details = new HabitDetails(name, motivation);
        TimeDetails timeDetails = new TimeDetails(
                0, 0,
                now.getYear(), now.getMonthValue(), now.getDayOfMonth(),
                now.getHour(), now.getMinute(), now.getSecond()
        );
        return addHabit(details, timeDetails, false);
    }

    public String habitDateViewAll() {
        StringBuilder response = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        for (Habit habit : habits) {
            response.append("[Habit: ").append(habit.getName()).append(", Records: ");
            List<LocalDateTime> records = tracker.getOrDefault(habit.getId(), Collections.emptyList());

            for (LocalDateTime record : records) {
                response.append(record.format(formatter)).append(", ");
            }

            response.append("]\n");
        }

        return response.toString();
    }


    // Adapter para adicionar hábitos usando listas de propriedades
    public int handleAddHabitAdapter(List<String> stringProperties, List<Integer> intProperties, boolean isConcluded) {
        HabitDetails details = new HabitDetails(
                stringProperties.get(0), // Nome
                stringProperties.get(1)  // Motivação
        );

        TimeDetails timeDetails = new TimeDetails(
                intProperties.get(0), // Minutos
                intProperties.get(1), // Horas
                intProperties.get(2), // Ano
                intProperties.get(3), // Mês
                intProperties.get(4), // Dia
                intProperties.get(5), // Hora
                intProperties.get(6), // Minuto
                intProperties.get(7)  // Segundo
        );

        return addHabit(details, timeDetails, isConcluded);
    }

    public void addHabitRecord(Integer id) {
        tracker.getOrDefault(id, new ArrayList<>()).add(LocalDateTime.now());
    }

    public void toggleConcludeHabit(Integer id) {
        Habit habit = getHabitById(id);
        if (habit != null) {
            habit.setIsConcluded(!habit.getIsConcluded());
        }
    }

    public void removeHabit(Integer id) {
        this.habits.removeIf(habit -> habit.getId().equals(id));
        this.tracker.remove(id);
    }

    public List<LocalDateTime> getHabitRecords(Integer id) {
        return this.tracker.getOrDefault(id, Collections.emptyList());
    }

    public List<String> searchInHabits(String search) {
        List<String> results = new ArrayList<>();
        for (Habit habit : this.habits) {
            if (habit.getName().toLowerCase().contains(search.toLowerCase()) ||
                    habit.getMotivation().toLowerCase().contains(search.toLowerCase())) {
                results.add(habit.toString());
            }
        }
        return results;
    }

    // Classe para encapsular os detalhes do hábito
    public static class HabitDetails {
        private final String name;
        private final String motivation;

        public HabitDetails(String name, String motivation) {
            this.name = name;
            this.motivation = motivation;
        }

        public String getName() {
            return name;
        }

        public String getMotivation() {
            return motivation;
        }
    }

    // Classe para encapsular detalhes de tempo
    public static class TimeDetails {
        private final int dailyMinutesDedication;
        private final int dailyHoursDedication;
        private final int year;
        private final int month;
        private final int day;
        private final int hour;
        private final int minute;
        private final int seconds;

        public TimeDetails(int dailyMinutesDedication, int dailyHoursDedication, int year, int month, int day,
                           int hour, int minute, int seconds) {
            this.dailyMinutesDedication = dailyMinutesDedication;
            this.dailyHoursDedication = dailyHoursDedication;
            this.year = year;
            this.month = month;
            this.day = day;
            this.hour = hour;
            this.minute = minute;
            this.seconds = seconds;
        }

        public LocalTime getDailyDedicationTime() {
            return LocalTime.of(dailyHoursDedication, dailyMinutesDedication);
        }

        public LocalDateTime getStartDateTime() {
            return LocalDateTime.of(year, month, day, hour, minute, seconds);
        }
    }
}