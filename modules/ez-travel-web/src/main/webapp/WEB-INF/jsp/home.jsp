<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ez-travel</title>
    <%@include file="commonCss.jsp"%>
</head>
<body>
<!--  wrapper -->
<div>
    <%@include file="header.jsp"%>
    <div class="booking-form-background">
        <div class="booking-form" id="booking-form">
            <div class="form_lable booking-form">
                <h4>Reserve a vehicle</h4>
            </div>
            <form:form action="" method="post" modelAttribute="signUp" >
                <div id="form-element">
                    <form:label path="pickup"> Pickup </form:label>
                    <form:input type="text" path="pickup" class="form-control input-sm chat-input"
                                placeholder="Pickup" id="pickup" />
                    <form:errors path="pickup" class="error" />
                    <span id="pickup-error"></span>
                </div>
                <div id="form-element">
                    <form:label path="drop">Drop</form:label>
                    <form:input type="text" path="drop" class="form-control input-sm chat-input"
                                placeholder="Drop"  id="drop" />
                    <form:errors path="drop" cssClass="error" />
                    <span id="drop-error"></span>
                </div>
                <div class="inline-input" id="form-element">
                    <form:label path="pickup-date">Pickup Date</form:label>
                    <form:input type="text" path="pickup-date" class="form-control input-sm chat-input"
                                placeholder="Pickup Date"  id="pickup-date" ><span class="fa fa-calendar  fa-2x"></span></form:input>
                    <form:errors path="pickup-date" cssClass="error" />
                    <span id="pickup-date-error"></span>
                </div>
                <div class="inline-input" id="form-element">
                    <form:label path="time">Time</form:label>
                    <form:input type="text" path="time" class="form-control input-sm chat-input"
                                placeholder="Time"  id="time" />
                    <form:errors path="time" cssClass="error" />
                    <span id="time-error"></span>
                </div>
                <div id="removeFloat"></div>
                <div id="form-element">
                    <form:label path="vehicle-type">Vehicle Type</form:label>
                    <form:input type="text" path="vehicle-type" class="form-control input-sm chat-input"
                                placeholder="Vehicle Type"  id="vehicle-type" />
                    <form:errors path="vehicle-type" cssClass="error" />
                    <span id="vehicle-type-error"></span>
                </div>
                <input type="submit" class="btn btn-primary btn-md button-long" value="Book">
            </form:form>
        </div>
    </div>
    <div id="map">
    </div>


</div>
<!-- end wrapper -->
<%@include file="commonJs.jsp"%>
<script src="../js/util.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDUdeTQ7RvCrXcACTn5lJUBUvTK6WOvXYg&callback=initMap&libraries=places" async defer></script>
</body>
</html>
