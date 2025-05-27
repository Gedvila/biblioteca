package com.jpgedvila.biblioteca.controllers;

import com.jpgedvila.biblioteca.dto.UsuarioDTO;
import com.jpgedvila.biblioteca.services.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/aluno")
    public ResponseEntity<UsuarioDTO> cadastrarAluno(@RequestBody UsuarioDTO dto){
        dto = usuarioService.insertAluno(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PostMapping("/professor")
    public ResponseEntity<UsuarioDTO> cadastrarProfessor(@RequestBody UsuarioDTO dto){
        dto = usuarioService.insertProfessor(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> findAll(Pageable pageable) {
        Page<UsuarioDTO> dto = usuarioService.findAll(pageable);
        return ResponseEntity.ok(dto);
    }
}
