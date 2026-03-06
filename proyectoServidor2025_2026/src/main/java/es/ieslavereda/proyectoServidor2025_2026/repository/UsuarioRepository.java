package es.ieslavereda.proyectoServidor2025_2026.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import es.ieslavereda.proyectoServidor2025_2026.repository.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
}