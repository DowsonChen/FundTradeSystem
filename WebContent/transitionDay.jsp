<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="template-top-employee.jsp" />
<h1>&nbsp</h1>
<h3>&nbspTransition Day :</h3>
<h4>&nbspLast trading day was : ${ lastDay }</h4>
<h4 style="color:red;">Note: Minimum Share Price is $0.01. Maximum Share Price is $1,000</h4>
<br>
<form class="form-horizontal" role="form" method="POST">
	<div class="form-group">
		<label class="control-label col-sm-2" for="Select the trading day:">Select the trading day:</label>
		<div class="col-sm-4">
			<table class="table table-bordered table-hover">
				<thead>
					<tr class="">
						<th width="20%">Trading Day: (mm/dd/yyyy)</th>
						<th><input name="date" type="date" required
							class="form-control"></th>
						<th><button type="submit" name="action" value="create"
								class="btn btn-primary">Submit Transition Day</button></th>
					</tr>

				</thead>
			</table>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="Update the funds:">Update the funds:</label>
		<div class="col-sm-6">
			<table class="table table-bordered table-hover table-striped">
				<thead>
					<tr>
						<th>Name of Fund</th>
						<th>Symbol</th>
						<th>Price per Share</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="fund" items="${fundList}">
						<tr>
							<td width="30%">${ fund.getName() }</td>
							<td width="30%">${ fund.getSymbol() }</td>
							<td width="30%" >
								<div class="input-group" >
									<span class="input-group-addon">$</span> <input
										name="fund_${fund.getFundId()}" width="10" type="text" required
										class="form-control"
										value="${price_map.get(fund.getFundId())}">
								</div>
							</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
				<c:if test="${!(empty errors)}">
					<div class="form-group">
						<div class="alert alert-warning">
							<c:forEach var="error" items="${errors}">
								${error}
							</c:forEach>
						</div>
					</div>
				</c:if>
		</div>
	</div>
</form>


