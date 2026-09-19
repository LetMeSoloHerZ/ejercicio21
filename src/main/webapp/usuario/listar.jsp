<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Listado de Usuarios</title></head>
<body>
<h2>Listado de Usuarios</h2>
<p><a href="usuarios?accion=nuevo">+ Nuevo Usuario</a></p>
<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>ID</th><th>Nombre</th><th>Correo</th><th>Rol</th><th>Acciones</th>
    </tr>
    <c:forEach var="u" items="${usuarios}">
    <tr>
        <td>${u.id}</td>
        <td>${u.nombre}</td>
        <td>${u.correo}</td>
        <td>${u.rol}</td>
        <td>
            <a href="usuarios?accion=editar&id=${u.id}">Editar</a> |
            <a href="usuarios?accion=eliminar&id=${u.id}" onclick="return confirm('¿Eliminar este usuario?');">Eliminar</a>
        </td>
    </tr>
    </c:forEach>
</table>
</body>
</html>