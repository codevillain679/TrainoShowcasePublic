package Persistance;

public abstract class ScheduleItem {
    private Weekday weekday;
    private Status status ;

    public ScheduleItem(Weekday day, Status status){
        this.weekday = day;
        this.status = status;
    }

    public void setWeekday(Weekday weekday) {
        this.weekday = weekday;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
