package com.traino.app.interfaces;

import com.traino.app.*;

import java.util.List;

public interface SporterProvider {
    public List<Sporter> getAllSporters();
    public void addSporter(Sporter sporter);
    public Sporter login(LoginBean loginBean);
    public void logout();
    public List<Goal> getAllGoals();
    public void addGoal(Goal goal);
    public List<Exercise> getAllExercises(Workout workout);
    public void addGoalExercise(Goal goal, Exercise exercise);
    public void addWorkoutExercise(Workout workout, Exercise exercise);
    public List<Tag> getTags();
    public void addTag(Workout workout, Tag tag);
    public List<Workout> getSuggestions(Goal goal);
    public List<ScheduleItem> getSchedule(Sporter sporter);
    public void addScheduleItem(ScheduleItem scheduleItem);
    public void deleteScheduleItem(ScheduleItem scheduleItem);
}
