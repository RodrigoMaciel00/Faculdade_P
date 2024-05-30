import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Inicializando alguns usuários
        Usuario usuario1 = new Usuario(1, "Rodrigo Pinheiro", "rodrigo@example.com", "senha123");
        Usuario usuario2 = new Usuario(2, "Fernanda Dias", "fernanda@example.com", "senha456");

        // Criando um serviço para gerenciar as entradas de gratidão
        ServicoEntradaGratidao servico = new ServicoEntradaGratidao();

        // Adicionando algumas entradas de gratidão
        servico.adicionarEntrada(new EntradaGratidao(1, "Sou grato pela minha família.", LocalDate.of(2024, 5, 1), usuario1));
        servico.adicionarEntrada(new EntradaGratidao(2, "Grato pelo trabalho que amo.", LocalDate.of(2024, 5, 2), usuario1));
        servico.adicionarEntrada(new EntradaGratidao(3, "Agradecido pela saúde e bem-estar.", LocalDate.of(2024, 5, 3), usuario2));

        // Listando as entradas de gratidão de um usuário
        System.out.println("Entradas de Gratidão do Usuário 1:");
        for (EntradaGratidao entrada : servico.listarEntradasPorUsuario(usuario1)) {
            System.out.println(entrada);
        }

        System.out.println("\nEntradas de Gratidão do Usuário 2:");
        for (EntradaGratidao entrada : servico.listarEntradasPorUsuario(usuario2)) {
            System.out.println(entrada);
        }
    }
}
