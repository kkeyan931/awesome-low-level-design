package atm__;

public class CashDeposit extends Transaction {

    protected CashDeposit(Integer amount, Account account) {
        super(amount, account);
    }

    @Override
    boolean execute() {
        return this.getAccount().deposit(this.getAmount());
    }
}
