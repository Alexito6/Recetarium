package es.ieslavereda.proyectoServidor2025_2026.controller;

import es.ieslavereda.proyectoServidor2025_2026.repository.model.Ingrediente;
import es.ieslavereda.proyectoServidor2025_2026.service.IngredienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredientes")
@CrossOrigin(origins = "*")
public class IngredienteController {

    private final IngredienteService ingredienteService;

    public IngredienteController(IngredienteService ingredienteService) {
        this.ingredienteService = ingredienteService;
    }

    @GetMapping
    public List<Ingrediente> getAll() {
        return ingredienteService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingrediente> getById(@PathVariable Long id) {
        return ingredienteService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Ingrediente create(@RequestBody Ingrediente ingrediente) {
        return ingredienteService.create(ingrediente);
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
