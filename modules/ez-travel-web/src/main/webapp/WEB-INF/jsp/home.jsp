<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>ez-travel</title>
    <%@include file="commonCss.jsp" %>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
</head>
<body>
<!--  wrapper -->
<div id="test">
    <%@include file="header.jsp" %>
    <div class="booking-form-background">
        <div class="booking-form" id="booking-form">
            <div class="form_lable booking-form">
                <h4>Reserve a vehicle</h4>
            </div>
            <form:form action="" method="post" modelAttribute="hire" onsubmit="return validateForm()">
                <span id="form-errors" class="alert-warning"></span>
                <div id="form-element">
                    <form:label path="pickup"> Pickup </form:label>
                    <form:input type="text" path="pickup" class="form-control input-sm chat-input"
                                placeholder="Pickup" id="pickup" onchange="pickupValidation()"/>
                    <form:errors path="pickup" class="error"/>
                    <span id="pickup-error"></span>
                </div>
                <div id="form-element">
                    <form:label path="drop">Drop</form:label>
                    <form:input type="text" path="drop" class="form-control input-sm chat-input"
                                placeholder="Drop" id="drop" onchange="dropValidation()"/>
                    <form:errors path="drop" cssClass="error"/>
                    <span id="drop-error"></span>
                </div>
                <div class="inline-input" id="form-element">
                    <form:label path="pickupDate">Pickup Date</form:label>
                    <form:input type="text" path="pickupDate" class="form-control input-sm chat-input"
                                placeholder="`Pickup Date" id="pickupDate"
                                onchange="pickupDateValidation()"></form:input>
                    <form:errors path="pickupDate" cssClass="error"/>
                    <span id="pickupDate-error"></span>
                </div>
                <div class="inline-input" id="form-element">
                    <form:label path="time">Time</form:label>
                    <form:input type="text" path="time" class="form-control input-sm chat-input"
                                placeholder="Time" id="time" onchange="pickupTimeValidation()"/>
                    <form:errors path="time" cssClass="error"/>
                    <span id="time-error"></span>
                </div>
                <div id="removeFloat"></div>
                <div id="form-element">
                    <form:label path="vehicleType">Vehicle Type</form:label>
                    <form:select path="vehicleType" class="form-control input-sm chat-input"
                                 placeholder="Vehicle Type" id="vehicleType" onchange="vehicleTypeValidation()">
                        <option selected disabled>Vehicle Type</option>
                        <option>Budget Car</option>
                        <option>Hibrid Car</option>
                        <option>Van</option>
                    </form:select>
                    <form:errors path="vehicleType" cssClass="error"/>
                    <span id="vehicleType-error"></span>
                </div>
                <input type="submit" class="btn btn-primary btn-md button-long" value="Book">
            </form:form>
        </div>
    </div>
    <div id="map" style="position:static; overflow: hidden;">
    </div>


</div>
<!-- end wrapper -->
<%@include file="commonJs.jsp" %>
<script src="../js/validation/hire-validation.js"></script>
<script src="../js/util.js"></script>
<script src="../js/scripts.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDUdeTQ7RvCrXcACTn5lJUBUvTK6WOvXYg&callback=initMap&libraries=places"
        async defer></script>
</body>
</html>
