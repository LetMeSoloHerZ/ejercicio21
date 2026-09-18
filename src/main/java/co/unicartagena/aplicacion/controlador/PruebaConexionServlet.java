package co.unicartagena.aplicacion.controlador;

import co.unicartagena.aplicacion.dao.UsuarioDAO;
import co.unicartagena.aplicacion.modelo.Usuario;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/prueba-conexion")
public class PruebaConexionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> usuarios = dao.listar();

        resp.getWriter().println("<h2>Usuarios en la base de datos:</h2><ul>");
        for (Usuario u : usuarios) {
            resp.getWriter().println("<li>" + u.getId() + " - " + u.getNombre() + " - " + u.getRol() + "</li>");
        }
        resp.getWriter().println("</ul>");
    }
}