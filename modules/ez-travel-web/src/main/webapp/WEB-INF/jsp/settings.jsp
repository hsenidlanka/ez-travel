<%--
  Created by IntelliJ IDEA.
  User: hsenid
  Date: 05/12/17
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <%@include file="commonCss.jsp" %>
</head>
<body>
<!--  wrapper -->
<div>
    <%@include file="header.jsp" %>
    <!-- navbar side -->
    <%@include file="sideNavBar.jsp" %>
    <!-- end navbar side -->

    <!-- start content-->
    <div id="page-wrapper">
        <div class="row" id="content-start">
            <h2>Settings</h2>
        </div>

        <div class="row" id="table-hire">
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">Personal Info</h3>
                </div>
                <form:form method="post" action="updateInfo" id="personalInfoUpdate" modelAttribute="personalInfo">
                    <div class="box-body">
                        <div class="form-group">
                            <label class="col-form-label" for="email">Email</label>
                            <form:input type="text" class="form-control" path="email" id="email" value="${email}"
                                        disabled="true"></form:input>
                        </div>
                        <div class="form-group">
                            <label class="col-form-label" for="firstName">First Name</label>
                            <form:input type="text" class="form-control" path="first_name" id="firstName"
                                        value="${first_name}"></form:input>
                        </div>
                        <div class=" form-group">
                            <label class="col-form-label" for="lastName">Last Name</label>
                            <form:input type="text" class="form-control" path="last_name" id="lastName"
                                        value="${last_name}"></form:input>
                        </div>
                        <div class=" form-group">
                            <label class="col-form-label" for="contactNumber">Contact number</label>
                            <form:input type="text" class="form-control" path="contact_number" id="contactNumber"
                                        value="${contact_number}"></form:input>
                        </div>
                        <div class=" form-group">
                            <label class="col-form-label">Upload a profile picture</label><br/>
                            <label class="custom-file">
                                <form:input type="file" path="user_image" id="user_image"
                                            class="custom-file-input"></form:input>
                                <span class="custom-file-control"></span>
                            </label>
                        </div>
                        <div class="form-group">
                            <input type="submit" id="btnUpdatePersonelInfo" class="btn btn-primary fnt"
                                   value="&#xf0c7; Save changes">
                            </input>
                        </div>
                    </div>
                </form:form>
                <!-- /.box-body -->
            </div>
        </div>

        <div class="row" id="table-hire">
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">Account</h3>
                </div>
                <div class="box-body">
                    <c:if test="${not empty update_password_error}">
                        <div class="alert-warning">${update_password_error}</div>
                    </c:if>
                    <form:form method="post" action="updatePassword" modelAttribute="updatePassword">
                        <div class="form-group">
                            <label class="col-form-label" for="currentPassword">Current Password</label>
                            <C:if test="${not empty current_password_error}">
                                <div class="alert-warning">${current_password_error}</div>
                            </C:if>
                            <form:input type="password" path="password" class="form-control" id="currentPassword"
                                        placeholder="Current Password"></form:input>
                        </div>
                        <div class="form-group">
                            <label class="col-form-label" for="newPassword">New Password</label>
                            <form:input type="password" path="newPassword" class="form-control" id="newPassword"
                                        placeholder="New Password"></form:input>
                        </div>
                        <div class="form-group">
                            <label class="col-form-label" for="confirmPassword">Confirm Password</label>
                            <C:if test="${not empty password_not_matching_error}">
                                <div class="alert-warning">${password_not_matching_error}</div>
                            </C:if>
                            <form:input type="password" path="reNewPassword" class="form-control" id="confirmPassword"
                                        placeholder="Confirm Password"></form:input>
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btn btn-primary fnt" value="&#xf0c7; Update Password">
                            </input>
                        </div>
                    </form:form>
                </div>
                <div class="box-body">
                    <a href="" data-toggle="modal" data-target="#deleteAccountModel">Delete your account?</a>
                    <!-- Modal -->
                    <div class="modal fade" id="deleteAccountModel" role="dialog">
                        <div class="modal-dialog">

                            <!-- Modal content-->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">Are you sure you want to do this?</h4>
                                </div>
                                <div class="modal-body">
                                    <p>We will immediately delete all of records, along with all of your Hire records
                                        and user account.
                                        You will no longer be abel to use our services, and your username will be
                                        available to anyone
                                        on ez-Travel.</p>
                                </div>
                                <div class="form-modal">
                                    <form:form method="post" action="deleteAccount" modelAttribute="deleteAccount">
                                        <div class="form-group">
                                            <label class="col-form-label" for="password">Password</label>
                                            <C:if test="${not empty password_error}">
                                                <div class="alert-warning">${password_error}</div>
                                            </C:if>
                                            <form:input type="password" path="password" class="form-control"
                                                        id="currentPassword"
                                                        placeholder="Current Password"></form:input>
                                        </div>
                                        <div class="modal-footer">
                                            <input type="submit" class="btn btn-danger" value="Delete Account"></input>
                                        </div>
                                    </form:form>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <!-- /.box-body -->
            </div>
        </div>
        <!-- end content-->
    </div>
    <!-- end wrapper -->
    <script src="../js/jquery-1.10.2.js"></script>
    <script src="../js/bootstrap.min.js"></script>
</html>
