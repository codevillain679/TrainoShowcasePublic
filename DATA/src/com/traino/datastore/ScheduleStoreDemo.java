package com.traino.datastore;

import com.traino.app.Exercise;
import com.traino.app.Status;
import com.traino.app.Weekday;
import com.traino.app.Workout;
import com.traino.app.interfaces.Schedulable;
import com.traino.app.interfaces.ScheduleProvider;

import java.util.ArrayList;
import java.util.List;

public class ScheduleStoreDemo implements ScheduleProvider {
    private List<Schedulable> schedule;

    public ScheduleStoreDemo(){

        this.schedule = new ArrayList<>();

        Workout workout = new Workout("Example", Weekday.MONDAY, Status.ACTIVE);
        workout.addExercise(new Exercise("Add comments", 1, 1, "ad"));
        workout.addExercise(new Exercise("Complete To-Do's", 1, 1, "co"));
        workout.addExercise(new Exercise("Push-ups (50x)", 1, 1, "pu"));

        schedule.add(workout);

    }

    @Override
    public void addSchedulable(Schedulable item) {
        schedule.add(item);
    }

    @Override
    public List<Schedulable> getAllSchedulables() {
        return schedule;
    }
}
