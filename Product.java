public class Product {
    private int productID;
    private String productName;
    private String manufacturer;
    private String condition;
    private int availableQuantity;
    private String inventoryStatus;

    public Product(int productID, String productName, String manufacturer, String condition,
                   int availableQuantity, String inventoryStatus) {
        this.productID = productID;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.condition = condition;
        this.availableQuantity = availableQuantity;
        this.inventoryStatus = inventoryStatus;
    }

    // Getters
    public int getProductID() { return productID; }
    public String getProductName() { return productName; }
    public String getManufacturer() { return manufacturer; }
    public String getCondition() { return condition; }
    public int getAvailableQuantity() { return availableQuantity; }
    public String getInventoryStatus() { return inventoryStatus; }

    // Setters
    public void setProductName(String productName) { this.productName = productName; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }
    public void setCondition(String condition) { this.condition = condition; }
    public void setAvailableQuantity(int availableQuantity) { this.availableQuantity = availableQuantity; }
    public void setInventoryStatus(String inventoryStatus) { this.inventoryStatus = inventoryStatus; }

    @Override
    public String toString() {
        return String.format("%d | %s | %s | %s | qty=%d",
                productID, manufacturer, productName, condition, availableQuantity);
    }
}