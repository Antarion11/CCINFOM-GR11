package models;

import java.time.LocalDateTime;

public class ReturnRequest {
    private int requestID;
    private int customerID;
    private int productID;
    private int salesTransactionID;
    private String returnReason;
    private String reportedCondition;
    private LocalDateTime requestDate;
    private String status;

    public ReturnRequest(int requestID, int customerID, int productID, int salesTransactionID,
                         String returnReason, String reportedCondition, LocalDateTime requestDate, String status) {
        this.requestID = requestID;
        this.customerID = customerID;
        this.productID = productID;
        this.salesTransactionID = salesTransactionID;
        this.returnReason = returnReason;
        this.reportedCondition = reportedCondition;
        this.requestDate = requestDate;
        this.status = status;
    }

    public int getRequestID() { return requestID; }
}
