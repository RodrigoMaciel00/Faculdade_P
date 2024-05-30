import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    public void adicionarUsuario(Usuario usuario) {
        String query = "INSERT INTO Usuario (nome, email, senha) VALUES (?, ?, ?)";
        try (Connection connection = ConexaoBancoDados.conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setString(3, usuario.getSenha());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Usuario obterUsuarioPorId(int usuarioId) {
        String query = "SELECT * FROM Usuario WHERE id = ?";
        try (Connection connection = ConexaoBancoDados.conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, usuarioId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");

                return new Usuario(id, nome, email, senha);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void atualizarUsuario(Usuario usuario) {
        String query = "UPDATE Usuario SET nome = ?, email = ?, senha = ? WHERE id = ?";
        try (Connection connection = ConexaoBancoDados.conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setString(3, usuario.getSenha());
            preparedStatement.setInt(4, usuario.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarUsuario(int usuarioId) {
        String query = "DELETE FROM Usuario WHERE id = ?";
        try (Connection connection = ConexaoBancoDados.conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, usuarioId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
