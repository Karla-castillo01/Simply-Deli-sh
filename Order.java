import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<Sandwich> sandwiches;
    private final List<Drink> drinks;
    private final List<Chip> chips;

    public Order() {
        this.sandwiches = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.chips = new ArrayList<>();
    }

    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public List<Chip> getChips() {
        return chips;
    }

    public void addSandwich(Sandwich sandwich) {
        this.sandwiches.add(sandwich);
    }

    public void addDrink(Drink drink) {
        this.drinks.add(drink);
    }

    public void addChip(Chip chip) {
        this.chips.add(chip);
    }

    public double calculateTotal() {
        double total = 0.0;
        for (Sandwich sandwich : sandwiches) {
            total += sandwich.getPrice();
        }
        for (Drink drink : drinks) {
            total += drink.getPrice();
        }
        for (Chip chip : chips) {
            total += chip.getPrice();
        }
        return total;
    }

    public void generateReceipt() {
        System.out.println("--- Order Receipt ---");
        for (Sandwich sandwich : sandwiches) {
            System.out.println(sandwich.getSize() + " " + sandwich.getBreadType().getName() + " Sandwich: $" + String.format("%.2f", sandwich.getPrice()));
        }
        for (Drink drink : drinks) {
            System.out.println(drink.getName() + " : $" + String.format("%.2f", drink.getPrice()));
        }
        for (Chip chip : chips) {
            System.out.println(chip.getName() + ": $" + String.format("%.2f", chip.getPrice()));
        }
        System.out.println("Total: $" + String.format("%.2f", calculateTotal()));
    }
}