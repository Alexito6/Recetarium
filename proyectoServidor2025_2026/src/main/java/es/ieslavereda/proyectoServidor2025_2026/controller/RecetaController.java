package es.ieslavereda.proyectoServidor2025_2026.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import es.ieslavereda.proyectoServidor2025_2026.repository.model.Receta;
import es.ieslavereda.proyectoServidor2025_2026.service.RecetaService;

import java.util.List;

@RestController
@RequestMapping("/api/recetas")
@CrossOrigin(origins = "*")
public class RecetaController {

    private final RecetaService recetaService;

    public RecetaController(RecetaService recetaService) {
        this.recetaService = recetaService;
    }

    @GetMapping
    public ResponseEntity<List<Receta>> getAllRecetas() {
        return ResponseEntity.ok(recetaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receta> getRecetaById(@PathVariable Long id) {
        return recetaService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Receta>> getRecetasByIngrediente(
            @RequestParam String ingrediente) {

        return ResponseEntity.ok(
                recetaService.getByIngrediente(ingrediente)
        );
    }
}

