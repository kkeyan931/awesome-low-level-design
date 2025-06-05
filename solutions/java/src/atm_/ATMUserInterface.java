package atm_;

import java.util.Scanner;

public class ATMUserInterface {
    private Scanner scanner;

    public ATMUserInterface() {
        scanner = new Scanner(System.in);
    }

    public void displayMenu(Account account) {
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Balance Inquiry");
            System.out.println("2. Cash Withdrawal");
            System.out.println("3. Cash Deposit");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            Transaction transaction = null;

            switch (choice) {
                case 1:
                    transaction = new BalanceInquiry(account);
                    if (transaction.execute()) {
                        System.out.println("Balance: $" + ((BalanceInquiry) transaction).getBalance());
                    }
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    transaction = new CashWithdrawal(account, withdrawAmount, new CashDispenser(10000));
                    if (transaction.execute()) {
                        System.out.println("Withdrawal successful");
                    } else {
                        System.out.println("Insufficient funds or cash unavailable");
                    }
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    transaction = new CashDeposit(account, depositAmount);
                    if (transaction.execute()) {
                        System.out.println("Deposit successful");
                    }
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM");
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    public void displayError(String message) {
        System.out.println("Error: " + message);
    }
}
