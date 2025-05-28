import java.util.ArrayList;
import java.util.List;

public class Sandwich {
    private String size;
    private Bread bread;
    private List<Topping> toppings;
    private boolean isToasted;
    private double price;

    public List<Topping> getToppings() {
        return toppings;
    }

    public boolean isToasted() {
        return isToasted;
    }

    public void setToasted(boolean toasted) {
        isToasted = toasted;
    }

    public String getSandwichSize() {
        return size;
    }

    public Bread getBreadType() {
        return bread;
    }

    public double getPrice() {
        return price;
    }

    public Sandwich(String size, Bread bread) {
        this.size = size;
        this.bread = bread;
        this.toppings = new ArrayList<>();
    }

    public void addTopping(Topping topping) {
        this.toppings.add(topping);
        this.price = calculatePrice();
    }

    private double calculatePrice() {
        double currentPrice = 0.00;
        currentPrice = switch (size) {
            case "4\"" -> 5.50;
            case "8\"" -> 7.00;
            case "12\"" -> 8.50;
            default -> 0.00;
        };
        if (toppings != null) {
            for (Topping topping : toppings) {
                currentPrice += topping.getPrice();
            }
        }
        return currentPrice;
    }

}
