package biblioteca.biblioteca.servicios;

import biblioteca.biblioteca.entidades.usuarios;
import biblioteca.biblioteca.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public usuarios findByNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre);
    }

    public usuarios save(usuarios usuario) {
        return usuarioRepository.save(usuario);
    }
}
