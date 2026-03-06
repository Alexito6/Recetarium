package es.ieslavereda.proyectoServidor2025_2026.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import es.ieslavereda.proyectoServidor2025_2026.repository.model.Receta;

import java.util.List;

public interface RecetaRepository extends JpaRepository<Receta, Long> {

    List<Receta> findByIngredientesNombreIgnoreCase(String nombreIngrediente);

}
