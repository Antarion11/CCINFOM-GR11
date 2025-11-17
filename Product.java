

public class Product {
    private int productID;
    private String itemCode;
    private String productName;
    private String brand;
    private String grade;
    private String category;
    private double price;
    private int availableQuantity;
    private int lowStockThreshold;

    public Product(int productID, String itemCode, String productName, String brand,
                   String grade, String category, double price, int availableQuantity, int lowStockThreshold) {
        this.productID = productID;
        this.itemCode = itemCode;
        this.productName = productName;
        this.brand = brand;
        this.grade = grade;
        this.category = category;
        this.price = price;
        this.availableQuantity = availableQuantity;
        this.lowStockThreshold = lowStockThreshold;
    }

    // Getters
    public int getProductID() { return productID; }
    public String getItemCode() { return itemCode; }
    public String getProductName() { return productName; }
    public String getBrand() { return brand; }
    public String getGrade() { return grade; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public int getAvailableQuantity() { return availableQuantity; }
    public int getLowStockThreshold() { return lowStockThreshold; }

    // Setters (for fields that might change)
    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setLowStockThreshold(int lowStockThreshold) {
        this.lowStockThreshold = lowStockThreshold;
    }

    @Override
    public String toString() {
        return String.format("%d | %s | %s | %s | %s | P%.2f | qty=%d",
                productID, itemCode, brand, productName, grade, price, availableQuantity);
    }
}       