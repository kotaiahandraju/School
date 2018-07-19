<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>


	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-2.1.3.min.js"></script>
	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/jquery.validate.min.js"></script>
	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/additional-methods.min.js"></script>
		<!-- Dashboard Wrapper starts -->
		<div class="dashboard-wrapper">

			<!-- Top Bar starts -->
			<div class="top-bar">
				<div class="page-title">Dashboard</div>
			</div>
			<!-- Top Bar ends -->

			<!-- Main Container starts -->
			<div class="main-container">

				<!-- Container fluid Starts -->
				<div class="container-fluid">

					<!-- Current Stats Start -->
					<div class="current-stats">
						<div class="row">
							<div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
								<a href="viewAttendanceHome">
                                <div class="danger-bg center-align-text">
									<div class="spacer-xs">
										<small class="text-white">View</small>
										<h4 class="no-margin no-padding">Attendance</h4>
									</div>
								</div>
                                </a>
							</div>
							 <div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                            <a href="viewEvents">
								<div class="success-bg center-align-text">
									<div class="spacer-xs">
										<small class="text-white">View</small>
										<h4 class="no-margin no-padding text-white">Notifications</h4>
									</div>
								</div>
                                </a>
							</div> 
							<div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                            <a href="viewStudent">
								<div class="info-bg center-align-text">
									<div class="spacer-xs">
										<small class="text-white">View</small>
										<h4 class="no-margin no-padding">Students</h4>
									</div>
								</div>
                                </a>
							</div>
							<div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                            <a href="exportStudent">
								<div class="brown-bg center-align-text">
									<div class="spacer-xs">
										<small class="text-white">Export</small>
										<h4 class="no-margin no-padding">Students</h4>
									</div>
								</div>
                                </a>
							</div>
							<div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                            <a href="exportStudent">
								<div class="success-bg center-align-text">
									<div class="spacer-xs">
										<small class="text-white">Ledger</small>
										<h4 class="no-margin no-padding">Ledger</h4>
									</div>
								</div>
                                </a>
							</div>
							<div class="col-lg-2 col-md-4 col-sm-4 col-xs-6">
                            <a href="dailyFeesCollection">
								<div class="info-bg center-align-text">
									<div class="spacer-xs">
									<small class="text-white">Daily Fees</small>
										<h4 class="no-margin no-padding" title="Daily Fees Collection">Collection</h4>
									</div>
								</div>
                                </a>
							</div>
						</div>
					</div>
					<!-- Current Stats End -->

				</div>
				<!-- Container fluid ends -->

			</div>
			<!-- Main Container ends -->

		</div>
		<!-- Dashboard Wrapper ends -->

<!-- <script src="http://code.jquery.com/jquery-1.10.2.js"></script> -->
<!-- <script src="http://code.jquery.com/ui/1.11.2/jquery-ui.js"></script> -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
$( document ).ready(function() {

	
	
	var getTabName = window.location.pathname.split('/')[2];
	$("#das_li").addClass('active');
	$("#das_li ul").css('display','block');
	$("#das_li ul li a[href='"+ getTabName +"']").addClass('subactive');

	
});
</script>
