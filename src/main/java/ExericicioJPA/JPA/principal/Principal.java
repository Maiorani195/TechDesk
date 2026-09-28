package ExericicioJPA.JPA.principal;

import ExericicioJPA.JPA.model.*;
import ExericicioJPA.JPA.repository.*;

import java.time.LocalDateTime;
import java.util.List;

public class Principal {

    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;
    private final ChamadoRepository chamadoRepository;

    private Usuario ana, bruno, carla, diego;
    private Categoria hardware, rede, software, acesso;

    public Principal(UsuarioRepository usuarioRepository,
                     CategoriaRepository categoriaRepository,
                     ChamadoRepository chamadoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
        this.chamadoRepository = chamadoRepository;
    }

    public void executar() {
        popularDados();
        exibirChamadosPorUsuario();
        exibirChamadosPorCategoria();
        exibirChamadosCriticosEmAberto();
        exibirRankingUsuarios();
    }

    private void popularDados() {
        // 1º: usuários e categorias (o save devolve a entidade já com id)
        ana = usuarioRepository.save(criarUsuario("Ana", "ana@empresa.com", "Financeiro"));
        bruno = usuarioRepository.save(criarUsuario("Bruno", "bruno@empresa.com", "RH"));
        carla = usuarioRepository.save(criarUsuario("Carla", "carla@empresa.com", "Comercial"));
        diego = usuarioRepository.save(criarUsuario("Diego", "diego@empresa.com", "TI"));

        hardware = categoriaRepository.save(criarCategoria("Hardware"));
        rede = categoriaRepository.save(criarCategoria("Rede"));
        software = categoriaRepository.save(criarCategoria("Software"));
        acesso = categoriaRepository.save(criarCategoria("Acesso"));


        salvarChamado("Monitor sem imagem", ana, hardware, StatusChamado.ABERTO, PrioridadeChamado.MEDIA);
        salvarChamado("Wi-Fi caindo toda hora", ana, rede, StatusChamado.EM_ATENDIMENTO, PrioridadeChamado.ALTA);
        salvarChamado("Não consigo entrar no sistema", ana, acesso, StatusChamado.FECHADO, PrioridadeChamado.ALTA);
        salvarChamado("Servidor lento", ana, rede, StatusChamado.ABERTO, PrioridadeChamado.URGENTE);
        salvarChamado("Excel travando", bruno, software, StatusChamado.ABERTO, PrioridadeChamado.BAIXA);
        salvarChamado("VPN não conecta", bruno, rede, StatusChamado.ABERTO, PrioridadeChamado.URGENTE);
        salvarChamado("Acesso à pasta compartilhada", bruno, acesso, StatusChamado.EM_ATENDIMENTO, PrioridadeChamado.ALTA);
        salvarChamado("Teclado com teclas falhando", carla, hardware, StatusChamado.FECHADO, PrioridadeChamado.BAIXA);
        salvarChamado("Senha bloqueada", carla, acesso, StatusChamado.FECHADO, PrioridadeChamado.MEDIA);
        salvarChamado("Sem internet no setor", carla, rede, StatusChamado.ABERTO, PrioridadeChamado.URGENTE);
        salvarChamado("Instalar antivírus", diego, software, StatusChamado.EM_ATENDIMENTO, PrioridadeChamado.MEDIA);
        salvarChamado("Impressora não imprime", diego, hardware, StatusChamado.PENDENTE, PrioridadeChamado.ALTA);
    }

    private Usuario criarUsuario(String nome, String email, String setor) {
        Usuario u = new Usuario();
        u.setNome(nome);
        u.setEmail(email);
        u.setSetor(setor);
        return u;
    }

    private Categoria criarCategoria(String nome) {
        Categoria c = new Categoria();
        c.setNome(nome);
        return c;
    }

    private void salvarChamado(String titulo, Usuario usuario, Categoria categoria,
                               StatusChamado status, PrioridadeChamado prioridade) {
        Chamado c = new Chamado();
        c.setTitulo(titulo);
        c.setDescricao("Descrição do chamado: " + titulo);
        c.setUsuario(usuario);
        c.setCategoria(categoria);
        c.setStatus(status);
        c.setPrioridade(prioridade);
        c.setDataAbertura(LocalDateTime.now().minusDays(3));
        if (status == StatusChamado.FECHADO) {
            c.setDataFechamento(LocalDateTime.now());
        }
        chamadoRepository.save(c);
    }

    private void exibirChamadosPorUsuario() {
        System.out.println("\n=== Chamados abertos da Ana ===");
        chamadoRepository.buscarPorUsuarioEStatus(ana.getId(), StatusChamado.ABERTO)
                .forEach(c -> System.out.println("- " + c.getTitulo()));
    }

    private void exibirChamadosPorCategoria() {
        System.out.println("\n=== Chamados de Rede ===");
        chamadoRepository.buscarPorCategoria(rede.getId())
                .forEach(c -> System.out.println("- " + c.getTitulo() + " [" + c.getStatus() + "]"));
    }

    private void exibirChamadosCriticosEmAberto() {
        System.out.println("\n=== Prioridade alta/urgente ainda não fechados ===");
        chamadoRepository.buscarCriticosNaoFechados(
                        List.of(PrioridadeChamado.ALTA, PrioridadeChamado.URGENTE),
                        StatusChamado.FECHADO)
                .forEach(c -> System.out.println("- " + c.getTitulo() + " [" + c.getPrioridade() + "]"));
    }

    private void exibirRankingUsuarios() {
        System.out.println("\n=== Ranking: quem mais abriu chamados ===");
        List<Object[]> ranking = chamadoRepository.rankingUsuarios();
        for (Object[] linha : ranking) {
            System.out.println("- " + linha[0] + ": " + linha[1] + " chamados");
        }
    }
}