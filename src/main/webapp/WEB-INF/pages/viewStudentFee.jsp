<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-2.1.3.min.js"></script>
	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/jquery.validate.min.js"></script>
	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/additional-methods.min.js"></script>
	<script src="js/dropdownsearch.js"></script>
	<link href="css/dropdownsearch.css"/>
	<style>
	@media only screen and (min-width: 768px) {
.modal-dialog {
    width: 850px;
    margin: 30px auto;
}
}
	
	</style>
		<!-- Dashboard Wrapper starts -->
		<div class="dashboard-wrapper">

			<!-- Top Bar starts -->
			<div class="top-bar">
				<div class="page-title">View Student Fee</div>
			</div>
			<!-- Top Bar ends -->

			<!-- Main Container starts -->
			<div class="main-container">

				<!-- Container fluid Starts -->
				<div class="container-fluid">

					<!-- Spacer starts -->
					<div class="spacer">
						
						
						<!-- Row Starts -->
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="blog" style="border: 1px solid;">
									<!-- <div class="blog-header">
										<h5 class="blog-title">Student Fee</h5>
									</div> -->
									<div class="blog-body">
										<form:form id="fee-form" commandName="packCmd" method="post" class="form-horizontal">
										
										<div class="row">
											<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
												<div class="form-group">
												    <label for="inputEmail3" class="col-sm-4 control-label">Board Name</label>
												    <div class="col-sm-8">
														<form:select path="boardName" tabindex="1" onchange="classNameFilter(),searchStudetnFee()" class="form-control" >
															<form:option value=""  >-- Choose Board --</form:option>
															<form:options items="${board}"></form:options>
														</form:select>
														<span class="boardName_error" id="boardName_error"></span>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
												<div class="form-group">
												    <label for="inputEmail3" class="col-sm-4 control-label">Class</label>
												    <div class="col-sm-8">
														<form:select path="className" tabindex="2" onchange="sectionFilter(),searchStudetnFee()" class="form-control" >
															<form:option value=""  >-- Choose Class --</form:option>
															<form:options items="${allClasses}"></form:options>
														</form:select>
														<span class="className_error" id="className_error"></span>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
												<div class="form-group">
												    <label for="inputEmail3" class="col-sm-4 control-label">Section</label>
												    <div class="col-sm-8"> 
														<form:select path="section" tabindex="3" onchange="mediumFilter(),searchStudetnFee()" class="form-control" >
															<form:option value=""  >-- Choose Section --</form:option>
															<form:options items="${allSection}"></form:options>
														</form:select>
														<span class="section_error" id="section_error"></span>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
												<div class="form-group">
												    <label for="inputEmail3" class="col-sm-4 control-label">Medium</label>
												    <div class="col-sm-8">	
														<form:select path="medium" tabindex="4" onchange="studentFilterDropdown(),searchStudetnFee()" class="form-control" >
															<form:option value=""  >-- Choose Medium --</form:option>
															<form:options items="${mediam}"></form:options>
														</form:select>
														<span class="medium_error" id="medium_error"></span>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
												<div class="form-group">
												    <label for="inputEmail3" class="col-sm-4 control-label">Student</label>
												    <div class="col-sm-8">	
														<form:select path="studentId" tabindex="5" class="form-control" onchange="searchStudetnFee()" >
															<form:option value=""  >-- Choose Student --</form:option>
															<form:options items="${allStudents}"></form:options>
														</form:select>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-8 col-sm-offset-4">
												<div class="form-group">
												    <div class="col-sm-8 col-sm-offset-2">
														<span id="displayId"></span>
													</div>
												</div>
												<form:hidden path="id" tabindex="1" />
												<div class="form-group">
												  	<div class="col-sm-8 col-sm-offset-2">
													<input type="submit" value="Search" class="btn btn-success" tabindex="6"/>
<!-- 													<button type="button" class="btn btn-danger" id="cancel" tabindex="7">Reset</button> -->
													<a href="viewStudentFee" class="btn btn-danger" tabindex="7">Reset</a>
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
						<div class="modal fade" id="myModal" role="dialog">
					<div class="modal-dialog" style="">

						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
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
						<div class="row gutter">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="blog">
									<div class="blog-header">
										<h4>List of Student Fees</h4>
									</div>
									<div class="blog-body">
										<div class="table-responsive">
											<div id="basicExample_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">
												<div class="row">
													<div class="col-sm-12">
													<div id="basicTable">
 														<table id="basicExample" class="table table-striped table-condensed table-bordered no-margin dataTable" role="grid" aria-describedby="basicExample_info">
															<thead>
																<tr role="row">
																	<th class="sorting_asc" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Name: activate to sort column descending">Student</th>
																	<th class="sorting" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending">Father Name</th>
																	<th class="sorting" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending">Mobile</th>
																	<th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending">Board</th>
																	<th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending">Medium</th>
																	<th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Office: activate to sort column ascending">Class</th>
																	<th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Age: activate to sort column ascending">Section</th>
																	<th class="sorting" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Start date: activate to sort column ascending">Total Fee</th>
																	<th class="sorting" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Start date: activate to sort column ascending">Discount Fee</th>
																	<th class="sorting" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Start date: activate to sort column ascending">Net Fee</th>
																	<th class="sorting" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Salary: activate to sort column ascending">Paid Fee</th>
																	<th class="sorting" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Start date: activate to sort column ascending">Due Fee</th>
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
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script type="text/javascript">

$(document).ready(function () 
		{
			$("#fee").val("");
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
					+ '<th class="sorting" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending">Father Name</th>'
					+ '<th class="sorting" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending">Mobile</th>'
					+ '<th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending">Board</th>'
					+ '<th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending">Medium</th>'
					+ '<th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Office: activate to sort column ascending">Class</th>'
					+ '<th class="sorting hidden-sm hidden-xs" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Age: activate to sort column ascending">Section</th>'
					+ '<th class="sorting" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Start date: activate to sort column ascending">Total Fee</th>'
					+ '<th class="sorting" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Start date: activate to sort column ascending">Discount Fee</th>'
					+ '<th class="sorting" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Start date: activate to sort column ascending">Net Fee</th>'
					+ '<th class="sorting" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Salary: activate to sort column ascending">Paid Fee</th>'
					+ '<th class="sorting" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Start date: activate to sort column ascending">Due Fee</th>'
					+ '</tr>' + '</thead>' + '<tbody></tbody></table>';
			$('#basicTable').html(tableHead);
			$
					.each(
							listOrders,
							function(i, orderObj) {
								
// 								contactNumber":"wertewrt","mediumId":"16","subjectId":"","name":"0","boardid":"1","gender":null,"className":"","qualifaction":"ewrt","section":""
								serviceUnitArray[orderObj.id] = orderObj;
								var id = '"' + orderObj.id + '"';
								var tblRow = "<tr align='center' role='row' class='odd'>" + "<td'><a  id='"
										+ orderObj.id
										+ "' href='javascript:forOrderDetails("
										+ id
										+ ")' style='font-color:red'>"
										+ orderObj.id
										+ "</a></td>"
										+ "<td title='"+orderObj.studentName+"'>"
										+ '<a style="cursor: pointer;" title="View Student Fee History"  data-keyboard="false" data-backdrop="static" onclick=popupOpen('
										+ orderObj.studentId
										+ ')>'
										+ '<b>'
										+ orderObj.studentName
										+ '</b></a>'
										+ '</td>'
										+ "<td title='"+orderObj.fatherName+"'>"
										+ orderObj.fatherName
										+ "</td>"
										+ "<td title='"+orderObj.mobile+"'>"
										+ orderObj.mobile
										+ "</td>"
										+ "<td class='hidden-sm hidden-xs' title='"+orderObj.boardName+"'>"
										+ orderObj.boardName
										+ "</td>"
										+ "<td class='hidden-sm hidden-xs' title='"+orderObj.medium+"' >"
										+ orderObj.medium
										+ "</td>"
										+ "<td class='hidden-sm hidden-xs' title='"+orderObj.className+"'>"
										+ orderObj.className
										+ "</td>"
										+ "<td class='hidden-sm hidden-xs' title='"+orderObj.sectionName+"' >"
										+ orderObj.sectionName
										+ "</td>"
										+ "<td title='"+orderObj.totalFee+"' >"
										+ orderObj.totalFee
										+ "</td>"
										+ "<td title='"+orderObj.discountFee+"' >"
										+ orderObj.discountFee
										+ "</td>"
										+ "<td title='"+orderObj.netFee+"' >"
										+ orderObj.netFee
										+ "</td>"
										+ "<td title='"+orderObj.paidFee+"' >"
										+ orderObj.fee
										+ "</td>"
										+ "<td title='"+orderObj.remainBal+"' >"
										+ orderObj.dueFee
										+ "</td>"
										+ '</tr>';
								$(tblRow).appendTo("#basicExample tbody");
							});
			$('#basicExample').dataTable();
		} else {
			//alert('no data to display..');
		}
	}  
 	
	
	
	function popupOpen(id) {
		$('#printTab').text("");
		var studentFeeId = id;
		$.ajax({
					type : "POST",
					url : "getHistoryFee.json",
					data : "studentFeeId=" + studentFeeId,
					dataType : "json",
					success : function(response) {
						
						$('#myModal').modal();

						var tableHead = "<table align='center' class='table table-stripped table-bordered table-condensed' style='font-size: 13px;'>"
							+ '<thead>'
							+ '</thead><tbody><tr style="height: 35px;"><th>Date</th><th>Admission Fee</th><th>Tution Fee</th><th>Transportation Fee</th><th>Hostel Fee</th><th>Stationary Fee</th></tr>'
							+'</tbody></table>';
							$('#printTab').html(tableHead);
							$.each(response,function(i, tests) {
								
								console.log(tests+"---loop---"+i);
								if(i==0){
									var thead = "<tr><td colspan='2'></td><td colspan='2'><img src='img/ABV-header.png' style='height: 70px;'></td><td colspan='3'></td></tr>"
									+ "<tr style='height: 35px;'><td colspan='8'><b>Student Name: </b>&nbsp;&nbsp;"+ tests.studentName+ "</td></tr>"
									+ "<tr style='height: 35px;'><td colspan='8'><b>Father Name: </b>&nbsp;&nbsp;"+ tests.fatherName+ "</td></tr>"
									+ "<tr style='height: 35px;'><td colspan='8'><b>Mobile: </b>&nbsp;&nbsp;"+ tests.mobile+ "</td></tr>"
									+ "<tr style='height: 35px;'><td colspan='8'><b>Board: </b>&nbsp;&nbsp;"+ tests.boardName+ ",&nbsp;&nbsp;"
									+ 	"<b>Medium: </b>&nbsp;&nbsp;"+ tests.medium+ ",&nbsp;&nbsp;"+ "<b>Class: </b>&nbsp;&nbsp;"+ tests.className
									+ 	",&nbsp;&nbsp;"+ "<b>Section: </b>&nbsp;&nbsp;"+ tests.sectionName
									+ "</td></tr>"
								}
								
								
								var tbody="<tr><td>"+ tests.created_time+ "</td><td> "+ tests.admissionFee+ " </td><td>"+ tests.tutionFee+ " </td><td>"+ tests.transportationFee+ "</td><td>"+ tests.hostelFee+ " </td><td> "+ tests.stationaryFee+ "</td></tr>"
								
								$(thead).appendTo("#printTab thead");
								$(tbody).appendTo("#printTab tbody");
							});
					},
					error : function(e) {
						// 					alert('Error: ' + e);
					}
				});
	}
						
						
						/* 
						// 						alert(response);
						 $('#myModal').modal();

						var popuptitle = null;
						
						$.each(response,function(i, tests) {
						               
var stockInformation1 = "<table align='center' class='table table-stripped table-bordered table-condensed' id='stockInformationTable' style='font-size: 13px;'>"

+ "<tr><td colspan='2'></td><td colspan='2'><img src='img/ABV-header.png' style='height: 70px;'></td><td colspan='3'></td></tr>"
+ "<tr style='height: 35px;'><td colspan='8'><span style='float: right;font-size: normal;color: blue;'>Date: "+ tests.created_time+ "</span></td></tr>"

+ "<tr style='height: 35px;'><td colspan='8'><b>Student Name: </b>&nbsp;&nbsp;"+ tests.studentName+ "</td></tr>"
+ "<tr style='height: 35px;'><td colspan='8'><b>Father Name: </b>&nbsp;&nbsp;"+ tests.fatherName+ "</td></tr>"
+ "<tr style='height: 35px;'><td colspan='8'><b>Mobile: </b>&nbsp;&nbsp;"+ tests.mobile+ "</td></tr>"
+ "<tr style='height: 35px;'><td colspan='8'><b>Board: </b>&nbsp;&nbsp;"+ tests.boardName+ ",&nbsp;&nbsp;"
+ 	"<b>Medium: </b>&nbsp;&nbsp;"+ tests.medium+ ",&nbsp;&nbsp;"+ "<b>Class: </b>&nbsp;&nbsp;"+ tests.className
+ 	",&nbsp;&nbsp;"+ "<b>Section: </b>&nbsp;&nbsp;"+ tests.sectionName
+ "</td></tr>"

+ "<tr style='height: 35px;'><th>Date</th><th>Admission Fee</th><th>Tution Fee</th><th>Transportation Fee</th><th>Hostel Fee</th><th>Stationary Fee</th><th>Total</th></tr>"
+ "<tr><td> "+ tests.admissionFee+ " </td><td>"+ tests.tutionFee+ " </td><td>"+ tests.transportationFee+ "</td><td>"+ tests.hostelFee+ " </td><td> </td><td></td><td></td></tr>"
/* + "<tr style='height: 35px;'><td align='center'>Admission Fee</td><td align='center'>"+ tests.admissionFee+ "</td></tr>"
+ "<tr style='height: 35px;'><td align='center'>Tution Fee</td><td align='center'>"+ tests.tutionFee+ "</td></tr>"
+ "<tr style='height: 35px;'><td align='center'>Transportation Fee</td><td align='center'>"+ tests.transportationFee+ "</td></tr>"
+ "<tr style='height: 35px;'><td align='center'>Hostel Fee</td><td align='center'>"+ tests.hostelFee+ "</td></tr>"
+ "<tr style='height: 35px;'><td align='center'>Stationary Fee</td><td align='center'>"+ tests.stationaryFee+ "</td></tr>"
+ "<tr style='height: 35px;'><td align='center'>Amount Paid</td><td align='center'>"+ tests.fee+ "/-</td></tr>"
+ "<tr style='height: 35px;'><td align='right'><b>Total Amount:</b></td><td align=''>"+ tests.fee+ "/-</td></tr>" */
// + "<tr style='height: 35px;'><td colspan='2' id='totalId'><b>(Amount) in words: </b>"+ toWords(Math.round(tests.fee))+ "</td></tr>"
// + "</table>"
// + "<input id='printbtn' style='' class='btn btn-default' type='button' value='Print' onclick=PrintElem('#printTab') />"

// $(stockInformation1).appendTo("#printTab");
// toWords(tests.fee);

// 										});
						// 							 $(stockInformation2).appendTo("#stockInformationTable"); 
						// 							 $('#dial').dialog({width:799,title:popuptitle,modal: true}).dialog('open');

// 					 */},
// 					error : function(e) {
// 						// 					alert('Error: ' + e);
// 					}
// 				});
// 	}
	function PrintElem(elem) {
		$("#printbtn").hide();
		Popup($(elem).html());
	}

	function Popup(data) {
		var mywindow = window.open('', 'new div');
		mywindow.document.write('<html><head><title>Fees Receipt</title>');
		/*optional stylesheet*///mywindow.document.write('<link rel="stylesheet" href="css/main.css" type="text/css" />');
		mywindow.document.write('</head><body >');
		mywindow.document.write(data);
		mywindow.document.write('</body></html>');
		mywindow.print();
		mywindow.close();
		$("#printbtn").show();
		return true;
	}
	
	
	function serviceFilter(id){
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
					var id=tests.id;
					var name=tests.name;
					optionsForClass.append(new Option(name, id));
				});
				$('#medium').trigger("chosen:updated");
			},
			error : function(e) {
			},
			statusCode : {
				406 : function() {
			
				}
			}
		});
	} 
 	function deletefacultysubject(id){
 		var facultySubject = id;
		var count = 0;
		var checkstr =  confirm('Are you sure you want to delete this?');
		if(checkstr == true){
		  // do your code
		  
		  $.ajax({
					type : "POST",
					url : "deleteFacultySubject.json",
					data : "facultySubject=" + facultySubject ,
					success : function(response) {
// 						alert(response);
						displayTable(response);
						
					},
					error : function(e) {
					}
				});
		
		
			
		}else{
		return false;
		}
 	}
 	
 	function classNameFilter(id){
		var boardId = $("#boardName").val();
		if(boardId.length !=0){
		$.ajax({
			type : "POST",
			url : "getClassNameFilter.json",
			data : "boardId=" + boardId,
			dataType : "json",
			success : function(response) {
// 				 alert(response);  
				var optionsForClass = "";
				optionsForClass = $("#className").empty();
				optionsForClass.append(new Option("-- Choose Class --", ""));
				$.each(response, function(i, tests) {
					var id=tests.id;
					var className=tests.className;
					optionsForClass.append(new Option(className, id));
				});
				$('#className').trigger("chosen:updated");
			},
			error : function(e) {
			},
			statusCode : {
				406 : function() {
			
				}
			}
		});
		}
	} 
 	function sectionFilter(){
		var boardId = $("#boardName").val();
		var classId = $("#className").val();
		if(boardId.length !=0 && classId.length != 0){
		$.ajax({
			type : "POST",
			url : "getSectionFilter.json",
			data : "boardId=" + boardId+"&classId="+classId,
			dataType : "json",
			success : function(response) {
// 				 alert(response);  
				var optionsForClass = "";
				optionsForClass = $("#section").empty();
				optionsForClass.append(new Option("-- Choose Section --", ""));
				$.each(response, function(i, tests) {
					var id=tests.id;
					var sectionName=tests.sectionName;
					optionsForClass.append(new Option(sectionName, id));
				});
				$('#section').trigger("chosen:updated");
			},
			error : function(e) {
			},
			statusCode : {
				406 : function() {
			
				}
			}
		});
		}
	} 
 	function mediumFilter(){
		var boardId = $("#boardName").val();
		var classId = $("#className").val();
		var sectionId = $("#section").val();
		if(boardId.length !=0 && classId.length != 0 &&  sectionId.length != 0){
		$.ajax({
			type : "POST",
			url : "getMediumFilter.json",
			data : "boardId=" + boardId+"&classId="+classId+"&sectionId="+sectionId,
			dataType : "json",
			success : function(response) {
// 				 alert(response);  
				var optionsForClass = "";
				optionsForClass = $("#medium").empty();
				optionsForClass.append(new Option("-- Choose Medium --", ""));
				$.each(response, function(i, tests) {
					var id=tests.id;
					var mediumName=tests.mediumName;
					optionsForClass.append(new Option(mediumName, id));
				});
				$('#section').trigger("chosen:updated");
			},
			error : function(e) {
			},
			statusCode : {
				406 : function() {
			
				}
			}
		});
		}
	} 
 	function studentFilterDropdown(){
 		var boardId = $("#boardName").val();
		var classId = $("#className").val();
		var sectionId = $("#section").val();
		var mediumId = $("#medium").val();
		if(boardId.length !=0 && classId.length != 0 &&  sectionId.length != 0 && mediumId.length !=0){
		$.ajax({
			type : "POST",
			url : "studentFilterDropdown.json",
			data : "boardId=" + boardId+"&classId="+classId+"&sectionId="+sectionId+"&mediumId="+mediumId,
			dataType : "json",
			success : function(response) {
// 				 alert(response);  
				var optionsForClass = "";
				optionsForClass = $("#studentId").empty();
				optionsForClass.append(new Option("-- Choose Student --", ""));
				$.each(response, function(i, tests) {
					var studentId=tests.studentId;
					var studentName=tests.studentName;
					optionsForClass.append(new Option(studentName, studentId));
				});
				$('#studentId').trigger("chosen:updated");
			},
			error : function(e) {
			},
			statusCode : {
				406 : function() {
			
				}
			}
		});
		}
 	}
 	

 	function searchTable() {
 	    var input, filter, found, table, tr, td, i, j;
 	    input = document.getElementById("search");
 	    filter = input.value.toUpperCase();
 	    table = document.getElementById("itemContainer");
 	    tr = table.getElementsByTagName("tr");
 	    for (i = 0; i < tr.length; i++) {
 	        td = tr[i].getElementsByTagName("td");
 	        for (j = 0; j < td.length; j++) {
 	            if (td[j].innerHTML.toUpperCase().indexOf(filter) > -1) {
 	                found = true;
 	            }
 	        }
 	        if (found) {
 	            tr[i].style.display = "";
 	            found = false;
 	        } else {
 	            tr[i].style.display = "none";
 	        }
 	    }
 	}
 	
 	 
    function searchStudetnFee(){
 	var studentId	=$('#studentId').val();
 	var classId	=$('#className').val();
 	var boardId	=$('#boardName').val();
 	var sectionId=	$('#section').val();
 	var mediumId=	$('#medium').val();
 	var studentId = $('#studentId').val();
 	$.ajax({
 		type : "POST",
 		url : "searchStudetnFee.json",
 		data : "studentId="+ studentId+"&classId="+classId+"&sectionId="+sectionId+"&mediumId="+mediumId+"&boardId="+boardId+"&studentId="+studentId,
 		dataType : "json",
 		success : function(response) {
//  				 alert(response);  
 				 displayTable(response)
 			
 		},
 		error : function(e) {
 		},
 		statusCode : {
 			406 : function() {
 		
 			}
 		}
 	});
 	   
 	   
    }
</script>
