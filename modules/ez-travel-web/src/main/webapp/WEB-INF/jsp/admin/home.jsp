<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script src="js/viewFeedback.js"></script>
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
                        <c:forEach items="${feedback_list}" var="feedback">
                            <li class="item">
                                <div class="product-info">
                                    <span class="product-description"
                                          id="feedback_description">${feedback.description}
                                        <div style="float: right;">
                                            <button type="button" class="btn btn-danger" data-toggle="modal"
                                                    data-target="#feedback_popup" style="padding: 0px"><i
                                                    class="fa fa-ban fa-fw"></i>
                                            </button>
                                        </div>
                                    </span>
                                    <input type="hidden" id="feedback_id" value="${feedback.feedback_id}">
                                    <input type="hidden" id="driver_id" value="${feedback.driver_id}">
                                    <input type="hidden" id="customer_id" value="${feedback.customer_id}">
                                    <input type="hidden" id="hire_id" value="${feedback.hire_id}">
                                </div>
                            </li>
                            <!-- /.item -->
                        </c:forEach>
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
