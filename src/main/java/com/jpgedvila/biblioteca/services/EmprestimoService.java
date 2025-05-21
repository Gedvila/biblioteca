package com.jpgedvila.biblioteca.services;

import com.jpgedvila.biblioteca.dto.EmprestimoDTO;
import com.jpgedvila.biblioteca.entities.Emprestimo;
import com.jpgedvila.biblioteca.repositories.EmprestimoRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    public EmprestimoService(EmprestimoRepository emprestimoRepository) {
        this.emprestimoRepository = emprestimoRepository;
    }

    @Transactional(readOnly = true)
    public EmprestimoDTO findById(Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Livro não encrontrado"));
        return new EmprestimoDTO(emprestimo);
    }

    @Transactional(readOnly = true)
    public Page<EmprestimoDTO> findAll(org.springframework.data.domain.Pageable pageable) {
        Page<Emprestimo> result = (Page<Emprestimo>) emprestimoRepository.findAll();
        return result.map(x -> new EmprestimoDTO(x));
    }
}
