package atm_;

public class CashDeposit extends Transaction {
    public CashDeposit(Account account, double amount) {
        super(account, amount);
    }

    @Override
    public boolean execute() {
        account.deposit(amount);
        return true;
    }
}