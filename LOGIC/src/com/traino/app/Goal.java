package com.traino.app;

import java.util.List;

public class Goal {
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

    public List<Exercise> getAllExercises() {
        return allExercises;
    }

    public void setAllExercises(List<Exercise> allExercises) {

        this.allExercises = allExercises;
    }

    public void addExercise(Exercise exercise) {
        this.allExercises.add(exercise);
    }

    public void removeExercise(Exercise exercise) {

    }

    public Tag getTag() {
        return tag;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Title:\t" + title +
                "\nTag:\t#" + tag.getLink() +
                "\nDescription:\t" + description;
    }
}
