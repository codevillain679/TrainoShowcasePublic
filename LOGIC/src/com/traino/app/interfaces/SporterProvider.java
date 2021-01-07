package com.traino.app.interfaces;

import com.traino.app.*;

import java.util.List;

public interface SporterProvider {
    public List<Sporter> getAllSporters();
    public void addSporter(Sporter sporter);
    public void updateSporter(Sporter sporter);
    public Sporter login(LoginBean loginBean);
    public void logout();


    public void addGoal(Goal goal);
    public List<Goal> getAllGoals();
    public List<Goal> getAllGoals(Sporter sporter);

    public void addWorkout(Workout workout, Goal goal);
    public List<Workout> getAllWorkouts();
    public List<Workout> getAllWorkouts(Sporter sporter);

    public List<Exercise> getAllExercises(Workout workout);
    public void addGoalExercise(Goal goal, Exercise exercise);
    public void addWorkoutExercise(Workout workout, Exercise exercise);
    public List<Tag> getTags();
    public void addTag(Workout workout, Tag tag);
    public List<Workout> getSuggestions(Goal goal);
    public Sporter getLoggedInSporter();
    public List<Exercise> getAllExercises(Goal goal);

    public List<Schedulable> getAllWorkouts(Goal goal);
}
