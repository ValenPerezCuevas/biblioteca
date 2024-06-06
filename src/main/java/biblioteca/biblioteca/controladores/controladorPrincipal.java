package biblioteca.biblioteca.controladores;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controladorPrincipal {
    @GetMapping("/")
    public String inicio(HttpServletRequest request, Model model) {
        model.addAttribute("requestURI", request.getRequestURI());
        return "home";
    }
}
