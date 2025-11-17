import java.time.LocalDate;

public class TransportLog {
    private int transportID;
    private int requestID;
    private int supplierID;
    private String contactPerson;
    private LocalDate deliveryDate;
    private LocalDate arrivalDate;
    private String status;

    public TransportLog(int transportID, int requestID, int supplierID, String contactPerson,
                        LocalDate deliveryDate, LocalDate arrivalDate, String status) {
        this.transportID = transportID;
        this.requestID = requestID;
        this.supplierID = supplierID;
        this.contactPerson = contactPerson;
        this.deliveryDate = deliveryDate;
        this.arrivalDate = arrivalDate;
        this.status = status;
    }

    // Getters
    public int getTransportID() { return transportID; }
    public int getRequestID() { return requestID; }
    public int getSupplierID() { return supplierID; }
    public String getContactPerson() { return contactPerson; }
    public LocalDate getDeliveryDate() { return deliveryDate; }
    public LocalDate getArrivalDate() { return arrivalDate; }
    public String getStatus() { return status; }

    // Setters
    public void setRequestID(int requestID) { this.requestID = requestID; }
    public void setSupplierID(int supplierID) { this.supplierID = supplierID; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }
    public void setDeliveryDate(LocalDate deliveryDate) { this.deliveryDate = deliveryDate; }
    public void setArrivalDate(LocalDate arrivalDate) { this.arrivalDate = arrivalDate; }
    public void setStatus(String status) { this.status = status; }
}