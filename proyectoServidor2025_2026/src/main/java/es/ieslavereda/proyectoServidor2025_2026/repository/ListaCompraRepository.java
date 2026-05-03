package es.ieslavereda.proyectoServidor2025_2026.repository;

import es.ieslavereda.proyectoServidor2025_2026.repository.model.ListaCompra;
import es.ieslavereda.proyectoServidor2025_2026.repository.model.ListaCompraId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ListaCompraRepository extends JpaRepository<ListaCompra, ListaCompraId> {

    List<ListaCompra> findByUsuarioId(Long usuarioId);

    Optional<ListaCompra> findByUsuarioIdAndIngredienteId(Long usuarioId, Long ingredienteId);

    void deleteByUsuarioIdAndIngredienteId(Long usuarioId, Long ingredienteId);
}