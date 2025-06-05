package atm__;

public class Main {
    public static void main(String[] args) {

        Account account = new Account("122334", "1321333", 1000, "1234");

        BankBackendSystem bankBackendSystem = new BankBackendSystem();

        bankBackendSystem.addAccount(account);

        CashDispenser cashDispenser = new CashDispenser(10000);

        Atm atm = new Atm(bankBackendSystem, cashDispenser);

        Card card = new Card("122334", "1321333");

        atm.start(card, "1234");
    }
}
