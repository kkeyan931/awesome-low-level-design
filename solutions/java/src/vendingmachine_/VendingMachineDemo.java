package vendingmachine_;

import vendingmachine_.data.Product;
import vendingmachine_.data.money.Ten;

public class VendingMachineDemo {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();

        Product product = new Product("Drinks", 30);

        try {
            vendingMachine.selectProduct(product, 1);
        } catch (VendingMachineException e) {
            System.out.println(e.getMessage());
        }

        vendingMachine.addInventory(product, 2);

        vendingMachine.selectProduct(product, 1);

        vendingMachine.insertMoney(new Ten());
        vendingMachine.insertMoney(new Ten());
        vendingMachine.insertMoney(new Ten());

        vendingMachine.dispenseProduct();

        vendingMachine.returnChanges();
    }
}
