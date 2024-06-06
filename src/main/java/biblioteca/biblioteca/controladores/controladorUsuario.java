package biblioteca.biblioteca.controladores;

import biblioteca.biblioteca.entidades.listas;
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

import java.util.ArrayList;
import java.util.List;
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

    @GetMapping("/usuarios")
    public String obtenerTodosLosUsuarios(
            HttpServletRequest request,
            Model model,
            @RequestParam(name = "pagina", required = false, defaultValue = "0") int pagina,
            @RequestParam(name = "tamanio", required = false, defaultValue = "10") int tamanio,
            @RequestParam(defaultValue = "rol,nombre") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        String[] sortProperties = sortBy.split(",");
        List<Sort.Order> orders = new ArrayList<>();
        for (String property : sortProperties) {
            Sort.Direction direction =
                    sortOrder.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
            orders.add(new Sort.Order(direction, property));
        }
        Sort sort = Sort.by(orders);
        Pageable pageable = PageRequest.of(pagina, tamanio, sort);
        Page<usuarios> paginaUsuarios = usuarioRepository.findAll(pageable);

        model.addAttribute("requestURI", request.getRequestURI());
        model.addAttribute("listaUsuarios", paginaUsuarios.getContent());
        model.addAttribute("paginaActual", pagina);
        model.addAttribute("totalPaginas", paginaUsuarios.getTotalPages());
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortOrder", sortOrder);

        model.addAttribute("nuevoUsuario", new usuarios());
        model.addAttribute("usuarioModificado", new usuarios());
        model.addAttribute("listaRoles", rolesRepository.findAll());

        return "usuarios";
    }

    @PostMapping("/agregarUsuario")
    public String guardarUsuario(@ModelAttribute("nuevoUsuario") usuarios usuario, @RequestParam("rol.id_rol") Long id) {
        roles rol = rolesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        usuario.setRol(rol);
        usuarioRepository.save(usuario);
        return "redirect:/usuarios";
    }

    @PostMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable("id") Long id) {
        usuarioRepository.deleteById(id);
        return "redirect:/usuarios";
    }

    @GetMapping("/modificarUsuario/{id}")
    @ResponseBody
    public ResponseEntity<usuarios> mostrarFormularioDeModificar(@PathVariable("id") Long id) {
        usuarios usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/modificarUsuario")
    public String modificarUsuario(@ModelAttribute("usuarioModificado") usuarios usuarioModificado) {
        usuarios usuarioExistente = usuarioRepository.findById(Long.valueOf(usuarioModificado.getId_usuario()))
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Actualizar campos básicos
        usuarioExistente.setNombre(usuarioModificado.getNombre());
        usuarioExistente.setContrasena(usuarioModificado.getContrasena());

        // Gestionar la relación con roles
        roles rol = rolesRepository.findById((long) usuarioModificado.getRol().getId_rol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        usuarioExistente.setRol(rol);

        // Gestionar listas: actualizar la colección
        List<listas> listasNuevas = usuarioModificado.getListas();
        if (listasNuevas != null) {
            // Eliminar listas huérfanas
            usuarioExistente.getListas().clear();
            // Asignar el usuario a las nuevas listas y añadirlas a la colección existente
            for (listas lista : listasNuevas) {
                lista.setUsuario(usuarioExistente);
                usuarioExistente.getListas().add(lista);
            }
        }

        usuarioRepository.save(usuarioExistente);
        return "redirect:/usuarios";
    }

}
