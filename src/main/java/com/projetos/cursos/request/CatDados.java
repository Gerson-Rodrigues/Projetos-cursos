package com.projetos.cursos.request;

import com.projetos.cursos.controller.CategoriasController;
import com.projetos.cursos.model.Categoria;

import lombok.Data;

@Data
public class CatDados {
    
    private Long id;
    private String categoria;
}
