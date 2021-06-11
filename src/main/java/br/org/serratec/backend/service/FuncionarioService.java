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

	public Funcionario inserir(@RequestBody Funcionario funcionario) {
		if (funcionario.getSalarioBruto() >= 1100.01 && funcionario.getSalarioBruto() <= 2203.48) {
			funcionario.setDescontoInss(
					funcionario.getSalarioBruto() * TaxasInss.TAXA1.getTaxa() - TaxasInss.TAXA1.getDeducao());
			funcionario.setTaxasInss(TaxasInss.TAXA1);
		} else if (funcionario.getSalarioBruto() >= 2203.49 && funcionario.getSalarioBruto() <= 3305.22) {
			funcionario.setDescontoInss(
					funcionario.getSalarioBruto() * TaxasInss.TAXA2.getTaxa() - TaxasInss.TAXA2.getDeducao());
			funcionario.setTaxasInss(TaxasInss.TAXA3);
		} else if (funcionario.getSalarioBruto() >= 3305.23 && funcionario.getSalarioBruto() <= 6433.57) {
			funcionario.setDescontoInss(
					funcionario.getSalarioBruto() * TaxasInss.TAXA3.getTaxa() - TaxasInss.TAXA3.getDeducao());
			funcionario.setTaxasInss(TaxasInss.TAXA4);
		} else if (funcionario.getSalarioBruto() > 6433.57) {
			funcionario.setDescontoInss(6433.57 * TaxasInss.TAXA4.getTaxa() - TaxasInss.TAXA4.getDeducao());
			funcionario.setTaxasInss(TaxasInss.TAXA4);
		} else {
			funcionario.setDescontoInss(
					funcionario.getSalarioBruto() * TaxasInss.TAXA5.getTaxa() - TaxasInss.TAXA5.getDeducao());
			funcionario.setTaxasInss(TaxasInss.TAXA5);
		}

		if (funcionario.getSalarioBruto() < 1903.98) {
			funcionario.setDescontoIR(0);
		} else if (funcionario.getSalarioBruto() >= 1903.98 && funcionario.getSalarioBruto() <= 2826.65) {
			funcionario.setDescontoIR(((funcionario.getSalarioBruto() - (funcionario.getDependentes().size() * 189.59)
					- funcionario.getDescontoInss()) * TaxaIR.TAXA1.getAliquota()) - TaxaIR.TAXA1.getDeducao());
			funcionario.setTaxaIR(TaxaIR.TAXA1);
		} else if (funcionario.getSalarioBruto() >= 2826.66 && funcionario.getSalarioBruto() <= 3751.05) {
			funcionario.setDescontoIR(((funcionario.getSalarioBruto() - (funcionario.getDependentes().size() * 189.59)
					- funcionario.getDescontoInss()) * TaxaIR.TAXA2.getAliquota()) - TaxaIR.TAXA2.getDeducao());
			funcionario.setTaxaIR(TaxaIR.TAXA2);
		} else if (funcionario.getSalarioBruto() >= 3751.06 && funcionario.getSalarioBruto() <= 4664.68) {
			funcionario.setDescontoIR(((funcionario.getSalarioBruto() - (funcionario.getDependentes().size() * 189.59)
					- funcionario.getDescontoInss()) * TaxaIR.TAXA3.getAliquota()) - TaxaIR.TAXA3.getDeducao());
			funcionario.setTaxaIR(TaxaIR.TAXA3);
		} else {
			funcionario.setDescontoIR(((funcionario.getSalarioBruto() - (funcionario.getDependentes().size() * 189.59)
					- funcionario.getDescontoInss()) * TaxaIR.TAXA4.getAliquota()) - TaxaIR.TAXA4.getDeducao());
			funcionario.setTaxaIR(TaxaIR.TAXA4);
		}

		funcionario.setSalarioLiquido(
				funcionario.getSalarioBruto() - funcionario.getDescontoInss() - funcionario.getDescontoIR());
		return funcionarioRepository.save(funcionario);
	}

	public List<Funcionario> inserirTodos(List<Funcionario> funcionarios) {
		return funcionarioRepository.saveAll(funcionarios);
	}

	public ResponseEntity<Funcionario> buscar(@PathVariable Long id) {
		Optional<Funcionario> funcionarios = funcionarioRepository.findById(id);
		if (funcionarios.isPresent()) {
			return ResponseEntity.ok(funcionarios.get());
		}
		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<List<Funcionario>> listar() {
		List<Funcionario> funcionarios = funcionarioRepository.findAll();
		for (Funcionario funcionario : funcionarios) {
			if (funcionario.getSalarioBruto() >= 1100.01 && funcionario.getSalarioBruto() <= 2203.48) {
				funcionario.setDescontoInss(
						funcionario.getSalarioBruto() * TaxasInss.TAXA1.getTaxa() - TaxasInss.TAXA1.getDeducao());
				funcionario.setTaxasInss(TaxasInss.TAXA1);
			} else if (funcionario.getSalarioBruto() >= 2203.49 && funcionario.getSalarioBruto() <= 3305.22) {
				funcionario.setDescontoInss(
						funcionario.getSalarioBruto() * TaxasInss.TAXA2.getTaxa() - TaxasInss.TAXA2.getDeducao());
				funcionario.setTaxasInss(TaxasInss.TAXA3);
			} else if (funcionario.getSalarioBruto() >= 3305.23 && funcionario.getSalarioBruto() <= 6433.57) {
				funcionario.setDescontoInss(
						funcionario.getSalarioBruto() * TaxasInss.TAXA3.getTaxa() - TaxasInss.TAXA3.getDeducao());
				funcionario.setTaxasInss(TaxasInss.TAXA4);
			} else if (funcionario.getSalarioBruto() > 6433.57) {
				funcionario.setDescontoInss(6433.57 * TaxasInss.TAXA4.getTaxa() - TaxasInss.TAXA4.getDeducao());
				funcionario.setTaxasInss(TaxasInss.TAXA4);
			} else {
				funcionario.setDescontoInss(
						funcionario.getSalarioBruto() * TaxasInss.TAXA5.getTaxa() - TaxasInss.TAXA5.getDeducao());
				funcionario.setTaxasInss(TaxasInss.TAXA5);
			}

			if (funcionario.getSalarioBruto() < 1903.98) {
				funcionario.setDescontoIR(0);
			} else if (funcionario.getSalarioBruto() >= 1903.98 && funcionario.getSalarioBruto() <= 2826.65) {
				funcionario
						.setDescontoIR(((funcionario.getSalarioBruto() - (funcionario.getDependentes().size() * 189.59)
								- funcionario.getDescontoInss()) * TaxaIR.TAXA1.getAliquota())
								- TaxaIR.TAXA1.getDeducao());
				funcionario.setTaxaIR(TaxaIR.TAXA1);
			} else if (funcionario.getSalarioBruto() >= 2826.66 && funcionario.getSalarioBruto() <= 3751.05) {
				funcionario
						.setDescontoIR(((funcionario.getSalarioBruto() - (funcionario.getDependentes().size() * 189.59)
								- funcionario.getDescontoInss()) * TaxaIR.TAXA2.getAliquota())
								- TaxaIR.TAXA2.getDeducao());
				funcionario.setTaxaIR(TaxaIR.TAXA2);
			} else if (funcionario.getSalarioBruto() >= 3751.06 && funcionario.getSalarioBruto() <= 4664.68) {
				funcionario
						.setDescontoIR(((funcionario.getSalarioBruto() - (funcionario.getDependentes().size() * 189.59)
								- funcionario.getDescontoInss()) * TaxaIR.TAXA3.getAliquota())
								- TaxaIR.TAXA3.getDeducao());
				funcionario.setTaxaIR(TaxaIR.TAXA3);
			} else {
				funcionario
						.setDescontoIR(((funcionario.getSalarioBruto() - (funcionario.getDependentes().size() * 189.59)
								- funcionario.getDescontoInss()) * TaxaIR.TAXA4.getAliquota())
								- TaxaIR.TAXA4.getDeducao());
				funcionario.setTaxaIR(TaxaIR.TAXA4);
			}

			funcionario.setSalarioLiquido(
					funcionario.getSalarioBruto() - funcionario.getDescontoInss() - funcionario.getDescontoIR());
		}
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
