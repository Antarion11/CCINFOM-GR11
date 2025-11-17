// import database.DBConnection;
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
            System.out.println("4) Process Purchase");
            System.out.println("5) Create Return Request");
            System.out.println("6) View All Products");
            System.out.println("7) View All Customers");
            System.out.println("8) Add New Customer");
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
                    case 8 -> addCustomer(); // This will now work
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

    private static void addProduct() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("\n--- Add New Product ---");
            System.out.print("Item Code: "); String code = sc.nextLine();
            System.out.print("Product Name: "); String name = sc.nextLine();
            System.out.print("Brand: "); String brand = sc.nextLine();
            System.out.print("Grade: "); String grade = sc.nextLine();
            System.out.print("Category: "); String category = sc.nextLine();
            System.out.print("Price: "); double price = Double.parseDouble(sc.nextLine());
            System.out.print("Quantity: "); int quantity = Integer.parseInt(sc.nextLine());
            System.out.print("Low Stock Threshold: "); int threshold = Integer.parseInt(sc.nextLine());

            String sql = "INSERT INTO Product (ItemCode, ProductName, Brand, Grade, Category, Price, AvailableQuantity, LowStockThreshold) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, code);
                stmt.setString(2, name);
                stmt.setString(3, brand);
                stmt.setString(4, grade);
                stmt.setString(5, category);
                stmt.setDouble(6, price);
                stmt.setInt(7, quantity);
                stmt.setInt(8, threshold);

                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("Product added successfully!");
                }
            }
        } catch (Exception e) {
            System.out.println("Error adding product: " + e.getMessage());
        }
    }

    private static void searchProducts() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("\n--- Search Products ---");
            System.out.print("Enter search keyword: "); String keyword = sc.nextLine();

            String sql = "SELECT * FROM Product WHERE ProductName LIKE ? OR ItemCode LIKE ? OR Brand LIKE ?";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, "%" + keyword + "%");
                stmt.setString(2, "%" + keyword + "%");
                stmt.setString(3, "%" + keyword + "%");

                ResultSet rs = stmt.executeQuery();
                System.out.println("\nSearch Results:");
                System.out.println("ID | Code | Brand | Name | Grade | Price | Stock");
                System.out.println("------------------------------------------------");

                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.printf("%d | %s | %s | %s | %s | ₱%.2f | %d%n",
                            rs.getInt("ProductID"),
                            rs.getString("ItemCode"),
                            rs.getString("Brand"),
                            rs.getString("ProductName"),
                            rs.getString("Grade"),
                            rs.getDouble("Price"),
                            rs.getInt("AvailableQuantity"));
                }

                if (!found) {
                    System.out.println("No products found.");
                }
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

            String sql = "UPDATE Product SET AvailableQuantity = ? WHERE ProductID = ?";

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
            System.out.println("\n--- Process Purchase ---");
            System.out.print("Customer ID: "); int customerId = Integer.parseInt(sc.nextLine());
            System.out.print("Product ID: "); int productId = Integer.parseInt(sc.nextLine());
            System.out.print("Quantity: "); int quantity = Integer.parseInt(sc.nextLine());

            // First check if customer exists
            String checkCustomer = "SELECT CustomerID FROM Customer WHERE CustomerID = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkCustomer)) {
                checkStmt.setInt(1, customerId);
                ResultSet rs = checkStmt.executeQuery();
                if (!rs.next()) {
                    System.out.println("Customer not found! Please create customer first.");
                    return;
                }
            }

            // Get product price and check stock
            String productSql = "SELECT Price, AvailableQuantity FROM Product WHERE ProductID = ?";
            double price = 0;
            int currentStock = 0;

            try (PreparedStatement productStmt = conn.prepareStatement(productSql)) {
                productStmt.setInt(1, productId);
                ResultSet rs = productStmt.executeQuery();

                if (rs.next()) {
                    price = rs.getDouble("Price");
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

            // Calculate total and create sale
            double totalAmount = price * quantity;
            String salesSql = "INSERT INTO SalesTransaction (CustomerID, ProductID, Quantity, TotalAmount) VALUES (?, ?, ?, ?)";

            try (PreparedStatement salesStmt = conn.prepareStatement(salesSql, Statement.RETURN_GENERATED_KEYS)) {
                salesStmt.setInt(1, customerId);
                salesStmt.setInt(2, productId);
                salesStmt.setInt(3, quantity);
                salesStmt.setDouble(4, totalAmount);

                salesStmt.executeUpdate();

                // Get the generated transaction ID
                ResultSet keys = salesStmt.getGeneratedKeys();
                if (keys.next()) {
                    int transactionId = keys.getInt(1);

                    // Update product stock
                    String updateStockSql = "UPDATE Product SET AvailableQuantity = AvailableQuantity - ? WHERE ProductID = ?";
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateStockSql)) {
                        updateStmt.setInt(1, quantity);
                        updateStmt.setInt(2, productId);
                        updateStmt.executeUpdate();
                    }

                    System.out.printf("Purchase completed! Transaction ID: %d, Total: ₱%.2f%n", transactionId, totalAmount);
                }
            }
        } catch (Exception e) {
            System.out.println("Error processing purchase: " + e.getMessage());
        }
    }

    private static void createReturnRequest() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("\n--- Create Return Request ---");
            System.out.print("Customer ID: "); int customerId = Integer.parseInt(sc.nextLine());
            System.out.print("Product ID: "); int productId = Integer.parseInt(sc.nextLine());
            System.out.print("Sales Transaction ID: "); int transactionId = Integer.parseInt(sc.nextLine());
            System.out.print("Return Reason: "); String reason = sc.nextLine();
            System.out.print("Reported Condition: "); String condition = sc.nextLine();

            String sql = "INSERT INTO ReturnRequest (CustomerID, ProductID, SalesTransactionID, ReturnReason, ReportedCondition) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, customerId);
                stmt.setInt(2, productId);
                stmt.setInt(3, transactionId);
                stmt.setString(4, reason);
                stmt.setString(5, condition);

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
            String sql = "SELECT * FROM Product ORDER BY ProductID";

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                System.out.println("\n--- All Products ---");
                System.out.println("ID | Code | Brand | Name | Grade | Category | Price | Stock | Low Stock Threshold");
                System.out.println("--------------------------------------------------------------------------------");

                while (rs.next()) {
                    System.out.printf("%d | %s | %s | %s | %s | %s | ₱%.2f | %d | %d%n",
                            rs.getInt("ProductID"),
                            rs.getString("ItemCode"),
                            rs.getString("Brand"),
                            rs.getString("ProductName"),
                            rs.getString("Grade"),
                            rs.getString("Category"),
                            rs.getDouble("Price"),
                            rs.getInt("AvailableQuantity"),
                            rs.getInt("LowStockThreshold"));
                }
            }
        } catch (Exception e) {
            System.out.println("Error viewing products: " + e.getMessage());
        }
    }

    private static void viewAllCustomers() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM Customer ORDER BY CustomerID";

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                System.out.println("\n--- All Customers ---");
                System.out.println("ID | First Name | Last Name | Phone | Email | Date Registered");
                System.out.println("------------------------------------------------------------");

                while (rs.next()) {
                    System.out.printf("%d | %s | %s | %s | %s | %s%n",
                            rs.getInt("CustomerID"),
                            rs.getString("FirstName"),
                            rs.getString("LastName"),
                            rs.getString("PhoneNumber"),
                            rs.getString("Email"),
                            rs.getTimestamp("DateRegistered"));
                }
            }
        } catch (Exception e) {
            System.out.println("Error viewing customers: " + e.getMessage());
        }
    }

    // --- NEW METHOD ADDED HERE ---
    private static void addCustomer() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("\n--- Add New Customer ---");
            System.out.print("First Name: "); String firstName = sc.nextLine();
            System.out.print("Last Name: "); String lastName = sc.nextLine();
            System.out.print("Phone Number: "); String phone = sc.nextLine();
            System.out.print("Email: "); String email = sc.nextLine();
            System.out.print("Address: "); String address = sc.nextLine();

            String sql = "INSERT INTO Customer (FirstName, LastName, PhoneNumber, Email, Address) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, firstName);
                stmt.setString(2, lastName);
                stmt.setString(3, phone);
                stmt.setString(4, email);
                stmt.setString(5, address);

                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    // Get the auto-generated customer ID
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