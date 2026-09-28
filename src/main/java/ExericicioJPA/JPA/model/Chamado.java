package ExericicioJPA.JPA.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "chamado")


public class Chamado {


    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String titulo;
private String descricao;
@Enumerated(EnumType.STRING)
private StatusChamado status;
@Enumerated(EnumType.STRING)
private PrioridadeChamado prioridade;
private LocalDateTime dataAbertura;
private LocalDateTime dataFechamento;

@ManyToOne
  private  Usuario usuario;

@ManyToOne
 private Categoria categoria;

public Chamado(){ //construtor exigido pelo JPA
}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusChamado getStatus() {
        return status;
    }

    public void setStatus(StatusChamado status) {
        this.status = status;
    }

    public PrioridadeChamado getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(PrioridadeChamado prioridade) {
        this.prioridade = prioridade;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDateTime getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDateTime dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
