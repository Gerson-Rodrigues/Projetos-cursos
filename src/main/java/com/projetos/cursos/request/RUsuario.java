package com.projetos.cursos.request;

import lombok.Data;

@Data
public class RUsuario {
    
    private Integer idusuario;
	private String nome;
	private String login;
	private String senha;
}
