package com.traino.app.interfaces;

import com.traino.app.Exercise;

import java.util.List;

public interface Actionable {
    void setAllExercises(List<Exercise> exerciseList);
    List<Exercise> getAllExercises();
    void addExercise(Exercise exercise);
    void removeExercise(Exercise exercise);
}
