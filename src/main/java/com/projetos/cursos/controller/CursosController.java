package com.projetos.cursos.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.projetos.cursos.model.Cursos;
import com.projetos.cursos.repository.CursoCrudRepo;
import com.projetos.cursos.request.Dados;
import com.projetos.cursos.request.DadosPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@Transactional
@CrossOrigin
public class CursosController {
    
    @Autowired
	private CursoCrudRepo crudRepo;

    @ApiOperation("Serviço de Cadastro. ")
	@PostMapping("/api/cursos")
	public ResponseEntity<String>post(@RequestBody DadosPost request){
		try {
            /*if(crudRepo.findByInicioCursos(request.getInicio()!=null)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Data já está cadastrado");
            }*/

            Cursos cursos = new Cursos();

            cursos.setDescricao(request.getDescricao());
            cursos.setAlunos(request.getAlunos());
            cursos.setInicio(request.getInicio());
            cursos.setTermino(request.getTermino());
            cursos.setCat(request.getCat());

            crudRepo.save(cursos);

            return ResponseEntity.status(HttpStatus.OK).body("Cadastrado com sucesso!!!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Ocorreu um erro: "+e.getMessage());
		}
	}

    @ApiOperation("Serviço que Busca geral")
	@GetMapping("/api/cursos")
	public ResponseEntity<List<Dados>> get(){
		List<Dados> response = new ArrayList<Dados>();
		
		for(Cursos cursos: crudRepo.findAll()) {
			Dados dados = new Dados();
			
            dados.setId(cursos.getId());
            dados.setDescricao(cursos.getDescricao());
            dados.setAlunos(cursos.getAlunos());
            dados.setInicio(cursos.getInicio());
            dados.setTermino(cursos.getTermino());
            dados.setCat(cursos.getCat());;

			response.add(dados);
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

    @ApiOperation("Serviço de Busca por ID")
    @GetMapping("/api/cursos/{id}")
    public ResponseEntity<Dados> getById(@PathVariable("id") Long id) {
        Optional<Cursos> optional = crudRepo.findById(id);

        if(optional.isEmpty()) {
            return ResponseEntity
            .status(HttpStatus.NOT_FOUND).body(null);
        }else{
            Dados dados = new Dados();
            Cursos cursos = optional.get();

            dados.setId(cursos.getId());
            dados.setDescricao(cursos.getDescricao());
            dados.setAlunos(cursos.getAlunos());
            dados.setInicio(cursos.getInicio());
            dados.setTermino(cursos.getTermino());
            dados.setCat(cursos.getCat());

            return ResponseEntity.status(HttpStatus.OK).body(dados);
        }
    }

    @ApiOperation("Serviço de Exclusão de Curso")
    @DeleteMapping("/api/cursos/{id}")
    public ResponseEntity<String>delete(@PathVariable("id")Long id) {
        try {
            Optional<Cursos> optional= crudRepo.findById(id);
            if(optional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("ID não encontrado!");
            }else{
                Cursos cursos = optional.get();
                crudRepo.delete(cursos);
                return ResponseEntity.status(HttpStatus.OK)
                .body("Dados excluidos com sucesso!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Ocorreu algum erro"+e.getMessage());
        }
    }
    
    @ApiOperation("Serviço de Atualização dos dados do Curso")
    @PutMapping("/api/cursos")
    public ResponseEntity<String>put(@RequestBody Dados dados) {
        try {
            Optional<Cursos> optional = crudRepo.findById(dados.getId());

            if(optional.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Não achei o Curso solicitado!!");
            }else{
                Cursos cursos = optional.get();

                cursos.setId(dados.getId());
                cursos.setDescricao(dados.getDescricao());
                cursos.setAlunos(dados.getAlunos());
                cursos.setInicio(dados.getInicio());
                cursos.setTermino(dados.getTermino());
                cursos.setCat(dados.getCat());
                
                crudRepo.save(cursos);
                return ResponseEntity.status(HttpStatus.OK).body("Dados do Curso atualizados!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body("Ocorreu um erro que não permitiu atualizar!!! "+e.getMessage());
        }
    }

}
