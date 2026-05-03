package es.ieslavereda.proyectoServidor2025_2026.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import es.ieslavereda.proyectoServidor2025_2026.repository.model.Receta;
import java.util.List;

public interface RecetaRepository extends JpaRepository<Receta, Long> {
    @Query("SELECT DISTINCT r FROM Receta r LEFT JOIN FETCH r.ingredientes")
    List<Receta> findAllWithIngredientes();

    List<Receta> findByIngredientesNombreIgnoreCase(String nombreIngrediente);
}