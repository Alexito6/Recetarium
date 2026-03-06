package es.ieslavereda.proyectoServidor2025_2026.controller;

import es.ieslavereda.proyectoServidor2025_2026.repository.model.Favorito;
import es.ieslavereda.proyectoServidor2025_2026.service.FavoritosService;
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
    public void addFavorito(@PathVariable Long usuarioId, @PathVariable Long recetaId) {
        favoritosService.addFavorito(usuarioId, recetaId);
    }

    @DeleteMapping("/{usuarioId}/{recetaId}")
    public void removeFavorito(@PathVariable Long usuarioId, @PathVariable Long recetaId) {
        favoritosService.removeFavorito(usuarioId, recetaId);
    }

    @GetMapping("/{usuarioId}")
    public List<Favorito> getFavoritos(@PathVariable Long usuarioId) {
        return favoritosService.getFavoritosByUsuario(usuarioId);
    }
}


