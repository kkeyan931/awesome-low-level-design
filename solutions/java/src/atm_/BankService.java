package atm_;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BankService {
    private Map<String, Account> accounts;

    public BankService() {
        accounts = new ConcurrentHashMap<>();
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public boolean validateCard(String cardNumber) {
        // Simulate card validation with bank backend
        return cardNumber != null && !cardNumber.isEmpty();
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }
}
