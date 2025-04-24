import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HashMap<Integer, Account> accounts = new HashMap<Integer, Account>();
        Scanner input = new Scanner(System.in);
        String choice;
        
        // Start banking system
        while (true) {
            // Welcome the user
            System.out.println("Welcome to London Central Bank, a small, credit union style bank in downtown London, ON.");

            // Prompt user for what they would like to do
            System.out.println("[C]reate account, [S]how account details, [D]eposits, [W]ithdraw, [Q]uit?");
            System.out.print("Choice: ");
            choice = input.nextLine().trim().toUpperCase();

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
    public void initializeBank(HashMap<Integer, Account> someAccounts) {
        // holder
        Savings holderS;
        Checking holderC;

        // Put the accounts in
        holderC = new Checking("Diego Martin", 668.57, 100.00);
        someAccounts.put(holderC.getAccountNumber(), holderC);
        holderS = new Savings("Janice Watt", 120.00, 1.9);
        someAccounts.put(holderS.getAccountNumber(), holder);
        holderC = new Checking("Michael Rose", 37.65, 0.0);
        someAccounts.put(holderC.getAccountNumber(), holderC);
        
    }
}