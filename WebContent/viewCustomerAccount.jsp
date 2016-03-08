<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="template-top-employee.jsp" />
<h1>&nbsp</h1>
<h3>&nbspView Customer Account:</h3>
<br>
<jsp:include page="error.jsp" />
<div class="row">
<div class="container-fluid">
<div class="col-lg-6">
	<h4>Customer information:</h4>
	<div class="table-responsive">
		<table class="table table-hover">
			<tbody>
				<tr>
					<td>Account User Name:</td>
					<td colspan="2">${userName}</td>

				</tr>
				<tr>
					<td width="40%">Customer Name:</td>
					<td colspan="2">${customerName}</td>

				</tr>
				<tr>
					<td>Address:</td>
					<td colspan="2">${address1 },${address2 }</td>

				</tr>
				<tr>
					<td>City:</td>
					<td colspan="2">${city}</td>

				</tr>
				<tr>
					<td>State:</td>
					<td colspan="2">${state}</td>

				</tr>
				<tr>
					<td>Zip:</td>
					<td colspan="2">${zip}</td>

				</tr>
				<tr>
					<td>Last trading date:</td>
					<td colspan="2">${lastDay}</td>

				</tr>
				<tr>
					<td>Last Posted Balance:</td>
					<td>$${cash}</td>
				</tr>
				<tr>
					<td>Available Cash:</td>
					<td>$${avai_cash}</td>
				</tr>
				</tbody>
			</tbody>
		</table>
	</div>

	<h4>Funds owned:</h4>
	<div class="table-responsive">
		<table class="table table-hover">
			<c:choose>

				<c:when test="${(empty positionInfoList)}"> No funds owned.</c:when>
				<c:otherwise>
					<thead>
						<tr>
							<td width="40%"><b>Fund Name</b></td>
							<td align="right"><b>Shares</b></td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="u" items="${positionInfoList}">
							<tr>
								<td>${ u.getName() }</td>
								<td align="right">${ u.getShares() }</td>
							</tr>
						</c:forEach>
					</tbody>
				</c:otherwise>
			</c:choose>

		</table>
	</div>
</div>
</div>
</div>


<jsp:include page="template-bottom.jsp" />