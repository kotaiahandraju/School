<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-2.1.3.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/jquery.validate.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/additional-methods.min.js"></script>
<script type="text/javascript" src="js/export.js"></script>

		<!-- Dashboard Wrapper starts -->
		<div class="dashboard-wrapper">

			<!-- Top Bar starts -->
			<div class="top-bar">
				<div class="page-title">
					Export Students
				</div>
				
			</div>
			<!-- Top Bar ends -->

			<!-- Main Container starts -->
			<div class="main-container">

				<!-- Container fluid Starts -->
				<div class="container-fluid">

					<!-- Spacer starts -->
					<div class="spacer">
					
						<!-- Row Starts -->
						<div class="row" style="">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="blog" style="border: 1px solid;">
									<!-- <div class="blog-header">
										<h5 class="blog-title">Export Students</h5>
									</div> -->
									<div class="blog-body">
										<form:form id="demo-form1" action="exportStudent1.htm" commandName="packCmd" method="post" class="form-horizontal">
										<div class="row">
											<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">  
												<div class="form-group">
													<label for="inputPassword3" class="col-sm-4 control-label">Board Name: </label>
													<div class="col-sm-8">
														<form:select path="boardName" class="form-control" tabindex="2" onchange="classNameFilter()">
															<form:option value="" >-- Choose Board --</form:option>
															<form:options items="${board}"></form:options>
														</form:select>
													</div>
												</div>
											</div>
											<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
												<div class="form-group">
												    <label for="inputPassword3" class="col-sm-4 control-label">Class: </label>
												    <div class="col-sm-8">
														<form:select path="className" class="form-control" tabindex="3" onchange="sectionFilter();">
															<form:option value="">-- Choose Class --</form:option>
															<form:options items="${allClasses}"></form:options>
														</form:select>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
											  	<div class="form-group">
												    <label for="inputPassword3" class="col-sm-4 control-label">Section: </label>
												    <div class="col-sm-8">
														<form:select path="section" class="form-control" tabindex="4" onchange="mediumFilter();">
															<form:option value="">-- Choose Section --</form:option>
															<form:options items="${allSection}"></form:options>
														</form:select>
													</div>
											  	</div>
											</div>
											<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
											  	<div class="form-group">
											    	<label for="inputPassword3" class="col-sm-4 control-label">Medium: </label>
												    <div class="col-sm-8">
														<form:select path="medium" class="form-control" tabindex="5" onchange="getFee();">
															<form:option value="">-- Choose Medium --</form:option>
															<form:options items="${mediam}"></form:options>
														</form:select>
													</div>
											  	</div>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-8 col-sm-offset-3">
											  	<div class="form-group">
												  	<div class="col-sm-8 col-sm-offset-2">
												  		<button type="submit" class="btn btn-success">Export</button>
												  		<button type="reset" id="cancel" class="btn btn-danger">Reset</button>
												  	</div>
											  	</div>
											</div>
										</div>
										</form:form>

									</div>
								</div>
							</div>
						</div>
						<!-- Row Ends -->
						
					</div>
					<!-- Spacer ends -->

				</div>
				<!-- Container fluid ends -->

			</div>
			<!-- Main Container ends -->

		</div>
		<!-- Dashboard Wrapper ends -->

