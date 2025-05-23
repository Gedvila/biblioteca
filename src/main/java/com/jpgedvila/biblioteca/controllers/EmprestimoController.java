package com.jpgedvila.biblioteca.controllers;

import com.jpgedvila.biblioteca.dto.EmprestimoDTO;
import com.jpgedvila.biblioteca.services.EmprestimoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/emprestimo")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;
    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmprestimoDTO> findById(@PathVariable Long id) {
        EmprestimoDTO dto = emprestimoService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<EmprestimoDTO>> findAll(Pageable pageable) {
        Page<EmprestimoDTO> dto = emprestimoService.findAll(pageable);
        return ResponseEntity.ok().body(dto);
    }


    @PostMapping
    public ResponseEntity<EmprestimoDTO> cadastrarEmprestimo(@RequestBody EmprestimoDTO dto) {
        dto = emprestimoService.criarEmprestimo(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

}
