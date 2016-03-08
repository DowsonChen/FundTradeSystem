<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="template-top-employee.jsp" />
<h1>&nbsp</h1>
<h3>&nbspChange Password :</h3>
<br>
<form class="form-horizontal" role="form" action="reset.do"
	method="POST">
	<div class="form-group">
		<label class="control-label col-sm-2">Choose an account by
			Username:</label>
		<div class="col-sm-4">
			<select class="form-control" name="customer1">
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
		<label class="control-label col-sm-2">Search by
			Username:</label>
		<div class="col-sm-4">
		<input class="form-control" name="customer2" type="text" class="form-control">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="newPassword">New
			Password:</label>
		<div class="col-sm-4">
			<input class="form-control" name="newPass" type="password"
				placeholder="Case sensitive field">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="confirmPassword">Confirm
			Password:</label>
		<div class="col-sm-4">
			<input class="form-control"
				name="confPass" type="password" placeholder="Case sensitive field">
			<input type="hidden" name="customer" value="${customer}">
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-4">
			<button type="submit" id="type" name="action" value="reset"
				class="btn btn-success pull-left">Set Password</button>
		</div>
	</div>
	<jsp:include page="error.jsp" />
</form>


<jsp:include page="template-bottom.jsp" />