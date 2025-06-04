package vendingmachine_;

import vendingmachine_.data.Product;
import vendingmachine_.data.money.Money;
import vendingmachine_.service.InventoryService;
import vendingmachine_.states.State;
import vendingmachine_.states.impl.DispenseState;
import vendingmachine_.states.impl.IdleState;
import vendingmachine_.states.impl.ReadyState;
import vendingmachine_.states.impl.ReturnChangesState;

public class VendingMachine {
    private Product selectedProduct;

    private Integer selectedQuantity;
    private Integer totalMoney;

    private final InventoryService inventoryService;

    private final IdleState idleState;
    private final ReadyState readyState;
    private final DispenseState dispenseState;
    private final ReturnChangesState returnChangesState;
    private State currentState;

    VendingMachine() {
        inventoryService = InventoryService.getInstance();
        idleState = new IdleState(this);
        readyState = new ReadyState(this);
        dispenseState = new DispenseState(this);
        returnChangesState = new ReturnChangesState(this);
        currentState = idleState;
        totalMoney = 0;
    }

    public void addInventory(Product product, Integer quantity) {
        inventoryService.addInventory(product, quantity);
    }

    public void reduceInventory(Product product, Integer quantity) {
        inventoryService.reduceInventory(product, quantity);
    }

    public void selectProduct(Product product, Integer quantity) {
        currentState.selectProduct(product, quantity);
        this.selectedProduct = product;
        this.selectedQuantity = quantity;
    }

    public void insertMoney(Money money) {
        currentState.insertMoney(money);
    }

    public void dispenseProduct() {
        currentState.dispenseProduct();
    }

    public void returnChanges() {
        currentState.returnChanges();
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public Integer getTotalMoney() {
        return totalMoney;
    }

    public void increaseTotal(Integer amount) {
        totalMoney += amount;
    }

    public void resetStates() {
        this.selectedProduct = null;
        this.selectedQuantity = null;
        this.totalMoney = 0;
    }

    public DispenseState getDispenseState() {
        return dispenseState;
    }

    public ReadyState getReadyState() {
        return readyState;
    }

    public IdleState getIdleState() {
        return idleState;
    }

    public State getCurrentState() {
        return currentState;
    }

    public Integer getSelectedQuantity() {
        return selectedQuantity;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public ReturnChangesState getReturnChangesState() {
        return returnChangesState;
    }

    public InventoryService getInventoryService() {
        return inventoryService;
    }
}
