import java.sql.*;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== ModelKit Store Return System ===");

        while (true) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1) Record Management");
            System.out.println("2) Transactions");
            System.out.println("3) Reports");
            System.out.println("0) Exit");
            System.out.print("Choose: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1 -> handleRecordManagement();
                    case 2 -> handleTransactions();
                    case 3 -> handleReports(); // Placeholder
                    case 0 -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static void handleRecordManagement() {
        while (true) {
            System.out.println("\n--- Record Management Menu ---");
            System.out.println("1) Product Management");
            System.out.println("2) Customer Management");
            System.out.println("3) Supplier Management");
            System.out.println("4) Courier Management");
            System.out.println("5) Transport Log Management");
            System.out.println("0) Back to Main Menu");
            System.out.print("Choose: ");
            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1 -> handleProductManagement();
                    case 2 -> handleCustomerManagement();
                    case 3 -> handleSupplierManagement();
                    case 4 -> handleCourierManagement();
                    case 5 -> handleTransportLogManagement();
                    case 0 -> { return; }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void handleTransactions() {
        while (true) {
            System.out.println("\n--- Transactions Menu ---");
            System.out.println("1. Create Return Request");
            System.out.println("2. Process New Order");
            System.out.println("3. Order from Supplier");
            System.out.println("4. Transport Return to Supplier");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose: ");
            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1 -> createReturnRequest();
                    case 2 -> processPurchase();
                    case 3 -> orderFromSupplier();
                    case 4 -> initiateReturnTransport();
                    case 0 -> { return; }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void handleReports() {
        System.out.println("\n--- Reports Menu ---");
        System.out.println("All reports are not yet implemented.");
    }

    private static void handleProductManagement() {
        while (true) {
            System.out.println("\n--- Product Management ---");
            System.out.println("1. Add New Product");
            System.out.println("2. View All Products");
            System.out.println("3. Search Products");
            System.out.println("4. View Product Details (w/ Suppliers)");
            System.out.println("5. Update Product Information");
            System.out.println("6. Delete Product");
            System.out.println("7. Update Stock (Quick)");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1 -> addProduct();
                    case 2 -> viewAllProducts();
                    case 3 -> searchProducts();
                    case 4 -> viewProductDetails();
                    case 5 -> updateProduct();
                    case 6 -> deleteProduct();
                    case 7 -> updateStock();
                    case 0 -> { return; }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void handleCustomerManagement() {
        while (true) {
            System.out.println("\n--- Customer Management ---");
            System.out.println("1. Add New Customer");
            System.out.println("2. View All Customers");
            System.out.println("3. View Customer Details (w/ Orders)");
            System.out.println("4. Update Customer Information");
            System.out.println("5. Delete Customer");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1 -> addCustomer();
                    case 2 -> viewAllCustomers();
                    case 3 -> viewCustomerDetails();
                    case 4 -> updateCustomer();
                    case 5 -> deleteCustomer();
                    case 0 -> { return; }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void handleSupplierManagement() {
        while (true) {
            System.out.println("\n--- Supplier Management ---");
            System.out.println("1. Add New Supplier");
            System.out.println("2. View All Suppliers");
            System.out.println("3. View Supplier Details (w/ Products)");
            System.out.println("4. Update Supplier Information");
            System.out.println("5. Delete Supplier");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1 -> addSupplier();
                    case 2 -> viewAllSuppliers();
                    case 3 -> viewSupplierDetails();
                    case 4 -> updateSupplier();
                    case 5 -> deleteSupplier();
                    case 0 -> { return; }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Manages Transport Logs (the events)
    private static void handleTransportLogManagement() {
        while (true) {
            System.out.println("\n--- Transport Log Management ---");
            System.out.println("1. Add New Transport Log");
            System.out.println("2. View All Transport Logs");
            System.out.println("3. Update Transport Log Status");
            System.out.println("4. Delete Transport Log");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1 -> addTransportLog();
                    case 2 -> viewAllTransportLogs();
                    case 3 -> updateTransportLog();
                    case 4 -> deleteTransportLog();
                    case 0 -> { return; }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Manages Couriers (the companies)
    private static void handleCourierManagement() {
        while (true) {
            System.out.println("\n--- Courier Management ---");
            System.out.println("1. Add New Courier");
            System.out.println("2. View All Couriers");
            System.out.println("3. Update Courier Information");
            System.out.println("4. Delete Courier");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1 -> addCourier();
                    case 2 -> viewAllCouriers();
                    case 3 -> updateCourier();
                    case 4 -> deleteCourier();
                    case 0 -> { return; }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }


    private static void addCustomer() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("\n--- Add New Customer ---");
            System.out.print("First Name: "); String firstName = sc.nextLine();
            System.out.print("Last Name: "); String lastName = sc.nextLine();
            System.out.print("Phone Number: "); String phone = sc.nextLine();
            System.out.print("Email: "); String email = sc.nextLine();
            System.out.print("Address: "); String address = sc.nextLine();

            String sql = "INSERT INTO Customers (FirstName, LastName, Phone, Email, Address) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, firstName);
                stmt.setString(2, lastName);
                stmt.setString(3, phone);
                stmt.setString(4, email);
                stmt.setString(5, address);

                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    ResultSet keys = stmt.getGeneratedKeys();
                    if (keys.next()) {
                        System.out.println("Customer added! New Customer ID: " + keys.getInt(1));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error adding customer: " + e.getMessage());
        }
    }

    private static void viewAllCustomers() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM Customers ORDER BY CustomerID";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                System.out.println("\n--- All Customers ---");
                System.out.println("ID | First Name | Last Name | Phone | Email");
                System.out.println("------------------------------------------------------------");
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.printf("%d | %s | %s | %s | %s%n",
                            rs.getInt("CustomerID"),
                            rs.getString("FirstName"),
                            rs.getString("LastName"),
                            rs.getString("Phone"),
                            rs.getString("Email"));
                }
                if (!found) System.out.println("No customers found.");
            }
        } catch (Exception e) {
            System.out.println("Error viewing customers: " + e.getMessage());
        }
    }

    //Views a single customer and their related orders
    private static void viewCustomerDetails() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("\nEnter Customer ID: ");
            int customerId = Integer.parseInt(sc.nextLine());

            // 1. Get Customer Details
            String custSql = "SELECT * FROM Customers WHERE CustomerID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(custSql)) {
                stmt.setInt(1, customerId);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    System.out.println("\n--- Customer Details ---");
                    System.out.println("ID: " + rs.getInt("CustomerID"));
                    System.out.println("Name: " + rs.getString("FirstName") + " " + rs.getString("LastName"));
                    System.out.println("Phone: " + rs.getString("Phone"));
                    System.out.println("Email: " + rs.getString("Email"));
                    System.out.println("Address: " + rs.getString("Address"));
                } else {
                    System.out.println("Customer not found.");
                    return;
                }
            }

            // 2. Get Related Orders
            String orderSql = "SELECT * FROM Orders WHERE CustomerID = ? ORDER BY OrderDate DESC";
            try (PreparedStatement stmt = conn.prepareStatement(orderSql)) {
                stmt.setInt(1, customerId);
                ResultSet rs = stmt.executeQuery();

                System.out.println("\n--- Order History ---");
                boolean foundOrders = false;
                while(rs.next()) {
                    foundOrders = true;
                    System.out.printf("  Order ID: %d | Date: %s | Status: %s%n",
                            rs.getInt("OrderID"),
                            rs.getTimestamp("OrderDate").toLocalDateTime().toString(),
                            rs.getString("OrderStatus"));
                }
                if (!foundOrders) System.out.println("No orders found for this customer.");
            }

        } catch (Exception e) {
            System.out.println("Error viewing customer details: " + e.getMessage());
        }
    }

    //Updates a customer's information
    private static void updateCustomer() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("\nEnter Customer ID to update: ");
            int customerId = Integer.parseInt(sc.nextLine());

            // Get current details
            String selectSql = "SELECT * FROM Customers WHERE CustomerID = ?";
            String phone, email, address;
            try (PreparedStatement stmt = conn.prepareStatement(selectSql)) {
                stmt.setInt(1, customerId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    phone = rs.getString("Phone");
                    email = rs.getString("Email");
                    address = rs.getString("Address");
                    System.out.println("Updating customer: " + rs.getString("FirstName") + " " + rs.getString("LastName"));
                } else {
                    System.out.println("Customer not found.");
                    return;
                }
            }

            // Get new details (allow blank to keep old value)
            System.out.print("New Phone (" + phone + "): ");
            String newPhone = sc.nextLine();
            if (newPhone.isBlank()) newPhone = phone;

            System.out.print("New Email (" + email + "): ");
            String newEmail = sc.nextLine();
            if (newEmail.isBlank()) newEmail = email;

            System.out.print("New Address (" + address + "): ");
            String newAddress = sc.nextLine();
            if (newAddress.isBlank()) newAddress = address;

            // Update database
            String updateSql = "UPDATE Customers SET Phone = ?, Email = ?, Address = ? WHERE CustomerID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(updateSql)) {
                stmt.setString(1, newPhone);
                stmt.setString(2, newEmail);
                stmt.setString(3, newAddress);
                stmt.setInt(4, customerId);

                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("Customer updated successfully!");
                } else {
                    System.out.println("Failed to update customer.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error updating customer: " + e.getMessage());
        }
    }


    //Deletes a customer

    private static void deleteCustomer() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("\nEnter Customer ID to delete: ");
            int customerId = Integer.parseInt(sc.nextLine());

            System.out.print("WARNING: This may fail if the customer has existing orders.");
            System.out.print("Are you sure? (y/n): ");
            String confirm = sc.nextLine();

            if (!confirm.equalsIgnoreCase("y")) {
                System.out.println("Delete cancelled.");
                return;
            }

            String sql = "DELETE FROM Customers WHERE CustomerID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, customerId);
                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("Customer deleted successfully!");
                } else {
                    System.out.println("Customer not found.");
                }
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Error deleting customer: This customer cannot be deleted because they are associated with existing orders or return requests.");
        } catch (Exception e) {
            System.out.println("Error deleting customer: " + e.getMessage());
        }
    }


    private static void addSupplier() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("\n--- Add New Supplier ---");
            System.out.print("Company Name: "); String company = sc.nextLine();
            System.out.print("Contact Person: "); String contact = sc.nextLine();
            System.out.print("Phone Number: "); String phone = sc.nextLine();
            System.out.print("Email: "); String email = sc.nextLine();
            System.out.print("Address: "); String address = sc.nextLine();

            String sql = "INSERT INTO Suppliers (CompanyName, ContactPerson, Phone, Email, Address) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, company);
                stmt.setString(2, contact);
                stmt.setString(3, phone);
                stmt.setString(4, email);
                stmt.setString(5, address);

                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    ResultSet keys = stmt.getGeneratedKeys();
                    if (keys.next()) {
                        System.out.println("Supplier added! New Supplier ID: " + keys.getInt(1));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error adding supplier: " + e.getMessage());
        }
    }

    private static void viewAllSuppliers() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM Suppliers ORDER BY SupplierID";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                System.out.println("\n--- All Suppliers ---");
                System.out.println("ID | Company Name | Contact Person | Phone | Email");
                System.out.println("------------------------------------------------------------");
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.printf("%d | %s | %s | %s | %s%n",
                            rs.getInt("SupplierID"),
                            rs.getString("CompanyName"),
                            rs.getString("ContactPerson"),
                            rs.getString("Phone"),
                            rs.getString("Email"));
                }
                if (!found) System.out.println("No suppliers found.");
            }
        } catch (Exception e) {
            System.out.println("Error viewing suppliers: " + e.getMessage());
        }
    }

    //Views a single supplier and the products they provide

    private static void viewSupplierDetails() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("\nEnter Supplier ID: ");
            int supplierId = Integer.parseInt(sc.nextLine());

            // 1. Get Supplier Details
            String suppSql = "SELECT * FROM Suppliers WHERE SupplierID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(suppSql)) {
                stmt.setInt(1, supplierId);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    System.out.println("\n--- Supplier Details ---");
                    System.out.println("ID: " + rs.getInt("SupplierID"));
                    System.out.println("Company: " + rs.getString("CompanyName"));
                    System.out.println("Contact: " + rs.getString("ContactPerson"));
                    System.out.println("Phone: " + rs.getString("Phone"));
                    System.out.println("Email: " + rs.getString("Email"));
                } else {
                    System.out.println("Supplier not found.");
                    return;
                }
            }

            // 2. Get Related Products
            String prodSql = "SELECT p.ProductID, p.ProductName, p.Manufacturer, sp.SupplierProductCode, sp.UnitCost " +
                    "FROM Products p " +
                    "JOIN SupplierProducts sp ON p.ProductID = sp.ProductID " +
                    "WHERE sp.SupplierID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(prodSql)) {
                stmt.setInt(1, supplierId);
                ResultSet rs = stmt.executeQuery();

                System.out.println("\n--- Products Provided by this Supplier ---");
                boolean foundProds = false;
                while(rs.next()) {
                    foundProds = true;
                    System.out.printf("  ID: %d | Name: %s (%s) | Supplier Code: %s | Cost: %.2f%n",
                            rs.getInt("ProductID"),
                            rs.getString("ProductName"),
                            rs.getString("Manufacturer"),
                            rs.getString("SupplierProductCode"),
                            rs.getDouble("UnitCost"));
                }
                if (!foundProds) System.out.println("No products linked to this supplier.");
            }

        } catch (Exception e) {
            System.out.println("Error viewing supplier details: " + e.getMessage());
        }
    }


    //Updates a supplier's information

    private static void updateSupplier() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("\nEnter Supplier ID to update: ");
            int supplierId = Integer.parseInt(sc.nextLine());

            // Get current details
            String selectSql = "SELECT * FROM Suppliers WHERE SupplierID = ?";
            String contact, phone, email, address;
            try (PreparedStatement stmt = conn.prepareStatement(selectSql)) {
                stmt.setInt(1, supplierId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    contact = rs.getString("ContactPerson");
                    phone = rs.getString("Phone");
                    email = rs.getString("Email");
                    address = rs.getString("Address");
                    System.out.println("Updating supplier: " + rs.getString("CompanyName"));
                } else {
                    System.out.println("Supplier not found.");
                    return;
                }
            }

            // Get new details
            System.out.print("New Contact Person (" + contact + "): ");
            String newContact = sc.nextLine();
            if (newContact.isBlank()) newContact = contact;

            System.out.print("New Phone (" + phone + "): ");
            String newPhone = sc.nextLine();
            if (newPhone.isBlank()) newPhone = phone;

            System.out.print("New Email (" + email + "): ");
            String newEmail = sc.nextLine();
            if (newEmail.isBlank()) newEmail = email;

            System.out.print("New Address (" + address + "): ");
            String newAddress = sc.nextLine();
            if (newAddress.isBlank()) newAddress = address;

            // Update database
            String updateSql = "UPDATE Suppliers SET ContactPerson = ?, Phone = ?, Email = ?, Address = ? WHERE SupplierID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(updateSql)) {
                stmt.setString(1, newContact);
                stmt.setString(2, newPhone);
                stmt.setString(3, newEmail);
                stmt.setString(4, newAddress);
                stmt.setInt(5, supplierId);

                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("Supplier updated successfully!");
                } else {
                    System.out.println("Failed to update supplier.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error updating supplier: " + e.getMessage());
        }
    }


    //Deletes a supplier

    private static void deleteSupplier() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("\nEnter Supplier ID to delete: ");
            int supplierId = Integer.parseInt(sc.nextLine());

            System.out.print("WARNING: This may fail if the supplier is linked to products or logs.");
            System.out.print("Are you sure? (y/n): ");
            String confirm = sc.nextLine();

            if (!confirm.equalsIgnoreCase("y")) {
                System.out.println("Delete cancelled.");
                return;
            }

            String sql = "DELETE FROM Suppliers WHERE SupplierID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, supplierId);
                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("Supplier deleted successfully!");
                } else {
                    System.out.println("Supplier not found.");
                }
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Error deleting supplier: This supplier cannot be deleted because they are associated with existing products, stock logs, or transport logs.");
        } catch (Exception e) {
            System.out.println("Error deleting supplier: " + e.getMessage());
        }
    }

    private static void addProduct() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("\n--- Add New Product ---");
            System.out.print("Product Name: "); String name = sc.nextLine();
            System.out.print("Manufacturer: "); String manufacturer = sc.nextLine();
            System.out.print("Condition (e.g., New): "); String condition = sc.nextLine();
            System.out.print("Initial Quantity: "); int quantity = Integer.parseInt(sc.nextLine());
            System.out.print("Inventory Status (e.g., In Stock): "); String status = sc.nextLine();
            System.out.print("Supplier ID for this product: "); int supplierId = Integer.parseInt(sc.nextLine());
            System.out.print("Supplier's Product Code: "); String supplierCode = sc.nextLine();
            System.out.print("Unit Cost (from supplier): "); double unitCost = Double.parseDouble(sc.nextLine());

            conn.setAutoCommit(false); // Start transaction
            int newProductId = -1;

            // 1. Insert into Products table
            String productSql = "INSERT INTO Products (ProductName, Manufacturer, `Condition`, AvailableQuantity, InventoryStatus) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement productStmt = conn.prepareStatement(productSql, Statement.RETURN_GENERATED_KEYS)) {
                productStmt.setString(1, name);
                productStmt.setString(2, manufacturer);
                productStmt.setString(3, condition);
                productStmt.setInt(4, quantity);
                productStmt.setString(5, status);

                int rows = productStmt.executeUpdate();
                if (rows > 0) {
                    ResultSet keys = productStmt.getGeneratedKeys();
                    if (keys.next()) {
                        newProductId = keys.getInt(1);
                        System.out.println("Product base record created with ID: " + newProductId);
                    }
                }
            }

            if (newProductId == -1) {
                throw new SQLException("Failed to create product, rolling back.");
            }

            // 2. Link to SupplierProducts table
            String linkSql = "INSERT INTO SupplierProducts (SupplierID, ProductID, SupplierProductCode, UnitCost) VALUES (?, ?, ?, ?)";
            try (PreparedStatement linkStmt = conn.prepareStatement(linkSql)) {
                linkStmt.setInt(1, supplierId);
                linkStmt.setInt(2, newProductId);
                linkStmt.setString(3, supplierCode);
                linkStmt.setDouble(4, unitCost);
                linkStmt.executeUpdate();
            }

            conn.commit(); // Commit transaction
            System.out.println("Product added and linked to supplier successfully!");

        } catch (Exception e) {
            System.out.println("Error adding product: " + e.getMessage());
            // Note: In a real app, you'd call conn.rollback() in the catch block
        }
    }

    private static void viewAllProducts() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM Products ORDER BY ProductID";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                System.out.println("\n--- All Products ---");
                System.out.println("ID | Manufacturer | Name | Condition | Qty | Status");
                System.out.println("--------------------------------------------------------------------------------");
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.printf("%d | %s | %s | %s | %d | %s%n",
                            rs.getInt("ProductID"),
                            rs.getString("Manufacturer"),
                            rs.getString("ProductName"),
                            rs.getString("Condition"),
                            rs.getInt("AvailableQuantity"),
                            rs.getString("InventoryStatus"));
                }
                if (!found) System.out.println("No products found.");
            }
        } catch (Exception e) {
            System.out.println("Error viewing products: " + e.getMessage());
        }
    }

    private static void searchProducts() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("\n--- Search Products ---");
            System.out.print("Enter search keyword: "); String keyword = sc.nextLine();

            String sql = "SELECT * FROM Products WHERE ProductName LIKE ? OR Manufacturer LIKE ?";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, "%" + keyword + "%");
                stmt.setString(2, "%" + keyword + "%");

                ResultSet rs = stmt.executeQuery();
                System.out.println("\nSearch Results:");
                System.out.println("ID | Manufacturer | Name | Condition | Stock");
                System.out.println("------------------------------------------------");

                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.printf("%d | %s | %s | %s | %d%n",
                            rs.getInt("ProductID"),
                            rs.getString("Manufacturer"),
                            rs.getString("ProductName"),
                            rs.getString("Condition"),
                            rs.getInt("AvailableQuantity"));
                }
                if (!found) { System.out.println("No products found."); }
            }
        } catch (Exception e) {
            System.out.println("Error searching products: " + e.getMessage());
        }
    }

    // Views a single product and its related suppliers
    private static void viewProductDetails() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("\nEnter Product ID: ");
            int productId = Integer.parseInt(sc.nextLine());

            // 1. Get Product Details
            String prodSql = "SELECT * FROM Products WHERE ProductID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(prodSql)) {
                stmt.setInt(1, productId);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    System.out.println("\n--- Product Details ---");
                    System.out.println("ID: " + rs.getInt("ProductID"));
                    System.out.println("Name: " + rs.getString("ProductName"));
                    System.out.println("Manufacturer: " + rs.getString("Manufacturer"));
                    System.out.println("Condition: " + rs.getString("Condition"));
                    System.out.println("Quantity: " + rs.getInt("AvailableQuantity"));
                    System.out.println("Status: " + rs.getString("InventoryStatus"));
                } else {
                    System.out.println("Product not found.");
                    return;
                }
            }

            // 2. Get Related Suppliers
            String suppSql = "SELECT s.SupplierID, s.CompanyName, sp.SupplierProductCode, sp.UnitCost " +
                    "FROM Suppliers s " +
                    "JOIN SupplierProducts sp ON s.SupplierID = sp.SupplierID " +
                    "WHERE sp.ProductID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(suppSql)) {
                stmt.setInt(1, productId);
                ResultSet rs = stmt.executeQuery();

                System.out.println("\n--- Suppliers for this Product ---");
                boolean foundSups = false;
                while(rs.next()) {
                    foundSups = true;
                    System.out.printf("  ID: %d | Company: %s | Supplier Code: %s | Cost: %.2f%n",
                            rs.getInt("SupplierID"),
                            rs.getString("CompanyName"),
                            rs.getString("SupplierProductCode"),
                            rs.getDouble("UnitCost"));
                }
                if (!foundSups) System.out.println("No suppliers linked to this product.");
            }

        } catch (Exception e) {
            System.out.println("Error viewing product details: " + e.getMessage());
        }
    }


    //Updates a product's information

    private static void updateProduct() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("\nEnter Product ID to update: ");
            int productId = Integer.parseInt(sc.nextLine());

            // Get current details
            String selectSql = "SELECT * FROM Products WHERE ProductID = ?";
            String name, manufacturer, condition, status;
            int quantity;
            try (PreparedStatement stmt = conn.prepareStatement(selectSql)) {
                stmt.setInt(1, productId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    name = rs.getString("ProductName");
                    manufacturer = rs.getString("Manufacturer");
                    condition = rs.getString("Condition");
                    quantity = rs.getInt("AvailableQuantity");
                    status = rs.getString("InventoryStatus");
                    System.out.println("Updating product: " + name);
                } else {
                    System.out.println("Product not found.");
                    return;
                }
            }

            // Get new details
            System.out.print("New Name (" + name + "): ");
            String newName = sc.nextLine();
            if (newName.isBlank()) newName = name;

            System.out.print("New Manufacturer (" + manufacturer + "): ");
            String newMan = sc.nextLine();
            if (newMan.isBlank()) newMan = manufacturer;

            System.out.print("New Condition (" + condition + "): ");
            String newCond = sc.nextLine();
            if (newCond.isBlank()) newCond = condition;

            System.out.print("New Quantity (" + quantity + "): ");
            String newQtyStr = sc.nextLine();
            int newQty = newQtyStr.isBlank() ? quantity : Integer.parseInt(newQtyStr);

            System.out.print("New Status (" + status + "): ");
            String newStatus = sc.nextLine();
            if (newStatus.isBlank()) newStatus = status;

            // Update database
            String updateSql = "UPDATE Products SET ProductName = ?, Manufacturer = ?, `Condition` = ?, AvailableQuantity = ?, InventoryStatus = ? WHERE ProductID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(updateSql)) {
                stmt.setString(1, newName);
                stmt.setString(2, newMan);
                stmt.setString(3, newCond);
                stmt.setInt(4, newQty);
                stmt.setString(5, newStatus);
                stmt.setInt(6, productId);

                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("Product updated successfully!");
                } else {
                    System.out.println("Failed to update product.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error updating product: " + e.getMessage());
        }
    }


    //Deletes a product

    private static void deleteProduct() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("\nEnter Product ID to delete: ");
            int productId = Integer.parseInt(sc.nextLine());

            System.out.print("WARNING: This may fail if the product is in existing orders or logs.");
            System.out.print("Are you sure? (y/n): ");
            String confirm = sc.nextLine();

            if (!confirm.equalsIgnoreCase("y")) {
                System.out.println("Delete cancelled.");
                return;
            }

            String sql = "DELETE FROM Products WHERE ProductID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, productId);
                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("Product deleted successfully!");
                } else {
                    System.out.println("Product not found.");
                }
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Error deleting product: This product cannot be deleted because it is associated with existing orders, logs, or supplier links.");
        } catch (Exception e) {
            System.out.println("Error deleting product: " + e.getMessage());
        }
    }

    private static void updateStock() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("\n--- Update Stock ---");
            System.out.print("Product ID: "); int productId = Integer.parseInt(sc.nextLine());
            System.out.print("New Quantity: "); int newQty = Integer.parseInt(sc.nextLine());

            String sql = "UPDATE Products SET AvailableQuantity = ? WHERE ProductID = ?";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, newQty);
                stmt.setInt(2, productId);
                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("Stock updated successfully!");
                } else {
                    System.out.println("Product not found!");
                }
            }
        } catch (Exception e) {
            System.out.println("Error updating stock: " + e.getMessage());
        }
    }

    //Adds a new transport log

    private static void addTransportLog() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("\n--- Add New Transport Log ---");
            System.out.print("Return Request ID: "); int reqId = Integer.parseInt(sc.nextLine());
            System.out.print("Transport ID (Courier): "); int transId = Integer.parseInt(sc.nextLine());
            System.out.print("Delivery Date (YYYY-MM-DD): "); String delDate = sc.nextLine();
            System.out.print("Status (e.g., Pending): "); String status = sc.nextLine();

            String sql = "INSERT INTO TransportLog (RequestID, TransportID, DeliveryDate, Status) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, reqId);
                stmt.setInt(2, transId);
                stmt.setDate(3, Date.valueOf(delDate));
                stmt.setString(4, status);

                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    ResultSet keys = stmt.getGeneratedKeys();
                    if (keys.next()) {
                        System.out.println("Transport log added! New Log ID: " + keys.getInt(1));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error adding transport log: " + e.getMessage());
        }
    }


    //Views all transport logs

    private static void viewAllTransportLogs() {
        String sql = "SELECT tl.LogID, tl.RequestID, t.CourierCompany, tl.DeliveryDate, tl.ArrivalDate, tl.Status " +
                "FROM TransportLog tl " +
                "JOIN Transport t ON tl.TransportID = t.TransportID " +
                "ORDER BY tl.LogID";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- All Transport Logs ---");
            System.out.println("Log ID | Req ID | Courier Company | Delivery Date | Arrival Date | Status");
            System.out.println("-------------------------------------------------------------------------");
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.printf("%d | %d | %s | %s | %s | %s%n",
                        rs.getInt("LogID"),
                        rs.getInt("RequestID"),
                        rs.getString("CourierCompany"),
                        rs.getDate("DeliveryDate"),
                        rs.getDate("ArrivalDate"), // Can be null
                        rs.getString("Status"));
            }
            if (!found) System.out.println("No transport logs found.");

        } catch (Exception e) {
            System.out.println("Error viewing transport logs: " + e.getMessage());
        }
    }


    //Updates a transport log
    private static void updateTransportLog() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("\nEnter Log ID to update: ");
            int logId = Integer.parseInt(sc.nextLine());

            // Get current details
            String selectSql = "SELECT * FROM TransportLog WHERE LogID = ?";
            String status;
            Date arrivalDate;
            try (PreparedStatement stmt = conn.prepareStatement(selectSql)) {
                stmt.setInt(1, logId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    status = rs.getString("Status");
                    arrivalDate = rs.getDate("ArrivalDate");
                    System.out.println("Updating Transport Log for Request ID: " + rs.getInt("RequestID"));
                } else {
                    System.out.println("Transport Log not found.");
                    return;
                }
            }

            // Get new details
            System.out.print("New Status (" + status + "): ");
            String newStatus = sc.nextLine();
            if (newStatus.isBlank()) newStatus = status;

            System.out.print("New Arrival Date (YYYY-MM-DD) (" + (arrivalDate != null ? arrivalDate : "null") + "): ");
            String newArrDateStr = sc.nextLine();
            Date newArrDate = arrivalDate;
            if (newArrDateStr.isBlank()) {
                // Keep original
            } else if (newArrDateStr.equalsIgnoreCase("null")) {
                newArrDate = null; // Allow setting to null
            } else {
                newArrDate = Date.valueOf(newArrDateStr);
            }

            // Update database
            String updateSql = "UPDATE TransportLog SET Status = ?, ArrivalDate = ? WHERE LogID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(updateSql)) {
                stmt.setString(1, newStatus);
                stmt.setDate(2, newArrDate);
                stmt.setInt(3, logId);

                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("Transport Log updated successfully!");
                } else {
                    System.out.println("Failed to update transport log.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error updating transport log: " + e.getMessage());
        }
    }

    //Deletes a transport log

    private static void deleteTransportLog() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("\nEnter Log ID to delete: ");
            int logId = Integer.parseInt(sc.nextLine());

            System.out.print("Are you sure? (y/n): ");
            String confirm = sc.nextLine();

            if (!confirm.equalsIgnoreCase("y")) {
                System.out.println("Delete cancelled.");
                return;
            }

            String sql = "DELETE FROM TransportLog WHERE LogID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, logId);
                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("Transport Log deleted successfully!");
                } else {
                    System.out.println("Transport Log not found.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error deleting transport log: " + e.getMessage());
        }
    }

    //Adds a new courier
    private static void addCourier() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("\n--- Add New Courier ---");
            System.out.print("Courier Company Name: "); String company = sc.nextLine();
            System.out.print("Contact Person: "); String contact = sc.nextLine();
            System.out.print("Phone Number: "); String phone = sc.nextLine();

            String sql = "INSERT INTO Transport (CourierCompany, ContactPerson, Phone) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, company);
                stmt.setString(2, contact);
                stmt.setString(3, phone);

                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    ResultSet keys = stmt.getGeneratedKeys();
                    if (keys.next()) {
                        System.out.println("Courier added! New Transport ID: " + keys.getInt(1));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error adding courier: " + e.getMessage());
        }
    }

    //Views all couriers
    private static void viewAllCouriers() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM Transport ORDER BY TransportID";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                System.out.println("\n--- All Couriers ---");
                System.out.println("ID | Courier Company | Contact Person | Phone");
                System.out.println("-----------------------------------------------------------------");
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.printf("%d | %s | %s | %s%n",
                            rs.getInt("TransportID"),
                            rs.getString("CourierCompany"),
                            rs.getString("ContactPerson"),
                            rs.getString("Phone"));
                }
                if (!found) System.out.println("No couriers found.");
            }
        } catch (Exception e) {
            System.out.println("Error viewing couriers: " + e.getMessage());
        }
    }

    //Updates courier info
    private static void updateCourier() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("\nEnter Transport ID (Courier) to update: ");
            int transId = Integer.parseInt(sc.nextLine());

            String selectSql = "SELECT * FROM Transport WHERE TransportID = ?";
            String contact, phone, company;
            try (PreparedStatement stmt = conn.prepareStatement(selectSql)) {
                stmt.setInt(1, transId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    company = rs.getString("CourierCompany");
                    contact = rs.getString("ContactPerson");
                    phone = rs.getString("Phone");
                    System.out.println("Updating courier: " + company);
                } else {
                    System.out.println("Courier not found.");
                    return;
                }
            }

            System.out.print("New Contact Person (" + contact + "): ");
            String newContact = sc.nextLine();
            if (newContact.isBlank()) newContact = contact;

            System.out.print("New Phone (" + phone + "): ");
            String newPhone = sc.nextLine();
            if (newPhone.isBlank()) newPhone = phone;

            // Note: CompanyName is not updatable here, but Contact and Phone are.

            String updateSql = "UPDATE Transport SET ContactPerson = ?, Phone = ? WHERE TransportID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(updateSql)) {
                stmt.setString(1, newContact);
                stmt.setString(2, newPhone);
                stmt.setInt(3, transId);

                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("Courier updated successfully!");
                } else {
                    System.out.println("Failed to update courier.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error updating courier: " + e.getMessage());
        }
    }

    //Deletes a courier
    private static void deleteCourier() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("\nEnter Transport ID (Courier) to delete: ");
            int transId = Integer.parseInt(sc.nextLine());

            System.out.print("WARNING: This may fail if the courier is used in existing logs.");
            System.out.print("Are you sure? (y/n): ");
            String confirm = sc.nextLine();

            if (!confirm.equalsIgnoreCase("y")) {
                System.out.println("Delete cancelled.");
                return;
            }

            String sql = "DELETE FROM Transport WHERE TransportID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, transId);
                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("Courier deleted successfully!");
                } else {
                    System.out.println("Courier not found.");
                }
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Error deleting courier: This courier cannot be deleted because they are associated with existing transport logs.");
        } catch (Exception e) {
            System.out.println("Error deleting courier: " + e.getMessage());
        }
    }

    private static void processPurchase() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("\n--- Process New Order ---");
            System.out.print("Customer ID: "); int customerId = Integer.parseInt(sc.nextLine());
            System.out.print("Product ID: "); int productId = Integer.parseInt(sc.nextLine());
            System.out.print("Quantity: "); int quantity = Integer.parseInt(sc.nextLine());

            // Check stock from 'Products' table
            String productSql = "SELECT AvailableQuantity FROM Products WHERE ProductID = ?";
            int currentStock = 0;
            try (PreparedStatement productStmt = conn.prepareStatement(productSql)) {
                productStmt.setInt(1, productId);
                ResultSet rs = productStmt.executeQuery();
                if (rs.next()) {
                    currentStock = rs.getInt("AvailableQuantity");
                    if (currentStock < quantity) {
                        System.out.println("Insufficient stock! Available: " + currentStock);
                        return;
                    }
                } else {
                    System.out.println("Product not found!");
                    return;
                }
            }

            conn.setAutoCommit(false); // Start transaction
            int newOrderId = -1;
            String orderSql = "INSERT INTO Orders (CustomerID, OrderStatus) VALUES (?, 'Completed')";
            try (PreparedStatement orderStmt = conn.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS)) {
                orderStmt.setInt(1, customerId);
                orderStmt.executeUpdate();

                ResultSet keys = orderStmt.getGeneratedKeys();
                if (keys.next()) {
                    newOrderId = keys.getInt(1);
                }
            }

            if (newOrderId == -1) {
                throw new SQLException("Failed to create order, rolling back.");
            }
            String itemSql = "INSERT INTO OrderItems (OrderID, ProductID, Quantity) VALUES (?, ?, ?)";
            try (PreparedStatement itemStmt = conn.prepareStatement(itemSql)) {
                itemStmt.setInt(1, newOrderId);
                itemStmt.setInt(2, productId);
                itemStmt.setInt(3, quantity);
                itemStmt.executeUpdate();
            }
            String updateStockSql = "UPDATE Products SET AvailableQuantity = AvailableQuantity - ? WHERE ProductID = ?";
            try (PreparedStatement updateStmt = conn.prepareStatement(updateStockSql)) {
                updateStmt.setInt(1, quantity);
                updateStmt.setInt(2, productId);
                updateStmt.executeUpdate();
            }

            conn.commit();
            System.out.printf("Order completed successfully! New Order ID: %d%n", newOrderId);

        } catch (Exception e) {
            System.out.println("Error processing purchase: " + e.getMessage());
        }
    }

    private static void createReturnRequest() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("\n--- Create Return Request ---");
            System.out.print("Customer ID: "); int customerId = Integer.parseInt(sc.nextLine());
            System.out.print("Product ID: "); int productId = Integer.parseInt(sc.nextLine());
            System.out.print("Original Order ID: "); int orderId = Integer.parseInt(sc.nextLine());
            System.out.print("Return Reason: "); String reason = sc.nextLine();

            String sql = "INSERT INTO ReturnRequests (OrderID, CustomerID, ProductID, ReturnReason, Status) VALUES (?, ?, ?, ?, 'Pending')";

            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, orderId);
                stmt.setInt(2, customerId);
                stmt.setInt(3, productId);
                stmt.setString(4, reason);
                stmt.executeUpdate();

                ResultSet keys = stmt.getGeneratedKeys();
                if (keys.next()) {
                    int requestId = keys.getInt(1);
                    System.out.println("Return request created successfully! Request ID: " + requestId);
                }
            }
        } catch (Exception e) {
            System.out.println("Error creating return request: " + e.getMessage());
        }
    }


    /**
     * Transaction 3 (Order from Supplier)
     *
     * 1. Records a new Stock Log
     * 2. Updates the product's quantity
     */
    private static void orderFromSupplier() {
        System.out.println("\n--- Order from Supplier ---");
        Connection conn = null;
        try {
            // Get inputs
            System.out.print("Enter Product ID to restock: ");
            int productId = Integer.parseInt(sc.nextLine());
            System.out.print("Enter Supplier ID: ");
            int supplierId = Integer.parseInt(sc.nextLine());
            System.out.print("Enter Quantity to add: ");
            int quantity = Integer.parseInt(sc.nextLine());
            System.out.print("Enter Transaction Type (new_stock / restock): ");
            String transType = sc.nextLine();

            // Start transaction
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            // 1. Record the stock log
            String logSql = "INSERT INTO StockLogs (SupplierID, ProductID, Quantity, TransactionType) VALUES (?, ?, ?, ?)";
            try (PreparedStatement logStmt = conn.prepareStatement(logSql)) {
                logStmt.setInt(1, supplierId);
                logStmt.setInt(2, productId);
                logStmt.setInt(3, quantity);
                logStmt.setString(4, transType);
                logStmt.executeUpdate();
            }
            System.out.println("Step 1/2: Stock log created.");

            // 2. Update the product's available quantity
            String updateSql = "UPDATE Products SET AvailableQuantity = AvailableQuantity + ? WHERE ProductID = ?";
            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                updateStmt.setInt(1, quantity);
                updateStmt.setInt(2, productId);
                int rows = updateStmt.executeUpdate();
                if (rows == 0) {
                    throw new SQLException("Product ID not found, rolling back.");
                }
            }
            System.out.println("Step 2/2: Product quantity updated.");

            // If both steps succeed, commit the transaction
            conn.commit();
            System.out.println("Order from supplier completed successfully!");

        } catch (Exception e) {
            System.out.println("Error ordering from supplier: " + e.getMessage());
            // If any error occurs, roll back the transaction
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Transaction rolled back.");
                } catch (SQLException ex) {
                    System.out.println("Error rolling back transaction: " + ex.getMessage());
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Reset to default
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        }
    }

    /**
     * Transaction 4 (Transport Return to Supplier)
     * 1. Reads and updates a Return Request status to 'Confirmed'
     * 2. Records a new Transport Log
     */
    private static void initiateReturnTransport() {
        System.out.println("\n--- Initiate Return Transport ---");
        Connection conn = null;
        try {
            // Get inputs
            System.out.print("Enter Return Request ID to process: ");
            int requestId = Integer.parseInt(sc.nextLine());

            // Start transaction
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            // 1. Read and Update the Return Request
            String selectSql = "SELECT Status FROM ReturnRequests WHERE RequestID = ?";
            String currentStatus = "";
            try (PreparedStatement stmt = conn.prepareStatement(selectSql)) {
                stmt.setInt(1, requestId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    currentStatus = rs.getString("Status");
                } else {
                    throw new SQLException("Return Request ID not found.");
                }
            }

            if (!currentStatus.equalsIgnoreCase("Pending")) {
                System.out.println("This return request is already being processed (Status: " + currentStatus + ").");
                conn.rollback();
                return;
            }

            // Now, get the rest of the info
            System.out.print("Enter Transport ID (Courier): ");
            int transportId = Integer.parseInt(sc.nextLine());
            System.out.print("Enter Delivery/Pickup Date (YYYY-MM-DD): ");
            String delDate = sc.nextLine();

            // Update the request status
            String updateSql = "UPDATE ReturnRequests SET Status = 'Confirmed' WHERE RequestID = ?";
            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                updateStmt.setInt(1, requestId);
                updateStmt.executeUpdate();
            }
            System.out.println("Step 1/2: Return Request status updated to 'Confirmed'.");

            // 2. Record the new transport log
            String logSql = "INSERT INTO TransportLog (RequestID, TransportID, DeliveryDate, Status) VALUES (?, ?, ?, 'Scheduled')";
            try (PreparedStatement logStmt = conn.prepareStatement(logSql, Statement.RETURN_GENERATED_KEYS)) {
                logStmt.setInt(1, requestId);
                logStmt.setInt(2, transportId);
                logStmt.setDate(3, Date.valueOf(delDate));
                logStmt.executeUpdate();

                ResultSet keys = logStmt.getGeneratedKeys();
                if (keys.next()) {
                    System.out.println("Step 2/2: Transport log created with ID: " + keys.getInt(1));
                }
            }

            // If both steps succeed, commit the transaction
            conn.commit();
            System.out.println("Return transport initiated successfully!");

        } catch (Exception e) {
            System.out.println("Error initiating return transport: " + e.getMessage());
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Transaction rolled back.");
                } catch (SQLException ex) {
                    System.out.println("Error rolling back transaction: " + ex.getMessage());
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Reset to default
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        }
    }
}