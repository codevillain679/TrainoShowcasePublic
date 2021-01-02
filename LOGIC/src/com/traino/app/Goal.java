package com.traino.app;

import com.traino.app.interfaces.Actionable;

import java.util.List;

public class Goal implements Actionable {
    private int id;
    private String title;
    private Tag tag;
    private String description;
    private List<Exercise> allExercises;

    public Goal(int id, String title, String description, Tag goalTag, List<Exercise> exerciseList) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.tag = goalTag;
        this.allExercises = exerciseList;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    @Override
    public List<Exercise> getAllExercises() {
        return allExercises;
    }

    public void setAllExercises(List<Exercise> allExercises) {

        this.allExercises = allExercises;
    }

    @Override
    public void addExercise(Exercise exercise) {
        this.allExercises.add(exercise);
    }

    @Override
    public void removeExercise(Exercise exercise) {

    }
}
