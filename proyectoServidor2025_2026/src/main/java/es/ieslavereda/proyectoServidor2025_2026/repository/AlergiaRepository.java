package es.ieslavereda.proyectoServidor2025_2026.repository;

import es.ieslavereda.proyectoServidor2025_2026.repository.model.Alergia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlergiaRepository extends JpaRepository<Alergia,Long> {

}
