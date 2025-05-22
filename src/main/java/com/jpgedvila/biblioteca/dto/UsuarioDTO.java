package com.jpgedvila.biblioteca.dto;

import com.jpgedvila.biblioteca.entities.Aluno;
import com.jpgedvila.biblioteca.entities.Usuario;

import java.time.LocalDate;

public class UsuarioDTO {

    private Long id;
    private String nome;
    private String telefone;
    private LocalDate dataDeCadastro;
    private String email;

    public UsuarioDTO() {}

    public UsuarioDTO(Long id, String nome, String telefone, LocalDate dataDeCadastro, String email) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.dataDeCadastro = dataDeCadastro;
        this.email = email;
    }

    public UsuarioDTO(Usuario usuario) {
        id = usuario.getId();
        nome = usuario.getNome();
        telefone = usuario.getTelefone();
        dataDeCadastro = usuario.getDataDeCadastro();
        email = usuario.getEmail();
    }


    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public LocalDate getDataDeCadastro() {
        return dataDeCadastro;
    }

    public String getEmail() {
        return email;
    }
}
