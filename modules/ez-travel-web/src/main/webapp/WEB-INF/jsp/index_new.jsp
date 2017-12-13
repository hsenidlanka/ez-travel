<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:useBean id="date" class="java.util.Date"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Home</title>
    <meta charset="utf-8">
    <meta name="format-detection" content="telephone=no"/>

    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/booking.css">
    <link rel="stylesheet" href="css/bootstrap.css">

    <script src="js/jquery.min.js"></script>
    <script src="js/scripts.js"></script>
    <script src="js/popup.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDUdeTQ7RvCrXcACTn5lJUBUvTK6WOvXYg&callback=initMap&libraries=places"
            async defer></script>
</head>
<body class="page1" id="top">
<div class="main">
    <!--==============================header=================================-->
    <header>
        <div class="menu_block ">
            <div class="container_12">
                <div class="grid_12">
                    <nav class="horizontal-nav full-width horizontalNav-notprocessed">
                        <ul class="sf-menu">
                            <a href="index.html"><img src="images/logo.png" alt="Your Happy Family"
                                                      style="width: 120px"></a>
                            <li class="current"><a href="index.html">Home</a></li>
                            <li><a href="index-1.html">About</a></li>
                            <li><a href="index-2.html">Cars</a></li>
                            <li><a href="index-4.html">Contacts</a></li>

                            <li><a data-toggle="modal" data-target="#popup">Login</a>
                            <li class="btn-custom"><a data-toggle="modal" data-target="#signup_popup">Signup</a></li>
                        </ul>
                    </nav>
                    <div class="clear"></div>
                </div>
                <div class="clear"></div>
            </div>
        </div>
        <div class="clear"></div>
    </header>
    <div class="slider_wrapper ">
        <div id="camera_wrap" class="">
            <div src="images/slide.jpg"></div>
            <div data-src="images/slide1.jpg"></div>
            <div data-src="images/slide2.jpg"></div>
        </div>
    </div>
    <div class="container_12">
        <div class="grid_4">
            <div class="banner">
                <div class="maxheight">
                    <div class="banner_title">
                        <img src="images/icon1.png" alt="">
                        <div class="extra_wrapper">Fast&amp;
                            <div class="color1">Safe</div>
                        </div>
                    </div>
                    Dorem ipsum dolor sit amet, consectetur adipiscinger elit. In mollis erat mattis neque facilisis,
                    sit ameter ultricies erat rutrum. Cras facilisis, nulla vel viver auctor, leo magna sodales felis,
                    quis malesuad
                    <a href="#" class="fa fa-share-square"></a>
                </div>
            </div>
        </div>
        <div class="grid_4">
            <div class="banner">
                <div class="maxheight">
                    <div class="banner_title">
                        <img src="images/icon2.png" alt="">
                        <div class="extra_wrapper">Best
                            <div class="color1">Prices</div>
                        </div>
                    </div>
                    Hem ipsum dolor sit amet, consectetur adipiscinger elit. In mollis erat mattis neque facilisis, sit
                    ameter ultricies erat rutrum. Cras facilisis, nulla vel viver auctor, leo magna sodales felis, quis
                    malesuader
                    <a href="#" class="fa fa-share-square"></a>
                </div>
            </div>
        </div>
        <div class="grid_4">
            <div class="banner">
                <div class="maxheight">
                    <div class="banner_title">
                        <img src="images/icon3.png" alt="">
                        <div class="extra_wrapper">Package
                            <div class="color1">Delivery</div>
                        </div>
                    </div>
                    Kurem ipsum dolor sit amet, consectetur adipiscinger elit. In mollis erat mattis neque facilisis,
                    sit ameter ultricies erat rutrum. Cras facilisis, nulla vel viver auctor, leo magna sodales felis,
                    quis malesuki
                    <a href="#" class="fa fa-share-square"></a>
                </div>
            </div>
        </div>
        <div class="clear"></div>
    </div>
    <div class="c_phone">
        <div class="container_12">
            <div class="grid_12">
                <div class="fa fa-phone"></div>
                <fmt:message key="hotline.number"/>
                <span>ORDER NOW!</span>
            </div>
            <div class="clear"></div>
        </div>
    </div>
    <!--==============================Content=================================-->
    <div class="content">
        <div class="container_12">
            <div class="grid_5">
                <h3>Need a Taxi?</h3>
                <form:form method="POST" id="bookingForm" action="hire" modelAttribute="hire">
                    <div class="fl1">
                        <div class="tmInput">
                            <form:input path="pickup" name="pickup" id="pickup" placeHolder="From:" type="text"/>
                        </div>
                    </div>
                    <div class="fl1">
                        <div class="tmInput mr0">
                            <form:input path="drop" name="drop" id="drop" placeHolder="To:" type="text"/>
                        </div>
                    </div>
                    <div class="fl1">
                        <div class="tmInput">
                            <form:select path="vehicleType" class="form-control input-sm chat-input" id="vehicleType">
                                <option selected disabled>Vehicle Type</option>
                                <option>Budget</option>
                                <option>Hibrid</option>
                                <option>Van</option>
                            </form:select>
                        </div>
                    </div>
                    <div class="clear"></div>
                    <a href="#" class="btn" data-type="submit" id="index_booking_btn">Book Now!</a>
                </form:form>
            </div>
            <div class="grid_6 prefix_1" id="div_map" style="margin-top: 2em; width: 40em; height: 30em;"></div>
            <div class="clear"></div>
        </div>
    </div>
</div>

<!-- ==================popup driver passenger select======================-->

<div class="modal modalbox modal-transparent fade " id="popup" role="dialog">

    <!-- Modal content-->
    <div class=" box">
        <div style="padding-bottom: 36px;">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="content">
            <div class="col-md-6 image" style="width:280px;">
                <a href="driver/login"><img class="image" src="images/driver.png" id="imagepreview"
                                            style="width: 250px; height: 260px;"></a>
            </div>
            <div class="col-md-6 image" style="width:280px;padding-left: 45px;">
                <a href="customer/login"><img class="image" src="images/passenger.png" id="imagepreview"
                                              style="width: 250; height: 260px;"></a>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal modalbox modal-transparent fade " id="signup_popup" role="dialog">

    <!-- Modal content-->
    <div class=" box">
        <div style="padding-bottom: 36px;">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="content">
            <div class="col-md-6 image" style="width:280px;">
                <a href="driver/signup"><img class="image" src="images/driver.png" id="imagepreview"
                                             style="width: 250px; height: 260px;"></a>
            </div>
            <div class="col-md-6 image" style="width:280px;padding-left: 45px;">
                <a href="customer/signup"><img class="image" src="images/passenger.png" id="imagepreview"
                                               style="width: 250; height: 260px;"></a>
            </div>
        </div>
    </div>
</div>
<!--==============================footer=================================-->
<footer>
    <div class="container_12">
        <div class="grid_12">
            <div class="f_phone"><span>Call Us:</span> <fmt:message key="hotline.number"/></div>
            <div class="socials">
                <a href="#" class="fa fa-twitter"></a>
                <a href="#" class="fa fa-facebook"></a>
                <a href="#" class="fa fa-google-plus"></a>
            </div>
            <div class="copy">
                <div class="st1">
                    <div class="brand">ez<span class="color1">T</span>ravel</div>
                    &copy; <fmt:formatDate value="${date}" pattern="yyyy"/> | <a href="#">Privacy Policy</a></div>
                Website designed by <a href="<fmt:message key="designer.web"/>" rel="nofollow"><fmt:message
                    key="designer.name"/></a>
            </div>
        </div>
        <div class="clear"></div>
    </div>
</footer>
