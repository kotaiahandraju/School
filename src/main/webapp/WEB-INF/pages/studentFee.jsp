<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<style>
/* .ui-dialog .ui-widget .ui-widget-content .ui-corner-all .ui-front .ui-draggable .ui-resizable
#dial{
    position: relative;
    height: auto;
    width: 799px;
    top: -5em;
    display: block;
}*/
.mediu {
width:200px;
}
.form-control {
width:200px !important;
}
@media (min-width: 768px) {
.modal-dialog {
    width: 59%;
    margin: 30px auto;
}
}
</style>

<!-- 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js" type="text/javascript"></script> -->
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-2.1.3.min.js"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/jquery.validate.min.js"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/additional-methods.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.2/jspdf.min.js"></script>
<!-- <script src="js/dropdownsearch.js"></script>
	<link href="css/dropdownsearch.css"/> -->
<!-- 	  <script src="js/chosen.jquery.js"></script> -->

<!-- Dashboard Wrapper starts -->
<div class="dashboard-wrapper">

	<!-- Top Bar starts -->
	<div class="top-bar">
		<div class="page-title">Fee Payment</div>
	</div>
	<!-- Top Bar ends -->

	<!-- Main Container starts -->
	<div class="main-container">

		<!-- Container fluid Starts -->
		<div class="container-fluid">
			<ol class="breadcrumb">
    	<li><a href="dashBoard">Home</a></li>
    	<li><a href="#">Student Details</a></li>
		<li><a href="#">Fee Payment</a></li>
	    </ol>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>Filter Student</h4>
					</div>
					<div class="panel-body collapse in">
			<!-- Spacer starts -->
				<!-- Row Starts -->
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						
								<form:form id="fee-form" action="addStudentFee.htm"	commandName="packCmd" method="post" class="form-horizontal">
										<input type="hidden" id="forFormValidation" required="required" /> 
									<div class="row">
										<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12">
											<div class="form-group">
												<label for="inputEmail3" class="col-sm-4 control-label">Board Name<span style="color: red;">*</span></label>
												<div class="col-sm-8">
													<form:select path="boardName" tabindex="1"	onchange="classNameFilter()"	class="form-control" required="true" >

														<form:option value="">-- Choose Board --</form:option>
														<form:options items="${board}"></form:options>
													</form:select>
													<span class="boardName_error" id="boardName_error"></span>
												</div>
											</div>
										</div>
										<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12">
											<div class="form-group">
												<label for="inputEmail3" class="col-sm-4 control-label">Class<span style="color: red;">*</span></label>
												<div class="col-sm-8">
													<form:select path="className" tabindex="2"	onchange="sectionFilter()" class="form-control" required="true" >
													<form:option value="">-- Choose Class --</form:option>
<%-- 														<form:options items="${allClasses}"></form:options> --%>
													</form:select>
													<span class="className_error" id="className_error"></span>
												</div>
											</div>
										</div>
										<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12">
											<div class="form-group">
												<label for="inputEmail3" class="col-sm-4 control-label">Section<span style="color: red;">*</span></label>
												<div class="col-sm-8">
													<form:select path="section" tabindex="3"	onchange="mediumFilter()"	class="form-control" required="true" >

														<form:option value="">-- Choose Section --</form:option>
<%-- 														<form:options items="${allSection}"></form:options> --%>
													</form:select>
													<span class="section_error" id="section_error"></span>
												</div>
											</div>
										</div>
										<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12">
											<div class="form-group">
												<label for="inputEmail3" class="col-sm-4 control-label">Medium<span style="color: red;">*</span></label>
												<div class="col-sm-8">
													<form:select path="medium" tabindex="4"	onchange="studentFilterDropdown()"	class="form-control" required="true" >

														<form:option value="">-- Choose Medium --</form:option>
<%-- 														<form:options items="${mediam}"></form:options> --%>
													</form:select>
													<span class="medium_error" id="medium_error"></span>
												</div>
											</div>
										</div>
										<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12">
											<div class="form-group">
												<label for="inputEmail3" class="col-sm-4 control-label">Student<span style="color: red;">*</span></label>
												<div class="col-sm-8">
													<form:select path="studentId" tabindex="5"	class="form-control" required="true" onchange="getDueFee()">

														<form:option value="">-- Choose Student --</form:option>
<%-- 														<form:options items="${allStudents}"></form:options> --%>
													</form:select>
												</div>
											</div>
										</div>
										<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12" id="divAdmissionFee">
											<div class="form-group">
												<label for="inputEmail3" class="col-sm-4 control-label">Admission Fees</label>
												<span id="admissionNetFee"></span>
												<div class="col-sm-8">
													<form:input path="admissionFee" class="form-control numericOnly"	tabindex="6" placeholder="Admission Fee" required="true" />

												</div>
											</div>
										</div>
										<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12" id="divTutionFee">
											<div class="form-group">
												<label for="inputEmail3" class="col-sm-4 control-label">Tuition Fees</label>
												<span id="tutionNetFee"></span>
												<div class="col-sm-8">
													<form:input path="tutionFee" class="form-control numericOnly"	tabindex="6" placeholder="Tuition Fee"  required="true" />

												</div>
											</div>
										</div>
										<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12" id="divTransportationFee">
											<div class="form-group">
												<label for="inputEmail3" class="col-sm-4 control-label">Bus Fees</label>
												<span id="transportationNetFee"></span>
												<div class="col-sm-8">
													<form:input path="transportationFee" class="form-control numericOnly"	tabindex="6" placeholder="Transportation Fee" required="true" />

												</div>
											</div>
										</div>
										<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12" id="divHostelFee">
											<div class="form-group">
												<label for="inputEmail3" class="col-sm-4 control-label">Hostel Fees</label>
												<span id="hostelNetFee"></span>
												<div class="col-sm-8">
													<form:input path="hostelFee" class="form-control numericOnly"	tabindex="6" placeholder="Hostel Fee" required="true" />

												</div>
											</div>
										</div>
									<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12" id="divStationaryFee">
											<div class="form-group">
												<label for="inputEmail3" class="col-sm-4 control-label">Stationary Fees</label>
												<span id="stationaryNetFee"></span>
												<div class="col-sm-8">
													<form:input path="stationaryFee" class="form-control numericOnly"	tabindex="6" placeholder="Stationary Fee" required="true" />

												</div>
											</div>
										</div>
									
										<div class="col-sm-8 col-sm-offset-4">
											<div class="form-group">
												<div class="col-sm-8 col-sm-offset-2">
													<span id="displayId"></span>
												</div>
											</div>
											<form:hidden path="id" tabindex="1" />
											<div class="form-group">
												<div class="col-sm-8 col-sm-offset-2">
													<input type="submit" id="submitId" value="Submit" class="btn btn-success"	tabindex="8" />
													<button type="button" class="btn btn-danger" id="cancel"	tabindex="9">Reset</button>
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

				<!-- <a data-toggle="modal" data-target="#myModal" data-keyboard="false" data-backdrop="static">Open Modal</a> -->

				<div class="modal fade" id="myModal" role="dialog">
					<div class="modal-dialog" style="">

						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" style="color:#fff;">&times;</button>
								<h4 class="modal-title">Fee Receipt</h4>
							</div>
							<div class="modal-body" id='printTab'></div>
							<!-- <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div> -->
						</div>

					</div>
				</div>

				<!-- Row Starts -->
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="blog">
							<div id="editor"></div>
							<!-- 									<button class="btn btn-primary" id="pdfDownload">Download Students Fee List</button> -->
							<div id="tableId">
								<table id="itemContainer1"
									class="table table-bordered table-condensed no-margin">
									<!-- <thead>
									          <tr><th>Student Name</th></tr>
						                      <tr><th>Father Name</th></tr>
						                      <tr><th>Mobile</th></tr>
						                      <tr><th>Class</th></tr>
						                      <tr><th>Board</th></tr>
						                      <tr><th>Medium</th></tr>
						                      <tr><th>Section</th></tr>
						                      <tr><th>Fee Type</th></tr>
						                      <tr><th>Paid Fee</th></tr>
						                      <tr><th>Due Fee</th></tr>
						                      <tr><th>Fee Date</th></tr>
									        </thead> -->
									<tbody></tbody>
								</table>
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
								<h4>List of Fee Payments</h4>
							</div>
							<div class="blog-body">
								<div class="table-responsive">
									<div id="basicExample_wrapper"
										class="dataTables_wrapper form-inline dt-bootstrap">
										<div class="row">
											<div class="col-sm-12">
												<div id="basicTable">
													<table id="basicExample"	class="table table-striped table-condensed table-bordered no-margin dataTable"	role="grid" aria-describedby="basicExample_info">
														<thead>
															<tr role="row">
																<th class="sorting_asc" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Name: activate to sort column descending">Student</th>
																<th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending">Father Name</th>
																<th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending">Mobile Number</th>
																<!-- <th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending">Board</th>
																<th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending">Medium</th> -->
																<th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Office: activate to sort column ascending">Class</th>
															<!--<th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Age: activate to sort column ascending">Section</th> -->
																<th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Age: activate to sort column ascending">Admission Fees</th>
																<th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Age: activate to sort column ascending">Tuition Fees</th>
																<th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Age: activate to sort column ascending">Bus Fees</th>
																<th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Age: activate to sort column ascending">Hostel Fees</th>
																<th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Age: activate to sort column ascending">Stationary Fees</th>
																<th class="sorting" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Start date: activate to sort column ascending">Paid Fee</th>
																<th class="sorting" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Start date: activate to sort column ascending">Due Fee</th>
																<th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Start date: activate to sort column ascending">Fee Date</th>
																<th class="sorting" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Salary: activate to sort column ascending">Action</th>
															</tr>
														</thead>
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

			<!-- Spacer ends -->

		</div>
		<!-- Container fluid ends -->

	</div>
	<!-- Main Container ends -->

	<div id="dial"
		style="width: auto; min-height: 130px; max-height: none; height: auto; background-color: #c3dac3; display: none;"></div>
</div>
<!-- Dashboard Wrapper ends -->

<!-- <script src="http://code.jquery.com/jquery-1.10.2.js"></script> -->
<!-- <script src="http://code.jquery.com/ui/1.11.2/jquery-ui.js"></script> -->
<!-- <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script> -->
<script type="text/javascript">
	$(document).ready(function() {
		$("#fee").val("");
		$('.has-sub').trigger("click");

		
		var getTabName = window.location.pathname.split('/')[2];
		$("#stu_li").addClass('active');
		$("#stu_li ul").css('display','block');
		$("#stu_li ul li a[href='"+ getTabName +"']").addClass('subactive');
		
	});
	 $("#admissionFee").val("");
	   $("#tutionFee").val("");
	   $("#transportationFee").val("");
	   $("#hostelFee").val("");
	   $("#stationaryFee").val("");
	jQuery.validator.addMethod('lettersonly', function(value, element) {
		return this.optional(element)
				|| /^[a-z. áãâäàéêëèíîïìóõôöòúûüùçñ]+$/i.test(value);
	}, "Please Enter Valid Name");

	jQuery.validator.addMethod("mobileNO", function(phone_number, element) {
		phone_number = phone_number.replace(/\s+/g, "");
		return this.optional(element) || phone_number.length > 9
				&& phone_number.match(/^[7-9]\d+$/);
	}, "Invalid Mobile Number");

	$("#fee-form").validate({
		errorElement : 'span',
		errorClass : 'has-error',
		rules : {
			 boardName:{required:true},
			className:{required:true},  
			section:{required:true},
			medium:{required:true}, 
			studentId : {
				required : true
			},
			fee : {
				required : true,
				number : true
			},
			feeType : {
				required : true
			},
			 admissionFee: {required: true, number: true},
			    tutionFee: {required: true, number: true},
			    transportationFee: {required: true, number: true},
			    hostelFee: {required: true, number: true},
			    stationaryFee: {required: true, number: true},
			    forFormValidation:{required:true}
		},
		messages : {
		    boardName:{required:'Please Choose Board'},
			className:{required:'Please Choose Class'},
			section:{required:'Please Choose Section'},
			medium:{required:'Please Choose Medium'},
			studentId : {required : 'Choose Student Name'},
			fee : {required : 'Fee Amount',number : 'Fee Amount'},
			feeType : {required : 'Fee Type'},
		    admissionFee: {required: 'Admission Fee', number: 'Numeric Characters'},
			tutionFee: {required: 'Tuition Fee', number: 'Numeric Characters'},
			transportationFee: {required: 'Bus Fee', number: 'Numeric Characters'},
			hostelFee: {required: 'Hostel Fee', number: 'Numeric Characters'},
			stationaryFee: {required: 'Stationary Fee', number: 'Numeric Characters'},
			forFormValidation:{required:''}
		},
	});
	 $('#submitId').click(function(){
	    	var admissionFee = $('#admissionFee').val();
	    	var tutionFee   = $('#tutionFee').val();
	    	var transportationFee = $('#transportationFee').val();
	    	var hostelFee = $('#hostelFee').val();
	    	var stationaryFee = $('#stationaryFee').val();
	    	var stationaryFee = $('#stationaryFee').val();
	    	$('#forFormValidation').val('');
	    	
	    	if( dueFee12 != 0){
	    	if(  admissionFee == "0"  && tutionFee == "0" && transportationFee == "0"  && hostelFee == "0" &&  stationaryFee == "0"   ){
	    		$('#forFormValidation').val();
	    		alert("insufficient fund");
	    		return false;
	    	}
	    	/* if(admissionFee =='' && tutionFee=='' && transportationFee == '' &&  hostelFee == '' && stationaryFee == '' ){
	    		//return false;
	    		$('#forFormValidation').val();
	    	} */
	    	else{
	    	if(admissionFee1 < admissionFee){
	    		alert("Maximum AdmissionFee is : "+admissionFee1);
	    		//$('#erroradmissionFee').text("Max Aoount is:"+admissionFee1);
		     return false;
		     }else if(tutionFee1 < tutionFee){
		    	 alert("Maximum TutionFee is : "+tutionFee1);
			     return false;
		     }else if(transportationFee1 < transportationFee){
		    	 alert("Maximum TransportationFee is : "+transportationFee1);
			     return false;
		     }else if(hostelFee1 < hostelFee){
		    	 alert("Maximum HostelFee is : "+hostelFee1);
			     return false;
		     }else if(stationaryFee1 < stationaryFee){
		    	 alert("Maximum StationaryFee is : "+stationaryFee1);
			     return false;
		     }else {
		    	 $('#forFormValidation').val(1);
		    	 return true;
		     }
	    	}
	    	
	    	}else{
	    		
	    		$('#forFormValidation').val();
	    		alert("There is no Due Amount");
	    		return false;
	    	}
	    	
	    });
	   
	$('#cancel').click(function() {
		$("#fee-form").validate().resetForm();
		$("#fee-form").removeClass("has-error");
		$("#boardName").val('');
		$("#className").val('');
		$("#section").val('');
		$("#medium").val('');
		$("#studentId").val('');
		$("#fee").val('');
		$("#admissionFee").val('');
		$("#admissionNetFee").text('');
		$("#tutionFee").val('');
		$("#tutionNetFee").text('');
		$("#transportationFee").val('');
		$("#transportationNetFee").text('');
		$("#hostelFee").val('');
		$("#hostelNetFee").text('');
		$("#stationaryFee").val('');
		$("#stationaryNetFee").text('');
		$("#displayId").val('');
		$("#fee-form").addClass('form-horizontal');
		$("#submitId").val("Submit");
		$("#displayId").text('');
		$('#erroradmissionFee').text("");
  		$('#errortutionFee').text("");
  		$('#errortransportationFee').text("");
  		$('#errorhostelFee').text("");
  		$('#errorstationaryFee').text("");
	});
	var listOrders1 = ${allOrders1};
	if (listOrders1 != "") {
		displayTable(listOrders1);
	}

	function displayTable(listOrders) {
		if (listOrders != null) {
			$('#basicTable').html('');
			serviceUnitArray = {};
			var tableHead = '<table id="basicExample" class="table table-striped table-condensed table-bordered no-margin dataTable" role="grid" aria-describedby="basicExample_info">'
					+ '<thead>'
					+ '<tr role="row">'
					+ '<th class="sorting_asc" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Name: activate to sort column descending">Student</th>'
					+ '<th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending">Father Name</th>'
					+ '<th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending">Mobile Number</th>'
// 					+ '<th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending">Board</th>'
// 					+ '<th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending">Medium</th>'
					+ '<th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Office: activate to sort column ascending">Class</th>'
// 					+ '<th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Age: activate to sort column ascending">Section</th>'
					+ '<th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Age: activate to sort column ascending">Admission Fees</th>'
					+ '<th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Age: activate to sort column ascending">Tuition Fees</th>'
					+ '<th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Age: activate to sort column ascending">Bus Fees</th>'
					+ '<th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Age: activate to sort column ascending">Hostel Fees</th>'
					+ '<th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Age: activate to sort column ascending">Stationary Fees</th>'
					+ '<th class="sorting" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Start date: activate to sort column ascending">Paid Fee</th>'
					+ '<th class="sorting" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Start date: activate to sort column ascending">Due Fee</th>'
					+ '<th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Start date: activate to sort column ascending">Fee Date</th>'
					+ '<th class="sorting" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Salary: activate to sort column ascending">Action</th>'
					+ '</tr>' + '</thead>' + '<tbody></tbody></table>';
			$('#basicTable').html(tableHead);
			$.each(listOrders,
							 function(i, orderObj) {
				/*	if (orderObj.dueFee == 0.00) {
									orderObj.dueFee = orderObj.netFee;
								}
 */
								// 	contactNumber":"wertewrt","mediumId":"16","subjectId":"","name":"0","boardid":"1","gender":null,"className":"","qualifaction":"ewrt","section":""
								serviceUnitArray[orderObj.id] = orderObj;
								var id = '"' + orderObj.id + '"';
								var tblRow = "<tr align='center' role='row' class='odd'>"
										+ "<td>"
										+ '<a style="cursor: pointer;" title="View Fee Receipt" data-toggle="modal" data-target="#myModal" data-keyboard="false" data-backdrop="static" onclick=popupOpen('
										+ orderObj.id
										+ ')>'
										+ '<b>'
										+ orderObj.studentName
										+ '</b></a>'
										+ '</td>'
										+ "<td class='hidden-sm hidden-xs' title='"+orderObj.fatherName+"'>"
										+ orderObj.fatherName
										+ "</td>"
										+ "<td class='hidden-sm hidden-xs' title='"+orderObj.mobile+"'>"
										+ orderObj.mobile
										+ "</td>"
										/* + "<td class='hidden-sm hidden-xs' title='"+orderObj.boardName+"'>"
										+ orderObj.boardName
										+ "</td>" */
										/* + "<td class='hidden-sm hidden-xs' title='"+orderObj.mediumName+"' >"
										+ orderObj.mediumName
										+ "</td>" */
										+ "<td class='hidden-sm hidden-xs' title='"+orderObj.className+"'>"
										+ orderObj.className
										+ "</td>"
										/* + "<td class='hidden-sm hidden-xs' title='"+orderObj.sectionName+"' >"
										+ orderObj.sectionName
										+ "</td>" */
										+ "<td class='hidden-sm hidden-xs'>"
										+ orderObj.admissionFee							
										+ "</td>"																		    
										+ "<td class='hidden-sm hidden-xs'>"
										+ orderObj.tutionFee		
										+ '</td>'
										+ "<td class='hidden-sm hidden-xs'>"
										+ orderObj.transportationFee		
										+ '</td>'
										+ "<td class='hidden-sm hidden-xs'>"
										+ orderObj.hostelFee		
										+ '</td>'
										+ "<td class='hidden-sm hidden-xs'>"
										+ orderObj.stationaryFee		
										+ '</td>'
										+ "<td title='"+orderObj.fee+"' >"
										+ orderObj.fee
										+ "</td>"
										+ "<td title='"+orderObj.dueFee+"' >"
										+ orderObj.dueFee
										+ "</td>"
										+ "<td class='hidden-sm hidden-xs' title='"+orderObj.feeDate+"' >"
										+ orderObj.feeDate
										+ "</td>"
										+ "<td align='center'>"
										+ '<a href="javascript:void(0)" onclick=editPack('
										+ orderObj.id
										+ ')'
										+ '  ><i style="color: green;" class="fa fa-edit"></i></a>'
										+ '</td>' + '</tr>';
								$(tblRow).appendTo("#basicExample tbody");

								//* for download PDF option
							/* 	var tblRow1 = "<tr role='row' class='odd'><th>Student Name</th><td>"
										+ orderObj.studentName
										+ "</td></tr>"
										+ "<tr><th>Father Name</th><td>"
										+ orderObj.fatherName
										+ "</td></tr>"
										+ "<tr><th>Mobile</th><td>"
										+ orderObj.mobile
										+ "</td></tr>"
										+ "<tr><th>Board</th><td title='"+orderObj.boardName+"'>"
										+ orderObj.boardName
										+ "</td></tr>"
										+ "<tr><th>Medium</th><td title='"+orderObj.mediumName+"'>"
										+ orderObj.mediumName
										+ "</td></tr>"
										+ "<tr><th>Class</th><td title='"+orderObj.className+"'>"
										+ orderObj.className
										+ "</td></tr>"
										+ "<tr><th>Section</th><td title='"+orderObj.sectionName+"'>"
										+ orderObj.sectionName
										+ "</td></tr>"
										+ "<tr><th>Fee Type</th><td title='"+orderObj.feeType+"'>"
										+ orderObj.feeType
										+ "</td></tr>"
										+ "<tr><th>Paid Fee</th><td title='"+orderObj.fee+"'>"
										+ orderObj.fee
										+ "</td></tr>"
										+ "<tr><th>Due Fee</th><td title='"+orderObj.dueFee+"'>"
										+ orderObj.dueFee
										+ "</td></tr>"
										+ "<tr><th>Fee Date</th><td title='"+orderObj.feeDate+"'>"
										+ orderObj.feeDate + "</td></tr>";
								$(tblRow1).appendTo("#itemContainer1"); */
								// for download PDF option */

							});
			$('#basicExample').dataTable();
		} else {
			//alert('no data to display..');
		}
	}
	
	function editPack(id) {
		
		$("#admissionFee").prop("disabled", false);
		$("#tutionFee").prop("disabled", false);
		$("#transportationFee").prop("disabled", false);
		$("#hostelFee").prop("disabled", false);
		$("#stationaryFee").prop("disabled", false);
		
		$("#divAdmissionFee").show();  
		$("#divTutionFee").show();
		$("#divTransportationFee").show();
		$("#divHostelFee").show();
		$("#divStationaryFee").show();
		var transactionId = serviceUnitArray[id].id;
		$("#fee-form").validate().resetForm();
		$("#id").val(serviceUnitArray[id].id);
		$('#boardName').val(serviceUnitArray[id].boardId);
		classNameFilter();
		$('#className').val(serviceUnitArray[id].classId);
 		sectionFilter();
		
		$('#section').val(serviceUnitArray[id].sectionId);
 		mediumFilter();
		$('#medium').val(serviceUnitArray[id].mediumId);
		studentFilterDropdown();
		$('#studentId').val(serviceUnitArray[id].studentId);
		$('#studentId').trigger("chosen:updated");
		$('#fee').val(serviceUnitArray[id].fee);
		$('#admissionFee').val(serviceUnitArray[id].admissionFee);
		$('#tutionFee').val(serviceUnitArray[id].tutionFee);
		$('#transportationFee').val(serviceUnitArray[id].transportationFee);
		$('#hostelFee').val(serviceUnitArray[id].hostelFee);
		$('#stationaryFee').val(serviceUnitArray[id].stationaryFee);
		$("#displayId").text("Due Fee: " + serviceUnitArray[id].dueFee);
		$("#submitId").val("Update");
		
		$(window).scrollTop($('#boardName').offset().top);
	}

	function serviceFilter(id) {
		var borderId = $("#boardName").val();
		$.ajax({
			type : "POST",
			url : "getBordName.json",
			data : "borderId=" + borderId,
			dataType : "json",
			success : function(response) {
				var optionsForClass = "";
				optionsForClass = $("#medium").empty();
				optionsForClass.append(new Option("--Select--", ""));
				$.each(response, function(i, tests) {
					var id = tests.id;
					var name = tests.name;
					optionsForClass.append(new Option(name, id));
				});
				$('#medium').trigger("chosen:updated");
			}
		});
	}

	function classNameFilter() {
		var boardId = $("#boardName").val();
			$.ajax({
				type : "POST",
				url : "getClassNameFilter.json",
				data : "boardId=" + boardId,
				async:false,
				success : function(response) {
					
					var optionsForClass = "";
					optionsForClass = $("#className").empty();
					optionsForClass.append(new Option("-- Choose Class --", ""));
					$.each(response, function(i, tests) {
						var id = tests.id;
						var className = tests.className;
						optionsForClass.append(new Option(className, id));
					});
					
				}
			});
	}
	function sectionFilter() {
		var boardId = $("#boardName").val();
		var classId = $("#className").val();
		if (boardId.length != 0 ) {
			$.ajax({
				type : "POST",
				url : "getSectionFilter.json",
				data : "boardId=" + boardId + "&classId=" + classId,
				dataType : "json",
				async:false,
				success : function(response) {
					/* alert(response); */
					var optionsForClass = "";
					optionsForClass = $("#section").empty();
					optionsForClass.append(new Option("-- Choose Section --",""));
					$.each(response, function(i, tests) {
						var id = tests.id;
						var sectionName = tests.sectionName;
						optionsForClass.append(new Option(sectionName, id));
					});
				}
			});
		}
	}
	function mediumFilter() {
		var boardId = $("#boardName").val();
		var classId = $("#className").val();
		var sectionId = $("#section").val();
		if (boardId.length != 0) {
			$.ajax({
				type : "POST",
				url : "getMediumFilter.json",
				data : "boardId=" + boardId + "&classId=" + classId
						+ "&sectionId=" + sectionId,
				dataType : "json",
				async:false,
				success : function(response) {
					/* alert(response); */
					var optionsForClass = "";
					optionsForClass = $("#medium").empty();
					optionsForClass
							.append(new Option("-- Choose Medium --", ""));
					$.each(response, function(i, tests) {
						var id = tests.id;
						var mediumName = tests.mediumName;
						optionsForClass.append(new Option(mediumName, id));
					});
				}
			});
		}
	}
	function studentFilterDropdown() {
		var boardId = $("#boardName").val();
		var classId = $("#className").val();
		var sectionId = $("#section").val();
		var mediumId = $("#medium").val();
		
			$.ajax({
				type : "POST",
				url : "studentFilterDropdown.json",
				data : "boardId=" + boardId + "&classId=" + classId
						+ "&sectionId=" + sectionId + "&mediumId=" + mediumId,
				dataType : "json",
				async:false,
				success : function(response) {
					// 				 alert(response);  
					var optionsForClass = "";
					optionsForClass = $("#studentId").empty();
					optionsForClass.append(new Option("-- Choose Student --",
							""));
					$.each(response, function(i, tests) {
						var studentId = tests.studentId;
						var studentName = tests.studentName;
						optionsForClass.append(new Option(studentName,
								studentId));
					});
					
				}
			});
		
	}

	/* $("#search").on("keyup", function() {
	    var value = $(this).val();

	    $("table tr").each(function(index) {
	        if (index !== 0) {

	            $row = $(this);

	            var id = $row.find("td:first").text();

	            if (id.indexOf(value) !== 0) {
	                $row.hide();
	            }
	            else {
	                $row.show();
	            }
	        }
	    });
	}); */
	var admissionFee1 =0.00;
	var tutionFee1 = 0.00;
	var transportationFee1 = 0.00;
	var hostelFee1 = 0.00;
	var stationaryFee1 = 0.00;
	
	var dueFee12 ;
	function getDueFee() {
		
		
		var studentId = $("#studentId").val();
		$.ajax({
			type : "POST",
			url : "getDueFee.json",
			data : "studentId=" + studentId,
			dataType : "json",
			success : function(response) {
				// 				 alert(response.totalFee); 
				
				if (response.dueFee == null || response.dueFee == 0) {
					
					dueFee12=  response.netFee;
					$("#displayId").text("Due Fee: " + response.netFee);
															
					$("#admissionFee").prop("disabled", true);
					$("#tutionFee").prop("disabled", true);
					$("#transportationFee").prop("disabled", true);
					$("#hostelFee").prop("disabled", true);
					$("#stationaryFee").prop("disabled", true);
					
					$("#divAdmissionFee").hide();  
					$("#divTutionFee").hide();
					$("#divTransportationFee").hide();
					$("#divHostelFee").hide();
					$("#divStationaryFee").hide();
					
					 $('#forFormValidation').val('');
					
					
					
				} else {
					$("#displayId").text("Due Fee: " + response.dueFee);
					
					if(response.admissionFee == 0){
						$("#admissionFee").prop("disabled", true);
						$("#divAdmissionFee").hide();  
					}
					
					$("#admissionNetFee").text("Net Fee: " +response.admissionFee);
					
					if(response.tutionFee == 0){
						
						$("#tutionFee").prop("disabled", true);
						$("#divTutionFee").hide();
					}
					
					$("#tutionNetFee").text("Net Fee: " +response.tutionFee);
					
					if(response.transportationFee == 0){
						
						$("#transportationFee").prop("disabled", true);
						$("#divTransportationFee").hide();
						
					}
					$("#transportationNetFee").text("Net Fee: " +response.transportationFee);
					
					if(response.hostelFee == 0){
						
						$("#hostelFee").prop("disabled", true);
						$("#divHostelFee").hide();
					}
					
					$("#hostelNetFee").text("Net Fee: " +response.hostelFee);
					
					if(response.stationaryFee == 0){
						
						$("#stationaryFee").prop("disabled", true);
						$("#divStationaryFee").hide();
					}
					
					$("#stationaryNetFee").text("Net Fee: " +response.stationaryFee);
					
					
					admissionFee1 = response.admissionFee;
					tutionFee1 = response.tutionFee;
					transportationFee1 = response.transportationFee;
					hostelFee1 = response.hostelFee;
					stationaryFee1 = response.stationaryFee;
				}
			},
			error : function(e) {
			},
			statusCode : {
				406 : function() {

				}
			}
		});
	}

	
	 
	/* $(document).ready(function(){
	$("select").chosen({allow_single_deselect:true});
	}); */
	function popupOpen(id) {
		$('#printTab').text("");
		var studentFeeId = id;

		$
				.ajax({
					type : "POST",
					url : "getPrintFee.json",
					data : "studentFeeId=" + studentFeeId,
					dataType : "json",
					success : function(response) {
						// 						alert(response);
						var popuptitle = null;
						$
								.each(
										response,
										function(i, tests) {

var stockInformation1 = "<table align='center' class='table table-stripped table-bordered table-condensed' id='stockInformationTable' style='font-family: 'Open Sans', arial, sans-serif;font-size: 16px;'>"

/* + "<tr><td colspan='2'><img src='img/ABV-header.png' style='width: 100%;height: 70px;'></td></tr>" */
+ "<tr><td colspan='2' align='center'><img src='img/logoprint.png' style='width:300px;'></td></tr>"
+ "<tr style='height: 35px;'><td colspan='2'><span style='float: right;font-size: normal;color: blue;'>Date: "+ tests.created_time+ "</span></td></tr>"

+ "<tr style='height: 35px;'><td colspan='2'><b>Student Name: </b>&nbsp;&nbsp;"+ tests.studentName+ "</td></tr>"
+ "<tr style='height: 35px;'><td colspan='2'><b>Father Name: </b>&nbsp;&nbsp;"+ tests.fatherName+ "</td></tr>"
+ "<tr style='height: 35px;'><td colspan='2'><b>Mobile: </b>&nbsp;&nbsp;"+ tests.mobile+ "</td></tr>"
+ "<tr style='height: 35px;'><td colspan='2'><span class='mediu'><b>Board: </b>&nbsp;&nbsp;"+ tests.boardName+ "</span>&nbsp;&nbsp;"
+ 	"<span><b>Medium: </b>&nbsp;&nbsp;"+ tests.medium+ "</span>&nbsp;&nbsp;"+ "<span><b>Class: </b>&nbsp;&nbsp;"+ tests.className
+ 	"</span>&nbsp;&nbsp;"+ "<span><b>Section: </b>&nbsp;&nbsp;"+ tests.sectionName
+ "</span></td></tr>"

+ "<tr style='height: 35px;'><th>Particulars</th><th>Amount</th></tr>"

+ "<tr style='height: 35px;'><td align='center'>Admission Fee</td><td align='center'>"+ tests.admissionFee+ "</td></tr>"
+ "<tr style='height: 35px;'><td align='center'>Tuition Fee</td><td align='center'>"+ tests.tutionFee+ "</td></tr>"
+ "<tr style='height: 35px;'><td align='center'>Bus Fee</td><td align='center'>"+ tests.transportationFee+ "</td></tr>"
+ "<tr style='height: 35px;'><td align='center'>Hostel Fee</td><td align='center'>"+ tests.hostelFee+ "</td></tr>"
+ "<tr style='height: 35px;'><td align='center'>Stationary Fee</td><td align='center'>"+ tests.stationaryFee+ "</td></tr>"
+ "<tr style='height: 35px;'><td align='center'>Amount Paid</td><td align='center'>"+ tests.fee+ "/-</td></tr>"
+ "<tr style='height: 35px;'><td align='right'><b>Total Amount:</b></td><td align=''>"+ tests.fee+ "/-</td></tr>"
// + "<tr style='height: 35px;'><td colspan='2' id='totalId'><b>(Amount) in words: </b>"+ toWords(Math.round(tests.fee))+ "</td></tr>"
+ "</table>"
+ "<span><p><b>Due Fee : "+ tests.dueFee+ "</b></p></span>"
+ "<br>"
+ "<input id='printbtn' style='' class='btn btn-default' type='button' value='Print' onclick=PrintElem('#printTab') />"

$(stockInformation1).appendTo("#printTab");
// toWords(tests.fee);

										});
						// 							 $(stockInformation2).appendTo("#stockInformationTable"); 
						// 							 $('#dial').dialog({width:799,title:popuptitle,modal: true}).dialog('open');

					},
					error : function(e) {
						// 					alert('Error: ' + e);
					}
				});
	}
	function PrintElem(elem) {
		$("#printbtn").hide();
		Popup($(elem).html());
	}

	function Popup(data) {
		var mywindow = window.open('','new div');

	    var is_chrome = Boolean(mywindow.chrome);
	    var isPrinting = false;
	    mywindow.document.write('<html><head><title>VVN School</title> <link rel="stylesheet" type="text/css" href="../assets/css/img.css"><link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"></head><body>');
	    mywindow.document.write(data);
	   
	    mywindow.document.write('</body></html>');
	    mywindow.document.close(); // necessary for IE >= 10 and necessary before onload for chrome
		
	    if (is_chrome) {
	        mywindow.onload = function() { // wait until all resources loaded 
	            mywindow.focus(); // necessary for IE >= 10
	            mywindow.print();  // change window to mywindow
	            mywindow.close();// change window to mywindow
	        };
	    
	    
	   } else {
	        mywindow.document.close(); // necessary for IE >= 10
	        mywindow.focus(); // necessary for IE >= 10

	        mywindow.print();
	        mywindow.close();
	   }
		
		$("#printbtn").show();
		return true;
	}
	/*  var doc = new jsPDF();
	 var specialElementHandlers = {
	     '#editor': function (element, renderer) {
	         return true;
	     }
	 };

	 $('#pdfDownload').click(function () {
	     doc.fromHTML($('#itemContainer').html(), 15, 15, {
	         'width': 170,
	             'elementHandlers': specialElementHandlers
	     });
	     doc.save('sample-file.pdf');
	 }); */
	$("#itemContainer1").hide();
	$(function() {
		var doc = new jsPDF();
		var specialElementHandlers = {
			'#editor' : function(element, renderer) {
				return true;
			}
		};
	});
	$('#pdfDownload').click(function() {

		var table = tableToJson($('#itemContainer1').get(0))
		var doc = new jsPDF('p', 'pt', 'a4', true);
		doc.cellInitialize();
		$.each(table, function(i, row) {
			console.debug(row);
			$.each(row, function(j, cell) {
				doc.cell(1, 1, 120, 50, cell, i); // 2nd parameter=top margin,1st=left margin 3rd=row cell width 4th=Row height
			})
		})

		doc.save('StudentFees-List.pdf');
	});
	function tableToJson(table) {
		var data = [];

		// first row needs to be headers
		var headers = [];
		for (var i = 0; i < table.rows[0].cells.length; i++) {
			headers[i] = table.rows[0].cells[i].innerHTML.toLowerCase()
					.replace(/ /gi, '');
		}

		// go through cells
		for (var i = 0; i < table.rows.length; i++) {

			var tableRow = table.rows[i];
			var rowData = {};

			for (var j = 0; j < tableRow.cells.length; j++) {

				rowData[headers[j]] = tableRow.cells[j].innerHTML;

			}

			data.push(rowData);
		}

		return data;
	}

	/* function searchStudetnFee() {
		var studentId = $('#studentId').val();
		var classId = $('#className').val();
		var boardId = $('#boardName').val();
		var sectionId = $('#section').val();
		var mediumId = $('#medium').val();
		$.ajax({
			type : "POST",
			url : "searchStudetnFee.json",
			data : "studentId=" + studentId + "&classId=" + classId
					+ "&sectionId=" + sectionId + "&mediumId=" + mediumId
					+ "&boardId=" + boardId,
			dataType : "json",
			success : function(response) {
				// 				 alert(response);  
				displayTable(response)

			},
			error : function(e) {
			},
			statusCode : {
				406 : function() {

				}
			}
		});

	} */
	var th = ['','Thousand','million', 'billion','trillion'];
	 //uncomment this line for English Number System
	 //var th = ['','thousand','million', 'milliard','billion'];

	 var dg = ['Zero','One','Two','Three','Four', 'Five','Six','Seven','Eight','Nine']; 
	 var tn = ['Ten','Eleven','Twelve','Thirteen', 'Fourteen','Fifteen','Sixteen', 'Seventeen','Eighteen','Nineteen']; var tw = ['Twenty','Thirty','Forty','Fifty', 'Sixty','Seventy','Eighty','Ninety']; 
	 function toWords(s){s = s.toString(); s = s.replace(/[\, ]/g,''); if (s != parseFloat(s)) return 'not a number'; var x = s.indexOf('.'); if (x == -1) x = s.length; if (x > 15) return 'too big'; var n = s.split(''); var str = ''; var sk = 0; for (var i=0; i < x; i++) {if ((x-i)%3==2) {if (n[i] == '1') {str += tn[Number(n[i+1])] + ' '; i++; sk=1;} else if (n[i]!=0) {str += tw[n[i]-2] + ' ';sk=1;}} else if (n[i]!=0) {str += dg[n[i]] +' '; if ((x-i)%3==0) str += 'Hundred ';sk=1;} if ((x-i)%3==1) {if (sk) str += th[(x-i-1)/3] + ' ';sk=0;}} 
	 if (x != s.length)
	 {
	 	var y = s.length;
	 	str += 'point ';
	 	for (var i=x+1; i<y; i++) 
	 		str += dg[n[i]] +' ';
	 	} return str.replace(/\s+/g,' ');
	 	}
</script>
