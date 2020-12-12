package com.traino.app.admin;

import com.traino.app.*;
import com.traino.app.interfaces.SporterProvider;

import java.util.List;

public class SporterAdmin {
    private SporterProvider provider;

    public SporterAdmin(SporterProvider provider){

        this.provider = provider;
    }

    public List<Sporter> getAllSporters() {

        return provider.getAllSporters();
    }

    public void addSporter(Sporter sporter){
        provider.addSporter(sporter);
    }

    public List<Goal> getAllGoals() {

        return provider.getAllGoals();
    }

    public void addGoal(Goal goal){
        provider.addGoal(goal);
    }

    public Sporter login(LoginBean loginBean){

        return provider.login(loginBean);
    }

    public void logout(){

        provider.logout();
    }

    public void addWorkout(Goal goal, Workout workout) {

    }

    public List<Workout> getAllWorkouts(Goal goal){
        return null;
    }

    public List<Exercise> getAllExercises(Workout workout) {
        return provider.getAllExercises(workout);
    }

    public void addGoalExercise(Goal goal, Exercise exercise){
        provider.addGoalExercise(goal, exercise);
    }

    public void addWorkoutExercise(Workout workout, Exercise exercise){
        provider.addWorkoutExercise(workout, exercise);
    }

    public List<Workout> getSuggestions(Goal goal){
        return provider.getSuggestions(goal);
    }
}
