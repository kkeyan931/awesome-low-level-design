package atm_;

public class Card {
    private String cardNumber;
    private String accountNumber;

    public Card(String cardNumber, String accountNumber) {
        this.cardNumber = cardNumber;
        this.accountNumber = accountNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}