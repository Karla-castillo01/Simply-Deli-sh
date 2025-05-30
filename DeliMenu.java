import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeliMenu {
    private final List<MenuItem> breadOptions;
    private final List<MenuItem> meatToppings;
    private final List<MenuItem> cheeseToppings;
    private final List<MenuItem> regularToppings;
    private final List<MenuItem> sauces;
    private final List<MenuItem> drinks;
    private final List<MenuItem> chips;

    public DeliMenu() {
        breadOptions = new ArrayList<>();
        meatToppings = new ArrayList<>();
        cheeseToppings = new ArrayList<>();
        regularToppings = new ArrayList<>();
        sauces = new ArrayList<>();
        drinks = new ArrayList<>();
        chips = new ArrayList<>();
        loadMenu();
    }

    private void loadMenu() {
        // Bread
        breadOptions.add(new Bread("White"));
        breadOptions.add(new Bread("Wheat"));
        breadOptions.add(new Bread("Rye"));
        breadOptions.add(new Bread("Wrap"));

        // Meats
        meatToppings.add(new Meat("Steak"));
        meatToppings.add(new Meat("Ham"));
        meatToppings.add(new Meat("Salami"));
        meatToppings.add(new Meat("Roast Beef"));
        meatToppings.add(new Meat("Chicken"));
        meatToppings.add(new Meat("Bacon"));

        // Cheese
        cheeseToppings.add(new Cheese("American Cheese"));
        cheeseToppings.add(new Cheese("Provolone Cheese"));
        cheeseToppings.add(new Cheese("Cheddar Cheese"));
        cheeseToppings.add(new Cheese("Swiss Cheese"));

        // Regular Toppings
        regularToppings.add(new Topping("Lettuce", "Regular Topping", 0.00));
        regularToppings.add(new Topping("Peppers", "Regular Topping", 0.00));
        regularToppings.add(new Topping("Onions", "Regular Topping", 0.00));
        regularToppings.add(new Topping("Tomatoes", "Regular Topping", 0.00));
        regularToppings.add(new Topping("Jalapenos", "Regular Topping", 0.00));
        regularToppings.add(new Topping("Cucumbers", "Regular Topping", 0.00));
        regularToppings.add(new Topping("Pickles", "Regular Topping", 0.00));
        regularToppings.add(new Topping("Guacamole", "Regular Topping", 0.00));
        regularToppings.add(new Topping("Mushrooms", "Regular Topping", 0.00));

        // Sauces
        sauces.add(new Topping("Mayo", "Sauce", 0.00));
        sauces.add(new Topping("Mustard", "Sauce", 0.00));
        sauces.add(new Topping("Ketchup", "Sauce", 0.00));
        sauces.add(new Topping("Ranch", "Sauce", 0.00));
        sauces.add(new Topping("Thousand Islands", "Sauce", 0.00));
        sauces.add(new Topping("Vinaigrette", "Sauce", 0.00));
        sauces.add(new Topping("Au Jus", "Sauce", 0.00));
        sauces.add(new Topping("Sauce (Side)", "Sauce", 0.00));

        // Drinks
        drinks.add(new Drink("Coke (Small)", 2.00));
        drinks.add(new Drink("Coke (Medium)", 2.50));
        drinks.add(new Drink("Coke (Large)", 3.00));
        drinks.add(new Drink("Sprite (Small)", 2.00));
        drinks.add(new Drink("Sprite (Medium)", 2.50));
        drinks.add(new Drink("Sprite (Large)", 3.00));
        drinks.add(new Drink("Lemonade (Small)", 2.25));
        drinks.add(new Drink("Lemonade (Medium)", 2.75));
        drinks.add(new Drink("Lemonade (Large)", 3.25));

        // Chips
        chips.add(new Chip("Hot Cheetos", 1.50));
        chips.add(new Chip("Doritos", 1.50));
        chips.add(new Chip("Lays", 1.50));
        chips.add(new Chip("Takis", 1.50));
    }

    public List<MenuItem> getBreadOptions() {
        return Collections.unmodifiableList(breadOptions);
    }

    public List<MenuItem> getMeatToppings() {
        return Collections.unmodifiableList(meatToppings);
    }

    public List<MenuItem> getCheeseToppings() {
        return Collections.unmodifiableList(cheeseToppings);
    }

    public List<MenuItem> getRegularToppings() {
        return Collections.unmodifiableList(regularToppings);
    }

    public List<MenuItem> getSauces() {
        return Collections.unmodifiableList(sauces);
    }

    public List<MenuItem> getDrinks() {
        return Collections.unmodifiableList(drinks);
    }

    public List<MenuItem> getChips() {
        return Collections.unmodifiableList(chips);
    }
}