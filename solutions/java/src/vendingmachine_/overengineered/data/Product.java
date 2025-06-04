package vendingmachine_.overengineered.data;

public class Product {
    private final String id;
    private final String name;
    private final ProductType type;

    public Product(String id, String name, ProductType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public ProductType getType() {
        return type;
    }

    public String getId() {
        return id;
    }
}
