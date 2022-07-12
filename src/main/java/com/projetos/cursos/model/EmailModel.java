package com.projetos.cursos.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.projetos.cursos.enums.StatusEmail;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_EMAIL")
public class EmailModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String ownerRef;
	private String de;
	private String para;
	private String assunto;
	@Column(columnDefinition = "TEXT")
	private String texto;
	private LocalDateTime sendDateEmail;
	private StatusEmail statusEmail;
	
}
