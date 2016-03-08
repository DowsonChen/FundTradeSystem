<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="template-top-customer.jsp" />
<br>
<br>
<br>
<br>

<div class="container">

	<form method="post" action="researchFund.do">
		<div class="col-lg-4 col-lg-offset-4">
			<table class="table table-bordered table-hover">
				<c:forEach var="error" items="${errors}">
					<h3 style="color: red">${error}</h3>
				</c:forEach>
				<tr>
					<td><label>Select the fund:</label>
						<div class="form-group">
							<!-- Add the account stocks below -->
							<select class="form-control" name="fundName">
								<c:choose>
									<c:when test="${ (empty funds) }"></c:when>
									<c:otherwise>
										<c:forEach var="u" items="${ funds }">
											<option>${ u.getName() }</option>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</select>
						</div>
				</tr>
				<tr>
					<td align="center"><input type="submit" name="action"
						class="btn btn-primary" value="Research" /></td>
				</tr>
			</table>
		</div>
		<br>
		<script type="text/javascript"
			src="https://www.gstatic.com/charts/loader.js"></script>
		<script type="text/javascript">
			google.charts.load('current', {
				'packages' : [ 'bar' ]
			});
			google.charts.setOnLoadCallback(drawChart);
			function drawChart() {
				document.getElementById("chart").style.visibility = 'hidden';
				var source = document.getElementById("chartData").value;
				var dataTable = source.split(",");
				if (source) {
					document.getElementById("chart").style.visibility = 'visible';
					var data = new google.visualization.DataTable();
					data.addColumn('string', 'Fund Execute Date');
					data.addColumn('number', 'Price USD');
					var num = parseInt(dataTable.length / 2 - 1);
					if (dataTable.length / 2 > 10){
						data.addRows(parseInt(10));
						var num = 9;
					} else {
					data.addRows(parseInt(dataTable.length / 2));
					}
					
					for (var i = 0; i < dataTable.length - 1 & num >= 0 ; i = i + 2) {
						data.setCell(parseInt(num), 0, String(dataTable[dataTable.length - 3 - i]));
						num = num - 1;
					}
					if (dataTable.length / 2 > 10){
						var num = 9;
					} else {
						var num = parseInt(dataTable.length / 2 - 1);
					}
					for (var i = 1; i < dataTable.length - 1 & num >= 0; i = i + 2) {
						data
								.setCell(parseInt(num), 1,
										parseFloat(dataTable[dataTable.length - 1 - i]));
						num = num - 1;
					}

					var options = {
						chart : {
							title : 'Fund Performance',
						}
					};

					var chart = new google.charts.Bar(document
							.getElementById('chart_div'));

					chart.draw(data, options);
				}
			}
		</script>
		<br>
		<div class="col-lg-4 col-lg-offset-4">
			<div id="chart">
				<b>Displaying results for:</b><br> <br>
				<table class="table table-bordered table-hover table-striped">
					<tr>
						<td><b>Fund Name:</b></td>
						<td><b>Symbol:</b></td>
					</tr>
					<tr>
						<td>${fundTitle}</td>
						<td>${symbol}</td>
					</tr>
				</table>
				<b>Price fluctuation:</b><br><br>
			</div>
		</div>
		<input type="hidden" name="chartData" id="chartData"
			value="${chartData}" />
		<div id="chart_div" style="height: 500px;"
			class="col-lg-6 col-lg-offset-3"></div>
	</form>
</div>


<jsp:include page="template-bottom.jsp" />