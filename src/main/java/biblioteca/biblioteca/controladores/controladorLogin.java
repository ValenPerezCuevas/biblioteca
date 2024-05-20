package biblioteca.biblioteca.controladores;

import biblioteca.biblioteca.entidades.usuarios;
import biblioteca.biblioteca.repositorios.RolesRepository;
import biblioteca.biblioteca.repositorios.UsuarioRepository;
import biblioteca.biblioteca.configuracion.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Controller
public class controladorLogin {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("usuarioModificado", new usuarios());
        model.addAttribute("nuevoUsuario", new usuarios());
        model.addAttribute("listaRoles", rolesRepository.findAll());
        return "login";
    }

    @PostMapping("/login")
    public String checkLogin(@ModelAttribute("usuarioModificado") usuarios usuario, Model model) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(usuario.getNombre());
            if (passwordEncoder.matches(usuario.getContrasena(), userDetails.getPassword())) {
                Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
                return "redirect:/home";
            } else {
                model.addAttribute("error", "Nombre de usuario o contraseña incorrectos");
                model.addAttribute("nuevoUsuario", new usuarios());
                model.addAttribute("listaRoles", rolesRepository.findAll());
                return "login";
            }
        } catch (UsernameNotFoundException e) {
            model.addAttribute("error", "Nombre de usuario o contraseña incorrectos");
            model.addAttribute("nuevoUsuario", new usuarios());
            model.addAttribute("listaRoles", rolesRepository.findAll());
            return "login";
        }
    }

    @PostMapping("/registrar")
    public String registrarNuevoUsuario(@ModelAttribute("nuevoUsuario") usuarios usuario, Model model) {
        if (usuarioRepository.findByNombre(usuario.getNombre()).isPresent()) {
            model.addAttribute("error", "Usuario ya existe");
            model.addAttribute("listaRoles", rolesRepository.findAll());
            model.addAttribute("usuarioModificado", new usuarios());
            return "login"; // Usamos la misma vista de login para manejar el error de registro
        }

        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        usuarioRepository.save(usuario);
        return "redirect:/login";
    }

    @GetMapping("/crearUsuario")
    public String registrarUsuario(Model model) {
        model.addAttribute("nuevoUsuario", new usuarios());
        model.addAttribute("listaRoles", rolesRepository.findAll());
        return "crearUsuario";
    }
}
