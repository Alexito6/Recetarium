package es.ieslavereda.proyectoServidor2025_2026.controller;

import es.ieslavereda.proyectoServidor2025_2026.repository.FavoritoRepository;
import es.ieslavereda.proyectoServidor2025_2026.repository.model.Favorito;
import es.ieslavereda.proyectoServidor2025_2026.repository.model.FavoritoId;
import es.ieslavereda.proyectoServidor2025_2026.repository.model.Receta;
import es.ieslavereda.proyectoServidor2025_2026.repository.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/favoritos")
@CrossOrigin(origins = "*")
public class FavoritoController {

    private final FavoritoRepository favoritoRepository;

    public FavoritoController(FavoritoRepository favoritoRepository) {
        this.favoritoRepository = favoritoRepository;
    }

    @PostMapping
    public ResponseEntity<?> guardarFavorito(@RequestBody Map<String, Long> datos) {
        try {
            Long usuarioId = datos.get("usuario_id");
            Long recetaId = datos.get("receta_id");

            Usuario u = new Usuario();
            u.setId(usuarioId);

            Receta r = new Receta();
            r.setId(recetaId);

            Favorito favorito = Favorito.builder()
                    .usuario(u)
                    .receta(r)
                    .build();

            favoritoRepository.save(favorito);

            return ResponseEntity.ok().body("{\"status\":\"ok\"}");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @DeleteMapping("/usuario/{usuarioId}/receta/{recetaId}")
    public ResponseEntity<?> eliminarFavorito(
            @PathVariable Long usuarioId,
            @PathVariable Long recetaId) {
        try {

            FavoritoId id = new FavoritoId(usuarioId, recetaId);

            if (favoritoRepository.existsById(id)) {
                favoritoRepository.deleteById(id);
                return ResponseEntity.ok().body("{\"status\":\"deleted\"}");
            }

            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}