<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<link href="css/datepicker1.css" rel="stylesheet">
	<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js"></script>
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
				<div class="page-title">DFC</div>
			</div>
			<!-- Top Bar ends -->

			<!-- Main Container starts -->
			<div class="main-container">

				<!-- Container fluid Starts -->
				<div class="container-fluid">

					<!-- Spacer starts -->
					<ol class="breadcrumb">
					    	<li><a href="dashBoard">Home</a></li>
					    	<li><a href="#">dailyFeesCollection</a></li>
						    </ol>
				<div class="panel panel-info">
					<div class="panel-heading">
						<h4>DFC</h4>
					</div>
					<div class="panel-body collapse in">						
						
						<!-- Row Starts -->
						<div class="row">
							<div class="col-sm-6">
										<form:form id="dfc-form" commandName="dfc" method="post" class="form-horizontal">
										
										
										<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12">
											  	<div class="form-group">
												    <label  class="col-sm-4 control-label">From <span style="color: red;">*</span></label>
												    <div class="col-sm-5">
														<form:input path="from" data-format="dd-MM-yyyy" placeholder="Date" class="form-control validate" type="text"  onfocus="removeBorder(this.id)" />
														<span class="dob1_error" id="dob1_error"></span>
													</div>
											  	</div>
											</div>
											<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12">
											  	<div class="form-group">
												    <label  class="col-sm-4 control-label">To <span style="color: red;">*</span></label>
												    <div class="col-sm-5">
														<form:input path="to" data-format="dd-MM-yyyy" placeholder="Date" class="form-control validate" type="text" onfocus="removeBorder(this.id)" />
														<span class="dob1_error" id="dob1_error"></span>
													</div>
											  	</div>
											</div>
											
											<div class="col-sm-12">
												<div class="col-sm-8 col-sm-offset-2">
													<input type="submit" id="submitId" value="Get DFC" class="btn btn-success"	/>
													<button type="button" class="btn btn-danger" id="cancel"	tabindex="9">Reset</button>
												</div>
											</div>
										
										</form:form>
									</div>
								</div>
								<!-- Row Ends -->
							</div>
						</div>
						
				
							<!-- Row Starts -->
							<div class="row gutter">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="panel panel-info">
						<div class="panel-heading">
							<h4>List of DFC</h4>
						</div>
						<div class="panel-body collapse in">	
											<div class="table-responsive">
												<div id="basicExample_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">
													<div class="row">
														<div class="col-sm-12">
														<div id="basicTable">
	 														<table id="basicExample" class="table table-striped table-condensed table-bordered no-margin dataTable" role="grid" aria-describedby="basicExample_info">
																<thead>
																	<tr role="row">
																		<th class="sorting_asc" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Name: activate to sort column descending">Date</th>
																		<th class="sorting" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending">Total Amount</th>
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
							
							<!-- Row Starts -->
							<div class="row gutter">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="panel panel-info">
						<div class="panel-heading">
							<h4>List of DFC</h4>
						</div>
						<div class="panel-body collapse in">	
											<div class="table-responsive">
												<div id="betweenDatesDiv" class="dataTables_wrapper form-inline dt-bootstrap">
													<div class="row">
														<div class="col-sm-12">
														<div id="basicTable">
	 														<table id="betweenDatesTable" class="table table-striped table-condensed table-bordered no-margin dataTable" role="grid" aria-describedby="basicExample_info">
																<thead>
																	<tr role="row">
																		<th class="sorting_asc" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Name: activate to sort column descending">Date</th>
																		<th class="sorting" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending">Total Amount</th>
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
				<!-- Container fluid ends -->

				</div>
			<!-- Main Container ends -->

	
			</div>
		<!-- Dashboard Wrapper ends -->


<script type="text/javascript">

$(function () {
	
	$('#basicExample').dataTable();
	
	$("#from,#to").datepicker({
		changeDate : true,
		changeMonth : true,
		changeYear : true,
		yearRange: "-50:+0",
		showButtonPanel : false,
// 		minDate: '-50Y',
	    maxDate: '0', 
		dateFormat : 'dd-MM-yy'
	});
	
		 
		 $("#dfc-form").validate({
				errorElement : 'span',
				errorClass : 'has-error',
				rules : {
					 from:{required:true},
					 to:{required:true},  
					
				},
				messages : {
					from:{required:'Select Date '},
					to:{required:'Select Date'},
					
				},
				errorPlacement: function(error, element){
				      if(element.attr("name") == "from")
				        error.insertAfter(".from_error").css("color", "red");
				      else if(element.attr("name") == "to")
				        error.insertAfter(".to_error").css("color", "red"); 
				      					    
				      else
				        error.insertAfter(element);
				      }
			});

			var getTabName = window.location.pathname.split('/')[2];
			$("#stu_li").addClass('active');
			$("#stu_li ul").css('display','block');
			$("#stu_li ul li a[href='"+ getTabName +"']").addClass('subactive');
			
			$('#cancel').click(function () {
			  	$("#dfc-form").validate().resetForm();
			    $("#dfc-form").removeClass("has-error");
			    $("#from").val('');
			    $("#to").val('');
			});
			

	 
});
//remove borders
function removeBorder(el){	
	  $("#"+el).css("border", "");
	  $("#"+el).css('color','black');
	  $('#'+el).addClass('default-class');
	  if ($("#" + el+"_chosen").length)
		{
			$("#" +el+"_chosen").children('a').css('border-color','black');
		}
}
		
	 var listOrders1 = ${dfcList};
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
					+ '<th class="sorting_asc" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Name: activate to sort column descending">DATE</th>'
					+ '<th class="sorting_asc" tabindex="0" aria-controls="basicExample" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Name: activate to sort column descending">AMOUNT</th>'
					+ '</tr>' + '</thead>' + '<tbody></tbody></table>';
			$('#basicTable').html(tableHead);
								
								
								var tblRow = "<tr align='center' role='row' class='odd'>" 
										+ "<td title='"+listOrders.createdTime+"'>"
										+ listOrders.createdTime
										+ "</td>"
										+ "<td title='"+listOrders.total+"'>"
										+ listOrders.total
										+ "</td>"
										+ "</tr>";
								$(tblRow).appendTo("#basicExample tbody");
			
		} 
	}  
 	
	
</script>
