
import javax.swing.*;
        import java.awt.event.*;
        import java.sql.*;

public class LoginForm extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginForm() {
        // Configuración básica del formulario
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Etiquetas y campos de texto
        JLabel lblUsername = new JLabel("Usuario:");
        lblUsername.setBounds(20, 30, 80, 25);
        add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(100, 30, 150, 25);
        add(txtUsername);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(20, 70, 80, 25);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(100, 70, 150, 25);
        add(txtPassword);

        // Botón de iniciar sesión
        btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setBounds(100, 110, 150, 25);
        add(btnLogin);

        // Acción al presionar el botón
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticate(); // Llama al método para autenticar
            }
        });
    }

    // Método para autenticar al usuario
    private void authenticate() {
        String username = txtUsername.getText(); // Obtiene el texto ingresado
        String password = new String(txtPassword.getPassword());

        try (Connection conn = DatabaseConnection.getConnection()) { // Conecta a la base de datos
            String query = "SELECT * FROM usuarios WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery(); // Ejecuta la consulta
            if (rs.next()) { // Si encuentra un registro, inicia sesión
                JOptionPane.showMessageDialog(this, "¡Bienvenido, " + username + "!");
                new MainForm(username).setVisible(true); // Abre el formulario principal
                this.dispose(); // Cierra el formulario actual
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error en la conexión a la base de datos.");
        }
    }

    public static void main(String[] args) {
        // Inicia el formulario
        SwingUtilities.invokeLater(() -> new LoginForm().setVisible(true));
    }
}
