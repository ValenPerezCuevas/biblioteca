package biblioteca.biblioteca.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String[] nonAuthPages = new String[] {"/", "/login", "/registrar", "/crearUsuario"};
        String uri = request.getRequestURI();

        for (String page : nonAuthPages) {
            if (uri.equals(page)) {
                return true;
            }
        }

        if (request.getSession().getAttribute("usuario") != null) {
            return true;
        }

        response.sendRedirect("/");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // Do nothing
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // Do nothing
    }
}
