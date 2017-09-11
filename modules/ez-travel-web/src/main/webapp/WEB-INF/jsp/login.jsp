<%--
  Created by IntelliJ IDEA.
  User: hsenid
  Date: 04/09/17
  Time: 08:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ez-travel Login</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">


</head>
<body>

<div class="container">
    <div class="login_form">
        <div class="row">
            <div class="col-md-offset-5 col-md-3">
                <div class="form-login">
                    <h4>Login</h4>
                    <input type="text" id="email" class="form-control input-sm chat-input" placeholder="username"/>
                    </br>
                    <input type="text" id="password" class="form-control input-sm chat-input"
                           placeholder="password"/>
                    </br>
                    <input type="checkbox" class="remember_me">Remember me.
                    <br>
                    <div class="wrapper">
                    <span class="group-btn">
                        <a href="/api/customer/login" class="btn btn-primary btn-md">login</a>
                    </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
</body>
</html>
