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

import br.org.serratec.backend.model.Funcionario;
import br.org.serratec.backend.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Funcionario inserir(@RequestBody Funcionario funcionario) {
		System.out.println("Inserir funcionario");
		return funcionarioService.inserir(funcionario);
	}

	@PostMapping("/inserirTodos")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Funcionario> inserirTodos(List<Funcionario> funcionarios) {
		System.out.println("Inserir todos os funcionarios");
		return funcionarioService.inserirTodos(funcionarios);
	}

	@GetMapping("{id}")
	public ResponseEntity<Funcionario> buscar(@PathVariable Long id) {
		return funcionarioService.buscar(id);
	}

	@GetMapping
	public ResponseEntity<List<Funcionario>> listar() {
		return funcionarioService.listar();
	}

	@PutMapping("{id}")
	public ResponseEntity<Funcionario> atualizar(@Valid @RequestBody Funcionario funcionario, @PathVariable Long id) {
		return funcionarioService.atualizar(funcionario, id);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		return funcionarioService.deletar(id);
	}
}
