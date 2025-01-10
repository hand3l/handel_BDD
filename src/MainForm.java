
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
            String query = "SELECT * FROM datos"; // Seleccionar todas las columnas de la tabla
            PreparedStatement stmt = conn.prepareStatement(query);

            ResultSet rs = stmt.executeQuery(); // Ejecuta la consulta
            ResultSetMetaData metaData = rs.getMetaData(); // Obtiene los metadatos de la consulta
            int columnCount = metaData.getColumnCount(); // Número de columnas en la tabla
            DefaultTableModel model = new DefaultTableModel();

            // Agregar los nombres de las columnas automáticamente
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnName(i)); // Obtiene el nombre de cada columna
            }

            // Añadir las filas a la tabla
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i); // Obtiene el valor de cada columna
                }
                model.addRow(row); // Agrega la fila al modelo
            }

            table.setModel(model); // Asigna el modelo a la tabla
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error cargando los datos.");
        }
    }

}
