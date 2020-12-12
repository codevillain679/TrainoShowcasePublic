package com.traino.app.admin;

import com.traino.app.Workout;
import com.traino.app.interfaces.Schedulable;
import com.traino.app.interfaces.ScheduleProvider;

import java.util.List;

public class ScheduleAdmin {

    private ScheduleProvider provider;

    public ScheduleAdmin(ScheduleProvider provider){
        this.provider = provider;
    }

    public void addScheduleItem(Schedulable item){
        provider.addSchedulable(item);
        //Schedulable test = new Workout()
    }

    public List<Schedulable> getAllScheduleItems() {
        return provider.getAllSchedulables();
    }
}
