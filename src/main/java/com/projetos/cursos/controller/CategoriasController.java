package com.projetos.cursos.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetos.cursos.model.Categoria;
import com.projetos.cursos.repository.CatCrudRepo;
import com.projetos.cursos.request.CatDados;
import com.projetos.cursos.service.EmailService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriasController {

	@Autowired
    CatCrudRepo crudRepo;
	
    EmailService service;

    @ApiOperation("Serviço de Busca de Categorias")
    @GetMapping
	public ResponseEntity<List<CatDados>> get(){
		List<CatDados> response = new ArrayList<CatDados>();
		List<Categoria> listaCursos = (List<Categoria>) service.todosCursos();
        
		for(Categoria p: listaCursos) {
			CatDados item = new CatDados();
			
			item.setId(p.getId());
			item.setCategoria(p.getCategoria());
			
			response.add(item);
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
