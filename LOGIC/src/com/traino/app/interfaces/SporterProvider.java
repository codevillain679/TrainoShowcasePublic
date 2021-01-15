package com.traino.app.interfaces;

import com.traino.app.*;

import java.util.List;

public interface SporterProvider {
    List<Sporter> getAllSporters();
    void addSporter(Sporter sporter);
    void updateSporter(Sporter sporter);
    Sporter login(LoginBean loginBean);
    void logout();
    void addGoal(Goal goal);
    List<Goal> getAllGoals();
    List<Goal> getAllGoals(Sporter sporter);

    void addWorkout(Workout workout, Goal goal);
    List<Workout> getAllWorkouts();
    //List<Workout> getAllWorkouts(Sporter sporter);

    List<Exercise> getAllExercises(Workout workout);
    void addGoalExercise(Goal goal, Exercise exercise);
    void addWorkoutExercise(Workout workout, Exercise exercise);
    List<Tag> getTags();
    void addTag(Workout workout, Tag tag);
    Sporter getLoggedInSporter();
    List<Exercise> getAllExercises(Goal goal);

    List<Workout> getAllWorkouts(Goal goal);
}
