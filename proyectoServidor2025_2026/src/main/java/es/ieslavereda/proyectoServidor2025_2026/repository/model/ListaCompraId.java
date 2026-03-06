package es.ieslavereda.proyectoServidor2025_2026.repository.model;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListaCompraId implements Serializable {
    private Long usuario;
    private Long ingrediente;

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof ListaCompraId)) return false;
        ListaCompraId that = (ListaCompraId) o;
        return Objects.equals(usuario, that.usuario) &&
                Objects.equals(ingrediente, that.ingrediente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, ingrediente);
    }
}

