//////////////////////////////////////////////////////////////////////////
//
//  Class Name:          ATM_INTERFACE
//  Description:         This program implements a simple ATM (Automated Teller Machine)
//                       interface allowing users to check balance, withdraw money, and
//                       deposit money. The interface is text-based with colored text
//                       using ANSI escape codes for a better user experience. It includes
//                       a menu system with options for different banking operations.
//  Author:              Navnath Jadhav
//  Date:                03-10-2023
//  Update Date:         10-10-2023
//
//////////////////////////////////////////////////////////////////////////

import java.util.Scanner;

public class ATM_INTERFACE {
    public static void main(String[] args) {
        // Initialize user's bank account with an initial balance of ₹1000.0
        BankAccount userAccount = new BankAccount(1000.0);
        // Create an ATM object with the user's bank account
        ATM atm = new ATM(userAccount);

        // Main ATM interface loop
        while (true) {
            // Print the ATM menu
            printMenu();
            Scanner scanner = new Scanner(System.in);
            System.out.print("\u001B[36mEnter your choice: \u001B[0m"); // Cyan color for input text
            int choice = scanner.nextInt();
            // Process the user's choice
            atm.processOption(choice);
        }
    }

    // Method to print the ATM menu
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

// Class representing the user's bank account
class BankAccount {
    private double balance; // Stores the account balance

    // Constructor to initialize the account with an initial balance
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Method to get the account balance
    public double getBalance() {
        return balance;
    }

    // Method to deposit money into the account
    public void deposit(double amount) {
        balance += amount;
    }

    // Method to withdraw money from the account
    // Returns true if withdrawal is successful, false if there are insufficient funds
    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

// Class representing the ATM machine
class ATM {
    private BankAccount account; // Reference to the user's bank account

    // Constructor to initialize the ATM with a user's bank account
    public ATM(BankAccount account) {
        this.account = account;
    }

    // Method to process the user's choice and perform corresponding actions
    public void processOption(int option) {
        Scanner scanner = new Scanner(System.in);
        double amount; // Variable to store the transaction amount entered by the user

        // Switch statement to handle different user choices
        switch (option) {
            case 1:
                // Display user's balance
                System.out.println("\u001B[32mYour balance: ₹" + account.getBalance() + "\u001B[0m"); // Green color for balance
                break;
            case 2:
                // Withdraw money
                System.out.print("\u001B[36mEnter amount to withdraw: ₹\u001B[0m"); // Cyan color for input text
                amount = scanner.nextDouble();
                if (account.withdraw(amount)) {
                    // Withdrawal successful
                    System.out.println("\u001B[32mWithdrawal successful. Remaining balance: ₹" + account.getBalance() + "\u001B[0m"); // Green color for success message
                } else {
                    // Insufficient funds for withdrawal
                    System.out.println("\u001B[31mInsufficient funds. Withdrawal failed.\u001B[0m"); // Red color for error message
                }
                break;
            case 3:
                // Deposit money
                System.out.print("\u001B[36mEnter amount to deposit: ₹\u001B[0m"); // Cyan color for input text
                amount = scanner.nextDouble();
                account.deposit(amount);
                // Deposit successful
                System.out.println("\u001B[32mDeposit successful. Updated balance: ₹" + account.getBalance() + "\u001B[0m"); // Green color for success message
                break;
            case 4:
                // Exit the ATM interface
                System.out.println("\u001B[32mThank you for using the ATM. Goodbye!\u001B[0m"); // Green color for exit message
                System.exit(0);
                break;
            default:
                // Invalid option
                System.out.println("\u001B[31mInvalid option. Please try again.\u001B[0m"); // Red color for error message
        }
    }
}
