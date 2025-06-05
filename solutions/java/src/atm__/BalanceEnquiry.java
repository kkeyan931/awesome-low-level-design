package atm__;

public class BalanceEnquiry extends Transaction {

    protected BalanceEnquiry(Account account) {
        super(0, account);
    }

    @Override
    boolean execute() {
        return true;
    }

    Integer getBalance() {
        return this.getAccount().getBalance();
    }
}
