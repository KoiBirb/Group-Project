import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final Scanner input = new Scanner(System.in);
    private static final HashMap<Integer, Account> accounts = new HashMap<>();

    public static void main(String[] args) {
        String choice;

        initializeBank();
        System.out.println("Welcome to London Central Bank, a small, credit union style bank in downtown London, ON.");

        while (true) {
            boolean validChoice;

            do {
                System.out.println("[C]reate account, [S]how account details, [D]eposits, [W]ithdraw, [Q]uit?");
                System.out.print("Choice: ");
                choice = input.nextLine().trim().toUpperCase();

                validChoice = choice.equals("C") || choice.equals("S") || choice.equals("D") ||
                        choice.equals("W") || choice.equals("Q");

                if (!validChoice)
                    System.out.println("Invalid choice. Please try again.");

            } while (!validChoice);

            switch (choice) {
                case "Q":
                    input.close();
                    System.out.println("Thanks for banking with us. We hope to see you again soon.");
                    System.exit(0);
                    break;

                case "C":
                    String name;
                    double balance = 0;

                    do {
                        System.out.print("Type of Account ([S]avings or [C]hecking): ");
                        choice = input.nextLine().trim().toUpperCase();

                        validChoice = choice.equals("S") || choice.equals("C");

                        if (!validChoice)
                            System.out.println("Invalid choice. Please try again.");

                    } while (!validChoice);

                    do {
                        System.out.print("Account Holder Name (First Last): ");
                        name = input.nextLine().trim();

                        validChoice = !name.isEmpty();

                        if (!validChoice)
                            System.out.println("Invalid name. Please try again.");

                    } while (!validChoice);


                    do {
                        validChoice = false;
                        System.out.print("Balance: ");

                        try {
                            balance = input.nextDouble();
                            validChoice = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid balance. Please try again.");
                            input.nextLine();
                        }
                    } while (!validChoice);


                    if (choice.equals("S")) {

                        double intrestRate = 0;

                        do {
                            System.out.print("Interest Rate (%): ");

                            try {
                                intrestRate = input.nextDouble();
                                validChoice = intrestRate >= 0;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid interest rate. Please try again.");
                                input.nextLine();
                            }

                            if (!validChoice)
                                System.out.println("Invalid interest rate. Please try again.");

                        } while (!validChoice);

                        createSavingsAccount(name, balance, intrestRate);

                    } else {

                        double overdraft = 0;

                        do {
                            System.out.print("Overdraft Limit: ");

                            try {
                                overdraft = input.nextDouble();
                                validChoice = overdraft >= 0;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid overdraft limit. Please try again.");
                                input.nextLine();
                            }

                            if (!validChoice)
                                System.out.println("Invalid overdraft limit. Please try again.");
                        } while (!validChoice);

                        createCheckingAccount(name, balance, overdraft);
                    }
                    input.nextLine();
                    break;

                case "S":
                    showAccountDetails();
                    break;

                case "D":
                    double deposit = 0;

                    System.out.println("What account would you like to use? ");
                    Account depositAccount = getAccount();

                    do {
                        System.out.print("How much would you like to deposit: ");

                        try {
                            deposit = input.nextDouble();
                            if (deposit < 0)
                                System.out.println("Deposit must be positive.");
                            else
                                validChoice = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid deposit. Please try again.");
                            input.nextLine();
                            validChoice = false;
                        }
                    } while (!validChoice);

                    depositAccount.deposit(deposit);
                    System.out.println("Deposit successful. New balance: $" + String.format("%.2f", depositAccount.getBalance()));
                    break;

                case "W":
                    double withdrawl = 0;

                    System.out.print("What account would you like to use? ");
                    Account withdrawlAccount = getAccount();

                    do {
                        System.out.print("How much would you like to withdraw: ");

                        try {
                            withdrawl = input.nextDouble();
                            validChoice = withdrawlAccount.withdraw(withdrawl);
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid number. Please try again.");
                            input.nextLine();
                            validChoice = false;
                        }
                    } while (!validChoice);
                    System.out.println("Withdrawal successful. New balance: $" + String.format("%.2f", withdrawlAccount.getBalance()));
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            if (! choice.equals("S")) input.nextLine();
            System.out.println();
        }
    }

    /**
     * Initializes the bank by adding 3 required accounts
     */
    public static void initializeBank() {

        Account holder;

        // Put the accounts in hashmap
        holder = new Checking("Diego Martin", 668.57, 100.00);
        accounts.put(holder.getAccountNumber(), holder);
        System.out.println(holder.getName() + ": " + holder.getAccountNumber());
        holder = new Savings("Janice Watt", 120.00, 1.9);
        accounts.put(holder.getAccountNumber(), holder);
        System.out.println(holder.getName() + ": " + holder.getAccountNumber());
        holder = new Checking("Michael Rose", 37.65, 0.0);
        accounts.put(holder.getAccountNumber(), holder);
        System.out.println(holder.getName() + ": " + holder.getAccountNumber());
        System.out.println("---------------------------------------");
        System.out.println();
        
    }

    /**
     * Creates a Savings account
     * @param name  The name for the account
     * @param initial   The initial balance of the account
     * @param interest The interest rate of the account
     */
    public static void createSavingsAccount(String name, double initial, double interest) {
        Account holder;
        holder = new Savings(name, initial, interest);
        accounts.put(holder.getAccountNumber(), holder);

        System.out.println("Account Created! Account number: " + holder.getAccountNumber());
    }

    /**
     * Creates a Checking account
     * @param name  The name for the account
     * @param initial   The initial balance of the account
     * @param overdraft The overdraft limit of the account
     */
    public static void createCheckingAccount(String name, double initial, double overdraft) {
        Account holder;
        holder = new Checking(name, initial, overdraft);
        accounts.put(holder.getAccountNumber(), holder);

        System.out.println("Account Created! Account number: " + holder.getAccountNumber());
    }

    /**
     * Gets an account from the user using an account number
     * @return The account that was found
     */
    public static Account getAccount () {
        Account account = null;

        do {
            System.out.print("Account number: ");

            try {
                int accountNumber = input.nextInt();
                account = accounts.get(accountNumber);
            } catch (InputMismatchException e) {
                System.out.println("Invalid number. Please try again.");
            }

            if (account == null)
                System.out.println("Account not found. Please try again.");

        } while (account == null);

        return account;
    }

    /**
     * Finds account using a name
     * @param name  The name to look for in the hashmap of accounts
     * @return An arraylist of all the accounts that match the specified name
     */
    public static ArrayList<Account> findAccounts(String name) {
        ArrayList<Account> matchingAccounts = new ArrayList<Account>();

        // Add all the accounts that have the name
        for (Account acc: accounts.values()) {
            if ((acc.getName()).equals(name)) matchingAccounts.add(acc);
        }

        return matchingAccounts;
    }

    /**
     * Shows account using account name or account number
     */
    public static void showAccountDetails() {
        String nameOrNumber;
        int accountNumber;
        System.out.print("Account number or name (First then Last): ");
        nameOrNumber = input.nextLine().trim();
        System.out.println();
        try {
            // See if the input was a number
            accountNumber = Integer.valueOf(nameOrNumber);
            // Use account number to find and display info.
            System.out.println(accounts.get(accountNumber));
        } catch (Exception e) { // treat the input like a name
            ArrayList<Account> showAccounts = findAccounts(nameOrNumber);
            System.out.println("There are " + showAccounts.size() + " account(s) with the name " + nameOrNumber);

            // Display all the account info
            for (Account acc: showAccounts) {
                System.out.println();
                System.out.println(acc.toStringHidden());
            }
        }
    }
}