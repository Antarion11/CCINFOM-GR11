 1. Drop the database if it already exists (to start fresh)
DROP DATABASE IF EXISTS modelkit_returns;

-- 2. Create the new database
CREATE DATABASE modelkit_returns;

-- 3. Select the new database
USE modelkit_returns;

-- 4. Create Tables (3NF Design)

CREATE TABLE Customers (
    CustomerID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Phone VARCHAR(20),
    Email VARCHAR(100) UNIQUE,
    Address TEXT
);

CREATE TABLE Suppliers (
    SupplierID INT AUTO_INCREMENT PRIMARY KEY,
    CompanyName VARCHAR(100) NOT NULL,
    ContactPerson VARCHAR(100),
    Phone VARCHAR(20),
    Email VARCHAR(100),
    Address TEXT
);

CREATE TABLE Products (
    ProductID INT AUTO_INCREMENT PRIMARY KEY,
    ProductName VARCHAR(100) NOT NULL,
    Manufacturer VARCHAR(100),
    `Condition` VARCHAR(50),
    AvailableQuantity INT DEFAULT 0,
    InventoryStatus VARCHAR(50)
);

-- This new table links Suppliers and Products (Many-to-Many)
CREATE TABLE SupplierProducts (
    SupplierID INT,
    ProductID INT,
    SupplierProductCode VARCHAR(50), -- The supplier's own code for the product
    UnitCost DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (SupplierID, ProductID),
    FOREIGN KEY (SupplierID) REFERENCES Suppliers(SupplierID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

-- This new table replaces the old "Sales" table
CREATE TABLE Orders (
    OrderID INT AUTO_INCREMENT PRIMARY KEY,
    CustomerID INT,
    OrderDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    OrderStatus VARCHAR(50) DEFAULT 'Pending', -- e.g., 'Pending', 'Completed', 'Cancelled'
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

-- This new table holds the details for each order
CREATE TABLE OrderItems (
    OrderID INT,
    ProductID INT,
    Quantity INT NOT NULL,
    -- Price at the time of sale can be stored here
    -- We will get it from the Product table for now
    PRIMARY KEY (OrderID, ProductID),
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

CREATE TABLE StockLogs (
    StockLogID INT AUTO_INCREMENT PRIMARY KEY,
    SupplierID INT,
    ProductID INT,
    Quantity INT NOT NULL,
    TransactionType ENUM('new_stock', 'restock'),
    TransactionDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (SupplierID) REFERENCES Suppliers(SupplierID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

CREATE TABLE ReturnRequests (
    RequestID INT AUTO_INCREMENT PRIMARY KEY,
    OrderID INT,
    CustomerID INT,
    ProductID INT,
    ReturnReason TEXT,
    RequestDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    Status VARCHAR(50) DEFAULT 'Pending',
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

CREATE TABLE TransportLogs (
    TransportID INT AUTO_INCREMENT PRIMARY KEY,
    RequestID INT,
    SupplierID INT,
    ContactPerson VARCHAR(100), -- This should match your proposal
    DeliveryDate DATE,
    ArrivalDate DATE,
    Status VARCHAR(50) DEFAULT 'Pending',
    FOREIGN KEY (RequestID) REFERENCES ReturnRequests(RequestID),
    FOREIGN KEY (SupplierID) REFERENCES Suppliers(SupplierID)
);

-- 5. Insert Sample Data for the new structure
INSERT INTO Customers (FirstName, LastName, Phone, Email, Address) VALUES
('John', 'Doe', '123-4567', 'john@email.com', '123 Main St'),
('Jane', 'Smith', '987-6543', 'jane@email.com', '456 Oak Ave');

INSERT INTO Suppliers (CompanyName, ContactPerson, Phone, Email, Address) VALUES
('Bandai Co.', 'Mr. Tanaka', '111-2222', 'orders@bandai.com', 'Tokyo, Japan'),
('Kotobukiya Ltd.', 'Ms. Yamamoto', '333-4444', 'sales@kotobukiya.co.jp', 'Osaka, Japan');

INSERT INTO Products (ProductName, Manufacturer, `Condition`, AvailableQuantity, InventoryStatus) VALUES
('Gundam RX-78', 'Bandai', 'New', 10, 'In Stock'),
('EVA Unit-01', 'Kotobukiya', 'New', 3, 'Low Stock'),
('Zaku II', 'Bandai', 'New', 15, 'In Stock');

-- Link suppliers to the products they provide
INSERT INTO SupplierProducts (SupplierID, ProductID, SupplierProductCode, UnitCost) VALUES
(1, 1, 'B-RX78', 1800.00), -- Bandai supplies Gundam RX-78
(1, 3, 'B-ZAKU2', 1200.00), -- Bandai supplies Zaku II
(2, 2, 'K-EVA01', 2500.00); -- Kotobukiya supplies EVA Unit-01