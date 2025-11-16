-- 1. Customer Table
CREATE TABLE Customer (
    CustomerID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    PhoneNumber VARCHAR(15),
    Email VARCHAR(100),
    Address TEXT,
    DateRegistered DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 2. Product Table (UPDATED to match Java classes)
CREATE TABLE Product (
    ProductID INT AUTO_INCREMENT PRIMARY KEY,
    ItemCode VARCHAR(50),
    ProductName VARCHAR(100) NOT NULL,
    Brand VARCHAR(100),
    Grade VARCHAR(50),
    Category VARCHAR(50),
    AvailableQuantity INT DEFAULT 0,
    LowStockThreshold INT DEFAULT 5,
    Price DECIMAL(10,2) NOT NULL
);

-- 3. Supplier Table
CREATE TABLE Supplier (
    SupplierID INT AUTO_INCREMENT PRIMARY KEY,
    CompanyName VARCHAR(100) NOT NULL,
    ContactPerson VARCHAR(100) NOT NULL,
    PhoneNumber VARCHAR(15) NOT NULL,
    Email VARCHAR(100),
    Address TEXT NOT NULL
);

-- 4. Sales Transaction Table
CREATE TABLE SalesTransaction (
    TransactionID INT AUTO_INCREMENT PRIMARY KEY,
    CustomerID INT NOT NULL,
    ProductID INT NOT NULL,
    Quantity INT NOT NULL CHECK (Quantity > 0),
    TotalAmount DECIMAL(10,2) NOT NULL,
    OrderDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    Status VARCHAR(20) DEFAULT 'Completed',
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID),
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
);

-- 5. Return Request Table (UPDATED to match Java classes)
CREATE TABLE ReturnRequest (
    RequestID INT AUTO_INCREMENT PRIMARY KEY,
    CustomerID INT NOT NULL,
    ProductID INT NOT NULL,
    SalesTransactionID INT NOT NULL, -- Link to original purchase
    ReturnReason TEXT NOT NULL,
    ReportedCondition VARCHAR(100),
    RequestDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    Status ENUM('Pending', 'Approved', 'Rejected', 'Processing', 'Completed') DEFAULT 'Pending',
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID),
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID),
    FOREIGN KEY (SalesTransactionID) REFERENCES SalesTransaction(TransactionID)
);

-- 6. Stock Log Table
CREATE TABLE StockLog (
    LogID INT AUTO_INCREMENT PRIMARY KEY,
    SupplierID INT NOT NULL,
    ProductID INT NOT NULL,
    Quantity INT NOT NULL CHECK (Quantity > 0),
    UnitCost DECIMAL(10,2) NOT NULL,
    TransactionType ENUM('New Stock', 'Restock') NOT NULL,
    TransactionDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (SupplierID) REFERENCES Supplier(SupplierID),
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
);

-- 7. Return Transport Table
CREATE TABLE ReturnTransport (
    TransportID INT AUTO_INCREMENT PRIMARY KEY,
    RequestID INT NOT NULL UNIQUE, -- One-to-one with ReturnRequest
    SupplierID INT NOT NULL,
    ContactPerson VARCHAR(100) NOT NULL,
    DeliveryDate DATE,
    ArrivalDate DATE,
    Status ENUM('Scheduled', 'In-Transit', 'Delivered', 'Confirmed') DEFAULT 'Scheduled',
    ShippingCost DECIMAL(10,2),
    FOREIGN KEY (RequestID) REFERENCES ReturnRequest(RequestID),
    FOREIGN KEY (SupplierID) REFERENCES Supplier(SupplierID)
);

-- Insert sample data for testing
INSERT INTO Customer (FirstName, LastName, PhoneNumber, Email, Address) VALUES
('John', 'Doe', '123-4567', 'john@email.com', '123 Main St'),
('Jane', 'Smith', '987-6543', 'jane@email.com', '456 Oak Ave');

INSERT INTO Product (ItemCode, ProductName, Brand, Grade, Category, Price, AvailableQuantity, LowStockThreshold) VALUES
('MK001', 'Gundam RX-78', 'Bandai', 'Master', 'Robot', 2500.00, 10, 5),
('MK002', 'EVA Unit-01', 'Kotobukiya', 'Premium', 'Robot', 3500.00, 3, 2),
('MK003', 'Zaku II', 'Bandai', 'Standard', 'Robot', 1800.00, 15, 5);

INSERT INTO Supplier (CompanyName, ContactPerson, PhoneNumber, Email, Address) VALUES
('Bandai Co.', 'Mr. Tanaka', '111-2222', 'orders@bandai.com', 'Tokyo, Japan'),
('Kotobukiya Ltd.', 'Ms. Yamamoto', '333-4444', 'sales@kotobukiya.co.jp', 'Osaka, Japan');