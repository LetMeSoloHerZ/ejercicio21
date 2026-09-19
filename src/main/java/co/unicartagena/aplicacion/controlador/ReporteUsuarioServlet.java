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

@WebServlet("/reporteUsuarios")
public class ReporteUsuarioServlet extends HttpServlet {

    private final UsuarioDAO dao = new UsuarioDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String rol = request.getParameter("rol");
        String nombre = request.getParameter("nombre");
        List<Usuario> resultado;

        if (rol != null && !rol.isEmpty()) {
            resultado = dao.listarPorRol(rol);
        } else if (nombre != null && !nombre.isEmpty()) {
            resultado = dao.buscarPorNombre(nombre);
        } else {
            resultado = dao.listar();
        }

        request.setAttribute("usuarios", resultado);
        request.getRequestDispatcher("/usuario/reporte.jsp").forward(request, response);
    }
}