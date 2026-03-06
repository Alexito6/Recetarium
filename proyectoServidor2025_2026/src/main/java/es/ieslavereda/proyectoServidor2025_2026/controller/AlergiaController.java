package es.ieslavereda.proyectoServidor2025_2026.controller;

import es.ieslavereda.proyectoServidor2025_2026.repository.model.Alergia;
import es.ieslavereda.proyectoServidor2025_2026.service.AlergiaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alergias")
public class AlergiaController {

    private final AlergiaService alergiaService;

    public AlergiaController(AlergiaService alergiaService) {
        this.alergiaService = alergiaService;
    }

    @GetMapping
    public List<Alergia> getAll() {
        return alergiaService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alergia> getById(@PathVariable Long id) {
        return alergiaService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Alergia create(@RequestBody Alergia alergia) {
        return alergiaService.save(alergia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alergia> update(@PathVariable Long id, @RequestBody Alergia alergia) {
        return alergiaService.getById(id)
                .map(existing -> {
                    existing.setNombre(alergia.getNombre());
                    return ResponseEntity.ok(alergiaService.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        alergiaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

