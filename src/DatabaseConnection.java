import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            String url = "jdbc:mysql://localhost:3306/login_app"; // Cambia esto por el nombre de tu BD
            String username = "root"; // Usuario de tu base de datos
            String password = "123pendejos"; // Contraseña de tu base de datos
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al conectar con la base de datos.");
        }
        return connection;
    }
}

