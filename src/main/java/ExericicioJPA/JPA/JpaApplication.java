package ExericicioJPA.JPA;

import ExericicioJPA.JPA.model.Categoria;
import ExericicioJPA.JPA.model.Chamado;
import ExericicioJPA.JPA.model.Usuario;
import ExericicioJPA.JPA.principal.Principal;
import ExericicioJPA.JPA.repository.CategoriaRepository;
import ExericicioJPA.JPA.repository.ChamadoRepository;
import ExericicioJPA.JPA.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaApplication implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal(usuarioRepository, categoriaRepository, chamadoRepository);
        principal.executar();
    }
}