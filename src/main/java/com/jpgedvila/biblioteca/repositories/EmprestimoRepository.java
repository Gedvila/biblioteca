package com.jpgedvila.biblioteca.repositories;

import com.jpgedvila.biblioteca.dto.EmprestimoDTO;
import com.jpgedvila.biblioteca.entities.Emprestimo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

//    Page<EmprestimoDTO> findAtrasados(Pageable pageable);

    Page<Emprestimo> findAll(Pageable pageable);
}
