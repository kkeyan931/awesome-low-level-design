package atm__;


public class Atm {
    private final BankBackendSystem bankBackendSystem;
    private final AtmUserInterface atmUserInterface;
    private final CashDispenser cashDispenser;

    public Atm(BankBackendSystem bankBackendSystem, CashDispenser cashDispenser) {
        this.bankBackendSystem = bankBackendSystem;
        this.cashDispenser = cashDispenser;
        this.atmUserInterface = new AtmUserInterface(this);
    }

    public void start(Card card, String pin) {
        if (!bankBackendSystem.authenticate(card.getCardNumber(), pin)) {
            System.out.println("Authentication failed");
            return;
        }
        atmUserInterface.start(bankBackendSystem.getAccount(card.getCardNumber()));
    }

    public Integer balanceEnquiry(Account account) {
        BalanceEnquiry balanceEnquiry = new BalanceEnquiry(account);
        balanceEnquiry.execute();
        return balanceEnquiry.getBalance();
    }

    public void deposit(Account account, int amount) {
        CashDeposit cashDeposit = new CashDeposit(amount, account);
        cashDeposit.execute();
        cashDispenser.loadCash(cashDeposit.getAmount());
    }

    public void withdraw(Account account, int amount) {
        CashWithdrawal cashWithdrawal = new CashWithdrawal(amount, account);
        if (!cashWithdrawal.execute()) {
            System.out.println("Withdrawal failed");
            return;
        }
        cashDispenser.dispense(cashWithdrawal.getAmount());
    }
}
