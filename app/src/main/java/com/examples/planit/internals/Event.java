package com.examples.planit.internals;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Event extends UniqueIDProvider<Event> {
    private final Budget budget;
    private String name;
    private EventStatus eventStatus;
    private Date startDate;
    private String location;

    public Event() {
        super();
        this.name = "Unknown";
        this.startDate = new Date();
        this.location = "Unknown";
        this.budget = new Budget(0);
        this.eventStatus = EventStatus.UPCOMING;
    }

    public Event(String name) {
        super();
        this.name = name;
        this.startDate = new Date();
        this.location = "Unknown";
        this.budget = new Budget(0);
        this.eventStatus = EventStatus.UPCOMING;
    }

    public Event(String name, double initialBudget) {
        super();
        this.name = name;
        this.startDate = new Date();
        this.location = "Unknown";
        this.budget = new Budget(initialBudget);
        this.eventStatus = EventStatus.UPCOMING;
    }

    public Event(String name, Date startDate, Double initial_budget) {
        super();
        this.name = name;
        this.startDate = startDate;
        this.location = "Unknown";
        this.budget = new Budget(initial_budget);
        this.eventStatus = EventStatus.UPCOMING;
    }

    public Event(String uid, String name, @NonNull String startDate, String location, Budget budget, String eventStatus) {
        super(uid);
        this.name = name;
        String[] dateParts = (startDate.split(" "))[0].split("/");
        int dayOfMonth = Integer.parseInt(dateParts[0]);   // Day of month
        int month = Integer.parseInt(dateParts[1]) - 1;    // Month (subtract 1 because Calendar.MONTH is zero-based)
        int year = Integer.parseInt(dateParts[2]);         // Year

        // Extracting time components
        String[] timeParts = (startDate.split(" "))[1].split(":");
        int hourOfDay = Integer.parseInt(timeParts[0]);     // Hour of day (24-hour format)
        int minute = Integer.parseInt(timeParts[1]);        // Minute

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);

        this.startDate = calendar.getTime();
        this.location = location;
        this.budget = budget;
        this.eventStatus = EventStatus.getEventStatusFromString(eventStatus);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getFormattedStartDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        return dateFormat.format(startDate);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Budget getBudget() {
        return budget;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }
}

