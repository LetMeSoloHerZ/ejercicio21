package co.unicartagena.aplicacion.util;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

public class PasswordUtil {
    public static String hashear(String claveTextoPlano) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(claveTextoPlano.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error al hashear la clave", e);
        }
    }
}