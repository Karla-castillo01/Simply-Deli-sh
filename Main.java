import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String RECEIPTS_FOLDER = "receipts/";
    private static final String LINE_SEPARATOR = "=".repeat(40);
    private static final String SUB_SEPARATOR = "-".repeat(30);
    private static final String DELI_NAME = "Simply Deli-sh";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DeliMenu menu = new DeliMenu();
        Order order = new Order();
        ReceiptService receiptSvc = new ReceiptService(RECEIPTS_FOLDER);

        homeMenu:
        while (true) {
            System.out.println("\n" + LINE_SEPARATOR);
            System.out.println("   \uD83E\uDD6A Welcome to " + DELI_NAME + "! \uD83E\uDD6A");
            System.out.println(LINE_SEPARATOR);
            System.out.println("1. \uD83D\uDED2 New Order");
            System.out.println("0. \uD83D\uDEAA Exit");
            System.out.print("Enter your choice: ");

            int homeChoice = getInt(scanner);

            switch (homeChoice) {
                case 1:
                    handleOrder(scanner, menu, order, receiptSvc);
                    order = new Order();
                    break;
                case 0:
                    System.out.print("Are you sure you want to exit? (yes/no): ");
                    String exitConfirmation = scanner.next().toLowerCase();
                    if (exitConfirmation.equals("yes")) {
                        System.out.println("\uD83D\uDC4B Thanks for stopping by " + DELI_NAME + "! Enjoy your day! \uD83D\uDC4B");
                        break homeMenu;
                    } else {
                        System.out.println("Continuing with your order.");
                    }
                    break;
                default:
                    System.out.println("\uD83E\uDD54 Invalid choice. Try again.");
            }
        }
        scanner.close();
    }

    private static void handleOrder(Scanner scanner, DeliMenu menu, Order order, ReceiptService receiptSvc) {
        boolean inOrder = true;
        while (inOrder) {
            System.out.println("\n" + SUB_SEPARATOR);
            System.out.println("\uD83D\uDECD Your " + DELI_NAME + " Order \uD83D\uDECD");
            System.out.println(SUB_SEPARATOR);
            System.out.println("1. \uD83E\uDD6A Add Sandwich");
            System.out.println("2. \uD83E\uDD64 Add Drink");
            System.out.println("3. \uD83E\uDD54 Add Chips");
            System.out.println("4. \uD83D\uDCB0 Checkout");
            System.out.println("0. \uD83D\uDEAB Cancel");
            System.out.print("Enter your choice: ");

            int orderChoice = getInt(scanner);

            switch (orderChoice) {
                case 1:
                    Sandwich sandwich = buildSandwich(scanner, menu);
                    if (sandwich != null) {
                        order.addSandwich(sandwich);
                        System.out.println("✅ Sandwich added!");
                    }
                    break;
                case 2:
                    addDrink(scanner, menu, order);
                    break;
                case 3:
                    addChips(scanner, menu, order);
                    break;
                case 4:
                    if (!order.getSandwiches().isEmpty() || !order.getDrinks().isEmpty() || !order.getChips().isEmpty()) {
                        checkout(scanner, order, receiptSvc);
                        inOrder = false;
                    } else {
                        System.out.println("\uD83E\uDD54 Your order is empty. Add items!");
                    }
                    break;
                case 0:
                    System.out.println("❌ Order cancelled.");
                    inOrder = false;
                    break;
                default:
                    System.out.println("\uD83E\uDD54 Invalid choice. Try again.");
            }
        }
    }

    private static Sandwich buildSandwich(Scanner scanner, DeliMenu menu) {
        System.out.println("\n" + "*".repeat(35));
        System.out.println("\uD83E\uDD6A Building Your Sandwich! 🛠️");
        System.out.println("*".repeat(35));

        // Bread
        System.out.println("\n \uD83E\uDD56 Choose your bread:");
        List<MenuItem> breadOpts = menu.getBreadOptions();
        displayItems(breadOpts);
        System.out.print("Enter the number for your bread choice (or 0 to cancel): ");
        int breadChoice = getValidChoice(scanner, 0, breadOpts.size());
        if (breadChoice == 0) return null;
        Bread bread = (Bread) breadOpts.get(breadChoice - 1); // Corrected casting

        // Size
        System.out.println("\n\uD83D\uDCCF Select your sandwich size:");
        System.out.println("1. 4\"");
        System.out.println("2. 8\"");
        System.out.println("3. 12\"");
        System.out.print("Enter the number for your sandwich size: ");
        int sizeChoice = getValidChoice(scanner, 1, 3);
        String size = switch (sizeChoice) {
            case 1 -> "4\"";
            case 2 -> "8\"";
            case 3 -> "12\"";
            default -> "4\"";
        };

        Sandwich currentSandwich = new Sandwich(size, bread);
        System.out.printf("\n \uD83E\uDD6A Your %s sandwich on %s bread costs: $%.2f%n",
                currentSandwich.getSize(), currentSandwich.getBreadType().getName(), currentSandwich.getPrice());

        // Toppings
        System.out.println("\n➕ Add some delicious toppings:");
        addToppings(scanner, menu.getMeatToppings(), "Meat \uD83E\uDD69", currentSandwich, true, size);
        addToppings(scanner, menu.getCheeseToppings(), "Cheese \uD83E\uDDC0", currentSandwich, true, size);
        addToppings(scanner, menu.getRegularToppings(), "Other toppings \uD83E\uDDC5 \uD83E\uDED1 \uD83E\uDD6C", currentSandwich, false, size);
        addToppings(scanner, menu.getSauces(), "Select sauces ", currentSandwich, false, size);

        // Toasting
        System.out.println("\n\uD83D\uDD25 Would you like your sandwich toasted?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.print("Enter your choice: ");
        int toastChoice = getValidChoice(scanner, 1, 2);
        currentSandwich.setToasted(toastChoice == 1);

        return currentSandwich;
    }

    private static void addToppings(Scanner scanner, List<MenuItem> toppings, String category, Sandwich sandwich, boolean allowExtra, String size) {
        System.out.println("\n--- " + category + " ---");
        boolean adding = true;
        while (adding) {
            displayToppings(toppings, size);
            System.out.print("Enter number for " + category.toLowerCase().replaceAll("[^a-zA-Z ]", "") + " (or 0 to finish): ");
            int choice = getValidChoice(scanner, 0, toppings.size());

            if (choice == 0) {
                adding = false;
            } else if (choice > 0 && choice <= toppings.size()) {
                MenuItem selectedItem = toppings.get(choice - 1);
                String toppingName = selectedItem.getName();
                String toppingCategory = selectedItem.getCategory();
                double toppingPrice = getToppingPrice(toppingName, size);
                sandwich.addTopping(new Topping(toppingName, toppingCategory, toppingPrice));
                System.out.println("✅ " + toppingName + " added!");

                if (allowExtra) {
                    System.out.print("Want extra " + toppingName + "? (yes/no): ");
                    String extraChoice = scanner.next().toLowerCase();
                    if (extraChoice.equals("yes")) {
                        double extraPrice = getExtraPrice(toppingName, size);
                        sandwich.addTopping(new Topping("Extra " + toppingName, "Extra", extraPrice));
                        System.out.println("  ✅ Extra " + toppingName + " added!");
                    }
                }
            } else {
                System.out.println("\uD83E\uDD54 Invalid choice. Try again.");
            }
        }
    }

    private static double getToppingPrice(String toppingName, String size) {
        String lowerName = toppingName.toLowerCase();
        switch (lowerName) {
            // Meats
            case "steak":
            case "ham":
            case "salami":
            case "roast beef":
            case "chicken":
            case "bacon":
                switch (size) {
                    case "4\"":
                        return 1.00;
                    case "8\"":
                        return 2.00;
                    case "12\"":
                        return 3.00;
                }
                break;
            // Cheeses
            case "american cheese":
            case "provolone cheese":
            case "cheddar cheese":
            case "swiss cheese":
                switch (size) {
                    case "4\"":
                        return 0.75;
                    case "8\"":
                        return 1.50;
                    case "12\"":
                        return 2.25;
                }
                break;
        }
        return 0.0;
    }

    private static double getExtraPrice(String toppingName, String size) {
        String lowerName = toppingName.toLowerCase();
        switch (lowerName) {
            // Meats
            case "steak":
            case "ham":
            case "salami":
            case "roast beef":
            case "chicken":
            case "bacon":
                switch (size) {
                    case "4\"":
                        return 0.50;
                    case "8\"":
                        return 1.00;
                    case "12\"":
                        return 1.50;
                }
                break;
            // Cheeses
            case "american cheese":
            case "provolone cheese":
            case "cheddar cheese":
            case "swiss cheese":
                switch (size) {
                    case "4\"":
                        return 0.30;
                    case "8\"":
                        return 0.60;
                    case "12\"":
                        return 0.90;
                }
                break;
        }
        return 0.0;
    }

    private static void addDrink(Scanner scanner, DeliMenu menu, Order order) {
        System.out.println("\n\uD83E\uDD64 Choose drink:");
        List<MenuItem> drinkOpts = menu.getDrinks();
        displayItems(drinkOpts);
        System.out.print("Enter drink number (or 0 to skip): ");
        int choice = getValidChoice(scanner, 0, drinkOpts.size());
        if (choice != 0) {
            MenuItem selectedDrink = drinkOpts.get(choice - 1);
            order.addDrink(new Drink(selectedDrink.getName(), selectedDrink.getPrice()));
            System.out.println("✅ " + selectedDrink.getName() + " added!");
        }
    }

    private static void addChips(Scanner scanner, DeliMenu menu, Order order) {
        System.out.println("\n\uD83E\uDD54 Grab chips:");
        List<MenuItem> chipOpts = menu.getChips();
        displayItems(chipOpts);
        System.out.print("Enter chip number (or 0 to skip): ");
        int choice = getValidChoice(scanner, 0, chipOpts.size());
        if (choice != 0) {
            MenuItem selectedChip = chipOpts.get(choice - 1);
            order.addChip(new Chip(selectedChip.getName(), selectedChip.getPrice()));
            System.out.println("✅ " + selectedChip.getName() + " added!");
        }
    }

    private static void checkout(Scanner scanner, Order order, ReceiptService receiptSvc) {
        System.out.println("\n" + LINE_SEPARATOR);
        System.out.println("\uD83D\uDCB0 Checkout - Review Order \uD83E\uDDFE");
        System.out.println(LINE_SEPARATOR);
        viewOrder(order);
        double total = calculateTotal(order);
        System.out.printf("Total: $%.2f%n", total);

        System.out.print("\nConfirm order? (yes/no): ");
        String confirm = scanner.next().toLowerCase();
        if (confirm.equals("yes")) {
            Receipt receipt = new Receipt(order, total);
            receiptSvc.saveReceipt(receipt);
            System.out.println("✅ Order confirmed! Receipt saved.");
        } else {
            System.out.println("❌ Checkout cancelled.");
        }
    }

    private static void viewOrder(Order order) {
        System.out.println("\n\uD83D\uDCDD Your Order:");
        if (order.getSandwiches().isEmpty() && order.getDrinks().isEmpty() && order.getChips().isEmpty()) {
            System.out.println("Your order is empty.");
            return;
        }

        if (!order.getSandwiches().isEmpty()) {
            System.out.println("\n \uD83E\uDD6A Sandwiches:");
            for (Sandwich sandwich : order.getSandwiches()) {
                System.out.printf("- %s (%s) - $%.2f%n", sandwich.getBreadType().getName(), sandwich.getSize(), sandwich.getPrice());
                if (!sandwich.getToppings().isEmpty()) {
                    System.out.println("  Toppings:");
                    for (Topping topping : sandwich.getToppings()) {
                        System.out.println("    - " + topping);
                    }
                }
                if (sandwich.isToasted()) {
                    System.out.println("  \uD83D\uDD25\uD83E\uDD56 Toasted");
                }
            }
            System.out.println("--------------------");
        }

        if (!order.getDrinks().isEmpty()) {
            System.out.println("\n \uD83E\uDD64 Drinks:");
            for (Drink drink : order.getDrinks()) {
                System.out.printf("- %s: $%.2f%n", drink.getName(), drink.getPrice());
            }
            System.out.println("--------------------");
        }

        if (!order.getChips().isEmpty()) {
            System.out.println("\n \uD83E\uDD54 Chips:");
            for (Chip chip : order.getChips()) {
                System.out.printf("- %s: $%.2f%n", chip.getName(), chip.getPrice());
            }
            System.out.println("--------------------");
        }
    }

    private static double calculateTotal(Order order) {
        double total = 0.0;
        for (Sandwich sandwich : order.getSandwiches()) {
            total += sandwich.getPrice();
        }
        for (Drink drink : order.getDrinks()) {
            total += drink.getPrice();
        }
        for (Chip chip : order.getChips()) {
            total += chip.getPrice();
        }
        return total;
    }

    private static int getInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("\uD83E\uDD54 Invalid input. Enter a number!:");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static int getValidChoice(Scanner scanner, int min, int max) {
        int choice;
        while (true) {
            choice = getInt(scanner);
            if (choice >= min && choice <= max) {
                return choice;
            } else {
                System.out.println("\uD83E\uDD54 Invalid input. Enter a number between " + min + " and " + max + ":");
            }
        }
    }

    private static void displayItems(List<? extends MenuItem> items) {
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("%-2d) %-20s", (i + 1), items.get(i).getName());
            if (!items.get(i).getCategory().equalsIgnoreCase("Bread")) {
                System.out.printf(" $%.2f", items.get(i).getPrice());
            }
            System.out.println();
        }
    }

    private static void displayToppings(List<? extends MenuItem> items, String sandwichSize) {
        for (int i = 0; i < items.size(); i++) {
            String itemName = items.get(i).getName();
            double price;
            if (items.get(i).getCategory().equalsIgnoreCase("Meat") || items.get(i).getCategory().equalsIgnoreCase("Cheese")) {
                price = Main.getToppingPrice(itemName, sandwichSize);
            } else {
                price = items.get(i).getPrice();
            }
            System.out.printf("%-2d) %-20s $%.2f%n", (i + 1), itemName, price);
        }
    }
}