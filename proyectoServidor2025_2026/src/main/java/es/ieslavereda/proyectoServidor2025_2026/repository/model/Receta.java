package es.ieslavereda.proyectoServidor2025_2026.repository.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "recetas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "ingredientes")
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(columnDefinition = "TEXT")
    private String instrucciones;

    @Column(name = "imagen_url")
    @JsonProperty("imagen_url")
    private String imagenUrl;

    @Transient
    private boolean isFavorito;

    private int dificultad;

    private int duracion;

    @ManyToMany
    @JoinTable(
            name = "recetas_ingredientes",
            joinColumns = @JoinColumn(name = "receta_id"),
            inverseJoinColumns = @JoinColumn(name = "ingrediente_id")
    )
    @Builder.Default
    private Set<Ingrediente> ingredientes = new LinkedHashSet<>();
}