import java.util.ArrayList;
import java.util.List;

public class Sandwich {
    private String size;
    private Bread bread;
    private List<Topping> toppings;
    private boolean toasted;
    private double basePrice;

    public Sandwich(String size, Bread bread) {
        this.size = size;
        this.bread = bread;
        this.toppings = new ArrayList<>();
        this.toasted = false;
        setBasePrice();
    }

    private void setBasePrice() {
        switch (size) {
            case "4\"":
                this.basePrice = 5.50;
                break;
            case "8\"":
                this.basePrice = 7.00;
                break;
            case "12\"":
                this.basePrice = 8.50;
                break;
            default:
                this.basePrice = 5.50;
                break;
        }
    }

    public String getSize() {
        return size;
    }

    public Bread getBreadType() {
        return bread;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public boolean isToasted() {
        return toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    public double getPrice() {
        double totalPrice = basePrice;
        for (Topping topping : toppings) {
            totalPrice += topping.getPrice();
        }
        return totalPrice;
    }
}