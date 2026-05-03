package es.ieslavereda.proyectoServidor2025_2026.service;

import es.ieslavereda.proyectoServidor2025_2026.repository.ListaCompraRepository;
import es.ieslavereda.proyectoServidor2025_2026.repository.model.ListaCompra;
import es.ieslavereda.proyectoServidor2025_2026.repository.model.ListaCompraId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ListaCompraService {

    private final ListaCompraRepository repository;

    public ListaCompraService(ListaCompraRepository repository) {
        this.repository = repository;
    }

    public List<ListaCompra> getByUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    @Transactional
    public ListaCompra addItem(ListaCompra item) {
        return repository.save(item);
    }

    @Transactional
    public void markAsComprado(Long usuarioId, Long ingredienteId, boolean comprado) {
        repository.findByUsuarioIdAndIngredienteId(usuarioId, ingredienteId)
                .ifPresent(item -> {
                    item.setComprado(comprado);
                    repository.save(item);
                });
    }

    @Transactional
    public void removeItem(Long usuarioId, Long ingredienteId) {
        repository.deleteByUsuarioIdAndIngredienteId(usuarioId, ingredienteId);
    }
}

