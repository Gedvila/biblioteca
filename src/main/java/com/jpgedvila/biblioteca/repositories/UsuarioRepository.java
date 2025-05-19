package com.jpgedvila.biblioteca.repositories;

import com.jpgedvila.biblioteca.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
