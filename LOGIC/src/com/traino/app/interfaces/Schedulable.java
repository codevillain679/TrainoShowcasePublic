package com.traino.app.interfaces;

import com.traino.app.Status;
import com.traino.app.Weekday;

public interface Schedulable {
    String getActivity();
    void setDay(Weekday weekday);
    Weekday getDay();
    void setStatus(Status status);
    Status getStatus();
    String getScheduleInfo();
}
