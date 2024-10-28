
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {

    public Connection connectDB() {
        Connection conn = null;

        try {
            // Conexão com o banco de dados, verifique o nome do banco, usuário e senha
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Projeto_Leilao"); // Adicione a senha se necessário
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO: " + erro.getMessage());
        }

        return conn;
    }
}