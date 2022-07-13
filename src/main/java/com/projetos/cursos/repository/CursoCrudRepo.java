package com.projetos.cursos.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projetos.cursos.model.Cursos;

@Repository
public interface CursoCrudRepo extends CrudRepository<Cursos, Long>{

    List<Cursos> getAllBetweenDates(LocalDate inicio, LocalDate termino);
   
}