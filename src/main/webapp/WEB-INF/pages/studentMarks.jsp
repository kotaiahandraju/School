<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-2.1.3.min.js"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/jquery.validate.min.js"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/additional-methods.min.js"></script>
<script type="text/javascript" src="js/studentMarks.js"></script>
<style>
.btnsm {
	margin: 0.5px;
}
.form-horizontal .control-label {
    padding-top: 7px;
    margin-bottom: 0;
    text-align: right;
    margin-bottom: 8px;
}
</style>
<!-- Dashboard Wrapper starts -->
<div class="dashboard-wrapper">

	<!-- Top Bar starts -->
	<div class="top-bar">
		<div class="page-title" id="headId">Student Marks</div>
	</div>
	<!-- Top Bar ends -->

	<!-- Main Container starts -->
	<div class="main-container">

		<!-- Container fluid Starts -->
		<div class="container-fluid">

			<!-- Spacer starts -->
<ol class="breadcrumb">
    	<li><a href="dashBoard">Home</a></li>
    	<li><a href="#">Student Details</a></li>
		<li><a href="#">Student Marks</a></li>
	    </ol>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>Filter Students</h4>
					</div>
					<div class="panel-body collapse in">
				<!-- Row Starts -->
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<%-- 									<%${message} %> --%>

			<form:form action="#" commandName="studentMarksCmd" method="post" id="cls-form" class="form-horizontal">
									<div class="form-group row">
										<div class="col-md-2">
											<label class="control-label">Board Name:</label>
												<form:select path="boardId" tabindex="1"
													class="form-control" required="true"
													onchange="classNameFilter(this.id);">
													<form:option value="">-- Choose Board --</form:option>
													<form:options items="${board}" />
												</form:select>
										</div>
										<div class="col-md-2">
											<label class="control-label">Class:</label>
												<form:select path="classId" tabindex="2"
													class="form-control" required="true"
													onchange="getClassBySection(this.id);">
													<form:option value="">--Choose Class--</form:option>
													<%-- <form:options items="${subject}" ></form:options> --%>
												</form:select>

										</div>
										<div class="col-md-2">
											<label class="control-label">Section:</label>
												<form:select path="sectionId" tabindex="2"
													class="form-control" required="true"
													onchange="getClassBySectionByStudent(this.id);">
													<form:option value="">--Choose Section--</form:option>
													<%-- <form:options items="${subject}" ></form:options> --%>
												</form:select>

										</div>
										<div class="col-md-2">
											<label class="control-label">Student:</label>
												<form:select path="studentId" tabindex="1"
													class="form-control" required="true"
													onchange="getClassBySectionByStudentByExamType(this.id);">
												<!-- 	<option value="">-- Select Student --</option> -->
													<form:option value="">--Select Student--</form:option>
<%-- 													<form:options items="${allStudents}"></form:options> --%>
												</form:select>
										</div>
										<div class="col-md-2">
											<label class="control-label">Exam Type:</label>
												<form:select path="examTypeId" tabindex="1"
													class="form-control" required="true">
													<form:option value="">-- Exam Type --</form:option>
													<form:options items="${examType}"></form:options>
													<%-- <c:forEach var="exampType" items="${exampType}">
														<option value="${exampType.key}">${exampType.value}</option>
													</c:forEach> --%>
												</form:select>
											</div>
										</div>
									
									
									
									
									<%-- 											<form:hidden path="id"/> --%>

									<div class="form-group">
										<div class="col-sm-8 col-sm-offset-4">
											<%
												String message = null;
													message = (String) session.getAttribute("message");
													if (message != null) {
														out.println(
																"<span class='animated fadeIn' style='animation-iteration-count: 7;animation-duration: 1s;color: red;'>"
																		+ message + "</span>");
														session.setAttribute("message", null);
													}
											%>
										</div>
									</div>
									
									
									<div class="row">
										<div class="col-sm-8 col-sm-offset-4">
											<form:hidden path="id" />
											<div class="form-group">
												<div class="col-sm-8 col-sm-offset-2">
													<input type="submit" id="submitId" onsubmit="getExamList" value="Enter Student Marks"
														class="btn btn-success" tabindex="6" />
													<button type="button" class="btn btn-danger" id="cancel"
														tabindex="7">Reset</button>
												</div>
											</div>
										</div>
									</div>
									
								</form:form>
							</div>

						</div>
					</div>
				</div>
			</div>
			<!-- Row Ends -->

			<!-- Row Starts -->
			<div class="row gutter">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="blog">
						<div class="blog-header">
							<div class="col-md-12">
								<div class="col-md-4">
									<h4>Exam Marks</h4>
								</div>

								<div class="col-md-8" align="right">
									<span class="btn btn-primary btnsm"><span
										class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>
										Save </span> <span class="btn btn-warning btnsm"><span
										class="glyphicon glyphicon-remove" aria-hidden="true"></span>
										Cancel </span> <span class="btn btn-success btnsm"><span
										class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
										SMS Marks</span>
								</div>

							</div>
							<div class="blog-body">
								<div class="table-responsive">
									<div class="panel-body">
										<table
											class="table table-striped table-selectable table-bordered">
											<tbody>
												<tr>
													<th><input type="checkbox" name="filter-checkbox"
														value="" style="float: left"></th>
													<th><span class="cursor-pointer">Roll No</span></th>
													<th><span class="cursor-pointer">Full Name </span></th>
													<th>Contact Number</th>
													<th class="centered">Total (max0)</th>
													<th class="centered">GPA</th>
												</tr>
												<tr>
													<td><input type="checkbox"></td>
													<td></td>
													<td><a href="#398/1/">BALA HARSHA AVVARU</a></td>
													<td>9550860252</td>
													<td><div class="row">
															<span class="col-xs-6"><span>0</span> </span><span
																class="col-xs-6 right-align">
														</div></td>
													<td><div class="row">
															<label class="col-ms-6 control-label" title="GPA"></label>
														</div></td>
												</tr>
												<tr>
													<td><input type="checkbox"></td>
													<td></td>
													<td><a href="#">HARSHA DODDA</a></td>
													<td>9603858746</td>
													<td><div class="row">
															<span class="col-xs-6"><span>0</span> </span><span
																class="col-xs-6 right-align">
														</div></td>
													<td><div class="row">
															<label class="col-ms-6 control-label" title="GPA"></label>
														</div></td>
												</tr>
												<tr>
													<td><input type="checkbox"></td>
													<td></td>
													<td><a href="#">Jahnavi Punna</a></td>
													<td>7674851388</td>
													<td><div class="row">
															<span class="col-xs-6"><span>0</span> </span><span
																class="col-xs-6 right-align">
														</div></td>
													<td><div class="row">
															<label class="col-ms-6 control-label" title="GPA"></label>
														</div></td>
												</tr>
												<tr>
													<td><input type="checkbox"></td>
													<td></td>
													<td><a href="#">Nakshatra Kalakoti</a></td>
													<td>9703044060</td>
													<td><div class="row">
															<span class="col-xs-6"><span>0</span> </span><span
																class="col-xs-6 right-align">
														</div></td>
													<td><div class="row">
															<label class="col-ms-6 control-label" title="GPA"></label>
														</div></td>
												</tr>
												<tr>
													<td><input type="checkbox"></td>
													<td></td>
													<td><a href="#">Moksha Vaka</a></td>
													<td>9948381488</td>
													<td><div class="row">
															<span class="col-xs-6"><span>0</span> </span><span
																class="col-xs-6 right-align">
														</div></td>
													<td><div class="row">
															<label class="col-ms-6 control-label" title="GPA"></label>
														</div></td>
												</tr>
												<tr>
													<td><input type="checkbox"></td>
													<td></td>
													<td><a href="#">Chaitanya Naga Jyothi. Damarla</a></td>
													<td>7036430279</td>
													<td><div class="row">
															<span class="col-xs-6"><span>0</span> </span><span
																class="col-xs-6 right-align">

																<td></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
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

<!-- <script src="http://code.jquery.com/jquery-1.10.2.js"></script> -->
<!-- <script src="http://code.jquery.com/ui/1.11.2/jquery-ui.js"></script> -->
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
	var listOrders1 = $
	{
		allOrders1
	};
	if (listOrders1 != "") {
		displayTable(listOrders1);
	} else {
		$("#emptyMessageId").val("No Results Found");
	}
</script>
