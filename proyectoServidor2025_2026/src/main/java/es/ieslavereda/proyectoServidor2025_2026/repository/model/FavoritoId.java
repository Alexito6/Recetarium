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

    // Es fundamental para claves compuestas en JPA
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoritoId that = (FavoritoId) o;
        return Objects.equals(usuario, that.usuario) && Objects.equals(receta, that.receta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, receta);
    }
}