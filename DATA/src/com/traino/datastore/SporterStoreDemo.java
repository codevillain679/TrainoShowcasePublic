package com.traino.datastore;

import com.traino.app.*;
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

        sporters.add(new Sporter(1, "John", goals));
        sporters.add(new Sporter(2, "Kees", goals));

        Tag tag = new Tag("train");

        goals.add(new Goal("Pushups", "Perform record amount of pushups", tag, new ArrayList<>()));
        goals.add(new Goal("Morning routines", "Daily 15min exercises", tag, new ArrayList<>()));
        goals.add(new Goal( "Code cleanups", "Make sure the code complies to SOLID principles", tag, new ArrayList<>()));
        goals.add(new Goal("Implementing...", "Classes, interfaces, and finally testing...", tag, new ArrayList<>()));

        goals.get(2).addExercise(new Exercise("Adding comments!", 1, 1, "ad"));
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
    public Sporter login(LoginBean loginBean) {
        if(loginBean.getUsername().equals("nkorporaal") && loginBean.getPassword().equals("traino213")){
            this.sporter = new Sporter(1 ,"Niels", goals);
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
    public List<Exercise> getAllExercises(Workout workout) {
        return workout.getAllExercises();
    }

    @Override
    public void addGoalExercise(Goal goal, Exercise exercise) {
        goal.addExercise(exercise);
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
        List<Workout> suggestionList = new ArrayList<>();

        Workout suggestion = new Workout(goal.getTitle() + " example workout", Weekday.MONDAY, Status.ACTIVE);

        suggestionList.add(suggestion);

        //get goal exercises
        List<Exercise> goalExercises = goal.getAllExercises();

        for(Exercise exercise: goalExercises){
            Exercise suggestion_exercise = new Exercise(exercise.getName(), exercise.getReps()/2,exercise.getSets()/2,exercise.getSymbol());
            suggestion.addExercise(suggestion_exercise);
        }


        // TODO: Think of a way to compare goal exercises to its relevant workout exercise(s)
        return suggestionList;
    }

    @Override
    public List<ScheduleItem> getSchedule(Sporter sporter) {
        return null;
    }

    @Override
    public void addScheduleItem(ScheduleItem scheduleItem) {

    }

    @Override
    public void deleteScheduleItem(ScheduleItem scheduleItem) {

    }
}
