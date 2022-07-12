package com.projetos.cursos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idusuario")
	private Integer idusuario;
	@Column(name= "nome", length=150, nullable=false)
	private String nome;
	@Column(name="login", length=50, nullable=false)
	private String login;
	@Column(name="senha", length=50, nullable=false)
	private String senha;
}