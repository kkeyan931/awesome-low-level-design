package vendingmachine_.overengineered.data.money.impl;

import vendingmachine_.overengineered.data.money.Money;
import vendingmachine_.overengineered.data.money.MoneyType;

public abstract class Coin extends Money {
    protected Coin(Integer value) {
        super(value, MoneyType.COIN);
    }
}
