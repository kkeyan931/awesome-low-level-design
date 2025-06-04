package vendingmachine_.states.impl;

import vendingmachine_.VendingMachine;
import vendingmachine_.VendingMachineException;
import vendingmachine_.data.Product;
import vendingmachine_.data.money.Money;
import vendingmachine_.states.State;

public class DispenseState implements State {
    private final VendingMachine vendingMachine;

    public DispenseState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(Product product, Integer quantity) {
        System.out.println("Vending machine is in Dispense state");
    }

    @Override
    public void insertMoney(Money money) {
        System.out.println("Vending machine is in Dispense state");
    }

    @Override
    public void dispenseProduct() {
        if (this.vendingMachine.getTotalMoney() < (this.vendingMachine.getSelectedQuantity() * this.vendingMachine.getSelectedProduct().getPrice())) {
            throw new VendingMachineException("You don't have enough money");
        }
        System.out.println("Dispensing product " + this.vendingMachine.getSelectedProduct().getName());
        this.vendingMachine.setCurrentState(this.vendingMachine.getReturnChangesState());
    }

    @Override
    public void returnChanges() {
        System.out.println("Vending machine is in Dispense state");
    }
}
