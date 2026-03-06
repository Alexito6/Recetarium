package es.ieslavereda.proyectoServidor2025_2026.repository.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "ingredientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;

    // Relación con recetas
    @ManyToMany(mappedBy = "ingredientes")
    private List<Receta> recetas;
}
