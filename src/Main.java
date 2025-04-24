import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        HashMap<Integer, Account> accounts = new HashMap<>();
        Scanner input = new Scanner(System.in);
        String choice;
        
        // Start banking system
        initializeBank(accounts);

        // Welcome the user
        System.out.println("Welcome to London Central Bank, a small, credit union style bank in downtown London, ON.");

        while (true) {
            // Prompt user for what they would like to do
            boolean validChoice;

            // Ensure the user's choice is valid
            do {
                System.out.println("[C]reate account, [S]how account details, [D]eposits, [W]ithdraw, [Q]uit?");
                System.out.print("Choice: ");
                choice = input.nextLine().trim().toUpperCase();
                System.out.println();

                validChoice = choice.equals("C") || choice.equals("S") || choice.equals("D") ||
                        choice.equals("W") || choice.equals("Q");

                if (!validChoice)
                    System.out.println("Invalid choice. Please try again.");

            } while (!validChoice);

            // Decide what to do based on choice
            switch (choice) {
                // If the user wants to QUIT
                case "Q": System.exit(0);

                case "C": // If the user wants to CREATE an account
                    while (true) {
                        // Create variables to store info. for the new account
                        String name;
                        Double balance, interestOrOverdraft;

                        // Prompt user for the kind of account, name, and balance and store the values
                        System.out.print("Type of Account ([S]avings or [C]hecking): ");
                        choice = input.nextLine().trim().toUpperCase();
                        
                        // Make sure the user's input is valid before continuing
                        if (!(choice.equals("S") || choice.equals("C"))) {
                            System.out.println("No no no, the only options were S and C!");
                            continue;
                        }
                        System.out.println("When inputing a name, make sure everything is capitalized correctly.");
                        System.out.println("Our systems are case sensitive. Input first name followed by a space, then last name.");
                        System.out.print("Name: ");
                        name = input.nextLine().trim(); // What this doesn't deal with is extra spaces between the names

                        // If the name is invalid, inform the user
                        if ((name.split(" ")).length == 1) {
                            System.out.println("So you're telling me you go by " + name + "?");
                            System.out.println("Like, hey " + name + "!");
                            System.out.println("Try again.");
                            System.out.println();
                            continue;
                        }
                        System.out.print("Balance: ");
                        balance = toDouble(input.nextLine());
                        if (balance == null) {
                            System.out.println("Well, that's not a number. What do you want me to do? Try again.");
                            System.out.println();
                            continue;
                        }

                        // Based on their choice, ask them the specific questions and create the corresponding account
                        if (choice.equals("S")) { // savings
                            System.out.print("Interest Rate: ");
                            interestOrOverdraft = toDouble(input.nextLine().trim());
                            if (interestOrOverdraft == null) {
                                System.out.println("Should I choose the rate for you? I'm thinking negative will work.");
                                System.out.println("Kidding. Try again.");
                                System.out.println();
                                continue;
                            }
                            // Create account
                            createSavingsAccount(accounts, name, balance, interestOrOverdraft);

                        } else if (choice.equals("C")) { // checking
                            System.out.print("Overdraft Limit: ");
                            interestOrOverdraft = toDouble(input.nextLine());
                            if (interestOrOverdraft == null) {
                                System.out.println("Ummm...try again.");
                                continue;
                            }
                            // Create account
                            createCheckingAccount(accounts, name, balance, interestOrOverdraft);
                        }

                        // Let user know the account has been created
                        System.out.println("Account Created.");
                    }

                case "S": // If the user wants account details to be shown
                    String nameOrNumber;
                    int accountNumber;
                    System.out.print("Account number or name (First then Last): ");
                    nameOrNumber = input.nextLine().trim();
                    System.out.println();
                    try {
                        // See if the input was a number
                        accountNumber = Integer.valueOf(nameOrNumber);
                        // Use account number to find account and display info
                        System.out.println((accounts.get(accountNumber)).toString());
                        System.out.println();
                    } catch (Exception e) { // treat the input like a name
                        ArrayList<Account> showAccounts = findAccounts(accounts, nameOrNumber);
                        System.out.println("There are " + showAccounts.size() + " account(s) with the name " + nameOrNumber);
                        System.out.println();

                        // Display all the account info
                        for (Account acc: showAccounts) {
                            System.out.println(acc.toString());
                            System.out.println();
                        }
                    }
                    break;

                case "D": // If the user wants to make a deposit
                    String nameOrNumber2;
                    int accountNumber2;
                    Double deposit;
                    System.out.println("Account number or name (First then Last): ");
                    nameOrNumber2 = input.nextLine().trim();
                    // Prompt user for a valid double deposit
                    while (true) {
                        System.out.print("Deposit: ");
                        deposit = toDouble(input.nextLine());
                        if (deposit != null) break;
                        System.out.println("Try again.");
                    }

                    try {
                        // See if the input was a number
                        accountNumber2 = Integer.valueOf(nameOrNumber2);
                        // Use account number to find account and deposit
                        (accounts.get(accountNumber2)).deposit(deposit);
                        break;
                    } catch (Exception e) { // treat the input like a name
                        ArrayList<Account> showAccounts = findAccounts(accounts, nameOrNumber2);
                        System.out.println("There are " + showAccounts.size() + " with the name " + nameOrNumber2);

                        // If there are no accounts matching the name, the program shouldn't continue
                        if (showAccounts.size() == 0) {
                            System.out.println("Cancelling...");
                            break;
                        }
                        // Display all the account info so the user can choose an account
                        for (Account acc: showAccounts) {
                            acc.toString();
                            System.out.println();
                        }

                        // Prompt the user for the account number
                        System.out.println("Please input the account number of the account you would like to make a deposit in.");
                        while (true) {
                            try {
                                System.out.println("Account Number: ");
                                accountNumber2 = input.nextInt();
                                (accounts.get(accountNumber2)).deposit(deposit);
                                break;
                            } catch (Exception k) {
                                System.out.println("That is not one of the accounts listed.");
                            }
                        }
                        break;
                    }
                case "W": // If the user wants to make a withdrawal
                String nameOrNumber3;
                    int accountNumber3;
                    Double withdraw;
                    System.out.println("Account number or name (First then Last): ");
                    nameOrNumber3 = input.nextLine().trim();
                    // Prompt user for a valid double deposit
                    while (true) {
                        System.out.print("Deposit: ");
                        withdraw = toDouble(input.nextLine());
                        if (withdraw != null) break;
                        System.out.println("Try again.");
                    }

                    try {
                        // See if the input was a number
                        accountNumber3 = Integer.valueOf(nameOrNumber3);
                        // Use account number to find account and deposit
                        (accounts.get(accountNumber3)).withdraw(withdraw);
                        break;
                    } catch (Exception e) { // treat the input like a name
                        ArrayList<Account> showAccounts = findAccounts(accounts, nameOrNumber3);
                        System.out.println("There are " + showAccounts.size() + " with the name " + nameOrNumber3);

                        // If there are no accounts matching the name, the program shouldn't continue
                        if (showAccounts.size() == 0) {
                            System.out.println("Cancelling...");
                            break;
                        }
                        // Display all the account info so the user can choose an account
                        for (Account acc: showAccounts) {
                            acc.toString();
                            System.out.println();
                        }

                        // Prompt the user for the account number
                        System.out.println("Please input the account number of the account you would like to make a deposit in.");
                        while (true) {
                            try {
                                System.out.println("Account Number: ");
                                accountNumber3 = input.nextInt();
                                (accounts.get(accountNumber3)).withdraw(withdraw);
                                break;
                            } catch (Exception k) {
                                System.out.println("That is not one of the accounts listed.");
                            }
                        }
                        break;
                    }
                default: System.out.println("Hmmm, why are you here?");
            }
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
        holder = new Savings("Janice Watt", 120.00, 1.9);
        someAccounts.put(holder.getAccountNumber(), holder);
        holder = new Checking("Michael Rose", 37.65, 0.0);
        someAccounts.put(holder.getAccountNumber(), holder);
        
    }

    /**
     * Tries to convert a string to a double
     * @param pleaseConvert The string value to be converted
     * @return  The double or null if it can't be converted
     */
    public static Double toDouble(String pleaseConvert) {
        Double myDouble;
        try {
            myDouble = Double.valueOf(pleaseConvert);
            return myDouble;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Creates a Savings account
     */
    public static void createSavingsAccount(HashMap<Integer, Account> someAccounts, String name, double initial, double interest) {
        Account holder;
        holder = new Savings(name, initial, interest);
        someAccounts.put(holder.getAccountNumber(), holder);
    }

    /**
     * Creates a Checking account
     */
    public static void createCheckingAccount(HashMap<Integer, Account> someAccounts, String name, double initial, double overdraft) {
        Account holder;
        holder = new Checking(name, initial, overdraft);
        someAccounts.put(holder.getAccountNumber(), holder);
    }

    /**
     * Finds account using a name
     */
    public static ArrayList<Account> findAccounts(HashMap<Integer, Account> someAccounts, String name) {
        ArrayList<Account> matchingAccounts = new ArrayList<Account>();

        // Add all the accounts that have the name
        for (Account acc: someAccounts.values()) {
            if ((acc.getName()).equals(name)) matchingAccounts.add(acc);
        }

        return matchingAccounts;
    }
}