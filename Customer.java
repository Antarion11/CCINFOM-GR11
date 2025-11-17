public class Customer {
    private int customerID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String address;

    public Customer(int customerID, String firstName, String lastName, String phoneNumber, String email, String address) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    // Getters
    public int getCustomerID() { return customerID; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }

    // Setters (for record management)
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setEmail(String email) { this.email = email; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public String toString() {
        return String.format("Customer [ID=%d, Name=%s %s, Email=%s]", customerID, firstName, lastName, email);
    }
}