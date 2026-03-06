package es.ieslavereda.proyectoServidor2025_2026.repository;

import es.ieslavereda.proyectoServidor2025_2026.repository.model.Receta;
import es.ieslavereda.proyectoServidor2025_2026.repository.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import es.ieslavereda.proyectoServidor2025_2026.repository.model.Favorito;
import es.ieslavereda.proyectoServidor2025_2026.repository.model.FavoritoId;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, FavoritoId> {
    List<Favorito> findByUsuario(Usuario usuario);
    void deleteByUsuarioAndReceta(Usuario usuario, Receta receta);
}

