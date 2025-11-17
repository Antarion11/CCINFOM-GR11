import java.sql.*;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== ModelKit Store Return System ===");

        while (true) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1) Add Product");
            System.out.println("2) Search Products");
            System.out.println("3) Update Stock Quantity");
            System.out.println("4) Process Purchase (NEW Order)"); // Updated
            System.out.println("5) Create Return Request");
            System.out.println("6) View All Products");
            System.out.println("7) View All Customers");
            System.out.println("8) Add New Customer");
            System.out.println("9) Add New Supplier"); // <-- ADDED
            System.out.println("10) View All Suppliers"); // <-- ADDED
            System.out.println("0) Exit");
            System.out.print("Choose: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1 -> addProduct();
                    case 2 -> searchProducts();
                    case 3 -> updateStock();
                    case 4 -> processPurchase();
                    case 5 -> createReturnRequest();
                    case 6 -> viewAllProducts();
                    case 7 -> viewAllCustomers();
                    case 8 -> addCustomer();
                    case 9 -> addSupplier(); // <-- ADDED
                    case 10 -> viewAllSuppliers(); // <-- ADDED
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

                while (rs.next()) {
                    System.out.printf("%d | %s | %s | %s | %s%n",
                            rs.getInt("SupplierID"),
                            rs.getString("CompanyName"),
                            rs.getString("ContactPerson"),
                            rs.getString("Phone"),
                            rs.getString("Email"));
                }
            }
        } catch (Exception e) {
            System.out.println("Error viewing suppliers: " + e.getMessage());
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

            conn.setAutoCommit(false);
            int newProductId = -1;

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

            String linkSql = "INSERT INTO SupplierProducts (SupplierID, ProductID, SupplierProductCode, UnitCost) VALUES (?, ?, ?, ?)";
            try (PreparedStatement linkStmt = conn.prepareStatement(linkSql)) {
                linkStmt.setInt(1, supplierId);
                linkStmt.setInt(2, newProductId);
                linkStmt.setString(3, supplierCode);
                linkStmt.setDouble(4, unitCost);
                linkStmt.executeUpdate();
            }

            conn.commit();
            System.out.println("Product added and linked to supplier successfully!");

        } catch (Exception e) {
            System.out.println("Error adding product: " + e.getMessage());
        }
    }

    private static void searchProducts() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("\n--- Search Products ---");
            System.out.print("Enter search keyword: "); String keyword = sc.nextLine();

            // Uses 'Products' table and new column names
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

    private static void updateStock() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("\n--- Update Stock ---");
            System.out.print("Product ID: "); int productId = Integer.parseInt(sc.nextLine());
            System.out.print("New Quantity: "); int newQty = Integer.parseInt(sc.nextLine());

            // Uses 'Products' table
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
            System.out.print("Original Order ID: "); int orderId = Integer.parseInt(sc.nextLine()); // <-- FIXED
            System.out.print("Return Reason: "); String reason = sc.nextLine();

            // Uses 'ReturnRequests' table
            String sql = "INSERT INTO ReturnRequests (OrderID, CustomerID, ProductID, ReturnReason, Status) VALUES (?, ?, ?, ?, 'Pending')";

            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, orderId); // <-- FIXED
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

    private static void viewAllProducts() {
        try (Connection conn = DBConnection.getConnection()) {
            // Uses 'Products' table and new column names
            String sql = "SELECT * FROM Products ORDER BY ProductID";

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                System.out.println("\n--- All Products ---");
                System.out.println("ID | Manufacturer | Name | Condition | Qty | Status");
                System.out.println("--------------------------------------------------------------------------------");

                while (rs.next()) {
                    System.out.printf("%d | %s | %s | %s | %d | %s%n",
                            rs.getInt("ProductID"),
                            rs.getString("Manufacturer"),
                            rs.getString("ProductName"),
                            rs.getString("Condition"),
                            rs.getInt("AvailableQuantity"),
                            rs.getString("InventoryStatus"));
                }
            }
        } catch (Exception e) {
            System.out.println("Error viewing products: " + e.getMessage());
        }
    }

    private static void viewAllCustomers() {
        try (Connection conn = DBConnection.getConnection()) {
            // Uses 'Customers' table
            String sql = "SELECT * FROM Customers ORDER BY CustomerID";

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                System.out.println("\n--- All Customers ---");
                System.out.println("ID | First Name | Last Name | Phone | Email");
                System.out.println("------------------------------------------------------------");

                while (rs.next()) {
                    System.out.printf("%d | %s | %s | %s | %s%n",
                            rs.getInt("CustomerID"),
                            rs.getString("FirstName"),
                            rs.getString("LastName"),
                            rs.getString("Phone"),
                            rs.getString("Email"));
                }
            }
        } catch (Exception e) {
            System.out.println("Error viewing customers: " + e.getMessage());
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

            // Uses 'Customers' table
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
                        int customerId = keys.getInt(1);
                        System.out.println("Customer added successfully! New Customer ID: " + customerId);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error adding customer: " + e.getMessage());
        }
    }
}