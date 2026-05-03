package es.ieslavereda.proyectoServidor2025_2026.repository.model;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoritoId implements Serializable {
    private Long usuario;
    private Long receta;
}
