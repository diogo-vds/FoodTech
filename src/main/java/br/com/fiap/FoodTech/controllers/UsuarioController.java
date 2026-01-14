package br.com.fiap.FoodTech.controllers;

import br.com.fiap.FoodTech.dtos.UsuarioCreateDTO;
import br.com.fiap.FoodTech.dtos.UsuarioUpdateDTO;
import br.com.fiap.FoodTech.services.UsuarioService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {

        logger.info("GET /v1/usuarios/{}", id);

        var usuario = usuarioService.findById(id);

        return usuario
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> findUsuarios(
            @RequestParam(required = false) String nome
    ) {
        logger.info("GET /v1/usuarios?nome={}", nome);

        var usuarios = usuarioService.findUsersByName(nome);

        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<Void> saveUsuario(@Valid @RequestBody UsuarioCreateDTO dto) {

        logger.info("POST /v1/usuarios");

        usuarioService.saveUsuario(dto);

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUsuario(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioUpdateDTO usuarioDto
    ) {
        logger.info("PUT /v1/usuarios/{}", id);

        usuarioService.updateUsuario(id, usuarioDto);

        return ResponseEntity.noContent().build(); // 204
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {

        logger.info("DELETE /v1/usuarios/{}", id);

        usuarioService.deleteUsuario(id);

        return ResponseEntity.noContent().build(); // 204
    }
}
