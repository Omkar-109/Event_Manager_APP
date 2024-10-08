package com.examples.planit.internals;

public class Budget {
    private double totalBudget;
    private double expenses;

    public Budget(double initialBudget) {

        this.totalBudget = initialBudget;
        this.expenses = 0.0;
    }

    public void addExpense(double amount) {
        expenses += amount;
    }

    public double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(double newBudget) {
        this.totalBudget = newBudget;
    }

    public double getExpenses() {
        return expenses;
    }

    public double getRemainingBudget() {
        return totalBudget - expenses;
    }
}
