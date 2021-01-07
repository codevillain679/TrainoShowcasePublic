package com.traino.datastoredemo;

import com.traino.app.*;
import com.traino.app.interfaces.Schedulable;
import com.traino.app.interfaces.SporterProvider;

import java.util.ArrayList;
import java.util.List;

public class SporterStoreDemo implements SporterProvider {
    private Sporter sporter;
    private List<Sporter> sporters;
    private List<Goal> goals;
    private List<Workout> workouts;

    public SporterStoreDemo(){
        sporter = null;
        sporters = new ArrayList<>();
        goals = new ArrayList<>();
        workouts = new ArrayList<>();

        //example sporters in db
        sporters.add(new Sporter(1, "Niels", "Komodidjojo", "nkorporaal", "traino213", "nk@email.com", "0612341236", false, 70.0, 179.0, 9.0, "O-"));
        sporters.add(new Sporter(4, "Joey", "Scha", "joeys", "test", "js@email.com", "0612341234", false, 80.0, 183.0, 11.0, "B-"));
        sporters.add(new Sporter(6, "Mark", "Baij", "mbj", "test", "mb@email.com", "0612341235", false, 77.0, 181.0,9.0, "O-"));

        //example tag
        Tag tag = new Tag("train");

        //example goals in db
        goals.add(new Goal(0, "Pushups", "Perform record amount of pushups", tag, new ArrayList<>()));
        goals.add(new Goal(1, "Morning routines", "Daily 15min exercises", tag, new ArrayList<>()));
        goals.add(new Goal(2, "Code cleanups", "Make sure the code complies to SOLID principles", tag, new ArrayList<>()));
        goals.add(new Goal(3, "Implementing...", "Classes, interfaces, and finally testing...", tag, new ArrayList<>()));

        //example exercises
        goals.get(2).addExercise(new Exercise(4,"Adding comments!", 1, 1, "ad"));
    }

    @Override
    public List<Sporter> getAllSporters() {
        return sporters;
    }

    @Override
    public void addSporter(Sporter sporter) {
        sporters.add(sporter);
    }

    @Override
    public void updateSporter(Sporter sporter) {

    }

    @Override
    public Sporter login(LoginBean loginBean) {
        if(loginBean.getUsername().equals("nkorporaal") && loginBean.getPassword().equals("traino213")){ //example login
            this.sporter = getAllSporters().get(0);
            this.sporter.setGoals(goals);
        }
        return sporter;
    }

    @Override
    public void logout() {
        this.sporter = null;
    }

    @Override
    public List<Goal> getAllGoals() {
        return goals;
    }

    @Override
    public void addGoal(Goal goal) {
        goals.add(goal);
    }

    @Override
    public void addWorkout(Workout workout, Goal goal) {
        this.workouts.add(workout);
    }

    @Override
    public List<Workout> getAllWorkouts() {
        return workouts;
    }

    @Override
    public List<Workout> getAllWorkouts(Sporter sporter) {
        return null;
    }

    @Override
    public List<Exercise> getAllExercises(Workout workout) {
        return workout.getAllExercises();
    }

    @Override
    public void addGoalExercise(Goal g, Exercise exercise) {
        for(Goal goal : getAllGoals()){
            if(goal.equals(g)){
                goal.addExercise(exercise);
            }
        }
    }

    public void addWorkoutExercise(Workout workout, Exercise exercise){
        workout.addExercise(exercise);
    }

    @Override
    public List<Tag> getTags() {
        return null;
    }

    @Override
    public void addTag(Workout workout, Tag tag) {

    }

    @Override
    public List<Workout> getSuggestions(Goal goal) {

        //returns a workout with three exercises for this goal
        List<Workout> suggestionList = new ArrayList<>();

        Workout suggestion = new Workout(-1, goal.getTitle() + " example workout", Weekday.MONDAY, Status.ACTIVE);

        suggestion.addExercise(new Exercise(0,"push-ups", 50, 4, "pu"));
        suggestion.addExercise(new Exercise(1,"sit-ups", 35, 4, "si"));
        suggestion.addExercise(new Exercise(2,"ankle-taps", 35, 4, "an"));

        suggestionList.add(suggestion);

        //get goal exercises
        List<Exercise> goalExercises = goal.getAllExercises();

        for(Exercise exercise: goalExercises){
            Exercise suggestion_exercise = new Exercise(0, exercise.getName(), exercise.getReps()/2,exercise.getSets()/2,exercise.getSymbol());
            suggestion.addExercise(suggestion_exercise);
        }
        
        return suggestionList;
    }

    @Override
    public Sporter getLoggedInSporter() {
        return sporter;
    }

    @Override
    public List<Goal> getAllGoals(Sporter sporter) {
        return null;
    }

    @Override
    public List<Exercise> getAllExercises(Goal goal) {
        return null;
    }

    @Override
    public List<Schedulable> getAllWorkouts(Goal goal) {
        return null;
    }
}
