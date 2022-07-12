package com.projetos.cursos.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projetos.cursos.model.Usuario;
import com.projetos.cursos.repository.IUsuarioRepo;
import com.projetos.cursos.request.Account;
import com.projetos.cursos.security.MD5Cryptography;
import io.swagger.annotations.ApiOperation;

@Controller
@Transactional
public class AccountController {
	@Autowired
	private IUsuarioRepo usuarioRepository;

	private static final String ENDPOINT = "/api/account";
	
	@ApiOperation("Serviço para criação de Conta/Login")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	@CrossOrigin
	public ResponseEntity<String>post(@RequestBody Account request){
		try {
			//verificar se o login informado existe no banco da dados
			if(usuarioRepository.findByLogin(request.getLogin())!=null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Não pode!!! /n Login já está cadastrado");
			}
			
			Usuario p = new Usuario();
			p.setNome(request.getNome());
			p.setLogin(request.getLogin());
			p.setSenha(MD5Cryptography.encrypt(request.getSenha()));
			
			usuarioRepository.save(p);
			
			return ResponseEntity.status(HttpStatus.OK).body("Cadastrado com sucesso");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERRO: "+e.getMessage());
		}
	}

}
