<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${!(empty errors)}">
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-4 alert alert-warning">
			<h4>Warning!</h4>
			<c:forEach var="error" items="${errors}">
				${error}
				<br>
			</c:forEach>
		</div>
	</div>
</c:if>