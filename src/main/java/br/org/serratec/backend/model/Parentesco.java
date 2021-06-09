package br.org.serratec.backend.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.org.serratec.backend.exception.EnumValidationException;

public enum Parentesco {
	FILHO, PRIMO, OUTROS;
	
	@JsonCreator
	public static Parentesco verifica(String value) throws EnumValidationException {
		for (Parentesco parentesco : values()) {
			if (value.equals(parentesco.name())) {
				return parentesco;
			}
		}
		throw new EnumValidationException("Parentesco preechido incorretamente");
	}
}