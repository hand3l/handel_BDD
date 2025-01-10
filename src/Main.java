public class Main {
    public static void main(String[] args) {
        if (DatabaseConnection.getConnection() != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        } else {
            System.out.println("Error al conectar con la base de datos.");
        }

        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginForm().setVisible(true); // Muestra el formulario de login
        });
    }
}


