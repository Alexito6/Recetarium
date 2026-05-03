package es.ieslavereda.proyectoServidor2025_2026.service;

import es.ieslavereda.proyectoServidor2025_2026.repository.RecetaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import es.ieslavereda.proyectoServidor2025_2026.repository.model.Receta;

import java.util.List;
import java.util.Optional;

@Service
public class RecetaService {

    private final RecetaRepository recetaRepository;

    public RecetaService(RecetaRepository recetaRepository) {
        this.recetaRepository = recetaRepository;
    }

    @Transactional(readOnly = true)
    public List<Receta> getAll() {
        return recetaRepository.findAllWithIngredientes();
    }

    @Transactional(readOnly = true)
    public Optional<Receta> getById(Long id) {
        return recetaRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Receta> getByIngrediente(String ingrediente) {
        return recetaRepository.findByIngredientesNombreIgnoreCase(ingrediente);
    }
}