package datastoredemo;

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

        Workout workout = new Workout(0, "Example", Weekday.MONDAY, Status.ACTIVE);
        workout.addExercise(new Exercise(1, "Add comments", 1, 1, "ad"));
        workout.addExercise(new Exercise(2, "Complete To-Do's", 1, 1, "co"));
        workout.addExercise(new Exercise(3, "Push-ups (50x)", 1, 1, "pu"));


        Workout workout2 = new Workout(2, "Example 2", Weekday.MONDAY, Status.ACTIVE);
        workout2.setAllExercises(workout.getAllExercises());

        Workout workout3 = new Workout(3, "Example 2", Weekday.MONDAY, Status.ACTIVE);
        workout3.setAllExercises(workout.getAllExercises());


        schedule.add(workout);
        schedule.add(workout2);
        schedule.add(workout3);
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
