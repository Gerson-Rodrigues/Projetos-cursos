package com.projetos.cursos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projetos.cursos.model.Categoria;

@Repository
public interface CatCrudRepo extends CrudRepository<Categoria, Long>{
    
}
