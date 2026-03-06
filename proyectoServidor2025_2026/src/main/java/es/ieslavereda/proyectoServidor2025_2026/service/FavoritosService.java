package es.ieslavereda.proyectoServidor2025_2026.service;

import es.ieslavereda.proyectoServidor2025_2026.repository.FavoritoRepository;
import es.ieslavereda.proyectoServidor2025_2026.repository.RecetaRepository;
import es.ieslavereda.proyectoServidor2025_2026.repository.UsuarioRepository;
import es.ieslavereda.proyectoServidor2025_2026.repository.model.Favorito;
import es.ieslavereda.proyectoServidor2025_2026.repository.model.Receta;
import es.ieslavereda.proyectoServidor2025_2026.repository.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritosService {

    private final FavoritoRepository favoritoRepository;
    private final UsuarioRepository usuarioRepository;
    private final RecetaRepository recetaRepository;

    public FavoritosService(FavoritoRepository favoritoRepository,
                            UsuarioRepository usuarioRepository,
                            RecetaRepository recetaRepository) {
        this.favoritoRepository = favoritoRepository;
        this.usuarioRepository = usuarioRepository;
        this.recetaRepository = recetaRepository;
    }

    public void addFavorito(Long usuarioId, Long recetaId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Receta receta = recetaRepository.findById(recetaId)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada"));

        Favorito favorito = Favorito.builder()
                .usuario(usuario)
                .receta(receta)
                .build();

        favoritoRepository.save(favorito);
    }

    public void removeFavorito(Long usuarioId, Long recetaId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Receta receta = recetaRepository.findById(recetaId)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada"));

        favoritoRepository.deleteByUsuarioAndReceta(usuario, receta);
    }

    public List<Favorito> getFavoritosByUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return favoritoRepository.findByUsuario(usuario);
    }
}