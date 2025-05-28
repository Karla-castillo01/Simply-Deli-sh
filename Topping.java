public class Topping {
    private final String name;
    private final String type;
    private final double price;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public Topping(String name, String type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " ("  + type + "): $" + price;
    }
}
