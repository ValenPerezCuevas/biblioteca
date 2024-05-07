package biblioteca.biblioteca.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class controladorLogin {
    @GetMapping("/login")
    public String login(){ return "login";}

    @PostMapping("/login")
    public String Checklogin(){ return "home";}

    @GetMapping("/registrar")
    public String Registrarusuario(){ return "crearUsuario";}


}
