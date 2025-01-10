
import javax.swing.*;
        import javax.swing.table.DefaultTableModel;
import java.awt.*;
        import java.sql.*;

public class MainForm extends JFrame {
    private JTable table;
    private JLabel lblWelcome;

    public MainForm(String username) {
        // Configuración básica del formulario
        setTitle("Panel Principal");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Mensaje de bienvenida
        lblWelcome = new JLabel("Bienvenido, " + username + "!");
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblWelcome, BorderLayout.NORTH);

        // Tabla para mostrar los datos
        table = new JTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Cargar los datos en la tabla
        loadTableData();
    }

    // Método para cargar los datos desde la base de datos al JTable
    private void loadTableData() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT titulo, descripcion FROM datos";
            PreparedStatement stmt = conn.prepareStatement(query);

            ResultSet rs = stmt.executeQuery(); // Ejecuta la consulta
            DefaultTableModel model = new DefaultTableModel(new String[]{"Título", "Descripción"}, 0);

            while (rs.next()) {
                // Añade cada fila de datos al modelo de la tabla
                model.addRow(new Object[]{rs.getString("titulo"), rs.getString("descripcion")});
            }

            table.setModel(model); // Asigna el modelo a la tabla
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error cargando los datos.");
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
