package ExericicioJPA.JPA.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String nome;
    private String email;
    private String setor;

    @OneToMany(mappedBy = "usuario" )
    List<Chamado> chamados = new ArrayList<>();


    public Usuario(){ //construtor exigido pelo JPA

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public List<Chamado> getChamados() {
        return chamados;
    }
}
