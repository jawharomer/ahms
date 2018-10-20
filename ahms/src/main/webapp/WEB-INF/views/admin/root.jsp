<%@ page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<jsp:useBean id="now" class="java.util.Date" />
<c:set var="tomorrow"
	value="<%=new Date(new Date().getTime() + 60 * 60 * 24 * 1000)%>" />

<fmt:formatDate var="currentDate" value="${now}" pattern="yyyy-MM-dd" />
<fmt:formatDate var="tomorrow" value="${tomorrow}" pattern="yyyy-MM-dd" />

<section id="admin-body">
	<section id="section-right" class="card">

		<ul class="list-group">
			<li><h4 class="p-1">Monitor</h4></li>
			<li class="list-group-item"><a
				href="<c:url value="/notifications/pharmacy"/>">Pharmacy</a></li>
			<li class="list-group-item"><a
				href="<c:url value="/notifications/stock"/>">Stock</a></li>


			<li class="list-group-item"><a
				href="<c:url value="/admin/revenue"/>?from=${currentDate}&to=${tomorrow}">Revenues</a></li>



			<li class="list-group-item"><a
				href="<c:url value="/patients"/>?from=${currentDate}&to=${tomorrow}">
					Patients</a></li>

			<li class="list-group-item"><a
				href="<c:url value="/patientVisits"/>?from=${currentDate}&to=${tomorrow}">
					Patient Visits</a></li>


			<li class="list-group-item"><a
				href="<c:url value="/patientVisits/patientProductUseds"/>?from=${currentDate}&to=${tomorrow}">
					Patient Product Used</a></li>

			<li class="list-group-item"><a
				href="<c:url value="/operations"/>">Operations</a></li>
			<li class="list-group-item"><a href="<c:url value="/doctors"/>">Doctors</a></li>


			<li><h4 class="text-info p-1 pl-2">Stock</h4></li>
			<li class="list-group-item text-info"><a
				href="<c:url value="/orders/add"/>">Order</a></li>
			<li class="list-group-item text-info"><a
				href="<c:url value="/orders"/>?from=${currentDate}&to=${tomorrow}">
					Orders</a></li>

			<li class="list-group-item text-info"><a
				href="<c:url value="/orderDetails"/>?from=${currentDate}&to=${tomorrow}">
					Stock</a></li>

			<li class="list-group-item text-info"><a
				href="<c:url value="/vendors"/>">Vendors</a></li>

			<li class="list-group-item text-info"><a
				href="<c:url value="/productCategories"/>">ProductCategories</a></li>
		</ul>
	</section>

	<section id="main-content">

		<tiles:insertAttribute name="adminBody" />

	</section>

</section>