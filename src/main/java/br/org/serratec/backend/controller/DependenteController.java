package br.org.serratec.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.backend.exception.DependenteException;
import br.org.serratec.backend.model.Dependente;
import br.org.serratec.backend.service.DependenteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/dependentes")
public class DependenteController {

	@Autowired
	private DependenteService dependenteService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Insere dados de um dependente", notes = "Inserir dependente")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Dependente cadastrado com sucesso"),
			@ApiResponse(code = 401, message = "Erro de Autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Quando ocorre uma exceçãooo") })
	public Dependente inserir(@Valid @RequestBody Dependente dependente) throws DependenteException {
		return dependenteService.inserir(dependente);
	}

	@PostMapping("/inserirTodos")
	@ApiOperation(value = "Insere dados de vários dependentes", notes = "Inserir dependentes")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Dependentes cadastrados com sucesso"),
			@ApiResponse(code = 401, message = "Erro de Autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Quando ocorre uma exceção") })
	@ResponseStatus(HttpStatus.CREATED)
	public List<Dependente> inserirTodos(@Valid @RequestBody List<Dependente> dependentes) throws DependenteException {
		return dependenteService.inserirTodos(dependentes);
	}

	@GetMapping("{id}")
	@ApiOperation(value = "Retorna um dependente", notes = "Dependente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um dependente"),
			@ApiResponse(code = 401, message = "Erro de Autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Quando ocorre uma exceção") })
	public ResponseEntity<Dependente> buscar(@PathVariable Long id) {
		return dependenteService.buscar(id);
	}

	@GetMapping
	@ApiOperation(value = "Lista todos os dependentes ", notes = "Listagem de dependentes")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna todos os dependentes"),
			@ApiResponse(code = 401, message = "Erro de Autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Quando ocorre uma exceção") })
	public ResponseEntity<List<Dependente>> listar() {
		return dependenteService.listar();
	}

	@PutMapping("{id}")
	@ApiOperation(value = "Atualiza dados de um dependente", notes = "Atualizar dependente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Dependente atualizado"),
			@ApiResponse(code = 401, message = "Erro de Autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Quando ocorre uma exceção") })
	public ResponseEntity<Dependente> atualizar(@Valid @RequestBody Dependente dependente, @PathVariable Long id) {
		return dependenteService.atualizar(dependente, id);
	}

	@DeleteMapping("{id}")
	@ApiOperation(value = "Remove um dependente", notes = "Remover dependente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Dependente removido"),
			@ApiResponse(code = 401, message = "Erro de Autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Quando ocorre uma exceção") })
	public ResponseEntity<Void> deletarDependente(@PathVariable Long id) {
		return dependenteService.deletarDependente(id);
	}
}
