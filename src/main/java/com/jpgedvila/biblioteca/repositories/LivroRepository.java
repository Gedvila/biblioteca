package com.jpgedvila.biblioteca.repositories;

import com.jpgedvila.biblioteca.entities.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT obj FROM Livro obj WHERE obj.genero = :genero")
    Page<Livro> findByGenero(@Param("genero") String genero, Pageable pageable);

    Page<Livro> findAll(Pageable pageable);
}
