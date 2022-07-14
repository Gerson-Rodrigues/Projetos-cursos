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

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Audited
public class Cursos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( nullable = false )
    private String descricao; 

    @Column( nullable = true )
    private Long alunos;
    
    @Column( nullable = false )
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate inicio;
    
    @Column( nullable = false )
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate termino;

    @JoinColumn( nullable = false )
    @ManyToOne
    private Categoria cat;


}
