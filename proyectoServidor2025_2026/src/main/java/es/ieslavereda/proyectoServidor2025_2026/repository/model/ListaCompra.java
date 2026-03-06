package es.ieslavereda.proyectoServidor2025_2026.repository.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lista_compra")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(ListaCompraId.class) // clave compuesta usuario + ingrediente
public class ListaCompra {

    @Id
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Id
    @ManyToOne
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;

    private boolean comprado;
}

