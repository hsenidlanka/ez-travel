<%--
  Created by IntelliJ IDEA.
  User: eztravel
  Date: 04/09/17
  Time: 08:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">
    <link href="../css/formStyle.css" rel="stylesheet">
    <script src="../js/validation/login-validation.js"></script>
    <script src="../js/util.js"></script>
</head>
<body>
<fmt:setLocale value="en"/>
<fmt:setBundle basename="hsenid.messages" var="lang"/>


<div class="container">
    <div class="login_form">
        <div class="row">
            <div class="col-md-offset-5 col-md-3">
                <div class="form-login">
                    <h4>Login</h4>
                    <c:if test="${not empty login_error}">
                        <div id="loginError" class="alert-warning">${login_error}</div>
                    </c:if>
                    <form:form action="login" method="post" modelAttribute="customer" name="customer-login">
                        <div>
                            <form:input type="text" path="email" class="form-control input-sm chat-input "
                                        placeholder="username" id="email" onchange="emailValidate()"/>
                            <span id="email-error"></span>
                            <form:errors path="email" element="div" cssClass="error" />
                        </div>
                        </br>
                        <div>
                            <form:input type="password" path="password" class="form-control input-sm chat-input"
                                        placeholder="password" id="password" onchange="passwordValidate()"/>
                            <span id="password-error"></span>
                            <form:errors path="password" element="div" cssClass="error"/>
                        </div>
                        </br>
                        <div>
                            <a href="password_reset" id="forgotPassword">Forgot password?</a>
                        </div>
                        <br>
                        <input type="submit" class="btn btn-primary btn-md button-long" value="login">
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
