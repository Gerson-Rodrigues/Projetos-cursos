package com.projetos.cursos.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetos.cursos.model.Cursos;

@Repository
public interface CursoJpaRepo extends JpaRepository<Cursos, Integer> {
    
}