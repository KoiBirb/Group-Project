import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        HashMap<Integer, Account> accounts = new HashMap<>();
        Scanner input = new Scanner(System.in);
        String choice;
        
        // Start banking system

        initializeBank(accounts);

        while (true) {
            // Welcome the user
            System.out.println("Welcome to London Central Bank, a small, credit union style bank in downtown London, ON.");

            // Prompt user for what they would like to do
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

            // Decide what to do based on choice
            if (choice == "Q") {
                System.exit(0);
            } else if (choice == "C") {
                System.out.println("")
            }  else if (choice == "S") {

            }  else if (choice == "D") {

            }  else if (choice == "W") {

            } else {
                System.out.println("");
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
}