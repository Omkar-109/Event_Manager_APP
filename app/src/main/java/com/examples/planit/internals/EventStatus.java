package com.examples.planit.internals;

import androidx.annotation.NonNull;

public enum EventStatus {
    UPCOMING("Upcoming"),
    ONGOING("Ongoing"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled");

    private final String status;

    EventStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @NonNull
    public static EventStatus getEventStatusFromString(String statusString) {
        for (EventStatus status : EventStatus.values()) {
            if (status.getStatus().equals(statusString)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + statusString);
    }
}
