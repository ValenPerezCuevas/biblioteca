package biblioteca.biblioteca.controladores;

import biblioteca.biblioteca.entidades.roles;
import biblioteca.biblioteca.entidades.usuarios;
import biblioteca.biblioteca.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("usuario", new usuarios());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute usuarios usuario, Model model, HttpSession session) {
        usuarios foundUsuario = usuarioRepository.findByNombre(usuario.getNombre());
        if (foundUsuario != null && foundUsuario.getContrasena().equals(usuario.getContrasena())) {
            session.setAttribute("usuario", foundUsuario);
            return "redirect:/"; //
        }
        model.addAttribute("error", "Usuario o contraseña incorrectos");
        return "login"; // Volver a la plantilla unificada en caso de error
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/registrar")
    public String registerUser(@ModelAttribute usuarios usuario, Model model) {
        if (usuarioRepository.findByNombre(usuario.getNombre()) != null) {
            model.addAttribute("errorRegistro", "El nombre de usuario ya está en uso");
            return "login";// Vuelve a la vista de login con el mensaje de error
        }
        roles rolUsuario = new roles();
        rolUsuario.setId_rol(2);
        usuario.setRol(rolUsuario);
        usuarioRepository.save(usuario);
        return "redirect:/login";
    }
}




