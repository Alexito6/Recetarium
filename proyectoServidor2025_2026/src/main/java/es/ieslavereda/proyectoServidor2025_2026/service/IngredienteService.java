package es.ieslavereda.proyectoServidor2025_2026.service;

import es.ieslavereda.proyectoServidor2025_2026.repository.IngredienteRepository;
import es.ieslavereda.proyectoServidor2025_2026.repository.model.Ingrediente;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredienteService {

    private final IngredienteRepository ingredienteRepository;

    public IngredienteService(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }

    public List<Ingrediente> getAll() {
        return ingredienteRepository.findAll();
    }

    public Optional<Ingrediente> getById(Long id) {
        return ingredienteRepository.findById(id);
    }

    public Ingrediente create(Ingrediente ingrediente) {
        return ingredienteRepository.save(ingrediente);
    }

    public Optional<Ingrediente> update(Long id, Ingrediente ingrediente) {
        return ingredienteRepository.findById(id).map(i -> {
            i.setNombre(ingrediente.getNombre());
            return ingredienteRepository.save(i);
        });
    }

    public boolean delete(Long id) {
        if (ingredienteRepository.existsById(id)) {
            ingredienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
