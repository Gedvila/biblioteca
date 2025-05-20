package com.jpgedvila.biblioteca.dto;

import com.jpgedvila.biblioteca.entities.Livro;

public class LivroDTO {

    private Long id;
    private String titulo;
    private String autor;
    private String editora;
    private Integer anoDePublicacao;
    private String genero;
    private Boolean disponivel;

    public LivroDTO(){}

    public LivroDTO(Long id, String titulo, String autor, String editora, Integer anoDePublicacao, String genero, Boolean disponivel) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.anoDePublicacao = anoDePublicacao;
        this.genero = genero;
        this.disponivel = disponivel;
    }

    public LivroDTO(Livro livro) {
        id = livro.getId();
        titulo = livro.getTitulo();
        autor = livro.getAutor();
        editora = livro.getEditora();
        anoDePublicacao = livro.getAnoDePublicacao();
        genero = livro.getGenero();
        disponivel = livro.getDisponivel();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditora() {
        return editora;
    }

    public Integer getAnoDePublicacao() {
        return anoDePublicacao;
    }

    public String getGenero() {
        return genero;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }
}
