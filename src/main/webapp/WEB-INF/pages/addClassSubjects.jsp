<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>


	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-2.1.3.min.js"></script>
	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/jquery.validate.min.js"></script>
	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/additional-methods.min.js"></script>

	<script type="text/javascript" src="js/classSubjects.js"></script>
	
<!-- 	<div class="loadingajax" id="loadAjax" style="display:none"></div> -->
	
		<!-- Dashboard Wrapper starts -->
		<div class="dashboard-wrapper">

			<!-- Top Bar starts -->
			<div class="top-bar">
				<div class="page-title" id="headId">Class Subjects</div>
			</div>
			<!-- Top Bar ends -->

			<!-- Main Container starts -->
			<div class="main-container">

				<!-- Container fluid Starts -->
				<div class="container-fluid">
<ol class="breadcrumb">
    	<li><a href="dashBoard">Home</a></li>
    	<li><a href="#">Configurations</a></li>
		<li><a href="#">Class Subjects</a></li>
	</ol>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>List of Class Subjects</h4>
					</div>
					<div class="panel-body collapse in">
					<!-- Spacer starts -->
					
						<!-- Row Starts -->
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="col-md-4">
									<form:form action="saveClassSubject" commandName="classSubjectCmd" method="post" id="cls-form" class="form-horizontal">
									<div class="row">
											<div class="form-group">
											    <label for="inputEmail3" class="col-sm-4 control-label">Board Name</label>
											    <div class="col-sm-6">
												    <form:select path="boardId" tabindex="1" class="form-control" required="true" onchange="classNameFilter();">
														<form:option value="">-- Choose Board --</form:option>
														<form:options items="${board}"></form:options>
													</form:select>
													<span class="boardId_error" id="boardId_error"></span>
												</div>
											</div>
											<div class="form-group">
											    <label for="inputEmail3" class="col-sm-4 control-label">Class/Branch</label>
											    <div class="col-sm-6">
												    <form:select path="classId" tabindex="3" class="form-control" required="true">
														<form:option value="">-- Choose Class --</form:option>
														<form:options items="${allClasses}"></form:options>
													</form:select>
<%-- 													<form:input path="className" class="form-control" tabindex="3" placeholder="Enter Class/Branch" required="true"/> --%>
													<span class="className_error" id="className_error"></span>
												</div>
											</div>
											<div class="form-group">
											    <label for="inputEmail3" class="col-sm-4 control-label">Class Subjects</label>
											    <div class="col-sm-6">
												    <form:select path="subjectId" tabindex="2" class="form-control" required="true">
														<form:option value="">-- Choose Subject--</form:option>
														<form:options items="${subject}" ></form:options>
													</form:select>
													<span class="mediumId_error" id="mediumId_error"></span>
												</div>
											</div>
									</div>
									<div class="row">
										<div class="col-sm-8 col-sm-offset-5">
											<form:hidden path="id"/>
											<div class="form-group">
													<input type="submit" id="submitId" value="Submit" class="btn btn-success" tabindex="6"/>
													<button type="button" class="btn btn-danger" id="cancel" tabindex="7">Reset</button>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-8 col-sm-offset-4">
											<div class="form-group">
												<div class="col-sm-8 col-sm-offset-2">
												<%
													String message = null;
													message=(String)session.getAttribute("message");
											        if(message!=null)
											        {
														out.println("<span class='animated fadeOut' style='animation-iteration-count:1;animation-duration:8s;color: red;'>"+message+"</span>");
														session.setAttribute("message", null);
													}
										        %>
												</div>
											</div>
										</div>
									</div>
									</form:form>
									</div>
									<div class="col-md-8 gutter">
									<div class="blog">
									<div class="blog-body">
										<div class="">
											<div id="basicExample_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">
												<div class="row">
													<div class="col-sm-12">
 														<table id="basicExample" class="table table-striped table-condensed table-bordered no-margin dataTable" role="grid" aria-describedby="basicExample_info">
															<thead>
																<tr role="row">
																	<th class="sorting_asc" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Name: activate to sort column descending">Board</th>
																	<th class="sorting" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Office: activate to sort column ascending">Class</th>
																	<th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Age: activate to sort column ascending">Subjects</th>
																	<th class="sorting" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Salary: activate to sort column ascending">Action</th>
																</tr>
															</thead>
															<!-- <tfoot>
																<tr><th rowspan="1" colspan="1">Board</th><th rowspan="1" colspan="1">Medium</th><th rowspan="1" colspan="1">Class</th><th rowspan="1" colspan="1">Section</th><th rowspan="1" colspan="1">Fees</th><th rowspan="1" colspan="1">Action</th></tr>
															</tfoot> -->
															<tbody>
																
															</tbody>
														</table>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
									</div>
									
							</div>
						</div>
						<!-- Row Ends -->
						
						<!-- Row Starts -->
						<div class="row gutter">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								
							</div>
						</div>
						<!-- Row Ends -->
					<!-- Spacer ends -->

				</div>
				<!-- Container fluid ends -->

			</div>
			<!-- Main Container ends -->

		</div>
		<!-- Dashboard Wrapper ends -->

<!-- <script src="http://code.jquery.com/jquery-1.10.2.js"></script> -->
<!-- <script src="http://code.jquery.com/ui/1.11.2/jquery-ui.js"></script> -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script>
	var listOrders1 = ${allOrders1};
	if (listOrders1 != "") {
		displayTable(listOrders1);
	}else{
		$("#emptyMessageId").val("No Results Found");
	}
	

</script>
