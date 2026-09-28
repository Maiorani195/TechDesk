package ExericicioJPA.JPA.principal;

import ExericicioJPA.JPA.model.Categoria;
import ExericicioJPA.JPA.model.Usuario;
import ExericicioJPA.JPA.repository.CategoriaRepository;
import ExericicioJPA.JPA.repository.ChamadoRepository;
import ExericicioJPA.JPA.repository.UsuarioRepository;

public class Principal {
    private UsuarioRepository usuarioRepository;
    private CategoriaRepository categoriaRepository;
    private ChamadoRepository chamadoRepository;

    private Usuario ana, bruno , carla , diego;
    private Categoria hardware , rede ,softwar , acesso;



    public Principal(UsuarioRepository usuarioRepository,
                     CategoriaRepository categoriaRepository,
                     ChamadoRepository chamadoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
        this.chamadoRepository = chamadoRepository;
    }

    public void  executar(){
        popularDados();
        exibirChamadosPorUsuario();
        exibirChamadosPorCategoria();
        exibirChamadosCriticosEmAberto();
        exibirRankingUsuarios();
    }

    private void exibirRankingUsuarios() {
    }

    private void exibirChamadosCriticosEmAberto() {
    }

    private void exibirChamadosPorCategoria() {
    }

    private void exibirChamadosPorUsuario() {
    }

    private void popularDados() {

    }

}