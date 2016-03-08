<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="template-top-customer.jsp" />
<h1>&nbsp</h1>
<h3>&nbspBuy Fund :</h3>
<h4 class="col-sm-10 col-sm-offset-2" style="color:red;">Note: Minimum Buy Amount is $1. Maximum Buy Amount is $1,000,000</h4>
<form class="form-horizontal" role="form" action="buyFund.do"
	method="POST">
	<div class="form-group">
		<label class="control-label col-sm-2">Available Cash :</label>
		<div class="col-sm-4">
			<label class="col-sm-4">$${avai_cash}</label>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2">Buy Fund :</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="name" name="name"
				placeholder="Put Fund Name here" value="${form.name}">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2">Buy Amount :</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="amount" name="amount"
				placeholder="Put Amount here" value="${form.amount}">
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-4">
			<button type="submit" id="type" name="type" value="buy fund"
				class="btn btn-success pull-left">Buy Fund</button>
		</div>
	</div>
	<jsp:include page="error.jsp" />
	<br></br>
	<br>
	<div class="form-group">
		<label class="control-label col-sm-2">Funds you can buy: </label>
		<div class="col-sm-4">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<td><b>Fund Name</b></td>
						<td><b>Price</b></td>
					</tr>
				</thead>
				<tbody>
					<c:if test="${!(empty fundListInfoList)}">
						<c:forEach var="u" items="${fundListInfoList}">
							<tr>
								<td>${ u.getName() }</td>
								<td>${ u.getPrice() }</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
</form>
<jsp:include page="template-bottom.jsp" />