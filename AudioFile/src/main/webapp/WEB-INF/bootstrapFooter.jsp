<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
</div>
</div>
<div class="col">
	<c:choose>
		<c:when test="${ success != null }">
			<div class="alert alert-success" role="alert">${ success }</div>
		</c:when>
		<c:when test="${ warning != null }">
			<div class="alert alert-warning" role="alert">${ warning }</div>
		</c:when>
		<c:when test="${ error != null }">
			<div class="alert alert-danger" role="alert">${ error }</div>
		</c:when>
	</c:choose>
</div>
<br>
<footer class="container-fluid text-center page-footer">
	<div class="footer-contents">
		<a href="#">Subscribe</a>
	</div>
</footer>

</body>
</html>
