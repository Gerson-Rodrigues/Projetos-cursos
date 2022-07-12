package com.projetos.cursos.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class EmailDto {

	@NotBlank
	@Email
	private String de;
	@NotBlank
	@Email
	private String para;
	@NotBlank
	private String assunto;
	@NotBlank
	private String texto;

}