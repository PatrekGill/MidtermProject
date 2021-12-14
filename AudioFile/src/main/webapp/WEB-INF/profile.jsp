<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="bootstrapHeader.jsp" />



<body>
<center><h1>Profile</h1></center>
<tbody>
<form action="login" method="post">
<tr>
<td>Login</td>
<td><input class="textbox" name="username"/></td>
</tr>
<tr>
<td>Password</td>
<td><input class="textbox" name="password" /></td>

</tr>
</tbody>
</form>
</body>


</jsp:include>