package com.jpgedvila.biblioteca.repositories;

import com.jpgedvila.biblioteca.entities.Emprestimo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    @Query("SELECT obj FROM Emprestimo obj WHERE obj.dataDevolucao < CURRENT_DATE ")
    Page<Emprestimo> findAtrasados(Pageable pageable);

    Page<Emprestimo> findAll(Pageable pageable);
}
