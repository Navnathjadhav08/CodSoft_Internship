import java.util.Scanner;

public class ATM_INTERFACE {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.0); // Initial balance ₹1000.0
        ATM atm = new ATM(userAccount);

        while (true) {
            printMenu();
            Scanner scanner = new Scanner(System.in);
            System.out.print("\u001B[36mEnter your choice: \u001B[0m"); // Cyan color for input text
            int choice = scanner.nextInt();
            atm.processOption(choice);
        }
    }

    private static void printMenu() {
        System.out.println("\u001B[33m*****************************\u001B[0m"); // Yellow color for border
        System.out.println("\u001B[33m**      \u001B[32mWelcome to ATM\u001B[33m      **\u001B[0m"); // Green color for header
        System.out.println("\u001B[33m*****************************\u001B[0m");
        System.out.println("\u001B[36m1. Check Balance\u001B[0m"); // Cyan color for options
        System.out.println("\u001B[36m2. Withdraw\u001B[0m");
        System.out.println("\u001B[36m3. Deposit\u001B[0m");
        System.out.println("\u001B[36m4. Exit\u001B[0m");
        System.out.println("\u001B[33m*****************************\u001B[0m");
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void processOption(int option) {
        Scanner scanner = new Scanner(System.in);
        double amount;
        switch (option) {
            case 1:
                System.out.println("\u001B[32mYour balance: ₹" + account.getBalance() + "\u001B[0m"); // Green color for balance
                break;
            case 2:
                System.out.print("\u001B[36mEnter amount to withdraw: ₹\u001B[0m"); // Cyan color for input text
                amount = scanner.nextDouble();
                if (account.withdraw(amount)) {
                    System.out.println("\u001B[32mWithdrawal successful. Remaining balance: ₹" + account.getBalance() + "\u001B[0m"); // Green color for success message
                } else {
                    System.out.println("\u001B[31mInsufficient funds. Withdrawal failed.\u001B[0m"); // Red color for error message
                }
                break;
            case 3:
                System.out.print("\u001B[36mEnter amount to deposit: ₹\u001B[0m"); // Cyan color for input text
                amount = scanner.nextDouble();
                account.deposit(amount);
                System.out.println("\u001B[32mDeposit successful. Updated balance: ₹" + account.getBalance() + "\u001B[0m"); // Green color for success message
                break;
            case 4:
                System.out.println("\u001B[32mThank you for using the ATM. Goodbye!\u001B[0m"); // Green color for exit message
                System.exit(0);
                break;
            default:
                System.out.println("\u001B[31mInvalid option. Please try again.\u001B[0m"); // Red color for error message
        }
    }
}

