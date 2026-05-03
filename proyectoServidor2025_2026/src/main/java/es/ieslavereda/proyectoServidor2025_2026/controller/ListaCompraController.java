package es.ieslavereda.proyectoServidor2025_2026.controller;

import es.ieslavereda.proyectoServidor2025_2026.repository.model.ListaCompra;
import es.ieslavereda.proyectoServidor2025_2026.service.ListaCompraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lista-compra")
@CrossOrigin(origins = "*")
public class ListaCompraController {

    private final ListaCompraService listaCompraService;

    public ListaCompraController(ListaCompraService listaCompraService) {
        this.listaCompraService = listaCompraService;
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<ListaCompra>> getItems(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(listaCompraService.getByUsuario(usuarioId));
    }

    @PostMapping
    public ResponseEntity<ListaCompra> addItem(@RequestBody ListaCompra item) {
        return new ResponseEntity<>(listaCompraService.addItem(item), HttpStatus.CREATED);
    }

    @PatchMapping("/{usuarioId}/{ingredienteId}")
    public ResponseEntity<Void> marcarComprado(
            @PathVariable Long usuarioId,
            @PathVariable Long ingredienteId,
            @RequestParam boolean comprado) {
        listaCompraService.markAsComprado(usuarioId, ingredienteId, comprado);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{usuarioId}/{ingredienteId}")
    public ResponseEntity<Void> removeItem(@PathVariable Long usuarioId, @PathVariable Long ingredienteId) {
        listaCompraService.removeItem(usuarioId, ingredienteId);
        return ResponseEntity.noContent().build();
    }
}