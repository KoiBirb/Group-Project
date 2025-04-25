import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final Scanner input = new Scanner(System.in);
    private static final HashMap<Integer, Account> accounts = new HashMap<>();

    public static void main(String[] args) {
        String choice;

        initializeBank(accounts);
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
                    break;

                case "S":
                    System.out.println(getAccount());
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
            input.nextLine();
        }
    }

    /**
     * Initializes the bank by adding 3 required accounts
     * @param someAccounts  The hashmap that will store the accounts
     */
    public static void initializeBank(HashMap<Integer, Account> someAccounts) {

        Account holder;

        // Put the accounts in hashmap
        holder = new Checking("Diego Martin", 668.57, 100.00);
        someAccounts.put(holder.getAccountNumber(), holder);
        System.out.println(holder.getAccountNumber());
        holder = new Savings("Janice Watt", 120.00, 1.9);
        someAccounts.put(holder.getAccountNumber(), holder);
        holder = new Checking("Michael Rose", 37.65, 0.0);
        someAccounts.put(holder.getAccountNumber(), holder);
        
    }

    /**
     * Creates a Savings account
     */
    public static void createSavingsAccount(String name, double initial, double interest) {
        Account holder;
        holder = new Savings(name, initial, interest);
        accounts.put(holder.getAccountNumber(), holder);

        System.out.println("Account Created! Account number: " + holder.getAccountNumber());
    }

    /**
     * Creates a Checking account
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
}