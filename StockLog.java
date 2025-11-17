import java.time.LocalDateTime;

public class StockLog {
    private int stockID;
    private int supplierID;
    private int productID;
    private int quantity;
    private String transactionType; // e.g., "new_stock" or "restock"
    private LocalDateTime transactionDate;

    public StockLog(int stockID, int supplierID, int productID, int quantity, String transactionType, LocalDateTime transactionDate) {
        this.stockID = stockID;
        this.supplierID = supplierID;
        this.productID = productID;
        this.quantity = quantity;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
    }

    // Getters
    public int getStockID() { return stockID; }
    public int getSupplierID() { return supplierID; }
    public int getProductID() { return productID; }
    public int getQuantity() { return quantity; }
    public String getTransactionType() { return transactionType; }
    public LocalDateTime getTransactionDate() { return transactionDate; }

    // Setters
    public void setSupplierID(int supplierID) { this.supplierID = supplierID; }
    public void setProductID(int productID) { this.productID = productID; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setTransactionType(String transactionType) { this.transactionType = transactionType; }
    public void setTransactionDate(LocalDateTime transactionDate) { this.transactionDate = transactionDate; }
}