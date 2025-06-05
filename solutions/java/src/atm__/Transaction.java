package atm__;

public abstract class Transaction {
    private final Integer amount;
    private final Account account;

    protected Transaction(Integer amount, Account account) {
        this.amount = amount;
        this.account = account;
    }

    public Integer getAmount() {
        return amount;
    }

    public Account getAccount() {
        return account;
    }

    abstract boolean execute();
}
