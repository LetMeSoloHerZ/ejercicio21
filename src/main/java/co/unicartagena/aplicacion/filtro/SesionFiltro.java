package co.unicartagena.aplicacion.filtro;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class SesionFiltro implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String uri = request.getRequestURI();
        boolean esPublica = uri.endsWith("/login")
                || uri.endsWith("/login.jsp")
                || uri.endsWith(".ico")
                || uri.endsWith(".css")
                || uri.endsWith(".js");

        HttpSession session = request.getSession(false);
        boolean autenticado = (session != null && session.getAttribute("usuario") != null);

        if (esPublica || autenticado) {
            chain.doFilter(req, res);
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}