package com.traino.app.admin;

import com.traino.app.*;
import com.traino.app.interfaces.SporterProvider;

import java.util.ArrayList;
import java.util.List;

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

    public Workout createSuggestion(Goal goal) {

        Workout suggestion = new Workout(0, null, null, null); //this workout is used to temporarily store the exercises and doesn't have activity, day or status information

        List<Exercise> allGoalExercises = provider.getAllExercises(goal);

        List<Workout> allGoalWorkouts = provider.getAllWorkouts(goal);

        List<Exercise> allWorkoutExercises = new ArrayList<>();

        for(Workout workout : allGoalWorkouts){
            allWorkoutExercises.addAll(provider.getAllExercises(workout));
        }

        for(Exercise exercise : allGoalExercises) {
            int sets = exercise.getSets();
            int reps = exercise.getReps();
            //System.out.println(exercise.toString());
            //calculate how many exercises relate to this goal
            for(Exercise exercise1 : allWorkoutExercises){
                if(exercise.getSymbol().equals(exercise1.getSymbol())){
                    //same symbol
                    if(reps == exercise1.getReps()){
                        sets -= exercise1.getSets(); //calculate sets
                    }
                    else if(reps > exercise1.getReps() && sets == 0){
                        reps -= exercise1.getReps();
                    }
                }
            }

            if(reps > 0 || sets > 0){
                Exercise suggestedExercise = new Exercise(0, exercise.getName(), reps, sets, exercise.getSymbol());
                suggestion.addExercise(suggestedExercise);
            }
        }

        return suggestion;
    }
}
