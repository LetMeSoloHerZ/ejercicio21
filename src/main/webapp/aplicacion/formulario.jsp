<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head><title>Formulario de Aplicacion</title></head>
<body>
<h2>${aplicacion != null ? "Editar Aplicacion" : "Nueva Aplicacion"}</h2>
<form action="aplicaciones" method="post">
    <input type="hidden" name="id" value="${aplicacion.id}" />

    <label>Nombre:</label><br>
    <input type="text" name="nombre" value="${aplicacion.nombre}" required /><br><br>

    <label>Proveedor:</label><br>
    <input type="text" name="proveedor" value="${aplicacion.proveedor}" /><br><br>

    <label>Categoria:</label><br>
    <input type="text" name="categoria" value="${aplicacion.categoria}" /><br><br>

    <label>Lenguaje Principal:</label><br>
    <input type="text" name="lenguajePrincipal" value="${aplicacion.lenguajePrincipal}" /><br><br>

    <label>Lenguaje Secundario:</label><br>
    <input type="text" name="lenguajeSecundario" value="${aplicacion.lenguajeSecundario}" /><br><br>

    <label><input type="checkbox" name="usaBd" ${aplicacion.usaBd ? 'checked' : ''} /> Usa Base de Datos</label><br><br>

    <label><input type="checkbox" name="requiereConexionRed" ${aplicacion.requiereConexionRed ? 'checked' : ''} /> Requiere Conexion a Red</label><br><br>

    <label>Numero de Bits:</label><br>
    <input type="number" name="numBits" value="${aplicacion.numBits}" /><br><br>

    <label>Sistema Operativo:</label><br>
    <input type="text" name="sistemaOperativo" value="${aplicacion.sistemaOperativo}" /><br><br>

    <label>Requisitos de Hardware:</label><br>
    <input type="text" name="requisitosHardware" value="${aplicacion.requisitosHardware}" /><br><br>

    <label>Licencia:</label><br>
    <input type="text" name="licencia" value="${aplicacion.licencia}" /><br><br>

    <label>Precio:</label><br>
    <input type="number" step="0.01" name="precio" value="${aplicacion.precio}" /><br><br>

    <label>Descripcion:</label><br>
    <textarea name="descripcion">${aplicacion.descripcion}</textarea><br><br>

    <label>Web:</label><br>
    <input type="text" name="web" value="${aplicacion.web}" /><br><br>

    <label>Correo:</label><br>
    <input type="email" name="correo" value="${aplicacion.correo}" /><br><br>

    <label>Tamano del Instalador (MB):</label><br>
    <input type="number" step="0.01" name="tamanoInstalador" value="${aplicacion.tamanoInstalador}" /><br><br>

    <button type="submit">Guardar</button>
</form>
<br>
<a href="aplicaciones">Volver al listado</a>
</body>
</html>