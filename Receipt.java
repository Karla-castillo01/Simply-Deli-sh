public class Receipt {
    private final Order order;
    private final double totalCost;
    private final String dateTime;

    public Receipt(Order order, double totalCost) {
        this.order = order;
        this.totalCost = totalCost;
        this.dateTime = java.time.LocalDateTime.now().toString(); // Get current date/time
    }

    public Order getOrder() {
        return order;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public String getDateTime() {
        return dateTime;
    }
}