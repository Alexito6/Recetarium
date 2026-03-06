package es.ieslavereda.proyectoServidor2025_2026.repository.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "favoritos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(FavoritoId.class) // Clave compuesta usuario + receta
public class Favorito {

    @Id
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Id
    @ManyToOne
    @JoinColumn(name = "receta_id")
    private Receta receta;
}
