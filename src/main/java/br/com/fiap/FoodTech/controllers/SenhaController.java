package br.com.fiap.FoodTech.controllers;

import br.com.fiap.FoodTech.dtos.AtualizarSenhaDTO;
import br.com.fiap.FoodTech.services.SenhaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/senhas")
public class SenhaController {

    private static final Logger logger =
            LoggerFactory.getLogger(SenhaController.class);

    private final SenhaService senhaService;

    public SenhaController(SenhaService senhaService) {
        this.senhaService = senhaService;
    }

    @PutMapping
    public ResponseEntity<Void> updateSenha(
            @Valid @RequestBody AtualizarSenhaDTO senhaDto
    ) {
        logger.info("PUT /v1/senhas");

        senhaService.updateSenha(senhaDto);

        return ResponseEntity.noContent().build(); // 204
    }
}
