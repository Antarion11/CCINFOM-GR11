import java.time.LocalDateTime;

public class ReturnRequest {
    private int requestID;
    private int orderID;
    private int customerID;
    private int productID;
    private String returnReason;
    private LocalDateTime requestDate;
    private String status;
    public ReturnRequest(int requestID, int orderID, int customerID, int productID,
                         String returnReason, LocalDateTime requestDate, String status) {
        this.requestID = requestID;
        this.orderID = orderID;
        this.customerID = customerID;
        this.productID = productID;
        this.returnReason = returnReason;
        this.requestDate = requestDate;
        this.status = status;
    }

    // Getters
    public int getRequestID() { return requestID; }
    public int getOrderID() { return orderID; } // <-- ADDED
    public int getCustomerID() { return customerID; }
    public int getProductID() { return productID; }
    public String getReturnReason() { return returnReason; }
    public LocalDateTime getRequestDate() { return requestDate; }
    public String getStatus() { return status; }
}