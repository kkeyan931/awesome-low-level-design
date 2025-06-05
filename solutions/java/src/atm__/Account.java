package atm__;

public class Account {
    private final String accountNumber;
    private Integer balance;
    private final String cardNumber;
    private final String cardPin;

    public Account(String accountNumber, String cardNumber, Integer balance, String cardPin) {
        this.accountNumber = accountNumber;
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.cardPin = cardPin;
    }

    public boolean deposit(int amount) {
        this.balance += amount;
        return true;
    }

    public boolean withdraw(int amount) {
        if (this.balance < amount) {
            return false;
        }
        this.balance -= amount;
        return true;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Integer getBalance() {
        return balance;
    }

    public String getCardPin() {
        return cardPin;
    }

    public String getCardNumber() {
        return cardNumber;
    }
}
