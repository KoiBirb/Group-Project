/*
 * Accounts.Savings.java
 * Leo Bogaert
 * April 24, 2025,
 * This class creates a savings account with an interest rate.
 */

package Accounts;

public class Savings extends Account {

    private double interestRate;

    /**
     * Constructor for the Accounts.Savings class.
     * @param name The name of the account holder.
     * @param initialBalance The initial balance of the account.
     * @param interestRate The interest rate for the account.
     */
    public Savings(String name, double initialBalance, double interestRate) {
        super(name, initialBalance);

        // ensure interest rate is not negative
        if (interestRate < 0) {
            interestRate = 0;
            System.out.println("Interest Rate cannot be negative. Setting to 0.");
        }

        this.interestRate = interestRate;
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
        } else if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            System.out.println("Withdrawal amount exceeds balance.");
            return false;
        }
    }

    /**
     * Deposits money into the account, adds interest on successful deposit.
     * @param amount The amount to deposit.
     * @return true if the deposit was successful, false otherwise.
     */
    @Override
    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            balance += calculateInterest();
            return true;
        } else {
            System.out.println("Deposit amount must be positive.");
            return false;
        }
    }

    /**
     * Prints out account information including interest rate.
     * @return A string representation of the account information.
     */
    @Override
    public String toString() {
        return super.toString() + "\nInterest Rate: " + interestRate + "%";
    }

    /**
     * Calculates the interest based on the current balance and interest rate.
     * @return The updated balance including calculated interest.
     */
    private double calculateInterest() {
        return balance * (1+ Math.pow(((interestRate/100)/12),12));
    }

    /**
     * Gets the interest rate.
     * @return The interest rate.
     */
    public double getInterestRate() {
        return interestRate;
    }

    /**
     * Sets the interest rate.
     * @param interestRate The new interest rate.
     * @return true if the interest rate was set successfully, false otherwise.
     */
    public boolean setInterestRate(double interestRate) {
        if (interestRate < 0) {
            System.out.println("Interest Rate cannot be negative.");
            return false;
        } else {
            this.interestRate = interestRate;
            return true;
        }
    }
}
