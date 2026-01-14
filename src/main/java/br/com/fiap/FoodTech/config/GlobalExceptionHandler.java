package br.com.fiap.FoodTech.config;

import br.com.fiap.FoodTech.exceptions.EmailAlreadyExistsException;
import br.com.fiap.FoodTech.exceptions.LoginInvalidoException;
import br.com.fiap.FoodTech.exceptions.SenhaInvalidaException;
import br.com.fiap.FoodTech.exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // VALIDAÇÃO DE CAMPOS
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleInvalidArguments(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        logger.warn("Erro de validação: {}", ex.getMessage());

        ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        detail.setTitle("Erro de validação");
        detail.setType(URI.create("https://foodtech.com/errors/validation"));
        detail.setInstance(URI.create(request.getRequestURI()));

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));

        detail.setProperty("errors", errors);
        detail.setProperty("timestamp", Instant.now());

        return detail;
    }

    // EMAIL DUPLICADO
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ProblemDetail handleEmailAlreadyExists(
            EmailAlreadyExistsException ex,
            HttpServletRequest request
    ) {
        logger.warn("E-mail já existente: {}", ex.getMessage());

        ProblemDetail detail =
                ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());

        detail.setTitle("E-mail já cadastrado");
        detail.setType(URI.create("https://foodtech.com/errors/email-already-exists"));
        detail.setInstance(URI.create(request.getRequestURI()));
        detail.setProperty("timestamp", Instant.now());

        return detail;
    }

    // USUÁRIO NÃO ENCONTRADO
    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail handleUserNotFound(
            UserNotFoundException ex,
            HttpServletRequest request
    ) {
        logger.warn("Usuário não encontrado: {}", ex.getMessage());

        ProblemDetail detail =
                ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());

        detail.setTitle("Usuário não encontrado");
        detail.setType(URI.create("https://foodtech.com/errors/user-not-found"));
        detail.setInstance(URI.create(request.getRequestURI()));
        detail.setProperty("timestamp", Instant.now());

        return detail;
    }

    // LOGIN INVÁLIDO
    @ExceptionHandler(LoginInvalidoException.class)
    public ProblemDetail handleLoginInvalido(
            LoginInvalidoException ex,
            HttpServletRequest request
    ) {
        logger.warn("Login inválido: {}", ex.getMessage());

        ProblemDetail detail =
                ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());

        detail.setTitle("Falha na autenticação");
        detail.setType(URI.create("https://foodtech.com/errors/invalid-login"));
        detail.setInstance(URI.create(request.getRequestURI()));
        detail.setProperty("timestamp", Instant.now());

        return detail;
    }

    // SENHA INVÁLIDA
    @ExceptionHandler(SenhaInvalidaException.class)
    public ResponseEntity<String> handleSenhaInvalida(
            SenhaInvalidaException ex
    ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }


    // ERRO GENÉRICO
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGeneric(
            Exception ex,
            HttpServletRequest request
    ) {
        logger.error("Erro inesperado", ex);

        ProblemDetail detail =
                ProblemDetail.forStatusAndDetail(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Ocorreu um erro inesperado. Tente novamente mais tarde."
                );

        detail.setTitle("Erro interno");
        detail.setType(URI.create("https://foodtech.com/errors/internal"));
        detail.setInstance(URI.create(request.getRequestURI()));
        detail.setProperty("timestamp", Instant.now());

        return detail;
    }
}