package es.ieslavereda.proyectoServidor2025_2026.controller;

import es.ieslavereda.proyectoServidor2025_2026.repository.model.Usuario;
import es.ieslavereda.proyectoServidor2025_2026.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> getAll() {
        return usuarioService.getAll();
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(
            @RequestParam String identifier,
            @RequestParam String password) {

        return usuarioService.getByIdentifier(identifier)
                .map(usuario -> {

                    if (usuario.getPasswordHash().equals(password)) {
                        return ResponseEntity.ok(usuario);
                    } else {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).<Usuario>build();
                    }
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/password")
    public ResponseEntity<Usuario> changePassword(
            @RequestParam String identifier,
            @RequestParam String newPassword) {

        return usuarioService.getByIdentifier(identifier)
                .map(usuario -> {
                    usuario.setPasswordHash(newPassword);

                    usuarioService.update(usuario.getId(), usuario);
                    return ResponseEntity.ok(usuario);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id) {
        return usuarioService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Usuario usuario) {

        if (usuarioService.getByIdentifier(usuario.getNombre()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El nombre de usuario ya está en uso");
        }

        if (usuarioService.getByIdentifier(usuario.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El email ya está registrado");
        }

        return ResponseEntity.ok(usuarioService.create(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.update(id, usuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if(usuarioService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}