package com.examples.planit.internals;

import java.util.Date;

public class Payment extends UniqueIDProvider<Payment>{
    private double amount;
    private Date date;
    private long payTo;
    private PaymentStatus status;

    public Payment(double amount, Date date, long payTo, PaymentStatus status) {
        super();
        this.amount = amount;
        this.date = date;
        this.payTo = payTo;
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getPayTo() {
        return payTo;
    }

    public void setPayTo(long payTo) {
        this.payTo = payTo;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public void confirmPayment() {
        if (this.status == PaymentStatus.PENDING) {
            this.status = PaymentStatus.PAID;
        }
    }

    public void cancelPayment() {
        if (this.status == PaymentStatus.PENDING) {
            this.status = PaymentStatus.CANCELLED;
        }
    }
}
