package com.projetos.cursos.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projetos.cursos.model.Cursos;

@Repository
public interface CursoCrudRepo extends CrudRepository<Cursos, Long>{

    List<Cursos> findByInicioGreaterThanEqualAndTerminoLessThanEqual(LocalDate inicio, LocalDate termino);
   /*
    @Query(value= "form Cursos c where c.inicio <= :startDate AND c.termino :endDate")
    public List<Cursos> getAllBetweenDates(@Param("startDate") LocalDate startDate,
                                            @Param("endDate") LocalDate endDate);

    @Query("Select c from Cursos c where descricao like Contat(:desc)")
    public Cursos getByDescricao(@Param("desc") String descricao);*/
}