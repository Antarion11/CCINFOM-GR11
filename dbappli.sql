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

-- 2. Product Table
CREATE TABLE Product (
    ProductID INT AUTO_INCREMENT PRIMARY KEY,
    ProductName VARCHAR(100) NOT NULL,
    Manufacturer VARCHAR(100) NOT NULL,
    `Condition` VARCHAR(50) NOT NULL, -- New, Used, Refurbished
    AvailableQuantity INT DEFAULT 0,
    InventoryStatus VARCHAR(20) DEFAULT 'In Stock', -- In Stock, Low Stock, Out of Stock
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

-- 5. Return Request Table
CREATE TABLE ReturnRequest (
    RequestID INT AUTO_INCREMENT PRIMARY KEY,
    CustomerID INT NOT NULL,
    ProductID INT NOT NULL,
    SalesTransactionID INT NOT NULL, -- Link to original purchase
    ReturnReason TEXT NOT NULL,
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
