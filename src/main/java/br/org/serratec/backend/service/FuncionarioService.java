package br.org.serratec.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.model.Funcionario;
import br.org.serratec.backend.repository.FuncionarioRepository;
//kkkkkkkkkkkkkkkkkkkk
@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	public Funcionario inserir(Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}

	public List<Funcionario> inserirTodos(List<Funcionario> funcionarios) {
		return funcionarioRepository.saveAll(funcionarios);
	}
}
