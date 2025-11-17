public class SupplierProduct {
    private int supplierID;
    private int productID;
    private String supplierProductCode;
    private double unitCost;

    public SupplierProduct(int supplierID, int productID, String supplierProductCode, double unitCost) {
        this.supplierID = supplierID;
        this.productID = productID;
        this.supplierProductCode = supplierProductCode;
        this.unitCost = unitCost;
    }

    // Getters and Setters
    public int getSupplierID() { return supplierID; }
    public void setSupplierID(int supplierID) { this.supplierID = supplierID; }
    public int getProductID() { return productID; }
    public void setProductID(int productID) { this.productID = productID; }
    public String getSupplierProductCode() { return supplierProductCode; }
    public void setSupplierProductCode(String supplierProductCode) { this.supplierProductCode = supplierProductCode; }
    public double getUnitCost() { return unitCost; }
    public void setUnitCost(double unitCost) { this.unitCost = unitCost; }
}