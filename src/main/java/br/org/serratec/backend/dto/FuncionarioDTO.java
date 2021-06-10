package br.org.serratec.backend.dto;

import br.org.serratec.backend.model.Funcionario;

public class FuncionarioDTO {
	private String nome;
	private String cpf;
	private double salarioLiquido;

	public FuncionarioDTO() {
		// TODO Auto-generated constructor stub
	}

	public FuncionarioDTO(String nome, String cpf, double salarioLiquido) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.salarioLiquido = salarioLiquido;
	}

	public FuncionarioDTO(Funcionario funcionario) {
		super();
		this.nome = funcionario.getNome();
		this.cpf = funcionario.getCpf();
		this.salarioLiquido = funcionario.getSalarioLiquido();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public double getSalarioLiquido() {
		return salarioLiquido;
	}

	public void setSalarioLiquido(double salarioLiquido) {
		this.salarioLiquido = salarioLiquido;
	}

}
