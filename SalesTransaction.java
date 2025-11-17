

import java.time.LocalDateTime;

public class SalesTransaction {
    private int transactionID;
    private int customerID;
    private int productID;
    private int quantity;
    private double unitPrice;
    private LocalDateTime orderDate;

    public SalesTransaction(int transactionID, int customerID, int productID, int quantity, double unitPrice, LocalDateTime orderDate) {
        this.transactionID = transactionID;
        this.customerID = customerID;
        this.productID = productID;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.orderDate = orderDate;
    }

    public int getTransactionID() { return transactionID; }
}
