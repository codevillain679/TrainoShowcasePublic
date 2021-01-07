package com.traino.app;

import com.traino.app.interfaces.Schedulable;

import java.util.ArrayList;
import java.util.List;

public class Goal {
    private int id;
    private String title;
    private Tag tag;
    private String description;
    private List<Exercise> allExercises;
    private List<Schedulable> scheduleItems;

    public Goal(int id, String title, String description, Tag goalTag, List<Exercise> exerciseList) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.tag = goalTag;
        this.allExercises = exerciseList;
        this.scheduleItems = new ArrayList<>();
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

    public void addScheduleItem(Schedulable item){
        scheduleItems.add(item);
    }

    public void removeScheduleItem(Schedulable item){
        scheduleItems.remove(item);
    }

    public List<Schedulable> getScheduleItems() {
        return scheduleItems;
    }

    public void setScheduleItems(List<Schedulable> scheduleItems) {
        this.scheduleItems = scheduleItems;
    }
}
