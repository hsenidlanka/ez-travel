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
    <script src="js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>
    <script src="js/costCalculate.js"></script>
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
    <div>
        <img src="images/taxi-3.jpeg" style="margin-top: 30px"></img>
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
                    We operate 24/7, 365 days around the clock. Geared with latest state
                    of the art modern technology we ensure that our customers enjoy the
                    convenience and best of ez-travel experience. All the cars are equipped
                    with GPS tracking system for prompt executions. SMS messaging systems
                    are incorporated to give out information to our clients with time of
                    dispatch, type of car and plate numbers, the all new mobile phone app
                    enables the customers to place bookings even faster.
                    <a class="fa fa-share-square"></a>
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
                    Budget cars at everyday prices are always available. For special
                    occasions, no occasion at all, or when you just a need a bit more
                    room, call a black Hibrid or Van. Prices are change based on vehicle
                    category and distence you ride.

                    Big fleet of vehicles from 4 seat cars up to 8 seat minibuses.
                    If all our vehicles are busy we use trusted partners to cover
                    our bookings.
                    <a class="fa fa-share-square"></a>
                </div>
            </div>
        </div>
        <div class="grid_4">
            <div class="banner">
                <div class="maxheight">
                    <div class="banner_title">
                        <img src="images/icon3.png" alt="">
                        <div class="extra_wrapper">Service&amp;
                            <div class="color1">Support</div>
                        </div>
                    </div>
                    Our travel portal account is open to travel operators looking to make
                    and manage bookings online. By signing up to the business account you
                    will receive a login which give you access to special platform designed
                    for travel operators who take bookings over the phone.

                    You will be able to make bookings and manage payments online plus you
                    will also be assigned your own account manager who is available to
                    assist you with any questions you may have.
                    <a class="fa fa-share-square"></a>
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
                <form id="bookingForm" action="" modelAttribute="hire">
                    <div class="fl1">
                        <div class="tmInput">
                            <input path="pickup" name="pickup" id="pickup" placeHolder="From:" type="text"/>
                        </div>
                    </div>
                    <div class="fl1">
                        <div class="tmInput mr0">
                            <input path="drop" name="drop" id="drop" placeHolder="To:" type="text"/>
                        </div>
                    </div>
                    <div class="fl1" style="padding-top: 16px; margin-left: 0px">
                        <div class="tmInput">
                            <select path="vehicleType" class="form-control input-sm chat-input" id="vehicleType"
                                    name="vehicleType">
                                <option selected disabled>Vehicle Type</option>
                                <option>budget</option>
                                <option>hibrid</option>
                                <option>van</option>
                            </select>
                        </div>
                    </div>
                    <div class="clear"></div>
                    <input type="hidden" id="length" name="length"/>
                    <button type="button" class="btn" id="find_cost_btn">Find Cost</button>
                </form>

                <div id="cost_result" style="display: none; padding-right: 36px; padding-top: 16px">
                    <table class="table">
                        <tbody>
                        <tr>
                            <td scope="row">Estimated cost</td>
                            <td id="cost"></td>
                        </tr>
                        <tr>
                            <td scope="row" colspan="2">
                                <button type="button" id="sign_in_for_booking" class="btn"
                                        style="width: 100%; height: 42px"/>
                                Find a Taxi
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="grid_6 prefix_1" id="map" style="margin-top: 2em; width: 40em; height: 30em;"></div>
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
                                              style="width: 250px; height: 260px;"></a>
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
