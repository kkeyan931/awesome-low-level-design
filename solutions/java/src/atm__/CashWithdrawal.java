package atm__;

public class CashWithdrawal extends Transaction {

    public CashWithdrawal(Integer amount, Account account) {
        super(amount, account);
    }

    @Override
    boolean execute() {
        return this.getAccount().withdraw(this.getAmount());
    }
}
