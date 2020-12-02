package Persistance;

public class Workout extends ScheduleItem{ // extends SCHEDULE ITEM : holds data related to schedule only, WOKROUT contains practical information
    private int id;
    private String activity;
    private Exercise exercise;
    private Goal goal;

    public Workout(int id, String activity, Exercise exercise) {
        super(Weekday.MONDAY, Status.ACTIVE);
        this.id = id;
        this.activity = activity;
        this.exercise = exercise;
    }

    public int getId() {
        return id;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Exercise getUnit() {
        return exercise;
    }

    public void setUnit(Exercise exercise) {
        this.exercise = exercise;
    }

    @Override
    public void setStatus(Status status) {
        super.setStatus(status);
    }

    @Override
    public void setWeekday(Weekday weekday) {
        super.setWeekday(weekday);
    }

    @Override
    public String toString() {
        return "Workout{" +
                "activity='" + activity + '\'' +
                ", exercise=" + exercise +
                '}';
    }
}
