package ExericicioJPA.JPA.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nome;


    @OneToMany(mappedBy = "categoria")
    private List<Chamado> chamados = new ArrayList<>();

    public Categoria(){

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

    public List<Chamado> getChamados() {
        return chamados;
    }
}
