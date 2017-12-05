<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: hsenid
  Date: 22/09/17
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ez-travel</title>
    <%@include file="commonCss.jsp"%>
    <script src="../js/util.js"></script>
    <script src="../js/validation/registration-validation.js"></script>
</head>
<body>
<%@include file="header.jsp" %>
<div class="form-background">
    <div id="description">
        <h1>Ez-Travel</h1>
        <p>ez-travel gives more reliable servise with this web based system.</p>
    </div>
    <div class="customer-signup-form" id="signup-form">
        <div class="form_lable">
            <h4>Register</h4>
        </div>
        <c:if test="${not empty signup_error}">
            <div id="loginError" class="alert-warning">${signup_error}</div>
        </c:if>
        <form:form action="" method="post" modelAttribute="signUp" onsubmit="return validateForm()">
            <div id="form-element">
                <form:label path="firstName"> First Name </form:label>
                <form:input type="text" path="firstName" class="form-control input-sm chat-input"
                            placeholder="First Name" id="fName" onchange="validateName()"/>
                <form:errors path="firstName" class="error" />
                <span id="fName-error"></span>
            </div>
            <div id="form-element">
                <form:label path="lastName">Last Name</form:label>
                <form:input type="text" path="lastName" class="form-control input-sm chat-input"
                            placeholder="Last Name"  id="lName" onchange="validateLastName()"/>
                <form:errors path="lastName" cssClass="error" />
                <span id="lName-error"></span>
            </div>
            <div id="form-element" class="inline-input">
                <form:label path="mobileNumber">Mobile Number</form:label>
                <form:input type="text" path="mobileNumber" class="form-control input-sm chat-input"
                            placeholder="Mobile Number"  id="pNumber" onchange="validatePNumber()"/>
                <span id="pNumber-error"></span>
            </div>
            <div id="form-element" class="inline-input">
                <form:label path="nic">NIC</form:label>
                <form:input type="text" path="nic" class="form-control input-sm chat-input"
                            placeholder="NIC" id="nic" onchange="validateNIC()"/>
                <span id="nic-error"></span>
            </div>
            <div id="removeFloat"></div>
            <div id="form-element" class="inline-input-3">
                <form:label path="year">Date of Birth</form:label>
                <form:input type="text" path="year" class="form-control input-sm chat-input"
                            placeholder="Year" id="year" onchange="validateYear()"/>
                <span id="year-error"></span>
            </div>
            <div id="form-element" class="inline-input-3">
                <form:label path="month" id="lbl-empty"></form:label>
                <form:select path="month" class="form-control input-sm chat-input" id="month" onchange="validateMonth()">
                    <option selected disabled>Month</option>
                    <option>January</option>
                    <option>February</option>
                    <option>March</option>
                    <option>April</option>
                    <option>May</option>
                    <option>June</option>
                    <option>July</option>
                    <option>August</option>
                    <option>September</option>
                    <option>October</option>
                    <option>November</option>
                    <option>December</option>
                </form:select>
                <span id="month-error"></span>
            </div>
            <div id="form-element" class="inline-input-3">
                <form:label path="day" id="lbl-empty"></form:label>
                <form:input type="text" path="day" class="form-control input-sm chat-input"
                            placeholder="day"  id="day" onchange="validateDay()"/>
                <span id="day-error"></span>
            </div>
            <div id="removeFloat"></div>
            <div id="form-element">
                <form:label path="email">Email</form:label>
                <form:input type="email" path="email" class="form-control input-sm chat-input"
                            placeholder="Email" id="email" onchange="validateEmail()"/>
                <span id="email-error"></span>
            </div>
            <div id="form-element">
                <form:label path="password">Password</form:label>
                <form:input type="password" path="password" class="form-control input-sm chat-input"
                            placeholder="Password" id="password" onchange="validatePassword()"/>
                <span id="password-error"></span>
            </div>
            <div id="form-element">
                <form:label path="rePassword">Confirm Password</form:label>
                <form:input type="password" path="rePassword" class="form-control input-sm chat-input"
                            placeholder="Re-Password" id="rePassword" onchange="checkRePassword()"/>
                <span id="rePassword-error"></span>
            </div>
            <input type="submit" class="btn btn-primary btn-md button-long" value="Sign up"/>
        </form:form>
    </div>
</div>
</body>
</html>
