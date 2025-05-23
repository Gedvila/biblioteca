package com.jpgedvila.biblioteca.services;

import com.jpgedvila.biblioteca.dto.EmprestimoDTO;
import com.jpgedvila.biblioteca.entities.Emprestimo;
import com.jpgedvila.biblioteca.entities.Livro;
import com.jpgedvila.biblioteca.entities.Usuario;
import com.jpgedvila.biblioteca.repositories.EmprestimoRepository;
import com.jpgedvila.biblioteca.repositories.LivroRepository;
import com.jpgedvila.biblioteca.repositories.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.View;

import java.util.Optional;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final LivroRepository livroRepository;
    private final UsuarioRepository usuarioRepository;
    private final View error;

    public EmprestimoService(EmprestimoRepository emprestimoRepository, LivroRepository livroRepository, UsuarioRepository usuarioRepository, View error) {
        this.emprestimoRepository = emprestimoRepository;
        this.livroRepository = livroRepository;
        this.usuarioRepository = usuarioRepository;
        this.error = error;
    }

    @Transactional(readOnly = true)
    public EmprestimoDTO findById(Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Livro não encrontrado"));
        return new EmprestimoDTO(emprestimo);
    }

    @Transactional(readOnly = true)
    public Page<EmprestimoDTO> findAtrasados(Pageable pageable) {
        Page<Emprestimo> result = emprestimoRepository.findAtrasados(pageable);
        return result.map(EmprestimoDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<EmprestimoDTO> findAll(Pageable pageable) {
        Page<Emprestimo> result = emprestimoRepository.findAll(pageable);
        return result.map(EmprestimoDTO::new);
    }

    public EmprestimoDTO criarEmprestimo(EmprestimoDTO dto) {
        Livro livro = livroRepository.findById(dto.getIdLivro()).orElseThrow(
                (() -> new RuntimeException("Livro não encontrado com ID: " + dto.getIdLivro()))
        );

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario()).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado com ID: " + dto.getIdUsuario())
        );

        if (!livro.getDisponivel()) {
            throw new RuntimeException("Livro com ID " + livro.getId() + " não está disponível para empréstimo.");
        }

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setDataRetirada(dto.getDataRetirada());
        emprestimo.setDataDevolucao(dto.getDataDevolucao());
        emprestimo.setLivro(livro);
        emprestimo.setUsuario(usuario);

        emprestimoRepository.save(emprestimo);

        livro.setDisponivel(false);
        livroRepository.save(livro);

        return new EmprestimoDTO(emprestimo);


    }
}
