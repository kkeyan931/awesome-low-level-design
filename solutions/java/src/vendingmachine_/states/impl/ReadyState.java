package vendingmachine_.states.impl;

import vendingmachine_.VendingMachine;
import vendingmachine_.data.Product;
import vendingmachine_.data.money.Money;
import vendingmachine_.states.State;

public class ReadyState implements State {
    private final VendingMachine vendingMachine;

    public ReadyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(Product product, Integer quantity) {
        System.out.println("Vending machine is in ready state");
    }

    @Override
    public void insertMoney(Money money) {
        this.vendingMachine.increaseTotal(money.getValue());

        if (this.vendingMachine.getTotalMoney() >= (this.vendingMachine.getSelectedQuantity() * this.vendingMachine.getSelectedProduct().getPrice())) {
            this.vendingMachine.setCurrentState(this.vendingMachine.getDispenseState());
        }
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Vending machine is in ready state");
    }

    @Override
    public void returnChanges() {
        System.out.println("Vending machine is in ready state");
    }
}
