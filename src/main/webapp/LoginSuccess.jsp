<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Success Page</title>
</head>
<body>
<h3> hi <%= request.getAttribute("user")%>>, Login successful.</h3>
<a href="Login.html">Login Page</a>

</body>
</html>