import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServicoEntradaGratidao {
    private final List<EntradaGratidao> entradas;

    public ServicoEntradaGratidao() {
        this.entradas = new ArrayList<>();
    }

    public void adicionarEntrada(EntradaGratidao entrada) {
        entradas.add(entrada);
    }

    public void removerEntrada(int id) {
        entradas.removeIf(e -> e.getId() == id);
    }

    public EntradaGratidao buscarEntradaPorId(int id) {
        for (EntradaGratidao entrada : entradas) {
            if (entrada.getId() == id) {
                return entrada;
            }
        }
        return null;
    }

    public List<EntradaGratidao> listarTodasEntradas() {
        return new ArrayList<>(entradas);
    }

    public List<EntradaGratidao> listarEntradasPorUsuario(Usuario usuario) {
        return entradas.stream()
                .filter(e -> e.getUsuario().equals(usuario))
                .collect(Collectors.toList());
    }

    // Outros métodos de gerenciamento de entradas podem ser adicionados aqui
}
