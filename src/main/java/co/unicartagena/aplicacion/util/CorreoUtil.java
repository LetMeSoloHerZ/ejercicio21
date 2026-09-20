package co.unicartagena.aplicacion.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class CorreoUtil {

    public static void enviarCorreo(String destinatario, String asunto, String cuerpo) throws MessagingException {
        String usuario = System.getenv("GMAIL_USER");
        String claveApp = System.getenv("GMAIL_APP_PASSWORD");

        if (usuario == null || claveApp == null) {
            throw new IllegalStateException("Faltan las variables de entorno GMAIL_USER o GMAIL_APP_PASSWORD");
        }

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, claveApp);
            }
        });

        Message mensaje = new MimeMessage(session);
        mensaje.setFrom(new InternetAddress(usuario));
        mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
        mensaje.setSubject(asunto);
        mensaje.setText(cuerpo);

        Transport.send(mensaje);
    }
}