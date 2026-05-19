package es.ieslavereda.proyectoServidor2025_2026.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ingredientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "recetas")
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;

    @Column(name = "nombre_ingles")
    private String nombreIngles;

    @ManyToMany(mappedBy = "ingredientes")
    @JsonIgnore
    private Set<Receta> recetas;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "alergias_ingredientes",
            joinColumns = @JoinColumn(name = "ingrediente_id"),
            inverseJoinColumns = @JoinColumn(name = "alergia_id")
    )
    @Builder.Default
    private Set<Alergia> alergias = new LinkedHashSet<>();

    public List<Long> getAlergiasIds() {
        if (alergias == null) return null;
        return alergias.stream().map(Alergia::getId).toList();
    }
}