package br.org.serratec.backend.model;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

@MappedSuperclass
public abstract class Pessoa {

	@Size(max = 30)
	@Column(nullable = false)
	protected String nome;
	@CPF
	protected String cpf;
	protected LocalDate dataNascimento;
	// Atributo que armazena a idade calculada com base na data de nascimento
	// No trabalho final de POO nos usavamos a classe Period do java para fazer esse
	// calculo e assim lancavamos o erro se o dependente for maior de idade
	// OBS : Ainda nao sabemos se esse atributo permanecera, vai depender de como
	// vamos fazer a validacao de idade do dependente.
	// @CaduHammes

	public Pessoa() {
		// TODO Auto-generated constructor stub
	}

	public Pessoa(@Size(max = 30) String nome, @CPF String cpf, LocalDate dataNascimento) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}

}
