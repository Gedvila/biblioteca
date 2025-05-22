package com.jpgedvila.biblioteca.services;

import com.jpgedvila.biblioteca.dto.UsuarioDTO;
import com.jpgedvila.biblioteca.entities.Aluno;
import com.jpgedvila.biblioteca.entities.Professor;
import com.jpgedvila.biblioteca.entities.Usuario;
import com.jpgedvila.biblioteca.repositories.AlunoRepository;
import com.jpgedvila.biblioteca.repositories.ProfessorRepository;
import com.jpgedvila.biblioteca.repositories.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;
    public UsuarioService(UsuarioRepository usuarioRepository, AlunoRepository alunoRepository, ProfessorRepository professorRepository){
        this.usuarioRepository = usuarioRepository;
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
    }

    @Transactional
    public UsuarioDTO insertAluno(UsuarioDTO dto){
        Aluno entity = new Aluno();

        saveUsuario(dto,entity);

        entity = alunoRepository.save(entity);

        return new UsuarioDTO(entity);

    }

    public UsuarioDTO insertProfessor(UsuarioDTO dto){
        Professor entity = new Professor();

        saveUsuario(dto, entity);

        entity = professorRepository.save(entity);

        return new UsuarioDTO(entity);

    }

    @Transactional(readOnly = true)
    public Page<UsuarioDTO> findAll(org.springframework.data.domain.Pageable pageable) {
        Page<Usuario> result = usuarioRepository.findAll((org.springframework.data.domain.Pageable) pageable);
        return result.map(x -> new UsuarioDTO(x));
    }

    private void saveUsuario(UsuarioDTO dto, Usuario entity){
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setTelefone(dto.getTelefone());
    }
}
