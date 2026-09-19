<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>Reporte de Usuarios</title></head>
<body>

<h2>Reporte de Usuarios</h2>

<form action="reporteUsuarios" method="get">
    <label>Filtrar por rol:</label>
    <select name="rol">
        <option value="">-- Todos --</option>
        <option value="administrador">Administrador</option>
        <option value="usuario">Usuario</option>
    </select>
    <button type="submit">Filtrar por rol</button>
</form>

<form action="reporteUsuarios" method="get">
    <label>Buscar por nombre:</label>
    <input type="text" name="nombre" placeholder="Nombre parcial">
    <button type="submit">Buscar</button>
</form>

<table border="1">
    <tr><th>ID</th><th>Nombre</th><th>Correo</th><th>Rol</th></tr>
    <c:forEach var="u" items="${usuarios}">
        <tr>
            <td>${u.id}</td>
            <td>${u.nombre}</td>
            <td>${u.correo}</td>
            <td>${u.rol}</td>
        </tr>
    </c:forEach>
</table>

<p><a href="usuarios">Volver al listado</a></p>

</body>
</html>