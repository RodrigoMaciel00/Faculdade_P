import java.time.LocalDate;

public class EntradaGratidao {
    private int id;
    private String texto;
    private LocalDate data;
    private Usuario usuario;

    public EntradaGratidao(int id, String texto, LocalDate data, Usuario usuario) {
        this.id = id;
        this.texto = texto;
        this.data = data;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Entrada de Gratidão {" +
                "ID: " + id +
                ", Texto: '" + texto + '\'' +
                ", Data: " + data +
                ", Usuário: " + usuario.getNome() +
                '}';
    }
}
