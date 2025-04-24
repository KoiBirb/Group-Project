import java.util.Random;

public abstract class Account {

    private String name;
    private final int accountNumber;
    private int balance;

    private final Random rand = new Random();

    public Account (String name, int initialBalance){
        this.name = name;
        this.balance = initialBalance;
        accountNumber = rand.nextInt(9000) + 1000;
    }

    public Account (String name){
        this.name = name;
        this.balance = 0;
        accountNumber = rand.nextInt(9000) + 1000;
    }

    public abstract boolean withdraw(int amount);

    public abstract boolean deposit(int amount);

    public abstract String toString();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getBalance() {
        return balance;
    }

}
