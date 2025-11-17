
public class Supplier {
    private int supplierID;
    private String companyName;
    private String contactPerson;
    private String phoneNumber;
    private String email;
    private String address;

    public Supplier(int supplierID, String companyName, String contactPerson, String phoneNumber, String email, String address) {
        this.supplierID = supplierID;
        this.companyName = companyName;
        this.contactPerson = contactPerson;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    // Getters
    public int getSupplierID() { return supplierID; }
    public String getCompanyName() { return companyName; }
    public String getContactPerson() { return contactPerson; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }

    // Setters (for record management)
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setEmail(String email) { this.email = email; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public String toString() {
        return String.format("Supplier [ID=%d, Name=%s]", supplierID, companyName);
    }
}