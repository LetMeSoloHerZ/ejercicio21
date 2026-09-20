package co.unicartagena.aplicacion.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String HOST = System.getenv().getOrDefault("DB_HOST", "localhost");
    private static final String PORT = System.getenv().getOrDefault("DB_PORT", "3306");
    private static final String NOMBRE_BD = System.getenv().getOrDefault("DB_NAME", "ejercicio21");
    private static final String USUARIO = System.getenv().getOrDefault("DB_USER", "root");
    private static final String CLAVE = System.getenv().getOrDefault("DB_PASSWORD", "3166195789");

    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + NOMBRE_BD
            + "?useSSL=false&allowPublicKeyRetrieval=true";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }
}