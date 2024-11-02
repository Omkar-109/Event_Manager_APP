package com.examples.planit.internals;

import androidx.annotation.NonNull;

/**
 * @author Ram Amoncar
 * @author Omkar Mhamal
 * @version 1.0
 * Enum representing the possible payment statuses.
 */
public enum PaymentStatus {
    /**
     * Indicates that the payment is pending.
     */
    PENDING("Pending"),

    /**
     * Indicates that the payment has been completed.
     */
    PAID("Paid"),

    /**
     * Indicates that the payment has been cancelled.
     */
    CANCELLED("Cancelled");

    private final String value;

    PaymentStatus(String value) {
        this.value = value;
    }

    /**
     * Returns the PaymentStatus corresponding to the provided string value.
     *
     * @param value the string value of the status
     * @return the corresponding PaymentStatus
     * @throws IllegalArgumentException if the value does not match any status
     */
    public static PaymentStatus fromString(String value) {
        for (PaymentStatus status : PaymentStatus.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown payment status: " + value);
    }

    /**
     * Returns the string representation of the payment status.
     *
     * @return the status value
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
