import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConexaoBancoDados {
    // Configurações de conexão com o banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/DiarioGratidao";
    private static final String USUARIO = "seu_usuario";
    private static final String SENHA = "sua_senha";

    // Método para obter uma conexão com o banco de dados
    private static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    // Método para listar todos os usuários do banco de dados
    public static List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Usuario");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String senha = rs.getString("senha");

                Usuario usuario = new Usuario(id, nome, email, senha);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public static void main(String[] args) {
        // Testando a conexão e listando usuários
        List<Usuario> usuarios = listarUsuarios();
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }
}
