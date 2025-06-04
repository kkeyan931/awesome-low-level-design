package vendingmachine_.overengineered;

import vendingmachine_.overengineered.data.Cart;
import vendingmachine_.overengineered.data.Product;
import vendingmachine_.overengineered.data.ProductType;
import vendingmachine_.overengineered.data.money.Money;
import vendingmachine_.overengineered.data.money.impl.notes.Fifty;
import vendingmachine_.overengineered.data.money.impl.notes.Hundred;

import java.util.ArrayList;
import java.util.List;

public class VendingMachineSystemDemo {
    public static void main(String[] args) {
        VendingMachineSystem vendingMachineSystem = new VendingMachineSystem();

        Product beer = vendingMachineSystem.createProduct("Beer", ProductType.DRINK);
        Product cake = vendingMachineSystem.createProduct("Cake", ProductType.SWEET);
        Product chocolate = vendingMachineSystem.createProduct("Chocolate", ProductType.SWEET);

        vendingMachineSystem.updateInventory(beer.getId(), 10);
        vendingMachineSystem.updateInventory(cake.getId(), 5);
        vendingMachineSystem.updateInventory(chocolate.getId(), 5);

        vendingMachineSystem.updatePrice(beer.getId(), 100);
        vendingMachineSystem.updatePrice(cake.getId(), 50);
        vendingMachineSystem.updatePrice(chocolate.getId(), 10);


        Cart myCart = vendingMachineSystem.createEmptyCart();

        myCart = vendingMachineSystem.addToCart(myCart.getId(), beer.getId());

        List<Money> money = new ArrayList<>(List.of(new Fifty(), new Fifty()));
        List<Integer> changes = vendingMachineSystem.placeOrder(myCart.getId(), money);

        System.out.println("Order placed");
        if (changes != null && !changes.isEmpty()) {
            System.out.println("dispensing the changes");
            for (Integer change : changes) {
                System.out.print(change + " ");
            }
        }

        myCart = vendingMachineSystem.createEmptyCart();
        myCart = vendingMachineSystem.addToCart(myCart.getId(), cake.getId());

        money = new ArrayList<>(List.of(new Hundred()));
        changes = vendingMachineSystem.placeOrder(myCart.getId(), money);

        System.out.println("Order placed");
        if (changes != null && !changes.isEmpty()) {
            System.out.println("dispensing the changes");
            for (Integer change : changes) {
                System.out.print(change + " ");
            }
        }
        System.out.println("\n\n");
    }
}
