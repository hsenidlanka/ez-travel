<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Driver Login</title>
    <%@include file="../commonCss.jsp" %>
    <script src="../js/validation/login-validation.js"></script>
    <script src="../js/util.js"></script>
</head>
<body>

<div class="container">
    <div class="login_form">
        <div class="row">
            <div class="col-md-offset-5 col-md-3">
                <div class="form-login">
                    <h4>Login</h4>
                    <c:if test="${not empty login_error}">
                        <div id="loginError" class="alert-warning">${login_error}</div>
                    </c:if>
                    <form:form action="login" method="post" modelAttribute="driver">
                        <div>
                            <form:input type="text" path="email" class="form-control input-sm chat-input "
                                        placeholder="username" id="email" onchange="emailValidate()"/>
                            <span id="email-error"></span>
                        </div>
                        </br>
                        <div>
                            <form:input type="password" path="password" class="form-control input-sm chat-input"
                                        placeholder="password" id="password" onchange="passwordValidate()"/>
                            <span id="password-error"></span>
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