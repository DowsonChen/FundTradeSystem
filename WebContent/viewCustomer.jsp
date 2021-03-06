<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="template-top-employee.jsp" />
<h1>&nbsp</h1>
<h3>&nbspView Customer Account:</h3>
<br>
<form class="form-horizontal" role="form" action="viewCustomer.do"
	method="POST">
	<div class="form-group">
		<label class="control-label col-sm-2">Select Username: </label>
		<div class="col-sm-4">
			<select class="form-control" name="userName1">
				<option></option>
				<c:choose>
					<c:when test="${ (empty customerList) }"></c:when>
					<c:otherwise>
						<c:forEach var="u" items="${ customerList }">
							<option>${ u.getUserName() }</option>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2">Search Username: </label>
		<div class="col-sm-4">
			<input name="userName2" type="text" class="form-control"
				value="${form.userName2}">
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-4">
			<button type="submit" name="action" value="select"
				class="btn btn-success pull-left">Select Account</button>
		</div>
	</div>
	<jsp:include page="error.jsp" />
</form>
<jsp:include page="template-bottom.jsp" />