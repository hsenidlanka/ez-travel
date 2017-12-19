<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>ez-travel</title>
    <%@include file="commonCss.jsp" %>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="../css/timepicki.css">
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
            <form action="" method="post" id="hireBooking" modelAttribute="hire" onsubmit="return validateForm()">
                <span id="form-errors" class="alert-warning"></span>
                <div id="form-element">
                    <label path="pickup"> Pickup </label>
                    <input type="text" path="pickup" class="form-control input-sm chat-input"
                                placeholder="Pickup" id="pickup" onchange="pickupValidation()"/>
                    <input type="hidden" path="pickupLng" id="pickupLng" name="pickupLng"/>
                    <input type="hidden" path="pickupLat" id="pickupLat" name="pickupLat"/>
                    <input type="hidden" path="pickupLat" id="length" name="pickupLat"/>
                    <span id="pickup-error"></span>
                </div>
                <div id="form-element">
                    <label path="drop">Drop</label>
                    <input type="text" path="drop" class="form-control input-sm chat-input"
                                placeholder="Drop" id="drop" onchange="dropValidation()"/>
                    <span id="drop-error"></span>
                </div>
                <div class="inline-input" id="form-element">
                    <label path="date">Pickup Date</label>
                    <input type="text" path="date" class="form-control input-sm chat-input"
                           placeholder="`Pickup Date" id="date"
                           onchange="pickupDateValidation()"/>
                    <span id="date-error"></span>
                </div>
                <div class="inline-input" id="form-element">
                    <label path="time">Time</label>
                    <input type="text" path="time" class="form-control input-sm chat-input timepick" id="time"
                           placeholder="Time"/>
                    <span id="time-error"></span>
                </div>
                <div id="removeFloat"></div>
                <div id="form-element">
                    <label path="vehicleType">Vehicle Type</label>
                    <select path="vehicleType" class="form-control input-sm chat-input"
                                 placeholder="Vehicle Type" id="vehicleType" onchange="vehicleTypeValidation()">
                        <option selected disabled>Vehicle Type</option>
                        <option>budget</option>
                        <option>hibrid</option>
                        <option>van</option>
                    </select>
                    <span id="vehicleType-error"></span>
                    <hidden path="distance" id="distance" name="distance"></hidden>
                </div>
                <button type="button" class="btn btn-primary btn-md button-long" id="booking_hire_btn"/>
                Book
            </form>
        </div>
        <div id="hirePlaced-dialog" style="display: none"></div>
    </div>
    <div id="map" style="position:static; overflow: hidden;">
    </div>


</div>
<!-- end wrapper -->
<%@include file="commonJs.jsp" %>
<script src="../js/validation/hire-validation.js"></script>
<script src="../js/datepicker.js"></script>
<script src="../js/scripts.js"></script>
<script src="../js/timepicki.js"></script>
<script src="../js/hireBooking.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDUdeTQ7RvCrXcACTn5lJUBUvTK6WOvXYg&callback=initMap&libraries=places"
        async defer></script>
<script>
    $(document).ready(function () {
        $(".timepick").timepicki();
    });

    function pickTime() {
        var time = $("#time").val();
        console.log(time);
    }
</script>
</body>
</html>
