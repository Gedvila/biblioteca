package com.jpgedvila.biblioteca.repositories;

import com.jpgedvila.biblioteca.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor,Long> {
}
