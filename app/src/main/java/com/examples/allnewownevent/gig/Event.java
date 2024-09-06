package com.examples.allnewownevent.gig;

import java.util.Date;
import java.util.UUID;

public class Event extends UniqueIDProvider<Event> {
    private final Budget budget;
    private String name;
    private Date startDate;
    private Date endDate;
    private String location;

    public Event() {
        super();
        this.name = "Unknown";
        this.startDate = new Date();
        this.endDate = new Date();
        this.location = "Unknown";
        this.budget = new Budget(0);
    }

    public Event(String name) {
        super();
        this.name = name;
        this.budget = new Budget(0);
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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
