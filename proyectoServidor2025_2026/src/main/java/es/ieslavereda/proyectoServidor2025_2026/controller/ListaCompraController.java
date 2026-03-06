package es.ieslavereda.proyectoServidor2025_2026.controller;

import es.ieslavereda.proyectoServidor2025_2026.repository.model.ListaCompra;
import es.ieslavereda.proyectoServidor2025_2026.service.ListaCompraService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lista-compra")
public class ListaCompraController {

    private final ListaCompraService listaCompraService;

    public ListaCompraController(ListaCompraService listaCompraService) {
        this.listaCompraService = listaCompraService;
    }

    @GetMapping("/{usuarioId}")
    public List<ListaCompra> getItems(@PathVariable Long usuarioId) {
        return listaCompraService.getByUsuario(usuarioId);
    }

    @PostMapping
    public ListaCompra addItem(@RequestBody ListaCompra item) {
        return listaCompraService.addItem(item);
    }

    @PutMapping("/{usuarioId}/{ingredienteId}")
    public void marcarComprado(@PathVariable Long usuarioId, @PathVariable Long ingredienteId, @RequestParam boolean comprado) {
        listaCompraService.markAsComprado(usuarioId, ingredienteId, comprado);
    }

    @DeleteMapping("/{usuarioId}/{ingredienteId}")
    public void removeItem(@PathVariable Long usuarioId, @PathVariable Long ingredienteId) {
        listaCompraService.removeItem(usuarioId, ingredienteId);
    }
}

