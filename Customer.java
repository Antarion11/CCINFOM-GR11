

public class Customer {
    private int customerID;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    public Customer(int customerID, String firstName, String lastName, String phone, String email) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public int getCustomerID() { return customerID; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getFullName() { return firstName + " " + lastName; }

    @Override
    public String toString() {
        return String.format("%d | %s %s | %s | %s", customerID, firstName, lastName, phone, email);
    }
}