package es.ieslavereda.proyectoServidor2025_2026.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "alergias")
@Getter // Cambia @Data por Getter/Setter/ToString para evitar problemas con JPA
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "ingredientes") // Evita bucles en los logs
public class Alergia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;

    @ManyToMany
    @JoinTable(
            name = "alergias_ingredientes",
            joinColumns = @JoinColumn(name = "alergia_id"),
            inverseJoinColumns = @JoinColumn(name = "ingrediente_id")
    )
    @JsonIgnoreProperties("alergias")
    private List<Ingrediente> ingredientes;
}