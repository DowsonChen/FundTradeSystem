<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="template-top-employee.jsp" />
<br><br><br><br>
<jsp:include page="error.jsp" />
<div class="row">
<div class="container-fluid">
<div class="col-lg-6">
<h4>Customer information:</h4>
<div class="table-responsive">
		<table class="table table-hover">
			<tbody>
				<tr>
					<td>Customer Username:</td>
					<td colspan="2">${customer.userName}</td>
				</tr>
				<tr>
					<td>Customer Name:</td>
					<td colspan="2">${customer.firstName} ${customer.lastName}</td>
				</tr>
				</tbody>
				</table>
</div>
</div>
</div>
</div>
<div class="container">
    <h3>Detailed Information</h3>
    <table class="table table-hover">
        <thead>
            <tr>
                <th>Transaction Date</th>
                <th>Operation</th>
                <th>Fund Name</th>
                <th>Number of Shares</th>
                <th>Share Price</th>
                <th>Dollar Amount</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="tran" items="${transactions}">   
                <tr>
                    <th>${tran.executeDate}</th>
                    <th>${tran.transactionType}</th>
                    <c:choose>
                        <c:when test="${tran.fundId == -1}">
                        <th>N/A</th>
                        </c:when>
                        <c:otherwise>
                        <th>${tran.fundName}</th>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${tran.shares == -1}">
                        <th>N/A</th>
                        </c:when>
                        <c:otherwise>
                        <th>${tran.shares}</th>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${tran.sharePrice == -1}">
                        <th>N/A</th>
                        </c:when>
                        <c:otherwise>
                        <th><fmt:setLocale value="en_US"/>
                        <fmt:formatNumber value="${tran.sharePrice}" type="currency"/></th>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${tran.amount == -1}">
                        <th>N/A</th>
                        </c:when>
                        <c:otherwise>
                        <th><fmt:setLocale value="en_US"/>
                        <fmt:formatNumber value="${tran.amount}" type="currency"/></th>
                        </c:otherwise>
                    </c:choose>
                </tr>
             </c:forEach>   
        </tbody>
    </table>
</div>

<br><br>

<jsp:include page="template-bottom.jsp" />
