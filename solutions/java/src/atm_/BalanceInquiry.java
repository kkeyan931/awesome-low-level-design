package atm_;

public class BalanceInquiry extends Transaction {
    public BalanceInquiry(Account account) {
        super(account, 0);
    }

    @Override
    public boolean execute() {
        return true; // Just retrieving balance, no changes
    }

    public double getBalance() {
        return account.getBalance();
    }
}