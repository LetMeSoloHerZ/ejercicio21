package co.unicartagena.aplicacion.dao;

import co.unicartagena.aplicacion.modelo.Aplicacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AplicacionDAO {

    public List<Aplicacion> listar() {
        List<Aplicacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM aplicacion";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Aplicacion buscarPorId(int id) {
        String sql = "SELECT * FROM aplicacion WHERE id = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertar(Aplicacion a) {
        String sql = "INSERT INTO aplicacion (nombre, proveedor, categoria, lenguaje_principal, " +
                "lenguaje_secundario, usa_bd, requiere_conexion_red, num_bits, sistema_operativo, " +
                "requisitos_hardware, licencia, precio, descripcion, web, correo, tamano_instalador) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            setParametros(ps, a);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Aplicacion a) {
        String sql = "UPDATE aplicacion SET nombre=?, proveedor=?, categoria=?, lenguaje_principal=?, " +
                "lenguaje_secundario=?, usa_bd=?, requiere_conexion_red=?, num_bits=?, sistema_operativo=?, " +
                "requisitos_hardware=?, licencia=?, precio=?, descripcion=?, web=?, correo=?, tamano_instalador=? " +
                "WHERE id=?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            setParametros(ps, a);
            ps.setInt(17, a.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM aplicacion WHERE id=?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void setParametros(PreparedStatement ps, Aplicacion a) throws SQLException {
        ps.setString(1, a.getNombre());
        ps.setString(2, a.getProveedor());
        ps.setString(3, a.getCategoria());
        ps.setString(4, a.getLenguajePrincipal());
        ps.setString(5, a.getLenguajeSecundario());
        ps.setBoolean(6, a.isUsaBd());
        ps.setBoolean(7, a.isRequiereConexionRed());
        ps.setInt(8, a.getNumBits());
        ps.setString(9, a.getSistemaOperativo());
        ps.setString(10, a.getRequisitosHardware());
        ps.setString(11, a.getLicencia());
        ps.setDouble(12, a.getPrecio());
        ps.setString(13, a.getDescripcion());
        ps.setString(14, a.getWeb());
        ps.setString(15, a.getCorreo());
        ps.setDouble(16, a.getTamanoInstalador());
    }

    private Aplicacion mapear(ResultSet rs) throws SQLException {
        Aplicacion a = new Aplicacion();
        a.setId(rs.getInt("id"));
        a.setNombre(rs.getString("nombre"));
        a.setProveedor(rs.getString("proveedor"));
        a.setCategoria(rs.getString("categoria"));
        a.setLenguajePrincipal(rs.getString("lenguaje_principal"));
        a.setLenguajeSecundario(rs.getString("lenguaje_secundario"));
        a.setUsaBd(rs.getBoolean("usa_bd"));
        a.setRequiereConexionRed(rs.getBoolean("requiere_conexion_red"));
        a.setNumBits(rs.getInt("num_bits"));
        a.setSistemaOperativo(rs.getString("sistema_operativo"));
        a.setRequisitosHardware(rs.getString("requisitos_hardware"));
        a.setLicencia(rs.getString("licencia"));
        a.setPrecio(rs.getDouble("precio"));
        a.setDescripcion(rs.getString("descripcion"));
        a.setWeb(rs.getString("web"));
        a.setCorreo(rs.getString("correo"));
        a.setTamanoInstalador(rs.getDouble("tamano_instalador"));
        return a;
    }
}