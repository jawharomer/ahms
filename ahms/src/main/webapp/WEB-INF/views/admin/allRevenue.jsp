<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<div>
	<div class="py-2">
		<h3>Revenues</h3>
	</div>

	<div class="p-1">
		<form action="<c:url value="/admin/revenue"/>">
			<table class="w-100">
				<tr>
					<td>
						<div class="d-flex">
							<div class="d-flex align-items-center p-1">From</div>
							<div class="w-100">
								<input readonly="readonly" class="form-control" id="from"
									name="from"
									value="<fmt:formatDate pattern = "yyyy-MM-dd"  value = "${from}" />" />
							</div>
						</div>
					</td>
					<td>
						<div class="d-flex">
							<div class="d-flex align-items-center p-1">To</div>
							<div class="w-100">
								<input readonly="readonly" class="form-control" id="to"
									name="to"
									value="<fmt:formatDate pattern = "yyyy-MM-dd"  value = "${to}" />" />
							</div>
						</div>
					</td>
					<td>
						<div class="p-1">
							<button class="btn btn-success">
								<i class="fa fa-eye"></i>
							</button>
						</div>
					</td>
				</tr>
			</table>

		</form>

	</div>


	<table id="revenue-table" class="display nowrap">
		<thead>
			<tr>
				<th>Date</th>
				<th>Income</th>
				<th>Expense</th>
				<th>Revenue</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${revenues}" var="item">
				<tr>
					<td><fmt:formatDate value="${item.date}" pattern="yyyy-MM-dd" />
					</td>
					<td>${item.totalIncome}</td>
					<td>${item.totalExpense}</td>
					<td>${item.totalIncome-item.totalExpense}</td>
				</tr>

			</c:forEach>

		</tbody>


	</table>



</div>