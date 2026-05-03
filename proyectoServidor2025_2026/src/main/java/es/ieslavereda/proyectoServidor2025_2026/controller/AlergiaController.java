package es.ieslavereda.proyectoServidor2025_2026.controller;

import es.ieslavereda.proyectoServidor2025_2026.repository.model.Alergia;
import es.ieslavereda.proyectoServidor2025_2026.service.AlergiaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Alergia> create(@RequestBody Alergia alergia) {
        // Devolvemos 201 Created para creaciones exitosas
        return new ResponseEntity<>(alergiaService.save(alergia), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alergia> update(@PathVariable Long id, @RequestBody Alergia alergia) {
        Optional<Alergia> updated = alergiaService.update(id, alergia);
        return updated.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (alergiaService.getById(id).isPresent()) {
            alergiaService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}