package com.projetos.cursos.request;

import java.time.LocalDate;

import com.projetos.cursos.model.Categoria;

import lombok.Data;

@Data
public class Dados {

    private Long alunos, id; 
    private String descricao;
    private LocalDate inicio, termino;
    private Categoria cat;
}
