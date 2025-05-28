package com.jpgedvila.biblioteca.controllers;

import com.jpgedvila.biblioteca.dto.LivroDTO;
import com.jpgedvila.biblioteca.dto.UsuarioDTO;
import com.jpgedvila.biblioteca.entities.Livro;
import com.jpgedvila.biblioteca.services.LivroService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping(value = "/livro")
public class LivroController {

    private final LivroService livroService;
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<LivroDTO> findById(@PathVariable Long id) {
        LivroDTO livroDTO = livroService.findById(id);
        return ResponseEntity.ok().body(livroDTO);
    }


    @GetMapping
    public ResponseEntity<Page<LivroDTO>> findAll(Pageable pageable) {
        Page<LivroDTO> dto = livroService.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/disponivel")
    public ResponseEntity<Page<LivroDTO>> findDisponivel(Pageable pageable){
        Page<LivroDTO> dtos = livroService.findDisponivel(pageable);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/autor")
    public ResponseEntity<Page<LivroDTO>> findByAutor(@RequestParam String autor, Pageable pageable){
        Page<LivroDTO> dto = livroService.findByAutor(autor, pageable);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/genero")
    public ResponseEntity<Page<LivroDTO>> findByGenero(@RequestParam String genero, Pageable pageable) {
        Page<LivroDTO> dto = livroService.findByGenero(genero, pageable);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        livroService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<LivroDTO> insertLivro(@RequestBody LivroDTO dto){
        dto = livroService.insertLivro(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

}
