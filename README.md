# 🥪 Simply Deli-sh 🥪

This project is a Java command-line application built to simulate a deli ordering system. It serves as a practical demonstration of core programming concepts, providing a user-friendly way to interact with a simulated deli.
## ✨ Key Features

* **Custom Sandwich Creation:** Build your ideal sandwich by selecting bread, size, and various toppings.
* **Order Item Selection:** Easily add drinks and chips to your order.
* **Order Review:** View a summary of your selected items before finalizing.
* **Simple Checkout Process:** A straightforward method to complete your order.
* **Digital Receipt Generation:** Creates a text file containing the details of your order.
* **Command-Line Interface:** Interactive text-based experience for placing orders.
* **Exit Confirmation:** Prompts for confirmation before closing the application.

## 📸 Application Screenshots

### Brainstorming

* This diagram presents the Simply Deli-sh application's design. It outlines the classes , the data they store, and the actions they perform, providing a blueprint for the application.

### Main Menu

* This screen displays the initial options available to the user, such as starting a new order or exiting the application.*

### Building a Sandwich

* This screen illustrates the process of customizing a sandwich by choosing bread type, size, and adding desired toppings.*

### Adding Drinks and Chips

* This screen shows the options for adding beverages and snacks to the current order.*

### Order Summary and Checkout
 [Screenshot 2025-05-30 at 8.16.36 AM.png](../../Desktop/Screenshot%202025-05-30%20at%208.16.36%E2%80%AFAM.png)
* This screen presents a summary of all the items in the user's order, including individual prices and the total cost, before proceeding to checkout.*

### Final Receipt
 [Screenshot 2025-05-30 at 8.18.05 AM.png](../../Desktop/Screenshot%202025-05-30%20at%208.18.05%E2%80%AFAM.png)
* This screen displays the generated receipt, which includes a detailed list of ordered items and the final amount.*

### Exit Confirmation

* This screen shows a prompt asking the user to confirm their action before exiting the application.*


## 💡 Interesting Code Snippet: Encapsulation in the `Sandwich` Class

The `Sandwich` class effectively demonstrates **encapsulation** by managing its own data and behavior. The base price is set internally, and the total price is calculated based on the sandwich's attributes:

```java
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
        setBasePrice(); // Base price is determined internally upon creation
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

    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    public double getPrice() {
        double totalPrice = basePrice;
        for (Topping topping : toppings) {
            totalPrice += topping.getPrice(); // Price is calculated based on toppings
        }
        return totalPrice;
    }
}
```
**Why This Code Snippet Highlights Good OOP:**

**OOP Highlight: Keeping Sandwich Info Together**

**OOP Focus: Self-Managing Sandwich**

The `Sandwich` class demonstrates a core programming concept. It handles its own base price internally (`setBasePrice`) and calculates its total price based on its contents (including toppings added via `addTopping`). This design centralizes the sandwich's pricing logic within the Sandwich class, making the code easier to understand.
