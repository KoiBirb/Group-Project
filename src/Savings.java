public class Savings extends Account{

    private double interestRate;

    public Savings(String name, int initialBalance, double interestRate) {
        super(name, initialBalance);

        if (interestRate < 0) {
            interestRate = 0;
            System.out.println("Interest Rate cannot be negative. Setting to 0.");
        }

        this.interestRate = interestRate;
    }

    @Override
    public boolean withdraw(int amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        } else if (amount <= getBalance()) {
            setBalance(getBalance() - amount);
            return true;
        } else {
            System.out.println("Withdrawal amount exceeds balance.");
            return false;
        }
    }

    @Override
    public boolean deposit(int amount) {
        if (amount > 0) {
            setBalance(getBalance() + amount);

            // Add interest to the balance
            setBalance(calculateInterest());
            return true;
        } else {
            System.out.println("Deposit amount must be positive.");
            return false;
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\nInterest Rate: " + interestRate;
    }

    private double calculateInterest() {
        return getBalance() * (1+ Math.pow((interestRate/12),12));
    }

    public double getInterestRate() {
        return interestRate;
    }

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
