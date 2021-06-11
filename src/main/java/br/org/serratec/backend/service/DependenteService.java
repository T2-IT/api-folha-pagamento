package br.org.serratec.backend.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.org.serratec.backend.exception.DependenteException;
import br.org.serratec.backend.model.Dependente;
import br.org.serratec.backend.model.Funcionario;
import br.org.serratec.backend.repository.DependenteRepository;

@Service
public class DependenteService {

	@Autowired
	private DependenteRepository dependenteRepository;

	public Dependente inserir(@RequestBody Dependente dependente) throws DependenteException {
		dependente.setIdade(Period.between(dependente.getDataNascimento(), LocalDate.now()));
		if (dependente.getIdade().getYears() > 18) { 
			throw new DependenteException("O dependente" + dependente.getNome() + " nao pode ser menor de 18 anos");
		} else if (dependente.getDataNascimento() == LocalDate.now()) {
			throw new DependenteException("O dependente nao pode ter nascido hoje");
		}
		return dependenteRepository.save(dependente);
	}

	public List<Dependente> inserirTodos(List<Dependente> dependentes) throws DependenteException {
		for (Dependente dependente : dependentes) {
			dependente.setIdade(Period.between(dependente.getDataNascimento(), LocalDate.now()));
			if (dependente.getIdade().getYears() > 18) { 
				throw new DependenteException("O dependente" + dependente.getNome() + " nao pode ser menor de 18 anos");
			}
		}
		return dependenteRepository.saveAll(dependentes);
	}

	public ResponseEntity<Dependente> buscar(@PathVariable Long id) {
		Optional<Dependente> dependente = dependenteRepository.findById(id);
		if (dependente.isPresent()) {
			return ResponseEntity.ok(dependente.get());
		}
		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<List<Dependente>> listar() {
		List<Dependente> dependentes = dependenteRepository.findAll();
		return ResponseEntity.ok(dependentes);
	}

	public ResponseEntity<Dependente> atualizar(@Valid @RequestBody Dependente dependente, @PathVariable Long id) {
		if (!dependenteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		dependente.setId(id);
		dependente = dependenteRepository.save(dependente);
		return ResponseEntity.ok(dependente);
	}

	public ResponseEntity<Void> deletarDependente(@PathVariable Long id) {
		if (!dependenteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		dependenteRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
