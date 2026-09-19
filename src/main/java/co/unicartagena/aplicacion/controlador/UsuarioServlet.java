package co.unicartagena.aplicacion.controlador;

import co.unicartagena.aplicacion.dao.UsuarioDAO;
import co.unicartagena.aplicacion.modelo.Usuario;
import co.unicartagena.aplicacion.util.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {

    private final UsuarioDAO dao = new UsuarioDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "nuevo":
                request.getRequestDispatcher("/usuario/formulario.jsp").forward(request, response);
                break;

            case "editar":
                int idEditar = Integer.parseInt(request.getParameter("id"));
                Usuario u = dao.buscarPorId(idEditar);
                request.setAttribute("usuario", u);
                request.getRequestDispatcher("/usuario/formulario.jsp").forward(request, response);
                break;

            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                dao.eliminar(idEliminar);
                response.sendRedirect("usuarios");
                break;

            default:
                List<Usuario> usuarios = dao.listar();
                request.setAttribute("usuarios", usuarios);
                request.getRequestDispatcher("/usuario/listar.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        String claveIngresada = request.getParameter("clave");

        Usuario u = new Usuario();
        u.setNombre(request.getParameter("nombre"));
        u.setCorreo(request.getParameter("correo"));
        u.setRol(request.getParameter("rol"));

        if (idParam == null || idParam.isEmpty()) {
            u.setClave(PasswordUtil.hashear(claveIngresada));
            dao.insertar(u);
        } else {
            u.setId(Integer.parseInt(idParam));
            if (claveIngresada != null && !claveIngresada.isEmpty()) {
                u.setClave(PasswordUtil.hashear(claveIngresada));
            } else {
                Usuario existente = dao.buscarPorId(u.getId());
                u.setClave(existente.getClave());
            }
            dao.actualizar(u);
        }
        response.sendRedirect("usuarios");
    }
}