package com.examples.planit.internals;

import androidx.annotation.NonNull;

/**
 * Represents the RSVP status for guests attending an event.
 * Possible statuses include PENDING, REJECTED, and CONFIRMED.
 *
 * @author Ram Amoncar
 * @author Omkar Mhamal
 * @version 1.0
 * @see Guest
 * @see Event
 */
public enum RSVPStatus {
    PENDING("Pending"),
    REJECTED("Rejected"),
    CONFIRMED("Confirmed");

    private final String value;

    /**
     * Constructs an RSVPStatus with the specified string value.
     *
     * @param value the string representation of the RSVP status
     */
    RSVPStatus(String value) {
        this.value = value;
    }

    /**
     * Converts a string value to an RSVPStatus enum.
     *
     * @param value the string representation of the RSVP status
     * @return the corresponding RSVPStatus enum
     * @throws IllegalArgumentException if the value does not match any RSVP status
     */
    public static RSVPStatus fromString(String value) {
        for (RSVPStatus status : RSVPStatus.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown RSVP status: " + value);
    }

    /**
     * Gets the string value of the RSVP status.
     *
     * @return the string representation of the RSVP status
     */
    public String getValue() {
        return value;
    }

    @NonNull
    @Override
    public String toString() {
        return value;
    }
}
