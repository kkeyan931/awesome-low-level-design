package vendingmachine_.overengineered.data.money;

public abstract class Money {
    private final Integer value;
    private final MoneyType type;

    protected Money(Integer value, MoneyType type) {
        this.value = value;
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public MoneyType getType() {
        return type;
    }
}
