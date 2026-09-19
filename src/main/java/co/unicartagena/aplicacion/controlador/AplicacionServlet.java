package co.unicartagena.aplicacion.controlador;

import co.unicartagena.aplicacion.dao.AplicacionDAO;
import co.unicartagena.aplicacion.modelo.Aplicacion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/aplicaciones")
public class AplicacionServlet extends HttpServlet {

    private final AplicacionDAO dao = new AplicacionDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "nuevo":
                request.getRequestDispatcher("/aplicacion/formulario.jsp").forward(request, response);
                break;

            case "editar":
                int idEditar = Integer.parseInt(request.getParameter("id"));
                Aplicacion a = dao.buscarPorId(idEditar);
                request.setAttribute("aplicacion", a);
                request.getRequestDispatcher("/aplicacion/formulario.jsp").forward(request, response);
                break;

            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                dao.eliminar(idEliminar);
                response.sendRedirect("aplicaciones");
                break;

            default:
                List<Aplicacion> aplicaciones = dao.listar();
                request.setAttribute("aplicaciones", aplicaciones);
                request.getRequestDispatcher("/aplicacion/listar.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");

        Aplicacion a = new Aplicacion();
        a.setNombre(request.getParameter("nombre"));
        a.setProveedor(request.getParameter("proveedor"));
        a.setCategoria(request.getParameter("categoria"));
        a.setLenguajePrincipal(request.getParameter("lenguajePrincipal"));
        a.setLenguajeSecundario(request.getParameter("lenguajeSecundario"));
        a.setUsaBd(request.getParameter("usaBd") != null);
        a.setRequiereConexionRed(request.getParameter("requiereConexionRed") != null);
        a.setNumBits(parseIntSeguro(request.getParameter("numBits")));
        a.setSistemaOperativo(request.getParameter("sistemaOperativo"));
        a.setRequisitosHardware(request.getParameter("requisitosHardware"));
        a.setLicencia(request.getParameter("licencia"));
        a.setPrecio(parseDoubleSeguro(request.getParameter("precio")));
        a.setDescripcion(request.getParameter("descripcion"));
        a.setWeb(request.getParameter("web"));
        a.setCorreo(request.getParameter("correo"));
        a.setTamanoInstalador(parseDoubleSeguro(request.getParameter("tamanoInstalador")));

        if (idParam == null || idParam.isEmpty()) {
            dao.insertar(a);
        } else {
            a.setId(Integer.parseInt(idParam));
            dao.actualizar(a);
        }
        response.sendRedirect("aplicaciones");
    }

    private int parseIntSeguro(String valor) {
        try { return Integer.parseInt(valor); } catch (Exception e) { return 0; }
    }

    private double parseDoubleSeguro(String valor) {
        try { return Double.parseDouble(valor); } catch (Exception e) { return 0.0; }
    }
}