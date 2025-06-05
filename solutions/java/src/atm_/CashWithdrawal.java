package atm_;

public class CashWithdrawal extends Transaction {
    private CashDispenser cashDispenser;

    public CashWithdrawal(Account account, double amount, CashDispenser cashDispenser) {
        super(account, amount);
        this.cashDispenser = cashDispenser;
    }

    @Override
    public boolean execute() {
        if (account.withdraw(amount) && cashDispenser.dispenseCash((int) amount)) {
            return true;
        }
        return false;
    }
}