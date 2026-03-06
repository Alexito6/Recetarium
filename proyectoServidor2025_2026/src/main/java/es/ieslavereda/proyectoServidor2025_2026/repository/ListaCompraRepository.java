package es.ieslavereda.proyectoServidor2025_2026.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import es.ieslavereda.proyectoServidor2025_2026.repository.model.ListaCompra;

import java.util.List;
import java.util.Optional;

public interface ListaCompraRepository extends JpaRepository<ListaCompra, Long> {

    List<ListaCompra> findByUsuarioId(Long usuarioId);
    ListaCompra findByUsuarioIdAndIngredienteId(Long usuarioId, Long ingredienteId);
    void deleteByUsuarioIdAndIngredienteId(Long usuarioId, Long ingredienteId);

}

