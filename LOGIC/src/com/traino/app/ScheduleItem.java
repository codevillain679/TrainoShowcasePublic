package com.traino.app;

import com.traino.app.interfaces.Schedulable;

public abstract class ScheduleItem {
    protected abstract Schedulable createSuggestion(Goal g);
}
