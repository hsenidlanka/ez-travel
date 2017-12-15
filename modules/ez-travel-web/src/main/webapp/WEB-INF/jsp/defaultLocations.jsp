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
            <h2>Default Locations</h2>
        </div>
        <div class="row" id="table-locations">
            <table class="table table-hover" style="color: black">
                <thead>
                <tr>
                    <th></th>
                    <th>Location</th>
                    <th>Change</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th class="vertical-align"><i class="fa fa-home fa-fw"></i> Home</th>
                    <td>
                        <div><img class="default-locations" src="../images/location_home.png"/></div>
                    </td>
                    <td class="vertical-align">
                        <button type="button" class="btn btn-primary" id="location-home" data-toggle="modal"
                                data-target="#popupMap"><i class="fa fa-pencil fa-fw"></i> Edit
                        </button>
                    </td>
                </tr>
                <tr>
                    <th class="vertical-align"><i class="fa fa-briefcase fa-fw"></i> Office</th>
                    <td>
                        <div><img class="default-locations" src="../images/location_office.png"/></div>
                    </td>
                    <td class="vertical-align">
                        <button type="button" class="btn btn-primary" id="location-office"><i
                                class="fa fa-pencil fa-fw"></i> Edit
                        </button>
                    </td>
                </tr>
                <tr>
                    <th class="vertical-align"><i class="fa fa-book fa-fw"></i> School</th>
                    <td>
                        <div><img class="default-locations" src="../images/location_school.png"/></div>
                    </td>
                    <td class="vertical-align">
                        <button type="button" class="btn btn-primary" id="location-school"><i
                                class="fa fa-pencil fa-fw"></i> Edit
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
            <a id="location-add-more"><i class="fa fa-plus fa-fw"></i> Add</a>
            <div>
            </div>
        </div>

        <!-- Modal -->
        <div class="modal modalbox modal-transparent fade " id="popupMap" role="dialog">

            <!-- Modal content-->
            <div class=" box" style="width: 745px; height: 485px; margin-left: 185px; margin-top: 125px">
                <div style="padding-bottom: 36px;">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="content">
                    <div id="map"></div>
                </div>
            </div>
        </div>
        <!-- end content-->
    </div>
    <!-- end wrapper -->
    <script src="../js/jquery-1.10.2.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/scripts.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDUdeTQ7RvCrXcACTn5lJUBUvTK6WOvXYg&callback=initMap&libraries=places"
            async defer></script>
</html>
