package com.traino.app.admin;

import com.traino.app.*;
import com.traino.app.interfaces.Schedulable;
import com.traino.app.interfaces.SporterProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SporterAdmin {
    private SporterProvider provider;

    public SporterAdmin(SporterProvider provider) {

        this.provider = provider;
    }

    public List<Sporter> getAllSporters() {

        return provider.getAllSporters();
    }

    public void updateSporter(Sporter sporter) {
        provider.updateSporter(sporter);
    }

    public void addSporter(Sporter sporter) {
        provider.addSporter(sporter);
    }

    public List<Goal> getAllGoals() {

        return provider.getAllGoals();
    }

    public void addGoal(Goal goal) {
        provider.addGoal(goal);
    }

    public Sporter login(LoginBean loginBean) {

        return provider.login(loginBean);
    }

    public void logout() {

        provider.logout();
    }

    public List<Exercise> getAllExercises(Workout workout) {
        return provider.getAllExercises(workout);
    }

    public void addGoalExercise(Goal goal, Exercise exercise) {
        provider.addGoalExercise(goal, exercise);
    }

    public void addWorkoutExercise(Workout workout, Exercise exercise) {
        provider.addWorkoutExercise(workout, exercise);
    }

    public List<Workout> getSuggestions(Goal goal) {
        return provider.getSuggestions(goal);
    }

    public Sporter getLoggedInSporter() {
        return provider.getLoggedInSporter();
    }

    public List<Workout> getAllWorkouts() {
        return provider.getAllWorkouts();
    }

    public void addWorkout(Workout workout, Goal goal) {
        provider.addWorkout(workout, goal);
    }

    public List<Goal> getAllGoals(Sporter sporter) {
        return provider.getAllGoals(sporter);
    }

    public Schedulable createSuggestion(Goal goal) {
        Workout suggestion = new Workout(0, null, null, null);
        List<Exercise> allSuggestionExercises = new ArrayList<>();

        List<Exercise> allGoalExercises = provider.getAllExercises(goal);

        List<Workout> allGoalWorkouts = provider.getAllWorkouts(goal);

        List<Exercise> scheduledExercises = new ArrayList<>();

        for (Exercise exercise : allGoalExercises) {
            System.out.println(exercise.toString());
        }

        for (Workout workout : allGoalWorkouts) {
            suggestion.setActivity(workout.getActivity());
            suggestion.setDay(workout.getDay());
            suggestion.setStatus(workout.getStatus());
            List<Exercise> allWorkoutExercises = workout.getAllExercises();
            scheduledExercises.addAll(allWorkoutExercises);

            for (Exercise exercise : allGoalExercises) {
                Exercise suggestion_exercise = new Exercise(0, exercise.getName(), exercise.getReps(), exercise.getSets(), exercise.getSymbol());

                int diffReps = exercise.getReps();
                int diffSets = exercise.getReps();
                int diffTotal = exercise.getReps() * exercise.getSets();

                for (Exercise scheduled : scheduledExercises) {
                    if (scheduled.getSymbol().equals(suggestion_exercise.getSymbol())) {
                        //calculate difference between goal exercise and scheduled
                        diffReps -= scheduled.getReps();
                        diffSets -= scheduled.getSets();
                        diffTotal -= (scheduled.getReps() * scheduled.getSets());
                    }
                }

                if (diffTotal > 0) {
                    suggestion_exercise.setReps(diffReps);
                    suggestion_exercise.setSets(diffSets);
                    allSuggestionExercises.add(suggestion_exercise);
                }
            }
        }

        if(allSuggestionExercises.size() > 0){
            for(Exercise exercise : allSuggestionExercises){
                suggestion.addExercise(exercise);
            }
        }

        return suggestion;
    }
}
