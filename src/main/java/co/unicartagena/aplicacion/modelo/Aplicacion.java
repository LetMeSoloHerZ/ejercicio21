package co.unicartagena.aplicacion.modelo;

public class Aplicacion {
    private int id;
    private String nombre;
    private String proveedor;
    private String categoria;
    private String lenguajePrincipal;
    private String lenguajeSecundario;
    private boolean usaBd;
    private boolean requiereConexionRed;
    private int numBits;
    private String sistemaOperativo;
    private String requisitosHardware;
    private String licencia;
    private double precio;
    private String descripcion;
    private String web;
    private String correo;
    private double tamanoInstalador;

    public Aplicacion() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getProveedor() { return proveedor; }
    public void setProveedor(String proveedor) { this.proveedor = proveedor; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getLenguajePrincipal() { return lenguajePrincipal; }
    public void setLenguajePrincipal(String lenguajePrincipal) { this.lenguajePrincipal = lenguajePrincipal; }

    public String getLenguajeSecundario() { return lenguajeSecundario; }
    public void setLenguajeSecundario(String lenguajeSecundario) { this.lenguajeSecundario = lenguajeSecundario; }

    public boolean isUsaBd() { return usaBd; }
    public void setUsaBd(boolean usaBd) { this.usaBd = usaBd; }

    public boolean isRequiereConexionRed() { return requiereConexionRed; }
    public void setRequiereConexionRed(boolean requiereConexionRed) { this.requiereConexionRed = requiereConexionRed; }

    public int getNumBits() { return numBits; }
    public void setNumBits(int numBits) { this.numBits = numBits; }

    public String getSistemaOperativo() { return sistemaOperativo; }
    public void setSistemaOperativo(String sistemaOperativo) { this.sistemaOperativo = sistemaOperativo; }

    public String getRequisitosHardware() { return requisitosHardware; }
    public void setRequisitosHardware(String requisitosHardware) { this.requisitosHardware = requisitosHardware; }

    public String getLicencia() { return licencia; }
    public void setLicencia(String licencia) { this.licencia = licencia; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getWeb() { return web; }
    public void setWeb(String web) { this.web = web; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public double getTamanoInstalador() { return tamanoInstalador; }
    public void setTamanoInstalador(double tamanoInstalador) { this.tamanoInstalador = tamanoInstalador; }
}