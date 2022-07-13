package com.projetos.cursos.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projetos.cursos.model.Cursos;

@Repository
public interface CursoCrudRepo extends CrudRepository<Cursos, Long>{
    
    /*
    @Query("select u from tb_cursos u where u.inicio=:inicio")
	Cursos findByInicioCursos(@Param("inicio") boolean b);
	
	@Query("select u from tb_cursos u where u.login=:login and u.senha=:senha")
	Cursos findByInicioTerminoCursos(@Param("login")String login, 
								@Param("senha")String senha);*/
}