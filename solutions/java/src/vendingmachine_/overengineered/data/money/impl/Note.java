package vendingmachine_.overengineered.data.money.impl;

import vendingmachine_.overengineered.data.money.Money;
import vendingmachine_.overengineered.data.money.MoneyType;

public abstract class Note extends Money {
    protected Note(Integer value) {
        super(value, MoneyType.NOTE);
    }
}
