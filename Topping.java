public class Topping extends MenuItem {
    public Topping(String name, String category, double price) {
        super(name, price, category);
    }

    @Override
    public String toString() {
        return getName();
    }
}