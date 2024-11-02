package com.examples.planit.internals;

import java.util.Date;

/**
 * Represents a Payment with a unique identifier, amount, date, payee ID, and payment status.
 * Provides methods to manage and update payment details.
 *
 * @author Ram Amoncar
 * @author Omkar Mhamal
 * @version 1.0
 * @see UniqueIDProvider
 * @see PaymentStatus
 * @see Guest
 */
public class Payment extends UniqueIDProvider<Payment> {
    private double amount;
    private Date date;
    private long payTo;
    private PaymentStatus status;

    /**
     * Constructs a Payment with the specified amount, date, payee ID, and status.
     *
     * @param amount the payment amount
     * @param date   the date of the payment
     * @param payTo  the ID of the payee
     * @param status the current status of the payment
     */
    public Payment(double amount, Date date, long payTo, PaymentStatus status) {
        super();
        this.amount = amount;
        this.date = date;
        this.payTo = payTo;
        this.status = status;
    }

    /**
     * Gets the payment amount.
     *
     * @return the payment amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the payment amount.
     *
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Gets the date of the payment.
     *
     * @return the payment date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date of the payment.
     *
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the ID of the payee.
     *
     * @return the payee ID
     */
    public long getPayTo() {
        return payTo;
    }

    /**
     * Sets the ID of the payee.
     *
     * @param payTo the payee ID to set
     */
    public void setPayTo(long payTo) {
        this.payTo = payTo;
    }

    /**
     * Gets the status of the payment.
     *
     * @return the payment status
     */
    public PaymentStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the payment.
     *
     * @param status the status to set
     */
    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    /**
     * Confirms the payment by setting its status to PAID if it is currently PENDING.
     */
    public void confirmPayment() {
        if (this.status == PaymentStatus.PENDING) {
            this.status = PaymentStatus.PAID;
        }
    }

    /**
     * Cancels the payment by setting its status to CANCELLED if it is currently PENDING.
     */
    public void cancelPayment() {
        if (this.status == PaymentStatus.PENDING) {
            this.status = PaymentStatus.CANCELLED;
        }
    }
}
