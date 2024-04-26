package biblioteca.biblioteca.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controladorPrincipal {
    @GetMapping("/inicio")
    public String inicio(){
        return "home";
    }



}
