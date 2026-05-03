package es.ieslavereda.proyectoServidor2025_2026.controller;

import es.ieslavereda.proyectoServidor2025_2026.repository.model.Favorito;
import es.ieslavereda.proyectoServidor2025_2026.service.FavoritosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favoritos")
public class FavoritosController {

    private final FavoritosService favoritosService;

    public FavoritosController(FavoritosService favoritosService) {
        this.favoritosService = favoritosService;
    }

    @PostMapping("/{usuarioId}/{recetaId}")
    public ResponseEntity<Void> addFavorito(@PathVariable Long usuarioId, @PathVariable Long recetaId) {
        favoritosService.addFavorito(usuarioId, recetaId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{usuarioId}/{recetaId}")
    public ResponseEntity<Void> removeFavorito(@PathVariable Long usuarioId, @PathVariable Long recetaId) {
        favoritosService.removeFavorito(usuarioId, recetaId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<Favorito>> getFavoritos(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(favoritosService.getFavoritosByUsuario(usuarioId));
    }
}