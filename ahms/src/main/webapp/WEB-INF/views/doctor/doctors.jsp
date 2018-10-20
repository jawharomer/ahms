<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>
	<div class="py-2">
		<h3>Doctors</h3>
		<button class="btn btn-success" onclick="getAddingDoctor()">
			<i class="fa fa-plus"></i>
		</button>

	</div>

	<table class="table table-bordered">
		<thead>
			<tr>
				<th>#</th>
				<th>Name</th>
				<th>Phone</th>
				<th>F</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${doctors}" var="item">
				<tr>
					<td>${item.id}</td>
					<td>${item.fullName}</td>
					<td>${item.phone}</td>
					<td>
						<button class="btn btn-sm btn-danger"
							onclick="deleteDoctor(${item.id})">
							<i class="fa fa-times"></i>
						</button>

						<button class="btn btn-sm btn-warning"
							onclick="getEditingDoctor(${item.id})">
							<i class="fa fa-edit"></i>
						</button>

					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>