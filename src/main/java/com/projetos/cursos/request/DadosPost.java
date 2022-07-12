package com.projetos.cursos.request;

import java.time.LocalDate;

import com.projetos.cursos.model.Categoria;

import lombok.Data;

@Data
public class DadosPost {
    
    private String alunos, descricao;
    private LocalDate inicio, termino;
    private Categoria cat;
}