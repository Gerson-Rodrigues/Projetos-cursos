package com.projetos.cursos.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projetos.cursos.model.Cursos;

@Repository
public interface CursoCrudRepo extends CrudRepository<Cursos, Integer>{

    Optional<Cursos> findById(Long id);
    
}