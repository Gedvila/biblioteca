package com.jpgedvila.biblioteca.controllers;

import com.jpgedvila.biblioteca.services.LivroService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/livro")
public class LivroController {

    private final LivroService livroService;
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }
}
