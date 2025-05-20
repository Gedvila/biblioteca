package com.jpgedvila.biblioteca.services;

import com.jpgedvila.biblioteca.dto.LivroDTO;
import com.jpgedvila.biblioteca.entities.Livro;
import com.jpgedvila.biblioteca.repositories.LivroRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LivroService {

    private final LivroRepository repository;
    public LivroService(LivroRepository repository){
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public LivroDTO findById(Long id) {
        Livro livro = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Livro não encrontrado"));
        return new LivroDTO(livro);
    }

    @Transactional(readOnly = true)
    public Page<LivroDTO> findAll(org.springframework.data.domain.Pageable pageable) {
        Page<Livro> result = (Page<Livro>) repository.findAll();
        return result.map(x -> new LivroDTO(x));
    }
}
