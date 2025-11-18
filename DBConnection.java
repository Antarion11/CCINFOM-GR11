import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // These are your connection details
    private static final String URL = "jdbc:mysql://localhost:3306/modelkit_returns?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "071924";


    static {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            System.out.println("--- FATAL ERROR ---");
            System.out.println("MySQL JDBC Driver not found!");
            System.out.println("Make sure you are running Java with the -cp flag to include the .jar file.");
            System.out.println("Example (Windows): java -cp \".;mysql-connector-j-8.0.33.jar\" Main");
            System.out.println("---------------------");
            throw new RuntimeException("Failed to load JDBC driver", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}