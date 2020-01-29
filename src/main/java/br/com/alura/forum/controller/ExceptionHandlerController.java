package br.com.alura.forum.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import br.com.alura.forum.dto.FieldErrorOutputDto;
import br.com.alura.forum.dto.MessageCodeOutput;
import br.com.alura.forum.exception.ResourceNotFoundException;

@RestControllerAdvice
public class ExceptionHandlerController {
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ ResourceNotFoundException.class })
	public MessageCodeOutput handleNotFoundException(Exception exception, WebRequest request) {
		return MessageCodeOutput.of("Resource Not Found", HttpStatus.NOT_FOUND.value());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ AuthenticationException.class })
	public MessageCodeOutput handleBadCredentialsException(Exception exception, WebRequest request) {
		return MessageCodeOutput.of("Wrong credentials! Invalid username or password.", HttpStatus.UNAUTHORIZED.value());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<FieldErrorOutputDto> handlerValidationHandlerErros(MethodArgumentNotValidException exception) {

		return exception.getBindingResult().getFieldErrors()
				.stream()
				.map(FieldErrorOutputDto::new)
				.collect(Collectors.toList());
	}
}
