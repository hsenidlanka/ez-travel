<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/custom-style.css" rel="stylesheet">
<link href="css/main-style.css" rel="stylesheet">
<link href="css/formStyle.css" rel="stylesheet">
<link href="css/admin.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/chart.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.bundle.js"></script>

<body>
<%@include file="header.jsp" %>
<%@include file="sideNavBar.jsp" %>

<div id="page-wrapper" style="margin-top: 69px">
    <div class="row" style="padding-top: 15px">
        <div class="col-md-4 col-sm-4 col-xs-12">
            <div class="info-box">
                <span class="info-box-icon bg-aqua"><i class="fa fa-taxi"></i></span>

                <div class="info-box-content">
                    <span class="info-box-text">Drivers</span>
                    <span class="info-box-number">${driverCount}</span>
                </div>
                <!-- /.info-box-content -->
            </div>
            <!-- /.info-box -->
        </div>
        <!-- /.col -->
        <div class="col-md-4 col-sm-4 col-xs-12">
            <div class="info-box">
                <span class="info-box-icon bg-red"><i class="fa fa-users"></i></span>

                <div class="info-box-content">
                    <span class="info-box-text">Customers</span>
                    <span class="info-box-number">${customerCount}</span>
                </div>
                <!-- /.info-box-content -->
            </div>
            <!-- /.info-box -->
        </div>
        <!-- /.col -->

        <div class="col-md-4 col-sm-4 col-xs-12">
            <div class="info-box">
                <span class="info-box-icon bg-green"><i class="fa fa-money"></i></span>

                <div class="info-box-content">
                    <span class="info-box-text">Payment</span>
                    <span class="info-box-number">760,000</span>
                </div>
                <!-- /.info-box-content -->
            </div>
            <!-- /.info-box -->
        </div>
    </div>


    <div class="row">
        <div class="col-md-8 col-sm-8 col-xs-12">
            <canvas id="myChart" style="margin-top: 42px"></canvas>
        </div>
        <!--------------------------- feedback --------------------------------------->
        <div class="col-md-4 col-sm-4 col-xs-12">
            <div class="box box-primary" style="padding-left: 0px; padding-right: 5px">
                <div class="box-header with-border">
                    <h3 class="box-title">User feedbacks</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body" style="">
                    <ul class="products-list product-list-in-box">
                        <li class="item">
                            <div class="product-img">
                                <img src="dist/img/default-50x50.gif" alt="Product Image">
                            </div>
                            <div class="product-info">
                                <a href="javascript:void(0)" class="product-title">Samsung TV
                                    <span class="label label-warning pull-right">$1800</span></a>
                                <span class="product-description">
                          Samsung 32" 1080p 60Hz LED Smart HDTV.
                        </span>
                            </div>
                        </li>
                        <!-- /.item -->
                        <li class="item">
                            <div class="product-img">
                                <img src="dist/img/default-50x50.gif" alt="Product Image">
                            </div>
                            <div class="product-info">
                                <a href="javascript:void(0)" class="product-title">Bicycle
                                    <span class="label label-info pull-right">$700</span></a>
                                <span class="product-description">
                          26" Mongoose Dolomite Men's 7-speed, Navy Blue.
                        </span>
                            </div>
                        </li>
                        <!-- /.item -->
                        <li class="item">
                            <div class="product-img">
                                <img src="dist/img/default-50x50.gif" alt="Product Image">
                            </div>
                            <div class="product-info">
                                <a href="javascript:void(0)" class="product-title">Xbox One <span
                                        class="label label-danger pull-right">$350</span></a>
                                <span class="product-description">
                          Xbox One Console Bundle with Halo Master Chief Collection.
                        </span>
                            </div>
                        </li>
                        <!-- /.item -->
                        <li class="item">
                            <div class="product-img">
                                <img src="dist/img/default-50x50.gif" alt="Product Image">
                            </div>
                            <div class="product-info">
                                <a href="javascript:void(0)" class="product-title">PlayStation 4
                                    <span class="label label-success pull-right">$399</span></a>
                                <span class="product-description">
                          PlayStation 4 500GB Console (PS4)
                        </span>
                            </div>
                        </li>
                        <!-- /.item -->
                    </ul>
                </div>
                <!-- /.box-body -->
                <div class="box-footer text-center" style="">
                    <a href="#" class="uppercase">View All Products</a>
                </div>
                <!-- /.box-footer -->
            </div>
        </div>
    </div>
</div>
</body>
