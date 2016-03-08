<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<jsp:include page="template-top-customer.jsp" />
<div class="jumbotron text-center">
	<h1>&nbsp</h1>
	<h1>&nbsp</h1>
	<c:forEach var="error" items="${errors}">
		<h3 style="color: red">${error}</h3>
	</c:forEach>
	<h1>Not Available!</h1>
</div>
<jsp:include page="template-bottom.jsp" />