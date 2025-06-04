package vendingmachine_.states.impl;

import vendingmachine_.VendingMachine;
import vendingmachine_.data.Product;
import vendingmachine_.data.money.Money;
import vendingmachine_.states.State;

public class ReturnChangesState implements State {
    private final VendingMachine vendingMachine;

    public ReturnChangesState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(Product product, Integer quantity) {
        System.out.println("Vending machine is in Return changes state");
    }

    @Override
    public void insertMoney(Money money) {
        System.out.println("Vending machine is in Return changes state");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Vending machine is in Return changes state");
    }

    @Override
    public void returnChanges() {
        int remainingChanges = vendingMachine.getTotalMoney() - (this.vendingMachine.getSelectedQuantity() * this.vendingMachine.getSelectedProduct().getPrice());
        if (remainingChanges > 0) {
            System.out.println("Returning changes " + remainingChanges);
        } else {
            System.out.println("No remaining changes available");
        }
        this.vendingMachine.setCurrentState(this.vendingMachine.getIdleState());
        this.vendingMachine.resetStates();
    }
}
