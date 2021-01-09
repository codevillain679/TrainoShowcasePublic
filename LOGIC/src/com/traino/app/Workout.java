package com.traino.app;

import com.traino.app.interfaces.Schedulable;

import java.util.ArrayList;
import java.util.List;

public class Workout implements Schedulable { // extends Actionable : holds data related to schedule only, WORKOUT contains practical information
    private int id;
    private String activity;
    private Weekday day;
    private Status status;
    private List<Exercise> allExercises;

    public Workout(int id, String activity, Weekday day, Status status) {
        this.id = id;
        this.activity = activity;
        this.day = day;
        this.status = status;
        this.allExercises = new ArrayList<>();
    }

    @Override
    public String getActivity() {
        return this.activity;
    }

    public List<Exercise> getAllExercises() {
        return this.allExercises;
    }

    public void setAllExercises(List<Exercise> allExercises) {
        this.allExercises = allExercises;
    }

    public void removeExercise(Exercise exercise) {
        this.allExercises.remove(exercise);
    }

    public void addExercise(Exercise exercise) {
        this.allExercises.add(exercise);
    }

    public void setDay(Weekday weekday) {
        this.day = weekday;
    }

    @Override
    public Weekday getDay() {
        return day;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getScheduleInfo() {
        String scheduleInfo = activity + " ("+ day.toString() + ")\n";

        if(getAllExercises().size() == 0){
            scheduleInfo += "\t- No Exercises Added!";
        }
        for(Exercise exercise : getAllExercises()){
            scheduleInfo += exercise.toString() + "\n";
        }
        return scheduleInfo;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Name:\t" + activity +
                "\nDay:\t" + day +
                "\nStatus:\t" + status;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
