<%--
  Created by IntelliJ IDEA.
  User: hsenid
  Date: 05/12/17
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <%@include file="commonCss.jsp"%>
</head>
<body>
<!--  wrapper -->
<div>
    <%@include file="header.jsp"%>
    <!-- navbar side -->
    <nav class="navbar-default navbar-static-side" role="navigation" id=navbar-side>
        <!-- sidebar-collapse -->
        <div class="sidebar-collapse">
            <!-- side-menu -->
            <ul class="nav" id="side-menu">
                <li>
                    <!-- user image section-->
                    <div class="user-section">
                        <div class="user-section-inner">
                            <img src="../images/user.jpg" alt="">
                        </div>
                        <div class="user-info">
                            <div>Chathuranga <strong>Silva</strong></div>
                            <div class="user-text-online">
                                <span class="user-circle-online btn btn-success btn-circle "></span>&nbsp;Online
                            </div>
                        </div>
                    </div>
                    <!--end user image section-->
                </li>
                <li>
                    <a href="index.html"><i class="fa fa-map-marker fa-fw"></i>  New Hire</a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i>  My Travels</a>
                </li>
                <li>
                    <a href="timeline.html"><i class="fa fa-location-arrow fa-fw"></i>  Default Locations</a>
                </li>
                <li class="selected">
                    <a href="tables.html"><i class="fa fa-cogs fa-fw"></i>  Settings</a>
                </li>
                <li>
                    <a href="forms.html"><i class="fa fa-sign-out fa-fw"></i>  Logout</a>
                </li>
            </ul>
            <!-- end side-menu -->
        </div>
        <!-- end sidebar-collapse -->
    </nav>
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
                <div class="box-body">
                    <div class="form-group">
                        <label class="col-form-label" for="userName">User Name</label>
                        <input type="text" class="form-control" id="userName" placeholder="User Name">
                    </div>
                    <div class="form-group">
                        <label class="col-form-label" for="userName">Upload a profile picture</label><br />
                        <label class="custom-file">
                            <input type="file" id="file2" class="custom-file-input">
                            <span class="custom-file-control"></span>
                        </label>
                    </div>
                    <div class="form-group">
                        <button type="button" class="btn btn-primary"><i class="fa fa-save fa-fw"></i> Save changes</button>
                    </div>
                </div>
                <!-- /.box-body -->
            </div>
        </div>
        <div class="row" id="table-hire">
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">Contact Details</h3>
                </div>
                <div class="box-body">
                    <div class="form-group">
                        <label class="col-form-label" for="email">Email</label>
                        <input type="text" class="form-control" id="email" placeholder="email">
                    </div>
                    <div class="form-group">
                        <label class="col-form-label" for="contactNumber">Contact number</label>
                        <input type="text" class="form-control" id="contactNumber" placeholder="Contact Number">
                    </div>
                    <div class="form-group">
                        <button type="button" class="btn btn-primary"><i class="fa fa-save fa-fw"></i> Save changes</button>
                    </div>
                </div>
                <!-- /.box-body -->
            </div>
        </div>
        <div class="row" id="table-hire">
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">Account</h3>
                </div>
                <div class="box-body">
                    <div class="form-group">
                        <label class="col-form-label" for="currentPassword">Current Password</label>
                        <input type="text" class="form-control" id="currentPassword" placeholder="Current Password">
                    </div>
                    <div class="form-group">
                        <label class="col-form-label" for="newPassword">New Password</label>
                        <input type="text" class="form-control" id="newPassword" placeholder="New Password">
                    </div>
                    <div class="form-group">
                        <label class="col-form-label" for="confirmPassword">Confirm Password</label>
                        <input type="text" class="form-control" id="confirmPassword" placeholder="Confirm Password">
                    </div>
                    <div class="form-group">
                        <button type="button" class="btn btn-primary"><i class="fa fa-save fa-fw"></i> Update Password</button>
                    </div>
                </div>
                <div class="box-body">
                    <a href="#">Delete your account?</a>
                </div>
                <!-- /.box-body -->
            </div>
        </div>
    </div>
    <!-- end content-->
</div>
<!-- end wrapper -->

<%@include file="commonJs.jsp"%>
</body>
</html>
