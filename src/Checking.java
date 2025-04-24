public class Checking extends Account{

    private double overdraftLimit;

    public Checking(String name, int initialBalance, double overdraftLimit) {
        super(name, initialBalance);

        if (overdraftLimit < 0) {
            overdraftLimit = 0;
            System.out.println("Overdraft limit cannot be negative. Setting to 0.");
        }

        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public boolean withdraw(int amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        } else if (amount <= getBalance() + overdraftLimit) {
            setBalance(getBalance() - amount);
            return true;
        } else {
            System.out.println("Withdrawal amount exceeds balance and overdraft limit.");
            return false;
        }
    }

    @Override
    public boolean deposit(int amount) {
        if (amount > 0) {
            setBalance(getBalance() + amount);
            return true;
        } else {
            System.out.println("Deposit amount must be positive.");
            return false;
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\nOverdraft Limit: " + overdraftLimit;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

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
