package co.unicartagena.aplicacion.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CorreoUtil {

    public static void enviarCorreo(String destinatario, String asunto, String cuerpo) throws IOException, InterruptedException {
        String apiKey = System.getenv("RESEND_API_KEY");

        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalStateException("Falta la variable de entorno RESEND_API_KEY");
        }

        String json = "{"
                + "\"from\": \"onboarding@resend.dev\","
                + "\"to\": [\"" + escaparJson(destinatario) + "\"],"
                + "\"subject\": \"" + escaparJson(asunto) + "\","
                + "\"text\": \"" + escaparJson(cuerpo) + "\""
                + "}";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.resend.com/emails"))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 400) {
            throw new IOException("Error al enviar correo (HTTP " + response.statusCode() + "): " + response.body());
        }
    }

    private static String escaparJson(String texto) {
        return texto
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "");
    }
}