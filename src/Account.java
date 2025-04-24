import java.util.Random;

public abstract class Account {

    private String name;
    private final int accountNumber;
    private double balance;

    private final Random rand = new Random();

    public Account (String name, int initialBalance){
        this.name = name;
        this.balance = initialBalance;
        accountNumber = rand.nextInt(9000) + 1000;
    }

    public abstract boolean withdraw(int amount);

    public abstract void deposit(int amount);

    public String toString() {
        return "Account Information:\n" +
                "Account Holder: " + name + "\n" +
                "Account Number: " + accountNumber + "\n" +
                "Account Type: " + this.getClass().getSimpleName() + "\n" +
                "Account Balance: " + balance;
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

}
