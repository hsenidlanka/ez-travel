<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">
<link href="../css/custom-style.css" rel="stylesheet">
<link href="../css/main-style.css" rel="stylesheet">
<link href="../css/formStyle.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<%@include file="header.jsp" %>
<%@include file="sideNavBar.jsp" %>

<!-- start content-->
<div id="page-wrapper">
    <div class="row" id="content-start">
        <h2>My Travels</h2>
    </div>
    <div class="row" id="table-hire">
        <table class="table table-hover" style="color: black">
            <thead>
            <tr>
                <th>Pickup</th>
                <th>Drop</th>
                <th>Driver</th>
                <th>Fare</th>
                <th>Vehicle</th>
                <th>Feedback</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${hires}" var="item">
                <tr>
                    <td style="max-width: 125px">${item.from}</td>
                    <td style="max-width: 125px">${item.to}</td>
                    <td>${item.driver_name}</td>
                    <td>${item.cost}</td>
                    <td>${item.vehicle_type}</td>
                    <td>
                        <button type="button" class="btn btn-primary"><i class="fa fa-pencil fa-fw"></i> Add</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<!-- end content-->

