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

@WebServlet("/reporteAplicaciones")
public class ReporteAplicacionServlet extends HttpServlet {

    private final AplicacionDAO dao = new AplicacionDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String categoria = request.getParameter("categoria");
        String precioMinStr = request.getParameter("precioMin");
        String precioMaxStr = request.getParameter("precioMax");
        String so = request.getParameter("sistemaOperativo");
        String requiereRedStr = request.getParameter("requiereConexionRed");

        List<Aplicacion> resultado;

        if (categoria != null && !categoria.isEmpty()
                && precioMinStr != null && !precioMinStr.isEmpty()
                && precioMaxStr != null && !precioMaxStr.isEmpty()) {
            double precioMin = Double.parseDouble(precioMinStr);
            double precioMax = Double.parseDouble(precioMaxStr);
            resultado = dao.filtrarPorCategoriaYPrecio(categoria, precioMin, precioMax);
        } else if (so != null && !so.isEmpty() && requiereRedStr != null) {
            boolean requiereRed = Boolean.parseBoolean(requiereRedStr);
            resultado = dao.filtrarPorSOYConexion(so, requiereRed);
        } else {
            resultado = dao.listar();
        }

        request.setAttribute("aplicaciones", resultado);
        request.getRequestDispatcher("/aplicacion/reporte.jsp").forward(request, response);
    }
}