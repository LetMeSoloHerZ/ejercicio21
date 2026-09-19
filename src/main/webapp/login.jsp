<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>Iniciar Sesion</title></head>
<body>
<h2>Iniciar Sesion</h2>

<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>

<form action="login" method="post">
    <label>Correo:</label><br>
    <input type="email" name="correo" required /><br><br>

    <label>Clave:</label><br>
    <input type="password" name="clave" required /><br><br>

    <button type="submit">Ingresar</button>
</form>
</body>
</html>