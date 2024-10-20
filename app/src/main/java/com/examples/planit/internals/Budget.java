package com.examples.planit.internals;

public class Budget extends UniqueIDProvider<Budget> {
    private final double totalBudget;
    private double allocated;

    public Budget(double initialBudget) {
        super();
        this.totalBudget = initialBudget;
        this.allocated = 0.0;
    }

    public Budget(String uid, double totalBudget, double allocated) {
        super(uid);
        this.totalBudget = totalBudget;
        this.allocated = allocated;
    }

    public void addExpense(double amount) {
        allocated += amount;
    }

    public double getTotalBudget() {
        return totalBudget;
    }

    public double getAllocated() {
        return allocated;
    }

    public double getRemainingBudget() {
        return totalBudget - allocated;
    }
}
