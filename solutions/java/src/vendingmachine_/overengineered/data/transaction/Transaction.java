package vendingmachine_.overengineered.data.transaction;

import vendingmachine_.overengineered.data.Cart;
import vendingmachine_.overengineered.data.money.Money;

import java.util.List;

public class Transaction {
    private final String id;
    private final Cart cart;
    private final List<Money> money;
    private TransactionStatus status;

    public Transaction(String id, Cart cart, List<Money> money) {
        this.id = id;
        this.cart = cart;
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public Cart getCart() {
        return cart;
    }

    public List<Money> getMoney() {
        return money;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
}
