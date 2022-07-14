package com.projetos.cursos.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projetos.cursos.dto.EmailDto;
import com.projetos.cursos.model.EmailModel;
import com.projetos.cursos.service.EmailService;

@RestController

public class EmailController {

	@Autowired
	EmailService emailService;
	
	@PostMapping("/envia-email")
	public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailDto emailDto){
		EmailModel emailModel = new EmailModel();
		BeanUtils.copyProperties(emailDto, emailModel);
		
		emailService.sendEmail(emailModel);
		return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
	}
}