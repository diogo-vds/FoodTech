package br.com.fiap.FoodTech.controllers;

import br.com.fiap.FoodTech.dtos.LoginRequestDTO;
import br.com.fiap.FoodTech.services.AuthService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
public class AutenticacaoController {

    private static final Logger logger = LoggerFactory.getLogger(AutenticacaoController.class);

    private final AuthService authService;

    public AutenticacaoController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(
            @Valid @RequestBody LoginRequestDTO loginDto
    ) {
        logger.info("Tentativa de login para usuário: {}", loginDto.login());

        if (!authService.validaLogin(loginDto)) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        }

        return ResponseEntity.ok().build();
    }
}
