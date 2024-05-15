import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EntradaGratidaoDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/DiarioGratidao";
    private static final String USUARIO = "seu_usuario";
    private static final String SENHA = "sua_senha";

    public void adicionarEntrada(EntradaGratidao entrada) {
        String sql = "INSERT INTO EntradaGratidao (texto, data, usuario_id) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entrada.getTexto());
            stmt.setDate(2, java.sql.Date.valueOf(entrada.getData()));
            stmt.setInt(3, entrada.getUsuario().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<EntradaGratidao> listarEntradasPorUsuario(Usuario usuario) {
        List<EntradaGratidao> entradas = new ArrayList<>();

        String sql = "SELECT * FROM EntradaGratidao WHERE usuario_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuario.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String texto = rs.getString("texto");
                LocalDate data = rs.getDate("data").toLocalDate();

                EntradaGratidao entrada = new EntradaGratidao(id, texto, data, usuario);
                entradas.add(entrada);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entradas;
    }

    public EntradaGratidao getEntradaGratidaoById(int id) {
        EntradaGratidao entrada = null;
        String sql = "SELECT * FROM EntradaGratidao WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String texto = rs.getString("texto");
                LocalDate data = rs.getDate("data").toLocalDate();
                int usuarioId = rs.getInt("usuario_id");

                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario usuario = usuarioDAO.getUsuarioById(usuarioId);

                entrada = new EntradaGratidao(id, texto, data, usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entrada;
    }

    public void atualizarEntradaGratidao(EntradaGratidao entrada) {
        String sql = "UPDATE EntradaGratidao SET texto = ?, data = ?, usuario_id = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entrada.getTexto());
            stmt.setDate(2, java.sql.Date.valueOf(entrada.getData()));
            stmt.setInt(3, entrada.getUsuario().getId());
            stmt.setInt(4, entrada.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarEntradaGratidao(int id) {
        String sql = "DELETE FROM EntradaGratidao WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
