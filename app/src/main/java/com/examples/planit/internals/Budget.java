package com.examples.planit.internals;

/**
 * Represents a Budget with a unique identifier, total budget amount, and allocated amount.
 * Provides methods to manage and track expenses.
 *
 * @author Ram Amoncar
 * @author Omkar Mhamal
 * @version 1.0
 * @see UniqueIDProvider
 */
public class Budget extends UniqueIDProvider<Budget> {
    private final double totalBudget;
    private double allocated;

    /**
     * Constructs a new Budget instance with a specified initial budget amount.
     *
     * @param initialBudget the total budget to set initially
     */
    public Budget(double initialBudget) {
        super();
        this.totalBudget = initialBudget;
        this.allocated = 0.0;
    }

    /**
     * Constructs a Budget instance with a specified unique identifier, total budget, and allocated amount.
     *
     * @param uid the unique identifier for this budget
     * @param totalBudget the total budget amount
     * @param allocated the amount already allocated
     */
    public Budget(String uid, double totalBudget, double allocated) {
        super(uid);
        this.totalBudget = totalBudget;
        this.allocated = allocated;
    }

    /**
     * Adds an expense to the allocated amount.
     *
     * @param amount the amount to add to allocated budget
     */
    public void addExpense(double amount) {
        allocated += amount;
    }

    /**
     * Gets the total budget amount.
     *
     * @return the total budget
     */
    public double getTotalBudget() {
        return totalBudget;
    }

    /**
     * Gets the currently allocated amount.
     *
     * @return the allocated amount
     */
    public double getAllocated() {
        return allocated;
    }

    /**
     * Calculates and returns the remaining budget amount.
     *
     * @return the remaining budget
     */
    public double getRemainingBudget() {
        return totalBudget - allocated;
    }
}
