# TechDesk: Sistema de Chamados de TI

<p>
<img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java" />
<img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot" />
<img src="https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="Spring Data JPA" />
<img src="https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white" alt="Hibernate" />
<img src="https://img.shields.io/badge/H2_Database-1021FF?style=for-the-badge" alt="H2 Database" />
<img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven" />
</p>

Mini projeto desenvolvido para praticar **Spring Data JPA**: mapeamento de entidades, relacionamentos e consultas com JPQL. Simula o núcleo de um sistema de helpdesk, em que usuários abrem chamados classificados por categoria, status e prioridade.

## Modelo de dados

```
Usuario (1) ────── (N) Chamado (N) ────── (1) Categoria
```

- **Usuario**: nome, email e setor
- **Categoria**: nome (ex.: Hardware, Rede, Software, Acesso)
- **Chamado**: título, descrição, status, prioridade, data de abertura e data de fechamento
- **StatusChamado** (enum): `ABERTO`, `EM_ATENDIMENTO`, `PENDENTE`, `FECHADO`
- **PrioridadeChamado** (enum): `BAIXA`, `MEDIA`, `ALTA`, `URGENTE`

Os enums são gravados como texto (`@Enumerated(EnumType.STRING)`), o que deixa o banco legível e evita quebrar dados se a ordem dos valores mudar.

## Consultas implementadas (JPQL)

Todas no `ChamadoRepository`:

| Consulta | O que faz |
|---|---|
| `buscarPorUsuarioEStatus` | Chamados de um usuário com determinado status |
| `buscarPorCategoria` | Chamados de uma categoria |
| `buscarCriticosNaoFechados` | Chamados de prioridade alta ou urgente que ainda não foram fechados |
| `rankingUsuarios` | Ranking de quem mais abriu chamados (`COUNT` + `GROUP BY`) |

Em JPQL a consulta é feita sobre as **classes Java**, não sobre as tabelas. Os relacionamentos são navegados direto (por exemplo, `c.usuario.id`), sem escrever `JOIN` manualmente.

## Estrutura do projeto

```
model/        entidades e enums
repository/   interfaces JPA e consultas
principal/    popula o banco com dados de teste e exibe os resultados
```

## Como executar

```bash
./mvnw spring-boot:run
```

Ao iniciar, a aplicação cria o banco em memória, insere 4 usuários, 4 categorias e 12 chamados de exemplo, e imprime o resultado de cada consulta no console.

## Decisões de projeto

- **Sem `cascade`:** `Usuario`, `Categoria` e `Chamado` têm ciclos de vida independentes. Usuários e categorias existem antes dos chamados, e apagar um usuário não deve apagar o histórico dele. Cada entidade é salva explicitamente, na ordem correta.
- **Banco H2 em memória:** escolhido pela simplicidade, já que o foco do projeto é o JPA e não a infraestrutura de banco.

## Aprendizados

- Diferença entre mapear relacionamentos (`@OneToMany`, `@ManyToOne`) e consultá-los com JPQL
- O lado com `mappedBy` não controla a chave estrangeira; quem controla é o lado `@ManyToOne`
- Por que agrupar por `id` e não só por `nome` em consultas com `GROUP BY`
- Uso de parâmetros nomeados (`@Param`) em vez de concatenar valores na query
