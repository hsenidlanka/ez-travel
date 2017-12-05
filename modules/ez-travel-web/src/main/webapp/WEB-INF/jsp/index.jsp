<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <title>ez-travel</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDUdeTQ7RvCrXcACTn5lJUBUvTK6WOvXYg&callback=initMap&libraries=places"
            async defer></script>

</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <nav class="navbar navbar-default" role="navigation">
                <div class="navbar-header">

                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span
                            class="icon-bar"></span><span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">ez-travel</a>
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="active">
                            <a href="#">Driver</a>
                        </li>
                        <li>
                            <a href="#">Ride</a>
                        </li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="customer/login">Login</a>
                        </li>
                        <li class="btn-custom">
                            <a href="customer/signup">Signup</a>
                        </li>
                    </ul>
                </div>

            </nav>
            <div class="carousel slide" id="carousel-235482">
                <ol class="carousel-indicators">
                    <li class="active" data-slide-to="0" data-target="#carousel-235482">
                    </li>
                    <li data-slide-to="1" data-target="#carousel-235482">
                    </li>
                    <li data-slide-to="2" data-target="#carousel-235482">
                    </li>
                </ol>
                <div class="carousel-inner" id="img_slider">
                    <div class="item active">
                        <img alt="Carousel Bootstrap First" src="images/img_01.jpg" class="slider-image">
                        <!--<div class="carousel-caption">
                            <h4>
                                First Thumbnail label
                            </h4>
                            <p>
                                Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
                            </p>
                        </div> -->
                    </div>
                    <div class="item">
                        <img alt="Carousel Bootstrap Second" src="images/img_02.jpg" class="slider-image">
                    </div>
                    <div class="item">
                        <img alt="Carousel Bootstrap Third" src="images/img_03.jpg" class="slider-image">
                    </div>
                </div>
                <a class="left carousel-control" href="#carousel-235482" data-slide="prev"><span
                        class="glyphicon glyphicon-chevron-left"></span></a> <a class="right carousel-control"
                                                                                href="#carousel-235482"
                                                                                data-slide="next"><span
                    class="glyphicon glyphicon-chevron-right"></span></a>
            </div>
            <!--<ul class="nav nav-tabs">
                <li class="active">
                    <a href="#">ez-traval</a>
                </li>
                <li>
                    <a href="#">Driver</a>
                </li>
                <li class="disabled">
                    <a href="#">Rider</a>
                </li>
                <li class="dropdown pull-right">
                     <a href="#" data-toggle="dropdown" class="dropdown-toggle">Dropdown<strong class="caret"></strong></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="#">Action</a>
                        </li>
                        <li>
                            <a href="#">Another action</a>
                        </li>
                        <li>
                            <a href="#">Something else here</a>
                        </li>
                        <li class="divider">
                        </li>
                        <li>
                            <a href="#">Separated link</a>
                        </li>
                    </ul>
                </li>
            </ul> -->
            <div class="jumbotron col-md-6">
                <form action="#" method="post" id="index_hire_form">
                    <div class="form-group row">
                        <label for="pickup" class="col-sm-2 col-form-label form_lable">Pickup</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="pickup" placeholder="Pickup">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="drop" class="col-sm-2 col-form-label form_lable">Drop</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="drop" placeholder="Drop">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="estimated_cost" class="col-sm-2 col-form-label form_lable">Estimated cost</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="estimated_cost" placeholder="Estimated cost">
                        </div>
                    </div>
                    <div class="form-group row">
                        <input type="submit" value="Book Now" class="btn btn-primary" id="btn_book_now">
                        <input type="button" value="Cancel" class="btn btn-primary" id="Cancel">
                    </div>
                </form>
            </div>
            <div class="jumbotron col-md-6" id="map">

            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4">
            <div class="jumbotron">
                <h4>
                    The ez-travel App
                </h4>
                <p>
                    Developed with modern technology, ez-travel cabs phone app uses GPS
                    to identify the customer location and connects the customer
                    with the nearest available driver. The booking will be confirmed
                    immediately and will be informed to the customer via an SMS.
                </p>
            </div>
        </div>
        <div class="col-md-4">
            <div class="jumbotron">
                <h4>
                    We Drive People Happy
                </h4>
                <p>
                    For us it isn’t just our working spirit but it’s an ethos which
                    extends to our Corporate Social responsibility activities as well.
                    We pride ourselves on responding to the needs of our fellow
                    Sri Lankans uplifting their spirit, lives and social well-being.
                </p>
            </div>
        </div>
        <div class="col-md-4">
            <div class="jumbotron">
                <h4>
                    SAFETY FIRST
                </h4>
                <p>
                    Safety is always first and Our drivers are well trained with any
                    emergency such as First Aids etc...
                </p>
            </div>
        </div>
    </div>
</div>

</body>
</html>