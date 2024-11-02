package com.examples.planit.internals;

import androidx.annotation.NonNull;

public enum RSVPStatus {
    PENDING("Pending"),
    REJECTED("Rejected"),
    CONFIRMED("Confirmed");

    private final String value;

    RSVPStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RSVPStatus fromString(String value) {
        for (RSVPStatus status : RSVPStatus.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown RSVP status: " + value);
    }

    @NonNull
    @Override
    public String toString() {
        return value;
    }
}
