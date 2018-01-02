<nav class="navbar-default navbar-static-side" role="navigation" id=navbar-side>
    <!-- sidebar-collapse -->
    <div class="sidebar-collapse">
        <!-- side-menu -->
        <ul class="nav" id="side-menu">
            <li>
                <!-- user image section-->
                <div class="user-section">
                    <div class="user-section-inner">
                        <img src="images/user.jpg" alt="">
                    </div>
                    <div class="user-info">
                        <div>${first_name} <strong>${last_name}</strong></div>
                        <div class="user-text-online">
                            <span class="user-circle-online btn btn-success btn-circle "></span>&nbsp;Online
                        </div>
                    </div>
                </div>
                <!--end user image section-->
            </li>
            <li>
                <a href="login"><i class="fa fa-map-marker fa-fw"></i> Admin</a>
            </li>
            <li>
                <a href="myTrips"><i class="fa fa-bar-chart-o fa-fw"></i> Driver</a>
            </li>
            <li>
                <a href="defaultLocations"><i class="fa fa-location-arrow fa-fw"></i> Customer</a>
            </li>
            <li class="selected">
                <a href="settings"><i class="fa fa-cogs fa-fw"></i> Payment</a>
            </li>
            <li>
                <a href="logout"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
            </li>
        </ul>
        <!-- end side-menu -->
    </div>
    <!-- end sidebar-collapse -->
</nav>
