
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {

    public Connection connectDB() {
        Connection conn = null;
        String usuario = "root"; // Substitua pelo seu usuário
        String senha = "@pedro05072000PE"; // Substitua pela sua senha

        try {
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/uc11?useSSL=false", usuario, senha);
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO: " + erro.getMessage());
        }

        return conn;
    }
}