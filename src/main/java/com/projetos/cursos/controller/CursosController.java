package com.projetos.cursos.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.projetos.cursos.CursosApplication;
import com.projetos.cursos.model.Cursos;
import com.projetos.cursos.repository.CursoCrudRepo;
import com.projetos.cursos.request.Dados;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@Transactional
@CrossOrigin
public class CursosController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CursosApplication.class);

    @Autowired
    private CursoCrudRepo crudRepo;


    @PersistenceContext
    EntityManager em;

    @ApiOperation("Serviço de Cadastro. ")
    @PostMapping("/api/cursos")
    public ResponseEntity<String> post(@RequestBody Cursos cursos) {
        try {
            validaData(cursos);

            if (cursos.getInicio().isBefore(LocalDate.now())) {
                LOGGER.info("Serviço para cadastro não realizado!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("ERRO: Data de início menor que a data atual.");
            } else {
                crudRepo.save(cursos);
                LOGGER.info("Serviço para cadastro realizado com sucesso.");
                return ResponseEntity.status(HttpStatus.OK).body("Curso cadastrado");
            }

        } catch (Exception e) {
            LOGGER.error("Erro ao cadastrar.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro :" + e.getMessage());
        }
    }

    private void validaData(Cursos cursos) {
        if (cursos.getInicio().isAfter(cursos.getTermino())) {
            LOGGER.info("Periodo por data requisitado com sucesso!");
            throw new RuntimeException("não permitido");
        }

        List<Cursos> cursosBuscados = crudRepo.getAllBetweenDates(cursos.getInicio(), cursos.getTermino());
        if (cursosBuscados.size() > 0) {
            LOGGER.info("Existe(m) curso(s) planejados(s) dentro do período informado.");
            // Método de validação de data não permitido
            throw new RuntimeException("Já existem cursos cadastrados no período informado.");
        }
    }

    @ApiOperation("Serviço que Busca geral")
    @GetMapping("/api/cursos")
    public ResponseEntity<List<Cursos>> get(
            @RequestParam(required = false) String descricao,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate termino) {

                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<Cursos> cq = cb.createQuery(Cursos.class);
        
                Root<Cursos> cursos = cq.from(Cursos.class);
        
                List<Predicate> predicates = new ArrayList<>();
        
                if (descricao != null) {
        
                    Predicate descricaoAssuntoPredicate = cb.equal(cursos.get("descricaoAssunto"), descricao);
                    predicates.add(descricaoAssuntoPredicate);
                }
        
                Predicate[] predicateArray = new Predicate[predicates.size()];
                predicates.toArray(predicateArray);
                cq.where(predicateArray);
        
                TypedQuery<Cursos> query = em.createQuery(cq);
        
                LOGGER.info("Serviço para consulta realizada com sucesso!");
                return ResponseEntity.status(HttpStatus.OK).body(query.getResultList());
    }

    @ApiOperation("Serviço de Busca por ID")
    @GetMapping("/api/cursos/{id}")
    public ResponseEntity<Cursos> getById(@PathVariable("id") Long id) {
        Optional<Cursos> optional = crudRepo.findById(id);

        if (optional.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND).body(null);
        } else {
            Dados dados = new Dados();
            Cursos cursos = optional.get();

            dados.setId(cursos.getId());
            dados.setDescricao(cursos.getDescricao());
            dados.setAlunos(cursos.getAlunos());
            dados.setInicio(cursos.getInicio());
            dados.setTermino(cursos.getTermino());
            dados.setCat(cursos.getCat());

            return ResponseEntity.status(HttpStatus.OK).body(cursos);
        }
    }

    @ApiOperation("Serviço de Exclusão de Curso")
    @DeleteMapping("/api/cursos/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            Optional<Cursos> optional = crudRepo.findById(id);
            if (optional.isEmpty()) {
                LOGGER.info("Serviço para deletar não realizado!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("ID não encontrado!");
            } else {
                Cursos cursos = optional.get();
                crudRepo.delete(cursos);
                LOGGER.info("Serviço para deletar efetuado!");
                return ResponseEntity.status(HttpStatus.OK)
                        .body("Dados excluidos com sucesso!");
            }
        } catch (Exception e) {
            LOGGER.error("Erro ao deletar.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu algum erro" + e.getMessage());
        }
    }

    @ApiOperation("Serviço de Atualização dos dados do Curso")
    @PutMapping("/api/cursos")
    public ResponseEntity<String> put(@RequestBody Cursos cursos ) {
        try {

			if (cursos.getInicio().isBefore(LocalDate.now())) {

				LOGGER.info("Serviço para atualizar não realizado!");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Curso não atualizado");

			} else {

				crudRepo.save(cursos);
				LOGGER.info("Serviço para atualizar realizado.");
				return ResponseEntity.status(HttpStatus.OK).body("Curso atualizado");
			}

		} catch (Exception e) {
			LOGGER.error("Erro ao atualizar.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro: " + e.getMessage());
		}

    }

}
