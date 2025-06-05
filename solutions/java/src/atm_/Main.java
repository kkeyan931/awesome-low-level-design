package atm_;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BankService bankService = new BankService();
        CashDispenser cashDispenser = new CashDispenser(10000);
        ATMUserInterface userInterface = new ATMUserInterface();
        ATM atm = new ATM(bankService, cashDispenser, userInterface);

        // Sample account setup
        Account account = new Account("123456789", 1000.0, "1234");
        bankService.addAccount(account);
        Card card = new Card("987654321", "123456789");

        // Start ATM session
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();
        atm.startSession(card, pin);
    }
}
