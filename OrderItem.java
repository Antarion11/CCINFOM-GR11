public class OrderItem {
    private int orderID;
    private int productID;
    private int quantity;

    public OrderItem(int orderID, int productID, int quantity) {
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
    }

    // Getters and Setters
    public int getOrderID() { return orderID; }
    public void setOrderID(int orderID) { this.orderID = orderID; }
    public int getProductID() { return productID; }
    public void setProductID(int productID) { this.productID = productID; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}