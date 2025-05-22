package com.jpgedvila.biblioteca.repositories;

import com.jpgedvila.biblioteca.entities.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    Page<Livro> findByGenero(String genero, Pageable pageable);
}
