package com.projetos.cursos.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_cursos")
public class Cursos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( nullable = false )
    private String descricao; 

    @Column( nullable = false )
    private String alunos;
    
    @Column( nullable = false )
    private LocalDate inicio;
    
    @Column( nullable = false )
    private LocalDate termino;

    @JoinColumn( nullable = false )
    @ManyToOne
    private Categoria cat;


}
