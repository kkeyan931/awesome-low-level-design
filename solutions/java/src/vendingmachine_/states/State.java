package vendingmachine_.states;

import vendingmachine_.data.Product;
import vendingmachine_.data.money.Money;

public interface State {
    void selectProduct(Product product, Integer quantity);

    void insertMoney(Money money);

    void dispenseProduct();

    void returnChanges();
}
