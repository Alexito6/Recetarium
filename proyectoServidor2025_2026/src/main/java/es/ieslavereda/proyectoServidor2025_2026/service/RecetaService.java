package es.ieslavereda.proyectoServidor2025_2026.service;

import es.ieslavereda.proyectoServidor2025_2026.repository.RecetaRepository;
import org.springframework.stereotype.Service;
import es.ieslavereda.proyectoServidor2025_2026.repository.model.Receta;

import java.util.List;
import java.util.Optional;

@Service
public class RecetaService {

    private final RecetaRepository recetaRepository;

    public RecetaService(RecetaRepository recetaRepository) {
        this.recetaRepository = recetaRepository;
    }

    public List<Receta> getAll() {
        return recetaRepository.findAll();
    }

    public Optional<Receta> getById(Long id) {
        return recetaRepository.findById(id);
    }

    public List<Receta> getByIngrediente(String ingrediente) {
        return recetaRepository
                .findByIngredientesNombreIgnoreCase(ingrediente);
    }
}

