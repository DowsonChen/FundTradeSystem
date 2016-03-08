<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="template-top-customer.jsp" />
<h1>&nbsp</h1>
<h3>&nbspSell Fund :</h3>
<br>
<form class="form-horizontal" role="form" action="sellFund.do"
	method="POST">
	<div class="form-group">
		<label class="control-label col-sm-2">Sell Fund :</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="name" name="name"
				placeholder="Put Fund Name here" value="${form.name}">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2">Sell Shares :</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="shares" name="shares"
				placeholder="Put Shares here" value="${form.shares}">
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-4">
			<button type="submit" id="type" name="type" value="sell fund"
				class="btn btn-success pull-left">Sell Fund</button>
		</div>
	</div>
	<jsp:include page="error.jsp" />
	<br></br>
	<br></br>
	<div class="form-group">
		<label class="control-label col-sm-2"><b>Funds owned: </b></label>
		<div class="col-sm-4">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<td><b>Fund Name</b></td>
						<td><b>Shares</b></td>
					</tr>
				</thead>
				<tbody>
					<c:if test="${!(empty positionInfoList)}">
						<c:forEach var="u" items="${positionInfoList}">
							<tr>
								<td>${ u.getName() }</td>
								<td>${ u.getShares() }</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
</form>

<jsp:include page="template-bottom.jsp" />