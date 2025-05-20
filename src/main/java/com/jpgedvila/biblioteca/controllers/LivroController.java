package com.jpgedvila.biblioteca.controllers;

import com.jpgedvila.biblioteca.dto.LivroDTO;
import com.jpgedvila.biblioteca.services.LivroService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/livro")
public class LivroController {

    private final LivroService livroService;
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<LivroDTO> findById(@PathVariable Long id) {
        LivroDTO dto = livroService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<LivroDTO>> findAll(Pageable pageable) {
        Page<LivroDTO> dto = livroService.findAll(pageable);
        return ResponseEntity.ok(dto);
    }
}
