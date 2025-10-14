package br.edu.infnet.hanielapi.controller.handler;

import br.edu.infnet.hanielapi.exception.AtivoInvalidoException;
import br.edu.infnet.hanielapi.exception.AtivoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AtivoNaoEncontradoException.class)
    public ResponseEntity<String> handleAtivoNaoEncontrado(AtivoNaoEncontradoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AtivoInvalidoException.class)
    public ResponseEntity<String> handleAtivoInvalido(AtivoInvalidoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}