package com.projetos.cursos.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import com.projetos.cursos.enums.StatusEmail;
import com.projetos.cursos.model.Categoria;
import com.projetos.cursos.model.Cursos;
import com.projetos.cursos.model.EmailModel;
import com.projetos.cursos.repository.CatCrudRepo;
import com.projetos.cursos.repository.EmailRepository;

@Service
public class EmailService {

	@Autowired
	EmailRepository emailRepository;
	
    CatCrudRepo crudRepo;

	@PersistenceContext
    EntityManager em;

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
	public List<Cursos> consultar(String descricao, LocalDate dataInicio, LocalDate dataTermino) {

        CriteriaBuilder criteria = em.getCriteriaBuilder();
        CriteriaQuery<Cursos> criteriaQuery = criteria.createQuery(Cursos.class);

        Root<Cursos> curso = criteriaQuery.from(Cursos.class);
        List<Predicate> predList = new ArrayList<>();

        if (descricao != "") {
            Predicate descricaoPredicate = criteria.equal(curso.get("descricao"), descricao);
            predList.add(descricaoPredicate);
        }

        if (dataInicio != null) {
            Predicate dataIniPredicate = criteria.greaterThanOrEqualTo(curso.get("dataInicio"), dataInicio);
            predList.add(dataIniPredicate);
        }

        if (dataTermino != null) {
            Predicate dataTerPredicate = criteria.lessThanOrEqualTo(curso.get("dataTermino"), dataTermino);
            predList.add(dataTerPredicate);
        }

        Predicate[] predicateArray = new Predicate[predList.size()];

        predList.toArray(predicateArray);

        criteriaQuery.where(predicateArray);

        TypedQuery<Cursos> query = em.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @Transactional
    public Iterable<Categoria> todosCursos() {
        return crudRepo.findAll();
    }

}
