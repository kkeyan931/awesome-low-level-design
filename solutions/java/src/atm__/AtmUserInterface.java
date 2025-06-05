package atm__;

import java.util.Scanner;

public class AtmUserInterface {
    private final Atm atm;
    private final Scanner scanner;

    public AtmUserInterface(Atm atm) {
        this.atm = atm;
        scanner = new Scanner(System.in);
    }

    public void start(Account account) {
        while (true) {
            this.showOptions();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    this.handleBalanceEnquiry(account);
                    break;
                case 2:
                    this.handleCashWithdrawal(account);
                    break;
                case 3:
                    this.handleCashDeposit(account);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void handleBalanceEnquiry(Account account) {
        Integer balance = this.atm.balanceEnquiry(account);
        System.out.println("Account balance: " + balance);
    }

    private void handleCashWithdrawal(Account account) {
        System.out.println("Enter amount to withdraw: ");
        int amount = scanner.nextInt();
        this.atm.withdraw(account, amount);
    }

    private void handleCashDeposit(Account account) {
        System.out.println("Enter amount to deposit: ");
        int amount = scanner.nextInt();
        this.atm.deposit(account, amount);
    }

    public void showOptions() {
        System.out.println("Choose an option:");
        System.out.println("1. Balance Enquiry");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Exit\n");
    }
}
