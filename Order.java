import java.time.LocalDateTime;

public class Order {
    private int orderID;
    private int customerID;
    private LocalDateTime orderDate;
    private String orderStatus;

    // Full constructor
    public Order(int orderID, int customerID, LocalDateTime orderDate, String orderStatus) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

    // Getters and Setters
    public int getOrderID() { return orderID; }
    public void setOrderID(int orderID) { this.orderID = orderID; }
    public int getCustomerID() { return customerID; }
    public void setCustomerID(int customerID) { this.customerID = customerID; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
    public String getOrderStatus() { return orderStatus; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }
}