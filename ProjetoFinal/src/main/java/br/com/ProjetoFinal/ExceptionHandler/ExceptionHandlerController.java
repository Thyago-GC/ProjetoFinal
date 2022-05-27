package br.com.ProjetoFinal.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(NotFoundException.class) // 404
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<MensagemErro> resourceNotFoundException(NotFoundException ex, WebRequest request) {
		MensagemErro mensagem = new MensagemErro(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		return new ResponseEntity<MensagemErro>(mensagem, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class) // 500
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<MensagemErro> globalExceptionHandler(Exception ex, WebRequest request) {
		MensagemErro mensagem = new MensagemErro(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		return new ResponseEntity<MensagemErro>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}