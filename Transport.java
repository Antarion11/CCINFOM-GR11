public class Transport {
    private int transportID;
    private String contactPerson;
    private String phoneNumber;
    private String courierCompany;

    public Transport(int transportID, String contactPerson, String phoneNumber, String courierCompany) {
        this.transportID = transportID;
        this.contactPerson = contactPerson;
        this.phoneNumber = phoneNumber;
        this.courierCompany = courierCompany;
    }

    // Getters
    public int getTransportID() { return transportID; }
    public String getContactPerson() { return contactPerson; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getCourierCompany() { return courierCompany; }

    // Setters (for record management)
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setCourierCompany(String courierCompany) { this.courierCompany = courierCompany; }

    @Override
    public String toString() {
        return String.format("Transport [ID=%d, Courier=%s, Contact=%s]",
                transportID, courierCompany, contactPerson);
    }
}