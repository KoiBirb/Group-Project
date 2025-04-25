/*
 * Accounts.Checking.java
 * Leo Bogaert
 * April 24, 2025,
 * Creates a checking account with an overdraft limit.
 */

package Accounts;

public class Checking extends Account {

    private double overdraftLimit;

    /**
     * Constructor for the Accounts.Checking class.
     * @param name The name of the account holder.
     * @param initialBalance The initial balance of the account.
     * @param overdraftLimit The overdraft limit for the account.
     */
    public Checking(String name, double initialBalance, double overdraftLimit) {
        super(name, initialBalance);

        // ensure overdraft limit is not negative
        if (overdraftLimit < 0) {
            overdraftLimit = 0;
            System.out.println("Overdraft limit cannot be negative. Setting to 0.");
        }

        this.overdraftLimit = overdraftLimit;
    }

    /**
     * Withdraws money from the account.
     * @param amount The amount to withdraw.
     * @return true if the withdrawal was successful, false otherwise.
     */
    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        } else if (amount <= balance + overdraftLimit) {
            balance -= amount;
            return true;
        } else {
            System.out.println("Withdrawal amount exceeds balance and overdraft limit.");
            return false;
        }
    }

    /**
     * Deposits money into the account.
     * @param amount The amount to deposit.
     * @return true if the deposit was successful, false otherwise.
     */
    @Override
    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        } else {
            System.out.println("Deposit amount must be positive.");
            return false;
        }
    }

    /**
     * Prints out account information.
     * @return A string containing account information.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Gets the overdraft limit.
     * @return The overdraft limit.
     */
    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    /**
     * Sets the overdraft limit.
     * @param overdraftLimit The new overdraft limit.
     * @return true if the overdraft limit was set successfully, false otherwise.
     */
    public boolean setOverdraftLimit(double overdraftLimit) {
        if (overdraftLimit < 0) {
            System.out.println("Overdraft limit cannot be negative.");
            return false;
        } else {
            this.overdraftLimit = overdraftLimit;
            return true;
        }
    }
}
