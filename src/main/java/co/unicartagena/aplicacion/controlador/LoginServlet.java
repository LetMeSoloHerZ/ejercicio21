package co.unicartagena.aplicacion.controlador;

import co.unicartagena.aplicacion.dao.UsuarioDAO;
import co.unicartagena.aplicacion.modelo.Usuario;
import co.unicartagena.aplicacion.util.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UsuarioDAO dao = new UsuarioDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String correo = request.getParameter("correo");
        String clave = request.getParameter("clave");
        String claveHash = PasswordUtil.hashear(clave);

        Usuario u = dao.autenticar(correo, claveHash);

        if (u != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", u);
            session.setAttribute("rol", u.getRol());
            response.sendRedirect("usuarios");
        } else {
            request.setAttribute("error", "Correo o clave incorrectos");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}