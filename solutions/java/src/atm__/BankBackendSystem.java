package atm__;

import java.util.HashMap;
import java.util.Map;

public class BankBackendSystem {
    private Map<String, Account> cardToAccountMap;

    public BankBackendSystem() {
        this.cardToAccountMap = new HashMap<>();
    }

    public boolean authenticate(String cardNumber, String cardPin) {
        Account account = this.cardToAccountMap.get(cardNumber);
        if (account == null) {
            return false;
        }
        return account.getCardPin().equals(cardPin);
    }

    public Account getAccount(String cardNumber) {
        return this.cardToAccountMap.get(cardNumber);
    }

    public Account addAccount(Account account) {
        this.cardToAccountMap.put(account.getCardNumber(), account);
        return account;
    }
}
