package es.ieslavereda.proyectoServidor2025_2026.repository.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    private String imagenUrl;

    @ManyToMany
    @JoinTable(
            name = "recetas_ingredientes",
            joinColumns = @JoinColumn(name = "receta_id"),
            inverseJoinColumns = @JoinColumn(name = "ingrediente_id")
    )
    @Builder.Default // Para que el builder no deje la lista en null
    private List<Ingrediente> ingredientes = new ArrayList<>();
}
