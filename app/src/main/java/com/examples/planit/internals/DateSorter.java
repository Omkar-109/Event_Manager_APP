package com.examples.planit.internals;

import java.util.Comparator;
import java.util.Date;

public class DateSorter implements Comparator<Event> {
    @Override
    public int compare(Event event1, Event event2) {
        Date currentDate = new Date(); // Get the current date and time
        long diff1 = Math.abs(event1.getStartDate().getTime() - currentDate.getTime());
        long diff2 = Math.abs(event2.getStartDate().getTime() - currentDate.getTime());
        return Long.compare(diff1, diff2);
    }
}
