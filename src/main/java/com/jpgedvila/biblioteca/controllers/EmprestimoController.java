package com.jpgedvila.biblioteca.controllers;

import com.jpgedvila.biblioteca.services.EmprestimoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/emprestimo")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;
    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }
}
