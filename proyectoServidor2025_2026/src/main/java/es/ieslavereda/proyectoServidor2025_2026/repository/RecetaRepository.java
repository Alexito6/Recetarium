package es.ieslavereda.proyectoServidor2025_2026.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import es.ieslavereda.proyectoServidor2025_2026.repository.model.Receta;
import java.util.List;

public interface RecetaRepository extends JpaRepository<Receta, Long> {

    @Query("SELECT DISTINCT r FROM Receta r " +
            "LEFT JOIN FETCH r.ingredientes i " +
            "LEFT JOIN FETCH i.alergias")
    List<Receta> findAllWithIngredientes();

    @Query("SELECT DISTINCT r FROM Receta r " +
            "JOIN FETCH r.ingredientes i " +
            "LEFT JOIN FETCH i.alergias " +
            "WHERE LOWER(i.nombre) LIKE LOWER(CONCAT('%', :nombreIngrediente, '%')) " +
            "OR LOWER(i.nombreIngles) LIKE LOWER(CONCAT('%', :nombreIngrediente, '%'))")
    List<Receta> findByIngredientesNombreContieneIgnoreCase(@Param("nombreIngrediente") String nombreIngrediente);

    @Query("SELECT DISTINCT r FROM Receta r " +
            "JOIN FETCH r.ingredientes i " +
            "LEFT JOIN FETCH i.alergias " +
            "WHERE LOWER(i.nombre) = LOWER(:nombreIngrediente) " +
            "OR LOWER(i.nombreIngles) = LOWER(:nombreIngrediente)")
    List<Receta> findByIngredientesNombreIgnoreCase(@Param("nombreIngrediente") String nombreIngrediente);
}