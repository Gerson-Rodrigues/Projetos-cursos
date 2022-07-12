package com.projetos.cursos.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import com.projetos.cursos.enums.StatusEmail;
import com.projetos.cursos.model.EmailModel;
import com.projetos.cursos.repository.EmailRepository;

@Service
public class EmailService {

	@Autowired
	EmailRepository emailRepository;
	
	@Autowired
	private JavaMailSender emailSender;
	
	public EmailModel sendEmail(EmailModel emailModel) {
		emailModel.setSendDateEmail(LocalDateTime.now());
		
		try {
			
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(emailModel.getDe());
			message.setTo(emailModel.getPara());
			message.setSubject(emailModel.getAssunto());
			message.setText(emailModel.getTexto());

			emailSender.send(message);
			
			emailModel.setStatusEmail(StatusEmail.SENT);
			return emailRepository.save(emailModel);
		} catch (MailException e) {
			emailModel.setStatusEmail(StatusEmail.ERROR);
			return emailRepository.save(emailModel);
		}		
	}
}
