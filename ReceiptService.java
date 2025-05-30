import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptService {

    private final String receiptsFolder;

    public ReceiptService(String receiptsFolder) {
        this.receiptsFolder = receiptsFolder;
        createDirectoryIfNotExists();
    }

    private void createDirectoryIfNotExists() {
        File directory = new File(receiptsFolder);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Receipts directory created successfully.");
            } else {
                System.err.println("Failed to create receipts directory!");
            }
        }
    }

    public void saveReceipt(Receipt receipt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String fileName = "receipt_" + LocalDateTime.now().format(formatter) + ".txt";
        String filePath = receiptsFolder + fileName;

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("--- Simply Deli-sh Receipt ---");
            writer.println("Date/Time: " + receipt.getDateTime());
            writer.println("-----------------------------");
            writer.println("Order Details:");

            Order order = receipt.getOrder();
            for (Sandwich sandwich : order.getSandwiches()) {
                writer.printf("- %s (%s) - $%.2f%n", sandwich.getBreadType().getName(), sandwich.getSize(), sandwich.getPrice());
                if (!sandwich.getToppings().isEmpty()) {
                    writer.println("  Toppings:");
                    for (Topping topping : sandwich.getToppings()) {
                        writer.println("    - " + topping);
                    }
                }
                if (sandwich.isToasted()) {
                    writer.println("  Toasted");
                }
            }
            for (Drink drink : order.getDrinks()) {
                writer.printf("- %s: $%.2f%n", drink.getName(), drink.getPrice());
            }
            for (Chip chip : order.getChips()) {
                writer.printf("- %s: $%.2f%n", chip.getName(), chip.getPrice());
            }

            writer.println("-----------------------------");
            writer.printf("Total: $%.2f%n", receipt.getTotalCost());
            System.out.println("Receipt saved to: " + filePath);

        } catch (IOException e) {
            System.err.println("Error saving receipt: " + e.getMessage());
        }
    }
}
