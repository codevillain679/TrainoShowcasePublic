package com.traino.app.interfaces;

import java.util.List;

public interface ScheduleProvider {
    void addSchedulable(Schedulable item);
    List<Schedulable> getAllSchedulables();
}
