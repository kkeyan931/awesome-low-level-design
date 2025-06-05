package atm__;

public class CashDispenser {
    private Integer availableAmount;

    public CashDispenser(Integer availableAmount) {
        this.availableAmount = availableAmount;
    }

    public void dispense(Integer amount) {
        if (availableAmount < amount) {
            System.out.println("Dispenser does not have enough money");
        }
        System.out.println("Dispensing... " + amount);
        availableAmount -= amount;
    }

    public void loadCash(Integer amount) {
        this.availableAmount += amount;
    }

    public Integer getAvailableAmount() {
        return availableAmount;
    }
}
