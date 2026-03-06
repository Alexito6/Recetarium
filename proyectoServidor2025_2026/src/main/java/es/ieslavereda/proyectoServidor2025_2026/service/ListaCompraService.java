package es.ieslavereda.proyectoServidor2025_2026.service;

import es.ieslavereda.proyectoServidor2025_2026.repository.ListaCompraRepository;
import es.ieslavereda.proyectoServidor2025_2026.repository.model.ListaCompra;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaCompraService {

    private final ListaCompraRepository listaCompraRepository;

    public ListaCompraService(ListaCompraRepository listaCompraRepository) {
        this.listaCompraRepository = listaCompraRepository;
    }

    public List<ListaCompra> getByUsuario(Long usuarioId) {
        return listaCompraRepository.findByUsuarioId(usuarioId);
    }

    public ListaCompra addItem(ListaCompra item) {
        return listaCompraRepository.save(item);
    }

    public void markAsComprado(Long usuarioId, Long ingredienteId, boolean comprado) {
        ListaCompra item = listaCompraRepository.findByUsuarioIdAndIngredienteId(usuarioId, ingredienteId);
        if (item != null) {
            item.setComprado(comprado);
            listaCompraRepository.save(item);
        }
    }

    public void removeItem(Long usuarioId, Long ingredienteId) {
        listaCompraRepository.deleteByUsuarioIdAndIngredienteId(usuarioId, ingredienteId);
    }
}

