package es.ieslavereda.proyectoServidor2025_2026.controller;

import es.ieslavereda.proyectoServidor2025_2026.repository.model.Ingrediente;
import es.ieslavereda.proyectoServidor2025_2026.service.IngredienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredientes")
// Nota: cambia "*" por el dominio del frontend, ej: "http://localhost:4200"
@CrossOrigin(origins = "*")
public class IngredienteController {

    private final IngredienteService ingredienteService;

    public IngredienteController(IngredienteService ingredienteService) {
        this.ingredienteService = ingredienteService;
    }

    @GetMapping
    public ResponseEntity<List<Ingrediente>> getAll() {
        List<Ingrediente> ingredientes = ingredienteService.getAll();
        return ResponseEntity.ok(ingredientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingrediente> getById(@PathVariable Long id) {
        return ingredienteService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Ingrediente> create(@RequestBody Ingrediente ingrediente) {
        // Validar que el nombre no sea nulo o vacío
        if (ingrediente.getNombre() == null || ingrediente.getNombre().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        Ingrediente creado = ingredienteService.create(ingrediente);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingrediente> update(@PathVariable Long id, @RequestBody Ingrediente ingrediente) {
        return ingredienteService.update(id, ingrediente)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (ingredienteService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}