<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>Listado de Aplicaciones</title></head>
<body>
<h2>Listado de Aplicaciones</h2>
<p><a href="aplicaciones?accion=nuevo">+ Nueva Aplicacion</a></p>
<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>ID</th><th>Nombre</th><th>Proveedor</th><th>Categoria</th>
        <th>SO</th><th>Licencia</th><th>Precio</th><th>Acciones</th>
    </tr>
    <c:forEach var="a" items="${aplicaciones}">
    <tr>
        <td>${a.id}</td>
        <td>${a.nombre}</td>
        <td>${a.proveedor}</td>
        <td>${a.categoria}</td>
        <td>${a.sistemaOperativo}</td>
        <td>${a.licencia}</td>
        <td>${a.precio}</td>
        <td>
            <a href="aplicaciones?accion=editar&id=${a.id}">Editar</a> |
            <a href="aplicaciones?accion=eliminar&id=${a.id}" onclick="return confirm('¿Eliminar esta aplicacion?');">Eliminar</a>
        </td>
    </tr>
    </c:forEach>
</table>
</body>
</html>