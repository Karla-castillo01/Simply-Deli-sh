public class Receipt {
    private Order order;
    private double totalCost;
    private String dateTime;

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