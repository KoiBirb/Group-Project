package Accounts;/*
 * Accounts.Account.java
 * Leo Bogaert
 * April 24, 2025,
 * Abstract class for accounts, serves as a template for the Accounts.Checking and Accounts.Savings classes.
 */

import java.util.Random;

public abstract class Account {

    protected String name;
    protected final int accountNumber;
    protected double balance;

    /**
     * Constructor for the Accounts.Account class.
     * @param name The name of the account holder.
     * @param initialBalance The initial balance of the account.
     */
    public Account (String name, double initialBalance){
        this.name = name;
        this.balance = initialBalance;
        Random rand = new Random();
        accountNumber = rand.nextInt(9000) + 1000;
    }

    /**
     * Abstract method to withdraw money from the account.
     * @param amount The amount to withdraw.
     * @return true if the withdrawal was successful, false otherwise.
     */
    public abstract boolean withdraw(double amount);

    /**
     * Abstract method to deposit money into the account.
     * @param amount The amount to deposit.
     * @return true if the deposit was successful, false otherwise.
     */
    public abstract boolean deposit(double amount);

    /**
     * Method to print out account information.
     */
    public String toString() {
       return   "Accounts.Account Holder: " + name + "\n" +
                "Accounts.Account Number: " + accountNumber + "\n" +
                "Accounts.Account Type: " + this.getClass().getSimpleName() + "\n" +
                "Accounts.Account Balance: $" + String.format("%.2f", balance);
    };

    /**
     * Gets the account name.
     * @return The account name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the account holder's name.
     * @param name The new name of the account holder.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the account number.
     * @return The account number.
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * Gets the account balance.
     * @return The account balance.
     */
    public double getBalance() {
        return balance;
    }
}
