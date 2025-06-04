package vendingmachine_.states.impl;

import vendingmachine_.VendingMachine;
import vendingmachine_.VendingMachineException;
import vendingmachine_.data.Product;
import vendingmachine_.data.money.Money;
import vendingmachine_.states.State;

public class IdleState implements State {
    private final VendingMachine vendingMachine;

    public IdleState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(Product product, Integer quantity) {
        boolean inventoryStatus = this.vendingMachine.getInventoryService().checkInventory(product, quantity);

        if (!inventoryStatus) {
            throw new VendingMachineException("The inventory for product " + product.getName() + " is less than " + quantity);
        }
        this.vendingMachine.setCurrentState(this.vendingMachine.getReadyState());
    }

    @Override
    public void insertMoney(Money money) {
        System.out.println("Vending machine is in idle state");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Vending machine is in idle state");
    }

    @Override
    public void returnChanges() {
        System.out.println("Vending machine is in idle state");
    }
}
