package com.example.dsevolution.teste.manegerExceptions;

import com.example.dsevolution.teste.manegerExceptions.defaulException.Exception;
import com.example.dsevolution.teste.services.exceptions.RegraNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ControlerAdviceException {

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<Exception> notFound(RegraNegocioException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        String msg = "não encontrado";
        String path = request.getRequestURI();
        Exception exception = new Exception(Instant.now(), status.value(), e.getMessage(), msg, path);
        return ResponseEntity.status(status).body(exception);
    }
}
