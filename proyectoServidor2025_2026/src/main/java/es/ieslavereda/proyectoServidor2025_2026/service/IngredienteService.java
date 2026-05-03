package es.ieslavereda.proyectoServidor2025_2026.service;

import es.ieslavereda.proyectoServidor2025_2026.repository.IngredienteRepository;
import es.ieslavereda.proyectoServidor2025_2026.repository.model.Ingrediente;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class IngredienteService {

    private final IngredienteRepository ingredienteRepository;

    public IngredienteService(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }

    @Transactional(readOnly = true)
    public List<Ingrediente> getAll() {
        return ingredienteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Ingrediente> getById(Long id) {
        return ingredienteRepository.findById(id);
    }

    @Transactional
    public Ingrediente create(Ingrediente ingrediente) {
        return ingredienteRepository.save(ingrediente);
    }

    @Transactional
    public Optional<Ingrediente> update(Long id, Ingrediente ingrediente) {
        return ingredienteRepository.findById(id).map(existing -> {
            existing.setNombre(ingrediente.getNombre());
            return ingredienteRepository.save(existing);
        });
    }

    @Transactional
    public boolean delete(Long id) {
        return ingredienteRepository.findById(id).map(ingrediente -> {
            ingredienteRepository.delete(ingrediente);
            return true;
        }).orElse(false);
    }
}