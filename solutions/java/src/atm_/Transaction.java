package atm_;

public abstract class Transaction {
    protected Account account;
    protected double amount;

    protected Transaction(Account account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    public abstract boolean execute();
}