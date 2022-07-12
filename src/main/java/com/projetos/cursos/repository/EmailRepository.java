package com.projetos.cursos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetos.cursos.model.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel , UUID>{
    
}
