package com.jpgedvila.biblioteca.repositories;

import com.jpgedvila.biblioteca.entities.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
}
