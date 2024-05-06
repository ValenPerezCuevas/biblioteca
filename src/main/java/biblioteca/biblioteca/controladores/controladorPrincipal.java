package biblioteca.biblioteca.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controladorPrincipal {
    @GetMapping("/")
    public String inicio(){
        return "home";
    }

    @GetMapping("/admin")
    public String adminitrar(){
        return "admin";
    }
//    @GetMapping("/usuarios")
//    public String usuarios(){ return "usuarios";}


    @GetMapping("/listar")
    public String libros(){ return "listar";}


}
