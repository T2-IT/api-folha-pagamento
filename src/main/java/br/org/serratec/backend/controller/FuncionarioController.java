package br.org.serratec.backend.controller;

import java.util.List;
import java.util.Optional;

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
import br.org.serratec.backend.repository.FuncionarioRepository;
import br.org.serratec.backend.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
//fjufgu
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private FuncionarioService funcionarioService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Funcionario inserir(@Valid @RequestBody Funcionario funcionario) {
		return funcionarioService.inserir(funcionario);
	}
	@PostMapping("/inserirTodos")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Funcionario> inserirTodos(List<Funcionario> funcionarios) {
		return funcionarioRepository.saveAll(funcionarios);
	}

	@GetMapping("{id}")
	public ResponseEntity<Funcionario> buscar(@PathVariable Long id) {
		Optional<Funcionario> funcionarios = funcionarioRepository.findById(id);
		if (funcionarios.isPresent()) {
			return ResponseEntity.ok(funcionarios.get());
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping
	public ResponseEntity<List<Funcionario>> listar() {
		List<Funcionario> funcionarios = funcionarioRepository.findAll();
		return ResponseEntity.ok(funcionarios);
	}

	@PutMapping("{id}")
	public ResponseEntity<Funcionario> atualizar(@Valid @RequestBody Funcionario funcionario, @PathVariable Long id) {
		if (!funcionarioRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		funcionario.setId(id);
		funcionarioRepository.save(funcionario);
		return ResponseEntity.ok(funcionario);

	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (!funcionarioRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		funcionarioRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
