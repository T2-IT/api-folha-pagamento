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

import br.org.serratec.backend.exception.FuncionarioException;
import br.org.serratec.backend.model.Funcionario;
import br.org.serratec.backend.service.FuncionarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Insere dados de um funcionario", notes = "Inserir funcionario")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Funcionario cadastrado com sucesso"),
			@ApiResponse(code = 401, message = "Erro de Autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Quando ocorre uma exceçãooo") })
	
	public Funcionario inserir(@Valid @RequestBody Funcionario funcionario) throws FuncionarioException{
		return funcionarioService.inserir(funcionario);
	}

	@PostMapping("/inserirTodos")
	@ApiOperation(value = "Insere dados de vários funcionarios", notes = "Inserir funcionarios")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Funcionarios cadastrados com sucesso"),
			@ApiResponse(code = 401, message = "Erro de Autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Quando ocorre uma exceção") })
	@ResponseStatus(HttpStatus.CREATED)
	public List<Funcionario> inserirTodos(@Valid @RequestBody List<Funcionario> funcionarios) throws FuncionarioException {
		return funcionarioService.inserirTodos(funcionarios);
	}

	@GetMapping("{id}")
	@ApiOperation(value = "Retorna um funcionario", notes = "Funcionario")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um funcionario"),
			@ApiResponse(code = 401, message = "Erro de Autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Quando ocorre uma exceção") })
	public ResponseEntity<Funcionario> buscar(@PathVariable Long id) {
		return funcionarioService.buscar(id);
	}

	@GetMapping
	@ApiOperation(value = "Lista todos os funcionarios ", notes = "Listagem de funcionarios")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna todos os funcionarios"),
			@ApiResponse(code = 401, message = "Erro de Autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Quando ocorre uma exceção") })
	public ResponseEntity<List<Funcionario>> listar() throws FuncionarioException {
		return funcionarioService.listar();
	}

	@PutMapping("{id}")
	@ApiOperation(value = "Atualiza dados de um funcionario", notes = "Atualizar funcionario")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Funcionario atualizado"),
			@ApiResponse(code = 401, message = "Erro de Autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Quando ocorre uma exceção") })
	public ResponseEntity<Funcionario> atualizar(@PathVariable Long id, @Valid @RequestBody Funcionario funcionario) throws FuncionarioException {
		return funcionarioService.atualizar(id, funcionario);
	}

	@DeleteMapping("{id}")
	@ApiOperation(value = "Remove um funcionario", notes = "Remover funcionario")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Funcionario removido"),
			@ApiResponse(code = 401, message = "Erro de Autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Quando ocorre uma exceção") })
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		return funcionarioService.deletar(id);
	}
	//Documentação
}
