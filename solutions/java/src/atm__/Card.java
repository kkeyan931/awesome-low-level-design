package atm__;

public class Card {
    private final String accountNumber;
    private final String cardNumber;

    public Card(String accountNumber, String cardNumber) {
        this.accountNumber = accountNumber;
        this.cardNumber = cardNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }
}
