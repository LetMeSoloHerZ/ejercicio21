<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>Recuperar Clave</title></head>
<body>

<h2>Recuperar Clave</h2>

<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>
<c:if test="${not empty mensaje}">
    <p style="color:green;">${mensaje}</p>
</c:if>

<form action="recuperarClave" method="post">
    <label>Correo:</label>
    <input type="email" name="correo" required>
    <button type="submit">Enviar clave temporal</button>
</form>

<p><a href="login">Volver al inicio de sesión</a></p>

</body>
</html>