package es.ieslavereda.proyectoServidor2025_2026.service;

import es.ieslavereda.proyectoServidor2025_2026.repository.UsuarioRepository;
import es.ieslavereda.proyectoServidor2025_2026.repository.model.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> getById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Transactional
    public Usuario create(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Optional<Usuario> update(Long id, Usuario usuarioData) {
        return usuarioRepository.findById(id).map(u -> {

            u.setNombre(usuarioData.getNombre());
            u.setEmail(usuarioData.getEmail());

            if (usuarioData.getPasswordHash() != null) {
                u.setPasswordHash(usuarioData.getPasswordHash());
            }

            u.getAlergias().clear();

            if (usuarioData.getAlergias() != null) {
                u.getAlergias().addAll(usuarioData.getAlergias());
            }

            return usuarioRepository.save(u);
        });
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> getByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> getByIdentifier(String identifier) {
        return usuarioRepository.findByEmailOrNombre(identifier, identifier);
    }

    @Transactional
    public boolean delete(Long id) {
        if(usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}