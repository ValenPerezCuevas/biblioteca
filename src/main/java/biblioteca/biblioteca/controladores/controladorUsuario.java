package biblioteca.biblioteca.controladores;


import biblioteca.biblioteca.entidades.libros;
import biblioteca.biblioteca.entidades.roles;
import biblioteca.biblioteca.entidades.usuarios;
import biblioteca.biblioteca.repositorios.RolesRepository;
import biblioteca.biblioteca.repositorios.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller

public class controladorUsuario {


    private final UsuarioRepository usuarioRepository;
    private final RolesRepository rolesRepository;

    @Autowired
    public controladorUsuario(UsuarioRepository usuarioRepository, RolesRepository rolesRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolesRepository = rolesRepository;
    }

    /**********************************************************************************
     * Mostrar datos de los usuarios
     * *********************************************************************************/
    @GetMapping("/usuarios")
    public String obtenerTodosLosUsuarios(
            HttpServletRequest request,
            Model model,
            @RequestParam(name = "pagina", required = false, defaultValue = "0") int pagina,
            @RequestParam(name = "tamanio", required = false, defaultValue = "10") int tamanio,
            @RequestParam(defaultValue = "rol,nombre") String sortBy,
            @RequestParam(defaultValue = "asc,asc") String sortOrder
    ) {
        // dos métodos de ordenación por defecto 1ro rol y 2do nombre:
        String[] sortProperties = sortBy.split(",");
        String[] sortDirections = sortOrder.split(",");

        Sort sort = Sort.by(
                IntStream.range(0, sortProperties.length)
                        .mapToObj(i -> new Sort.Order(
                                sortDirections[i].equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
                                sortProperties[i]
                        ))
                        .collect(Collectors.toList())
        );

        Pageable pageable = PageRequest.of(pagina, tamanio, sort);
        Page<usuarios> paginaUsuarios = usuarioRepository.findAll(pageable);
        model.addAttribute("requestURI", request.getRequestURI());
        model.addAttribute("listaUsuarios", paginaUsuarios.getContent());
        model.addAttribute("paginaActual", pagina);
        model.addAttribute("totalPaginas", paginaUsuarios.getTotalPages());
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortOrder", sortOrder);

//        model.addAttribute("listaUsuarios", usuarioRepository.findAll());
        model.addAttribute("nuevoUsuario", new usuarios());
        model.addAttribute("usuarioModificado", new usuarios());
        model.addAttribute("listaRoles", rolesRepository.findAll()); // Agregar lista de roles al modelo

        return "usuarios";
    }


    /**********************************************************************************
     * Mandar formulario de agregar usuario
     * * *********************************************************************************/

    @PostMapping("/agregarUsuario")
    public String guardarUsuario(@ModelAttribute("nuevoUsuario") usuarios usuario, @RequestParam("rol.id_rol") Long id) {
        roles rol = rolesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        usuario.setRol(rol);
        usuarioRepository.save(usuario);
        return "redirect:/usuarios";
    }

    /**********************************************************************************
     * Eliminar datos
     * * *********************************************************************************/
    @PostMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable("id") Long id) {
        usuarioRepository.deleteById(id);
        return "redirect:/usuarios";
    }

    /**********************************************************************************
     * Modificar datos
     * * *********************************************************************************/
    @GetMapping("/modificarUsuario/{id}")
    @ResponseBody
    public ResponseEntity<usuarios> mostrarFormularioDeModificar(@PathVariable("id") Long id) {
        usuarios usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/modificarUsuario")
    public String modificarUsuario(@ModelAttribute("usuarioModificado") usuarios usuarioModificado) {
        usuarioRepository.save(usuarioModificado);
        return "redirect:/usuarios";
    }


}
