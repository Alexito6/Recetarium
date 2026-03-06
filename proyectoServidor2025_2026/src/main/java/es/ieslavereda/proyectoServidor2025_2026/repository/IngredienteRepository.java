package es.ieslavereda.proyectoServidor2025_2026.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import es.ieslavereda.proyectoServidor2025_2026.repository.model.Ingrediente;

import java.util.Optional;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {

    Optional<Ingrediente> findByNombreIgnoreCase(String nombre);
}
