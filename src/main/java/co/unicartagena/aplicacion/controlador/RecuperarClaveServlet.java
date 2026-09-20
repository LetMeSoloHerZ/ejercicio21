package co.unicartagena.aplicacion.controlador;

import co.unicartagena.aplicacion.dao.UsuarioDAO;
import co.unicartagena.aplicacion.modelo.Usuario;
import co.unicartagena.aplicacion.util.CorreoUtil;
import co.unicartagena.aplicacion.util.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SecureRandom;

@WebServlet("/recuperarClave")
public class RecuperarClaveServlet extends HttpServlet {

    private final UsuarioDAO dao = new UsuarioDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/recuperar.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String correo = request.getParameter("correo");
        Usuario usuario = dao.buscarPorCorreo(correo);

        if (usuario == null) {
            request.setAttribute("error", "No existe ningún usuario con ese correo.");
            request.getRequestDispatcher("/recuperar.jsp").forward(request, response);
            return;
        }

        String claveTemporal = generarClaveTemporal();
        String claveHash = PasswordUtil.hashear(claveTemporal);

        boolean actualizado = dao.actualizarClavePorCorreo(correo, claveHash);

        if (!actualizado) {
            request.setAttribute("error", "No se pudo actualizar la clave. Intenta de nuevo.");
            request.getRequestDispatcher("/recuperar.jsp").forward(request, response);
            return;
        }

        try {
            CorreoUtil.enviarCorreo(
                correo,
                "Recuperación de clave - Aplicación Ejercicio 21",
                "Hola " + usuario.getNombre() + ",\n\n" +
                "Tu nueva clave temporal es: " + claveTemporal + "\n\n" +
                "Úsala para iniciar sesión. Te recomendamos cambiarla luego si tu aplicación lo permite."
            );
            request.setAttribute("mensaje", "Se envió una clave temporal a tu correo.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "La clave se actualizó, pero no se pudo enviar el correo.");
        }

        request.getRequestDispatcher("/recuperar.jsp").forward(request, response);
    }

    private String generarClaveTemporal() {
        String caracteres = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz23456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }
        return sb.toString();
    }
}