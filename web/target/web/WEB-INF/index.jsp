<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<c:forEach items="${users}" var="user">
    <p>${user}</p>
</c:forEach>
</body>
</html>
