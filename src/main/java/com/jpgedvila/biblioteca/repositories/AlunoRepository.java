package com.jpgedvila.biblioteca.repositories;

import com.jpgedvila.biblioteca.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno,Long> {
}
