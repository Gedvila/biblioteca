package com.jpgedvila.biblioteca.dto;

import com.jpgedvila.biblioteca.entities.Aluno;
import com.jpgedvila.biblioteca.entities.Professor;
import com.jpgedvila.biblioteca.entities.Usuario;

import java.time.LocalDate;

public class UsuarioDTO {

    private Long id;
    private String nome;
    private String telefone;
    private LocalDate dataDeCadastro;
    private String email;
    private String tipoUsuario;

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

        if (usuario instanceof Aluno) {
            this.tipoUsuario = "ALUNO";
        } else if (usuario instanceof Professor) {
            this.tipoUsuario = "PROFESSOR";
        }
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

    public String getTipoUsuario() {
        return tipoUsuario;
    }
}
