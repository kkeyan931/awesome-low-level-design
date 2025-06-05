package atm_;

public class ATM {
    private BankService bankService;
    private CashDispenser cashDispenser;
    private ATMUserInterface userInterface;

    public ATM(BankService bankService, CashDispenser cashDispenser, ATMUserInterface userInterface) {
        this.bankService = bankService;
        this.cashDispenser = cashDispenser;
        this.userInterface = userInterface;
    }

    public boolean authenticate(Card card, String pin) {
        if (bankService.validateCard(card.getCardNumber())) {
            Account account = bankService.getAccount(card.getAccountNumber());
            if (account != null && account.validatePin(pin)) {
                return true;
            }
        }
        return false;
    }

    public void startSession(Card card, String pin) {
        if (authenticate(card, pin)) {
            Account account = bankService.getAccount(card.getAccountNumber());
            userInterface.displayMenu(account);
        } else {
            userInterface.displayError("Invalid card or PIN");
        }
    }

    public boolean performTransaction(Account account, Transaction transaction) {
        return transaction.execute();
    }
}
