package com.examples.planit.internals;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Represents an Event with a unique identifier, name, start date, location, and budget.
 * Provides various constructors to initialize event details and methods to manage the event data.
 *
 * @author Ram Amoncar
 * @author Omkar Mhamal
 * @version 1.0
 * @see UniqueIDProvider
 * @see Budget
 */
public class Event extends UniqueIDProvider<Event> {
    private final Budget budget;
    private String name;
    private Date startDate;
    private String location;

    /**
     * Default constructor that initializes an Event with default values.
     */
    public Event() {
        super();
        this.name = "Unknown";
        this.startDate = new Date();
        this.location = "Unknown";
        this.budget = new Budget(0);
    }

    /**
     * Constructs an Event with a specified name.
     *
     * @param name the name of the event
     */
    public Event(String name) {
        super();
        this.name = name;
        this.startDate = new Date();
        this.location = "Unknown";
        this.budget = new Budget(0);
    }

    /**
     * Constructs an Event with a specified name and initial budget.
     *
     * @param name          the name of the event
     * @param initialBudget the initial budget amount
     */
    public Event(String name, double initialBudget) {
        super();
        this.name = name;
        this.startDate = new Date();
        this.location = "Unknown";
        this.budget = new Budget(initialBudget);
    }

    /**
     * Constructs an Event with a specified name, start date, and initial budget.
     *
     * @param name           the name of the event
     * @param startDate      the start date of the event
     * @param initial_budget the initial budget amount
     */
    public Event(String name, Date startDate, Double initial_budget) {
        super();
        this.name = name;
        this.startDate = startDate;
        this.location = "Unknown";
        this.budget = new Budget(initial_budget);
    }

    /**
     * Constructs an Event with a specified name, start date, initial budget and location.
     *
     * @param name           the name of the event
     * @param startDate      the start date of the event
     * @param initial_budget the initial budget amount
     * @param location       the location of the event
     */
    public Event(String name, Date startDate, Double initial_budget, String location) {
        super();
        this.name = name;
        this.startDate = startDate;
        this.location = location;
        this.budget = new Budget(initial_budget);
    }


    /**
     * Constructs an Event with a specified unique identifier, name, start date, location, and budget.
     *
     * @param uid       the unique identifier for the event
     * @param name      the name of the event
     * @param startDate the start date in "dd/MM/yyyy HH:mm" format
     * @param location  the location of the event
     * @param budget    the budget for the event
     */
    public Event(String uid, String name, @NonNull String startDate, String location, Budget budget) {
        super(uid);
        this.name = name;
        String[] dateParts = (startDate.split(" "))[0].split("/");
        int dayOfMonth = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]) - 1;
        int year = Integer.parseInt(dateParts[2]);

        String[] timeParts = (startDate.split(" "))[1].split(":");
        int hourOfDay = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);

        this.startDate = calendar.getTime();
        this.location = location;
        this.budget = budget;
    }

    /**
     * Gets the name of the event.
     *
     * @return the event name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the event.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the start date of the event.
     *
     * @return the start date as a Date object
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the event.
     *
     * @param startDate the start date to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the formatted start date as a string in "dd/MM/yyyy HH:mm" format.
     *
     * @return the formatted start date string
     */
    public String getFormattedStartDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        return dateFormat.format(startDate);
    }

    /**
     * Gets the location of the event.
     *
     * @return the event location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the event.
     *
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the budget associated with the event.
     *
     * @return the event budget
     */
    public Budget getBudget() {
        return budget;
    }
}
