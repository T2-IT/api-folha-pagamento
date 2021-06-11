package br.org.serratec.backend.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(FuncionarioException.class)
	public ResponseEntity<Object> handleFuncionarioException (FuncionarioException funEX) { 
		FuncionarioException funcionarioException = new FuncionarioException(funEX.getMessage());
		return ResponseEntity.unprocessableEntity().body(funcionarioException);
	}
	
	@ExceptionHandler(DependenteException.class)
	public ResponseEntity<Object> handleDependenteException (DependenteException depEx) {
		DependenteException dependenteException = new DependenteException(depEx.getMessage());
		return ResponseEntity.unprocessableEntity().body(dependenteException);
	}
}
