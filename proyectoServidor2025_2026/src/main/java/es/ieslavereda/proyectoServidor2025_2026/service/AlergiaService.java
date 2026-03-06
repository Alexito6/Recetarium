package es.ieslavereda.proyectoServidor2025_2026.service;

import es.ieslavereda.proyectoServidor2025_2026.repository.AlergiaRepository;
import es.ieslavereda.proyectoServidor2025_2026.repository.model.Alergia;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlergiaService {

    private final AlergiaRepository alergiaRepository;

    public AlergiaService(AlergiaRepository alergiaRepository) {
        this.alergiaRepository = alergiaRepository;
    }

    public List<Alergia> getAll() {
        return alergiaRepository.findAll();
    }

    public Optional<Alergia> getById(Long id) {
        return alergiaRepository.findById(id);
    }

    public Alergia save(Alergia alergia) {
        return alergiaRepository.save(alergia);
    }

    public void delete(Long id) {
        alergiaRepository.deleteById(id);
    }
}

