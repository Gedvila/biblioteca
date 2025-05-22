package com.jpgedvila.biblioteca.services;

import com.jpgedvila.biblioteca.dto.LivroDTO;
import com.jpgedvila.biblioteca.entities.Livro;
import com.jpgedvila.biblioteca.repositories.LivroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<LivroDTO> findAll(Pageable pageable) {
        Page<Livro> result = repository.findAll(pageable);
        return result.map(this::mapToLivroDTO);
    }

    @Transactional(readOnly = true)
    public Page<LivroDTO> findByGenero(String genero, Pageable pageable) {
        Page<Livro> livros = repository.findByGenero(genero, pageable);
        return livros.map(this::mapToLivroDTO);
    }

    public LivroDTO insertLivro(LivroDTO dto){

        Livro entity = new Livro();

        saveLivro(dto,entity);

        entity = repository.save(entity);

        return new LivroDTO(entity);

    }

    private void saveLivro(LivroDTO dto, Livro entity){
        entity.setTitulo(dto.getTitulo());
        entity.setAutor(dto.getAutor());
        entity.setEditora(dto.getEditora());
        entity.setAnoDePublicacao(dto.getAnoDePublicacao());
        entity.setGenero(dto.getGenero());
        entity.setDisponivel(dto.getDisponivel());
    }

    private LivroDTO mapToLivroDTO(Livro livro) {
        return new LivroDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getEditora(),
                livro.getAnoDePublicacao(),
                livro.getGenero(),
                livro.getDisponivel()
        );
    }
}
