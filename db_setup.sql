    DROP DATABASE IF EXISTS modelkit_returns;

    CREATE DATABASE modelkit_returns;

    USE modelkit_returns;


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

    CREATE TABLE SupplierProducts (
        SupplierID INT,
        ProductID INT,
        SupplierProductCode VARCHAR(50),
        UnitCost DECIMAL(10, 2) NOT NULL,
        PRIMARY KEY (SupplierID, ProductID),
        FOREIGN KEY (SupplierID) REFERENCES Suppliers(SupplierID),
        FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
    );

    CREATE TABLE Orders (
        OrderID INT AUTO_INCREMENT PRIMARY KEY,
        CustomerID INT,
        OrderDate DATETIME DEFAULT CURRENT_TIMESTAMP,
        OrderStatus VARCHAR(50) DEFAULT 'Pending',
        FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
    );

    CREATE TABLE OrderItems (
        OrderID INT,
        ProductID INT,
        Quantity INT NOT NULL,
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

    CREATE TABLE Transport (
        TransportID INT AUTO_INCREMENT PRIMARY KEY,
        ContactPerson VARCHAR(100),
        Phone VARCHAR(20),
        CourierCompany VARCHAR(100) NOT NULL
    );

    CREATE TABLE TransportLog (
        LogID INT AUTO_INCREMENT PRIMARY KEY,
        TransportID INT,
        RequestID INT,
        DeliveryDate DATE,
        ArrivalDate DATE,
        Status VARCHAR(50) DEFAULT 'Pending',
        FOREIGN KEY (TransportID) REFERENCES Transport(TransportID),
        FOREIGN KEY (RequestID) REFERENCES ReturnRequests(RequestID)
    );


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

    INSERT INTO SupplierProducts (SupplierID, ProductID, SupplierProductCode, UnitCost) VALUES
    (1, 1, 'B-RX78', 1800.00),
    (1, 3, 'B-ZAKU2', 1200.00),
    (2, 2, 'K-EVA01', 2500.00);

    INSERT INTO Transport (CourierCompany, ContactPerson, Phone) VALUES
    ('LBC Express', 'Mr. Dela Cruz', '02-8858-5999'),
    ('J&T Express', 'Ms. Reyes', '02-8911-5282');