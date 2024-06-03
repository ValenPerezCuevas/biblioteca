package biblioteca.biblioteca.servicios;

import biblioteca.biblioteca.repositorios.Libros_listasRepository;
import biblioteca.biblioteca.repositorios.ListasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ListaService {

    @Autowired
    private ListasRepository listasRepository;

    @Autowired
    private Libros_listasRepository librosListasRepository;

    @Transactional
    public void eliminarLista(Integer idLista) {
        // Eliminar primero los libros_listas asociados
        librosListasRepository.deleteByListaId(idLista);
        // Luego eliminar la lista
        listasRepository.deleteByIdCustom(idLista);
    }
}

