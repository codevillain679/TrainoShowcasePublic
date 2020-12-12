package com.traino.app;

import com.traino.app.interfaces.Actionable;
import com.traino.app.interfaces.Schedulable;

import java.util.ArrayList;
import java.util.List;

public class Workout implements Actionable, Schedulable { // extends Actionable : holds data related to schedule only, WOKROUT contains practical information
    private String activity;
    private List<Exercise> allExercises;
    private Weekday day;
    private Status status;

    public Workout(String activity, Weekday day, Status status) {
        this.activity = activity;
        this.day = day;
        this.status = status;
        this.allExercises = new ArrayList<>();
    }

    @Override
    public String getActivity() {

        return this.activity;
    }

    @Override
    public List<Exercise> getAllExercises() {
        return this.allExercises;
    }

    @Override
    public void setAllExercises(List<Exercise> allExercises) {
        this.allExercises = allExercises;
    }

    @Override
    public void removeExercise(Exercise exercise) {
        this.allExercises.remove(exercise);
    }

    @Override
    public void addExercise(Exercise exercise) {
        this.allExercises.add(exercise);
    }

    @Override
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

    @Override
    public String getScheduleInfo() {
        String scheduleInfo = "";
        if(allExercises.size() == 0) return scheduleInfo;
        scheduleInfo = activity + "\n";
        for(Exercise exercise : getAllExercises()){
            scheduleInfo += "\t- " + exercise.getName() + "\n";
        }
        return scheduleInfo;
    }
}
