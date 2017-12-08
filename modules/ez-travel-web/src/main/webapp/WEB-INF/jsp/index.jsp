<<<<<<< HEAD
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:useBean id="date" class="java.util.Date" />
<fmt:setBundle basename="messages" />
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Home</title>
    <meta charset="utf-8">
    <meta name = "format-detection" content = "telephone=no" />
    <link rel="icon" href="images/favicon.ico">
    <link rel="shortcut icon" href="images/favicon.ico" />
    <link rel="stylesheet" href="css/camera.css">
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/booking.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/jquery-migrate-1.2.1.js"></script>
    <script src="js/script.js"></script>
    <script src="js/scripts.js"></script>
    <script src="js/superfish.js"></script>
    <script src="js/jquery.ui.totop.js"></script>
    <script src="js/jquery.equalheights.js"></script>
    <script src="js/jquery.mobilemenu.js"></script>
    <script src="js/jquery.easing.1.3.js"></script>
    <script src="js/owl.carousel.js"></script>
    <script src="js/camera.js"></script>
    <!--[if (gt IE 9)|!(IE)]><!-->
    <script src="js/jquery.mobile.customized.min.js"></script>
    <!--<![endif]-->
    <script src="js/booking.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDUdeTQ7RvCrXcACTn5lJUBUvTK6WOvXYg&callback=initMap&libraries=places"
            async defer></script>
    <script>
        $(document).ready(function(){
            jQuery('#camera_wrap').camera({
                loader: false,
                pagination: false ,
                minHeight: '444',
                thumbnails: false,
                height: '28.28125%',
                caption: true,
                navigation: true,
                fx: 'mosaic'
            });
            $().UItoTop({ easingType: 'easeOutQuart' });
        });
    </script>
    <!--[if lt IE 8]>
    <div style=' clear: both; text-align:center; position: relative;'>
        <a href="http://windows.microsoft.com/en-US/internet-explorer/products/ie/home?ocid=ie6_countdown_bannercode">
            <img src="http://storage.ie6countdown.com/assets/100/images/banners/warning_bar_0000_us.jpg" border="0" height="42" width="820" alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today." />
        </a>
    </div>
    <![endif]-->
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <link rel="stylesheet" media="screen" href="css/ie.css">
    <![endif]-->
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
                            <li class="current"><a href="index.html">Home</a></li>
                            <li><a href="index-1.html">About</a></li>
                            <li><a href="index-2.html">Cars</a></li>
                            <li><a href="index-3.html">Services</a></li>
                            <li><a href="index-4.html">Contacts</a></li>
                        </ul>
                    </nav>
                    <div class="clear"></div>
                </div>
                <div class="clear"></div>
            </div>
        </div>
        <div class="container_12">
            <div class="grid_12">
                <h1>
                    <a href="index.html">
                        <img src="images/logo.png" alt="Your Happy Family">
                    </a>
                </h1>
            </div>
        </div>
        <div class="clear"></div>
    </header>
    <div class="slider_wrapper ">
        <div id="camera_wrap" class="">
            <div data-src="images/slide.jpg" ></div>
            <div data-src="images/slide1.jpg" ></div>
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
                    Dorem ipsum dolor sit amet, consectetur adipiscinger elit. In mollis erat mattis neque facilisis, sit ameter ultricies erat rutrum. Cras facilisis, nulla vel viver auctor, leo magna sodales felis, quis malesuad
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
                    Hem ipsum dolor sit amet, consectetur adipiscinger elit. In mollis erat mattis neque facilisis, sit ameter ultricies erat rutrum. Cras facilisis, nulla vel viver auctor, leo magna sodales felis, quis malesuader
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
                    Kurem ipsum dolor sit amet, consectetur adipiscinger elit. In mollis erat mattis neque facilisis, sit ameter ultricies erat rutrum. Cras facilisis, nulla vel viver auctor, leo magna sodales felis, quis malesuki
                    <a href="#" class="fa fa-share-square"></a>
                </div>
            </div>
        </div>
        <div class="clear"></div>
    </div>
    <div class="c_phone">
        <div class="container_12">
            <div class="grid_12">
                <div class="fa fa-phone"></div><fmt:message key="hotline.number" />
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
                            <form:input path="pickup" name="pickup" id="pickup" placeHolder="From:" type="text" />
                        </div>
                    </div>
                    <div class="fl1">
                        <div class="tmInput mr0">
                            <form:input path="drop" name="drop" id="drop" placeHolder="To:" type="text" />
                        </div>
                    </div>
                    <div class="clear"></div>
                    <a href="#" class="btn" data-type="submit">Book Now!</a>
                </form:form>
            </div>
            <div class="grid_6 prefix_1" id="div_map" style="margin-top: 2em; width: 40em; height: 30em;"></div>
            <div class="clear"></div>
        </div>
    </div>
</div>
<!--==============================footer=================================-->
<footer>
    <div class="container_12">
        <div class="grid_12">
            <div class="f_phone"><span>Call Us:</span> <fmt:message key="hotline.number" /></div>
            <div class="socials">
                <a href="#" class="fa fa-twitter"></a>
                <a href="#" class="fa fa-facebook"></a>
                <a href="#" class="fa fa-google-plus"></a>
            </div>
            <div class="copy">
                <div class="st1">
                    <div class="brand">ez<span class="color1">T</span>ravel </div>
                    &copy; <fmt:formatDate value="${date}" pattern="yyyy" /> | <a href="#">Privacy Policy</a> </div> Website designed by <a href="<fmt:message key="designer.web"/>" rel="nofollow"><fmt:message key="designer.name" /></a>
            </div>
        </div>
        <div class="clear"></div>
    </div>
</footer>
<script>
    $(function (){
        $('#bookingForm').bookingForm({
            ownerEmail: '#'
        });
    })
    $(function() {
        $('#bookingForm input, #bookingForm textarea').placeholder();
    });
</script>