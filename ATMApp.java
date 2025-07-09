import java.util.Scanner;

// 1. Class to represent the user's bank account
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0;
        }
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

// 2. Class to represent the ATM machine
class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    // 3. Main interface loop
    public void start() {
        int choice;
        do {
            System.out.println("\n===== ATM Menu =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> checkBalance();
                case 2 -> deposit();
                case 3 -> withdraw();
                case 4 -> System.out.println("Thank you for using the ATM. Goodbye!");
                default -> System.out.println("Invalid option. Please choose 1-4.");
            }
        } while (choice != 4);
    }

    // 3.1 Check balance
    private void checkBalance() {
        System.out.printf("Your current balance is: $%.2f%n", account.getBalance());
    }

    // 3.2 Deposit method
    private void deposit() {
        System.out.print("Enter amount to deposit: $");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
        } else {
            account.deposit(amount);
            System.out.println("Deposit successful.");
            checkBalance();
        }
    }

    // 3.3 Withdraw method
    private void withdraw() {
        System.out.print("Enter amount to withdraw: $");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful.");
            checkBalance();
        } else {
            System.out.println("Insufficient balance. Transaction failed.");
        }
    }
}

// Main class with main method
public class ATMApp {
    public static void main(String[] args) {
        // Initialize a bank account with a starting balance
        BankAccount userAccount = new BankAccount(500.00); // starting with $500

        // Start the ATM interface
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}
