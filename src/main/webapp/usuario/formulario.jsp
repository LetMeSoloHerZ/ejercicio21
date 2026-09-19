<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head><title>Formulario de Usuario</title></head>
<body>
<h2>${usuario != null ? "Editar Usuario" : "Nuevo Usuario"}</h2>
<form action="usuarios" method="post">
    <input type="hidden" name="id" value="${usuario.id}" />

    <label>Nombre:</label><br>
    <input type="text" name="nombre" value="${usuario.nombre}" required /><br><br>

    <label>Clave:</label><br>
    <input type="text" name="clave" value="${usuario.clave}" required /><br><br>

    <label>Correo:</label><br>
    <input type="email" name="correo" value="${usuario.correo}" required /><br><br>

    <label>Rol:</label><br>
    <input type="text" name="rol" value="${usuario.rol}" required /><br><br>

    <button type="submit">Guardar</button>
</form>
<br>
<a href="usuarios">Volver al listado</a>
</body>
</html>