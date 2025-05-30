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

### Brainstorming
![Untitled Diagram](https://github.com/user-attachments/assets/6ac98c9d-556c-424e-b7fa-5a29ff9714b1)
* This diagram presents the Simply Deli-sh application's design. It outlines the classes , the data they store, and the actions they perform, providing a blueprint for the application.

## 📸 Application Screenshots

### Main Menu
![Screenshot 2025-05-30 at 8 10 53 AM](https://github.com/user-attachments/assets/1a118e62-ec63-4f45-8c64-c13c20d7bbb9)
* This screen displays the initial options available to the user, such as starting a new order or exiting the application.

### Building a Sandwich
![Screenshot 2025-05-30 at 8 13 45 AM](https://github.com/user-attachments/assets/d25c2c27-6e88-4e16-952e-5fa6afbc8f3f)
![Screenshot 2025-05-30 at 8 13 57 AM](https://github.com/user-attachments/assets/a17ce88e-6a0a-4874-b72e-876cead44b7f)
* This screen demonstrates the process of selecting bread, size, and adding toppings to a sandwich.
### Adding Drinks and Chips
![Screenshot 2025-05-30 at 8 15 11 AM](https://github.com/user-attachments/assets/fa049681-d29b-4147-8d27-5d6f77256b7c)
* This screen shows the options for adding beverages and snacks to the current order.

### Order Summary and Checkout
![Screenshot 2025-05-30 at 8 16 36 AM](https://github.com/user-attachments/assets/c4cba852-7cbc-4867-b58d-231bc0952672)
* This screen presents a summary of all the items in the user's order, including individual prices and the total cost, before proceeding to checkout.

### Final Receipt
![Screenshot 2025-05-30 at 8 18 05 AM](https://github.com/user-attachments/assets/8b15676c-4aa7-46e1-8109-d66f49aa6ae6)
* This screen displays the generated receipt, which includes a detailed list of ordered items and the final amount.

### Exit Confirmation
![Screenshot 2025-05-30 at 8 17 15 AM](https://github.com/user-attachments/assets/5476661b-8b6e-4167-89ad-2a5ad358c3fe)
* This screen shows a prompt asking the user to confirm their action before exiting the application.


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
            totalPrice += topping.getPrice(); 
        }
        return totalPrice;
    }
}
```
**Why This Code Snippet Highlights Good OOP:**

The `Sandwich` class demonstrates a core programming concept. It handles its own base price internally (`setBasePrice`) and calculates its total price based on its contents (including toppings added via `addTopping`). This design centralizes the sandwich's pricing logic within the Sandwich class, making the code easier to understand.
