package com.jpgedvila.biblioteca.controllers;

import com.jpgedvila.biblioteca.dto.LivroDTO;
import com.jpgedvila.biblioteca.dto.UsuarioDTO;
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

    @GetMapping
    public ResponseEntity<Page<LivroDTO>> findAll(Pageable pageable) {
        Page<LivroDTO> dto = livroService.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/{genero}")
    public ResponseEntity<Page<LivroDTO>> findByGenero(@PathVariable String genero, Pageable pageable) {
        Page<LivroDTO> dto = livroService.findByGenero(genero, pageable);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<LivroDTO> insertLivro(@RequestBody LivroDTO dto){
        dto = livroService.insertLivro(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

}
