package es.ieslavereda.proyectoServidor2025_2026.service;

import es.ieslavereda.proyectoServidor2025_2026.repository.FavoritoRepository;
import es.ieslavereda.proyectoServidor2025_2026.repository.RecetaRepository;
import es.ieslavereda.proyectoServidor2025_2026.repository.model.Favorito;
import es.ieslavereda.proyectoServidor2025_2026.repository.model.Usuario;
import es.ieslavereda.proyectoServidor2025_2026.repository.model.Receta;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecetaService {

    private final RecetaRepository recetaRepository;
    private final FavoritoRepository favoritoRepository;

    public RecetaService(RecetaRepository recetaRepository, FavoritoRepository favoritoRepository) {
        this.recetaRepository = recetaRepository;
        this.favoritoRepository = favoritoRepository;
    }

    @Transactional(readOnly = true)
    public List<Receta> getAll() {
        return recetaRepository.findAllWithIngredientes();
    }

    @Transactional(readOnly = true)
    public Optional<Receta> getById(Long id) {
        return recetaRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Receta> getByIngrediente(String ingrediente) {
        return recetaRepository.findByIngredientesNombreContieneIgnoreCase(ingrediente);
    }

    @Transactional(readOnly = true)
    public List<Receta> getAllParaUsuario(Long usuarioId) {
        List<Receta> todasLasRecetas = recetaRepository.findAllWithIngredientes();

        return marcarFavoritos(todasLasRecetas, usuarioId);
    }

    @Transactional(readOnly = true)
    public List<Receta> findByIngredienteNombre(String ingrediente, Long usuarioId) {
        List<Receta> recetasConIngrediente = recetaRepository.findByIngredientesNombreContieneIgnoreCase(ingrediente);

        return marcarFavoritos(recetasConIngrediente, usuarioId);
    }

    private List<Receta> marcarFavoritos(List<Receta> recetas, Long usuarioId) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);

        List<Favorito> favoritos = favoritoRepository.findByUsuario(usuario);

        Set<Long> idsFavoritos = favoritos.stream()
                .map(f -> f.getReceta().getId())
                .collect(Collectors.toSet());

        for (Receta receta : recetas) {
            receta.setFavorito(idsFavoritos.contains(receta.getId()));
        }

        return recetas;
    }
}