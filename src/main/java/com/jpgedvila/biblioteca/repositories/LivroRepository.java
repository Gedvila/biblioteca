package com.jpgedvila.biblioteca.repositories;

import com.jpgedvila.biblioteca.entities.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT obj FROM Livro obj WHERE obj.genero = :genero")
    Page<Livro> findByGenero(@Param("genero") String genero, Pageable pageable);

    @Query("SELECT obj FROM Livro obj WHERE obj.autor = :autor")
    Page<Livro> findByAutor(@Param("autor") String autor, Pageable pageable);

    Page<Livro> findAll(Pageable pageable);

    @Query("SELECT obj FROM Livro obj WHERE obj.disponivel = true")
    Page<Livro> findDisponivel(Pageable pageable);
}
