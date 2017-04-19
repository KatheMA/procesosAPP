<%@page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<body>
	<h1>Error!</h1>	
	<p>Ocurrió un error inesperado!</p>	
	
	<!--
    	Exception:  ${exception.message}
        <c:forEach items="${exception.stackTrace}" var="ste">    
        	${ste} 
    	</c:forEach>
    -->
</body>
</html>
