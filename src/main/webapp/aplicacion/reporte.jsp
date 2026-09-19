<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>Reporte de Aplicaciones</title></head>
<body>

<h2>Reporte de Aplicaciones</h2>

<form action="reporteAplicaciones" method="get">
    <label>Categoría:</label>
    <input type="text" name="categoria" placeholder="Ej: Desarrollo">
    <label>Precio mín:</label>
    <input type="number" step="0.01" name="precioMin">
    <label>Precio máx:</label>
    <input type="number" step="0.01" name="precioMax">
    <button type="submit">Filtrar por categoría y precio</button>
</form>

<form action="reporteAplicaciones" method="get">
    <label>Sistema operativo:</label>
    <input type="text" name="sistemaOperativo" placeholder="Ej: Windows">
    <label>Requiere conexión de red:</label>
    <select name="requiereConexionRed">
        <option value="true">Sí</option>
        <option value="false">No</option>
    </select>
    <button type="submit">Filtrar por SO y conexión</button>
</form>

<table border="1">
    <tr>
        <th>ID</th><th>Nombre</th><th>Categoría</th><th>SO</th>
        <th>Requiere red</th><th>Precio</th>
    </tr>
    <c:forEach var="a" items="${aplicaciones}">
        <tr>
            <td>${a.id}</td>
            <td>${a.nombre}</td>
            <td>${a.categoria}</td>
            <td>${a.sistemaOperativo}</td>
            <td>${a.requiereConexionRed}</td>
            <td>${a.precio}</td>
        </tr>
    </c:forEach>
</table>

<p><a href="aplicaciones">Volver al listado</a></p>

</body>
</html>