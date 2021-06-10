package br.org.serratec.backend.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.org.serratec.backend.model.Funcionario;
import br.org.serratec.backend.model.TaxaIR;
import br.org.serratec.backend.model.TaxasInss;
import br.org.serratec.backend.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	public Funcionario inserir(@Valid @RequestBody Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}

	public List<Funcionario> inserirTodos(List<Funcionario> funcionarios) {
		return funcionarioRepository.saveAll(funcionarios);
	}

	public ResponseEntity<Funcionario> buscar(@PathVariable Long id) {
		Optional<Funcionario> funcionarios = funcionarioRepository.findById(id);
		if (funcionarios.isPresent()) {
			Funcionario funcionario = new Funcionario();

			if (funcionario.getSalarioBruto() >= 1100.01 && funcionario.getSalarioBruto() <= 2203.48) {
				funcionario.setDescontoInss(
						funcionario.getSalarioBruto() * TaxasInss.TAXA1.getTaxa() - TaxasInss.TAXA1.getDeducao());
			} else if (funcionario.getSalarioBruto() >= 2203.49 && funcionario.getSalarioBruto() <= 3305.22) {
				funcionario.setDescontoInss(
						funcionario.getSalarioBruto() * TaxasInss.TAXA2.getTaxa() - TaxasInss.TAXA2.getDeducao());
			} else if (funcionario.getSalarioBruto() >= 3305.23 && funcionario.getSalarioBruto() <= 6433.57) {
				funcionario.setDescontoInss(
						funcionario.getSalarioBruto() * TaxasInss.TAXA3.getTaxa() - TaxasInss.TAXA3.getDeducao());
			} else if (funcionario.getSalarioBruto() > 6433.57) {
				funcionario.setDescontoInss(6433.57 * TaxasInss.TAXA5.getTaxa() - TaxasInss.TAXA5.getDeducao());
			} else {
				funcionario.setDescontoInss(
						funcionario.getSalarioBruto() * TaxasInss.TAXA1.getTaxa() - TaxasInss.TAXA1.getDeducao());
			}

			double salarioBase = funcionario.getSalarioBruto() - (funcionario.getDependentes().size() * 189.59)
					- funcionario.getDescontoInss();
			if (salarioBase < 1903.98) {
				funcionario.setDescontoIR(0);
			} else if (salarioBase >= 1903.98 && salarioBase <= 2826.65) {
				funcionario.setDescontoIR(((salarioBase) * TaxaIR.TAXA1.getAliquota()) - TaxaIR.TAXA1.getDeducao());
			} else if (salarioBase >= 2826.66 && salarioBase <= 3751.05) {
				funcionario.setDescontoIR(((salarioBase) * TaxaIR.TAXA2.getAliquota()) - TaxaIR.TAXA2.getDeducao());
			} else if (salarioBase >= 3751.06 && salarioBase <= 4664.68) {
				funcionario.setDescontoIR(((salarioBase) * TaxaIR.TAXA3.getAliquota()) - TaxaIR.TAXA3.getDeducao());
			} else {
				funcionario.setDescontoIR(((salarioBase) * TaxaIR.TAXA4.getAliquota()) - TaxaIR.TAXA4.getDeducao());
			}

			funcionario.setSalarioLiquido(
					funcionario.getSalarioBruto() - funcionario.getDescontoInss() - funcionario.getDescontoIR());

			return ResponseEntity.ok(funcionarios.get());
		}
		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<List<Funcionario>> listar() {
		List<Funcionario> funcionarios = funcionarioRepository.findAll();
		return ResponseEntity.ok(funcionarios);
	}

	public ResponseEntity<Funcionario> atualizar(@Valid @RequestBody Funcionario funcionario, @PathVariable Long id) {
		if (!funcionarioRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		funcionario.setId(id);
		funcionarioRepository.save(funcionario);
		return ResponseEntity.ok(funcionario);

	}

	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (!funcionarioRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		funcionarioRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
