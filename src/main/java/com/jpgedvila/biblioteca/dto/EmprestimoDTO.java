package com.jpgedvila.biblioteca.dto;

import com.jpgedvila.biblioteca.entities.Emprestimo;

import java.time.LocalDate;


public class EmprestimoDTO {

    private Long id;
    private LocalDate dataRetirada;
    private LocalDate dataDevolucao;
    private Long idLivro;
    private Long idUsuario;
    private String status;

    public EmprestimoDTO() {
    }

    public EmprestimoDTO(Long id, LocalDate dataRetirada, LocalDate dataDevolucao, Long idLivro, Long idUsuario, String status) {
        this.id = id;
        this.dataRetirada = dataRetirada;
        this.dataDevolucao = dataDevolucao;
        this.idLivro = idLivro;
        this.idUsuario = idUsuario;
        this.status = status;
    }

    public EmprestimoDTO(Emprestimo emprestimo) {
        id = emprestimo.getId();
        dataRetirada = emprestimo.getDataRetirada();
        dataDevolucao = emprestimo.getDataDevolucao();
        idLivro = emprestimo.getLivro().getId();
        idUsuario = emprestimo.getUsuario().getId();
        status = emprestimo.getStatus();
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDataRetirada() {
        return dataRetirada;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public Long getIdLivro() {
        return idLivro;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public String getStatus() {
        return status;
    }
}
