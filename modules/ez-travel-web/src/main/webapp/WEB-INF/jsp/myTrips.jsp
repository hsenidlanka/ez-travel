<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">
<link href="../css/styles.css" rel="stylesheet">
<link href="../css/custom-style.css" rel="stylesheet">
<link href="../css/main-style.css" rel="stylesheet">
<link href="../css/formStyle.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/feedbackHandler.js"></script>

<%@include file="header.jsp" %>
<%@include file="sideNavBar.jsp" %>

<!-- start content-->
<div id="page-wrapper">
    <div class="row" id="content-start">
        <h2>My Travels</h2>
    </div>

    <%-------------------------start feedback popup------------------------------------------%>
    <div class="modal modalbox modal-transparent fade " id="feedback_popup" role="dialog">

        <!-- Modal content-->
        <div class=" box">
            <div style="padding-bottom: 36px;">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <span id="feedback_success"></span>
            <div class="content" style="color: white; padding-bottom: 10px">
                <h2 style="color: white">Feedback</h2>
                <p>Send us any feedback or comment about the hire or driver, we will check those and take an action</p>
                <form id="feedback_form">
                    <div class="form-group">
                        <label for="feedbackDescription">Type here:</label>
                        <input type="textarea" class="form-control" rows="6" id="feedbackDescription"/>
                    </div>
                    <button type="button" class="btn btn-primary" id="feedback_submit">Submit</button>
                </form>
            </div>
        </div>
    </div>

    <%------------------------------end feedback popup---------------------------------------%>

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
                    <input type="hidden" id="hire_id" value="${item.hire_id}"/>
                    <input type="hidden" id="driver_id" value="${item.driver_id}"/>
                    <td>
                        <form>
                            <button type="button" class="btn btn-primary" data-toggle="modal"
                                    data-target="#feedback_popup"><i class="fa fa-pencil fa-fw"></i> Add
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<!-- end content-->
