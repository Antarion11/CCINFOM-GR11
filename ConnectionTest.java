// ConnectionTest.java
public class ConnectionTest {
    public static void main(String[] args) {
        try {
            java.sql.Connection conn = database.DBConnection.getConnection();
            System.out.println("✅ DATABASE CONNECTION SUCCESSFUL!");
            conn.close();
        } catch (Exception e) {
            System.out.println("❌ CONNECTION FAILED: " + e.getMessage());
            e.printStackTrace();
        }
    }
}