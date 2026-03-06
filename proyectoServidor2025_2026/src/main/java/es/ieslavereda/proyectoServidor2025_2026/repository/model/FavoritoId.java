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

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof FavoritoId)) return false;
        FavoritoId that = (FavoritoId) o;
        return Objects.equals(usuario, that.usuario) &&
                Objects.equals(receta, that.receta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, receta);
    }
}
